/*
 * Copyright (c) 2016, Robby, Kansas State University
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

package org.sireum.logika

import org.apfloat.Apint

package object math {
  final val defaultBitWidth = {
    def err = sys.error("org.sireum.logika.math.bitWidth should be either 8, 16, 32, or 64.")
    try Option(System.getProperty("org.sireum.logika.math.bitWidth")) match {
      case Some(v) =>
        val n = v.toInt
        n match {
          case 8 | 16 | 32 | 64 => n
          case _ => err
        }
      case _ => 0
    } catch {
      case _: Throwable => err
    }
  }

  trait LogikaNumberCompanion {
    def random: LogikaNumber
  }

  trait LogikaNumber {
    def toBigInteger: java.math.BigInteger

    def toBigInt: BigInt

    def toApint: Apint

    def toZ: Z

    final def toZ8: Z8 = Z8.checkRange(toZ)

    final def toZ16: Z16 = Z16.checkRange(toZ)

    final def toZ32: Z32 = Z32.checkRange(toZ)

    final def toZ64: Z64 = Z64.checkRange(toZ)

    final def toN: N = math.N(toZ)

    final def toN8: N8 = N8.checkRange(toZ)

    final def toN16: N16 = N16.checkRange(toZ)

    final def toN32: N32 = N32.checkRange(toZ)

    final def toN64: N64 = N64.checkRange(toZ)
  }

}
