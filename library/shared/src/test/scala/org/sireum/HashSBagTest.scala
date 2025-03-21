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

class HashSBagTest extends TestSuite {

  val tests = Tests {

    * - assert(HashSBag.empty[String].size =~ z"0")

    * - assert(!HashSBag.empty[String].contains("a"))

    * - assert(HashSBag.empty[String].+("a").contains("a"))

    * - assert(HashSBag.empty[String].+("a").+("a").count("a") == 2)

    * - assert(HashSBag.empty[String].addN("a", 4).+("a").count("a") == 5)

    * - assert(HashSBag.empty[String].addN("a", 4).-("a").count("a") == 3)

    * - assert(HashSBag.empty[String].addN("a", 4).removeN("a", 10).count("a") == 0)

    * - assert(!HashSBag.empty[String].+("a").contains("A"))

    * - assert(HashSBag.empty[String].+("a").+("b").contains("a"))

    * - assert(HashSBag.empty[String].+("a").+("b").contains("b"))

    * - assert(HashSBag.empty[String].++(ISZ("a", "b")).-("a").-("b").isEmpty)

    * - assert(HashSBag.empty[String].++(ISZ("a", "b")) =~ HashSBag.empty[String].+("a").+("b"))

    * - assert(HashSBag.empty[String].∪(HashSBag.empty[String].+("A")) =~ HashSBag.empty[String].+("A"))

    * - assert(HashSBag.empty[String].∩(HashSBag.empty[String].+("A")) =~ HashSBag.empty[String])

    * - assert(HashSBag.empty[String].+("a").∩(HashSBag.empty[String].+("A")) =~ HashSBag.empty[String])

  }

}
