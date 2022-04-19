// #Sireum #Logika
/*
 Copyright (c) 2017-2022, Robby, Kansas State University
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

@datatype trait Either[L, R] {

  @pure def isLeft: B = Contract.Only(Ensures(Res == ∃{e: L => Either.Left[L, R](e) == this}))

  @pure def isRight: B = Contract.Only(Ensures(!isLeft))

  @pure def leftOpt: Option[L] = Contract.Only(
    Case(
      "Left",
      Requires(isLeft),
      Ensures(Either.Left[L, R](Res[Option[L]].get) == this)
    ),
    Case(
      "Right",
      Requires(isRight),
      Ensures(Res == None[L]())
    ),
  )

  @pure def left: L = Contract.Only(
    Requires(isLeft),
    Ensures(Either.Left[L, R](Res) == this)
  )

  @pure def rightOpt: Option[R] = Contract.Only(
    Case(
      "Left",
      Requires(isLeft),
      Ensures(Res == None[R]())
    ),
    Case(
      "Right",
      Requires(isRight),
      Ensures(Either.Right[L, R](Res[Option[R]].get) == this)
    )
  )

  @pure def right: R = Contract.Only(
    Requires(isRight),
    Ensures(Either.Right[L, R](Res) == this)
  )
}

object Either {

  @datatype class Left[L, R](val value: L) extends Either[L, R] {

    @pure override def isLeft: B = {
      Contract(Ensures(Res))
      return T
    }

    @pure override def isRight: B = {
      Contract(Ensures(!Res[B]))
      return F
    }

    @pure override def leftOpt: Option[L] = {
      Contract(Ensures(Res == Some(value)))
      return Some(value)
    }

    @pure override def left: L = {
      Contract(Ensures(Res == value))
      return value
    }

    @pure override def rightOpt: Option[R] = {
      Contract(Ensures(Res == None[R]()))
      return None()
    }

    @pure override def right: R = {
      Contract(Requires(F))
      halt("Invalid 'Either.Left' operation 'right'.")
    }

  }

  @datatype class Right[L, R](val value: R) extends Either[L, R] {

    @pure override def isLeft: B = {
      Contract(Ensures(!Res[B]))
      return F
    }

    @pure override def isRight: B = {
      Contract(Ensures(Res))
      return T
    }

    @pure override def leftOpt: Option[L] = {
      Contract(Ensures(Res == None[L]()))
      return None()
    }

    @pure override def left: L = {
      Contract(Requires(F))
      halt("Invalid 'Either.Right' operation 'left'.")
    }

    @pure override def rightOpt: Option[R] = {
      Contract(Ensures(Res == Some(value)))
      return Some(value)
    }

    @pure override def right: R = {
      Contract(Ensures(Res == value))
      return value
    }

  }

  @strictpure def left[L, R](value: L): Either[L, R] = Left[L, R](value)

  @strictpure def right[L, R](value: R): Either[L, R] = Right[L, R](value)

}
