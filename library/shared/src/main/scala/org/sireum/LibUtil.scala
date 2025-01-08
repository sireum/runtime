// #Sireum
/*
 Copyright (c) 2017-2025, Robby, Kansas State University
 ∀ rights reserved.

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

object LibUtil {

  type OptionMap = HashMap[String, ISZ[String]]
  type FileOptionMap = HashMap[Option[String], OptionMap]

  val setOptions: String = "setOptions"

  object IS {

    @strictpure def unique[@index I, T](s: IS[I, T]): B = ∀(s.indices)(i => ∀(s.indices)(j => (i != j) ->: (s(i) != s(j))))

  }

  def parCores(maxCores: Z, percentage: Z): Z = {
    val r = percentage * maxCores / 100
    return if (r <= 1) 1 else if (r >= maxCores) maxCores else r
  }

  def parCoresOpt(maxCores: Z, percentageOpt: Option[Z]): Z = {
    val r: Z = percentageOpt match {
      case Some(v) => parCores(maxCores, v)
      case _ => 1
    }
    return r
  }

  @pure def mineOptions(fileContent: String): OptionMap = {
    return mineOptionsWithPrefix("//", fileContent)
  }

  @pure def mineOptionsWithPrefix(prefix: String, fileContent: String): OptionMap = {
    var r: OptionMap = HashMap.empty
    val lineCommentPrefix = conversions.String.toCis(prefix)
    val optPrefix = conversions.String.toCis(s"$prefix@")

    var key: String = ""
    var curr = ISZ[String]()
    for (line <- ops.StringOps(fileContent).cisLineStream.
      dropWhile((cis: ISZ[C]) => !ops.StringOps.startsWith(cis, lineCommentPrefix)).
      takeWhile((cis: ISZ[C]) => ops.StringOps.startsWith(cis, lineCommentPrefix) ||
        ops.StringOps.trim(cis).size == 0) if ops.StringOps.startsWith(line, optPrefix)) {
      val (start, end, continu): (Z, Z, B) = if (key.size == 0) {
        val i = ops.StringOps.indexOfFrom(line, ':', optPrefix.size)
        if (i < 0) {
          return r
        }
        key = ops.StringOps(ops.StringOps.substring(line, optPrefix.size, i)).trim
        if (line(line.size - 1) == '\\') {
          (i + 1, line.size - 1, T)
        } else {
          (i + 1, line.size, F)
        }
      } else {
        if (line(line.size - 1) == '\\') {
          (optPrefix.size, line.size - 1, T)
        } else {
          (optPrefix.size, line.size, F)
        }
      }
      if (continu) {
        curr = curr :+ ops.StringOps(ops.StringOps.substring(line, start, end)).trim
      } else {
        curr = curr :+ ops.StringOps(ops.StringOps.substring(line, start, end)).trim
        r = r + key ~> (r.get(key).getOrElse(ISZ()) :+ st"${(curr, " ")}".render)
        key = ""
        curr = ISZ()
      }
    }

    if (key.size != 0 && curr.nonEmpty) {
      r = r + key ~> (r.get(key).getOrElse(ISZ()) :+ st"${(curr, " ")}".render)
    }
    return r
  }

}
