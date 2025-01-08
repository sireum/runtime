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

@range(min = -128, max = 127) class Z8

@range(min = -32768, max = 32767) class Z16

@range(min = -2147483648, max = 2147483647) class Z32

@range(min = -9223372036854775808L, max = 9223372036854775807L) class Z64

@range(min = 0) class N

@range(min = 0, max = 255) class N8

@range(min = 0, max = 65535) class N16

@range(min = 0, max = 4294967295L) class N32

@range(min = 0, max = z"18,446,744,073,709,551,617") class N64

@bits(signed = T, width = 8) class S8

@bits(signed = T, width = 16) class S16

@bits(signed = T, width = 32) class S32

@bits(signed = T, width = 64) class S64

@bits(signed = F, width = 8, min = 0, max = 1) class U1

@bits(signed = F, width = 8, min = 0, max = 3) class U2

@bits(signed = F, width = 8, min = 0, max = 7) class U3

@bits(signed = F, width = 8, min = 0, max = 15) class U4

@bits(signed = F, width = 8, min = 0, max = 31) class U5

@bits(signed = F, width = 8, min = 0, max = 63) class U6

@bits(signed = F, width = 8, min = 0, max = 127) class U7

@bits(signed = F, width = 8) class U8

@bits(signed = F, width = 16, min = 0, max = 511) class U9

@bits(signed = F, width = 16, min = 0, max = 1023) class U10

@bits(signed = F, width = 16, min = 0, max = 2047) class U11

@bits(signed = F, width = 16, min = 0, max = 4095) class U12

@bits(signed = F, width = 16, min = 0, max = 8191) class U13

@bits(signed = F, width = 16, min = 0, max = 16383) class U14

@bits(signed = F, width = 16, min = 0, max = 32767) class U15

@bits(signed = F, width = 16) class U16

@bits(signed = F, width = 32, min = 0, max = 131071) class U17

@bits(signed = F, width = 32, min = 0, max = 262143) class U18

@bits(signed = F, width = 32, min = 0, max = 524287) class U19

@bits(signed = F, width = 32, min = 0, max = 1048575) class U20

@bits(signed = F, width = 32, min = 0, max = 2097151) class U21

@bits(signed = F, width = 32, min = 0, max = 4194303) class U22

@bits(signed = F, width = 32, min = 0, max = 8388607) class U23

@bits(signed = F, width = 32, min = 0, max = 16777215) class U24

@bits(signed = F, width = 32, min = 0, max = 33554431) class U25

@bits(signed = F, width = 32, min = 0, max = 67108863) class U26

@bits(signed = F, width = 32, min = 0, max = 134217727) class U27

@bits(signed = F, width = 32, min = 0, max = 268435455) class U28

@bits(signed = F, width = 32, min = 0, max = 536870911) class U29

@bits(signed = F, width = 32, min = 0, max = 1073741823) class U30

@bits(signed = F, width = 32, min = 0, max = 2147483647) class U31

@bits(signed = F, width = 32) class U32

@bits(signed = F, width = 64, min = 0, max = 8589934591L) class U33

@bits(signed = F, width = 64, min = 0, max = 17179869183L) class U34

@bits(signed = F, width = 64, min = 0, max = 34359738367L) class U35

@bits(signed = F, width = 64, min = 0, max = 68719476735L) class U36

@bits(signed = F, width = 64, min = 0, max = 137438953471L) class U37

@bits(signed = F, width = 64, min = 0, max = 274877906943L) class U38

@bits(signed = F, width = 64, min = 0, max = 549755813887L) class U39

@bits(signed = F, width = 64, min = 0, max = 1099511627775L) class U40

@bits(signed = F, width = 64, min = 0, max = 2199023255551L) class U41

@bits(signed = F, width = 64, min = 0, max = 4398046511103L) class U42

@bits(signed = F, width = 64, min = 0, max = 8796093022207L) class U43

@bits(signed = F, width = 64, min = 0, max = 17592186044415L) class U44

@bits(signed = F, width = 64, min = 0, max = 35184372088831L) class U45

@bits(signed = F, width = 64, min = 0, max = 70368744177663L) class U46

@bits(signed = F, width = 64, min = 0, max = 140737488355327L) class U47

@bits(signed = F, width = 64, min = 0, max = 281474976710655L) class U48

@bits(signed = F, width = 64, min = 0, max = 562949953421311L) class U49

@bits(signed = F, width = 64, min = 0, max = 1125899906842623L) class U50

@bits(signed = F, width = 64, min = 0, max = 2251799813685247L) class U51

@bits(signed = F, width = 64, min = 0, max = 4503599627370495L) class U52

@bits(signed = F, width = 64, min = 0, max = 9007199254740991L) class U53

@bits(signed = F, width = 64, min = 0, max = 18014398509481983L) class U54

@bits(signed = F, width = 64, min = 0, max = 36028797018963967L) class U55

@bits(signed = F, width = 64, min = 0, max = 72057594037927935L) class U56

@bits(signed = F, width = 64, min = 0, max = 144115188075855871L) class U57

@bits(signed = F, width = 64, min = 0, max = 288230376151711743L) class U58

@bits(signed = F, width = 64, min = 0, max = 576460752303423487L) class U59

@bits(signed = F, width = 64, min = 0, max = 1152921504606846975L) class U60

@bits(signed = F, width = 64, min = 0, max = 2305843009213693951L) class U61

@bits(signed = F, width = 64, min = 0, max = 4611686018427387903L) class U62

@bits(signed = F, width = 64, min = 0, max = 9223372036854775807L) class U63

@bits(signed = F, width = 64) class U64
