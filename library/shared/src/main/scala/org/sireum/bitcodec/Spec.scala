// #Sireum
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
package org.sireum.bitcodec

import org.sireum._


@datatype trait Spec {

  def name: String

  def isScalar: B

  def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z]

  @memoize def maxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
    return computeMaxSizeOpt(enumMaxSize)
  }

  def toJSON(isCompact: B): String = {
    return Spec.Ext.toJSON(this, isCompact)
  }

}

object Spec {

  type Concat = ConcatImpl
  type Union[T] = UnionImpl[T]
  type PredUnion = PredUnionImpl
  type GenUnion = GenUnionImpl

  @datatype trait Base extends Spec

  @datatype trait Composite extends Base {
    @strictpure def isScalar: B = F

    @pure def as(name: String): Composite

    @pure def asOpt: Option[String]
  }

  @datatype class Boolean(val name: String) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(1)
    }
    @strictpure def isScalar: B = T
  }

  @datatype class Bits(val name: String, val size: Z) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size)
    }
    @strictpure def isScalar: B = size <= 64
  }

  @datatype class BytesImpl(val name: String, val size: Z, val signed: B, val minOpt: Option[Z], val maxOpt: Option[Z]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 8)
    }
    @strictpure def isScalar: B = size == 1
  }

  @pure def Byte(name: String): BytesImpl = {
    return BytesImpl(name, 1, T, None(), None())
  }

  @pure def ByteConst(name: String, value: Z): BytesImpl = {
    assert(conversions.Z.isInRangeSigned8(value))
    return BytesImpl(name, 1, T, Some(value), Some(value))
  }

  @pure def ByteRange(name: String, min: Z, max: Z): BytesImpl = {
    assert(conversions.Z.isInRangeSigned8(min) && conversions.Z.isInRangeSigned8(max) && min <= max)
    return BytesImpl(name, 1, T, Some(min), Some(max))
  }

  @pure def UByte(name: String): BytesImpl = {
    return BytesImpl(name, 1, F, None(), None())
  }

  @pure def UByteConst(name: String, value: Z): BytesImpl = {
    assert(conversions.Z.isInRangeUnsigned8(value))
    return BytesImpl(name, 1, F, Some(value), Some(value))
  }

  @pure def UByteRange(name: String, min: Z, max: Z): BytesImpl = {
    assert(conversions.Z.isInRangeUnsigned8(min) && conversions.Z.isInRangeUnsigned8(max) && min <= max)
    return BytesImpl(name, 1, F, Some(min), Some(max))
  }

  @pure def Bytes(name: String, size: Z): BytesImpl = {
    return BytesImpl(name, size, T, None(), None())
  }

  @pure def BytesRange(name: String, size: Z, min: Z, max: Z): BytesImpl = {
    assert(conversions.Z.isInRangeSigned8(min) && conversions.Z.isInRangeSigned8(max) && min <= max)
    return BytesImpl(name, size, T, Some(min), Some(max))
  }

  @pure def UBytes(name: String, size: Z): BytesImpl = {
    return BytesImpl(name, size, F, None(), None())
  }

  @pure def UBytesRange(name: String, size: Z, min: Z, max: Z): BytesImpl = {
    assert(conversions.Z.isInRangeUnsigned8(min) && conversions.Z.isInRangeUnsigned8(max) && min <= max)
    return BytesImpl(name, size, F, Some(min), Some(max))
  }

  @datatype class ShortsImpl(val name: String, val size: Z, val signed: B, val minOpt: Option[Z], val maxOpt: Option[Z]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 16)
    }
    @strictpure def isScalar: B = size == 1
  }

  @pure def Short(name: String): ShortsImpl = {
    return ShortsImpl(name, 1, T, None(), None())
  }

  @pure def ShortConst(name: String, value: Z): ShortsImpl = {
    assert(conversions.Z.isInRangeSigned16(value))
    return ShortsImpl(name, 1, T, Some(value), Some(value))
  }

  @pure def ShortRange(name: String, min: Z, max: Z): ShortsImpl = {
    assert(conversions.Z.isInRangeSigned16(min) && conversions.Z.isInRangeSigned16(max) && min <= max)
    return ShortsImpl(name, 1, T, Some(min), Some(max))
  }

  @pure def UShort(name: String): ShortsImpl = {
    return ShortsImpl(name, 1, F, None(), None())
  }

  @pure def UShortConst(name: String, value: Z): ShortsImpl = {
    assert(conversions.Z.isInRangeUnsigned16(value))
    return ShortsImpl(name, 1, F, Some(value), Some(value))
  }

  @pure def UShortRange(name: String, min: Z, max: Z): ShortsImpl = {
    assert(conversions.Z.isInRangeUnsigned16(min) && conversions.Z.isInRangeUnsigned16(max) && min <= max)
    return ShortsImpl(name, 1, F, Some(min), Some(max))
  }

  @pure def Shorts(name: String, size: Z): ShortsImpl = {
    return ShortsImpl(name, size, T, None(), None())
  }

  @pure def ShortsRange(name: String, size: Z, min: Z, max: Z): ShortsImpl = {
    assert(conversions.Z.isInRangeSigned16(min) && conversions.Z.isInRangeSigned16(max) && min <= max)
    return ShortsImpl(name, size, T, Some(min), Some(max))
  }

  @pure def UShorts(name: String, size: Z): ShortsImpl = {
    return ShortsImpl(name, size, F, None(), None())
  }

  @pure def UShortsRange(name: String, size: Z, min: Z, max: Z): ShortsImpl = {
    assert(conversions.Z.isInRangeUnsigned16(min) && conversions.Z.isInRangeUnsigned16(max) && min <= max)
    return ShortsImpl(name, size, F, Some(min), Some(max))
  }

  @datatype class IntsImpl(val name: String, val size: Z, val signed: B, val minOpt: Option[Z], val maxOpt: Option[Z]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 32)
    }
    @strictpure def isScalar: B = size == 1
  }

  @pure def Int(name: String): IntsImpl = {
    return IntsImpl(name, 1, T, None(), None())
  }

  @pure def IntConst(name: String, value: Z): IntsImpl = {
    assert(conversions.Z.isInRangeSigned32(value))
    return IntsImpl(name, 1, T, Some(value), Some(value))
  }

  @pure def IntRange(name: String, min: Z, max: Z): IntsImpl = {
    assert(conversions.Z.isInRangeSigned32(min) && conversions.Z.isInRangeSigned32(max) && min <= max)
    return IntsImpl(name, 1, T, Some(min), Some(max))
  }

  @pure def UInt(name: String): IntsImpl = {
    return IntsImpl(name, 1, F, None(), None())
  }

  @pure def UIntConst(name: String, value: Z): IntsImpl = {
    assert(conversions.Z.isInRangeUnsigned32(value))
    return IntsImpl(name, 1, F, Some(value), Some(value))
  }

  @pure def UIntRange(name: String, min: Z, max: Z): IntsImpl = {
    assert(conversions.Z.isInRangeUnsigned32(min) && conversions.Z.isInRangeUnsigned32(max) && min <= max)
    return IntsImpl(name, 1, F, Some(min), Some(max))
  }

  @pure def Ints(name: String, size: Z): IntsImpl = {
    return IntsImpl(name, size, T, None(), None())
  }

  @pure def IntsRange(name: String, size: Z, min: Z, max: Z): IntsImpl = {
    assert(conversions.Z.isInRangeSigned32(min) && conversions.Z.isInRangeSigned32(max) && min <= max)
    return IntsImpl(name, size, T, Some(min), Some(max))
  }

  @pure def UInts(name: String, size: Z): IntsImpl = {
    return IntsImpl(name, size, F, None(), None())
  }

  @pure def UIntsRange(name: String, size: Z, min: Z, max: Z): IntsImpl = {
    assert(conversions.Z.isInRangeUnsigned32(min) && conversions.Z.isInRangeUnsigned32(max) && min <= max)
    return IntsImpl(name, size, F, Some(min), Some(max))
  }

  @datatype class LongsImpl(val name: String, val size: Z, val signed: B, val minOpt: Option[Z], val maxOpt: Option[Z]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 64)
    }
    @strictpure def isScalar: B = size == 1
  }

  @pure def Long(name: String): LongsImpl = {
    return LongsImpl(name, 1, T, None(), None())
  }

  @pure def LongConst(name: String, value: Z): LongsImpl = {
    assert(conversions.Z.isInRangeSigned64(value))
    return LongsImpl(name, 1, T, Some(value), Some(value))
  }

  @pure def LongRange(name: String, min: Z, max: Z): LongsImpl = {
    assert(conversions.Z.isInRangeSigned64(min) && conversions.Z.isInRangeSigned64(max) && min <= max)
    return LongsImpl(name, 1, T, Some(min), Some(max))
  }

  @pure def ULong(name: String): LongsImpl = {
    return LongsImpl(name, 1, F, None(), None())
  }

  @pure def ULongConst(name: String, value: Z): LongsImpl = {
    assert(conversions.Z.isInRangeUnsigned64(value))
    return LongsImpl(name, 1, F, Some(value), Some(value))
  }

  @pure def ULongRange(name: String, min: Z, max: Z): LongsImpl = {
    assert(conversions.Z.isInRangeUnsigned64(min) && conversions.Z.isInRangeUnsigned64(max) && min <= max)
    return LongsImpl(name, 1, F, Some(min), Some(max))
  }

  @pure def Longs(name: String, size: Z): LongsImpl = {
    return LongsImpl(name, size, T, None(), None())
  }

  @pure def LongsRange(name: String, size: Z, min: Z, max: Z): LongsImpl = {
    assert(conversions.Z.isInRangeSigned64(min) && conversions.Z.isInRangeSigned64(max) && min <= max)
    return LongsImpl(name, size, T, Some(min), Some(max))
  }

  @pure def ULongs(name: String, size: Z): LongsImpl = {
    return LongsImpl(name, size, F, None(), None())
  }

  @pure def ULongsRange(name: String, size: Z, min: Z, max: Z): LongsImpl = {
    assert(conversions.Z.isInRangeUnsigned64(min) && conversions.Z.isInRangeUnsigned64(max) && min <= max)
    return LongsImpl(name, size, F, Some(min), Some(max))
  }

  @datatype class FloatsImpl(val name: String, val size: Z, val minOpt: Option[F32], val maxOpt: Option[F32]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 32)
    }
    @strictpure def isScalar: B = size == 1
  }

  @pure def Float(name: String): FloatsImpl = {
    return FloatsImpl(name, 1, None(), None())
  }

  @pure def FloatRange(name: String, min: F32, max: F32): FloatsImpl = {
    return FloatsImpl(name, 1, Some(min), Some(max))
  }

  @pure def Floats(name: String, size: Z): FloatsImpl = {
    return FloatsImpl(name, size, None(), None())
  }

  @pure def FloatsRange(name: String, size: Z, min: F32, max: F32): FloatsImpl = {
    return FloatsImpl(name, size, Some(min), Some(max))
  }

  @datatype class DoublesImpl(val name: String, val size: Z, val minOpt: Option[F64], val maxOpt: Option[F64]) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size * 64)
    }
    @strictpure def isScalar: B = size == 1
  }

  @pure def Double(name: String): DoublesImpl = {
    return DoublesImpl(name, 1, None(), None())
  }

  @pure def DoubleRange(name: String, min: F64, max: F64): DoublesImpl = {
    return DoublesImpl(name, 1, Some(min), Some(max))
  }

  @pure def Doubles(name: String, size: Z): DoublesImpl = {
    return DoublesImpl(name, size, None(), None())
  }

  @pure def DoublesRange(name: String, size: Z, min: F64, max: F64): DoublesImpl = {
    return DoublesImpl(name, size, Some(min), Some(max))
  }

  @datatype class Enum(val name: String, val objectName: String) extends Base {
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(enumMaxSize(objectName))
    }
    @strictpure def isScalar: B = T
  }

  @strictpure def Concat(name: String, elements: ISZ[Spec]): ConcatImpl = ConcatImpl(name, elements, None())

  @datatype class ConcatImpl(val name: String, val elements: ISZ[Spec], @hidden val asOpt: Option[String]) extends Composite {
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

    @strictpure def as(name: String): ConcatImpl = this(asOpt = Some(name))
  }

  @datatype trait Poly {
    def polyDesc: Spec.PolyDesc
  }

  @datatype class PolyDesc(val compName: String, val name: String, val max: Z, val dependsOn: ISZ[String], val elementsOpt: Option[ISZ[Spec]], val asOpt: Option[String])

  @strictpure def Union[T](name: String, dependsOn: ISZ[String], choice: T => Z@pure, subs: ISZ[Spec]): UnionImpl[T] =
    UnionImpl[T](name, dependsOn, choice, subs, None())

  @datatype class UnionImpl[T](val name: String,
                               val dependsOn: ISZ[String],
                               @hidden val choice: T => Z@pure,
                               val subs: ISZ[Spec],
                               @hidden val asOpt: Option[String]) extends Composite with Poly {
    val polyDesc: Spec.PolyDesc = PolyDesc("Union", name, -1, dependsOn, Some(subs), asOpt)

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

    @strictpure def as(name: String): UnionImpl[T] = this(asOpt = Some(name))
  }

  @datatype class RepeatImpl[T](val name: String,
                                val maxElements: Z,
                                val dependsOn: ISZ[String],
                                @hidden val size: T => Z@pure,
                                val element: Base) extends Spec with Poly {
    val polyDesc: Spec.PolyDesc = PolyDesc("Repeat", name, maxElements, dependsOn, Some(ISZ(element)), None())

    @strictpure def isScalar: B = F

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
                             val maxSize: Z,
                             val dependsOn: ISZ[String],
                             @hidden val size: T => Z@pure) extends Spec with Poly {
    val polyDesc: Spec.PolyDesc = PolyDesc("Raw", name, maxSize, dependsOn, None(), None())

    @strictpure def isScalar: B = F

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

  @strictpure def PredUnion(name: String, subs: ISZ[PredSpec]): PredUnionImpl = PredUnionImpl(name, subs, None())

  @datatype class PredUnionImpl(val name: String, val subs: ISZ[PredSpec], @hidden val asOpt: Option[String]) extends Composite {
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
    @strictpure def as(name: String): PredUnionImpl = this(asOpt = Some(name))
  }

  @datatype class PredRepeatWhileImpl(val name: String, val maxElements: Z, val preds: ISZ[Pred], val element: Base) extends Spec {
    @strictpure def isScalar: B = F
    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      element.computeMaxSizeOpt(enumMaxSize) match {
        case Some(elementMaxSize) if maxElements >= 0 => return Some(maxElements * elementMaxSize)
        case _ => return None()
      }
    }
  }

  @pure def FixedRepeat(name: String, numOfElements: Z, element: Base): PredRepeatWhileImpl = {
    return PredRepeatWhileImpl(name, numOfElements, ISZ(), element)
  }

  @pure def PredRepeatWhile(name: String, preds: ISZ[Pred], element: Base): PredRepeatWhileImpl = {
    return PredRepeatWhileImpl(name, -1, preds, element)
  }

  @pure def BoundedPredRepeatWhile(name: String, maxElements: Z, preds: ISZ[Pred], element: Base): PredRepeatWhileImpl = {
    assert(maxElements >= 0, s"BoundedPredRepeatWhile '$name' maxElements must be non-negative")
    return PredRepeatWhileImpl(name, maxElements, preds, element)
  }

  @datatype class PredRepeatUntilImpl(val name: String, val maxElements: Z, val preds: ISZ[Pred], val element: Base) extends Spec {
    @strictpure def isScalar: B = F
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

  @strictpure def GenUnion(name: String, subs: ISZ[Spec]): GenUnionImpl = GenUnionImpl(name, subs, None())

  @datatype class GenUnionImpl(val name: String, val subs: ISZ[Spec], @hidden val asOpt: Option[String]) extends Composite {
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
    @strictpure def as(name: String): GenUnionImpl = this(asOpt = Some(name))
  }

  @datatype class GenRepeatImpl(val name: String, val maxElements: Z, val element: Base) extends Spec {
    @strictpure def isScalar: B = F
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

  @datatype class GenRawImpl(val name: String, val maxSize: Z) extends Spec {
    @strictpure def isScalar: B = F
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

  @datatype class Pads(val size: Z) extends Spec {
    def name: String = {
      return ""
    }

    @strictpure def isScalar: B = F

    override def computeMaxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return Some(size)
    }
  }

  @datatype class PredSpec(val preds: ISZ[Pred], val spec: Spec) {
    def maxSizeOpt(enumMaxSize: String => Z): Option[Z] = {
      return spec.computeMaxSizeOpt(enumMaxSize)
    }
  }

  @datatype trait Pred

  object Pred {

    @datatype class Boolean(val value: B) extends Pred

    @datatype class Bits(val size: Z, val value: Z) extends Pred

    @datatype class Bytes(val value: ISZ[Z]) extends Pred

    @datatype class Shorts(val value: ISZ[Z]) extends Pred

    @datatype class Ints(val value: ISZ[Z]) extends Pred

    @datatype class Longs(val value: ISZ[Z]) extends Pred

    @datatype class Floats(val value: ISZ[F32]) extends Pred

    @datatype class Doubles(val value: ISZ[F64]) extends Pred

    @datatype class Skip(val size: Z) extends Pred

    @datatype class Between(val size: Z, val lo: Z, val hi: Z) extends Pred

    @datatype class Not(val pred: Pred) extends Pred

    @datatype class Or(val preds: ISZ[Pred]) extends Pred


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
