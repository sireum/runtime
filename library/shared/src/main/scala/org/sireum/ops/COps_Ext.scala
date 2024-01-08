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

package org.sireum.ops

import org.sireum._

object COps_Ext {
  def categoryOf(c: C): COps.Category.Type = Character.getType(c.value) match {
    case Character.COMBINING_SPACING_MARK => COps.Category.Mc
    case Character.CONNECTOR_PUNCTUATION => COps.Category.Pc
    case Character.CONTROL => COps.Category.Cc
    case Character.CURRENCY_SYMBOL => COps.Category.Sc
    case Character.DASH_PUNCTUATION => COps.Category.Pd
    case Character.DECIMAL_DIGIT_NUMBER => COps.Category.Nd
    case Character.ENCLOSING_MARK => COps.Category.Me
    case Character.END_PUNCTUATION => COps.Category.Pe
    case Character.FINAL_QUOTE_PUNCTUATION => COps.Category.Pf
    case Character.FORMAT => COps.Category.Cf
    case Character.INITIAL_QUOTE_PUNCTUATION => COps.Category.Pi
    case Character.LETTER_NUMBER => COps.Category.Nl
    case Character.LINE_SEPARATOR => COps.Category.Zl
    case Character.LOWERCASE_LETTER => COps.Category.Ll
    case Character.MATH_SYMBOL => COps.Category.Sm
    case Character.MODIFIER_LETTER => COps.Category.Lm
    case Character.MODIFIER_SYMBOL => COps.Category.Sk
    case Character.NON_SPACING_MARK => COps.Category.Mn
    case Character.OTHER_LETTER => COps.Category.Lo
    case Character.OTHER_NUMBER => COps.Category.No
    case Character.OTHER_PUNCTUATION => COps.Category.Po
    case Character.OTHER_SYMBOL => COps.Category.So
    case Character.PARAGRAPH_SEPARATOR => COps.Category.Zp
    case Character.PRIVATE_USE => COps.Category.Co
    case Character.SPACE_SEPARATOR => COps.Category.Zs
    case Character.START_PUNCTUATION => COps.Category.Ps
    case Character.SURROGATE => COps.Category.Cs
    case Character.TITLECASE_LETTER => COps.Category.Lt
    case Character.UNASSIGNED => COps.Category.Cn
    case Character.UPPERCASE_LETTER => COps.Category.Lu
  }
}
