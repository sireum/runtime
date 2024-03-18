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

package org.sireum

import scala.annotation.StaticAnnotation

class datatype extends StaticAnnotation

class `enum` extends StaticAnnotation

class ext(name: String = "") extends StaticAnnotation

class just(name: String = "") extends StaticAnnotation

class hidden extends StaticAnnotation

class memoize extends StaticAnnotation

class msig extends StaticAnnotation

class pure extends StaticAnnotation

class strictpure extends StaticAnnotation

class record extends StaticAnnotation

class sig extends StaticAnnotation

class spec extends StaticAnnotation

class range(min: Z = 0, max: Z = 0, index: B = F) extends StaticAnnotation

class bits(signed: B = T, width: Z = 0, min: Z = 0, max: Z = 0, index: B = F) extends StaticAnnotation

class imm extends StaticAnnotation

class mut extends StaticAnnotation

class index extends StaticAnnotation

class l(num: Int = 0) extends StaticAnnotation

class rw extends StaticAnnotation

class abs extends StaticAnnotation

class induct extends StaticAnnotation
