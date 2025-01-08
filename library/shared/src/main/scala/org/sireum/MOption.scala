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

object MOption {

  @strictpure def some[T](value: T): MOption[T] = MSome(value)

  @strictpure def none[T](): MOption[T] = MNone()

}

@record trait MOption[T] {

  @pure def isEmpty: B = Contract.Only(
    Ensures((this ≡ MNone[T]()) == Res)
  )

  @pure def nonEmpty: B = Contract.Only(
    Ensures((this ≢ MNone[T]()) == Res)
  )

  @pure def map[T2](f: T => T2 @pure): MOption[T2] = Contract.Only(
    Case(
      "Empty",
      Requires(this ≡ MNone[T]()),
      Ensures(MNone[T2]() ≡ Res)
    ),
    Case(
      "Non-empty",
      Requires(this ≢ MNone[T]()),
      Ensures(MSome(f(get)) ≡ Res)
    )
  )

  @pure def flatMap[T2](f: T => MOption[T2] @pure): MOption[T2] = Contract.Only(
    Case(
      "Empty",
      Requires(this ≡ MNone[T]()),
      Ensures(MNone[T2]() ≡ Res)
    ),
    Case(
      "Non-empty",
      Requires(this ≢ MNone[T]()),
      Ensures(f(get) ≡ Res)
    )
  )

  @pure def forall(f: T => B @pure): B = Contract.Only(
    Case(
      "Empty",
      Requires(this ≡ MNone[T]()),
      Ensures(Res)
    ),
    Case(
      "Non-empty",
      Requires(this ≢ MNone[T]()),
      Ensures(f(get) == Res)
    )
  )

  @pure def exists(f: T => B @pure): B = Contract.Only(
    Case(
      "Empty",
      Requires(this ≡ MNone[T]()),
      Ensures(!Res[B])
    ),
    Case(
      "Non-empty",
      Requires(this ≢ MNone[T]()),
      Ensures(f(get) == Res)
    )
  )

  @pure def get: T = Contract.Only(
    Requires(this ≢ MNone[T]()),
    Ensures(this ≡ MSome(Res))
  )

  @pure def getOrElse(default: => T): T = Contract.Only(
    Case(
      "Empty",
      Requires(this ≡ MNone[T]()),
      Ensures(default ≡ Res)
    ),
    Case(
      "Non-empty",
      Requires(this ≢ MNone[T]()),
      Ensures(this ≡ MSome(Res))
    )
  )

  @pure def getOrElseEager(default: T): T = Contract.Only(
    Case(
      "Empty",
      Requires(this ≡ MNone[T]()),
      Ensures(default ≡ Res)
    ),
    Case(
      "Non-empty",
      Requires(this ≢ MNone[T]()),
      Ensures(this ≡ MSome(Res))
    )
  )

  @pure def toMS: MSZ[T] = Contract.Only(
    Case(
      "Empty",
      Requires(this ≡ MNone[T]()),
      Ensures(MSZ[T]() ≡ Res)
    ),
    Case(
      "Non-empty",
      Requires(this ≢ MNone[T]()),
      Ensures(MSZ[T](get) ≡ Res)
    )
  )

  def foreach[V](f: T => V): Unit
}

@record class MNone[T] extends MOption[T] {

  @pure override def isEmpty: B = {
    Contract(Ensures(Res))
    return T
  }

  @pure override def nonEmpty: B = {
    Contract(Ensures(!Res[B]))
    return F
  }

  @pure override def map[T2](f: T => T2 @pure): MOption[T2] = {
    Contract(Ensures(MNone[T2]() ≡ Res))
    return MNone[T2]()
  }

  @pure override def flatMap[T2](f: T => MOption[T2] @pure): MOption[T2] = {
    Contract(Ensures(MNone[T2]() ≡ Res))
    return MNone[T2]()
  }

  @pure override def forall(f: T => B @pure): B = {
    Contract(Ensures(Res))
    return T
  }

  @pure override def exists(f: T => B @pure): B = {
    Contract(Ensures(!Res[B]))
    return F
  }

  @pure override def getOrElse(default: => T): T = {
    Contract(Ensures(default ≡ Res))
    return default
  }

  @pure override def getOrElseEager(default: T): T = {
    Contract(Ensures(default ≡ Res))
    return default
  }

  @pure override def get: T = {
    Contract(Requires(F))
    halt("Invalid 'MNone' operation 'get'.")
  }

  @pure override def toMS: MS[Z, T] = {
    Contract(Ensures(MSZ[T]() == Res))
    return MS[Z, T]()
  }

  def foreach[V](f: T => V): Unit = {}
}

@record class MSome[T](val value: T) extends MOption[T] {

  @pure override def isEmpty: B = {
    Contract(Ensures(!Res[B]))
    return F
  }

  @pure override def nonEmpty: B = {
    Contract(Ensures(Res))
    return T
  }

  @pure override def map[T2](f: T => T2 @pure): MOption[T2] = {
    Contract(Ensures(MSome(f(value)) ≡ Res))
    return MSome(f(value))
  }

  @pure override def flatMap[T2](f: T => MOption[T2] @pure): MOption[T2] = {
    Contract(Ensures(f(value) ≡ Res))
    return f(value)
  }

  @pure override def forall(f: T => B @pure): B = {
    Contract(Ensures(f(value) == Res))
    return f(value)
  }

  @pure override def exists(f: T => B @pure): B = {
    Contract(Ensures(f(value) == Res))
    return f(value)
  }

  @pure override def getOrElse(default: => T): T = {
    Contract(Ensures(value ≡ Res))
    return value
  }

  @pure override def getOrElseEager(default: T): T = {
    Contract(Ensures(value ≡ Res))
    return value
  }

  @pure override def get: T = {
    Contract(Ensures(value ≡ Res))
    return value
  }

  @pure override def toMS: MS[Z, T] = {
    Contract(Ensures(MSZ(value) ≡ Res))
    return MSZ(value)
  }

  override def foreach[V](f: T => V): Unit = {
    f(value)
  }
}
