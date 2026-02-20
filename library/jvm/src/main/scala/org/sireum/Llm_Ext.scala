/*
 Copyright (c) 2017-2026, Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sireum

import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}
import java.nio.charset.StandardCharsets
import com.fasterxml.jackson.databind.ObjectMapper

object Llm_Ext {

  private lazy val client: HttpClient = HttpClient.newHttpClient()
  private lazy val mapper: ObjectMapper = new ObjectMapper()

  def params(model: String): LlmParams = {
    val m: Predef.String = if (model.value.nonEmpty) model.value
                           else { val e = System.getenv("SIREUM_LLM_MODEL"); if (e != null) e else "claude-sonnet-4-6" }
    LlmParams(
      model = String(m),
      maxTokens = Z.MP(4096),
      temperature = F64(0.7),
      systemPrompt = String(""),
      apiKey = String(""),
      endpoint = String("")
    )
  }

  def complete(prompt: String, p: LlmParams): String = {
    val model: Predef.String = p.model.value
    val apiKey: Predef.String = {
      val k = p.apiKey.value
      if (k.nonEmpty) k
      else { val e = System.getenv("SIREUM_LLM_API_KEY"); if (e != null) e
             else halt(String("Llm.complete: no API key — set SIREUM_LLM_API_KEY or provide in LlmParams.apiKey")) }
    }
    val isAnthropic: scala.Boolean = model.startsWith("claude")
    val endpoint: Predef.String = {
      val e = p.endpoint.value
      if (e.nonEmpty) e
      else if (isAnthropic) "https://api.anthropic.com/v1/messages"
      else "https://api.openai.com/v1/chat/completions"
    }
    val body: Predef.String = if (isAnthropic) anthropicBody(p, prompt) else openAIBody(p, prompt)
    val reqBuilder = HttpRequest.newBuilder()
      .uri(URI.create(endpoint))
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
    val req = if (isAnthropic) reqBuilder
      .header("x-api-key", apiKey)
      .header("anthropic-version", "2023-06-01")
      .build()
    else reqBuilder
      .header("Authorization", s"Bearer $apiKey")
      .build()
    val resp = client.send(req, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
    if (resp.statusCode() != 200) {
      halt(String(s"Llm.complete: HTTP ${resp.statusCode()}: ${resp.body()}"))
    }
    val json = mapper.readTree(resp.body())
    val text: Predef.String =
      if (isAnthropic) json.get("content").get(0).get("text").asText()
      else json.get("choices").get(0).get("message").get("content").asText()
    String(text)
  }

  private def anthropicBody(p: LlmParams, prompt: String): Predef.String = {
    val sys: Predef.String =
      if (p.systemPrompt.value.nonEmpty) s""","system":${mapper.writeValueAsString(p.systemPrompt.value)}"""
      else ""
    s"""{
      "model":${mapper.writeValueAsString(p.model.value)},
      "max_tokens":${p.maxTokens.toLong},
      "temperature":${p.temperature.value}$sys,
      "messages":[{"role":"user","content":${mapper.writeValueAsString(prompt.value)}}]
    }"""
  }

  private def openAIBody(p: LlmParams, prompt: String): Predef.String = {
    val msgs: Predef.String =
      if (p.systemPrompt.value.nonEmpty)
        s"""[{"role":"system","content":${mapper.writeValueAsString(p.systemPrompt.value)}},{"role":"user","content":${mapper.writeValueAsString(prompt.value)}}]"""
      else
        s"""[{"role":"user","content":${mapper.writeValueAsString(prompt.value)}}]"""
    s"""{
      "model":${mapper.writeValueAsString(p.model.value)},
      "max_tokens":${p.maxTokens.toLong},
      "temperature":${p.temperature.value},
      "messages":$msgs
    }"""
  }

}
