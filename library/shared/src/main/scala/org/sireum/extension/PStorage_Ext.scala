/*
 Copyright (c) 2017-2024, Robby, Kansas State University
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
package org.sireum.extension

import org.sireum._

object PStorage_Ext {

  class PStorageImpl(val map: HashMap[String, Any] = HashMap.empty) extends PStorage with DatatypeSig {

    @pure def keys: ISZ[String] = map.keys

    @pure def get[T](key: String): Option[T] = map.get(key).asInstanceOf[Option[T]]

    @pure def +[T](p: (String, T)): PStorage = new PStorageImpl(map + p._1 ~> p._2)

    override def $content: Seq[(Predef.String, Any)] = for (p <- map.$content) yield (p._1.toString, p._2)

    override def string: String = s"PStorage $map"

    override def hashCode: Int = map.hashCode

    override def equals(obj: Any): Boolean = obj match {
      case obj: PStorageImpl => map == obj.map
      case _ => false
    }

  }

  @pure def create: PStorage = new PStorageImpl()
}
