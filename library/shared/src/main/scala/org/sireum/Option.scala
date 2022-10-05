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

object Option {

  @strictpure def some[T](value: T): Option[T] = Some(value)

  @strictpure def none[T](): Option[T] = None()
}

@datatype trait Option[T] {

  @pure def isEmpty: B = Contract.Only(
    Ensures((this === None[T]()) == Res)
  )

  @pure def nonEmpty: B = Contract.Only(
    Ensures(!isEmpty == Res)
  )

  @pure def map[T2](f: T => T2 @pure): Option[T2] = Contract.Only(
    Case(
      "Empty",
      Requires(isEmpty),
      Ensures(None[T2]() === Res)
    ),
    Case(
      "Non-empty",
      Requires(nonEmpty),
      Ensures(Some(f(get)) === Res)
    )
  )

  @pure def flatMap[T2](f: T => Option[T2] @pure): Option[T2] = Contract.Only(
    Case(
      "Empty",
      Requires(isEmpty),
      Ensures(None[T2]() === Res)
    ),
    Case(
      "Non-empty",
      Requires(nonEmpty),
      Ensures(f(get) === Res)
    )
  )

  @pure def forall(f: T => B @pure): B = Contract.Only(
    Case(
      "Empty",
      Requires(isEmpty),
      Ensures(Res)
    ),
    Case(
      "Non-empty",
      Requires(nonEmpty),
      Ensures(f(get) == Res)
    )
  )

  @pure def exists(f: T => B @pure): B = Contract.Only(
    Case(
      "Empty",
      Requires(isEmpty),
      Ensures(!Res[B])
    ),
    Case(
      "Non-empty",
      Requires(nonEmpty),
      Ensures(f(get) == Res)
    )
  )

  @pure def get: T = Contract.Only(
    Requires(nonEmpty),
    Ensures(this === Some(Res))
  )

  @pure def getOrElse(default: => T): T = Contract.Only(
    Case(
      "Empty",
      Requires(isEmpty),
      Ensures(default === Res)
    ),
    Case(
      "Non-empty",
      Requires(nonEmpty),
      Ensures(this === Some(Res))
    )
  )

  @pure def getOrElseEager(default: T): T = Contract.Only(
    Case(
      "Empty",
      Requires(isEmpty),
      Ensures(default === Res)
    ),
    Case(
      "Non-empty",
      Requires(nonEmpty),
      Ensures(this === Some(Res))
    )
  )

  @pure def toIS: ISZ[T] = Contract.Only(
    Case(
      "Empty",
      Requires(isEmpty),
      Ensures(ISZ[T]() === Res)
    ),
    Case(
      "Non-empty",
      Requires(nonEmpty),
      Ensures(ISZ[T](get) === Res)
    )
  )

  def foreach[V](f: T => V): Unit
}

@datatype class None[T] extends Option[T] {

  @pure override def isEmpty: B = {
    Contract(Ensures(Res))
    return T
  }

  @pure override def nonEmpty: B = {
    Contract(Ensures(!Res[B]))
    return F
  }

  @pure override def map[T2](f: T => T2 @pure): Option[T2] = {
    Contract(Ensures(None[T2]() === Res))
    return None[T2]()
  }

  @pure override def flatMap[T2](f: T => Option[T2] @pure): Option[T2] = {
    Contract(Ensures(None[T2]() === Res))
    return None[T2]()
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
    Contract(Ensures(default === Res))
    return default
  }

  @pure override def getOrElseEager(default: T): T = {
    Contract(Ensures(default === Res))
    return default
  }

  @pure override def get: T = {
    Contract(Requires(F))
    halt("Invalid 'None' operation 'get'.")
  }

  @pure override def toIS: IS[Z, T] = {
    Contract(Ensures(ISZ[T]() == Res))
    return IS[Z, T]()
  }

  override def foreach[V](f: T => V): Unit = {}
}

@datatype class Some[T](val value: T) extends Option[T] {

  @pure override def isEmpty: B = {
    Contract(Ensures(!Res[B]))
    return F
  }

  @pure override def nonEmpty: B = {
    Contract(Ensures(Res))
    return T
  }

  @pure override def map[T2](f: T => T2 @pure): Option[T2] = {
    Contract(Ensures(Some(f(value)) === Res))
    return Some(f(value))
  }

  @pure override def flatMap[T2](f: T => Option[T2] @pure): Option[T2] = {
    Contract(Ensures(f(value) === Res))
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
    Contract(Ensures(value === Res))
    return value
  }

  @pure override def getOrElseEager(default: T): T = {
    Contract(Ensures(value === Res))
    return value
  }

  @pure override def get: T = {
    Contract(Ensures(value === Res))
    return value
  }

  @pure override def toIS: IS[Z, T] = {
    Contract(Ensures(ISZ(value) === Res))
    return ISZ(value)
  }

  override def foreach[V](f: T => V): Unit = {
    f(value)
  }
}
