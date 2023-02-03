/*
 Copyright (c) 2017-2023, Robby, Kansas State University
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

object Random_Ext {

  val _gen64: _root_.java.lang.ThreadLocal[Random.Gen64] = new _root_.java.lang.ThreadLocal[Random.Gen64]

  def gen64: Random.Gen64 = {
    var r = _gen64.get()
    if (r == null) {
      r = Random.create64
      _gen64.set(r)
    }
    r
  }

  def setSeed(seed: Z): Unit = {
    var r = seed
    if (r < 0) {
      r = -r
    }
    r = r % (U64.Max.toZ + 1)
    val sm = Random.Xoroshiro.SplitMix64(U64.fromZ(r))
    val g64 = _gen64.get
    g64.gen.seed0 = sm.next()
    g64.gen.seed1 = sm.next()
    g64.gen.seed2 = sm.next()
    g64.gen.seed3 = sm.next()
  }
}
