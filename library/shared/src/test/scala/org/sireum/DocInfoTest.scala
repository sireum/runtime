// SPDX-License-Identifier: BSD-2-Clause
package org.sireum

import org.sireum.test._

class DocInfoTest extends TestSuite {
  val tests = Tests {
    * - {
      val inputs = Vector("", "ab", "\n", "a\nb", "a\n\nbc", "a\nb\ncd",
        "a\nb\ncd\n", "a\r\nb\r\ncd", "😀\n\nxy")
      for (input <- inputs) {
        val points = input.codePoints().toArray
        val info = message.DocInfo.create(None(), String(input))
        val indexed = Indexable.Ext.fromString(None(), String(input))
        var line = 1
        var column = 1
        for (offset <- 0 to points.length) {
          val packed = (U64.fromZ(Z(offset)) << U64.fromZ(32)) | U64.fromZ(1)
          val positions = Vector(message.PosInfo(info, packed), indexed.posOpt(Z(offset), Z(1)).get)
          for (position <- positions) {
            assert(position.beginLine == Z(line) && position.beginColumn == Z(column),
              s"offset=$offset, expected=$line:$column, actual=${position.beginLine}:${position.beginColumn}, input=$input")
          }
          if (offset < points.length) {
            if (points(offset) == '\n') {
              line += 1
              column = 1
            } else {
              column += 1
            }
          }
        }
      }
    }
  }
}
