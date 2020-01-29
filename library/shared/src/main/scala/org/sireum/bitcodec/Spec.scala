// #Sireum
/*
 Copyright (c) 2019, Robby, Kansas State University
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
package org.sireum.bitcodec

import org.sireum._


@datatype trait Spec {

  def name: String

  def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z]

  @memoize def maxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
    return computeMaxSizeOpt(enumMaxSize)
  }

  def toJSON(isCompact: B): String = {
    return Spec.Ext.toJSON(this, isCompact)
  }

}

object Spec {

  @datatype trait Base extends Spec

  @datatype trait Composite extends Base

  @datatype class Boolean(val name: String) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(1)
    }
  }

  @datatype class Bits(val name: String, size: Z) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size)
    }
  }

  @datatype class BytesImpl(val name: String, size: Z, signed: B, valueOpt: Option[Z]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 8)
    }
  }

  @pure def Byte(name: String): BytesImpl = {
    return BytesImpl(name, 1, T, None())
  }

  @pure def ByteConst(name: String, value: Z): BytesImpl = {
    assert(conversions.Z.isInRangeSigned8(value))
    return BytesImpl(name, 1, T, Some(value))
  }

  @pure def UByte(name: String): BytesImpl = {
    return BytesImpl(name, 1, F, None())
  }

  @pure def UByteConst(name: String, value: Z): BytesImpl = {
    assert(conversions.Z.isInRangeUnsigned8(value))
    return BytesImpl(name, 1, F, Some(value))
  }

  @pure def Bytes(name: String, size: Z): BytesImpl = {
    return BytesImpl(name, size, T, None())
  }

  @pure def UBytes(name: String, size: Z): BytesImpl = {
    return BytesImpl(name, size, F, None())
  }

  @datatype class ShortsImpl(val name: String, size: Z, signed: B, valueOpt: Option[Z]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 16)
    }
  }

  @pure def Short(name: String): ShortsImpl = {
    return ShortsImpl(name, 1, T, None())
  }

  @pure def ShortConst(name: String, value: Z): ShortsImpl = {
    assert(conversions.Z.isInRangeSigned16(value))
    return ShortsImpl(name, 1, T, Some(value))
  }

  @pure def UShort(name: String): ShortsImpl = {
    return ShortsImpl(name, 1, F, None())
  }

  @pure def UShortConst(name: String, value: Z): ShortsImpl = {
    assert(conversions.Z.isInRangeUnsigned16(value))
    return ShortsImpl(name, 1, F, Some(value))
  }

  @pure def Shorts(name: String, size: Z): ShortsImpl = {
    return ShortsImpl(name, size, T, None())
  }

  @pure def UShorts(name: String, size: Z): ShortsImpl = {
    return ShortsImpl(name, size, F, None())
  }

  @datatype class IntsImpl(val name: String, size: Z, signed: B, valueOpt: Option[Z]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 32)
    }
  }

  @pure def Int(name: String): IntsImpl = {
    return IntsImpl(name, 1, T, None())
  }

  @pure def IntConst(name: String, value: Z): IntsImpl = {
    assert(conversions.Z.isInRangeSigned32(value))
    return IntsImpl(name, 1, T, Some(value))
  }

  @pure def UInt(name: String): IntsImpl = {
    return IntsImpl(name, 1, F, None())
  }

  @pure def UIntConst(name: String, value: Z): IntsImpl = {
    assert(conversions.Z.isInRangeUnsigned32(value))
    return IntsImpl(name, 1, F, Some(value))
  }

  @pure def Ints(name: String, size: Z): IntsImpl = {
    return IntsImpl(name, size, T, None())
  }

  @pure def UInts(name: String, size: Z): IntsImpl = {
    return IntsImpl(name, size, F, None())
  }

  @datatype class LongsImpl(val name: String, size: Z, signed: B, valueOpt: Option[Z]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 64)
    }
  }

  @pure def Long(name: String): LongsImpl = {
    return LongsImpl(name, 1, T, None())
  }

  @pure def LongConst(name: String, value: Z): LongsImpl = {
    assert(conversions.Z.isInRangeSigned64(value))
    return LongsImpl(name, 1, T, Some(value))
  }

  @pure def ULong(name: String): LongsImpl = {
    return LongsImpl(name, 1, F, None())
  }

  @pure def ULongConst(name: String, value: Z): LongsImpl = {
    assert(conversions.Z.isInRangeUnsigned64(value))
    return LongsImpl(name, 1, F, Some(value))
  }

  @pure def Longs(name: String, size: Z): LongsImpl = {
    return LongsImpl(name, size, T, None())
  }

  @pure def ULongs(name: String, size: Z): LongsImpl = {
    return LongsImpl(name, size, F, None())
  }

  @datatype class FloatsImpl(val name: String, size: Z) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 32)
    }
  }

  @pure def Float(name: String): FloatsImpl = {
    return FloatsImpl(name, 1)
  }

  @pure def Floats(name: String, size: Z): FloatsImpl = {
    return FloatsImpl(name, size)
  }

  @datatype class DoublesImpl(val name: String, size: Z) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 64)
    }
  }

  @pure def Double(name: String): DoublesImpl = {
    return DoublesImpl(name, 1)
  }

  @pure def Doubles(name: String, size: Z): DoublesImpl = {
    return DoublesImpl(name, size)
  }

  @datatype class Enum(val name: String, objectName: String) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(enumMaxSize(objectName))
    }
  }

  @datatype class Concat(val name: String, elements: ISZ[Spec]) extends Composite {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      var r: Z = 0
      for (e <- elements) {
        e.computeMaxSizeOpt(enumMaxSize) match {
          case Some(maxSize) => r = r + maxSize
          case _ => return None()
        }
      }
      return Some(r)
    }
  }

  @datatype trait Poly {
    def polyDesc: Spec.PolyDesc
  }

  @datatype class PolyDesc(compName: String, name: String, max: Z, dependsOn: ISZ[String], elementsOpt: Option[ISZ[Spec]])

  @datatype class Union[T](val name: String,
                           dependsOn: ISZ[String],
                           @hidden choice: T => Z@pure,
                           subs: ISZ[Spec]) extends Composite with Poly {
    val polyDesc: Spec.PolyDesc = PolyDesc("Union", name, -1, dependsOn, Some(subs))

    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      var max: Z = 0
      for (sub <- subs) {
        sub.computeMaxSizeOpt(enumMaxSize) match {
          case Some(subMaxSize) =>
            if (subMaxSize > max) {
              max = subMaxSize
            }
          case _ => return None()
        }
      }
      return Some(max)
    }
  }

  @datatype class RepeatImpl[T](val name: String,
                                maxElements: Z,
                                dependsOn: ISZ[String],
                                @hidden size: T => Z@pure,
                                element: Base) extends Spec with Poly {
    val polyDesc: Spec.PolyDesc = PolyDesc("Repeat", name, maxElements, dependsOn, Some(ISZ(element)))

    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      element.computeMaxSizeOpt(enumMaxSize) match {
        case Some(elementMaxSize) if maxElements >= 0 => return Some(maxElements * elementMaxSize)
        case _ => return None()
      }
    }
  }

  @pure def Repeat[T](name: String, dependsOn: ISZ[String], size: T => Z@pure, element: Base): RepeatImpl[T] = {
    return RepeatImpl(name, -1, dependsOn, size, element)
  }

  @pure def BoundedRepeat[T](name: String, maxElements: Z, dependsOn: ISZ[String], size: T => Z@pure, element: Base): RepeatImpl[T] = {
    assert(maxElements >= 0, s"BoundedRepeat '$name' maxElements must be non-negative")
    return RepeatImpl(name, maxElements, dependsOn, size, element)
  }

  @datatype class RawImpl[T](val name: String,
                             maxSize: Z,
                             dependsOn: ISZ[String],
                             @hidden size: T => Z@pure) extends Spec with Poly {
    val polyDesc: Spec.PolyDesc = PolyDesc("Raw", name, maxSize, dependsOn, None())

    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return if (maxSize >= 0) Some(maxSize) else None()
    }
  }

  @pure def Raw[T](name: String, dependsOn: ISZ[String], size: T => Z@pure): RawImpl[T] = {
    return RawImpl[T](name, -1, dependsOn, size)
  }

  @pure def BoundedRaw[T](name: String, maxSize: Z, dependsOn: ISZ[String], size: T => Z@pure): RawImpl[T] = {
    assert(maxSize >= 0, s"BoundedRaw '$name' maxSize must be non-negative")
    return RawImpl[T](name, maxSize, dependsOn, size)
  }

  @datatype class PredUnion(val name: String, subs: ISZ[PredSpec]) extends Composite {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      var max: Z = 0
      for (sub <- subs) {
        sub.maxSizeOpt(enumMaxSize) match {
          case Some(subMaxSize) =>
            if (subMaxSize > max) {
              max = subMaxSize
            }
          case _ => return None()
        }
      }
      return Some(max)
    }
  }

  @datatype class PredRepeatWhileImpl(val name: String, maxElements: Z, preds: ISZ[Pred], element: Base) extends Spec {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      element.computeMaxSizeOpt(enumMaxSize) match {
        case Some(elementMaxSize) if maxElements >= 0 => return Some(maxElements * elementMaxSize)
        case _ => return None()
      }
    }
  }

  def FixedRepeat(name: String, numOfElements: Z, element: Base): PredRepeatWhileImpl = {
    return PredRepeatWhileImpl(name, numOfElements, ISZ(), element)
  }

  @pure def PredRepeatWhile(name: String, preds: ISZ[Pred], element: Base): PredRepeatWhileImpl = {
    return PredRepeatWhileImpl(name, -1, preds, element)
  }

  @pure def BoundedPredRepeatWhile(name: String, maxElements: Z, preds: ISZ[Pred], element: Base): PredRepeatWhileImpl = {
    assert(maxElements >= 0, s"BoundedPredRepeatWhile '$name' maxElements must be non-negative")
    return PredRepeatWhileImpl(name, maxElements, preds, element)
  }

  @datatype class PredRepeatUntilImpl(val name: String, maxElements: Z, preds: ISZ[Pred], element: Base) extends Spec {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      element.computeMaxSizeOpt(enumMaxSize) match {
        case Some(elementMaxSize) if maxElements >= 0 => return Some(maxElements * elementMaxSize)
        case _ => return None()
      }
    }
  }

  @pure def PredRepeatUntil(name: String, preds: ISZ[Pred], element: Base): PredRepeatUntilImpl = {
    return PredRepeatUntilImpl(name, -1, preds, element)
  }

  @pure def BoundedPredRepeatUntil(name: String, maxElements: Z, preds: ISZ[Pred], element: Base): PredRepeatUntilImpl = {
    assert(maxElements >= 0, s"BoundedPredRepeatWhile '$name' maxElements must be non-negative")
    return PredRepeatUntilImpl(name, maxElements, preds, element)
  }

  @datatype class GenUnion(val name: String, subs: ISZ[Spec]) extends Composite {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      var max: Z = 0
      for (sub <- subs) {
        sub.computeMaxSizeOpt(enumMaxSize) match {
          case Some(subMaxSize) =>
            if (subMaxSize > max) {
              max = subMaxSize
            }
          case _ => return None()
        }
      }
      return Some(max)
    }
  }

  @datatype class GenRepeatImpl(val name: String, maxElements: Z, element: Base) extends Spec {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      element.computeMaxSizeOpt(enumMaxSize) match {
        case Some(elementMaxSize) if maxElements >= 0 => return Some(maxElements * elementMaxSize)
        case _ => return None()
      }
    }
  }

  @pure def GenRepeat(name: String, element: Base): GenRepeatImpl = {
    return GenRepeatImpl(name, -1, element)
  }

  @pure def BoundedGenRepeat(name: String, maxElements: Z, element: Base): GenRepeatImpl = {
    assert(maxElements >= 0, s"BoundedGenRepeat '$name' maxElements must be non-negative")
    return GenRepeatImpl(name, maxElements, element)
  }

  @datatype class GenRawImpl(val name: String, maxSize: Z) extends Spec {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return if (maxSize >= 0) Some(maxSize) else None()
    }
  }

  @pure def GenRaw(name: String): GenRawImpl = {
    return GenRawImpl(name, -1)
  }

  @pure def BoundedGenRaw(name: String, maxSize: Z): GenRawImpl = {
    assert(maxSize >= 0, s"BoundedGenRaw '$name' maxSize must be non-negative")
    return GenRawImpl(name, maxSize)
  }

  @datatype class Pads(size: Z) extends Spec {
    def name: String = {
      return ""
    }

    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size)
    }
  }

  @datatype class PredSpec(preds: ISZ[Pred], spec: Spec) {
    def maxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return spec.computeMaxSizeOpt(enumMaxSize)
    }
  }

  @datatype trait Pred

  object Pred {

    @datatype class Boolean(value: B) extends Pred

    @datatype class Bits(size: Z, value: Z) extends Pred

    @datatype class Bytes(value: ISZ[Z]) extends Pred

    @datatype class Shorts(value: ISZ[Z]) extends Pred

    @datatype class Ints(value: ISZ[Z]) extends Pred

    @datatype class Longs(value: ISZ[Z]) extends Pred

    @datatype class Floats(value: ISZ[F32]) extends Pred

    @datatype class Doubles(value: ISZ[F64]) extends Pred

    @datatype class Skip(size: Z) extends Pred

    @datatype class Between(size: Z, lo: Z, hi: Z) extends Pred

    @datatype class Not(pred: Pred) extends Pred

    @datatype class Or(preds: ISZ[Pred]) extends Pred


  }

  @pure def boolean(value: B): Pred.Boolean = {
    return Pred.Boolean(value)
  }

  @pure def bits(size: Z, value: Z): Pred.Bits = {
    return Pred.Bits(size, value)
  }

  @pure def bytes(value: ISZ[Z]): Pred.Bytes = {
    return Pred.Bytes(value)
  }

  @pure def shorts(value: ISZ[Z]): Pred.Shorts = {
    return Pred.Shorts(value)
  }

  @pure def ints(value: ISZ[Z]): Pred.Ints = {
    return Pred.Ints(value)
  }

  @pure def longs(value: ISZ[Z]): Pred.Longs = {
    return Pred.Longs(value)
  }

  @pure def skip(size: Z): Pred.Skip = {
    return Pred.Skip(size)
  }

  @pure def between(size: Z, lo: Z, hi: Z): Pred.Between = {
    return Pred.Between(size, lo, hi)
  }

  @pure def not(pred: Pred): Pred.Not = {
    return Pred.Not(pred)
  }

  @pure def or(preds: ISZ[Pred]): Pred.Or = {
    return Pred.Or(preds)
  }

  @pure def fromJSON(s: String): Either[Spec, Json.ErrorMsg] = {
    return Ext.fromJSON(s)
  }

  @ext("Spec_Ext") object Ext {
    def toJSON(o: Spec, isCompact: B): String = $
    def fromJSON(s: String): Either[Spec, Json.ErrorMsg] = $
  }
}
