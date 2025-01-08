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

package org.sireum

@record class MBox[T](var value: T)

@record class MBox2[T1, T2](var value1: T1, var value2: T2)

@record class MBox3[T1, T2, T3](var value1: T1, var value2: T2, var value3: T3)

@record class MBox4[T1, T2, T3, T4](var value1: T1, var value2: T2, var value3: T3, var value4: T4)

@record class MBox5[T1, T2, T3, T4, T5](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5)

@record class MBox6[T1, T2, T3, T4, T5, T6](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6)

@record class MBox7[T1, T2, T3, T4, T5, T6, T7](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7)

@record class MBox8[T1, T2, T3, T4, T5, T6, T7, T8](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8)

@record class MBox9[T1, T2, T3, T4, T5, T6, T7, T8, T9](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9)

@record class MBox10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10)

@record class MBox11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11)

@record class MBox12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12)

@record class MBox13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13)

@record class MBox14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14)

@record class MBox15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14, var value15: T15)

@record class MBox16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14, var value15: T15, var value16: T16)

@record class MBox17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14, var value15: T15, var value16: T16, var value17: T17)

@record class MBox18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14, var value15: T15, var value16: T16, var value17: T17, var value18: T18)

@record class MBox19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14, var value15: T15, var value16: T16, var value17: T17, var value18: T18, var value19: T19)

@record class MBox20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14, var value15: T15, var value16: T16, var value17: T17, var value18: T18, var value19: T19, var value20: T20)

@record class MBox21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14, var value15: T15, var value16: T16, var value17: T17, var value18: T18, var value19: T19, var value20: T20, var value21: T21)

@record class MBox22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](var value1: T1, var value2: T2, var value3: T3, var value4: T4, var value5: T5, var value6: T6, var value7: T7, var value8: T8, var value9: T9, var value10: T10, var value11: T11, var value12: T12, var value13: T13, var value14: T14, var value15: T15, var value16: T16, var value17: T17, var value18: T18, var value19: T19, var value20: T20, var value21: T21, var value22: T22)
