// #Sireum #Logika
/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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

@record trait MEither[L, R] {

  @pure def isLeft: B = Contract.Only(Ensures(this.isInstanceOf[MEither.Left[L, R]] == Res))

  @pure def isRight: B = Contract.Only(Ensures(this.isInstanceOf[MEither.Right[L, R]] == Res))

  @pure def leftOpt: MOption[L] = Contract.Only(
    Case(
      "Left",
      Requires(this.isInstanceOf[MEither.Left[L, R]]),
      Ensures(this ≡ MEither.Left[L, R](Res[MOption[L]].get))
    ),
    Case(
      "Right",
      Requires(this.isInstanceOf[MEither.Right[L, R]]),
      Ensures(MNone[L]() ≡ Res)
    )
  )

  @pure def left: L = Contract.Only(
    Requires(this.isInstanceOf[MEither.Left[L, R]]),
    Ensures(this ≡ MEither.Left[L, R](Res))
  )

  @pure def rightOpt: MOption[R] = Contract.Only(
    Case(
      "Left",
      Requires(this.isInstanceOf[MEither.Left[L, R]]),
      Ensures(MNone[R]() ≡ Res)
    ),
    Case(
      "Right",
      Requires(this.isInstanceOf[MEither.Right[L, R]]),
      Ensures(this ≡ MEither.Right[L, R](Res[MOption[R]].get))
    )
  )

  @pure def right: R
}

object MEither {

  @record class Left[L, R](val value: L) extends MEither[L, R] {

    @pure override def isLeft: B = {
      Contract(Ensures(Res))
      return T
    }

    @pure override def isRight: B = {
      Contract(Ensures(!Res[B]))
      return F
    }

    @pure override def leftOpt: MOption[L] = {
      Contract(Ensures(MSome(value) ≡ Res))
      return MSome(value)
    }

    @pure override def left: L = {
      Contract(Ensures(value ≡ Res))
      return value
    }

    @pure override def rightOpt: MOption[R] = {
      Contract(Ensures(MNone[R]() ≡ Res))
      return MNone()
    }

    @pure override def right: R = {
      Contract(Requires(F))
      halt("Invalid 'MEither.Left' operation 'right'.")
    }

  }

  @record class Right[L, R](val value: R) extends MEither[L, R] {

    @pure override def isLeft: B = {
      Contract(Ensures(!Res[B]))
      return F
    }

    @pure override def isRight: B = {
      Contract(Ensures(Res))
      return T
    }

    @pure override def leftOpt: MOption[L] = {
      Contract(Ensures(MNone[L]() ≡ Res))
      return MNone()
    }

    @pure override def left: L = {
      Contract(Requires(F))
      halt("Invalid 'MEither.Right' operation 'left'.")
    }

    @pure override def rightOpt: MOption[R] = {
      Contract(Ensures(MSome(value) ≡ Res))
      return MSome(value)
    }

    @pure override def right: R = {
      Contract(Ensures(value ≡ Res))
      return value
    }

  }

  @strictpure def left[L, R](value: L): MEither[L, R] = Left(value)

  @strictpure def right[L, R](value: R): MEither[L, R] = Right(value)

}
