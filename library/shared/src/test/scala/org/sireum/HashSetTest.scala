/*
 * Copyright (c) 2017-2025, Robby, Kansas State University
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sireum

import org.sireum.test._

class HashSetTest extends TestSuite {

  val tests = Tests {

    * - assert(HashSet.empty[String].size =~ z"0")

    * - assert(!HashSet.empty[String].contains("a"))

    * - assert(HashSet.empty[String].+("a").contains("a"))

    * - assert(!HashSet.empty[String].+("a").contains("A"))

    * - assert(HashSet.empty[String].+("a").+("b").contains("a"))

    * - assert(HashSet.empty[String].+("a").+("b").contains("b"))

    * - assert(HashSet.empty[String].++(ISZ("a", "b")).-("a").-("b").isEmpty)

    * - assert(
      HashSet
        .empty[String]
        .++(ISZ("a", "b")) =~ HashSet.empty[String].+("b").+("a"))

    * - assert(
      HashSet.empty[String].∩(HashSet.empty[String].+("A")) =~ HashSet
        .empty[String])

    * - assert(
      HashSet.empty[String].∪(HashSet.empty[String].+("A")) =~ HashSet
        .empty[String]
        .+("A"))

    * - assert(
      HashSet.empty[String].+("a").∩(HashSet.empty[String].+("A")) =~ HashSet
        .empty[String])

  }
}
