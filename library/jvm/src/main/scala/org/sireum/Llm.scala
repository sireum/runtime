// #Sireum
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

// LLM parameters.
// - model:       model name (e.g., "claude-sonnet-4-6", "gpt-4o", "llama3.2")
// - maxTokens:   maximum tokens in the completion
// - temperature: sampling temperature (0.0–1.0)
// - systemPrompt: optional system prompt (empty = none)
// - apiKey:      API key; empty = read SIREUM_LLM_API_KEY environment variable
// - endpoint:    API base URL; empty = auto-detect from model name:
//                  claude-* → https://api.anthropic.com/v1/messages
//                  others   → https://api.openai.com/v1/chat/completions
//                  (set to http://localhost:11434/v1/chat/completions for Ollama)
@datatype class LlmParams(model: String,
                           maxTokens: Z,
                           temperature: F64,
                           systemPrompt: String,
                           apiKey: String,
                           endpoint: String)

@ext object Llm {

  // Returns default LlmParams, reading SIREUM_LLM_MODEL / SIREUM_LLM_API_KEY from env.
  def params(model: String): LlmParams = $

  // Sends prompt to the LLM and returns the completion text.
  // Halts on HTTP error or missing API key.
  def complete(prompt: String, params: LlmParams): String = $

}
