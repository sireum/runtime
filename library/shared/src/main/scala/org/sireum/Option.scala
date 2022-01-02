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
    Ensures(Res == (this == None[T]()))
  )

  @pure def nonEmpty: B = Contract.Only(
    Ensures(Res == !isEmpty)
  )

  @pure def map[T2](f: T => T2 @pure): Option[T2] = Contract.Only(
    Case(
      Requires(isEmpty),
      Ensures(Res == None[T2]())
    ),
    Case(
      Requires(nonEmpty),
      Ensures(Res == Some(f(get)))
    )
  )

  @pure def flatMap[T2](f: T => Option[T2] @pure): Option[T2] = Contract.Only(
    Case(
      Requires(isEmpty),
      Ensures(Res == None[T2]())
    ),
    Case(
      Requires(nonEmpty),
      Ensures(Res == f(get))
    )
  )

  @pure def forall(f: T => B @pure): B = Contract.Only(
    Case(
      Requires(isEmpty),
      Ensures(Res == T)
    ),
    Case(
      Requires(nonEmpty),
      Ensures(Res == f(get))
    )
  )

  @pure def exists(f: T => B @pure): B = Contract.Only(
    Case(
      Requires(isEmpty),
      Ensures(Res == F)
    ),
    Case(
      Requires(nonEmpty),
      Ensures(Res == f(get))
    )
  )

  @pure def get: T = Contract.Only(
    Requires(nonEmpty),
    Ensures(Some(Res) == this)
  )

  @pure def getOrElse(default: => T): T = Contract.Only(
    Case(
      Requires(isEmpty),
      Ensures(Res == default)
    ),
    Case(
      Requires(nonEmpty),
      Ensures(Some(Res) == this)
    )
  )

  @pure def getOrElseEager(default: T): T = Contract.Only(
    Case(
      Requires(isEmpty),
      Ensures(Res == default)
    ),
    Case(
      Requires(nonEmpty),
      Ensures(Some(Res) == this)
    )
  )

  @pure def toIS: IS[Z, T] = Contract.Only(
    Case(
      Requires(isEmpty),
      Ensures(Res == ISZ[T]())
    ),
    Case(
      Requires(nonEmpty),
      Ensures(Res == ISZ[T](get))
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
    Contract(Ensures(Res == None[T2]()))
    return None[T2]()
  }

  @pure override def flatMap[T2](f: T => Option[T2] @pure): Option[T2] = {
    Contract(Ensures(Res == None[T2]()))
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
    Contract(Ensures(Res == default))
    return default
  }

  @pure override def getOrElseEager(default: T): T = {
    Contract(Ensures(Res == default))
    return default
  }

  @pure override def get: T = {
    Contract(Requires(F))
    halt("Invalid 'None' operation 'get'.")
  }

  @pure override def toIS: IS[Z, T] = {
    Contract(Ensures(Res[ISZ[T]].size == 0))
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
    Contract(Ensures(Res == Some(f(value))))
    return Some(f(value))
  }

  @pure override def flatMap[T2](f: T => Option[T2] @pure): Option[T2] = {
    Contract(Ensures(Res == f(value)))
    return f(value)
  }

  @pure override def forall(f: T => B @pure): B = {
    Contract(Ensures(Res == f(value)))
    return f(value)
  }

  @pure override def exists(f: T => B @pure): B = {
    Contract(Ensures(Res == f(value)))
    return f(value)
  }

  @pure override def getOrElse(default: => T): T = {
    Contract(Ensures(Res == value))
    return value
  }

  @pure override def getOrElseEager(default: T): T = {
    Contract(Ensures(Res == value))
    return value
  }

  @pure override def get: T = {
    Contract(Ensures(Res == value))
    return value
  }

  @pure override def toIS: IS[Z, T] = {
    Contract(Ensures(Res == ISZ(value)))

    return ISZ(value)
  }

  override def foreach[V](f: T => V): Unit = {
    f(value)
  }
}
