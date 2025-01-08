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
// Auto-generated
package org.sireum

import org.sireum._
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap

import Reflection._

object LibJvmUtil_Ext {
  val enumInfo: Info = Info(Kind.Enum, ISZ(), ISZ())
  def create: Reflection = new LibJvmUtil_Ext
}

import LibJvmUtil_Ext._

class LibJvmUtil_Ext extends Reflection_Ext {

  private lazy val nameMap: Int2ObjectOpenHashMap[Reflection.Info] = {
    val r = new Int2ObjectOpenHashMap[Info](176)
    r.put(0xBF340B69, info0) // objectOrTypeKey("org.sireum.AssocS.Entries").value
    r.put(0x8620E4A5, enumInfo) // objectOrTypeKey("org.sireum.CircularQueue.Policy").value
    r.put(0xEDE310F1, enumInfo) // objectOrTypeKey("org.sireum.CircularQueue.Policy.Type").value
    r.put(0x0A395928, info3) // objectOrTypeKey("org.sireum.ContractUtil").value
    r.put(0xC2495125, info4) // objectOrTypeKey("org.sireum.Graph.Internal").value
    r.put(0x00F54577, info5) // objectOrTypeKey("org.sireum.Hash").value
    r.put(0x50D63C7A, info6) // objectOrTypeKey("org.sireum.Jen.Internal").value
    r.put(0x14B93CB4, info7) // objectOrTypeKey("org.sireum.Json").value
    r.put(0xBA1D8D4C, enumInfo) // objectOrTypeKey("org.sireum.Json.ValueKind").value
    r.put(0xCD60C27D, enumInfo) // objectOrTypeKey("org.sireum.Json.ValueKind.Type").value
    r.put(0xC7EA7002, info10) // objectOrTypeKey("org.sireum.Json.Printer").value
    r.put(0x9FF5C2B0, info11) // objectOrTypeKey("org.sireum.Json.Fun").value
    r.put(0x754EACAC, info12) // objectOrTypeKey("org.sireum.LibUtil").value
    r.put(0x6E5F5F55, info13) // objectOrTypeKey("org.sireum.LibUtil.IS").value
    r.put(0xA793A41C, info14) // objectOrTypeKey("org.sireum.Library").value
    r.put(0x0E1A94DA, info15) // objectOrTypeKey("org.sireum.MJen.Internal").value
    r.put(0x04D8C3B5, info16) // objectOrTypeKey("org.sireum.Map.Entries").value
    r.put(0xEB44D893, info17) // objectOrTypeKey("org.sireum.OsProto").value
    r.put(0x1C08E5AC, info18) // objectOrTypeKey("org.sireum.Poset.Internal").value
    r.put(0x9E01815E, info19) // objectOrTypeKey("org.sireum.Random").value
    r.put(0xE94336DC, info20) // objectOrTypeKey("org.sireum.Random.Impl").value
    r.put(0x72D7E325, info21) // objectOrTypeKey("org.sireum.Random.Ext").value
    r.put(0x65C0B3B4, info22) // objectOrTypeKey("org.sireum.Set.Elements").value
    r.put(0x2306EA3F, info23) // objectOrTypeKey("org.sireum.UnionFind.Internal").value
    r.put(0x5A1EBCEC, info24) // objectOrTypeKey("org.sireum.justification").value
    r.put(0xB3F5A778, info25) // objectOrTypeKey("org.sireum.justification.natded").value
    r.put(0x0B82CB8A, info26) // objectOrTypeKey("org.sireum.justification.natded.prop").value
    r.put(0xB964ADEE, info27) // objectOrTypeKey("org.sireum.justification.natded.pred").value
    r.put(0xB689416D, info28) // objectOrTypeKey("org.sireum.Asm").value
    r.put(0x846190A8, enumInfo) // objectOrTypeKey("org.sireum.CoursierClassifier").value
    r.put(0x05B64D38, enumInfo) // objectOrTypeKey("org.sireum.CoursierClassifier.Type").value
    r.put(0x146E7442, info31) // objectOrTypeKey("org.sireum.Coursier").value
    r.put(0x01A0B5C2, info32) // objectOrTypeKey("org.sireum.GitHub").value
    r.put(0xD9DEF21A, info33) // objectOrTypeKey("org.sireum.GitHub.Ext").value
    r.put(0x28E928C8, info34) // objectOrTypeKey("org.sireum.LibJvmUtil").value
    r.put(0x33F535A1, info35) // objectOrTypeKey("org.sireum.LibJvmUtil.Ext").value
    r.put(0xBB04BE7C, info36) // objectOrTypeKey("org.sireum.Os").value
    r.put(0x06932F23, enumInfo) // objectOrTypeKey("org.sireum.Os.Kind").value
    r.put(0xF9DB7A5F, enumInfo) // objectOrTypeKey("org.sireum.Os.Kind.Type").value
    r.put(0x16F0305E, enumInfo) // objectOrTypeKey("org.sireum.Os.Path.Kind").value
    r.put(0xA509AA25, enumInfo) // objectOrTypeKey("org.sireum.Os.Path.Kind.Type").value
    r.put(0x111F32DB, enumInfo) // objectOrTypeKey("org.sireum.Os.Path.WriteMode").value
    r.put(0xCAFD89A4, enumInfo) // objectOrTypeKey("org.sireum.Os.Path.WriteMode.Type").value
    r.put(0x298F2B40, info43) // objectOrTypeKey("org.sireum.Os.Ext").value
    r.put(0x5F9DDA1F, info44) // objectOrTypeKey("org.sireum.Scalafmt").value
    r.put(0x97BB94A6, info45) // objectOrTypeKey("org.sireum.Unit").value
    r.put(0xD292453A, info46) // objectOrTypeKey("org.sireum.Nothing").value
    r.put(0x4B6BCF82, info47) // objectOrTypeKey("org.sireum.AssocS").value
    r.put(0xF9FC3D12, info48) // objectOrTypeKey("org.sireum.Bag").value
    r.put(0x9D4EC2EB, info49) // objectOrTypeKey("org.sireum.StepId").value
    r.put(0xC68F5781, info50) // objectOrTypeKey("org.sireum.CircularQueue").value
    r.put(0x8FB81288, info51) // objectOrTypeKey("org.sireum.CircularQueue.NoDrop").value
    r.put(0x5F444BD4, info52) // objectOrTypeKey("org.sireum.CircularQueue.DropFront").value
    r.put(0xE129FFA4, info53) // objectOrTypeKey("org.sireum.CircularQueue.DropRear").value
    r.put(0x31666B93, info54) // objectOrTypeKey("org.sireum.Either").value
    r.put(0x19E203F0, info55) // objectOrTypeKey("org.sireum.Either.Left").value
    r.put(0xE373A36D, info56) // objectOrTypeKey("org.sireum.Either.Right").value
    r.put(0xDFE49387, info57) // objectOrTypeKey("org.sireum.Graph.Edge").value
    r.put(0x7A143155, info58) // objectOrTypeKey("org.sireum.Graph.Edge.Plain").value
    r.put(0x0FBB6850, info59) // objectOrTypeKey("org.sireum.Graph.Edge.Data").value
    r.put(0x047E61CC, info60) // objectOrTypeKey("org.sireum.Graph.Internal.Edge").value
    r.put(0x17E44376, info61) // objectOrTypeKey("org.sireum.Graph.Internal.Edges").value
    r.put(0xE619E2A5, info62) // objectOrTypeKey("org.sireum.Graph.Internal.Edges.Set").value
    r.put(0xB788F86D, info63) // objectOrTypeKey("org.sireum.Graph.Internal.Edges.Bag").value
    r.put(0x31657A1F, info64) // objectOrTypeKey("org.sireum.Graph.Internal.Edge.Plain").value
    r.put(0x158AA86A, info65) // objectOrTypeKey("org.sireum.Graph.Internal.Edge.Data").value
    r.put(0x9C2C0C94, info66) // objectOrTypeKey("org.sireum.Graph").value
    r.put(0x883E9A99, info67) // objectOrTypeKey("org.sireum.HashBag").value
    r.put(0xC1C8FDA4, info68) // objectOrTypeKey("org.sireum.HashMap").value
    r.put(0xEBADDB0C, info69) // objectOrTypeKey("org.sireum.HashSBag").value
    r.put(0xFB8738A0, info70) // objectOrTypeKey("org.sireum.HashSMap").value
    r.put(0xF13418B2, info71) // objectOrTypeKey("org.sireum.HashSSet").value
    r.put(0x842F9727, info72) // objectOrTypeKey("org.sireum.HashSet").value
    r.put(0x0DB5A38F, info73) // objectOrTypeKey("org.sireum.IndexMap").value
    r.put(0x6A9C0C3F, info74) // objectOrTypeKey("org.sireum.Indexable").value
    r.put(0x15561D5B, info75) // objectOrTypeKey("org.sireum.Indexable.Pos").value
    r.put(0x8F9F62AF, info76) // objectOrTypeKey("org.sireum.Indexable.Isz").value
    r.put(0x68CE5E47, info77) // objectOrTypeKey("org.sireum.Indexable.IszDocInfo").value
    r.put(0x1BD55A1E, info78) // objectOrTypeKey("org.sireum.Jen").value
    r.put(0x3DE02576, info79) // objectOrTypeKey("org.sireum.Jen.Internal.ISImpl").value
    r.put(0x00F6F10D, info80) // objectOrTypeKey("org.sireum.Jen.Internal.MapImpl").value
    r.put(0xA6059042, info81) // objectOrTypeKey("org.sireum.Jen.Internal.HashMapImpl").value
    r.put(0x8BD3A96F, info82) // objectOrTypeKey("org.sireum.Jen.Internal.Filtered").value
    r.put(0x6FC3A2A2, info83) // objectOrTypeKey("org.sireum.Jen.Internal.Mapped").value
    r.put(0x6E1636F5, info84) // objectOrTypeKey("org.sireum.Jen.Internal.FlatMapped").value
    r.put(0x74EEC5C0, info85) // objectOrTypeKey("org.sireum.Jen.Internal.Sliced").value
    r.put(0x7BAD7D88, info86) // objectOrTypeKey("org.sireum.Jen.Internal.TakeWhile").value
    r.put(0x5B1FD227, info87) // objectOrTypeKey("org.sireum.Jen.Internal.DropWhile").value
    r.put(0xCB753410, info88) // objectOrTypeKey("org.sireum.Jen.Internal.ZipWithIndexed").value
    r.put(0xBB793B19, info89) // objectOrTypeKey("org.sireum.Jen.Internal.Zipped").value
    r.put(0x007A1385, info90) // objectOrTypeKey("org.sireum.Jen.Internal.Concat").value
    r.put(0x1200DEFB, info91) // objectOrTypeKey("org.sireum.Jen.Internal.Product").value
    r.put(0x7149BA6B, info92) // objectOrTypeKey("org.sireum.Json.JsonAstBinding").value
    r.put(0xF84DADED, info93) // objectOrTypeKey("org.sireum.Json.ErrorMsg").value
    r.put(0x09B503BF, info94) // objectOrTypeKey("org.sireum.Json.Parser").value
    r.put(0x22D9BA60, info95) // objectOrTypeKey("org.sireum.MBox").value
    r.put(0x85596090, info96) // objectOrTypeKey("org.sireum.MBox2").value
    r.put(0x19620664, info97) // objectOrTypeKey("org.sireum.MBox3").value
    r.put(0x17E6C186, info98) // objectOrTypeKey("org.sireum.MBox4").value
    r.put(0x49391657, info99) // objectOrTypeKey("org.sireum.MBox5").value
    r.put(0x90C028AA, info100) // objectOrTypeKey("org.sireum.MBox6").value
    r.put(0x00547F85, info101) // objectOrTypeKey("org.sireum.MBox7").value
    r.put(0x4010E999, info102) // objectOrTypeKey("org.sireum.MBox8").value
    r.put(0x4367D13F, info103) // objectOrTypeKey("org.sireum.MBox9").value
    r.put(0xD087D69D, info104) // objectOrTypeKey("org.sireum.MBox10").value
    r.put(0xAB355F5F, info105) // objectOrTypeKey("org.sireum.MBox11").value
    r.put(0x5695E90D, info106) // objectOrTypeKey("org.sireum.MBox12").value
    r.put(0x6A36A51C, info107) // objectOrTypeKey("org.sireum.MBox13").value
    r.put(0x9479D861, info108) // objectOrTypeKey("org.sireum.MBox14").value
    r.put(0x88C35A8D, info109) // objectOrTypeKey("org.sireum.MBox15").value
    r.put(0x42D49241, info110) // objectOrTypeKey("org.sireum.MBox16").value
    r.put(0xC36F65D5, info111) // objectOrTypeKey("org.sireum.MBox17").value
    r.put(0x73158A8A, info112) // objectOrTypeKey("org.sireum.MBox18").value
    r.put(0x8C268331, info113) // objectOrTypeKey("org.sireum.MBox19").value
    r.put(0x75BBCAA6, info114) // objectOrTypeKey("org.sireum.MBox20").value
    r.put(0x043C4FA8, info115) // objectOrTypeKey("org.sireum.MBox21").value
    r.put(0x33B3CA6B, info116) // objectOrTypeKey("org.sireum.MBox22").value
    r.put(0x789B9607, info117) // objectOrTypeKey("org.sireum.MEither").value
    r.put(0x8E5C2AF7, info118) // objectOrTypeKey("org.sireum.MEither.Left").value
    r.put(0xA8BFF8F9, info119) // objectOrTypeKey("org.sireum.MEither.Right").value
    r.put(0xE0B7A907, info120) // objectOrTypeKey("org.sireum.MJen").value
    r.put(0x89D55DB4, info121) // objectOrTypeKey("org.sireum.MJen.Internal.ISImpl").value
    r.put(0x604300B1, info122) // objectOrTypeKey("org.sireum.MJen.Internal.MSImpl").value
    r.put(0xFAF9A5C6, info123) // objectOrTypeKey("org.sireum.MJen.Internal.MapImpl").value
    r.put(0xFBB3388F, info124) // objectOrTypeKey("org.sireum.MJen.Internal.HashMapImpl").value
    r.put(0x7593553A, info125) // objectOrTypeKey("org.sireum.MJen.Internal.Filtered").value
    r.put(0xA021C7B2, info126) // objectOrTypeKey("org.sireum.MJen.Internal.Mapped").value
    r.put(0xDD7B9281, info127) // objectOrTypeKey("org.sireum.MJen.Internal.FlatMapped").value
    r.put(0x26B896DF, info128) // objectOrTypeKey("org.sireum.MJen.Internal.Sliced").value
    r.put(0x04A2D591, info129) // objectOrTypeKey("org.sireum.MJen.Internal.TakeWhile").value
    r.put(0x42A4EB51, info130) // objectOrTypeKey("org.sireum.MJen.Internal.DropWhile").value
    r.put(0x2EB52536, info131) // objectOrTypeKey("org.sireum.MJen.Internal.ZipWithIndexed").value
    r.put(0xF9106E42, info132) // objectOrTypeKey("org.sireum.MJen.Internal.Zipped").value
    r.put(0x95939F2E, info133) // objectOrTypeKey("org.sireum.MJen.Internal.Concat").value
    r.put(0x7097D42B, info134) // objectOrTypeKey("org.sireum.MJen.Internal.Product").value
    r.put(0xBFA96725, info135) // objectOrTypeKey("org.sireum.MOption").value
    r.put(0x55BD72AC, info136) // objectOrTypeKey("org.sireum.MNone").value
    r.put(0xDD7FCF81, info137) // objectOrTypeKey("org.sireum.MSome").value
    r.put(0xDB9D0399, info138) // objectOrTypeKey("org.sireum.Map").value
    r.put(0xC001D0F8, info139) // objectOrTypeKey("org.sireum.ObjPrinter").value
    r.put(0x14C6A50F, info140) // objectOrTypeKey("org.sireum.Option").value
    r.put(0xC7F1E051, info141) // objectOrTypeKey("org.sireum.None").value
    r.put(0xAAFF1217, info142) // objectOrTypeKey("org.sireum.Some").value
    r.put(0x49AE4217, info143) // objectOrTypeKey("org.sireum.OsProto.Path").value
    r.put(0x14E10E5F, info144) // objectOrTypeKey("org.sireum.OsProto.Proc.Result").value
    r.put(0x81966518, info145) // objectOrTypeKey("org.sireum.OsProto.Proc").value
    r.put(0xDF372A83, info146) // objectOrTypeKey("org.sireum.Poset").value
    r.put(0x773232EE, info147) // objectOrTypeKey("org.sireum.Random.Gen.TestRunner").value
    r.put(0x20CD55DB, info148) // objectOrTypeKey("org.sireum.Random.Gen").value
    r.put(0x0120E7AB, info149) // objectOrTypeKey("org.sireum.Random.Gen64").value
    r.put(0x7F636E7F, info150) // objectOrTypeKey("org.sireum.Random.Gen64Impl").value
    r.put(0x775186D0, info151) // objectOrTypeKey("org.sireum.Random.Impl.SplitMix64").value
    r.put(0x0C40779D, info152) // objectOrTypeKey("org.sireum.Random.Impl.Xoshiro256").value
    r.put(0x7F11D172, info153) // objectOrTypeKey("org.sireum.Random.Impl.Xoroshiro128").value
    r.put(0x8010C428, info154) // objectOrTypeKey("org.sireum.Set").value
    r.put(0x2B1C8D90, info155) // objectOrTypeKey("org.sireum.Stack").value
    r.put(0x45FD9933, info156) // objectOrTypeKey("org.sireum.UnionFind").value
    r.put(0x8D7F602F, info157) // objectOrTypeKey("org.sireum.CoursierFileInfo").value
    r.put(0xBD7F2C41, info158) // objectOrTypeKey("org.sireum.Coursier.Proxy").value
    r.put(0x3CC69620, info159) // objectOrTypeKey("org.sireum.GitHub.Credential").value
    r.put(0xF38C97D4, info160) // objectOrTypeKey("org.sireum.GitHub.Repository").value
    r.put(0xF0EADD92, info161) // objectOrTypeKey("org.sireum.GitHub.Release").value
    r.put(0x2924BD4D, info162) // objectOrTypeKey("org.sireum.GitHub.Asset").value
    r.put(0x984F1492, info163) // objectOrTypeKey("org.sireum.Init.Plugin").value
    r.put(0x656AD968, info164) // objectOrTypeKey("org.sireum.Init").value
    r.put(0x9639C557, info165) // objectOrTypeKey("org.sireum.Os.Path.Impl").value
    r.put(0x467528A7, info166) // objectOrTypeKey("org.sireum.Os.Path.Jen").value
    r.put(0xFC765856, info167) // objectOrTypeKey("org.sireum.Os.Path.MJen").value
    r.put(0x88822D20, info168) // objectOrTypeKey("org.sireum.Os.Proc.LineFilter").value
    r.put(0xCA390F1D, info169) // objectOrTypeKey("org.sireum.Os.Proc.FunLineFilter").value
    r.put(0xDBA67033, info170) // objectOrTypeKey("org.sireum.Os.Proc.Result").value
    r.put(0xD6DC0267, info171) // objectOrTypeKey("org.sireum.Os.Proc.Result.Normal").value
    r.put(0x3EAB24E6, info172) // objectOrTypeKey("org.sireum.Os.Proc.Result.Exception").value
    r.put(0x8B77F5D3, info173) // objectOrTypeKey("org.sireum.Os.Proc.Result.Timeout").value
    r.put(0x8989ABF6, info174) // objectOrTypeKey("org.sireum.Os.Proc").value
    r.put(0xE8D15C6E, info175) // objectOrTypeKey("org.sireum.Os.Path").value
    r
  }

  private lazy val method0Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any](1219)
    r.put(0xB845B50D3B02FA78L, _ => org.sireum.CircularQueue.Policy.NoDrop) // methodKey(T, "org.sireum.CircularQueue.Policy", "NoDrop").value
    r.put(0x6CB47D05D6EFBA0BL, _ => org.sireum.CircularQueue.Policy.DropFront) // methodKey(T, "org.sireum.CircularQueue.Policy", "DropFront").value
    r.put(0x116832077C0E11BFL, _ => org.sireum.CircularQueue.Policy.DropRear) // methodKey(T, "org.sireum.CircularQueue.Policy", "DropRear").value
    r.put(0x2B2B99E0FFB2D546L, _ => org.sireum.Json.ValueKind.String) // methodKey(T, "org.sireum.Json.ValueKind", "String").value
    r.put(0xB33F12947BA120A3L, _ => org.sireum.Json.ValueKind.Number) // methodKey(T, "org.sireum.Json.ValueKind", "Number").value
    r.put(0xE7F4BF454BD6ECCDL, _ => org.sireum.Json.ValueKind.Object) // methodKey(T, "org.sireum.Json.ValueKind", "Object").value
    r.put(0xB8B1DC01CC95B6EFL, _ => org.sireum.Json.ValueKind.Array) // methodKey(T, "org.sireum.Json.ValueKind", "Array").value
    r.put(0x5BCE9D9213067F1BL, _ => org.sireum.Json.ValueKind.True) // methodKey(T, "org.sireum.Json.ValueKind", "True").value
    r.put(0x3A5166BC6B882A75L, _ => org.sireum.Json.ValueKind.False) // methodKey(T, "org.sireum.Json.ValueKind", "False").value
    r.put(0xD7F8FA59240335D1L, _ => org.sireum.Json.ValueKind.Null) // methodKey(T, "org.sireum.Json.ValueKind", "Null").value
    r.put(0x6DADBBA65A3E0380L, _ => org.sireum.Json.Printer.trueSt) // methodKey(T, "org.sireum.Json.Printer", "trueSt").value
    r.put(0x85F5B7EE105972CCL, _ => org.sireum.Json.Printer.falseSt) // methodKey(T, "org.sireum.Json.Printer", "falseSt").value
    r.put(0x2237D4EE4A33DAD9L, _ => org.sireum.Json.Printer.nullSt) // methodKey(T, "org.sireum.Json.Printer", "nullSt").value
    r.put(0xC7F143202794CB16L, _ => org.sireum.LibUtil.setOptions) // methodKey(T, "org.sireum.LibUtil", "setOptions").value
    r.put(0x2770437707001E59L, _ => org.sireum.Library.sharedFiles) // methodKey(T, "org.sireum.Library", "sharedFiles").value
    r.put(0x5D1619B7FBDEDE83L, _ => org.sireum.Library.jvmFiles) // methodKey(T, "org.sireum.Library", "jvmFiles").value
    r.put(0xD3F6CE7B2D83E3FBL, _ => org.sireum.Library.fontFiles) // methodKey(T, "org.sireum.Library", "fontFiles").value
    r.put(0x8853B75E3DDD60F0L, _ => org.sireum.Library.vscodeImageFiles) // methodKey(T, "org.sireum.Library", "vscodeImageFiles").value
    r.put(0x0D137D659C21744EL, _ => org.sireum.Library.files) // methodKey(T, "org.sireum.Library", "files").value
    r.put(0x48DC4AC1CAF34241L, _ => org.sireum.Poset.Internal.emptySet) // methodKey(T, "org.sireum.Poset.Internal", "emptySet").value
    r.put(0xDED3A22AA5504011L, _ => org.sireum.Random.create64) // methodKey(T, "org.sireum.Random", "create64").value
    r.put(0x67FCFBFFD09DC393L, _ => org.sireum.Random.Ext.instance) // methodKey(T, "org.sireum.Random.Ext", "instance").value
    r.put(0xBBB8143BE2E9CA39L, _ => org.sireum.CoursierClassifier.Default) // methodKey(T, "org.sireum.CoursierClassifier", "Default").value
    r.put(0x362650E67D6E58D2L, _ => org.sireum.CoursierClassifier.Javadoc) // methodKey(T, "org.sireum.CoursierClassifier", "Javadoc").value
    r.put(0x2318DAEF862D373EL, _ => org.sireum.CoursierClassifier.Sources) // methodKey(T, "org.sireum.CoursierClassifier", "Sources").value
    r.put(0x68DBB979A8C0F3E3L, _ => org.sireum.Coursier.defaultCacheDir) // methodKey(T, "org.sireum.Coursier", "defaultCacheDir").value
    r.put(0x319C26462669774AL, _ => org.sireum.LibJvmUtil.Ext.create) // methodKey(T, "org.sireum.LibJvmUtil.Ext", "create").value
    r.put(0x6CC38C9860E280F6L, _ => org.sireum.Os.cliArgs) // methodKey(T, "org.sireum.Os", "cliArgs").value
    r.put(0xCEE866DF844E988CL, _ => org.sireum.Os.cwd) // methodKey(T, "org.sireum.Os", "cwd").value
    r.put(0x8BB7902119190DC4L, _ => org.sireum.Os.envs) // methodKey(T, "org.sireum.Os", "envs").value
    r.put(0x41D23D46DA893D6DL, _ => org.sireum.Os.fileSep) // methodKey(T, "org.sireum.Os", "fileSep").value
    r.put(0xC214995D93B8F73AL, _ => org.sireum.Os.freeMemory) // methodKey(T, "org.sireum.Os", "freeMemory").value
    r.put(0xF9EC465B049ED421L, _ => org.sireum.Os.home) // methodKey(T, "org.sireum.Os", "home").value
    r.put(0xEC7DB9323B09CA07L, _ => org.sireum.Os.isLinux) // methodKey(T, "org.sireum.Os", "isLinux").value
    r.put(0x0C0BDAECF50A71DAL, _ => org.sireum.Os.isLinuxArm) // methodKey(T, "org.sireum.Os", "isLinuxArm").value
    r.put(0x5A09B80316284B83L, _ => org.sireum.Os.isMac) // methodKey(T, "org.sireum.Os", "isMac").value
    r.put(0xD9D4C954179BBD4EL, _ => org.sireum.Os.isMacArm) // methodKey(T, "org.sireum.Os", "isMacArm").value
    r.put(0xF9FC3F44DEB5F97DL, _ => org.sireum.Os.isWinArm) // methodKey(T, "org.sireum.Os", "isWinArm").value
    r.put(0x3101B846165B6861L, _ => org.sireum.Os.isWin) // methodKey(T, "org.sireum.Os", "isWin").value
    r.put(0x2162A94D5C1D8143L, _ => org.sireum.Os.kind) // methodKey(T, "org.sireum.Os", "kind").value
    r.put(0x5DD1938BC48065EDL, _ => org.sireum.Os.lineSep) // methodKey(T, "org.sireum.Os", "lineSep").value
    r.put(0xE02DE01C74364D6AL, _ => org.sireum.Os.maxMemory) // methodKey(T, "org.sireum.Os", "maxMemory").value
    r.put(0x8C3B943F32D64AA1L, _ => org.sireum.Os.numOfProcessors) // methodKey(T, "org.sireum.Os", "numOfProcessors").value
    r.put(0xB86A63627E305B55L, _ => org.sireum.Os.pathSep) // methodKey(T, "org.sireum.Os", "pathSep").value
    r.put(0xC53EC069E477E334L, _ => org.sireum.Os.pathSepChar) // methodKey(T, "org.sireum.Os", "pathSepChar").value
    r.put(0x683F197235B3C132L, _ => org.sireum.Os.props) // methodKey(T, "org.sireum.Os", "props").value
    r.put(0x7B2B06A3A9331CFBL, _ => org.sireum.Os.roots) // methodKey(T, "org.sireum.Os", "roots").value
    r.put(0x8C8EEEEE9D803AB1L, _ => org.sireum.Os.sireumHomeOpt) // methodKey(T, "org.sireum.Os", "sireumHomeOpt").value
    r.put(0x90A1AA721E5FD5D2L, _ => org.sireum.Os.slashDir) // methodKey(T, "org.sireum.Os", "slashDir").value
    r.put(0x270D0A80EE586F97L, _ => org.sireum.Os.temp()) // methodKey(T, "org.sireum.Os", "temp").value
    r.put(0x0283DE424184CE3FL, _ => org.sireum.Os.tempDir()) // methodKey(T, "org.sireum.Os", "tempDir").value
    r.put(0xE5B225005BEE82ABL, _ => org.sireum.Os.totalMemory) // methodKey(T, "org.sireum.Os", "totalMemory").value
    r.put(0x484ACBC92FAEF372L, _ => org.sireum.Os.Kind.Mac) // methodKey(T, "org.sireum.Os.Kind", "Mac").value
    r.put(0x08C016596AF85EFBL, _ => org.sireum.Os.Kind.Linux) // methodKey(T, "org.sireum.Os.Kind", "Linux").value
    r.put(0x4206A193D0E63E3FL, _ => org.sireum.Os.Kind.LinuxArm) // methodKey(T, "org.sireum.Os.Kind", "LinuxArm").value
    r.put(0x37748270742D5775L, _ => org.sireum.Os.Kind.Win) // methodKey(T, "org.sireum.Os.Kind", "Win").value
    r.put(0xC364EF807249681EL, _ => org.sireum.Os.Kind.Unsupported) // methodKey(T, "org.sireum.Os.Kind", "Unsupported").value
    r.put(0x296F7FA27037480EL, _ => org.sireum.Os.Path.Kind.Dir) // methodKey(T, "org.sireum.Os.Path.Kind", "Dir").value
    r.put(0xC5F8B0F758E1D70DL, _ => org.sireum.Os.Path.Kind.File) // methodKey(T, "org.sireum.Os.Path.Kind", "File").value
    r.put(0xE03B36B043200CCDL, _ => org.sireum.Os.Path.Kind.SymLink) // methodKey(T, "org.sireum.Os.Path.Kind", "SymLink").value
    r.put(0x0C0805BD888AF0DAL, _ => org.sireum.Os.Path.Kind.Other) // methodKey(T, "org.sireum.Os.Path.Kind", "Other").value
    r.put(0xCD3C19EA4E9B3D96L, _ => org.sireum.Os.Path.WriteMode.Regular) // methodKey(T, "org.sireum.Os.Path.WriteMode", "Regular").value
    r.put(0x6EB6AB530291BF18L, _ => org.sireum.Os.Path.WriteMode.Over) // methodKey(T, "org.sireum.Os.Path.WriteMode", "Over").value
    r.put(0xC1250CE25CB94044L, _ => org.sireum.Os.Path.WriteMode.Append) // methodKey(T, "org.sireum.Os.Path.WriteMode", "Append").value
    r.put(0x512868A2DFFF0366L, _ => org.sireum.Os.Ext.cliArgs) // methodKey(T, "org.sireum.Os.Ext", "cliArgs").value
    r.put(0xF67727BD6B4BFB5AL, _ => org.sireum.Os.Ext.fileSep) // methodKey(T, "org.sireum.Os.Ext", "fileSep").value
    r.put(0x75C795413F5DF8E3L, _ => org.sireum.Os.Ext.lineSep) // methodKey(T, "org.sireum.Os.Ext", "lineSep").value
    r.put(0x0C2CD76F448C2CF7L, _ => org.sireum.Os.Ext.pathSep) // methodKey(T, "org.sireum.Os.Ext", "pathSep").value
    r.put(0x1291511091A5C879L, _ => org.sireum.Os.Ext.pathSepChar) // methodKey(T, "org.sireum.Os.Ext", "pathSepChar").value
    r.put(0xACEF8573B2625500L, _ => org.sireum.Os.Ext.osKind) // methodKey(T, "org.sireum.Os.Ext", "osKind").value
    r.put(0x25757E71A6138921L, _ => org.sireum.Os.Ext.roots) // methodKey(T, "org.sireum.Os.Ext", "roots").value
    r.put(0x53AB3FFC7BF84304L, _ => org.sireum.Os.Ext.detectSireumHome) // methodKey(T, "org.sireum.Os.Ext", "detectSireumHome").value
    r.put(0x0FDF2BB7A3EA5DBEL, _ => org.sireum.Os.Ext.envs) // methodKey(T, "org.sireum.Os.Ext", "envs").value
    r.put(0x70D223245F2CF55AL, _ => org.sireum.Os.Ext.freeMemory) // methodKey(T, "org.sireum.Os.Ext", "freeMemory").value
    r.put(0xE6D80B11AF5BDBA9L, _ => org.sireum.Os.Ext.maxMemory) // methodKey(T, "org.sireum.Os.Ext", "maxMemory").value
    r.put(0x24603CB8E6908AAAL, _ => org.sireum.Os.Ext.numOfProcessors) // methodKey(T, "org.sireum.Os.Ext", "numOfProcessors").value
    r.put(0xF666343D097F2149L, _ => org.sireum.Os.Ext.props) // methodKey(T, "org.sireum.Os.Ext", "props").value
    r.put(0xD26F00F84A190465L, _ => org.sireum.Os.Ext.slashDir) // methodKey(T, "org.sireum.Os.Ext", "slashDir").value
    r.put(0x60EAA4F2A4E64BAAL, _ => org.sireum.Os.Ext.totalMemory) // methodKey(T, "org.sireum.Os.Ext", "totalMemory").value
    r.put(0x4EFBC222D945FCDDL, _ => org.sireum.Scalafmt.version) // methodKey(T, "org.sireum.Scalafmt", "version").value
    r.put(0x677F242B4FAF94AAL, _ => org.sireum.Scalafmt.minimalConfig) // methodKey(T, "org.sireum.Scalafmt", "minimalConfig").value
    r.put(0x8046C6A907740B58L, X[org.sireum.AssocS[_, _]](_).entries) // methodKey(F, "org.sireum.AssocS", "entries").value
    r.put(0x62BCB0192A28B940L, X[org.sireum.AssocS[_, _]](_).keys) // methodKey(F, "org.sireum.AssocS", "keys").value
    r.put(0xC21B0FAD8BEBB5B1L, X[org.sireum.AssocS[_, _]](_).values) // methodKey(F, "org.sireum.AssocS", "values").value
    r.put(0x92A3558D12E51570L, X[org.sireum.AssocS[_, _]](_).keySet) // methodKey(F, "org.sireum.AssocS", "keySet").value
    r.put(0x4CB17D1AB54562D0L, X[org.sireum.AssocS[_, _]](_).valueSet) // methodKey(F, "org.sireum.AssocS", "valueSet").value
    r.put(0x6C073ACE949EED18L, X[org.sireum.AssocS[_, _]](_).isEmpty) // methodKey(F, "org.sireum.AssocS", "isEmpty").value
    r.put(0xCDF8D0550413FEF0L, X[org.sireum.AssocS[_, _]](_).nonEmpty) // methodKey(F, "org.sireum.AssocS", "nonEmpty").value
    r.put(0x78FFED3F0A646E4CL, X[org.sireum.AssocS[_, _]](_).size) // methodKey(F, "org.sireum.AssocS", "size").value
    r.put(0x6329883C58D3B78BL, X[org.sireum.AssocS[_, _]](_).string) // methodKey(F, "org.sireum.AssocS", "string").value
    r.put(0x76277DBA56BFBEB6L, X[org.sireum.Bag[_]](_).map) // methodKey(F, "org.sireum.Bag", "map").value
    r.put(0xAD5449B003689F50L, X[org.sireum.Bag[_]](_).size) // methodKey(F, "org.sireum.Bag", "size").value
    r.put(0x84F49574E9895AB0L, X[org.sireum.Bag[_]](_).elements) // methodKey(F, "org.sireum.Bag", "elements").value
    r.put(0xFA2128D55B20B854L, X[org.sireum.Bag[_]](_).isEmpty) // methodKey(F, "org.sireum.Bag", "isEmpty").value
    r.put(0xCA01C47C167AB2EFL, X[org.sireum.Bag[_]](_).nonEmpty) // methodKey(F, "org.sireum.Bag", "nonEmpty").value
    r.put(0x0E9086DBD0E8D949L, X[org.sireum.Bag[_]](_).entries) // methodKey(F, "org.sireum.Bag", "entries").value
    r.put(0x45730085E39225BBL, X[org.sireum.Bag[_]](_).string) // methodKey(F, "org.sireum.Bag", "string").value
    r.put(0x223C1F06107C2E62L, X[org.sireum.CircularQueue[_]](_).max) // methodKey(F, "org.sireum.CircularQueue", "max").value
    r.put(0x3D4C892767234BC8L, X[org.sireum.CircularQueue[_]](_).default) // methodKey(F, "org.sireum.CircularQueue", "default").value
    r.put(0xE7D06D3B33067175L, X[org.sireum.CircularQueue[_]](_).scrub) // methodKey(F, "org.sireum.CircularQueue", "scrub").value
    r.put(0xD49CD5CEC8E01284L, X[org.sireum.CircularQueue[_]](_).policy) // methodKey(F, "org.sireum.CircularQueue", "policy").value
    r.put(0xE7318356AC819909L, X[org.sireum.CircularQueue[_]](_).size) // methodKey(F, "org.sireum.CircularQueue", "size").value
    r.put(0x73C70C0EE547E8CCL, X[org.sireum.CircularQueue[_]](_).isEmpty) // methodKey(F, "org.sireum.CircularQueue", "isEmpty").value
    r.put(0x95A445364D5E454CL, X[org.sireum.CircularQueue[_]](_).nonEmpty) // methodKey(F, "org.sireum.CircularQueue", "nonEmpty").value
    r.put(0x4EB7801F271CB48BL, X[org.sireum.CircularQueue[_]](_).isFull) // methodKey(F, "org.sireum.CircularQueue", "isFull").value
    r.put(0xECC7D595417EDBEAL, X[org.sireum.CircularQueue[_]](_).dequeue()) // methodKey(F, "org.sireum.CircularQueue", "dequeue").value
    r.put(0x81C91BFB08E0863CL, X[org.sireum.CircularQueue[_]](_).elements) // methodKey(F, "org.sireum.CircularQueue", "elements").value
    r.put(0x368E681149492D69L, X[org.sireum.CircularQueue[_]](_).string) // methodKey(F, "org.sireum.CircularQueue", "string").value
    r.put(0x5DC7A12F41A0195AL, X[org.sireum.CircularQueue.NoDrop[_]](_).max) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "max").value
    r.put(0xF040A4E28B79100CL, X[org.sireum.CircularQueue.NoDrop[_]](_).default) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "default").value
    r.put(0xCF17D6488B108FBAL, X[org.sireum.CircularQueue.NoDrop[_]](_).scrub) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "scrub").value
    r.put(0x9B97E4274520A738L, X[org.sireum.CircularQueue.NoDrop[_]](_).queue) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "queue").value
    r.put(0x83635F2544DBB000L, X[org.sireum.CircularQueue.NoDrop[_]](_).front) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "front").value
    r.put(0x8AA3A9EC01F6C68CL, X[org.sireum.CircularQueue.NoDrop[_]](_).rear) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "rear").value
    r.put(0x2F25A3C1171BE437L, X[org.sireum.CircularQueue.NoDrop[_]](_).numOfElements) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "numOfElements").value
    r.put(0x4875C30AC81476EDL, X[org.sireum.CircularQueue.NoDrop[_]](_).policy) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "policy").value
    r.put(0x5B30B166598F5AE1L, X[org.sireum.CircularQueue.NoDrop[_]](_).size) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "size").value
    r.put(0x7C4F6842A0CF624AL, X[org.sireum.CircularQueue.NoDrop[_]](_).isEmpty) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "isEmpty").value
    r.put(0x5EDC6B81D4B5BA28L, X[org.sireum.CircularQueue.NoDrop[_]](_).nonEmpty) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "nonEmpty").value
    r.put(0x421D06476079545CL, X[org.sireum.CircularQueue.NoDrop[_]](_).isFull) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "isFull").value
    r.put(0x8FE9A26F34520CC8L, X[org.sireum.CircularQueue.NoDrop[_]](_).dequeue()) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "dequeue").value
    r.put(0x0B7C093A31A6B20CL, X[org.sireum.CircularQueue.NoDrop[_]](_).elements) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "elements").value
    r.put(0x501575E7B8021981L, X[org.sireum.CircularQueue.NoDrop[_]](_).string) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "string").value
    r.put(0xFF40A2140F41420BL, X[org.sireum.CircularQueue.DropFront[_]](_).max) // methodKey(F, "org.sireum.CircularQueue.DropFront", "max").value
    r.put(0xA514EE074D755F3BL, X[org.sireum.CircularQueue.DropFront[_]](_).default) // methodKey(F, "org.sireum.CircularQueue.DropFront", "default").value
    r.put(0xD0A8ACC2B5C4BD19L, X[org.sireum.CircularQueue.DropFront[_]](_).scrub) // methodKey(F, "org.sireum.CircularQueue.DropFront", "scrub").value
    r.put(0xCB2AB9271689BAD4L, X[org.sireum.CircularQueue.DropFront[_]](_).queue) // methodKey(F, "org.sireum.CircularQueue.DropFront", "queue").value
    r.put(0x5602F90ADED609BBL, X[org.sireum.CircularQueue.DropFront[_]](_).front) // methodKey(F, "org.sireum.CircularQueue.DropFront", "front").value
    r.put(0xAFE9BE534536CC65L, X[org.sireum.CircularQueue.DropFront[_]](_).rear) // methodKey(F, "org.sireum.CircularQueue.DropFront", "rear").value
    r.put(0xDEA8748FECD2A64DL, X[org.sireum.CircularQueue.DropFront[_]](_).numOfElements) // methodKey(F, "org.sireum.CircularQueue.DropFront", "numOfElements").value
    r.put(0x92BBF2D639C60C74L, X[org.sireum.CircularQueue.DropFront[_]](_).policy) // methodKey(F, "org.sireum.CircularQueue.DropFront", "policy").value
    r.put(0x42B366522E7CD6E6L, X[org.sireum.CircularQueue.DropFront[_]](_).size) // methodKey(F, "org.sireum.CircularQueue.DropFront", "size").value
    r.put(0x1E5194BDEA2CC2BEL, X[org.sireum.CircularQueue.DropFront[_]](_).isEmpty) // methodKey(F, "org.sireum.CircularQueue.DropFront", "isEmpty").value
    r.put(0xED587D4B493BFE5DL, X[org.sireum.CircularQueue.DropFront[_]](_).nonEmpty) // methodKey(F, "org.sireum.CircularQueue.DropFront", "nonEmpty").value
    r.put(0x7F4F15F9B2923C6CL, X[org.sireum.CircularQueue.DropFront[_]](_).isFull) // methodKey(F, "org.sireum.CircularQueue.DropFront", "isFull").value
    r.put(0x5929FC5EB460EB48L, X[org.sireum.CircularQueue.DropFront[_]](_).dequeue()) // methodKey(F, "org.sireum.CircularQueue.DropFront", "dequeue").value
    r.put(0x208C4EC36D13AA58L, X[org.sireum.CircularQueue.DropFront[_]](_).elements) // methodKey(F, "org.sireum.CircularQueue.DropFront", "elements").value
    r.put(0x9C20899BB57704D3L, X[org.sireum.CircularQueue.DropFront[_]](_).string) // methodKey(F, "org.sireum.CircularQueue.DropFront", "string").value
    r.put(0xBAED923342328B90L, X[org.sireum.CircularQueue.DropRear[_]](_).max) // methodKey(F, "org.sireum.CircularQueue.DropRear", "max").value
    r.put(0x56E1E82040819A7CL, X[org.sireum.CircularQueue.DropRear[_]](_).default) // methodKey(F, "org.sireum.CircularQueue.DropRear", "default").value
    r.put(0xF3508F6EBBD543CAL, X[org.sireum.CircularQueue.DropRear[_]](_).scrub) // methodKey(F, "org.sireum.CircularQueue.DropRear", "scrub").value
    r.put(0x7A1390FB997EA0BCL, X[org.sireum.CircularQueue.DropRear[_]](_).queue) // methodKey(F, "org.sireum.CircularQueue.DropRear", "queue").value
    r.put(0xA2931F54E05A217BL, X[org.sireum.CircularQueue.DropRear[_]](_).front) // methodKey(F, "org.sireum.CircularQueue.DropRear", "front").value
    r.put(0x82E19E80A35BFD5EL, X[org.sireum.CircularQueue.DropRear[_]](_).rear) // methodKey(F, "org.sireum.CircularQueue.DropRear", "rear").value
    r.put(0x0EC733AA128D14B3L, X[org.sireum.CircularQueue.DropRear[_]](_).numOfElements) // methodKey(F, "org.sireum.CircularQueue.DropRear", "numOfElements").value
    r.put(0x6170B5145708A8C9L, X[org.sireum.CircularQueue.DropRear[_]](_).policy) // methodKey(F, "org.sireum.CircularQueue.DropRear", "policy").value
    r.put(0xAC44F744B1C7923DL, X[org.sireum.CircularQueue.DropRear[_]](_).size) // methodKey(F, "org.sireum.CircularQueue.DropRear", "size").value
    r.put(0x3333A7E8BBE991D8L, X[org.sireum.CircularQueue.DropRear[_]](_).isEmpty) // methodKey(F, "org.sireum.CircularQueue.DropRear", "isEmpty").value
    r.put(0xFE38156A35BC937BL, X[org.sireum.CircularQueue.DropRear[_]](_).nonEmpty) // methodKey(F, "org.sireum.CircularQueue.DropRear", "nonEmpty").value
    r.put(0xBC06802938A9E29BL, X[org.sireum.CircularQueue.DropRear[_]](_).isFull) // methodKey(F, "org.sireum.CircularQueue.DropRear", "isFull").value
    r.put(0xB9E27A19EF29A998L, X[org.sireum.CircularQueue.DropRear[_]](_).dequeue()) // methodKey(F, "org.sireum.CircularQueue.DropRear", "dequeue").value
    r.put(0x4270E60161D4F430L, X[org.sireum.CircularQueue.DropRear[_]](_).elements) // methodKey(F, "org.sireum.CircularQueue.DropRear", "elements").value
    r.put(0x083BFBEFA08914F4L, X[org.sireum.CircularQueue.DropRear[_]](_).string) // methodKey(F, "org.sireum.CircularQueue.DropRear", "string").value
    r.put(0xAAE77EAF79958027L, X[org.sireum.Either[_, _]](_).isLeft) // methodKey(F, "org.sireum.Either", "isLeft").value
    r.put(0x2F822A8F70E0269EL, X[org.sireum.Either[_, _]](_).isRight) // methodKey(F, "org.sireum.Either", "isRight").value
    r.put(0xD0717150BABD768FL, X[org.sireum.Either[_, _]](_).leftOpt) // methodKey(F, "org.sireum.Either", "leftOpt").value
    r.put(0x560F20931A6F4D3DL, X[org.sireum.Either[_, _]](_).left) // methodKey(F, "org.sireum.Either", "left").value
    r.put(0x06229B602C8AB6F7L, X[org.sireum.Either[_, _]](_).rightOpt) // methodKey(F, "org.sireum.Either", "rightOpt").value
    r.put(0x52AA8ED57EA119F1L, X[org.sireum.Either[_, _]](_).right) // methodKey(F, "org.sireum.Either", "right").value
    r.put(0x969093BCFCE53975L, X[org.sireum.Either.Left[_, _]](_).value) // methodKey(F, "org.sireum.Either.Left", "value").value
    r.put(0x00B9C41459E4D037L, X[org.sireum.Either.Left[_, _]](_).isLeft) // methodKey(F, "org.sireum.Either.Left", "isLeft").value
    r.put(0x1ECD1DC87C0826B1L, X[org.sireum.Either.Left[_, _]](_).isRight) // methodKey(F, "org.sireum.Either.Left", "isRight").value
    r.put(0x01967E572F6632AFL, X[org.sireum.Either.Left[_, _]](_).leftOpt) // methodKey(F, "org.sireum.Either.Left", "leftOpt").value
    r.put(0x58ED77E0C66452B4L, X[org.sireum.Either.Left[_, _]](_).left) // methodKey(F, "org.sireum.Either.Left", "left").value
    r.put(0x0995385CD20A51CFL, X[org.sireum.Either.Left[_, _]](_).rightOpt) // methodKey(F, "org.sireum.Either.Left", "rightOpt").value
    r.put(0x7081EACF3055BE6FL, X[org.sireum.Either.Left[_, _]](_).right) // methodKey(F, "org.sireum.Either.Left", "right").value
    r.put(0x3751193D4CA11DA1L, X[org.sireum.Either.Right[_, _]](_).value) // methodKey(F, "org.sireum.Either.Right", "value").value
    r.put(0xC540A20143E31C9BL, X[org.sireum.Either.Right[_, _]](_).isLeft) // methodKey(F, "org.sireum.Either.Right", "isLeft").value
    r.put(0xC5F355FBA8C435EEL, X[org.sireum.Either.Right[_, _]](_).isRight) // methodKey(F, "org.sireum.Either.Right", "isRight").value
    r.put(0x8F9EA91CECC1384DL, X[org.sireum.Either.Right[_, _]](_).leftOpt) // methodKey(F, "org.sireum.Either.Right", "leftOpt").value
    r.put(0xB309B00ACA69625DL, X[org.sireum.Either.Right[_, _]](_).left) // methodKey(F, "org.sireum.Either.Right", "left").value
    r.put(0xE36EF0E9793CFDA3L, X[org.sireum.Either.Right[_, _]](_).rightOpt) // methodKey(F, "org.sireum.Either.Right", "rightOpt").value
    r.put(0xD18121A971BE8F5CL, X[org.sireum.Either.Right[_, _]](_).right) // methodKey(F, "org.sireum.Either.Right", "right").value
    r.put(0x55240CEFBF3EB8CEL, X[org.sireum.Graph.Edge[_, _]](_).source) // methodKey(F, "org.sireum.Graph.Edge", "source").value
    r.put(0x70CBD6CF9812FED1L, X[org.sireum.Graph.Edge[_, _]](_).dest) // methodKey(F, "org.sireum.Graph.Edge", "dest").value
    r.put(0x903DD615139EBB74L, X[org.sireum.Graph.Edge.Plain[_, _]](_).source) // methodKey(F, "org.sireum.Graph.Edge.Plain", "source").value
    r.put(0x60D75CDC943DEC77L, X[org.sireum.Graph.Edge.Plain[_, _]](_).dest) // methodKey(F, "org.sireum.Graph.Edge.Plain", "dest").value
    r.put(0x27F5242BB71E4FD4L, X[org.sireum.Graph.Edge.Data[_, _]](_).source) // methodKey(F, "org.sireum.Graph.Edge.Data", "source").value
    r.put(0x0F0295FF0CA5AC6AL, X[org.sireum.Graph.Edge.Data[_, _]](_).dest) // methodKey(F, "org.sireum.Graph.Edge.Data", "dest").value
    r.put(0x13E3C28C365B1FD6L, X[org.sireum.Graph.Edge.Data[_, _]](_).data) // methodKey(F, "org.sireum.Graph.Edge.Data", "data").value
    r.put(0x37D14B950AEC31EBL, X[org.sireum.Graph.Internal.Edge[_]](_).source) // methodKey(F, "org.sireum.Graph.Internal.Edge", "source").value
    r.put(0x15FD5186D215A53BL, X[org.sireum.Graph.Internal.Edge[_]](_).dest) // methodKey(F, "org.sireum.Graph.Internal.Edge", "dest").value
    r.put(0x9235930F9A060AA5L, X[org.sireum.Graph.Internal.Edges[_]](_).elements) // methodKey(F, "org.sireum.Graph.Internal.Edges", "elements").value
    r.put(0xD04F866D5C14B73AL, X[org.sireum.Graph.Internal.Edges[_]](_).size) // methodKey(F, "org.sireum.Graph.Internal.Edges", "size").value
    r.put(0x4BE6F5EE429E8616L, X[org.sireum.Graph.Internal.Edges.Set[_]](_).set) // methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "set").value
    r.put(0x5EC4D2B307D05499L, X[org.sireum.Graph.Internal.Edges.Set[_]](_).elements) // methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "elements").value
    r.put(0x306E16ACB9B0926DL, X[org.sireum.Graph.Internal.Edges.Set[_]](_).size) // methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "size").value
    r.put(0xA5E5C327787ADF29L, X[org.sireum.Graph.Internal.Edges.Bag[_]](_).set) // methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "set").value
    r.put(0xAEA9E6B6F301B258L, X[org.sireum.Graph.Internal.Edges.Bag[_]](_).elements) // methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "elements").value
    r.put(0x476CF556CF1AF28DL, X[org.sireum.Graph.Internal.Edges.Bag[_]](_).size) // methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "size").value
    r.put(0xDED88B81E1C994A3L, X[org.sireum.Graph.Internal.Edge.Plain[_]](_).source) // methodKey(F, "org.sireum.Graph.Internal.Edge.Plain", "source").value
    r.put(0x2AC2D49938BB711BL, X[org.sireum.Graph.Internal.Edge.Plain[_]](_).dest) // methodKey(F, "org.sireum.Graph.Internal.Edge.Plain", "dest").value
    r.put(0x5FF1D7A11A0C3639L, X[org.sireum.Graph.Internal.Edge.Data[_]](_).source) // methodKey(F, "org.sireum.Graph.Internal.Edge.Data", "source").value
    r.put(0x13139E9F30CD591DL, X[org.sireum.Graph.Internal.Edge.Data[_]](_).dest) // methodKey(F, "org.sireum.Graph.Internal.Edge.Data", "dest").value
    r.put(0xBA7CE2A5E375A3CBL, X[org.sireum.Graph.Internal.Edge.Data[_]](_).data) // methodKey(F, "org.sireum.Graph.Internal.Edge.Data", "data").value
    r.put(0x57D6E4700AC95C07L, X[org.sireum.Graph[_, _]](_).nodes) // methodKey(F, "org.sireum.Graph", "nodes").value
    r.put(0x590002C0539358ECL, X[org.sireum.Graph[_, _]](_).nodesInverse) // methodKey(F, "org.sireum.Graph", "nodesInverse").value
    r.put(0xB1F093D049E10503L, X[org.sireum.Graph[_, _]](_).incomingEdges) // methodKey(F, "org.sireum.Graph", "incomingEdges").value
    r.put(0xDB545E9EEFEC782CL, X[org.sireum.Graph[_, _]](_).outgoingEdges) // methodKey(F, "org.sireum.Graph", "outgoingEdges").value
    r.put(0x8B3BEEEA206C8BFBL, X[org.sireum.Graph[_, _]](_).nextNodeId) // methodKey(F, "org.sireum.Graph", "nextNodeId").value
    r.put(0x0E4646D7446A0029L, X[org.sireum.Graph[_, _]](_).multi) // methodKey(F, "org.sireum.Graph", "multi").value
    r.put(0xFFB19AC0792729AAL, X[org.sireum.Graph[_, _]](_).allEdges) // methodKey(F, "org.sireum.Graph", "allEdges").value
    r.put(0xF17B714E62DE85A0L, X[org.sireum.Graph[_, _]](_).numOfNodes) // methodKey(F, "org.sireum.Graph", "numOfNodes").value
    r.put(0x6761EF83E13D8F9EL, X[org.sireum.Graph[_, _]](_).numOfEdges) // methodKey(F, "org.sireum.Graph", "numOfEdges").value
    r.put(0x3D0559A0B5177C06L, X[org.sireum.Graph[_, _]](_).hash) // methodKey(F, "org.sireum.Graph", "hash").value
    r.put(0xC3E9872E88387C3BL, X[org.sireum.Graph[_, _]](_).string) // methodKey(F, "org.sireum.Graph", "string").value
    r.put(0x11CECA6C5DB29A25L, X[org.sireum.HashBag[_]](_).map) // methodKey(F, "org.sireum.HashBag", "map").value
    r.put(0x0417D07F496D4F48L, X[org.sireum.HashBag[_]](_).size) // methodKey(F, "org.sireum.HashBag", "size").value
    r.put(0x64F7A253EE7E5AEEL, X[org.sireum.HashBag[_]](_).elements) // methodKey(F, "org.sireum.HashBag", "elements").value
    r.put(0xB6224B2F8FF1C04BL, X[org.sireum.HashBag[_]](_).isEmpty) // methodKey(F, "org.sireum.HashBag", "isEmpty").value
    r.put(0x028283385166C7E5L, X[org.sireum.HashBag[_]](_).nonEmpty) // methodKey(F, "org.sireum.HashBag", "nonEmpty").value
    r.put(0x05585FBAEC6EAC73L, X[org.sireum.HashBag[_]](_).entries) // methodKey(F, "org.sireum.HashBag", "entries").value
    r.put(0xCB7D5A19A07B5336L, X[org.sireum.HashBag[_]](_).string) // methodKey(F, "org.sireum.HashBag", "string").value
    r.put(0x7075D377D6CE5551L, X[org.sireum.HashMap[_, _]](_).mapEntries) // methodKey(F, "org.sireum.HashMap", "mapEntries").value
    r.put(0xCE4CB4B56644A20BL, X[org.sireum.HashMap[_, _]](_).size) // methodKey(F, "org.sireum.HashMap", "size").value
    r.put(0xCC08BFE28C980847L, X[org.sireum.HashMap[_, _]](_).entries) // methodKey(F, "org.sireum.HashMap", "entries").value
    r.put(0x44900222B91A2A70L, X[org.sireum.HashMap[_, _]](_).keys) // methodKey(F, "org.sireum.HashMap", "keys").value
    r.put(0xC79610A26A7D8F2AL, X[org.sireum.HashMap[_, _]](_).values) // methodKey(F, "org.sireum.HashMap", "values").value
    r.put(0x0C0146FC21923A10L, X[org.sireum.HashMap[_, _]](_).keySet) // methodKey(F, "org.sireum.HashMap", "keySet").value
    r.put(0x70133DF796A94034L, X[org.sireum.HashMap[_, _]](_).valueSet) // methodKey(F, "org.sireum.HashMap", "valueSet").value
    r.put(0x63D776EB10C63CE4L, X[org.sireum.HashMap[_, _]](_).isEmpty) // methodKey(F, "org.sireum.HashMap", "isEmpty").value
    r.put(0xB19DF446C1962523L, X[org.sireum.HashMap[_, _]](_).nonEmpty) // methodKey(F, "org.sireum.HashMap", "nonEmpty").value
    r.put(0xB1C65CD08579E6E7L, X[org.sireum.HashMap[_, _]](_).string) // methodKey(F, "org.sireum.HashMap", "string").value
    r.put(0xC205519E2CAD1AEAL, X[org.sireum.HashMap[_, _]](_).hash) // methodKey(F, "org.sireum.HashMap", "hash").value
    r.put(0x8D6DB3863687AE9FL, X[org.sireum.HashSBag[_]](_).map) // methodKey(F, "org.sireum.HashSBag", "map").value
    r.put(0xE015050BABF2465DL, X[org.sireum.HashSBag[_]](_).size) // methodKey(F, "org.sireum.HashSBag", "size").value
    r.put(0x287DC532FD79BD04L, X[org.sireum.HashSBag[_]](_).elements) // methodKey(F, "org.sireum.HashSBag", "elements").value
    r.put(0xA4506FBCDE362798L, X[org.sireum.HashSBag[_]](_).isEmpty) // methodKey(F, "org.sireum.HashSBag", "isEmpty").value
    r.put(0x2A3BDAEC1E701CE4L, X[org.sireum.HashSBag[_]](_).nonEmpty) // methodKey(F, "org.sireum.HashSBag", "nonEmpty").value
    r.put(0xD70CF0F6487F4401L, X[org.sireum.HashSBag[_]](_).entries) // methodKey(F, "org.sireum.HashSBag", "entries").value
    r.put(0xE65948CC431CDF5DL, X[org.sireum.HashSBag[_]](_).string) // methodKey(F, "org.sireum.HashSBag", "string").value
    r.put(0x24864ADE14D85CA4L, X[org.sireum.HashSMap[_, _]](_).map) // methodKey(F, "org.sireum.HashSMap", "map").value
    r.put(0xA51A087485E52DCEL, X[org.sireum.HashSMap[_, _]](_).keys) // methodKey(F, "org.sireum.HashSMap", "keys").value
    r.put(0x4108B8ECC45E15D0L, X[org.sireum.HashSMap[_, _]](_).size) // methodKey(F, "org.sireum.HashSMap", "size").value
    r.put(0xB6C980F5FEE3AE75L, X[org.sireum.HashSMap[_, _]](_).entries) // methodKey(F, "org.sireum.HashSMap", "entries").value
    r.put(0x316D377EAC8A9164L, X[org.sireum.HashSMap[_, _]](_).values) // methodKey(F, "org.sireum.HashSMap", "values").value
    r.put(0xE3A40F3DE9A2453FL, X[org.sireum.HashSMap[_, _]](_).keySet) // methodKey(F, "org.sireum.HashSMap", "keySet").value
    r.put(0xBD30F906E84E433FL, X[org.sireum.HashSMap[_, _]](_).valueSet) // methodKey(F, "org.sireum.HashSMap", "valueSet").value
    r.put(0xFD283301B59B92DFL, X[org.sireum.HashSMap[_, _]](_).isEmpty) // methodKey(F, "org.sireum.HashSMap", "isEmpty").value
    r.put(0xEB429920957EA19CL, X[org.sireum.HashSMap[_, _]](_).nonEmpty) // methodKey(F, "org.sireum.HashSMap", "nonEmpty").value
    r.put(0x67C3AF2E5ADA2406L, X[org.sireum.HashSMap[_, _]](_).string) // methodKey(F, "org.sireum.HashSMap", "string").value
    r.put(0x068AFA70FAFA0707L, X[org.sireum.HashSMap[_, _]](_).hash) // methodKey(F, "org.sireum.HashSMap", "hash").value
    r.put(0x54FAD30B61718A3EL, X[org.sireum.HashSSet[_]](_).map) // methodKey(F, "org.sireum.HashSSet", "map").value
    r.put(0x53F0849083D302E7L, X[org.sireum.HashSSet[_]](_).hash) // methodKey(F, "org.sireum.HashSSet", "hash").value
    r.put(0x55796D04B482BF24L, X[org.sireum.HashSSet[_]](_).isEmpty) // methodKey(F, "org.sireum.HashSSet", "isEmpty").value
    r.put(0x9411F6DCC1A0213CL, X[org.sireum.HashSSet[_]](_).nonEmpty) // methodKey(F, "org.sireum.HashSSet", "nonEmpty").value
    r.put(0x33717A7874A8E8D6L, X[org.sireum.HashSSet[_]](_).size) // methodKey(F, "org.sireum.HashSSet", "size").value
    r.put(0x88745B24F5BAD0AEL, X[org.sireum.HashSSet[_]](_).elements) // methodKey(F, "org.sireum.HashSSet", "elements").value
    r.put(0x6756AAB6782B6D46L, X[org.sireum.HashSSet[_]](_).string) // methodKey(F, "org.sireum.HashSSet", "string").value
    r.put(0xC58EA225CC631AACL, X[org.sireum.HashSet[_]](_).map) // methodKey(F, "org.sireum.HashSet", "map").value
    r.put(0x860BDED12EE54AB9L, X[org.sireum.HashSet[_]](_).hash) // methodKey(F, "org.sireum.HashSet", "hash").value
    r.put(0xCDB68C22AE1AE76FL, X[org.sireum.HashSet[_]](_).isEmpty) // methodKey(F, "org.sireum.HashSet", "isEmpty").value
    r.put(0xF72D16447F4A3A27L, X[org.sireum.HashSet[_]](_).nonEmpty) // methodKey(F, "org.sireum.HashSet", "nonEmpty").value
    r.put(0x67ABDFD9D0E31FACL, X[org.sireum.HashSet[_]](_).size) // methodKey(F, "org.sireum.HashSet", "size").value
    r.put(0x6F3C59D424DED487L, X[org.sireum.HashSet[_]](_).elements) // methodKey(F, "org.sireum.HashSet", "elements").value
    r.put(0xA1B2A8FAC7523B03L, X[org.sireum.HashSet[_]](_).string) // methodKey(F, "org.sireum.HashSet", "string").value
    r.put(0x38D34BD1B77D97AEL, X[org.sireum.IndexMap[_, _]](_).s) // methodKey(F, "org.sireum.IndexMap", "s").value
    r.put(0x171B31E618BBEF27L, X[org.sireum.IndexMap[_, _]](_).size) // methodKey(F, "org.sireum.IndexMap", "size").value
    r.put(0x291DCC5A91D699EBL, X[org.sireum.IndexMap[_, _]](_).entries) // methodKey(F, "org.sireum.IndexMap", "entries").value
    r.put(0x18C85343074BC388L, X[org.sireum.IndexMap[_, _]](_).keys) // methodKey(F, "org.sireum.IndexMap", "keys").value
    r.put(0x950F56C2A5A02FFBL, X[org.sireum.IndexMap[_, _]](_).values) // methodKey(F, "org.sireum.IndexMap", "values").value
    r.put(0xAB964318F56E2603L, X[org.sireum.IndexMap[_, _]](_).prettyST) // methodKey(F, "org.sireum.IndexMap", "prettyST").value
    r.put(0x36978CB2B0C86340L, X[org.sireum.IndexMap[_, _]](_).string) // methodKey(F, "org.sireum.IndexMap", "string").value
    r.put(0xB0BEE7CCBA4CDB92L, X[org.sireum.Indexable.Isz[_]](_).is) // methodKey(F, "org.sireum.Indexable.Isz", "is").value
    r.put(0xA3A23BD88A988B7BL, X[org.sireum.Indexable.IszDocInfo[_]](_).is) // methodKey(F, "org.sireum.Indexable.IszDocInfo", "is").value
    r.put(0x66CC763BE087E76DL, X[org.sireum.Indexable.IszDocInfo[_]](_).info) // methodKey(F, "org.sireum.Indexable.IszDocInfo", "info").value
    r.put(0x1BB03D0342BB4BC7L, X[org.sireum.Jen[_]](_).count()) // methodKey(F, "org.sireum.Jen", "count").value
    r.put(0x49AB3033CC454E67L, X[org.sireum.Jen[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen", "zipWithIndex").value
    r.put(0x278C8E8E5C1BACFBL, X[org.sireum.Jen[_]](_).head) // methodKey(F, "org.sireum.Jen", "head").value
    r.put(0x684D655591EC81B5L, X[org.sireum.Jen[_]](_).headOption) // methodKey(F, "org.sireum.Jen", "headOption").value
    r.put(0x8CD6C7BFE1DB6F53L, X[org.sireum.Jen[_]](_).toISZ) // methodKey(F, "org.sireum.Jen", "toISZ").value
    r.put(0x5DB39F91D4B2DB58L, X[org.sireum.Jen[_]](_).toMSZ) // methodKey(F, "org.sireum.Jen", "toMSZ").value
    r.put(0x9AEA729DC1847040L, X[org.sireum.Jen.Internal.ISImpl[_, _]](_).s) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "s").value
    r.put(0x44BA39CC01CAB452L, X[org.sireum.Jen.Internal.ISImpl[_, _]](_).string) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "string").value
    r.put(0x1ECB43BE41DB229EL, X[org.sireum.Jen.Internal.ISImpl[_, _]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "count").value
    r.put(0x4538CA39B4BCDA1FL, X[org.sireum.Jen.Internal.ISImpl[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "zipWithIndex").value
    r.put(0x708FC921DC35A819L, X[org.sireum.Jen.Internal.ISImpl[_, _]](_).head) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "head").value
    r.put(0x358BF973864B2587L, X[org.sireum.Jen.Internal.ISImpl[_, _]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "headOption").value
    r.put(0x3356633B4691D61AL, X[org.sireum.Jen.Internal.ISImpl[_, _]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "toISZ").value
    r.put(0xA65DD845D96249B2L, X[org.sireum.Jen.Internal.ISImpl[_, _]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "toMSZ").value
    r.put(0xAA6544E1CF9CE372L, X[org.sireum.Jen.Internal.MapImpl[_, _]](_).m) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "m").value
    r.put(0xDF18342AC22A9B4DL, X[org.sireum.Jen.Internal.MapImpl[_, _]](_).string) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "string").value
    r.put(0xAB137B3D10A3E031L, X[org.sireum.Jen.Internal.MapImpl[_, _]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "count").value
    r.put(0x5F21C5FF44C5CB85L, X[org.sireum.Jen.Internal.MapImpl[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "zipWithIndex").value
    r.put(0x77C84C96BD4A1575L, X[org.sireum.Jen.Internal.MapImpl[_, _]](_).head) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "head").value
    r.put(0xE42FD4BD60F39BA3L, X[org.sireum.Jen.Internal.MapImpl[_, _]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "headOption").value
    r.put(0xC540EFD702B58F32L, X[org.sireum.Jen.Internal.MapImpl[_, _]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "toISZ").value
    r.put(0xB70852F02A0142EEL, X[org.sireum.Jen.Internal.MapImpl[_, _]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "toMSZ").value
    r.put(0x2E027524BDFC4FF0L, X[org.sireum.Jen.Internal.HashMapImpl[_, _]](_).m) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "m").value
    r.put(0xBB2960EB923F7657L, X[org.sireum.Jen.Internal.HashMapImpl[_, _]](_).string) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "string").value
    r.put(0x4C47CBCD624FAA9BL, X[org.sireum.Jen.Internal.HashMapImpl[_, _]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "count").value
    r.put(0x0974C4ABE39EF3F6L, X[org.sireum.Jen.Internal.HashMapImpl[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "zipWithIndex").value
    r.put(0x53A942795B4D88D5L, X[org.sireum.Jen.Internal.HashMapImpl[_, _]](_).head) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "head").value
    r.put(0xE9C70B1FBEE0D8A8L, X[org.sireum.Jen.Internal.HashMapImpl[_, _]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "headOption").value
    r.put(0xD83E89054CB51665L, X[org.sireum.Jen.Internal.HashMapImpl[_, _]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "toISZ").value
    r.put(0xC04E4B76D212A272L, X[org.sireum.Jen.Internal.HashMapImpl[_, _]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "toMSZ").value
    r.put(0xA74A21AD9FCB63ACL, X[org.sireum.Jen.Internal.Filtered[_]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "gen").value
    r.put(0x7B636CE1742A07B8L, X[org.sireum.Jen.Internal.Filtered[_]](_).p) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "p").value
    r.put(0x0447C7DC058B58FBL, X[org.sireum.Jen.Internal.Filtered[_]](_).string) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "string").value
    r.put(0x4ACEED6B6F122940L, X[org.sireum.Jen.Internal.Filtered[_]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "count").value
    r.put(0x6A01807DCDF05A2EL, X[org.sireum.Jen.Internal.Filtered[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "zipWithIndex").value
    r.put(0xEDC3EE85DA620D4FL, X[org.sireum.Jen.Internal.Filtered[_]](_).head) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "head").value
    r.put(0xD598E6A5855E474BL, X[org.sireum.Jen.Internal.Filtered[_]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "headOption").value
    r.put(0x9BB8B1A72D81198EL, X[org.sireum.Jen.Internal.Filtered[_]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "toISZ").value
    r.put(0x8B4542C294698E11L, X[org.sireum.Jen.Internal.Filtered[_]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "toMSZ").value
    r.put(0xB4AFF47FC6944630L, X[org.sireum.Jen.Internal.Mapped[_, _]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "gen").value
    r.put(0x666E3465E7927372L, X[org.sireum.Jen.Internal.Mapped[_, _]](_).f) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "f").value
    r.put(0x7BB4A85113907737L, X[org.sireum.Jen.Internal.Mapped[_, _]](_).string) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "string").value
    r.put(0xFC7D980D0A05B1DCL, X[org.sireum.Jen.Internal.Mapped[_, _]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "count").value
    r.put(0x8E3AB87835B75781L, X[org.sireum.Jen.Internal.Mapped[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "zipWithIndex").value
    r.put(0xC694966F1DAFC63DL, X[org.sireum.Jen.Internal.Mapped[_, _]](_).head) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "head").value
    r.put(0x034E79B4D014E599L, X[org.sireum.Jen.Internal.Mapped[_, _]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "headOption").value
    r.put(0x225F75E4DC0EA227L, X[org.sireum.Jen.Internal.Mapped[_, _]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "toISZ").value
    r.put(0xBEBCDADBD493D3DEL, X[org.sireum.Jen.Internal.Mapped[_, _]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "toMSZ").value
    r.put(0xD3BE14C1D7901DE4L, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "gen").value
    r.put(0x1B138DBB5CA6FEA4L, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).f) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "f").value
    r.put(0xE684242635A98A01L, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).string) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "string").value
    r.put(0x6DABE7EF38243D99L, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "count").value
    r.put(0x539BEDBCAD2ACEEEL, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "zipWithIndex").value
    r.put(0x0B7DDDFA53AF5842L, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).head) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "head").value
    r.put(0x384BFCA355BBE450L, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "headOption").value
    r.put(0xEDF87CF33704312EL, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "toISZ").value
    r.put(0xEFA2F959C393BF97L, X[org.sireum.Jen.Internal.FlatMapped[_, _]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "toMSZ").value
    r.put(0x7B8EF4EAD605DE31L, X[org.sireum.Jen.Internal.Sliced[_]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "gen").value
    r.put(0x58614EEF6A1B4F6BL, X[org.sireum.Jen.Internal.Sliced[_]](_).start) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "start").value
    r.put(0x4734FEA37190BE20L, X[org.sireum.Jen.Internal.Sliced[_]](_).end) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "end").value
    r.put(0x1040DE74A2FCFBD8L, X[org.sireum.Jen.Internal.Sliced[_]](_).string) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "string").value
    r.put(0xB28EB5FEAEFC5C93L, X[org.sireum.Jen.Internal.Sliced[_]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "count").value
    r.put(0xB83F9B3EC508D06AL, X[org.sireum.Jen.Internal.Sliced[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "zipWithIndex").value
    r.put(0xAA12DFC04F0971C5L, X[org.sireum.Jen.Internal.Sliced[_]](_).head) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "head").value
    r.put(0x55A45923153525D9L, X[org.sireum.Jen.Internal.Sliced[_]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "headOption").value
    r.put(0x86C2F20435B6D08BL, X[org.sireum.Jen.Internal.Sliced[_]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "toISZ").value
    r.put(0x1F8DE11301B98871L, X[org.sireum.Jen.Internal.Sliced[_]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "toMSZ").value
    r.put(0x763E9BDBBB2DB39FL, X[org.sireum.Jen.Internal.TakeWhile[_]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "gen").value
    r.put(0xEEAA2B6444544F07L, X[org.sireum.Jen.Internal.TakeWhile[_]](_).p) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "p").value
    r.put(0x717C14DD689D34A0L, X[org.sireum.Jen.Internal.TakeWhile[_]](_).string) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "string").value
    r.put(0xBA2BD601697FEB17L, X[org.sireum.Jen.Internal.TakeWhile[_]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "count").value
    r.put(0xCCD6DA33BB550937L, X[org.sireum.Jen.Internal.TakeWhile[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "zipWithIndex").value
    r.put(0xBEF3C20B52B0C177L, X[org.sireum.Jen.Internal.TakeWhile[_]](_).head) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "head").value
    r.put(0x37C06782CE062732L, X[org.sireum.Jen.Internal.TakeWhile[_]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "headOption").value
    r.put(0xAFA5314C73A2DE5DL, X[org.sireum.Jen.Internal.TakeWhile[_]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "toISZ").value
    r.put(0x27E64C5DE430A42AL, X[org.sireum.Jen.Internal.TakeWhile[_]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "toMSZ").value
    r.put(0x684A6497FA64F6CCL, X[org.sireum.Jen.Internal.DropWhile[_]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "gen").value
    r.put(0x54DD9D5C7993DEF0L, X[org.sireum.Jen.Internal.DropWhile[_]](_).p) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "p").value
    r.put(0xD28357F961C9E47BL, X[org.sireum.Jen.Internal.DropWhile[_]](_).string) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "string").value
    r.put(0x45A487DADB78707DL, X[org.sireum.Jen.Internal.DropWhile[_]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "count").value
    r.put(0x9000A2378904C571L, X[org.sireum.Jen.Internal.DropWhile[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "zipWithIndex").value
    r.put(0x3C102CA704F1562DL, X[org.sireum.Jen.Internal.DropWhile[_]](_).head) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "head").value
    r.put(0x117C81C8D30C54A6L, X[org.sireum.Jen.Internal.DropWhile[_]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "headOption").value
    r.put(0xCEF869C2C8B55A82L, X[org.sireum.Jen.Internal.DropWhile[_]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "toISZ").value
    r.put(0xA097630B0C041565L, X[org.sireum.Jen.Internal.DropWhile[_]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "toMSZ").value
    r.put(0x3C7BF25485AAEBAFL, X[org.sireum.Jen.Internal.ZipWithIndexed[_]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "gen").value
    r.put(0x8C21F5D6B2654B1CL, X[org.sireum.Jen.Internal.ZipWithIndexed[_]](_).string) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "string").value
    r.put(0x94AC449E0159F956L, X[org.sireum.Jen.Internal.ZipWithIndexed[_]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "count").value
    r.put(0xFE7F83AE57380129L, X[org.sireum.Jen.Internal.ZipWithIndexed[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "zipWithIndex").value
    r.put(0x6F473ACC2151E1F2L, X[org.sireum.Jen.Internal.ZipWithIndexed[_]](_).head) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "head").value
    r.put(0x3A14C68F2615D96DL, X[org.sireum.Jen.Internal.ZipWithIndexed[_]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "headOption").value
    r.put(0x7E63E7E6B969DF44L, X[org.sireum.Jen.Internal.ZipWithIndexed[_]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "toISZ").value
    r.put(0xF4A23FC83FA13D74L, X[org.sireum.Jen.Internal.ZipWithIndexed[_]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "toMSZ").value
    r.put(0xE2F880D9097A7A3AL, X[org.sireum.Jen.Internal.Zipped[_, _]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "gen").value
    r.put(0x61786E07E75AB543L, X[org.sireum.Jen.Internal.Zipped[_, _]](_).gen2) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "gen2").value
    r.put(0x596CD4866534B55DL, X[org.sireum.Jen.Internal.Zipped[_, _]](_).string) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "string").value
    r.put(0x85022719A9C78EF9L, X[org.sireum.Jen.Internal.Zipped[_, _]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "count").value
    r.put(0xFDB60986EA7D2977L, X[org.sireum.Jen.Internal.Zipped[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "zipWithIndex").value
    r.put(0x414F4550F3AACF8EL, X[org.sireum.Jen.Internal.Zipped[_, _]](_).head) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "head").value
    r.put(0xB0A6739A1457CBE2L, X[org.sireum.Jen.Internal.Zipped[_, _]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "headOption").value
    r.put(0x67921FBDFC524CE7L, X[org.sireum.Jen.Internal.Zipped[_, _]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "toISZ").value
    r.put(0x3B1018B815151FFAL, X[org.sireum.Jen.Internal.Zipped[_, _]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "toMSZ").value
    r.put(0x4F680E13EAC3D452L, X[org.sireum.Jen.Internal.Concat[_]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.Concat", "gen").value
    r.put(0x1A68048137C41C09L, X[org.sireum.Jen.Internal.Concat[_]](_).gen2) // methodKey(F, "org.sireum.Jen.Internal.Concat", "gen2").value
    r.put(0x85C169F9BC594D88L, X[org.sireum.Jen.Internal.Concat[_]](_).string) // methodKey(F, "org.sireum.Jen.Internal.Concat", "string").value
    r.put(0x8FAD28D10B06FD07L, X[org.sireum.Jen.Internal.Concat[_]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.Concat", "count").value
    r.put(0xB9D8105988A1412FL, X[org.sireum.Jen.Internal.Concat[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.Concat", "zipWithIndex").value
    r.put(0x3B2EA745EFD653DDL, X[org.sireum.Jen.Internal.Concat[_]](_).head) // methodKey(F, "org.sireum.Jen.Internal.Concat", "head").value
    r.put(0x351BF0182BA0C00CL, X[org.sireum.Jen.Internal.Concat[_]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.Concat", "headOption").value
    r.put(0x33673D3836BC19F0L, X[org.sireum.Jen.Internal.Concat[_]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.Concat", "toISZ").value
    r.put(0xE2E026156646D7FBL, X[org.sireum.Jen.Internal.Concat[_]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.Concat", "toMSZ").value
    r.put(0xD02B3C88CF817CA7L, X[org.sireum.Jen.Internal.Product[_, _]](_).gen) // methodKey(F, "org.sireum.Jen.Internal.Product", "gen").value
    r.put(0x3B2814E81E5AC5CEL, X[org.sireum.Jen.Internal.Product[_, _]](_).gen2) // methodKey(F, "org.sireum.Jen.Internal.Product", "gen2").value
    r.put(0xEC0BD6317F3094C5L, X[org.sireum.Jen.Internal.Product[_, _]](_).string) // methodKey(F, "org.sireum.Jen.Internal.Product", "string").value
    r.put(0x00ED1CEB16366700L, X[org.sireum.Jen.Internal.Product[_, _]](_).count()) // methodKey(F, "org.sireum.Jen.Internal.Product", "count").value
    r.put(0x135B570E0626BBAFL, X[org.sireum.Jen.Internal.Product[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.Jen.Internal.Product", "zipWithIndex").value
    r.put(0x35FD48DDE1DE27E2L, X[org.sireum.Jen.Internal.Product[_, _]](_).head) // methodKey(F, "org.sireum.Jen.Internal.Product", "head").value
    r.put(0x26F54F97A094C910L, X[org.sireum.Jen.Internal.Product[_, _]](_).headOption) // methodKey(F, "org.sireum.Jen.Internal.Product", "headOption").value
    r.put(0x378AE4093F027C7DL, X[org.sireum.Jen.Internal.Product[_, _]](_).toISZ) // methodKey(F, "org.sireum.Jen.Internal.Product", "toISZ").value
    r.put(0x2DE874B3B8321AF0L, X[org.sireum.Jen.Internal.Product[_, _]](_).toMSZ) // methodKey(F, "org.sireum.Jen.Internal.Product", "toMSZ").value
    r.put(0x063AFEF0AE8BCD3DL, X[org.sireum.Json.JsonAstBinding[_]](_).toNull) // methodKey(F, "org.sireum.Json.JsonAstBinding", "toNull").value
    r.put(0x35BF75CAEC28EA16L, X[org.sireum.Json.ErrorMsg](_).line) // methodKey(F, "org.sireum.Json.ErrorMsg", "line").value
    r.put(0x3CFF06EEDBAEEFC5L, X[org.sireum.Json.ErrorMsg](_).column) // methodKey(F, "org.sireum.Json.ErrorMsg", "column").value
    r.put(0xEEB600A4DBFF909FL, X[org.sireum.Json.ErrorMsg](_).message) // methodKey(F, "org.sireum.Json.ErrorMsg", "message").value
    r.put(0xD6F9B6E0B4ADA9F7L, X[org.sireum.Json.Parser](_).input) // methodKey(F, "org.sireum.Json.Parser", "input").value
    r.put(0xD40574300D5398C7L, X[org.sireum.Json.Parser](_).offset) // methodKey(F, "org.sireum.Json.Parser", "offset").value
    r.put(0x81ABA5E6722133D7L, X[org.sireum.Json.Parser](_).errorOpt) // methodKey(F, "org.sireum.Json.Parser", "errorOpt").value
    r.put(0xEC5488A0D69593D9L, X[org.sireum.Json.Parser](_).typesOption) // methodKey(F, "org.sireum.Json.Parser", "typesOption").value
    r.put(0x37E487D54496B143L, X[org.sireum.Json.Parser](_).errorMessage) // methodKey(F, "org.sireum.Json.Parser", "errorMessage").value
    r.put(0xAAF7C0B9DE18C735L, X[org.sireum.Json.Parser](_).eof()) // methodKey(F, "org.sireum.Json.Parser", "eof").value
    r.put(0xF5A95A26239258E8L, X[org.sireum.Json.Parser](_).parseB()) // methodKey(F, "org.sireum.Json.Parser", "parseB").value
    r.put(0x6B999D8C5FA6CA67L, X[org.sireum.Json.Parser](_).parseC()) // methodKey(F, "org.sireum.Json.Parser", "parseC").value
    r.put(0x3E4352B36CC0E18BL, X[org.sireum.Json.Parser](_).parseZ()) // methodKey(F, "org.sireum.Json.Parser", "parseZ").value
    r.put(0x1A6189EE289B9711L, X[org.sireum.Json.Parser](_).parseZ8()) // methodKey(F, "org.sireum.Json.Parser", "parseZ8").value
    r.put(0xADFF75069BCBC9EBL, X[org.sireum.Json.Parser](_).parseZ16()) // methodKey(F, "org.sireum.Json.Parser", "parseZ16").value
    r.put(0x618E17F8D0413E24L, X[org.sireum.Json.Parser](_).parseZ32()) // methodKey(F, "org.sireum.Json.Parser", "parseZ32").value
    r.put(0x634326148343644AL, X[org.sireum.Json.Parser](_).parseZ64()) // methodKey(F, "org.sireum.Json.Parser", "parseZ64").value
    r.put(0xFF97ACD69BD02F14L, X[org.sireum.Json.Parser](_).parseN()) // methodKey(F, "org.sireum.Json.Parser", "parseN").value
    r.put(0xB1A2680D6CCD6B22L, X[org.sireum.Json.Parser](_).parseN8()) // methodKey(F, "org.sireum.Json.Parser", "parseN8").value
    r.put(0x3CB1B2693EC6C931L, X[org.sireum.Json.Parser](_).parseN16()) // methodKey(F, "org.sireum.Json.Parser", "parseN16").value
    r.put(0xF647F77B772BB0FBL, X[org.sireum.Json.Parser](_).parseN32()) // methodKey(F, "org.sireum.Json.Parser", "parseN32").value
    r.put(0xE9B6AAF11C70EE93L, X[org.sireum.Json.Parser](_).parseN64()) // methodKey(F, "org.sireum.Json.Parser", "parseN64").value
    r.put(0xDEC65E8D1AF625B9L, X[org.sireum.Json.Parser](_).parseS8()) // methodKey(F, "org.sireum.Json.Parser", "parseS8").value
    r.put(0xAACB1144F6B6060FL, X[org.sireum.Json.Parser](_).parseS16()) // methodKey(F, "org.sireum.Json.Parser", "parseS16").value
    r.put(0x942C2253F17DA469L, X[org.sireum.Json.Parser](_).parseS32()) // methodKey(F, "org.sireum.Json.Parser", "parseS32").value
    r.put(0x539F30F6398B09D4L, X[org.sireum.Json.Parser](_).parseS64()) // methodKey(F, "org.sireum.Json.Parser", "parseS64").value
    r.put(0xEA8CDC2B6D9BC925L, X[org.sireum.Json.Parser](_).parseU8()) // methodKey(F, "org.sireum.Json.Parser", "parseU8").value
    r.put(0x159FC936AAEF86B7L, X[org.sireum.Json.Parser](_).parseU16()) // methodKey(F, "org.sireum.Json.Parser", "parseU16").value
    r.put(0x5071A7049AA0F7D1L, X[org.sireum.Json.Parser](_).parseU32()) // methodKey(F, "org.sireum.Json.Parser", "parseU32").value
    r.put(0xE48E49CA6DE36E0EL, X[org.sireum.Json.Parser](_).parseU64()) // methodKey(F, "org.sireum.Json.Parser", "parseU64").value
    r.put(0x9A42E2EC6F6406BAL, X[org.sireum.Json.Parser](_).parseF32()) // methodKey(F, "org.sireum.Json.Parser", "parseF32").value
    r.put(0xC1A44F1D85AF5EABL, X[org.sireum.Json.Parser](_).parseF64()) // methodKey(F, "org.sireum.Json.Parser", "parseF64").value
    r.put(0xA625F86041D58A64L, X[org.sireum.Json.Parser](_).parseR()) // methodKey(F, "org.sireum.Json.Parser", "parseR").value
    r.put(0xCB010A395046444BL, X[org.sireum.Json.Parser](_).parseZS()) // methodKey(F, "org.sireum.Json.Parser", "parseZS").value
    r.put(0x7BF2B62403B72D09L, X[org.sireum.Json.Parser](_).parseMessage()) // methodKey(F, "org.sireum.Json.Parser", "parseMessage").value
    r.put(0xEADF946674011B0BL, X[org.sireum.Json.Parser](_).parsePosition()) // methodKey(F, "org.sireum.Json.Parser", "parsePosition").value
    r.put(0xAD983706E4D58B52L, X[org.sireum.Json.Parser](_).parseDocInfo()) // methodKey(F, "org.sireum.Json.Parser", "parseDocInfo").value
    r.put(0x54EC726E3637DD84L, X[org.sireum.Json.Parser](_).detect()) // methodKey(F, "org.sireum.Json.Parser", "detect").value
    r.put(0x6F3702F3E399C243L, X[org.sireum.Json.Parser](_).parseObjectBegin()) // methodKey(F, "org.sireum.Json.Parser", "parseObjectBegin").value
    r.put(0xFF26C2A86DC28A75L, X[org.sireum.Json.Parser](_).parseObjectNext()) // methodKey(F, "org.sireum.Json.Parser", "parseObjectNext").value
    r.put(0x5BF1EE2169EA42BEL, X[org.sireum.Json.Parser](_).parseArrayBegin()) // methodKey(F, "org.sireum.Json.Parser", "parseArrayBegin").value
    r.put(0xF9EE4AAD3C336F8CL, X[org.sireum.Json.Parser](_).parseArrayNext()) // methodKey(F, "org.sireum.Json.Parser", "parseArrayNext").value
    r.put(0xED82ECFB68A736E5L, X[org.sireum.Json.Parser](_).parseNumber()) // methodKey(F, "org.sireum.Json.Parser", "parseNumber").value
    r.put(0x9A6FAC83CC1B3E4EL, X[org.sireum.Json.Parser](_).parseString()) // methodKey(F, "org.sireum.Json.Parser", "parseString").value
    r.put(0x92596F36D2C6E53AL, X[org.sireum.Json.Parser](_).parseWhitespace()) // methodKey(F, "org.sireum.Json.Parser", "parseWhitespace").value
    r.put(0xFC518D2C3D292165L, X[org.sireum.MBox[_]](_).value) // methodKey(F, "org.sireum.MBox", "value").value
    r.put(0xB61A9F850C758B3AL, X[org.sireum.MBox2[_, _]](_).value1) // methodKey(F, "org.sireum.MBox2", "value1").value
    r.put(0xB9D2F442E2175A75L, X[org.sireum.MBox2[_, _]](_).value2) // methodKey(F, "org.sireum.MBox2", "value2").value
    r.put(0xA325943EFD0DF751L, X[org.sireum.MBox3[_, _, _]](_).value1) // methodKey(F, "org.sireum.MBox3", "value1").value
    r.put(0x73BFA8D55A7D6AA4L, X[org.sireum.MBox3[_, _, _]](_).value2) // methodKey(F, "org.sireum.MBox3", "value2").value
    r.put(0x3D5D418DDF95F471L, X[org.sireum.MBox3[_, _, _]](_).value3) // methodKey(F, "org.sireum.MBox3", "value3").value
    r.put(0x981B19E21BFD00BDL, X[org.sireum.MBox4[_, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox4", "value1").value
    r.put(0xBCB653D5A03E2C65L, X[org.sireum.MBox4[_, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox4", "value2").value
    r.put(0x5AD5B01F6DDC2CE8L, X[org.sireum.MBox4[_, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox4", "value3").value
    r.put(0x21AB04204C3D9731L, X[org.sireum.MBox4[_, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox4", "value4").value
    r.put(0xE849418E7FB1EB3BL, X[org.sireum.MBox5[_, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox5", "value1").value
    r.put(0x0FB5ACEDC7AE384CL, X[org.sireum.MBox5[_, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox5", "value2").value
    r.put(0xAE88DF6425E4A25FL, X[org.sireum.MBox5[_, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox5", "value3").value
    r.put(0x70266555E06EF94DL, X[org.sireum.MBox5[_, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox5", "value4").value
    r.put(0xE6CFAAB3AC64A367L, X[org.sireum.MBox5[_, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox5", "value5").value
    r.put(0x35DE551BEBAF2FC4L, X[org.sireum.MBox6[_, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox6", "value1").value
    r.put(0xBBB9C3C43D127F80L, X[org.sireum.MBox6[_, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox6", "value2").value
    r.put(0xDA0EB338104BA4A0L, X[org.sireum.MBox6[_, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox6", "value3").value
    r.put(0xEB067DD96C355ACBL, X[org.sireum.MBox6[_, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox6", "value4").value
    r.put(0xA66EE1180B4B0592L, X[org.sireum.MBox6[_, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox6", "value5").value
    r.put(0x161E277A408757E4L, X[org.sireum.MBox6[_, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox6", "value6").value
    r.put(0x0D6318C4BD4DB281L, X[org.sireum.MBox7[_, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox7", "value1").value
    r.put(0xEEA9D90766312BEFL, X[org.sireum.MBox7[_, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox7", "value2").value
    r.put(0x0F907565F337780EL, X[org.sireum.MBox7[_, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox7", "value3").value
    r.put(0xEFC13064CA6D5EE0L, X[org.sireum.MBox7[_, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox7", "value4").value
    r.put(0x88AB52DC3D3386AAL, X[org.sireum.MBox7[_, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox7", "value5").value
    r.put(0x6A598A55D248727DL, X[org.sireum.MBox7[_, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox7", "value6").value
    r.put(0x95E820F54F9BBBE4L, X[org.sireum.MBox7[_, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox7", "value7").value
    r.put(0x6EE392AD462EF167L, X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox8", "value1").value
    r.put(0xB3626086F5D1D02DL, X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox8", "value2").value
    r.put(0x478EB4920636CF9DL, X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox8", "value3").value
    r.put(0x6D9D14BA952BBBDAL, X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox8", "value4").value
    r.put(0x717B1E8CB8583B33L, X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox8", "value5").value
    r.put(0x34C25C83A8B182E6L, X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox8", "value6").value
    r.put(0x728F8F27AC922CB8L, X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox8", "value7").value
    r.put(0x4E77F52504DCCFC1L, X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox8", "value8").value
    r.put(0x504345716AD3DDF1L, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox9", "value1").value
    r.put(0x58321756722E09EBL, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox9", "value2").value
    r.put(0x7CC2F5EB6B9D87F0L, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox9", "value3").value
    r.put(0x8A419A508DD97A63L, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox9", "value4").value
    r.put(0x7FC1569A0140BE10L, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox9", "value5").value
    r.put(0x40E3499504AB0E61L, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox9", "value6").value
    r.put(0xFEDDC8D3041F40D4L, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox9", "value7").value
    r.put(0x6A46712A7CF01563L, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox9", "value8").value
    r.put(0x8AD9A6E158C96988L, X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox9", "value9").value
    r.put(0x3A9D481C87812FB9L, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox10", "value1").value
    r.put(0x43CD0AE0F64738EDL, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox10", "value2").value
    r.put(0xFCB691CDB87D9DB0L, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox10", "value3").value
    r.put(0x976FC58A2425CD35L, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox10", "value4").value
    r.put(0x606B1DCF7E59D4D2L, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox10", "value5").value
    r.put(0x0B73CD1CBABEAD53L, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox10", "value6").value
    r.put(0x3730DCB31E997C78L, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox10", "value7").value
    r.put(0xBAC54C1F235A375AL, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox10", "value8").value
    r.put(0x3BFBED2AC0A9FAF4L, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox10", "value9").value
    r.put(0x8F5C483B6711A507L, X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox10", "value10").value
    r.put(0xD70F76B598B0CD49L, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox11", "value1").value
    r.put(0xABA53DF11F756DC8L, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox11", "value2").value
    r.put(0xA8831C0F1647664AL, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox11", "value3").value
    r.put(0x56F6A0A5A782347CL, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox11", "value4").value
    r.put(0x74F78483A36E3364L, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox11", "value5").value
    r.put(0x2DABBE6A83394BC8L, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox11", "value6").value
    r.put(0xF92FE07FEA8C664DL, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox11", "value7").value
    r.put(0xB5FF0D054AA1EF20L, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox11", "value8").value
    r.put(0xDD04BDABD63768BDL, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox11", "value9").value
    r.put(0xF63500FF667D4030L, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox11", "value10").value
    r.put(0x67FD54A0968C25A0L, X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox11", "value11").value
    r.put(0x9BBC3E51975AEFD1L, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox12", "value1").value
    r.put(0x42A1F660262E77CBL, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox12", "value2").value
    r.put(0x9D6A9E23ECBCAC80L, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox12", "value3").value
    r.put(0x08FFE0A7FF0428CFL, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox12", "value4").value
    r.put(0x137B25A66AA313CEL, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox12", "value5").value
    r.put(0xDCF0C840381AF402L, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox12", "value6").value
    r.put(0xBA988AB81F4EA168L, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox12", "value7").value
    r.put(0xB884ABC22B68B341L, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox12", "value8").value
    r.put(0xA1C8A3CB1EF4BF99L, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox12", "value9").value
    r.put(0x39290EA7DD77BB21L, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox12", "value10").value
    r.put(0x7BE7599967F81AE6L, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox12", "value11").value
    r.put(0x07E1F9EBA06DA58DL, X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox12", "value12").value
    r.put(0x1F1AFBB8EE8DC3A6L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox13", "value1").value
    r.put(0x85F0E6FD040B5FFDL, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox13", "value2").value
    r.put(0x05FB7FBE2603FD3EL, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox13", "value3").value
    r.put(0xDC6DC9742A0D2918L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox13", "value4").value
    r.put(0x2B4559FBDD5DEBF4L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox13", "value5").value
    r.put(0x891765DD66EDDE4EL, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox13", "value6").value
    r.put(0x57CF3D0113EEBDC5L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox13", "value7").value
    r.put(0xB1BB32AF3D4146A6L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox13", "value8").value
    r.put(0x12CA215F152FC507L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox13", "value9").value
    r.put(0x8599E4B8C5A29834L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox13", "value10").value
    r.put(0x4783B0AB62C27F44L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox13", "value11").value
    r.put(0x67DC9344416D69EDL, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox13", "value12").value
    r.put(0x43F70FD9FB9F5448L, X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox13", "value13").value
    r.put(0x18FC5CF414D743F5L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox14", "value1").value
    r.put(0x6ACFCC0230D7031AL, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox14", "value2").value
    r.put(0x6F1D8AC5BBA315E6L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox14", "value3").value
    r.put(0xEBB3E6C1F7C43390L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox14", "value4").value
    r.put(0x4A69F5DF907A8E9CL, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox14", "value5").value
    r.put(0x8A083C340F4A83D0L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox14", "value6").value
    r.put(0x4D41A72560BF8845L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox14", "value7").value
    r.put(0x3E4700CB0FB149E6L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox14", "value8").value
    r.put(0xF3595788115CE6E8L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox14", "value9").value
    r.put(0x8703F8948B00B563L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox14", "value10").value
    r.put(0xA8DB56E2F775E46FL, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox14", "value11").value
    r.put(0xB71DC5BBDC5B6029L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox14", "value12").value
    r.put(0x05D02D5A5BD714F6L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox14", "value13").value
    r.put(0xE2E74178FDB163B0L, X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox14", "value14").value
    r.put(0x39F50343A556239EL, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox15", "value1").value
    r.put(0xBE581149109F1030L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox15", "value2").value
    r.put(0x49D85288D49ED1C4L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox15", "value3").value
    r.put(0xA0F46BC82F7FC6E2L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox15", "value4").value
    r.put(0xCD7F38EE94D56F44L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox15", "value5").value
    r.put(0x97741EF52A797555L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox15", "value6").value
    r.put(0xCCEBA61EFE2BF71CL, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox15", "value7").value
    r.put(0x504B8667ED261339L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox15", "value8").value
    r.put(0xBB81B573349F715CL, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox15", "value9").value
    r.put(0xBEAEC63B608C3403L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox15", "value10").value
    r.put(0xD5A4AD07BBD6872EL, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox15", "value11").value
    r.put(0x8944FD0AEF42FAE4L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox15", "value12").value
    r.put(0x237202CE7CC5C8AEL, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox15", "value13").value
    r.put(0xFD818B564028DB6BL, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox15", "value14").value
    r.put(0x29DD36635DBA2DD8L, X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value15) // methodKey(F, "org.sireum.MBox15", "value15").value
    r.put(0xEAF6A1895B808743L, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox16", "value1").value
    r.put(0x1003A5DED27A5744L, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox16", "value2").value
    r.put(0x51112535DB7BCC0AL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox16", "value3").value
    r.put(0xC79AAB780821B6EBL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox16", "value4").value
    r.put(0x51BF64C2BC263A57L, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox16", "value5").value
    r.put(0xC35845C0A9B6049BL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox16", "value6").value
    r.put(0x88E17CB7CF7563FEL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox16", "value7").value
    r.put(0x6CC1274DF937EE16L, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox16", "value8").value
    r.put(0x7F80C4E4A9BB4CDDL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox16", "value9").value
    r.put(0xBD7A2D7115B0C9A1L, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox16", "value10").value
    r.put(0xFB4DE0D7ABCE5CFFL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox16", "value11").value
    r.put(0xDC0671C92C829B8EL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox16", "value12").value
    r.put(0xDDF17388A522EDDAL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox16", "value13").value
    r.put(0x005DCED853FDFA0EL, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox16", "value14").value
    r.put(0x804AD0C5ACA3BB49L, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value15) // methodKey(F, "org.sireum.MBox16", "value15").value
    r.put(0x6D0CF01595CC6919L, X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value16) // methodKey(F, "org.sireum.MBox16", "value16").value
    r.put(0x352B9535E1C0011BL, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox17", "value1").value
    r.put(0xC5261E7538ED9747L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox17", "value2").value
    r.put(0xECD9B612CAC5FCF4L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox17", "value3").value
    r.put(0xB02589DAE41B0860L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox17", "value4").value
    r.put(0x292DACE04D3B4B5EL, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox17", "value5").value
    r.put(0x69227D818CD47B31L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox17", "value6").value
    r.put(0xF47C82F926F0DF18L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox17", "value7").value
    r.put(0x1A32010DB68A6240L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox17", "value8").value
    r.put(0xF4477EA7B2B130B1L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox17", "value9").value
    r.put(0x1528D2A96A8968B7L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox17", "value10").value
    r.put(0xCEEEFFEC8EFDE719L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox17", "value11").value
    r.put(0x5395F58B7CB65848L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox17", "value12").value
    r.put(0x7F70FFF8990CE826L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox17", "value13").value
    r.put(0x61595EA9376B5E5AL, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox17", "value14").value
    r.put(0xE8023461F069B9FFL, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value15) // methodKey(F, "org.sireum.MBox17", "value15").value
    r.put(0x459A1B2C35BF7F12L, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value16) // methodKey(F, "org.sireum.MBox17", "value16").value
    r.put(0xD89E7DDC4ECB321CL, X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value17) // methodKey(F, "org.sireum.MBox17", "value17").value
    r.put(0x0BD9BE8222814630L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox18", "value1").value
    r.put(0xDCDEAE4E1CE3801DL, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox18", "value2").value
    r.put(0xA995C37CEF198D3EL, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox18", "value3").value
    r.put(0xD972595BCC526E76L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox18", "value4").value
    r.put(0xC17411CE0EDE23EFL, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox18", "value5").value
    r.put(0x254FC4A6CF4C9A56L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox18", "value6").value
    r.put(0x45053F500E4667B5L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox18", "value7").value
    r.put(0x03AB5A6F3779BE52L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox18", "value8").value
    r.put(0xA275C1E8D9879CECL, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox18", "value9").value
    r.put(0xED3D28B8AFBDF28AL, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox18", "value10").value
    r.put(0x0D0709931741A07FL, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox18", "value11").value
    r.put(0x1C40D3B0EEF0EAA1L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox18", "value12").value
    r.put(0x7AE8B94CE571DED4L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox18", "value13").value
    r.put(0xABC63019F8E29368L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox18", "value14").value
    r.put(0x8F3EB626C5589445L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value15) // methodKey(F, "org.sireum.MBox18", "value15").value
    r.put(0x26AB07E856C8CF27L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value16) // methodKey(F, "org.sireum.MBox18", "value16").value
    r.put(0x5928295762B5F029L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value17) // methodKey(F, "org.sireum.MBox18", "value17").value
    r.put(0x8F7A43D394F13EF8L, X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value18) // methodKey(F, "org.sireum.MBox18", "value18").value
    r.put(0x133D9BA419DE8B2BL, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox19", "value1").value
    r.put(0xD50AE0ED4FE571D4L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox19", "value2").value
    r.put(0x8205DCA39469121AL, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox19", "value3").value
    r.put(0xEA0044FB8E464BDFL, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox19", "value4").value
    r.put(0x574BF3DC81AB5DFAL, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox19", "value5").value
    r.put(0x0BCE7DEE58486D4CL, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox19", "value6").value
    r.put(0xDF55038063D012B6L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox19", "value7").value
    r.put(0xE11633D8C78F7B50L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox19", "value8").value
    r.put(0x59BF208BBDA4FF19L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox19", "value9").value
    r.put(0x8C93173A8E5F40F2L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox19", "value10").value
    r.put(0x52C51C7C056CF339L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox19", "value11").value
    r.put(0xAEBA49D202301133L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox19", "value12").value
    r.put(0x3F64CE8BAFE63F7BL, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox19", "value13").value
    r.put(0xA663C41A31E8A48AL, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox19", "value14").value
    r.put(0x1821FEAAF948502AL, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value15) // methodKey(F, "org.sireum.MBox19", "value15").value
    r.put(0x1B1B9D3DCC247368L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value16) // methodKey(F, "org.sireum.MBox19", "value16").value
    r.put(0xD3A2FDC314AEE403L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value17) // methodKey(F, "org.sireum.MBox19", "value17").value
    r.put(0x31DD20F447B5F347L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value18) // methodKey(F, "org.sireum.MBox19", "value18").value
    r.put(0xD19438E4865F5818L, X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value19) // methodKey(F, "org.sireum.MBox19", "value19").value
    r.put(0x8A435AF0DE60E79CL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox20", "value1").value
    r.put(0x75415C939F1B54AAL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox20", "value2").value
    r.put(0x016A7BF9DE70001CL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox20", "value3").value
    r.put(0x36BFC2A15A7B2833L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox20", "value4").value
    r.put(0x847CBB10FDC3A8ECL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox20", "value5").value
    r.put(0x6CD1808EC3BE82D7L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox20", "value6").value
    r.put(0xF079A934D85EC9B5L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox20", "value7").value
    r.put(0xE0265B532BCCE106L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox20", "value8").value
    r.put(0x2AFB7BC6BA589A37L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox20", "value9").value
    r.put(0xB4917DBD576EE422L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox20", "value10").value
    r.put(0x71CBE331924F8053L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox20", "value11").value
    r.put(0x5827500207C186F6L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox20", "value12").value
    r.put(0xD2892DE0F3B88C43L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox20", "value13").value
    r.put(0xFB14A03A5EC61CCAL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox20", "value14").value
    r.put(0x055CFAC3DA4BF5F2L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value15) // methodKey(F, "org.sireum.MBox20", "value15").value
    r.put(0xF37C01384ECC924DL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value16) // methodKey(F, "org.sireum.MBox20", "value16").value
    r.put(0x1A4D439280F410BAL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value17) // methodKey(F, "org.sireum.MBox20", "value17").value
    r.put(0xE450D51172518877L, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value18) // methodKey(F, "org.sireum.MBox20", "value18").value
    r.put(0x6B13BECDAD4C21CBL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value19) // methodKey(F, "org.sireum.MBox20", "value19").value
    r.put(0x99F093A82131E7BCL, X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value20) // methodKey(F, "org.sireum.MBox20", "value20").value
    r.put(0x97D7B5C47C11BD3CL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox21", "value1").value
    r.put(0xE0554DB1C9FF7E60L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox21", "value2").value
    r.put(0x45A1081C2B7A10A9L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox21", "value3").value
    r.put(0xE8497351AFDBCB25L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox21", "value4").value
    r.put(0x66F6DE82854F1F8FL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox21", "value5").value
    r.put(0xD8B0927F341240E4L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox21", "value6").value
    r.put(0x93B36D427374ACBDL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox21", "value7").value
    r.put(0x5362CFB761295CF3L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox21", "value8").value
    r.put(0xD937ACD00CAEB935L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox21", "value9").value
    r.put(0x05C39B6592A7CDF5L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox21", "value10").value
    r.put(0x43021A5A35EA7671L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox21", "value11").value
    r.put(0x4ADAB3DB8F29D51FL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox21", "value12").value
    r.put(0x3E78A47BD161A51EL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox21", "value13").value
    r.put(0xEAAAA55AF42A34EDL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox21", "value14").value
    r.put(0xFB240EB4A6A3CAFFL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value15) // methodKey(F, "org.sireum.MBox21", "value15").value
    r.put(0x454CBA1831561D6CL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value16) // methodKey(F, "org.sireum.MBox21", "value16").value
    r.put(0xA3CA241D786EF651L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value17) // methodKey(F, "org.sireum.MBox21", "value17").value
    r.put(0xB9F47ED2A7923CE6L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value18) // methodKey(F, "org.sireum.MBox21", "value18").value
    r.put(0xEEA50CF7A1F324F6L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value19) // methodKey(F, "org.sireum.MBox21", "value19").value
    r.put(0x58943BF2DBDEC6C9L, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value20) // methodKey(F, "org.sireum.MBox21", "value20").value
    r.put(0x47CBDA6B957CB7FBL, X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value21) // methodKey(F, "org.sireum.MBox21", "value21").value
    r.put(0x6D87693A7A783097L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value1) // methodKey(F, "org.sireum.MBox22", "value1").value
    r.put(0x771386CB1E5D3EC8L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value2) // methodKey(F, "org.sireum.MBox22", "value2").value
    r.put(0xAF03B4B46E0DDA3CL, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value3) // methodKey(F, "org.sireum.MBox22", "value3").value
    r.put(0xFEF897E3E94FCDDFL, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value4) // methodKey(F, "org.sireum.MBox22", "value4").value
    r.put(0xF29198C61ABF40A4L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value5) // methodKey(F, "org.sireum.MBox22", "value5").value
    r.put(0xBC5D61385D78D976L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value6) // methodKey(F, "org.sireum.MBox22", "value6").value
    r.put(0xFAD3F487B2CB8F00L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value7) // methodKey(F, "org.sireum.MBox22", "value7").value
    r.put(0x772B591779CA5C02L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value8) // methodKey(F, "org.sireum.MBox22", "value8").value
    r.put(0xCE7EB15899A1A327L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value9) // methodKey(F, "org.sireum.MBox22", "value9").value
    r.put(0xA1575A2E514A421CL, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value10) // methodKey(F, "org.sireum.MBox22", "value10").value
    r.put(0xA54B6C7008B8ABB3L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value11) // methodKey(F, "org.sireum.MBox22", "value11").value
    r.put(0x60896232D60F6411L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value12) // methodKey(F, "org.sireum.MBox22", "value12").value
    r.put(0x38F24703842A4F2CL, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value13) // methodKey(F, "org.sireum.MBox22", "value13").value
    r.put(0x3AB9898A4D75756DL, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value14) // methodKey(F, "org.sireum.MBox22", "value14").value
    r.put(0xEF74A54571B36EA0L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value15) // methodKey(F, "org.sireum.MBox22", "value15").value
    r.put(0x2D11CE3BAC81AA87L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value16) // methodKey(F, "org.sireum.MBox22", "value16").value
    r.put(0x1A298AC0C5B2F470L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value17) // methodKey(F, "org.sireum.MBox22", "value17").value
    r.put(0x9D231E65804F6DADL, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value18) // methodKey(F, "org.sireum.MBox22", "value18").value
    r.put(0xFE6ED168F6CAF385L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value19) // methodKey(F, "org.sireum.MBox22", "value19").value
    r.put(0xCCEC9A46AB6C033EL, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value20) // methodKey(F, "org.sireum.MBox22", "value20").value
    r.put(0xF90C359594A6BE32L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value21) // methodKey(F, "org.sireum.MBox22", "value21").value
    r.put(0xC49F15F11E37C961L, X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](_).value22) // methodKey(F, "org.sireum.MBox22", "value22").value
    r.put(0x03B760E292FE47EEL, X[org.sireum.MEither[_, _]](_).isLeft) // methodKey(F, "org.sireum.MEither", "isLeft").value
    r.put(0x051B8EFE4C845FDCL, X[org.sireum.MEither[_, _]](_).isRight) // methodKey(F, "org.sireum.MEither", "isRight").value
    r.put(0x5BEE522E09D24258L, X[org.sireum.MEither[_, _]](_).leftOpt) // methodKey(F, "org.sireum.MEither", "leftOpt").value
    r.put(0xACA606421F38C9FEL, X[org.sireum.MEither[_, _]](_).left) // methodKey(F, "org.sireum.MEither", "left").value
    r.put(0x218131EF957842EFL, X[org.sireum.MEither[_, _]](_).rightOpt) // methodKey(F, "org.sireum.MEither", "rightOpt").value
    r.put(0x0111FBBABFA1BD1EL, X[org.sireum.MEither[_, _]](_).right) // methodKey(F, "org.sireum.MEither", "right").value
    r.put(0x326FD747ADF7F7A8L, X[org.sireum.MEither.Left[_, _]](_).value) // methodKey(F, "org.sireum.MEither.Left", "value").value
    r.put(0xC064B24A38CBF063L, X[org.sireum.MEither.Left[_, _]](_).isLeft) // methodKey(F, "org.sireum.MEither.Left", "isLeft").value
    r.put(0xA76FD2A5126A0BCBL, X[org.sireum.MEither.Left[_, _]](_).isRight) // methodKey(F, "org.sireum.MEither.Left", "isRight").value
    r.put(0x75C9B9814FE45822L, X[org.sireum.MEither.Left[_, _]](_).leftOpt) // methodKey(F, "org.sireum.MEither.Left", "leftOpt").value
    r.put(0x709359A9B444700AL, X[org.sireum.MEither.Left[_, _]](_).left) // methodKey(F, "org.sireum.MEither.Left", "left").value
    r.put(0x44AD81477C5B1E77L, X[org.sireum.MEither.Left[_, _]](_).rightOpt) // methodKey(F, "org.sireum.MEither.Left", "rightOpt").value
    r.put(0xB117C20DBCBBB7E6L, X[org.sireum.MEither.Left[_, _]](_).right) // methodKey(F, "org.sireum.MEither.Left", "right").value
    r.put(0x9C2BD49A91DB4D1AL, X[org.sireum.MEither.Right[_, _]](_).value) // methodKey(F, "org.sireum.MEither.Right", "value").value
    r.put(0xD34F89DD72E214ADL, X[org.sireum.MEither.Right[_, _]](_).isLeft) // methodKey(F, "org.sireum.MEither.Right", "isLeft").value
    r.put(0x7CCB2C3D46EF4C07L, X[org.sireum.MEither.Right[_, _]](_).isRight) // methodKey(F, "org.sireum.MEither.Right", "isRight").value
    r.put(0x4A9410B7626451BBL, X[org.sireum.MEither.Right[_, _]](_).leftOpt) // methodKey(F, "org.sireum.MEither.Right", "leftOpt").value
    r.put(0xA766A5C229E15FD2L, X[org.sireum.MEither.Right[_, _]](_).left) // methodKey(F, "org.sireum.MEither.Right", "left").value
    r.put(0xC4D5AD7892FDEF1AL, X[org.sireum.MEither.Right[_, _]](_).rightOpt) // methodKey(F, "org.sireum.MEither.Right", "rightOpt").value
    r.put(0x0B8F6A50C930592DL, X[org.sireum.MEither.Right[_, _]](_).right) // methodKey(F, "org.sireum.MEither.Right", "right").value
    r.put(0xB36D4E44CACFA1F9L, X[org.sireum.MJen[_]](_).count()) // methodKey(F, "org.sireum.MJen", "count").value
    r.put(0x0D2BA92FE00E2FFDL, X[org.sireum.MJen[_]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen", "zipWithIndex").value
    r.put(0xAF3E452F9041F388L, X[org.sireum.MJen[_]](_).head) // methodKey(F, "org.sireum.MJen", "head").value
    r.put(0x2F0BC7B0EE6F0EFEL, X[org.sireum.MJen[_]](_).headOption) // methodKey(F, "org.sireum.MJen", "headOption").value
    r.put(0x10D40047468F756BL, X[org.sireum.MJen[_]](_).toMSZ) // methodKey(F, "org.sireum.MJen", "toMSZ").value
    r.put(0xD62CE0BDE97C47D7L, X[org.sireum.MJen.Internal.ISImpl[_, _]](_).s) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "s").value
    r.put(0x9949B38324E4AFE3L, X[org.sireum.MJen.Internal.ISImpl[_, _]](_).string) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "string").value
    r.put(0xCA851D5FCB7EAEFFL, X[org.sireum.MJen.Internal.ISImpl[_, _]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "count").value
    r.put(0xCD43E1E82900418EL, X[org.sireum.MJen.Internal.ISImpl[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "zipWithIndex").value
    r.put(0x08102CB1521F05F2L, X[org.sireum.MJen.Internal.ISImpl[_, _]](_).head) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "head").value
    r.put(0x9FBE9E0E53BC3F53L, X[org.sireum.MJen.Internal.ISImpl[_, _]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "headOption").value
    r.put(0x3A596E0A00387803L, X[org.sireum.MJen.Internal.ISImpl[_, _]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "toMSZ").value
    r.put(0xA06146A1FDB1B157L, X[org.sireum.MJen.Internal.MSImpl[_, _]](_).s) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "s").value
    r.put(0x0F3D04487A85EFAEL, X[org.sireum.MJen.Internal.MSImpl[_, _]](_).string) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "string").value
    r.put(0x4BB76F11A1E7A9CDL, X[org.sireum.MJen.Internal.MSImpl[_, _]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "count").value
    r.put(0x1C0E9D824426342BL, X[org.sireum.MJen.Internal.MSImpl[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "zipWithIndex").value
    r.put(0xB8C40658CE3A99E1L, X[org.sireum.MJen.Internal.MSImpl[_, _]](_).head) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "head").value
    r.put(0xF7A7945786347706L, X[org.sireum.MJen.Internal.MSImpl[_, _]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "headOption").value
    r.put(0xE408B8C67E1CCFDCL, X[org.sireum.MJen.Internal.MSImpl[_, _]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "toMSZ").value
    r.put(0xC8FD3A523A2E815AL, X[org.sireum.MJen.Internal.MapImpl[_, _]](_).m) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "m").value
    r.put(0xDE371502346F1447L, X[org.sireum.MJen.Internal.MapImpl[_, _]](_).string) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "string").value
    r.put(0xAAF3610356E989C1L, X[org.sireum.MJen.Internal.MapImpl[_, _]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "count").value
    r.put(0x5F61C05B4552AC1AL, X[org.sireum.MJen.Internal.MapImpl[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "zipWithIndex").value
    r.put(0x42EB2D65D1A90604L, X[org.sireum.MJen.Internal.MapImpl[_, _]](_).head) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "head").value
    r.put(0x5BF84A4474B5A305L, X[org.sireum.MJen.Internal.MapImpl[_, _]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "headOption").value
    r.put(0x48FD05FCA77C43B5L, X[org.sireum.MJen.Internal.MapImpl[_, _]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "toMSZ").value
    r.put(0x73EAA4A7B4842E95L, X[org.sireum.MJen.Internal.HashMapImpl[_, _]](_).m) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "m").value
    r.put(0xC247219EDE3F289AL, X[org.sireum.MJen.Internal.HashMapImpl[_, _]](_).string) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "string").value
    r.put(0xF056F3BB58C843D7L, X[org.sireum.MJen.Internal.HashMapImpl[_, _]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "count").value
    r.put(0x4582588B5159457CL, X[org.sireum.MJen.Internal.HashMapImpl[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "zipWithIndex").value
    r.put(0x9F7B8BC67AEAF118L, X[org.sireum.MJen.Internal.HashMapImpl[_, _]](_).head) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "head").value
    r.put(0x3EB38422E79791D0L, X[org.sireum.MJen.Internal.HashMapImpl[_, _]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "headOption").value
    r.put(0x5577D6B6FD6543CCL, X[org.sireum.MJen.Internal.HashMapImpl[_, _]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "toMSZ").value
    r.put(0x5863E78C7A6AD471L, X[org.sireum.MJen.Internal.Filtered[_]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "gen").value
    r.put(0x617DCA39BBB91327L, X[org.sireum.MJen.Internal.Filtered[_]](_).p) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "p").value
    r.put(0x928BCB1FB6792FB1L, X[org.sireum.MJen.Internal.Filtered[_]](_).string) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "string").value
    r.put(0x5D29B85542EF6CE4L, X[org.sireum.MJen.Internal.Filtered[_]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "count").value
    r.put(0xA523999F1508E644L, X[org.sireum.MJen.Internal.Filtered[_]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "zipWithIndex").value
    r.put(0x81DD803C10B0CC99L, X[org.sireum.MJen.Internal.Filtered[_]](_).head) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "head").value
    r.put(0x22F8FC1AF942A3A1L, X[org.sireum.MJen.Internal.Filtered[_]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "headOption").value
    r.put(0x0FAC1758F3ADC1CAL, X[org.sireum.MJen.Internal.Filtered[_]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "toMSZ").value
    r.put(0x4E33389E3A2C836CL, X[org.sireum.MJen.Internal.Mapped[_, _]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "gen").value
    r.put(0x9BB209666EF7F748L, X[org.sireum.MJen.Internal.Mapped[_, _]](_).f) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "f").value
    r.put(0x3863697995693370L, X[org.sireum.MJen.Internal.Mapped[_, _]](_).string) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "string").value
    r.put(0x814D4615844400F0L, X[org.sireum.MJen.Internal.Mapped[_, _]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "count").value
    r.put(0x648B69AD92993A88L, X[org.sireum.MJen.Internal.Mapped[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "zipWithIndex").value
    r.put(0xF0369F539F4B9A91L, X[org.sireum.MJen.Internal.Mapped[_, _]](_).head) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "head").value
    r.put(0xE27A43938E164A83L, X[org.sireum.MJen.Internal.Mapped[_, _]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "headOption").value
    r.put(0x311F46FB10DE4570L, X[org.sireum.MJen.Internal.Mapped[_, _]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "toMSZ").value
    r.put(0xF001279820F29455L, X[org.sireum.MJen.Internal.FlatMapped[_, _]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "gen").value
    r.put(0xD9B1DF25A1D1154AL, X[org.sireum.MJen.Internal.FlatMapped[_, _]](_).f) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "f").value
    r.put(0x71F2D652D35CCBD0L, X[org.sireum.MJen.Internal.FlatMapped[_, _]](_).string) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "string").value
    r.put(0x6FCCD790DC306DAEL, X[org.sireum.MJen.Internal.FlatMapped[_, _]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "count").value
    r.put(0x025AA085F8548168L, X[org.sireum.MJen.Internal.FlatMapped[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "zipWithIndex").value
    r.put(0x997471A7E32614EAL, X[org.sireum.MJen.Internal.FlatMapped[_, _]](_).head) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "head").value
    r.put(0x6E95BF3107A2702AL, X[org.sireum.MJen.Internal.FlatMapped[_, _]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "headOption").value
    r.put(0xCDFCFD7AEF05A5C5L, X[org.sireum.MJen.Internal.FlatMapped[_, _]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "toMSZ").value
    r.put(0x760357B25A630227L, X[org.sireum.MJen.Internal.Sliced[_]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "gen").value
    r.put(0x6E1F7221FAC48879L, X[org.sireum.MJen.Internal.Sliced[_]](_).start) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "start").value
    r.put(0xD67CC3427062D69BL, X[org.sireum.MJen.Internal.Sliced[_]](_).end) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "end").value
    r.put(0x1692EF225FEED4F8L, X[org.sireum.MJen.Internal.Sliced[_]](_).string) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "string").value
    r.put(0xE89AFEB487E69B36L, X[org.sireum.MJen.Internal.Sliced[_]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "count").value
    r.put(0xE4E09EB6ACF6A8F4L, X[org.sireum.MJen.Internal.Sliced[_]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "zipWithIndex").value
    r.put(0xBE4E9F3981F5BFC7L, X[org.sireum.MJen.Internal.Sliced[_]](_).head) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "head").value
    r.put(0x52BEC2E0D935B925L, X[org.sireum.MJen.Internal.Sliced[_]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "headOption").value
    r.put(0x1108D4A6BC585695L, X[org.sireum.MJen.Internal.Sliced[_]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "toMSZ").value
    r.put(0x4B72975D0CBCA03DL, X[org.sireum.MJen.Internal.TakeWhile[_]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "gen").value
    r.put(0x9FCE0432FDA9100FL, X[org.sireum.MJen.Internal.TakeWhile[_]](_).p) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "p").value
    r.put(0xD2A7DA4EBDAFD949L, X[org.sireum.MJen.Internal.TakeWhile[_]](_).string) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "string").value
    r.put(0xA378A99C439309D9L, X[org.sireum.MJen.Internal.TakeWhile[_]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "count").value
    r.put(0x3D75F8E36E1E54AEL, X[org.sireum.MJen.Internal.TakeWhile[_]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "zipWithIndex").value
    r.put(0x8E8DEE8D1C4A7843L, X[org.sireum.MJen.Internal.TakeWhile[_]](_).head) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "head").value
    r.put(0x9DB3C3FAB61B4997L, X[org.sireum.MJen.Internal.TakeWhile[_]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "headOption").value
    r.put(0xCF893E015A4A836CL, X[org.sireum.MJen.Internal.TakeWhile[_]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "toMSZ").value
    r.put(0x5F2A9D30488A95B9L, X[org.sireum.MJen.Internal.DropWhile[_]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "gen").value
    r.put(0xE05A1012BF918616L, X[org.sireum.MJen.Internal.DropWhile[_]](_).p) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "p").value
    r.put(0x508E2C5FD3A7A98BL, X[org.sireum.MJen.Internal.DropWhile[_]](_).string) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "string").value
    r.put(0x8142196B6CDB2E29L, X[org.sireum.MJen.Internal.DropWhile[_]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "count").value
    r.put(0x77C3C012023813D9L, X[org.sireum.MJen.Internal.DropWhile[_]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "zipWithIndex").value
    r.put(0x6F27B9AAD6ED2DFEL, X[org.sireum.MJen.Internal.DropWhile[_]](_).head) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "head").value
    r.put(0xD4714DCD0E32C0FDL, X[org.sireum.MJen.Internal.DropWhile[_]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "headOption").value
    r.put(0x0455326B6F5F6D22L, X[org.sireum.MJen.Internal.DropWhile[_]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "toMSZ").value
    r.put(0x287D598288236E19L, X[org.sireum.MJen.Internal.ZipWithIndexed[_]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "gen").value
    r.put(0xBCA172A6DAA1C267L, X[org.sireum.MJen.Internal.ZipWithIndexed[_]](_).string) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "string").value
    r.put(0xCDE245B062E110CAL, X[org.sireum.MJen.Internal.ZipWithIndexed[_]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "count").value
    r.put(0xFB350ECA9D12ACE1L, X[org.sireum.MJen.Internal.ZipWithIndexed[_]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "zipWithIndex").value
    r.put(0xCCDD3B6DBDA719B0L, X[org.sireum.MJen.Internal.ZipWithIndexed[_]](_).head) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "head").value
    r.put(0x6889BBFFA32C04F5L, X[org.sireum.MJen.Internal.ZipWithIndexed[_]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "headOption").value
    r.put(0xB2EB1056DEB1A365L, X[org.sireum.MJen.Internal.ZipWithIndexed[_]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "toMSZ").value
    r.put(0xE0DEC887663C593DL, X[org.sireum.MJen.Internal.Zipped[_, _]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "gen").value
    r.put(0x690FB319DB5D9D90L, X[org.sireum.MJen.Internal.Zipped[_, _]](_).gen2) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "gen2").value
    r.put(0x9B900BD0BFAADB1AL, X[org.sireum.MJen.Internal.Zipped[_, _]](_).string) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "string").value
    r.put(0xD0B6628DC963895FL, X[org.sireum.MJen.Internal.Zipped[_, _]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "count").value
    r.put(0x38C8BE9A2CD4966CL, X[org.sireum.MJen.Internal.Zipped[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "zipWithIndex").value
    r.put(0xB3404E5253C45FBFL, X[org.sireum.MJen.Internal.Zipped[_, _]](_).head) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "head").value
    r.put(0x5DB6B77430BEA748L, X[org.sireum.MJen.Internal.Zipped[_, _]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "headOption").value
    r.put(0x3CC60E2366C233B7L, X[org.sireum.MJen.Internal.Zipped[_, _]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "toMSZ").value
    r.put(0x60B25CE8FE1AF566L, X[org.sireum.MJen.Internal.Concat[_]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.Concat", "gen").value
    r.put(0x3230F1D60D95E16EL, X[org.sireum.MJen.Internal.Concat[_]](_).gen2) // methodKey(F, "org.sireum.MJen.Internal.Concat", "gen2").value
    r.put(0x66DD51D7B0339E0AL, X[org.sireum.MJen.Internal.Concat[_]](_).string) // methodKey(F, "org.sireum.MJen.Internal.Concat", "string").value
    r.put(0x62D038981F81AEA0L, X[org.sireum.MJen.Internal.Concat[_]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.Concat", "count").value
    r.put(0xC6FB210BD61057E4L, X[org.sireum.MJen.Internal.Concat[_]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.Concat", "zipWithIndex").value
    r.put(0xAC045219309F28A1L, X[org.sireum.MJen.Internal.Concat[_]](_).head) // methodKey(F, "org.sireum.MJen.Internal.Concat", "head").value
    r.put(0xF0D65FE4CAEE3CDCL, X[org.sireum.MJen.Internal.Concat[_]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.Concat", "headOption").value
    r.put(0xFA3BB7B17DA5B0F1L, X[org.sireum.MJen.Internal.Concat[_]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.Concat", "toMSZ").value
    r.put(0x2E40DDBD5409CEABL, X[org.sireum.MJen.Internal.Product[_, _]](_).gen) // methodKey(F, "org.sireum.MJen.Internal.Product", "gen").value
    r.put(0x37E2FCCF440FC912L, X[org.sireum.MJen.Internal.Product[_, _]](_).gen2) // methodKey(F, "org.sireum.MJen.Internal.Product", "gen2").value
    r.put(0x3F47AB82E45E1309L, X[org.sireum.MJen.Internal.Product[_, _]](_).string) // methodKey(F, "org.sireum.MJen.Internal.Product", "string").value
    r.put(0x9C6B7B3FBEF41567L, X[org.sireum.MJen.Internal.Product[_, _]](_).count()) // methodKey(F, "org.sireum.MJen.Internal.Product", "count").value
    r.put(0x21AE9804935EC353L, X[org.sireum.MJen.Internal.Product[_, _]](_).zipWithIndex) // methodKey(F, "org.sireum.MJen.Internal.Product", "zipWithIndex").value
    r.put(0x605D7713134A0ACDL, X[org.sireum.MJen.Internal.Product[_, _]](_).head) // methodKey(F, "org.sireum.MJen.Internal.Product", "head").value
    r.put(0xF636B60B7710ED93L, X[org.sireum.MJen.Internal.Product[_, _]](_).headOption) // methodKey(F, "org.sireum.MJen.Internal.Product", "headOption").value
    r.put(0x24772ACD4535A57FL, X[org.sireum.MJen.Internal.Product[_, _]](_).toMSZ) // methodKey(F, "org.sireum.MJen.Internal.Product", "toMSZ").value
    r.put(0xD249655CA7A1CC2AL, X[org.sireum.MOption[_]](_).isEmpty) // methodKey(F, "org.sireum.MOption", "isEmpty").value
    r.put(0xB5A276945A5A83AFL, X[org.sireum.MOption[_]](_).nonEmpty) // methodKey(F, "org.sireum.MOption", "nonEmpty").value
    r.put(0x6E95A4393E70BA6AL, X[org.sireum.MOption[_]](_).get) // methodKey(F, "org.sireum.MOption", "get").value
    r.put(0xA2F560165877704CL, X[org.sireum.MOption[_]](_).toMS) // methodKey(F, "org.sireum.MOption", "toMS").value
    r.put(0xFEA70FCCF6CCC0AEL, _ => org.sireum.MNone.apply()) // methodKey(T, "org.sireum.MNone", "apply").value
    r.put(0x2FE7AAE45DF26868L, X[org.sireum.MNone[_]](_).isEmpty) // methodKey(F, "org.sireum.MNone", "isEmpty").value
    r.put(0x2EDAC939D7B5A26AL, X[org.sireum.MNone[_]](_).nonEmpty) // methodKey(F, "org.sireum.MNone", "nonEmpty").value
    r.put(0xD992F31A882A750EL, X[org.sireum.MNone[_]](_).get) // methodKey(F, "org.sireum.MNone", "get").value
    r.put(0x761B1B6ACF9A3E1CL, X[org.sireum.MNone[_]](_).toMS) // methodKey(F, "org.sireum.MNone", "toMS").value
    r.put(0xEC37BDFDA5A8EE0DL, X[org.sireum.MSome[_]](_).value) // methodKey(F, "org.sireum.MSome", "value").value
    r.put(0xF1E362051A5A346DL, X[org.sireum.MSome[_]](_).isEmpty) // methodKey(F, "org.sireum.MSome", "isEmpty").value
    r.put(0x6D922EAF7D6877A2L, X[org.sireum.MSome[_]](_).nonEmpty) // methodKey(F, "org.sireum.MSome", "nonEmpty").value
    r.put(0x196790B18C1EA704L, X[org.sireum.MSome[_]](_).get) // methodKey(F, "org.sireum.MSome", "get").value
    r.put(0x0966E955F9B80C4EL, X[org.sireum.MSome[_]](_).toMS) // methodKey(F, "org.sireum.MSome", "toMS").value
    r.put(0x39008A07B8040E60L, X[org.sireum.Map[_, _]](_).entries) // methodKey(F, "org.sireum.Map", "entries").value
    r.put(0x7858D1EB852B9473L, X[org.sireum.Map[_, _]](_).keys) // methodKey(F, "org.sireum.Map", "keys").value
    r.put(0x6716220D903A2C3EL, X[org.sireum.Map[_, _]](_).values) // methodKey(F, "org.sireum.Map", "values").value
    r.put(0x76E95A60C2DEBC87L, X[org.sireum.Map[_, _]](_).keySet) // methodKey(F, "org.sireum.Map", "keySet").value
    r.put(0x88744F508721D301L, X[org.sireum.Map[_, _]](_).valueSet) // methodKey(F, "org.sireum.Map", "valueSet").value
    r.put(0xD1BD87AD63F80A6AL, X[org.sireum.Map[_, _]](_).isEmpty) // methodKey(F, "org.sireum.Map", "isEmpty").value
    r.put(0x136DED63433F9151L, X[org.sireum.Map[_, _]](_).nonEmpty) // methodKey(F, "org.sireum.Map", "nonEmpty").value
    r.put(0xB8D5248EF0579882L, X[org.sireum.Map[_, _]](_).size) // methodKey(F, "org.sireum.Map", "size").value
    r.put(0x18332C225979836FL, X[org.sireum.Map[_, _]](_).string) // methodKey(F, "org.sireum.Map", "string").value
    r.put(0x8451A12070FDF8B5L, X[org.sireum.Map[_, _]](_).hash) // methodKey(F, "org.sireum.Map", "hash").value
    r.put(0xAE06B58811D6AC23L, X[org.sireum.ObjPrinter](_).freshNum()) // methodKey(F, "org.sireum.ObjPrinter", "freshNum").value
    r.put(0x055A847F797C8A9AL, X[org.sireum.Option[_]](_).isEmpty) // methodKey(F, "org.sireum.Option", "isEmpty").value
    r.put(0xF85F056ABDD42C4AL, X[org.sireum.Option[_]](_).nonEmpty) // methodKey(F, "org.sireum.Option", "nonEmpty").value
    r.put(0x66789EDFA0B390D2L, X[org.sireum.Option[_]](_).get) // methodKey(F, "org.sireum.Option", "get").value
    r.put(0x83CEBCAD28E43C27L, X[org.sireum.Option[_]](_).toIS) // methodKey(F, "org.sireum.Option", "toIS").value
    r.put(0x1725EFD9C70BC18EL, _ => org.sireum.None.apply()) // methodKey(T, "org.sireum.None", "apply").value
    r.put(0x9FAE67433DCA559FL, X[org.sireum.None[_]](_).isEmpty) // methodKey(F, "org.sireum.None", "isEmpty").value
    r.put(0x5176776D31203C0CL, X[org.sireum.None[_]](_).nonEmpty) // methodKey(F, "org.sireum.None", "nonEmpty").value
    r.put(0x740ACAC144861CAFL, X[org.sireum.None[_]](_).get) // methodKey(F, "org.sireum.None", "get").value
    r.put(0xEEA76DF934D260E6L, X[org.sireum.None[_]](_).toIS) // methodKey(F, "org.sireum.None", "toIS").value
    r.put(0x244076A6C28D9174L, X[org.sireum.Some[_]](_).value) // methodKey(F, "org.sireum.Some", "value").value
    r.put(0x7632AD54400C065EL, X[org.sireum.Some[_]](_).isEmpty) // methodKey(F, "org.sireum.Some", "isEmpty").value
    r.put(0x87D6C4FC8BC56AB0L, X[org.sireum.Some[_]](_).nonEmpty) // methodKey(F, "org.sireum.Some", "nonEmpty").value
    r.put(0x66419D546C36C8D2L, X[org.sireum.Some[_]](_).get) // methodKey(F, "org.sireum.Some", "get").value
    r.put(0x0C3BDF0F10F5EF12L, X[org.sireum.Some[_]](_).toIS) // methodKey(F, "org.sireum.Some", "toIS").value
    r.put(0xDF6D109B1995ED1CL, X[org.sireum.OsProto.Proc.Result](_).ok) // methodKey(F, "org.sireum.OsProto.Proc.Result", "ok").value
    r.put(0xCB8EA07C0E209EA3L, X[org.sireum.OsProto.Proc.Result](_).out) // methodKey(F, "org.sireum.OsProto.Proc.Result", "out").value
    r.put(0x09BEE08FBA133EB2L, X[org.sireum.OsProto.Proc.Result](_).err) // methodKey(F, "org.sireum.OsProto.Proc.Result", "err").value
    r.put(0xEB9FF3BFA83F6F4CL, X[org.sireum.OsProto.Proc.Result](_).exitCode) // methodKey(F, "org.sireum.OsProto.Proc.Result", "exitCode").value
    r.put(0x3E8B1C6D27FDEFD7L, X[org.sireum.OsProto.Proc](_).dontInheritEnv) // methodKey(F, "org.sireum.OsProto.Proc", "dontInheritEnv").value
    r.put(0xDBD27F5EE5291B46L, X[org.sireum.OsProto.Proc](_).redirectErr) // methodKey(F, "org.sireum.OsProto.Proc", "redirectErr").value
    r.put(0x4776B6063D67E314L, X[org.sireum.OsProto.Proc](_).bufferErr) // methodKey(F, "org.sireum.OsProto.Proc", "bufferErr").value
    r.put(0x002B7657F42F47CBL, X[org.sireum.OsProto.Proc](_).console) // methodKey(F, "org.sireum.OsProto.Proc", "console").value
    r.put(0x08A22769F88ECACAL, X[org.sireum.OsProto.Proc](_).echoEnv) // methodKey(F, "org.sireum.OsProto.Proc", "echoEnv").value
    r.put(0xDE332EEC9220CD4DL, X[org.sireum.OsProto.Proc](_).echo) // methodKey(F, "org.sireum.OsProto.Proc", "echo").value
    r.put(0x5D1453F86AA879BBL, X[org.sireum.OsProto.Proc](_).standard) // methodKey(F, "org.sireum.OsProto.Proc", "standard").value
    r.put(0x84C864B3FF3BA9B5L, X[org.sireum.OsProto.Proc](_).script) // methodKey(F, "org.sireum.OsProto.Proc", "script").value
    r.put(0x11B3D6416D75FBDFL, X[org.sireum.OsProto.Proc](_).shouldPrintCommands) // methodKey(F, "org.sireum.OsProto.Proc", "shouldPrintCommands").value
    r.put(0xBB51C3989E35E517L, X[org.sireum.OsProto.Proc](_).shouldOutputConsole) // methodKey(F, "org.sireum.OsProto.Proc", "shouldOutputConsole").value
    r.put(0x82B6DC0F736F83B4L, X[org.sireum.OsProto.Proc](_).isErrAsOut) // methodKey(F, "org.sireum.OsProto.Proc", "isErrAsOut").value
    r.put(0x1949000AA4956ACFL, X[org.sireum.OsProto.Proc](_).in) // methodKey(F, "org.sireum.OsProto.Proc", "in").value
    r.put(0x932DD7345D58AD62L, X[org.sireum.OsProto.Proc](_).cmds) // methodKey(F, "org.sireum.OsProto.Proc", "cmds").value
    r.put(0x340ADD96EFF0FAD7L, X[org.sireum.OsProto.Proc](_).run()) // methodKey(F, "org.sireum.OsProto.Proc", "run").value
    r.put(0x4C7720AB71830661L, X[org.sireum.OsProto.Proc](_).runCheck()) // methodKey(F, "org.sireum.OsProto.Proc", "runCheck").value
    r.put(0xEE8B3DCE4ABB66FEL, X[org.sireum.Poset[_]](_).nodes) // methodKey(F, "org.sireum.Poset", "nodes").value
    r.put(0xE03DA67D49832938L, X[org.sireum.Poset[_]](_).nodesInverse) // methodKey(F, "org.sireum.Poset", "nodesInverse").value
    r.put(0x7D737F7E6B0CBA13L, X[org.sireum.Poset[_]](_).parents) // methodKey(F, "org.sireum.Poset", "parents").value
    r.put(0x4570DD4B0EE22E1DL, X[org.sireum.Poset[_]](_).children) // methodKey(F, "org.sireum.Poset", "children").value
    r.put(0x70CD3071C5CA7545L, X[org.sireum.Poset[_]](_).emptySet) // methodKey(F, "org.sireum.Poset", "emptySet").value
    r.put(0x407A41EC93240598L, X[org.sireum.Poset[_]](_).size) // methodKey(F, "org.sireum.Poset", "size").value
    r.put(0x23CA7C6652E0E048L, X[org.sireum.Poset[_]](_).hash) // methodKey(F, "org.sireum.Poset", "hash").value
    r.put(0xFEF609F4D0BD0087L, X[org.sireum.Poset[_]](_).rootNodes) // methodKey(F, "org.sireum.Poset", "rootNodes").value
    r.put(0x5359B8BCA6824660L, X[org.sireum.Poset[_]](_).string) // methodKey(F, "org.sireum.Poset", "string").value
    r.put(0xBB49FFEE27734060L, X[org.sireum.Random.Gen.TestRunner[_]](_).next()) // methodKey(F, "org.sireum.Random.Gen.TestRunner", "next").value
    r.put(0x70C3ADA95BF97AF6L, X[org.sireum.Random.Gen](_).nextB()) // methodKey(F, "org.sireum.Random.Gen", "nextB").value
    r.put(0x3A59C7C91E0B02D7L, X[org.sireum.Random.Gen](_).nextC()) // methodKey(F, "org.sireum.Random.Gen", "nextC").value
    r.put(0x6D4FF9EE81E245DEL, X[org.sireum.Random.Gen](_).nextZ()) // methodKey(F, "org.sireum.Random.Gen", "nextZ").value
    r.put(0x29FDB54015ADCB02L, X[org.sireum.Random.Gen](_).nextF32_01()) // methodKey(F, "org.sireum.Random.Gen", "nextF32_01").value
    r.put(0x6E6F0DCE8E950E3CL, X[org.sireum.Random.Gen](_).nextF64_01()) // methodKey(F, "org.sireum.Random.Gen", "nextF64_01").value
    r.put(0xA3446174C977C743L, X[org.sireum.Random.Gen](_).nextF32()) // methodKey(F, "org.sireum.Random.Gen", "nextF32").value
    r.put(0x0807983A8C70B8B9L, X[org.sireum.Random.Gen](_).nextF64()) // methodKey(F, "org.sireum.Random.Gen", "nextF64").value
    r.put(0x0A9C273C416E6CA3L, X[org.sireum.Random.Gen](_).nextR()) // methodKey(F, "org.sireum.Random.Gen", "nextR").value
    r.put(0x85765CAEBF99C0C4L, X[org.sireum.Random.Gen](_).nextS8()) // methodKey(F, "org.sireum.Random.Gen", "nextS8").value
    r.put(0x01FD49E6B697B682L, X[org.sireum.Random.Gen](_).nextS16()) // methodKey(F, "org.sireum.Random.Gen", "nextS16").value
    r.put(0x7E44BFC8F3468C79L, X[org.sireum.Random.Gen](_).nextS32()) // methodKey(F, "org.sireum.Random.Gen", "nextS32").value
    r.put(0xA22F425C81D79CD3L, X[org.sireum.Random.Gen](_).nextS64()) // methodKey(F, "org.sireum.Random.Gen", "nextS64").value
    r.put(0x5C581E106F8DACA3L, X[org.sireum.Random.Gen](_).nextU8()) // methodKey(F, "org.sireum.Random.Gen", "nextU8").value
    r.put(0x55D54A23041AB6AFL, X[org.sireum.Random.Gen](_).nextU16()) // methodKey(F, "org.sireum.Random.Gen", "nextU16").value
    r.put(0x6A77B14E0E8F6468L, X[org.sireum.Random.Gen](_).nextU32()) // methodKey(F, "org.sireum.Random.Gen", "nextU32").value
    r.put(0x0413A0FB99F18F6FL, X[org.sireum.Random.Gen](_).nextU64()) // methodKey(F, "org.sireum.Random.Gen", "nextU64").value
    r.put(0x2C6620C10FF5985EL, X[org.sireum.Random.Gen](_).nextN8()) // methodKey(F, "org.sireum.Random.Gen", "nextN8").value
    r.put(0x0ED5B6E668FB7A12L, X[org.sireum.Random.Gen](_).nextN16()) // methodKey(F, "org.sireum.Random.Gen", "nextN16").value
    r.put(0x2A9BD70891D5EBE2L, X[org.sireum.Random.Gen](_).nextN32()) // methodKey(F, "org.sireum.Random.Gen", "nextN32").value
    r.put(0x20E3F7CF0BCFB243L, X[org.sireum.Random.Gen](_).nextN64()) // methodKey(F, "org.sireum.Random.Gen", "nextN64").value
    r.put(0x17E38D41277CE680L, X[org.sireum.Random.Gen](_).nextZ8()) // methodKey(F, "org.sireum.Random.Gen", "nextZ8").value
    r.put(0xBC5AF0C19FB9A67AL, X[org.sireum.Random.Gen](_).nextZ16()) // methodKey(F, "org.sireum.Random.Gen", "nextZ16").value
    r.put(0x8BC955DDBF5BAA4CL, X[org.sireum.Random.Gen](_).nextZ32()) // methodKey(F, "org.sireum.Random.Gen", "nextZ32").value
    r.put(0xD34048B746B2A386L, X[org.sireum.Random.Gen](_).nextZ64()) // methodKey(F, "org.sireum.Random.Gen", "nextZ64").value
    r.put(0x0100CF13FC37E316L, X[org.sireum.Random.Gen64](_).genU64()) // methodKey(F, "org.sireum.Random.Gen64", "genU64").value
    r.put(0x2FFEC0E7E431C48DL, X[org.sireum.Random.Gen64](_).nextU32()) // methodKey(F, "org.sireum.Random.Gen64", "nextU32").value
    r.put(0x8D16BDD1818A38A6L, X[org.sireum.Random.Gen64](_).nextU64()) // methodKey(F, "org.sireum.Random.Gen64", "nextU64").value
    r.put(0x52F3D7E1A51D7579L, X[org.sireum.Random.Gen64](_).nextB()) // methodKey(F, "org.sireum.Random.Gen64", "nextB").value
    r.put(0x5AAF9BEF32D0AABEL, X[org.sireum.Random.Gen64](_).nextC()) // methodKey(F, "org.sireum.Random.Gen64", "nextC").value
    r.put(0x77AA30FCB09ABF46L, X[org.sireum.Random.Gen64](_).nextZ()) // methodKey(F, "org.sireum.Random.Gen64", "nextZ").value
    r.put(0xA771A86C83F5649CL, X[org.sireum.Random.Gen64](_).nextF32_01()) // methodKey(F, "org.sireum.Random.Gen64", "nextF32_01").value
    r.put(0xE15984A1C74FF89AL, X[org.sireum.Random.Gen64](_).nextF64_01()) // methodKey(F, "org.sireum.Random.Gen64", "nextF64_01").value
    r.put(0xE7F7D5D6DCB3FD70L, X[org.sireum.Random.Gen64](_).nextF32()) // methodKey(F, "org.sireum.Random.Gen64", "nextF32").value
    r.put(0xB55CC739186C283EL, X[org.sireum.Random.Gen64](_).nextF64()) // methodKey(F, "org.sireum.Random.Gen64", "nextF64").value
    r.put(0x38A963B9DEEF5393L, X[org.sireum.Random.Gen64](_).nextR()) // methodKey(F, "org.sireum.Random.Gen64", "nextR").value
    r.put(0x281DE6EE2FD3AE6FL, X[org.sireum.Random.Gen64](_).nextS8()) // methodKey(F, "org.sireum.Random.Gen64", "nextS8").value
    r.put(0x81D60483022FB187L, X[org.sireum.Random.Gen64](_).nextS16()) // methodKey(F, "org.sireum.Random.Gen64", "nextS16").value
    r.put(0xBF0F90688DF01DF7L, X[org.sireum.Random.Gen64](_).nextS32()) // methodKey(F, "org.sireum.Random.Gen64", "nextS32").value
    r.put(0xD232F92820118A84L, X[org.sireum.Random.Gen64](_).nextS64()) // methodKey(F, "org.sireum.Random.Gen64", "nextS64").value
    r.put(0xF322B9A8CE958BACL, X[org.sireum.Random.Gen64](_).nextU8()) // methodKey(F, "org.sireum.Random.Gen64", "nextU8").value
    r.put(0xFE1824054D455FB8L, X[org.sireum.Random.Gen64](_).nextU16()) // methodKey(F, "org.sireum.Random.Gen64", "nextU16").value
    r.put(0xA712220E4972404FL, X[org.sireum.Random.Gen64](_).nextN8()) // methodKey(F, "org.sireum.Random.Gen64", "nextN8").value
    r.put(0x96DEA8DBCE8ECA24L, X[org.sireum.Random.Gen64](_).nextN16()) // methodKey(F, "org.sireum.Random.Gen64", "nextN16").value
    r.put(0x849D4647D9F2575DL, X[org.sireum.Random.Gen64](_).nextN32()) // methodKey(F, "org.sireum.Random.Gen64", "nextN32").value
    r.put(0xCB2313A80D1F7412L, X[org.sireum.Random.Gen64](_).nextN64()) // methodKey(F, "org.sireum.Random.Gen64", "nextN64").value
    r.put(0x5EE69B204E2C2196L, X[org.sireum.Random.Gen64](_).nextZ8()) // methodKey(F, "org.sireum.Random.Gen64", "nextZ8").value
    r.put(0xE71A54A1C38B99B7L, X[org.sireum.Random.Gen64](_).nextZ16()) // methodKey(F, "org.sireum.Random.Gen64", "nextZ16").value
    r.put(0x8FCB51BECB555E06L, X[org.sireum.Random.Gen64](_).nextZ32()) // methodKey(F, "org.sireum.Random.Gen64", "nextZ32").value
    r.put(0x4832E47D8ED0A6E6L, X[org.sireum.Random.Gen64](_).nextZ64()) // methodKey(F, "org.sireum.Random.Gen64", "nextZ64").value
    r.put(0x319F59BE1F98CEFBL, X[org.sireum.Random.Gen64Impl](_).gen) // methodKey(F, "org.sireum.Random.Gen64Impl", "gen").value
    r.put(0x4F93271AB86D3A9EL, X[org.sireum.Random.Gen64Impl](_).genU64()) // methodKey(F, "org.sireum.Random.Gen64Impl", "genU64").value
    r.put(0xE5B8F03A8497E881L, X[org.sireum.Random.Gen64Impl](_).nextU32()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextU32").value
    r.put(0x44C6563B1FB3BF87L, X[org.sireum.Random.Gen64Impl](_).nextU64()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextU64").value
    r.put(0x183873AB8C493DB7L, X[org.sireum.Random.Gen64Impl](_).nextB()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextB").value
    r.put(0xE78A3838FFCE7823L, X[org.sireum.Random.Gen64Impl](_).nextC()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextC").value
    r.put(0x57635D9E5CCC1730L, X[org.sireum.Random.Gen64Impl](_).nextZ()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ").value
    r.put(0xCE5F6054F5E14D6BL, X[org.sireum.Random.Gen64Impl](_).nextF32_01()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextF32_01").value
    r.put(0x7C90BE0B74EFA888L, X[org.sireum.Random.Gen64Impl](_).nextF64_01()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextF64_01").value
    r.put(0x38826B9968049000L, X[org.sireum.Random.Gen64Impl](_).nextF32()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextF32").value
    r.put(0xB93C1A48D694C867L, X[org.sireum.Random.Gen64Impl](_).nextF64()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextF64").value
    r.put(0x08FE378860485E59L, X[org.sireum.Random.Gen64Impl](_).nextR()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextR").value
    r.put(0xF8D9EB1F3631413FL, X[org.sireum.Random.Gen64Impl](_).nextS8()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextS8").value
    r.put(0x2B4CA64DE7BBCEFDL, X[org.sireum.Random.Gen64Impl](_).nextS16()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextS16").value
    r.put(0x066B49490F253F56L, X[org.sireum.Random.Gen64Impl](_).nextS32()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextS32").value
    r.put(0x4156EAAA4185DBEEL, X[org.sireum.Random.Gen64Impl](_).nextS64()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextS64").value
    r.put(0x543364FDE746B215L, X[org.sireum.Random.Gen64Impl](_).nextU8()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextU8").value
    r.put(0x29F8F6F14EDEBC8CL, X[org.sireum.Random.Gen64Impl](_).nextU16()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextU16").value
    r.put(0xE5C14FC1DB1DBFF8L, X[org.sireum.Random.Gen64Impl](_).nextN8()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextN8").value
    r.put(0xA2D27C28E749B7BBL, X[org.sireum.Random.Gen64Impl](_).nextN16()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextN16").value
    r.put(0xA4391EB6AE593D6AL, X[org.sireum.Random.Gen64Impl](_).nextN32()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextN32").value
    r.put(0x33DB4CA7686B1E02L, X[org.sireum.Random.Gen64Impl](_).nextN64()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextN64").value
    r.put(0xA2C6BB7883FC121FL, X[org.sireum.Random.Gen64Impl](_).nextZ8()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ8").value
    r.put(0x730DB046E5BABE5AL, X[org.sireum.Random.Gen64Impl](_).nextZ16()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ16").value
    r.put(0x48C12E1D83627721L, X[org.sireum.Random.Gen64Impl](_).nextZ32()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ32").value
    r.put(0xB6054FAAB08F9395L, X[org.sireum.Random.Gen64Impl](_).nextZ64()) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ64").value
    r.put(0x666BDBF3CCB5B846L, X[org.sireum.Random.Impl.SplitMix64](_).seed) // methodKey(F, "org.sireum.Random.Impl.SplitMix64", "seed").value
    r.put(0x905BE489B918BF5CL, X[org.sireum.Random.Impl.SplitMix64](_).next()) // methodKey(F, "org.sireum.Random.Impl.SplitMix64", "next").value
    r.put(0xDA240AD9FE9CF266L, X[org.sireum.Random.Impl.Xoshiro256](_).seed0) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed0").value
    r.put(0x54E757587F88CAE5L, X[org.sireum.Random.Impl.Xoshiro256](_).seed1) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed1").value
    r.put(0x058EADA932BAA68AL, X[org.sireum.Random.Impl.Xoshiro256](_).seed2) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed2").value
    r.put(0xA55F3F89694D9FB6L, X[org.sireum.Random.Impl.Xoshiro256](_).seed3) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed3").value
    r.put(0x46E718DA75CEBEA4L, X[org.sireum.Random.Impl.Xoshiro256](_).update()) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "update").value
    r.put(0x532F777800268AAAL, X[org.sireum.Random.Impl.Xoshiro256](_).pp()) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "pp").value
    r.put(0xF94E24B9A0D7AD03L, X[org.sireum.Random.Impl.Xoshiro256](_).ss()) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "ss").value
    r.put(0x29B5EE96D40CFF2FL, X[org.sireum.Random.Impl.Xoshiro256](_).p()) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "p").value
    r.put(0x0EF16FE049591C27L, X[org.sireum.Random.Impl.Xoroshiro128](_).seed0) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed0").value
    r.put(0xB6FF0EECDF928E96L, X[org.sireum.Random.Impl.Xoroshiro128](_).seed1) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed1").value
    r.put(0x1E9552681FB9FA0FL, X[org.sireum.Random.Impl.Xoroshiro128](_).seed2) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed2").value
    r.put(0xA97A2E309C9FECBDL, X[org.sireum.Random.Impl.Xoroshiro128](_).seed3) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed3").value
    r.put(0x37FD9CE69FB1967DL, X[org.sireum.Random.Impl.Xoroshiro128](_).update()) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "update").value
    r.put(0xF5194CBD3F1AFA7FL, X[org.sireum.Random.Impl.Xoroshiro128](_).pp()) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "pp").value
    r.put(0x61E85DBC318ABC8BL, X[org.sireum.Random.Impl.Xoroshiro128](_).ss()) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "ss").value
    r.put(0x8742A1AA945FA553L, X[org.sireum.Random.Impl.Xoroshiro128](_).p()) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "p").value
    r.put(0xBA04DDC1AAB5F952L, X[org.sireum.Set[_]](_).elements) // methodKey(F, "org.sireum.Set", "elements").value
    r.put(0x84C3C498A59AE972L, X[org.sireum.Set[_]](_).hash) // methodKey(F, "org.sireum.Set", "hash").value
    r.put(0x175A84A7796C2B15L, X[org.sireum.Set[_]](_).isEmpty) // methodKey(F, "org.sireum.Set", "isEmpty").value
    r.put(0xD62C82F2B6D86FCEL, X[org.sireum.Set[_]](_).nonEmpty) // methodKey(F, "org.sireum.Set", "nonEmpty").value
    r.put(0xF536D42F73F31576L, X[org.sireum.Set[_]](_).size) // methodKey(F, "org.sireum.Set", "size").value
    r.put(0x3132731ADE4E99CBL, X[org.sireum.Set[_]](_).string) // methodKey(F, "org.sireum.Set", "string").value
    r.put(0x6C76D6BF1777DCB1L, X[org.sireum.Stack[_]](_).elements) // methodKey(F, "org.sireum.Stack", "elements").value
    r.put(0x2365A3560264E9F4L, X[org.sireum.Stack[_]](_).size) // methodKey(F, "org.sireum.Stack", "size").value
    r.put(0x13E54D95483378A4L, X[org.sireum.Stack[_]](_).isEmpty) // methodKey(F, "org.sireum.Stack", "isEmpty").value
    r.put(0x2765184A9F9ED91BL, X[org.sireum.Stack[_]](_).nonEmpty) // methodKey(F, "org.sireum.Stack", "nonEmpty").value
    r.put(0x86F5AC1507460108L, X[org.sireum.Stack[_]](_).peek) // methodKey(F, "org.sireum.Stack", "peek").value
    r.put(0xE4FC7C3BEBC50A99L, X[org.sireum.Stack[_]](_).pop) // methodKey(F, "org.sireum.Stack", "pop").value
    r.put(0x296028B53A88B384L, X[org.sireum.UnionFind[_]](_).elements) // methodKey(F, "org.sireum.UnionFind", "elements").value
    r.put(0xD1F62928F60386F4L, X[org.sireum.UnionFind[_]](_).elementsInverse) // methodKey(F, "org.sireum.UnionFind", "elementsInverse").value
    r.put(0xCCED102E798724F3L, X[org.sireum.UnionFind[_]](_).parentOf) // methodKey(F, "org.sireum.UnionFind", "parentOf").value
    r.put(0x9CCF7505C16DE5CBL, X[org.sireum.UnionFind[_]](_).sizeOf) // methodKey(F, "org.sireum.UnionFind", "sizeOf").value
    r.put(0xF299120462C83E3FL, X[org.sireum.UnionFind[_]](_).size) // methodKey(F, "org.sireum.UnionFind", "size").value
    r.put(0xC34755F56DC6F16AL, X[org.sireum.UnionFind[_]](_).hash) // methodKey(F, "org.sireum.UnionFind", "hash").value
    r.put(0xC3C3DDB94929FEB0L, X[org.sireum.UnionFind[_]](_).string) // methodKey(F, "org.sireum.UnionFind", "string").value
    r.put(0x3F7BC72B371022B5L, X[org.sireum.CoursierFileInfo](_).org) // methodKey(F, "org.sireum.CoursierFileInfo", "org").value
    r.put(0xF48034BB86BD0CC6L, X[org.sireum.CoursierFileInfo](_).module) // methodKey(F, "org.sireum.CoursierFileInfo", "module").value
    r.put(0x4061521D26B92766L, X[org.sireum.CoursierFileInfo](_).version) // methodKey(F, "org.sireum.CoursierFileInfo", "version").value
    r.put(0x8F21A4D5F1E5555FL, X[org.sireum.CoursierFileInfo](_).pathString) // methodKey(F, "org.sireum.CoursierFileInfo", "pathString").value
    r.put(0xABF77A217D4E0D5CL, X[org.sireum.CoursierFileInfo](_).path) // methodKey(F, "org.sireum.CoursierFileInfo", "path").value
    r.put(0xA413F637248EA204L, X[org.sireum.Coursier.Proxy](_).hostOpt) // methodKey(F, "org.sireum.Coursier.Proxy", "hostOpt").value
    r.put(0x3C94143049E7C6E3L, X[org.sireum.Coursier.Proxy](_).nonHostsOpt) // methodKey(F, "org.sireum.Coursier.Proxy", "nonHostsOpt").value
    r.put(0xF0A802E1BE5D29E0L, X[org.sireum.Coursier.Proxy](_).portOpt) // methodKey(F, "org.sireum.Coursier.Proxy", "portOpt").value
    r.put(0x0E615CEB697B5DA9L, X[org.sireum.Coursier.Proxy](_).protocolOpt) // methodKey(F, "org.sireum.Coursier.Proxy", "protocolOpt").value
    r.put(0xB0EEB8831C596641L, X[org.sireum.Coursier.Proxy](_).protocolUserEnvKeyOpt) // methodKey(F, "org.sireum.Coursier.Proxy", "protocolUserEnvKeyOpt").value
    r.put(0xFC8FBEA89C7FB601L, X[org.sireum.Coursier.Proxy](_).protocolPasswordEnvKeyOpt) // methodKey(F, "org.sireum.Coursier.Proxy", "protocolPasswordEnvKeyOpt").value
    r.put(0x6385FC3ED3C1DF7EL, X[org.sireum.GitHub.Repository](_).connection) // methodKey(F, "org.sireum.GitHub.Repository", "connection").value
    r.put(0xC52541F38D449DCAL, X[org.sireum.GitHub.Repository](_).owner) // methodKey(F, "org.sireum.GitHub.Repository", "owner").value
    r.put(0xFAAF9B7E15827429L, X[org.sireum.GitHub.Repository](_).name) // methodKey(F, "org.sireum.GitHub.Repository", "name").value
    r.put(0xCF01113035769901L, X[org.sireum.GitHub.Repository](_).latestRelease) // methodKey(F, "org.sireum.GitHub.Repository", "latestRelease").value
    r.put(0x45F1062445734626L, X[org.sireum.GitHub.Repository](_).releases) // methodKey(F, "org.sireum.GitHub.Repository", "releases").value
    r.put(0x8C8C6951055588F4L, X[org.sireum.GitHub.Release](_).repo) // methodKey(F, "org.sireum.GitHub.Release", "repo").value
    r.put(0x4C924DF002A544A0L, X[org.sireum.GitHub.Release](_).id) // methodKey(F, "org.sireum.GitHub.Release", "id").value
    r.put(0xCAAF4CC1284EBDE7L, X[org.sireum.GitHub.Release](_).name) // methodKey(F, "org.sireum.GitHub.Release", "name").value
    r.put(0xDC9411339225D406L, X[org.sireum.GitHub.Release](_).publishedTime) // methodKey(F, "org.sireum.GitHub.Release", "publishedTime").value
    r.put(0x83D4A07986173B79L, X[org.sireum.GitHub.Release](_).isDraft) // methodKey(F, "org.sireum.GitHub.Release", "isDraft").value
    r.put(0x91991F73C7E620F0L, X[org.sireum.GitHub.Release](_).isPrerelease) // methodKey(F, "org.sireum.GitHub.Release", "isPrerelease").value
    r.put(0x047622B7DA857025L, X[org.sireum.GitHub.Release](_).tagName) // methodKey(F, "org.sireum.GitHub.Release", "tagName").value
    r.put(0x560388A8408E8ADAL, X[org.sireum.GitHub.Release](_).commit) // methodKey(F, "org.sireum.GitHub.Release", "commit").value
    r.put(0x2F28B593ECCAFF91L, X[org.sireum.GitHub.Release](_).tarUrl) // methodKey(F, "org.sireum.GitHub.Release", "tarUrl").value
    r.put(0x9C28FB26DDECDCB4L, X[org.sireum.GitHub.Release](_).zipUrl) // methodKey(F, "org.sireum.GitHub.Release", "zipUrl").value
    r.put(0xCAEF5DB9B679AD88L, X[org.sireum.GitHub.Release](_).assets) // methodKey(F, "org.sireum.GitHub.Release", "assets").value
    r.put(0x8031F0F23347ECACL, X[org.sireum.GitHub.Asset](_).release) // methodKey(F, "org.sireum.GitHub.Asset", "release").value
    r.put(0x637EEB16C018C8BDL, X[org.sireum.GitHub.Asset](_).id) // methodKey(F, "org.sireum.GitHub.Asset", "id").value
    r.put(0x9D70C3296341DE4EL, X[org.sireum.GitHub.Asset](_).name) // methodKey(F, "org.sireum.GitHub.Asset", "name").value
    r.put(0x75006ECF02212C51L, X[org.sireum.GitHub.Asset](_).label) // methodKey(F, "org.sireum.GitHub.Asset", "label").value
    r.put(0x8C97F5A910E6652BL, X[org.sireum.GitHub.Asset](_).state) // methodKey(F, "org.sireum.GitHub.Asset", "state").value
    r.put(0x7C7315FF31E3D523L, X[org.sireum.GitHub.Asset](_).size) // methodKey(F, "org.sireum.GitHub.Asset", "size").value
    r.put(0xC9090B7DE5593913L, X[org.sireum.GitHub.Asset](_).contentType) // methodKey(F, "org.sireum.GitHub.Asset", "contentType").value
    r.put(0x6C93F7AC844F4E01L, X[org.sireum.GitHub.Asset](_).url) // methodKey(F, "org.sireum.GitHub.Asset", "url").value
    r.put(0xF61B1C3493728DF8L, X[org.sireum.GitHub.Asset](_).downloadCount) // methodKey(F, "org.sireum.GitHub.Asset", "downloadCount").value
    r.put(0x7192811918B18FFCL, X[org.sireum.Init.Plugin](_).id) // methodKey(F, "org.sireum.Init.Plugin", "id").value
    r.put(0xB8E00829EE9A8DE1L, X[org.sireum.Init.Plugin](_).isCommunity) // methodKey(F, "org.sireum.Init.Plugin", "isCommunity").value
    r.put(0x29A0906CF65F6132L, X[org.sireum.Init.Plugin](_).isJar) // methodKey(F, "org.sireum.Init.Plugin", "isJar").value
    r.put(0xF0ED9FA74CECFEACL, X[org.sireum.Init.Plugin](_).version) // methodKey(F, "org.sireum.Init.Plugin", "version").value
    r.put(0xEEDA64964F06D49FL, X[org.sireum.Init](_).home) // methodKey(F, "org.sireum.Init", "home").value
    r.put(0xE8D2F2859C20CB1BL, X[org.sireum.Init](_).kind) // methodKey(F, "org.sireum.Init", "kind").value
    r.put(0xF7716769FAACABEDL, X[org.sireum.Init](_).versions) // methodKey(F, "org.sireum.Init", "versions").value
    r.put(0x65CFE7BF52111E3EL, X[org.sireum.Init](_).sireumV) // methodKey(F, "org.sireum.Init", "sireumV").value
    r.put(0x8C55504143C57434L, X[org.sireum.Init](_).cache) // methodKey(F, "org.sireum.Init", "cache").value
    r.put(0x981B2B3232167A66L, X[org.sireum.Init](_).pluginPrefix) // methodKey(F, "org.sireum.Init", "pluginPrefix").value
    r.put(0xC0B0C2DE4A0CB881L, X[org.sireum.Init](_).ideaCacheDir) // methodKey(F, "org.sireum.Init", "ideaCacheDir").value
    r.put(0x032FD793073A3372L, X[org.sireum.Init](_).pluginsCacheDir) // methodKey(F, "org.sireum.Init", "pluginsCacheDir").value
    r.put(0x9D735E53E4BEA32BL, X[org.sireum.Init](_).homeBin) // methodKey(F, "org.sireum.Init", "homeBin").value
    r.put(0xAA88BE7C2E40044DL, X[org.sireum.Init](_).homeLib) // methodKey(F, "org.sireum.Init", "homeLib").value
    r.put(0xCA6FE7482DBCBD9EL, X[org.sireum.Init](_).homeBinPlatform) // methodKey(F, "org.sireum.Init", "homeBinPlatform").value
    r.put(0x6C11A982AA2992B5L, X[org.sireum.Init](_).binfmt) // methodKey(F, "org.sireum.Init", "binfmt").value
    r.put(0xF78FA3367448F578L, X[org.sireum.Init](_).distroPlugins) // methodKey(F, "org.sireum.Init", "distroPlugins").value
    r.put(0xE2A1015CD69EA9F3L, X[org.sireum.Init](_).scalacPluginVersion) // methodKey(F, "org.sireum.Init", "scalacPluginVersion").value
    r.put(0x510160BCC772FB1AL, X[org.sireum.Init](_).coursierVersion) // methodKey(F, "org.sireum.Init", "coursierVersion").value
    r.put(0x15ED0A616E935901L, X[org.sireum.Init](_).jacocoVersion) // methodKey(F, "org.sireum.Init", "jacocoVersion").value
    r.put(0xBE63343C2C18F73EL, X[org.sireum.Init](_).scalacPlugin) // methodKey(F, "org.sireum.Init", "scalacPlugin").value
    r.put(0x0145EBAD83F068DEL, X[org.sireum.Init](_).scalaVersion) // methodKey(F, "org.sireum.Init", "scalaVersion").value
    r.put(0xF45FC5D3A94CC9B1L, X[org.sireum.Init](_).scalaHome) // methodKey(F, "org.sireum.Init", "scalaHome").value
    r.put(0xFF0CE42D296BA155L, X[org.sireum.Init](_).sireumJar) // methodKey(F, "org.sireum.Init", "sireumJar").value
    r.put(0x1213E2FE38E802A4L, X[org.sireum.Init](_).maryTtsJar) // methodKey(F, "org.sireum.Init", "maryTtsJar").value
    r.put(0xECAA1CDF1B2E8932L, X[org.sireum.Init](_).checkstack) // methodKey(F, "org.sireum.Init", "checkstack").value
    r.put(0x3672E35612705476L, X[org.sireum.Init](_).checkstackVersion) // methodKey(F, "org.sireum.Init", "checkstackVersion").value
    r.put(0x36E11EC52DA0B6F5L, X[org.sireum.Init](_).javaVersion) // methodKey(F, "org.sireum.Init", "javaVersion").value
    r.put(0xFC8E5B3CD4A66226L, X[org.sireum.Init](_).installScala()) // methodKey(F, "org.sireum.Init", "installScala").value
    r.put(0x1FFA7C92CE77EAD3L, X[org.sireum.Init](_).installScalacPlugin()) // methodKey(F, "org.sireum.Init", "installScalacPlugin").value
    r.put(0xAE2412F12F627832L, X[org.sireum.Init](_).installCoursier()) // methodKey(F, "org.sireum.Init", "installCoursier").value
    r.put(0x4924789C9337C287L, X[org.sireum.Init](_).installJacoco()) // methodKey(F, "org.sireum.Init", "installJacoco").value
    r.put(0x7AF0439B866D4FB2L, X[org.sireum.Init](_).installZ3()) // methodKey(F, "org.sireum.Init", "installZ3").value
    r.put(0x2F3811F83D8BEEFFL, X[org.sireum.Init](_).installCVC()) // methodKey(F, "org.sireum.Init", "installCVC").value
    r.put(0xAC68FF7561F9281AL, X[org.sireum.Init](_).installMaryTTS()) // methodKey(F, "org.sireum.Init", "installMaryTTS").value
    r.put(0x9A32C9E5F4212A3FL, X[org.sireum.Init](_).installCheckStack()) // methodKey(F, "org.sireum.Init", "installCheckStack").value
    r.put(0x7D2A68A135CD2753L, X[org.sireum.Init](_).installScripts()) // methodKey(F, "org.sireum.Init", "installScripts").value
    r.put(0xA3394C0B89F4E4E3L, X[org.sireum.Init](_).install7zz()) // methodKey(F, "org.sireum.Init", "install7zz").value
    r.put(0xBA28ABD6C1F69D05L, X[org.sireum.Init](_).isIdeaInUserHome) // methodKey(F, "org.sireum.Init", "isIdeaInUserHome").value
    r.put(0x88F00413C0CBF17FL, X[org.sireum.Init](_).buildForms()) // methodKey(F, "org.sireum.Init", "buildForms").value
    r.put(0xEC4A65D5E8BF769FL, X[org.sireum.Init](_).basicDeps()) // methodKey(F, "org.sireum.Init", "basicDeps").value
    r.put(0xD878810D05D5DD20L, X[org.sireum.Init](_).proyekCompileDeps()) // methodKey(F, "org.sireum.Init", "proyekCompileDeps").value
    r.put(0x253BA259D4C72EA3L, X[org.sireum.Init](_).logikaDeps()) // methodKey(F, "org.sireum.Init", "logikaDeps").value
    r.put(0xAFCA44E74EF326E5L, X[org.sireum.Init](_).deps()) // methodKey(F, "org.sireum.Init", "deps").value
    r.put(0xC0213A716A8664FBL, X[org.sireum.Os.Path.Impl](_).value) // methodKey(F, "org.sireum.Os.Path.Impl", "value").value
    r.put(0x51BAFEDF90121833L, X[org.sireum.Os.Path.Impl](_).string) // methodKey(F, "org.sireum.Os.Path.Impl", "string").value
    r.put(0xF195EEACA5668080L, X[org.sireum.Os.Path.Impl](_).procString) // methodKey(F, "org.sireum.Os.Path.Impl", "procString").value
    r.put(0x6D8890A413C1C1F6L, X[org.sireum.Os.Path.Impl](_).canon) // methodKey(F, "org.sireum.Os.Path.Impl", "canon").value
    r.put(0xB852B90E1E2652E3L, X[org.sireum.Os.Path.Impl](_).abs) // methodKey(F, "org.sireum.Os.Path.Impl", "abs").value
    r.put(0x090D4A8D735DAA86L, X[org.sireum.Os.Path.Impl](_).exists) // methodKey(F, "org.sireum.Os.Path.Impl", "exists").value
    r.put(0xA143EDEEACC8E625L, X[org.sireum.Os.Path.Impl](_).ext) // methodKey(F, "org.sireum.Os.Path.Impl", "ext").value
    r.put(0xC8FD3C0F73F0D47AL, X[org.sireum.Os.Path.Impl](_).isAbs) // methodKey(F, "org.sireum.Os.Path.Impl", "isAbs").value
    r.put(0xF853F9FAF786EACAL, X[org.sireum.Os.Path.Impl](_).isDir) // methodKey(F, "org.sireum.Os.Path.Impl", "isDir").value
    r.put(0x709C38275EBBF09DL, X[org.sireum.Os.Path.Impl](_).isFile) // methodKey(F, "org.sireum.Os.Path.Impl", "isFile").value
    r.put(0x2E3D25A0843F4BF0L, X[org.sireum.Os.Path.Impl](_).isSymLink) // methodKey(F, "org.sireum.Os.Path.Impl", "isSymLink").value
    r.put(0x0B5B9A35FCF7F22DL, X[org.sireum.Os.Path.Impl](_).isExecutable) // methodKey(F, "org.sireum.Os.Path.Impl", "isExecutable").value
    r.put(0x4D93EE6A51798EECL, X[org.sireum.Os.Path.Impl](_).isReadable) // methodKey(F, "org.sireum.Os.Path.Impl", "isReadable").value
    r.put(0x0BB28086343CFBAEL, X[org.sireum.Os.Path.Impl](_).isWritable) // methodKey(F, "org.sireum.Os.Path.Impl", "isWritable").value
    r.put(0xF00A3C1F5928799FL, X[org.sireum.Os.Path.Impl](_).kind) // methodKey(F, "org.sireum.Os.Path.Impl", "kind").value
    r.put(0x25C244AEFC4BE2B9L, X[org.sireum.Os.Path.Impl](_).lastModified) // methodKey(F, "org.sireum.Os.Path.Impl", "lastModified").value
    r.put(0x6CF43ED98C9D211EL, X[org.sireum.Os.Path.Impl](_).length) // methodKey(F, "org.sireum.Os.Path.Impl", "length").value
    r.put(0xDEB07C577875983DL, X[org.sireum.Os.Path.Impl](_).list) // methodKey(F, "org.sireum.Os.Path.Impl", "list").value
    r.put(0xBEEB449B918AF22FL, X[org.sireum.Os.Path.Impl](_).md5) // methodKey(F, "org.sireum.Os.Path.Impl", "md5").value
    r.put(0x77B34D3360D4FF89L, X[org.sireum.Os.Path.Impl](_).mkdir()) // methodKey(F, "org.sireum.Os.Path.Impl", "mkdir").value
    r.put(0x4E0C8831254BB259L, X[org.sireum.Os.Path.Impl](_).mkdirAll()) // methodKey(F, "org.sireum.Os.Path.Impl", "mkdirAll").value
    r.put(0x5CB031BDE5A30A33L, X[org.sireum.Os.Path.Impl](_).name) // methodKey(F, "org.sireum.Os.Path.Impl", "name").value
    r.put(0xFDE5D9C87121E492L, X[org.sireum.Os.Path.Impl](_).properties) // methodKey(F, "org.sireum.Os.Path.Impl", "properties").value
    r.put(0xEFA74AF4E12BDBB0L, X[org.sireum.Os.Path.Impl](_).readSymLink) // methodKey(F, "org.sireum.Os.Path.Impl", "readSymLink").value
    r.put(0x23BBC638A789968AL, X[org.sireum.Os.Path.Impl](_).read) // methodKey(F, "org.sireum.Os.Path.Impl", "read").value
    r.put(0xEC4CC34B06675055L, X[org.sireum.Os.Path.Impl](_).readLines) // methodKey(F, "org.sireum.Os.Path.Impl", "readLines").value
    r.put(0x06C397D31DC10A35L, X[org.sireum.Os.Path.Impl](_).readLineStream) // methodKey(F, "org.sireum.Os.Path.Impl", "readLineStream").value
    r.put(0xF4BF0CB61E7F0DADL, X[org.sireum.Os.Path.Impl](_).readLineMStream) // methodKey(F, "org.sireum.Os.Path.Impl", "readLineMStream").value
    r.put(0x75DD71636D8AE1F0L, X[org.sireum.Os.Path.Impl](_).readU8s) // methodKey(F, "org.sireum.Os.Path.Impl", "readU8s").value
    r.put(0x3E06166BA7CF286CL, X[org.sireum.Os.Path.Impl](_).readU8ms) // methodKey(F, "org.sireum.Os.Path.Impl", "readU8ms").value
    r.put(0x12BCE2A81B980FDDL, X[org.sireum.Os.Path.Impl](_).readU8Stream) // methodKey(F, "org.sireum.Os.Path.Impl", "readU8Stream").value
    r.put(0x542A0B77401C64F3L, X[org.sireum.Os.Path.Impl](_).readU8MStream) // methodKey(F, "org.sireum.Os.Path.Impl", "readU8MStream").value
    r.put(0xF6F689B92711237BL, X[org.sireum.Os.Path.Impl](_).readCStream) // methodKey(F, "org.sireum.Os.Path.Impl", "readCStream").value
    r.put(0xE2DE18167274AE23L, X[org.sireum.Os.Path.Impl](_).readIndexableC) // methodKey(F, "org.sireum.Os.Path.Impl", "readIndexableC").value
    r.put(0xAB8EA0753FB3D740L, X[org.sireum.Os.Path.Impl](_).readCMStream) // methodKey(F, "org.sireum.Os.Path.Impl", "readCMStream").value
    r.put(0x5DD0190285063061L, X[org.sireum.Os.Path.Impl](_).remove()) // methodKey(F, "org.sireum.Os.Path.Impl", "remove").value
    r.put(0xF2BABC839A9413FFL, X[org.sireum.Os.Path.Impl](_).removeAll()) // methodKey(F, "org.sireum.Os.Path.Impl", "removeAll").value
    r.put(0x7B95E0EE2D006919L, X[org.sireum.Os.Path.Impl](_).removeOnExit()) // methodKey(F, "org.sireum.Os.Path.Impl", "removeOnExit").value
    r.put(0x7571CF897B6EC471L, X[org.sireum.Os.Path.Impl](_).sha1) // methodKey(F, "org.sireum.Os.Path.Impl", "sha1").value
    r.put(0x4175AE92111F86F0L, X[org.sireum.Os.Path.Impl](_).size) // methodKey(F, "org.sireum.Os.Path.Impl", "size").value
    r.put(0xD2820E8F6B6AAA50L, X[org.sireum.Os.Path.Impl](_).touch()) // methodKey(F, "org.sireum.Os.Path.Impl", "touch").value
    r.put(0x9EBCB808A930F8D7L, X[org.sireum.Os.Path.Impl](_).toUri) // methodKey(F, "org.sireum.Os.Path.Impl", "toUri").value
    r.put(0xD9F373D6960E1661L, X[org.sireum.Os.Path.Impl](_).up) // methodKey(F, "org.sireum.Os.Path.Impl", "up").value
    r.put(0xE5513EC0AA7EA1CEL, X[org.sireum.Os.Path.Jen[_]](_).path) // methodKey(F, "org.sireum.Os.Path.Jen", "path").value
    r.put(0xE79485389ECA27D7L, X[org.sireum.Os.Path.Jen[_]](_).string) // methodKey(F, "org.sireum.Os.Path.Jen", "string").value
    r.put(0x25C397E2779C1D97L, X[org.sireum.Os.Path.Jen[_]](_).count()) // methodKey(F, "org.sireum.Os.Path.Jen", "count").value
    r.put(0xC8AAC0079B66C86BL, X[org.sireum.Os.Path.Jen[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Os.Path.Jen", "zipWithIndex").value
    r.put(0x639CA3616F723FBBL, X[org.sireum.Os.Path.Jen[_]](_).head) // methodKey(F, "org.sireum.Os.Path.Jen", "head").value
    r.put(0xE22F30C19105F200L, X[org.sireum.Os.Path.Jen[_]](_).headOption) // methodKey(F, "org.sireum.Os.Path.Jen", "headOption").value
    r.put(0x720EA45AA9C91028L, X[org.sireum.Os.Path.Jen[_]](_).toISZ) // methodKey(F, "org.sireum.Os.Path.Jen", "toISZ").value
    r.put(0x1B06D59E6FD13ED5L, X[org.sireum.Os.Path.Jen[_]](_).toMSZ) // methodKey(F, "org.sireum.Os.Path.Jen", "toMSZ").value
    r.put(0x478C5208623E08EFL, X[org.sireum.Os.Path.MJen[_]](_).path) // methodKey(F, "org.sireum.Os.Path.MJen", "path").value
    r.put(0x10B6ACF09D76A8BFL, X[org.sireum.Os.Path.MJen[_]](_).string) // methodKey(F, "org.sireum.Os.Path.MJen", "string").value
    r.put(0xFF7433ACEAE3587DL, X[org.sireum.Os.Path.MJen[_]](_).count()) // methodKey(F, "org.sireum.Os.Path.MJen", "count").value
    r.put(0x547C16567F6A286BL, X[org.sireum.Os.Path.MJen[_]](_).zipWithIndex) // methodKey(F, "org.sireum.Os.Path.MJen", "zipWithIndex").value
    r.put(0x88AC050CB3F50896L, X[org.sireum.Os.Path.MJen[_]](_).head) // methodKey(F, "org.sireum.Os.Path.MJen", "head").value
    r.put(0x8BBF5C1FD2B64C0DL, X[org.sireum.Os.Path.MJen[_]](_).headOption) // methodKey(F, "org.sireum.Os.Path.MJen", "headOption").value
    r.put(0x136A9CD428AD5398L, X[org.sireum.Os.Path.MJen[_]](_).toMSZ) // methodKey(F, "org.sireum.Os.Path.MJen", "toMSZ").value
    r.put(0xFFA674CE69506E84L, X[org.sireum.Os.Proc.FunLineFilter](_).f) // methodKey(F, "org.sireum.Os.Proc.FunLineFilter", "f").value
    r.put(0x45E25F50F6921939L, X[org.sireum.Os.Proc.Result](_).ok) // methodKey(F, "org.sireum.Os.Proc.Result", "ok").value
    r.put(0x0FAB8127EB30AA83L, X[org.sireum.Os.Proc.Result](_).out) // methodKey(F, "org.sireum.Os.Proc.Result", "out").value
    r.put(0x5EF8B77D0D43274DL, X[org.sireum.Os.Proc.Result](_).err) // methodKey(F, "org.sireum.Os.Proc.Result", "err").value
    r.put(0x62545190B0655E48L, X[org.sireum.Os.Proc.Result](_).exitCode) // methodKey(F, "org.sireum.Os.Proc.Result", "exitCode").value
    r.put(0xAF1C09A79821DE03L, X[org.sireum.Os.Proc.Result.Normal](_).exitCode) // methodKey(F, "org.sireum.Os.Proc.Result.Normal", "exitCode").value
    r.put(0xDEAEA22E03806D25L, X[org.sireum.Os.Proc.Result.Normal](_).out) // methodKey(F, "org.sireum.Os.Proc.Result.Normal", "out").value
    r.put(0x1932A12302762539L, X[org.sireum.Os.Proc.Result.Normal](_).err) // methodKey(F, "org.sireum.Os.Proc.Result.Normal", "err").value
    r.put(0x22CB1AD01E471320L, X[org.sireum.Os.Proc.Result.Normal](_).ok) // methodKey(F, "org.sireum.Os.Proc.Result.Normal", "ok").value
    r.put(0xB91DEEC96677E85AL, X[org.sireum.Os.Proc.Result.Exception](_).err) // methodKey(F, "org.sireum.Os.Proc.Result.Exception", "err").value
    r.put(0xC9DCEF3F5408B581L, X[org.sireum.Os.Proc.Result.Exception](_).out) // methodKey(F, "org.sireum.Os.Proc.Result.Exception", "out").value
    r.put(0x5866CF9DFBDDB6F8L, X[org.sireum.Os.Proc.Result.Exception](_).exitCode) // methodKey(F, "org.sireum.Os.Proc.Result.Exception", "exitCode").value
    r.put(0x48919E3218C4D2D9L, X[org.sireum.Os.Proc.Result.Exception](_).ok) // methodKey(F, "org.sireum.Os.Proc.Result.Exception", "ok").value
    r.put(0xD7DA598AB4050F8EL, X[org.sireum.Os.Proc.Result.Timeout](_).out) // methodKey(F, "org.sireum.Os.Proc.Result.Timeout", "out").value
    r.put(0xDE44842BA5717574L, X[org.sireum.Os.Proc.Result.Timeout](_).err) // methodKey(F, "org.sireum.Os.Proc.Result.Timeout", "err").value
    r.put(0xC4B515AEA99091AEL, X[org.sireum.Os.Proc.Result.Timeout](_).exitCode) // methodKey(F, "org.sireum.Os.Proc.Result.Timeout", "exitCode").value
    r.put(0x4EB14452A1B66C8EL, X[org.sireum.Os.Proc.Result.Timeout](_).ok) // methodKey(F, "org.sireum.Os.Proc.Result.Timeout", "ok").value
    r.put(0x5E889CBF516A2D98L, X[org.sireum.Os.Proc](_).cmds) // methodKey(F, "org.sireum.Os.Proc", "cmds").value
    r.put(0x8A1D33F621B3BB8EL, X[org.sireum.Os.Proc](_).wd) // methodKey(F, "org.sireum.Os.Proc", "wd").value
    r.put(0xA39AC17106D97021L, X[org.sireum.Os.Proc](_).envMap) // methodKey(F, "org.sireum.Os.Proc", "envMap").value
    r.put(0x53997E91EB108FD9L, X[org.sireum.Os.Proc](_).shouldAddEnv) // methodKey(F, "org.sireum.Os.Proc", "shouldAddEnv").value
    r.put(0xCD39EC2C5D1D4A01L, X[org.sireum.Os.Proc](_).in) // methodKey(F, "org.sireum.Os.Proc", "in").value
    r.put(0xE07071DA908DA7A0L, X[org.sireum.Os.Proc](_).isErrAsOut) // methodKey(F, "org.sireum.Os.Proc", "isErrAsOut").value
    r.put(0x01C88905C062B005L, X[org.sireum.Os.Proc](_).shouldOutputConsole) // methodKey(F, "org.sireum.Os.Proc", "shouldOutputConsole").value
    r.put(0x795B831B65D10149L, X[org.sireum.Os.Proc](_).isErrBuffered) // methodKey(F, "org.sireum.Os.Proc", "isErrBuffered").value
    r.put(0x886850B036B9164FL, X[org.sireum.Os.Proc](_).shouldPrintEnv) // methodKey(F, "org.sireum.Os.Proc", "shouldPrintEnv").value
    r.put(0x7D8FBF4750E9F406L, X[org.sireum.Os.Proc](_).shouldPrintCommands) // methodKey(F, "org.sireum.Os.Proc", "shouldPrintCommands").value
    r.put(0x71C5CEE17875FF22L, X[org.sireum.Os.Proc](_).timeoutInMillis) // methodKey(F, "org.sireum.Os.Proc", "timeoutInMillis").value
    r.put(0x73E340402259E354L, X[org.sireum.Os.Proc](_).shouldUseStandardLib) // methodKey(F, "org.sireum.Os.Proc", "shouldUseStandardLib").value
    r.put(0x9477813FBA8D57FAL, X[org.sireum.Os.Proc](_).isScript) // methodKey(F, "org.sireum.Os.Proc", "isScript").value
    r.put(0x1D6975C045FF88E0L, X[org.sireum.Os.Proc](_).outLineActionOpt) // methodKey(F, "org.sireum.Os.Proc", "outLineActionOpt").value
    r.put(0x742021EBF7550CF1L, X[org.sireum.Os.Proc](_).errLineActionOpt) // methodKey(F, "org.sireum.Os.Proc", "errLineActionOpt").value
    r.put(0xD3740F8CD880ED69L, X[org.sireum.Os.Proc](_).dontInheritEnv) // methodKey(F, "org.sireum.Os.Proc", "dontInheritEnv").value
    r.put(0x51A5D85B37A96621L, X[org.sireum.Os.Proc](_).redirectErr) // methodKey(F, "org.sireum.Os.Proc", "redirectErr").value
    r.put(0x384F9F970FCA7EF2L, X[org.sireum.Os.Proc](_).bufferErr) // methodKey(F, "org.sireum.Os.Proc", "bufferErr").value
    r.put(0xD07BDE34A5967C39L, X[org.sireum.Os.Proc](_).console) // methodKey(F, "org.sireum.Os.Proc", "console").value
    r.put(0x55412441A49F0E5FL, X[org.sireum.Os.Proc](_).echoEnv) // methodKey(F, "org.sireum.Os.Proc", "echoEnv").value
    r.put(0x2B079AEB81F41EBCL, X[org.sireum.Os.Proc](_).echo) // methodKey(F, "org.sireum.Os.Proc", "echo").value
    r.put(0x8A8A45EC93F42133L, X[org.sireum.Os.Proc](_).standard) // methodKey(F, "org.sireum.Os.Proc", "standard").value
    r.put(0xB3E82D00DAFE9CC0L, X[org.sireum.Os.Proc](_).script) // methodKey(F, "org.sireum.Os.Proc", "script").value
    r.put(0x67C8B5D47773B076L, X[org.sireum.Os.Proc](_).run()) // methodKey(F, "org.sireum.Os.Proc", "run").value
    r.put(0x739A2E673EB76952L, X[org.sireum.Os.Proc](_).runCheck()) // methodKey(F, "org.sireum.Os.Proc", "runCheck").value
    r.put(0x7E46A39777DADADFL, X[org.sireum.Os.Path](_).value) // methodKey(F, "org.sireum.Os.Path", "value").value
    r.put(0x4CF98A1EC7F86843L, X[org.sireum.Os.Path](_).procString) // methodKey(F, "org.sireum.Os.Path", "procString").value
    r.put(0xFF1B4FE564BC2AEEL, X[org.sireum.Os.Path](_).canon) // methodKey(F, "org.sireum.Os.Path", "canon").value
    r.put(0x14E66AA5CBF404D3L, X[org.sireum.Os.Path](_).abs) // methodKey(F, "org.sireum.Os.Path", "abs").value
    r.put(0x3CE5A2AB2D664395L, X[org.sireum.Os.Path](_).exists) // methodKey(F, "org.sireum.Os.Path", "exists").value
    r.put(0x0A7CD7EA244F1CB7L, X[org.sireum.Os.Path](_).ext) // methodKey(F, "org.sireum.Os.Path", "ext").value
    r.put(0x758F1D3D76BC336FL, X[org.sireum.Os.Path](_).isAbs) // methodKey(F, "org.sireum.Os.Path", "isAbs").value
    r.put(0x80D491CC52D321FAL, X[org.sireum.Os.Path](_).isDir) // methodKey(F, "org.sireum.Os.Path", "isDir").value
    r.put(0x97B0699DA71242D0L, X[org.sireum.Os.Path](_).isFile) // methodKey(F, "org.sireum.Os.Path", "isFile").value
    r.put(0x926BB3D9E5CEE661L, X[org.sireum.Os.Path](_).isSymLink) // methodKey(F, "org.sireum.Os.Path", "isSymLink").value
    r.put(0x8DBEC4AA8EB247C5L, X[org.sireum.Os.Path](_).isExecutable) // methodKey(F, "org.sireum.Os.Path", "isExecutable").value
    r.put(0xF4A64F34C27FD111L, X[org.sireum.Os.Path](_).isReadable) // methodKey(F, "org.sireum.Os.Path", "isReadable").value
    r.put(0x4BAD999A0952EE1CL, X[org.sireum.Os.Path](_).isWritable) // methodKey(F, "org.sireum.Os.Path", "isWritable").value
    r.put(0x6CAEE9336697A9B8L, X[org.sireum.Os.Path](_).kind) // methodKey(F, "org.sireum.Os.Path", "kind").value
    r.put(0xB7285EB9C581DBD0L, X[org.sireum.Os.Path](_).lastModified) // methodKey(F, "org.sireum.Os.Path", "lastModified").value
    r.put(0x64C24849A8F8B0D9L, X[org.sireum.Os.Path](_).length) // methodKey(F, "org.sireum.Os.Path", "length").value
    r.put(0x9FC27D448C840C63L, X[org.sireum.Os.Path](_).list) // methodKey(F, "org.sireum.Os.Path", "list").value
    r.put(0x5509BDC284BE3516L, X[org.sireum.Os.Path](_).md5) // methodKey(F, "org.sireum.Os.Path", "md5").value
    r.put(0x3CD678AF52B38EC8L, X[org.sireum.Os.Path](_).mkdir()) // methodKey(F, "org.sireum.Os.Path", "mkdir").value
    r.put(0xC089F447448D84C7L, X[org.sireum.Os.Path](_).mkdirAll()) // methodKey(F, "org.sireum.Os.Path", "mkdirAll").value
    r.put(0xE971DF1D625A82DCL, X[org.sireum.Os.Path](_).name) // methodKey(F, "org.sireum.Os.Path", "name").value
    r.put(0x6B1DE84DA87A2E67L, X[org.sireum.Os.Path](_).properties) // methodKey(F, "org.sireum.Os.Path", "properties").value
    r.put(0x4359BF52CAFF8C98L, X[org.sireum.Os.Path](_).readSymLink) // methodKey(F, "org.sireum.Os.Path", "readSymLink").value
    r.put(0x358A90D915DB7F8FL, X[org.sireum.Os.Path](_).read) // methodKey(F, "org.sireum.Os.Path", "read").value
    r.put(0x9BB16DD596F89A47L, X[org.sireum.Os.Path](_).readLines) // methodKey(F, "org.sireum.Os.Path", "readLines").value
    r.put(0x33D478A9F8710C0CL, X[org.sireum.Os.Path](_).readLineStream) // methodKey(F, "org.sireum.Os.Path", "readLineStream").value
    r.put(0xAAD8E8CA2F73D5C1L, X[org.sireum.Os.Path](_).readLineMStream) // methodKey(F, "org.sireum.Os.Path", "readLineMStream").value
    r.put(0x6C6416A79F59A999L, X[org.sireum.Os.Path](_).readU8s) // methodKey(F, "org.sireum.Os.Path", "readU8s").value
    r.put(0xC6FA450DB00D1356L, X[org.sireum.Os.Path](_).readU8ms) // methodKey(F, "org.sireum.Os.Path", "readU8ms").value
    r.put(0x7EFA3916B86F98CCL, X[org.sireum.Os.Path](_).readU8Stream) // methodKey(F, "org.sireum.Os.Path", "readU8Stream").value
    r.put(0xDA32A2250D30585DL, X[org.sireum.Os.Path](_).readU8MStream) // methodKey(F, "org.sireum.Os.Path", "readU8MStream").value
    r.put(0xEBA885AA8D503271L, X[org.sireum.Os.Path](_).readCStream) // methodKey(F, "org.sireum.Os.Path", "readCStream").value
    r.put(0x9B39E592485160F2L, X[org.sireum.Os.Path](_).readIndexableC) // methodKey(F, "org.sireum.Os.Path", "readIndexableC").value
    r.put(0xD250E31E0EA2385DL, X[org.sireum.Os.Path](_).readCMStream) // methodKey(F, "org.sireum.Os.Path", "readCMStream").value
    r.put(0x07C0E9662908D97DL, X[org.sireum.Os.Path](_).remove()) // methodKey(F, "org.sireum.Os.Path", "remove").value
    r.put(0xF33CB77816BF6448L, X[org.sireum.Os.Path](_).removeAll()) // methodKey(F, "org.sireum.Os.Path", "removeAll").value
    r.put(0x9335B41FA7C3F484L, X[org.sireum.Os.Path](_).removeOnExit()) // methodKey(F, "org.sireum.Os.Path", "removeOnExit").value
    r.put(0x04B89F4CC0E5740CL, X[org.sireum.Os.Path](_).sha1) // methodKey(F, "org.sireum.Os.Path", "sha1").value
    r.put(0x19FD88503431BB56L, X[org.sireum.Os.Path](_).size) // methodKey(F, "org.sireum.Os.Path", "size").value
    r.put(0xEED09B061F11B809L, X[org.sireum.Os.Path](_).touch()) // methodKey(F, "org.sireum.Os.Path", "touch").value
    r.put(0xF0A7A3326CE0FA74L, X[org.sireum.Os.Path](_).toUri) // methodKey(F, "org.sireum.Os.Path", "toUri").value
    r.put(0xCF3FA32FE8F80BE1L, X[org.sireum.Os.Path](_).up) // methodKey(F, "org.sireum.Os.Path", "up").value
    r
  }

  private lazy val method1Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any](1692)
    r.put(0x1854D576D83AF7E4L, _ => (o1: Any) => org.sireum.AssocS.Entries.uniqueKeys(X(o1))) // methodKey(T, "org.sireum.AssocS.Entries", "uniqueKeys").value
    r.put(0xE5CBF86C0B8F9696L, _ => (o1: Any) => org.sireum.AssocS.Entries.keys(X(o1))) // methodKey(T, "org.sireum.AssocS.Entries", "keys").value
    r.put(0xCEBF203EDC8BD12DL, _ => (o1: Any) => org.sireum.AssocS.Entries.values(X(o1))) // methodKey(T, "org.sireum.AssocS.Entries", "values").value
    r.put(0xBFB0622683D47E61L, _ => (o1: Any) => org.sireum.Json.Printer.printB(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printB").value
    r.put(0xB9EC0293B10666B9L, _ => (o1: Any) => org.sireum.Json.Printer.printC(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printC").value
    r.put(0x4C7AB6B4078EB741L, _ => (o1: Any) => org.sireum.Json.Printer.printZ(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printZ").value
    r.put(0x10B59084059D4840L, _ => (o1: Any) => org.sireum.Json.Printer.printZ8(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printZ8").value
    r.put(0x3F2E3C3158E55CFFL, _ => (o1: Any) => org.sireum.Json.Printer.printZ16(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printZ16").value
    r.put(0x448ED071464AF5F6L, _ => (o1: Any) => org.sireum.Json.Printer.printZ32(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printZ32").value
    r.put(0xA2CBF2548B2B44C8L, _ => (o1: Any) => org.sireum.Json.Printer.printZ64(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printZ64").value
    r.put(0x94DF65507A3AD1EBL, _ => (o1: Any) => org.sireum.Json.Printer.printN(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printN").value
    r.put(0xFC3D432A82BDBF97L, _ => (o1: Any) => org.sireum.Json.Printer.printN8(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printN8").value
    r.put(0x647E1493DA164915L, _ => (o1: Any) => org.sireum.Json.Printer.printN16(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printN16").value
    r.put(0xDCF1F4CFC356EAFCL, _ => (o1: Any) => org.sireum.Json.Printer.printN32(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printN32").value
    r.put(0x041307925D3844F9L, _ => (o1: Any) => org.sireum.Json.Printer.printN64(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printN64").value
    r.put(0x9CF7000A6A73557FL, _ => (o1: Any) => org.sireum.Json.Printer.printS8(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printS8").value
    r.put(0xAC66E4040A1BC5EAL, _ => (o1: Any) => org.sireum.Json.Printer.printS16(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printS16").value
    r.put(0x442BE190EECE75DEL, _ => (o1: Any) => org.sireum.Json.Printer.printS32(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printS32").value
    r.put(0x68FDD21968816B32L, _ => (o1: Any) => org.sireum.Json.Printer.printS64(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printS64").value
    r.put(0x255CAF83B65E6120L, _ => (o1: Any) => org.sireum.Json.Printer.printU8(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printU8").value
    r.put(0x565573F33EDA62B2L, _ => (o1: Any) => org.sireum.Json.Printer.printU16(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printU16").value
    r.put(0x07795B37C8D54B84L, _ => (o1: Any) => org.sireum.Json.Printer.printU32(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printU32").value
    r.put(0xA66AE5D197FD8B32L, _ => (o1: Any) => org.sireum.Json.Printer.printU64(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printU64").value
    r.put(0xDF1E227224E3508FL, _ => (o1: Any) => org.sireum.Json.Printer.printF32(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printF32").value
    r.put(0x815DD841BE84EFE8L, _ => (o1: Any) => org.sireum.Json.Printer.printF64(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printF64").value
    r.put(0x5CEF0ABD2BEC4A0BL, _ => (o1: Any) => org.sireum.Json.Printer.printR(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printR").value
    r.put(0xBBA74778E693F52EL, _ => (o1: Any) => org.sireum.Json.Printer.printMessage(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printMessage").value
    r.put(0xB1DCD044BBDF51EFL, _ => (o1: Any) => org.sireum.Json.Printer.printPosition(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printPosition").value
    r.put(0xF52461EA2A9F5A23L, _ => (o1: Any) => org.sireum.Json.Printer.printDocInfo(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printDocInfo").value
    r.put(0x438F75AA263C3D59L, _ => (o1: Any) => org.sireum.Json.Printer.printString(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printString").value
    r.put(0x83C62B36BBD002A4L, _ => (o1: Any) => org.sireum.Json.Printer.printConstant(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printConstant").value
    r.put(0x93F71C2076F90CCBL, _ => (o1: Any) => org.sireum.Json.Printer.printNumber(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printNumber").value
    r.put(0xCC9E5F13813DF151L, _ => (o1: Any) => org.sireum.Json.Printer.printObject(X(o1))) // methodKey(T, "org.sireum.Json.Printer", "printObject").value
    r.put(0x8E171BD701343C34L, _ => (o1: Any) => org.sireum.Json.Fun.printPure0(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure0").value
    r.put(0xE1C8431F6D55849FL, _ => (o1: Any) => org.sireum.Json.Fun.print0(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print0").value
    r.put(0x9B18256463B15609L, _ => (o1: Any) => org.sireum.Json.Fun.printPure1(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure1").value
    r.put(0x5769F4FEED254D10L, _ => (o1: Any) => org.sireum.Json.Fun.print1(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print1").value
    r.put(0xD90C88DB65F4A2C1L, _ => (o1: Any) => org.sireum.Json.Fun.printPure2(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure2").value
    r.put(0xE5C3B0C5204E1837L, _ => (o1: Any) => org.sireum.Json.Fun.print2(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print2").value
    r.put(0x843A82BDD236E44CL, _ => (o1: Any) => org.sireum.Json.Fun.printPure3(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure3").value
    r.put(0x34DC0867D6B0300DL, _ => (o1: Any) => org.sireum.Json.Fun.print3(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print3").value
    r.put(0x4CA0517EE8D434F6L, _ => (o1: Any) => org.sireum.Json.Fun.printPure4(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure4").value
    r.put(0x33B9F26BBA04E871L, _ => (o1: Any) => org.sireum.Json.Fun.print4(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print4").value
    r.put(0xDEFB4085CCCDD6F7L, _ => (o1: Any) => org.sireum.Json.Fun.printPure5(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure5").value
    r.put(0x3EEAA7EAD29B0853L, _ => (o1: Any) => org.sireum.Json.Fun.print5(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print5").value
    r.put(0xD3954AE197B8AF56L, _ => (o1: Any) => org.sireum.Json.Fun.printPure6(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure6").value
    r.put(0xA5B15CA86E513775L, _ => (o1: Any) => org.sireum.Json.Fun.print6(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print6").value
    r.put(0xBAF782953E4FECE1L, _ => (o1: Any) => org.sireum.Json.Fun.printPure7(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure7").value
    r.put(0x797FE9CF3B826A15L, _ => (o1: Any) => org.sireum.Json.Fun.print7(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print7").value
    r.put(0x77A204D13338718BL, _ => (o1: Any) => org.sireum.Json.Fun.printPure8(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure8").value
    r.put(0xBD54C944E2CA9507L, _ => (o1: Any) => org.sireum.Json.Fun.print8(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print8").value
    r.put(0x6CC09348FA30D92EL, _ => (o1: Any) => org.sireum.Json.Fun.printPure9(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure9").value
    r.put(0xD5DBC2E14FFB9D61L, _ => (o1: Any) => org.sireum.Json.Fun.print9(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print9").value
    r.put(0x2986C175C3FEE911L, _ => (o1: Any) => org.sireum.Json.Fun.printPure10(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure10").value
    r.put(0x0A9366B3CA591A0AL, _ => (o1: Any) => org.sireum.Json.Fun.print10(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print10").value
    r.put(0xA3F4B7947581436AL, _ => (o1: Any) => org.sireum.Json.Fun.printPure11(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure11").value
    r.put(0xC73968A9588D407AL, _ => (o1: Any) => org.sireum.Json.Fun.print11(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print11").value
    r.put(0xBE0FC00F5982A4DEL, _ => (o1: Any) => org.sireum.Json.Fun.printPure12(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure12").value
    r.put(0xD39ADC3FC90AA661L, _ => (o1: Any) => org.sireum.Json.Fun.print12(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print12").value
    r.put(0x7135B659B44C9899L, _ => (o1: Any) => org.sireum.Json.Fun.printPure13(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure13").value
    r.put(0x6DFAED4491E7BE98L, _ => (o1: Any) => org.sireum.Json.Fun.print13(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print13").value
    r.put(0xF559E691458786D6L, _ => (o1: Any) => org.sireum.Json.Fun.printPure14(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure14").value
    r.put(0xF99E3C2F258FD012L, _ => (o1: Any) => org.sireum.Json.Fun.print14(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print14").value
    r.put(0x6075FCBB397A4C03L, _ => (o1: Any) => org.sireum.Json.Fun.printPure15(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure15").value
    r.put(0x417DCF927E32B477L, _ => (o1: Any) => org.sireum.Json.Fun.print15(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print15").value
    r.put(0x24FFA3096F2D0CEAL, _ => (o1: Any) => org.sireum.Json.Fun.printPure16(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure16").value
    r.put(0x78BD95D36FD7E738L, _ => (o1: Any) => org.sireum.Json.Fun.print16(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print16").value
    r.put(0x225BD8E73A5ECEA7L, _ => (o1: Any) => org.sireum.Json.Fun.printPure17(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure17").value
    r.put(0x847AF12D4D881298L, _ => (o1: Any) => org.sireum.Json.Fun.print17(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print17").value
    r.put(0x1872CCD70FDB9340L, _ => (o1: Any) => org.sireum.Json.Fun.printPure18(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure18").value
    r.put(0x9E459CEB4DD353BFL, _ => (o1: Any) => org.sireum.Json.Fun.print18(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print18").value
    r.put(0x26B7227475AD89D8L, _ => (o1: Any) => org.sireum.Json.Fun.printPure19(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure19").value
    r.put(0x6074B02B4F84EE59L, _ => (o1: Any) => org.sireum.Json.Fun.print19(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print19").value
    r.put(0x4262BFA06DDBBA02L, _ => (o1: Any) => org.sireum.Json.Fun.printPure20(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure20").value
    r.put(0xCC397DE6D03111DEL, _ => (o1: Any) => org.sireum.Json.Fun.print20(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print20").value
    r.put(0xF1D1882F20FAFEADL, _ => (o1: Any) => org.sireum.Json.Fun.printPure21(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure21").value
    r.put(0x2367B6237CAA5F9CL, _ => (o1: Any) => org.sireum.Json.Fun.print21(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print21").value
    r.put(0x84DCFA26542217B1L, _ => (o1: Any) => org.sireum.Json.Fun.printPure22(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "printPure22").value
    r.put(0x23B8D8ABC6A12E11L, _ => (o1: Any) => org.sireum.Json.Fun.print22(X(o1))) // methodKey(T, "org.sireum.Json.Fun", "print22").value
    r.put(0x41C2E9EB2CF6AA25L, _ => (o1: Any) => org.sireum.LibUtil.mineOptions(X(o1))) // methodKey(T, "org.sireum.LibUtil", "mineOptions").value
    r.put(0xE79E067CDCA2CD9DL, _ => (o1: Any) => org.sireum.LibUtil.IS.unique(X(o1))) // methodKey(T, "org.sireum.LibUtil.IS", "unique").value
    r.put(0x3EEC4C26206902F9L, _ => (o1: Any) => org.sireum.Random.setSeed(X(o1))) // methodKey(T, "org.sireum.Random", "setSeed").value
    r.put(0xBEE95E2C47100F36L, _ => (o1: Any) => org.sireum.Random.createSeed64(X(o1))) // methodKey(T, "org.sireum.Random", "createSeed64").value
    r.put(0x8DA178C956824E65L, _ => (o1: Any) => org.sireum.Random.Ext.setSeed(X(o1))) // methodKey(T, "org.sireum.Random.Ext", "setSeed").value
    r.put(0xF07C2BB05ECC202BL, _ => (o1: Any) => org.sireum.Set.Elements.unique(X(o1))) // methodKey(T, "org.sireum.Set.Elements", "unique").value
    r.put(0xF4B2B517FE88542BL, _ => (o1: Any) => org.sireum.justification.natded.prop.negE(X(o1))) // methodKey(T, "org.sireum.justification.natded.prop", "negE").value
    r.put(0xBDD03CC267AD9A16L, _ => (o1: Any) => org.sireum.justification.natded.prop.bottomE(X(o1))) // methodKey(T, "org.sireum.justification.natded.prop", "bottomE").value
    r.put(0x34C4F76CCF64D14DL, _ => (o1: Any) => org.sireum.Asm.eraseNonNative(X(o1))) // methodKey(T, "org.sireum.Asm", "eraseNonNative").value
    r.put(0x8C80F050BF0CBAC2L, _ => (o1: Any) => org.sireum.Asm.rewriteReleaseFence(X(o1))) // methodKey(T, "org.sireum.Asm", "rewriteReleaseFence").value
    r.put(0xEB109662C014D2E4L, _ => (o1: Any) => org.sireum.Asm.rewriteSetSecurityManager(X(o1))) // methodKey(T, "org.sireum.Asm", "rewriteSetSecurityManager").value
    r.put(0x22EAEEB5EB5E0D89L, _ => (o1: Any) => org.sireum.GitHub.Ext.latestRelease(X(o1))) // methodKey(T, "org.sireum.GitHub.Ext", "latestRelease").value
    r.put(0xE3B4C7390C3E1BE0L, _ => (o1: Any) => org.sireum.GitHub.Ext.releases(X(o1))) // methodKey(T, "org.sireum.GitHub.Ext", "releases").value
    r.put(0x1B5C15598D84A189L, _ => (o1: Any) => org.sireum.GitHub.Ext.assets(X(o1))) // methodKey(T, "org.sireum.GitHub.Ext", "assets").value
    r.put(0x29BBF2727F5FF44DL, _ => (o1: Any) => org.sireum.Os.exit(X(o1))) // methodKey(T, "org.sireum.Os", "exit").value
    r.put(0xF1DFEE31D449E56AL, _ => (o1: Any) => org.sireum.Os.env(X(o1))) // methodKey(T, "org.sireum.Os", "env").value
    r.put(0xC187877C8C9D667CL, _ => (o1: Any) => org.sireum.Os.javaExe(X(o1))) // methodKey(T, "org.sireum.Os", "javaExe").value
    r.put(0xD9706FE633E8860FL, _ => (o1: Any) => org.sireum.Os.path(X(o1))) // methodKey(T, "org.sireum.Os", "path").value
    r.put(0x6DBC59F180255399L, _ => (o1: Any) => org.sireum.Os.printParseableMessages(X(o1))) // methodKey(T, "org.sireum.Os", "printParseableMessages").value
    r.put(0x51D1983D914F61E0L, _ => (o1: Any) => org.sireum.Os.proc(X(o1))) // methodKey(T, "org.sireum.Os", "proc").value
    r.put(0x88680E48F4D8795CL, _ => (o1: Any) => org.sireum.Os.procs(X(o1))) // methodKey(T, "org.sireum.Os", "procs").value
    r.put(0x1DB0CCEFF2FC0711L, _ => (o1: Any) => org.sireum.Os.prop(X(o1))) // methodKey(T, "org.sireum.Os", "prop").value
    r.put(0x64FCFF3E03947CDDL, _ => (o1: Any) => org.sireum.Os.readIndexableCFrom(X(o1))) // methodKey(T, "org.sireum.Os", "readIndexableCFrom").value
    r.put(0xC83806499908BC11L, _ => (o1: Any) => org.sireum.Os.scalaHomeOpt(X(o1))) // methodKey(T, "org.sireum.Os", "scalaHomeOpt").value
    r.put(0xFF2FCA6FC2D9791BL, _ => (o1: Any) => org.sireum.Os.scalaScript(X(o1))) // methodKey(T, "org.sireum.Os", "scalaScript").value
    r.put(0xABFDBD8361D7E672L, _ => (o1: Any) => org.sireum.Os.scalacScript(X(o1))) // methodKey(T, "org.sireum.Os", "scalacScript").value
    r.put(0x6940B3C2DA6C507CL, _ => (o1: Any) => org.sireum.Os.tempDirFix(X(o1))) // methodKey(T, "org.sireum.Os", "tempDirFix").value
    r.put(0x9A85E310FF6BB59FL, _ => (o1: Any) => org.sireum.Os.uriToPath(X(o1))) // methodKey(T, "org.sireum.Os", "uriToPath").value
    r.put(0x2BD71ED4E5A0CA33L, _ => (o1: Any) => org.sireum.Os.Ext.abs(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "abs").value
    r.put(0x777FDF41C07CAE5FL, _ => (o1: Any) => org.sireum.Os.Ext.canon(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "canon").value
    r.put(0x2F24462D4C597D6BL, _ => (o1: Any) => org.sireum.Os.Ext.env(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "env").value
    r.put(0x4068EDA353008E8DL, _ => (o1: Any) => org.sireum.Os.Ext.exists(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "exists").value
    r.put(0xFC94285E96D1BFEEL, _ => (o1: Any) => org.sireum.Os.Ext.exit(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "exit").value
    r.put(0x14BD954CB8BF5680L, _ => (o1: Any) => org.sireum.Os.Ext.fromUri(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "fromUri").value
    r.put(0x05CB9440ADDA4A95L, _ => (o1: Any) => org.sireum.Os.Ext.isAbs(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "isAbs").value
    r.put(0xE5B9CF099012F3D4L, _ => (o1: Any) => org.sireum.Os.Ext.isDir(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "isDir").value
    r.put(0x49EAC87F1E767033L, _ => (o1: Any) => org.sireum.Os.Ext.isFile(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "isFile").value
    r.put(0x153DDEF28DB317CDL, _ => (o1: Any) => org.sireum.Os.Ext.isSymLink(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "isSymLink").value
    r.put(0x159E0E4EAE1DE0F2L, _ => (o1: Any) => org.sireum.Os.Ext.isExecutable(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "isExecutable").value
    r.put(0x86AD4399B50BEB33L, _ => (o1: Any) => org.sireum.Os.Ext.isReadable(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "isReadable").value
    r.put(0xE60ACD0E07DE0F89L, _ => (o1: Any) => org.sireum.Os.Ext.isWritable(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "isWritable").value
    r.put(0x44851BF8982C044FL, _ => (o1: Any) => org.sireum.Os.Ext.kind(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "kind").value
    r.put(0xBCEC9882CE26B939L, _ => (o1: Any) => org.sireum.Os.Ext.lastModified(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "lastModified").value
    r.put(0x0A7AD7D69C6278C2L, _ => (o1: Any) => org.sireum.Os.Ext.length(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "length").value
    r.put(0x5B5D0FB408BCF0BEL, _ => (o1: Any) => org.sireum.Os.Ext.list(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "list").value
    r.put(0xCA6540F5B52E1EB7L, _ => (o1: Any) => org.sireum.Os.Ext.md5(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "md5").value
    r.put(0x08A0CE13E438E8D6L, _ => (o1: Any) => org.sireum.Os.Ext.name(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "name").value
    r.put(0x75302B4FD4C9E303L, _ => (o1: Any) => org.sireum.Os.Ext.norm(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "norm").value
    r.put(0x643B1957BC5770BAL, _ => (o1: Any) => org.sireum.Os.Ext.prop(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "prop").value
    r.put(0xBA78121AB1FA86F0L, _ => (o1: Any) => org.sireum.Os.Ext.properties(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "properties").value
    r.put(0xC8C7C3CA2E1557F2L, _ => (o1: Any) => org.sireum.Os.Ext.readSymLink(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readSymLink").value
    r.put(0x4D9E3CB2112EA85CL, _ => (o1: Any) => org.sireum.Os.Ext.read(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "read").value
    r.put(0xFF680F42777C0EE7L, _ => (o1: Any) => org.sireum.Os.Ext.readU8s(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readU8s").value
    r.put(0xD2AF46FD84B3AB08L, _ => (o1: Any) => org.sireum.Os.Ext.readU8ms(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readU8ms").value
    r.put(0x5EEEBBB5D9EC30F4L, _ => (o1: Any) => org.sireum.Os.Ext.readLineStream(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readLineStream").value
    r.put(0xAF0AB0B2BE460167L, _ => (o1: Any) => org.sireum.Os.Ext.readU8Stream(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readU8Stream").value
    r.put(0x33037E6497608428L, _ => (o1: Any) => org.sireum.Os.Ext.readCStream(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readCStream").value
    r.put(0xE2420BEBA98611A0L, _ => (o1: Any) => org.sireum.Os.Ext.readIndexableCPath(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readIndexableCPath").value
    r.put(0xA872DE4E6C809008L, _ => (o1: Any) => org.sireum.Os.Ext.readIndexableCUrl(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readIndexableCUrl").value
    r.put(0xF68FA5DA0B29CD9AL, _ => (o1: Any) => org.sireum.Os.Ext.readLineMStream(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readLineMStream").value
    r.put(0x79128326455D9140L, _ => (o1: Any) => org.sireum.Os.Ext.readCMStream(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readCMStream").value
    r.put(0x5D31B1791B67E1AAL, _ => (o1: Any) => org.sireum.Os.Ext.readU8MStream(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "readU8MStream").value
    r.put(0xE3758699471EA0E1L, _ => (o1: Any) => org.sireum.Os.Ext.remove(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "remove").value
    r.put(0x7C85384C87EED7D9L, _ => (o1: Any) => org.sireum.Os.Ext.removeAll(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "removeAll").value
    r.put(0x8689872DCAC62F38L, _ => (o1: Any) => org.sireum.Os.Ext.removeOnExit(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "removeOnExit").value
    r.put(0x2E98906BCA688C1EL, _ => (o1: Any) => org.sireum.Os.Ext.sha1(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "sha1").value
    r.put(0xE00E5998690EAE7CL, _ => (o1: Any) => org.sireum.Os.Ext.size(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "size").value
    r.put(0x34BA7C4F89FEE10BL, _ => (o1: Any) => org.sireum.Os.Ext.tempDir(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "tempDir").value
    r.put(0xB6ABB9E19C3F01E2L, _ => (o1: Any) => org.sireum.Os.Ext.toUri(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "toUri").value
    r.put(0x175181727A1304B0L, _ => (o1: Any) => org.sireum.Os.Ext.parent(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "parent").value
    r.put(0x5B7D21E947D83182L, _ => (o1: Any) => org.sireum.Os.Ext.proc(X(o1))) // methodKey(T, "org.sireum.Os.Ext", "proc").value
    r.put(0x89C0705BC7C1FC1EL, _ => (o1: Any) => org.sireum.AssocS.apply(X(o1))) // methodKey(T, "org.sireum.AssocS", "apply").value
    r.put(0x5705C5608F220805L, _ => (o1: Any) => org.sireum.AssocS.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.AssocS", "unapply").value
    r.put(0x3DF6BE21C08E7781L, r => (o1: Any) => X[org.sireum.AssocS[_, _]](r).`+`(X(o1))) // methodKey(F, "org.sireum.AssocS", "+").value
    r.put(0x5E46A971AA573A6BL, r => (o1: Any) => X[org.sireum.AssocS[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.AssocS", "++").value
    r.put(0x817009D68D430268L, r => (o1: Any) => X[org.sireum.AssocS[_, _]](r).get(X(o1))) // methodKey(F, "org.sireum.AssocS", "get").value
    r.put(0x33801691372E3502L, r => (o1: Any) => X[org.sireum.AssocS[_, _]](r).entry(X(o1))) // methodKey(F, "org.sireum.AssocS", "entry").value
    r.put(0x23BF2AB505C4A2D7L, r => (o1: Any) => X[org.sireum.AssocS[_, _]](r).indexOf(X(o1))) // methodKey(F, "org.sireum.AssocS", "indexOf").value
    r.put(0xFCE7930216C71B3FL, r => (o1: Any) => X[org.sireum.AssocS[_, _]](r).`--`(X(o1))) // methodKey(F, "org.sireum.AssocS", "--").value
    r.put(0x0A780174366BAE11L, r => (o1: Any) => X[org.sireum.AssocS[_, _]](r).`-`(X(o1))) // methodKey(F, "org.sireum.AssocS", "-").value
    r.put(0x9F000E1C8F7C2A63L, r => (o1: Any) => X[org.sireum.AssocS[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.AssocS", "contains").value
    r.put(0x6E3C1FD5354E803FL, _ => (o1: Any) => org.sireum.Bag.apply(X(o1))) // methodKey(T, "org.sireum.Bag", "apply").value
    r.put(0x1F8E6A9BB348E56AL, _ => (o1: Any) => org.sireum.Bag.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Bag", "unapply").value
    r.put(0x930BDFEE70F7A00FL, r => (o1: Any) => X[org.sireum.Bag[_]](r).count(X(o1))) // methodKey(F, "org.sireum.Bag", "count").value
    r.put(0xF7853EF7AE515EF3L, r => (o1: Any) => X[org.sireum.Bag[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Bag", "contains").value
    r.put(0x1E048E36177E575EL, r => (o1: Any) => X[org.sireum.Bag[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.Bag", "+").value
    r.put(0x737118352F16934BL, r => (o1: Any) => X[org.sireum.Bag[_]](r).`+#`(X(o1))) // methodKey(F, "org.sireum.Bag", "+#").value
    r.put(0x9A4547715116D586L, r => (o1: Any) => X[org.sireum.Bag[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Bag", "++").value
    r.put(0x3A7DFC5AD0489680L, r => (o1: Any) => X[org.sireum.Bag[_]](r).`-`(X(o1))) // methodKey(F, "org.sireum.Bag", "-").value
    r.put(0xA37B81919137E419L, r => (o1: Any) => X[org.sireum.Bag[_]](r).`--`(X(o1))) // methodKey(F, "org.sireum.Bag", "--").value
    r.put(0x2318472F63B9BF92L, r => (o1: Any) => X[org.sireum.Bag[_]](r).`\\`(X(o1))) // methodKey(F, "org.sireum.Bag", "\\").value
    r.put(0xB18A7CE2DBA4166BL, r => (o1: Any) => X[org.sireum.Bag[_]](r).`-#`(X(o1))) // methodKey(F, "org.sireum.Bag", "-#").value
    r.put(0x26DA5DD109F2C6AEL, r => (o1: Any) => X[org.sireum.Bag[_]](r).union(X(o1))) // methodKey(F, "org.sireum.Bag", "union").value
    r.put(0x580AACA68A226AF0L, r => (o1: Any) => X[org.sireum.Bag[_]](r).`\u222A`(X(o1))) // methodKey(F, "org.sireum.Bag", "\u222A").value
    r.put(0x3D9003066414219CL, r => (o1: Any) => X[org.sireum.Bag[_]](r).intersect(X(o1))) // methodKey(F, "org.sireum.Bag", "intersect").value
    r.put(0x7AE9E2879FF2D3E0L, r => (o1: Any) => X[org.sireum.Bag[_]](r).`\u2229`(X(o1))) // methodKey(F, "org.sireum.Bag", "\u2229").value
    r.put(0xBF1FB262FB0491C8L, r => (o1: Any) => X[org.sireum.CircularQueue[_]](r).enqueue(X(o1))) // methodKey(F, "org.sireum.CircularQueue", "enqueue").value
    r.put(0xAE128494C0056022L, _ => (o1: Any) => org.sireum.CircularQueue.NoDrop.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6)) => MSome((o0, o1, o2, o3, o4, o5, o6))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.CircularQueue.NoDrop", "unapply").value
    r.put(0x0637E1CED50B78D2L, r => (o1: Any) => X[org.sireum.CircularQueue.NoDrop[_]](r).`front_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "front_=").value
    r.put(0x72F696238F3CEA25L, r => (o1: Any) => X[org.sireum.CircularQueue.NoDrop[_]](r).`rear_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "rear_=").value
    r.put(0x23B1E5E0EDDDEBA4L, r => (o1: Any) => X[org.sireum.CircularQueue.NoDrop[_]](r).`numOfElements_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "numOfElements_=").value
    r.put(0xE158CA4F8D2867D4L, r => (o1: Any) => X[org.sireum.CircularQueue.NoDrop[_]](r).enqueue(X(o1))) // methodKey(F, "org.sireum.CircularQueue.NoDrop", "enqueue").value
    r.put(0x7A3ED9FFA1E5A875L, _ => (o1: Any) => org.sireum.CircularQueue.DropFront.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6)) => MSome((o0, o1, o2, o3, o4, o5, o6))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.CircularQueue.DropFront", "unapply").value
    r.put(0x01A268220BF9BB1BL, r => (o1: Any) => X[org.sireum.CircularQueue.DropFront[_]](r).`front_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.DropFront", "front_=").value
    r.put(0xB494DD50755C8A73L, r => (o1: Any) => X[org.sireum.CircularQueue.DropFront[_]](r).`rear_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.DropFront", "rear_=").value
    r.put(0xA061C74E1B8659E8L, r => (o1: Any) => X[org.sireum.CircularQueue.DropFront[_]](r).`numOfElements_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.DropFront", "numOfElements_=").value
    r.put(0xD63CEAFA17735F45L, r => (o1: Any) => X[org.sireum.CircularQueue.DropFront[_]](r).enqueue(X(o1))) // methodKey(F, "org.sireum.CircularQueue.DropFront", "enqueue").value
    r.put(0x6F602304F71FA26BL, _ => (o1: Any) => org.sireum.CircularQueue.DropRear.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6)) => MSome((o0, o1, o2, o3, o4, o5, o6))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.CircularQueue.DropRear", "unapply").value
    r.put(0x28D3C7D36C9E7201L, r => (o1: Any) => X[org.sireum.CircularQueue.DropRear[_]](r).`front_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.DropRear", "front_=").value
    r.put(0xEDA0AE85B2946AECL, r => (o1: Any) => X[org.sireum.CircularQueue.DropRear[_]](r).`rear_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.DropRear", "rear_=").value
    r.put(0x4D43AE3FCCBB4139L, r => (o1: Any) => X[org.sireum.CircularQueue.DropRear[_]](r).`numOfElements_=`(X(o1))) // methodKey(F, "org.sireum.CircularQueue.DropRear", "numOfElements_=").value
    r.put(0x2807B6FF24535695L, r => (o1: Any) => X[org.sireum.CircularQueue.DropRear[_]](r).enqueue(X(o1))) // methodKey(F, "org.sireum.CircularQueue.DropRear", "enqueue").value
    r.put(0x2A145156F47E6E4FL, _ => (o1: Any) => org.sireum.Either.Left.apply(X(o1))) // methodKey(T, "org.sireum.Either.Left", "apply").value
    r.put(0xDB1661F19775E39EL, _ => (o1: Any) => org.sireum.Either.Left.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Either.Left", "unapply").value
    r.put(0x8E115A84FCF61952L, _ => (o1: Any) => org.sireum.Either.Right.apply(X(o1))) // methodKey(T, "org.sireum.Either.Right", "apply").value
    r.put(0x1F04F661E5DD82C3L, _ => (o1: Any) => org.sireum.Either.Right.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Either.Right", "unapply").value
    r.put(0xB9883FCCD850BFC2L, r => (o1: Any) => X[org.sireum.Graph.Edge[_, _]](r).toInternal(X(o1))) // methodKey(F, "org.sireum.Graph.Edge", "toInternal").value
    r.put(0x44C00D57780B7A3CL, _ => (o1: Any) => org.sireum.Graph.Edge.Plain.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Graph.Edge.Plain", "unapply").value
    r.put(0x616B0AAC7C4CB469L, r => (o1: Any) => X[org.sireum.Graph.Edge.Plain[_, _]](r).toInternal(X(o1))) // methodKey(F, "org.sireum.Graph.Edge.Plain", "toInternal").value
    r.put(0xAFC6C8E24E139EE9L, _ => (o1: Any) => org.sireum.Graph.Edge.Data.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Graph.Edge.Data", "unapply").value
    r.put(0x7344932FA132ABC6L, r => (o1: Any) => X[org.sireum.Graph.Edge.Data[_, _]](r).toInternal(X(o1))) // methodKey(F, "org.sireum.Graph.Edge.Data", "toInternal").value
    r.put(0x5EF1FD5DA4CC3C4BL, r => (o1: Any) => X[org.sireum.Graph.Internal.Edge[_]](r).toEdge(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edge", "toEdge").value
    r.put(0xB045800B483A8835L, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges", "+").value
    r.put(0xC942B2044C8B0B19L, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges", "++").value
    r.put(0x7EBD9AE009AD01E3L, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges[_]](r).`-#`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges", "-#").value
    r.put(0x9F2109BB5D2F78F7L, _ => (o1: Any) => org.sireum.Graph.Internal.Edges.Set.apply(X(o1))) // methodKey(T, "org.sireum.Graph.Internal.Edges.Set", "apply").value
    r.put(0x70B14E29F21A904FL, _ => (o1: Any) => org.sireum.Graph.Internal.Edges.Set.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Graph.Internal.Edges.Set", "unapply").value
    r.put(0x10A3A0EB5A4B2E20L, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges.Set[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "+").value
    r.put(0x1A135CE0356CD55DL, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges.Set[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "++").value
    r.put(0x6F5FBD17F756AC87L, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges.Set[_]](r).`-#`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges.Set", "-#").value
    r.put(0xBFB6233E4680DCB4L, _ => (o1: Any) => org.sireum.Graph.Internal.Edges.Bag.apply(X(o1))) // methodKey(T, "org.sireum.Graph.Internal.Edges.Bag", "apply").value
    r.put(0xD31E0D2E5F3B4CACL, _ => (o1: Any) => org.sireum.Graph.Internal.Edges.Bag.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Graph.Internal.Edges.Bag", "unapply").value
    r.put(0xFE9D6760AFB3005AL, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges.Bag[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "+").value
    r.put(0x365FD0277AF6836EL, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges.Bag[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "++").value
    r.put(0x03C3086DC88A19AFL, r => (o1: Any) => X[org.sireum.Graph.Internal.Edges.Bag[_]](r).`-#`(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edges.Bag", "-#").value
    r.put(0x650413D1EB377875L, _ => (o1: Any) => org.sireum.Graph.Internal.Edge.Plain.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Graph.Internal.Edge.Plain", "unapply").value
    r.put(0x8480B0D35D747F55L, r => (o1: Any) => X[org.sireum.Graph.Internal.Edge.Plain[_]](r).toEdge(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edge.Plain", "toEdge").value
    r.put(0x86BE8B8C300FF258L, _ => (o1: Any) => org.sireum.Graph.Internal.Edge.Data.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Graph.Internal.Edge.Data", "unapply").value
    r.put(0xCE3348EBA7737064L, r => (o1: Any) => X[org.sireum.Graph.Internal.Edge.Data[_]](r).toEdge(X(o1))) // methodKey(F, "org.sireum.Graph.Internal.Edge.Data", "toEdge").value
    r.put(0x46B747C8682AA910L, _ => (o1: Any) => org.sireum.Graph.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5)) => MSome((o0, o1, o2, o3, o4, o5))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Graph", "unapply").value
    r.put(0x13569C20BEAF68B7L, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).`*`(X(o1))) // methodKey(F, "org.sireum.Graph", "*").value
    r.put(0xBAE20750141FD137L, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).`--*`(X(o1))) // methodKey(F, "org.sireum.Graph", "--*").value
    r.put(0x06369F67ACF9D25AL, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).`+`(X(o1))) // methodKey(F, "org.sireum.Graph", "+").value
    r.put(0x8FD5F54D9498EDBFL, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).`+@`(X(o1))) // methodKey(F, "org.sireum.Graph", "+@").value
    r.put(0xD7DEB22798FA4775L, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).`-`(X(o1))) // methodKey(F, "org.sireum.Graph", "-").value
    r.put(0x0287137C0FAE2E23L, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).`-#`(X(o1))) // methodKey(F, "org.sireum.Graph", "-#").value
    r.put(0x5B363D982C88CCF2L, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).`--`(X(o1))) // methodKey(F, "org.sireum.Graph", "--").value
    r.put(0xFF5E0CC96DFD0033L, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).incoming(X(o1))) // methodKey(F, "org.sireum.Graph", "incoming").value
    r.put(0xCE2F58F6EE4BD98FL, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).outgoing(X(o1))) // methodKey(F, "org.sireum.Graph", "outgoing").value
    r.put(0x4D8587690CCDF18CL, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).addEdge(X(o1))) // methodKey(F, "org.sireum.Graph", "addEdge").value
    r.put(0xE4014371D0E22051L, r => (o1: Any) => X[org.sireum.Graph[_, _]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.Graph", "isEqual").value
    r.put(0xF8A739D900A5B52DL, _ => (o1: Any) => org.sireum.HashBag.apply(X(o1))) // methodKey(T, "org.sireum.HashBag", "apply").value
    r.put(0x8592A189C44ADEAEL, _ => (o1: Any) => org.sireum.HashBag.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.HashBag", "unapply").value
    r.put(0x14FE0A08F57C9F53L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).count(X(o1))) // methodKey(F, "org.sireum.HashBag", "count").value
    r.put(0x12C3654428000C69L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.HashBag", "contains").value
    r.put(0x5E7E5D6642A4D2C2L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.HashBag", "+").value
    r.put(0xB15AB83BA61193B1L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`+#`(X(o1))) // methodKey(F, "org.sireum.HashBag", "+#").value
    r.put(0x4240141859FF14A6L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.HashBag", "++").value
    r.put(0xC03850D27E4728ABL, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`-`(X(o1))) // methodKey(F, "org.sireum.HashBag", "-").value
    r.put(0x2864D1CA3EF8A048L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`--`(X(o1))) // methodKey(F, "org.sireum.HashBag", "--").value
    r.put(0xD3363A1408EB0F8BL, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`-#`(X(o1))) // methodKey(F, "org.sireum.HashBag", "-#").value
    r.put(0x757AA409EB11DD6EL, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`\\`(X(o1))) // methodKey(F, "org.sireum.HashBag", "\\").value
    r.put(0x9D819EB7A536752AL, r => (o1: Any) => X[org.sireum.HashBag[_]](r).union(X(o1))) // methodKey(F, "org.sireum.HashBag", "union").value
    r.put(0x231DD63D12F042F0L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`\u222A`(X(o1))) // methodKey(F, "org.sireum.HashBag", "\u222A").value
    r.put(0xAAD5EB7F8B09EDF5L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).intersect(X(o1))) // methodKey(F, "org.sireum.HashBag", "intersect").value
    r.put(0xB6E99A870EF6F830L, r => (o1: Any) => X[org.sireum.HashBag[_]](r).`\u2229`(X(o1))) // methodKey(F, "org.sireum.HashBag", "\u2229").value
    r.put(0x3D9F6BDE8FB0A203L, _ => (o1: Any) => org.sireum.HashMap.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.HashMap", "unapply").value
    r.put(0xD577D5020B803994L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).`+`(X(o1))) // methodKey(F, "org.sireum.HashMap", "+").value
    r.put(0x6AB1E10BDBB27AE5L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.HashMap", "++").value
    r.put(0x6F5A3D682DFCF7E3L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).ensureCapacity(X(o1))) // methodKey(F, "org.sireum.HashMap", "ensureCapacity").value
    r.put(0x56CD0C4554BD3DF7L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).hashIndex(X(o1))) // methodKey(F, "org.sireum.HashMap", "hashIndex").value
    r.put(0x9D72F48EE0E1C5F1L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).get(X(o1))) // methodKey(F, "org.sireum.HashMap", "get").value
    r.put(0xC6B28FB27F5F62A2L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).entry(X(o1))) // methodKey(F, "org.sireum.HashMap", "entry").value
    r.put(0x79C53A9C4CAD5BF8L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).`--`(X(o1))) // methodKey(F, "org.sireum.HashMap", "--").value
    r.put(0x5829834C2C0BAAC9L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).`-`(X(o1))) // methodKey(F, "org.sireum.HashMap", "-").value
    r.put(0xEDCB1EC58C955B44L, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.HashMap", "contains").value
    r.put(0x5F99C17253B17CDDL, r => (o1: Any) => X[org.sireum.HashMap[_, _]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.HashMap", "isEqual").value
    r.put(0x8CFFAA3ABB9175CDL, _ => (o1: Any) => org.sireum.HashSBag.apply(X(o1))) // methodKey(T, "org.sireum.HashSBag", "apply").value
    r.put(0xE5EF1CD507E500A8L, _ => (o1: Any) => org.sireum.HashSBag.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.HashSBag", "unapply").value
    r.put(0xC4B83CC78D888E6EL, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).count(X(o1))) // methodKey(F, "org.sireum.HashSBag", "count").value
    r.put(0x361844EC960DF41BL, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.HashSBag", "contains").value
    r.put(0x0B5DEFCC3FCA0088L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "+").value
    r.put(0xB50B0CEF51ADB256L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`+#`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "+#").value
    r.put(0x04B0585BE3C9BE45L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "++").value
    r.put(0xE481A7A9483A9EB8L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`-`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "-").value
    r.put(0xB0BF15918EC699FBL, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`--`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "--").value
    r.put(0x3882E271ACD171D1L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`-#`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "-#").value
    r.put(0x54F6E3976EC841DCL, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`\\`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "\\").value
    r.put(0x9E5AD49C288F45D9L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).union(X(o1))) // methodKey(F, "org.sireum.HashSBag", "union").value
    r.put(0x22ACAF56B02EF169L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`\u222A`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "\u222A").value
    r.put(0x819E13266DD1FEE7L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).intersect(X(o1))) // methodKey(F, "org.sireum.HashSBag", "intersect").value
    r.put(0x165909AF5127E816L, r => (o1: Any) => X[org.sireum.HashSBag[_]](r).`\u2229`(X(o1))) // methodKey(F, "org.sireum.HashSBag", "\u2229").value
    r.put(0x8A303DF7682517E6L, _ => (o1: Any) => org.sireum.HashSMap.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.HashSMap", "unapply").value
    r.put(0x67ECF5672213A3B8L, r => (o1: Any) => X[org.sireum.HashSMap[_, _]](r).`+`(X(o1))) // methodKey(F, "org.sireum.HashSMap", "+").value
    r.put(0xAB543E66DC36082BL, r => (o1: Any) => X[org.sireum.HashSMap[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.HashSMap", "++").value
    r.put(0x008CA0B6B0130329L, r => (o1: Any) => X[org.sireum.HashSMap[_, _]](r).get(X(o1))) // methodKey(F, "org.sireum.HashSMap", "get").value
    r.put(0xD1325A5B3B985B6BL, r => (o1: Any) => X[org.sireum.HashSMap[_, _]](r).entry(X(o1))) // methodKey(F, "org.sireum.HashSMap", "entry").value
    r.put(0x9303E11CF76CA5DCL, r => (o1: Any) => X[org.sireum.HashSMap[_, _]](r).`--`(X(o1))) // methodKey(F, "org.sireum.HashSMap", "--").value
    r.put(0x28F83DA5652CF8EFL, r => (o1: Any) => X[org.sireum.HashSMap[_, _]](r).`-`(X(o1))) // methodKey(F, "org.sireum.HashSMap", "-").value
    r.put(0xCC93B9EC1CC49D6EL, r => (o1: Any) => X[org.sireum.HashSMap[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.HashSMap", "contains").value
    r.put(0x0C988672729CD106L, r => (o1: Any) => X[org.sireum.HashSMap[_, _]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.HashSMap", "isEqual").value
    r.put(0xE5CE52FF3AE85B25L, _ => (o1: Any) => org.sireum.HashSSet.apply(X(o1))) // methodKey(T, "org.sireum.HashSSet", "apply").value
    r.put(0x8F87C7AE7CCE3C1FL, _ => (o1: Any) => org.sireum.HashSSet.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.HashSSet", "unapply").value
    r.put(0x5968249A649B012BL, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.HashSSet", "+").value
    r.put(0x7FE4A180BA29371AL, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.HashSSet", "++").value
    r.put(0x3C9A59C990110750L, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).`-`(X(o1))) // methodKey(F, "org.sireum.HashSSet", "-").value
    r.put(0xF30D56C88BFE6BC5L, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).`--`(X(o1))) // methodKey(F, "org.sireum.HashSSet", "--").value
    r.put(0xF7C9E025193327D2L, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.HashSSet", "contains").value
    r.put(0x94C2A0E86C861C92L, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).union(X(o1))) // methodKey(F, "org.sireum.HashSSet", "union").value
    r.put(0xF3CE7C692404629CL, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).`\u222A`(X(o1))) // methodKey(F, "org.sireum.HashSSet", "\u222A").value
    r.put(0x78ABA93A8F5C10CCL, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).intersect(X(o1))) // methodKey(F, "org.sireum.HashSSet", "intersect").value
    r.put(0x9DB5786C6F09CBEBL, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).`\u2229`(X(o1))) // methodKey(F, "org.sireum.HashSSet", "\u2229").value
    r.put(0x057459837BCC7247L, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).`\\`(X(o1))) // methodKey(F, "org.sireum.HashSSet", "\\").value
    r.put(0x12247002D3440BC1L, r => (o1: Any) => X[org.sireum.HashSSet[_]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.HashSSet", "isEqual").value
    r.put(0x5E6195818F2A4DD4L, _ => (o1: Any) => org.sireum.HashSet.apply(X(o1))) // methodKey(T, "org.sireum.HashSet", "apply").value
    r.put(0x98C1596D7FD76D41L, _ => (o1: Any) => org.sireum.HashSet.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.HashSet", "unapply").value
    r.put(0x959DA8AEBD379681L, r => (o1: Any) => X[org.sireum.HashSet[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.HashSet", "+").value
    r.put(0xAFF5FFF07FF06147L, r => (o1: Any) => X[org.sireum.HashSet[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.HashSet", "++").value
    r.put(0xF18CE82ADAACDE0AL, r => (o1: Any) => X[org.sireum.HashSet[_]](r).`-`(X(o1))) // methodKey(F, "org.sireum.HashSet", "-").value
    r.put(0x767A5038C516366DL, r => (o1: Any) => X[org.sireum.HashSet[_]](r).`--`(X(o1))) // methodKey(F, "org.sireum.HashSet", "--").value
    r.put(0xE30657A3BB3935F5L, r => (o1: Any) => X[org.sireum.HashSet[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.HashSet", "contains").value
    r.put(0x3260A355055B0CD9L, r => (o1: Any) => X[org.sireum.HashSet[_]](r).union(X(o1))) // methodKey(F, "org.sireum.HashSet", "union").value
    r.put(0x8FFEE6FE59FC38B4L, r => (o1: Any) => X[org.sireum.HashSet[_]](r).`\u222A`(X(o1))) // methodKey(F, "org.sireum.HashSet", "\u222A").value
    r.put(0x251D0E2352A5BF4BL, r => (o1: Any) => X[org.sireum.HashSet[_]](r).intersect(X(o1))) // methodKey(F, "org.sireum.HashSet", "intersect").value
    r.put(0x00B1DD9285C75DA4L, r => (o1: Any) => X[org.sireum.HashSet[_]](r).`\u2229`(X(o1))) // methodKey(F, "org.sireum.HashSet", "\u2229").value
    r.put(0xD1302D81DA7A1D99L, r => (o1: Any) => X[org.sireum.HashSet[_]](r).`\\`(X(o1))) // methodKey(F, "org.sireum.HashSet", "\\").value
    r.put(0x406E00366E215560L, r => (o1: Any) => X[org.sireum.HashSet[_]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.HashSet", "isEqual").value
    r.put(0x82CEE009EB5FFEA0L, _ => (o1: Any) => org.sireum.IndexMap.apply(X(o1))) // methodKey(T, "org.sireum.IndexMap", "apply").value
    r.put(0xA4969954EA3D5585L, _ => (o1: Any) => org.sireum.IndexMap.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.IndexMap", "unapply").value
    r.put(0x900B060FEFCCEDFFL, r => (o1: Any) => X[org.sireum.IndexMap[_, _]](r).`+`(X(o1))) // methodKey(F, "org.sireum.IndexMap", "+").value
    r.put(0x8800DAEDDA0889E2L, r => (o1: Any) => X[org.sireum.IndexMap[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.IndexMap", "contains").value
    r.put(0x21FC6362EF3EB274L, r => (o1: Any) => X[org.sireum.IndexMap[_, _]](r).get(X(o1))) // methodKey(F, "org.sireum.IndexMap", "get").value
    r.put(0xF98BB83B2BCD410AL, r => (o1: Any) => X[org.sireum.Indexable[_]](r).at(X(o1))) // methodKey(F, "org.sireum.Indexable", "at").value
    r.put(0x777373F372FC29FAL, r => (o1: Any) => X[org.sireum.Indexable[_]](r).has(X(o1))) // methodKey(F, "org.sireum.Indexable", "has").value
    r.put(0xC99DD8AD8ED5B4BBL, r => (o1: Any) => X[org.sireum.Indexable.Pos[_]](r).at(X(o1))) // methodKey(F, "org.sireum.Indexable.Pos", "at").value
    r.put(0xE8DEE8204B6975D8L, r => (o1: Any) => X[org.sireum.Indexable.Pos[_]](r).has(X(o1))) // methodKey(F, "org.sireum.Indexable.Pos", "has").value
    r.put(0x86024C81261236BFL, _ => (o1: Any) => org.sireum.Indexable.Isz.apply(X(o1))) // methodKey(T, "org.sireum.Indexable.Isz", "apply").value
    r.put(0x56D67B232E75303CL, _ => (o1: Any) => org.sireum.Indexable.Isz.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Indexable.Isz", "unapply").value
    r.put(0x1B2C4D0447C26BC6L, r => (o1: Any) => X[org.sireum.Indexable.Isz[_]](r).at(X(o1))) // methodKey(F, "org.sireum.Indexable.Isz", "at").value
    r.put(0x0CC1D563A132AAABL, r => (o1: Any) => X[org.sireum.Indexable.Isz[_]](r).has(X(o1))) // methodKey(F, "org.sireum.Indexable.Isz", "has").value
    r.put(0x5AB9FB1645CDDF4CL, _ => (o1: Any) => org.sireum.Indexable.IszDocInfo.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Indexable.IszDocInfo", "unapply").value
    r.put(0x7BA930D8E6F0AAAEL, r => (o1: Any) => X[org.sireum.Indexable.IszDocInfo[_]](r).at(X(o1))) // methodKey(F, "org.sireum.Indexable.IszDocInfo", "at").value
    r.put(0xA33A566D3398C70DL, r => (o1: Any) => X[org.sireum.Indexable.IszDocInfo[_]](r).has(X(o1))) // methodKey(F, "org.sireum.Indexable.IszDocInfo", "has").value
    r.put(0x696079B2288A0789L, r => (o1: Any) => X[org.sireum.Jen[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen", "generate").value
    r.put(0x65BDA0C4BE89B4FAL, r => (o1: Any) => X[org.sireum.Jen[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen", "foreach").value
    r.put(0xFA824C2EEBF91A39L, r => (o1: Any) => X[org.sireum.Jen[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen", "find").value
    r.put(0x29F6F7DE797D2F38L, r => (o1: Any) => X[org.sireum.Jen[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen", "exists").value
    r.put(0x323429C83E87F4C4L, r => (o1: Any) => X[org.sireum.Jen[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen", "contains").value
    r.put(0xC3AE606DC5792974L, r => (o1: Any) => X[org.sireum.Jen[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen", "forall").value
    r.put(0xDF19F58833F948C1L, r => (o1: Any) => X[org.sireum.Jen[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen", "countIf").value
    r.put(0x6ED18A737F43902FL, r => (o1: Any) => X[org.sireum.Jen[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen", "reduce").value
    r.put(0x293839077AE48AF4L, r => (o1: Any) => X[org.sireum.Jen[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen", "reduceLeft").value
    r.put(0x09123E14D86E3677L, r => (o1: Any) => X[org.sireum.Jen[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen", "filter").value
    r.put(0xD06DAE1260C563ABL, r => (o1: Any) => X[org.sireum.Jen[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen", "withFilter").value
    r.put(0xDBA73A7EFDE41222L, r => (o1: Any) => X[org.sireum.Jen[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen", "map").value
    r.put(0xA86D82C85DA64C81L, r => (o1: Any) => X[org.sireum.Jen[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen", "flatMap").value
    r.put(0xF3D56566AF548D3BL, r => (o1: Any) => X[org.sireum.Jen[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen", "flatten").value
    r.put(0x8866765E0EA72E3CL, r => (o1: Any) => X[org.sireum.Jen[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen", "take").value
    r.put(0x28B365DFCA120DAFL, r => (o1: Any) => X[org.sireum.Jen[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen", "drop").value
    r.put(0x79A815794A1411E6L, r => (o1: Any) => X[org.sireum.Jen[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen", "takeWhile").value
    r.put(0xD25644F25254E580L, r => (o1: Any) => X[org.sireum.Jen[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen", "dropWhile").value
    r.put(0xEAD5D10AC100858BL, r => (o1: Any) => X[org.sireum.Jen[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen", "zip").value
    r.put(0x5191A63B6B74429DL, r => (o1: Any) => X[org.sireum.Jen[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen", "product").value
    r.put(0xD8A570F0DDBCEF49L, r => (o1: Any) => X[org.sireum.Jen[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen", "++").value
    r.put(0x9986490B255567BFL, r => (o1: Any) => X[org.sireum.Jen[_]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen", "toIS").value
    r.put(0xA6DC351B6BD7CA78L, r => (o1: Any) => X[org.sireum.Jen[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen", "toMS").value
    r.put(0x783C95FA2234ADF5L, r => (o1: Any) => X[org.sireum.Jen[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen", "mkString").value
    r.put(0xC218D3440F9C4305L, _ => (o1: Any) => org.sireum.Jen.Internal.ISImpl.apply(X(o1))) // methodKey(T, "org.sireum.Jen.Internal.ISImpl", "apply").value
    r.put(0xE64547F736B456DBL, _ => (o1: Any) => org.sireum.Jen.Internal.ISImpl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.ISImpl", "unapply").value
    r.put(0x59243DFE59C50986L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "generate").value
    r.put(0x3BE45166C1041A74L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "foreach").value
    r.put(0x6523D9D3EA5CBEF7L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "find").value
    r.put(0xBF31DED6E028E676L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "exists").value
    r.put(0x6A3A7C840FA4C13DL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "contains").value
    r.put(0xA9E82492903945DBL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "forall").value
    r.put(0xBF3DE5E21C95DCE3L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "countIf").value
    r.put(0x77D6D7464CE1C0BEL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "reduce").value
    r.put(0xACE0200A8A8A0658L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "reduceLeft").value
    r.put(0x0E208264E6A35A1BL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "filter").value
    r.put(0x868FEACFB9748173L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "withFilter").value
    r.put(0xF025B2D3126B0E37L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "map").value
    r.put(0x0B8F9A312B83FCF1L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "flatMap").value
    r.put(0x845EDE65C8A055FCL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "flatten").value
    r.put(0x078683F9042FC460L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "take").value
    r.put(0x2E1840F599D4EF7AL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "drop").value
    r.put(0x924D9F4090C2E1D5L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "takeWhile").value
    r.put(0xAADB15CC2F44B34AL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "dropWhile").value
    r.put(0x5C4F5DF893E75870L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "zip").value
    r.put(0xDEC6E39BF6614145L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "product").value
    r.put(0xE32672040C1E3B5DL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "++").value
    r.put(0x3C5477B34CD8D9DFL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "toIS").value
    r.put(0xFCFF5A0146F19692L, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "toMS").value
    r.put(0x6F5EFB41710F7C4EL, r => (o1: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "mkString").value
    r.put(0x637A2B3806FF723CL, _ => (o1: Any) => org.sireum.Jen.Internal.MapImpl.apply(X(o1))) // methodKey(T, "org.sireum.Jen.Internal.MapImpl", "apply").value
    r.put(0xA772BC42ED3CA7CFL, _ => (o1: Any) => org.sireum.Jen.Internal.MapImpl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.MapImpl", "unapply").value
    r.put(0x2D3FDF824A52FD76L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "generate").value
    r.put(0xCBB288F4C4F9AD86L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "foreach").value
    r.put(0x3D71DC02991C3CFAL, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "find").value
    r.put(0x7B0ECFDECF9607BEL, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "exists").value
    r.put(0x2BB7DA8502D25E84L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "contains").value
    r.put(0x46E18CB3108CA415L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "forall").value
    r.put(0x00794397E9304CEEL, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "countIf").value
    r.put(0x2CA81E90EB95798DL, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "reduce").value
    r.put(0x9DDBE26D7C178676L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "reduceLeft").value
    r.put(0x73977D5829105195L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "filter").value
    r.put(0x3B8E7F2B560DF1E8L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "withFilter").value
    r.put(0x539A7DBC1952D7B4L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "map").value
    r.put(0x91A88A70D977C9F7L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "flatMap").value
    r.put(0x643CBD495A5E4F29L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "flatten").value
    r.put(0x861A563FDF790237L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "take").value
    r.put(0x8F8BD440B32A037DL, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "drop").value
    r.put(0x800F0BB4F1D9F069L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "takeWhile").value
    r.put(0xF2ACD4AA1F495E61L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "dropWhile").value
    r.put(0x75C55A0B8E45F000L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "zip").value
    r.put(0xDA9514DEBDCF0B48L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "product").value
    r.put(0x4E0505E2649195ABL, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "++").value
    r.put(0x8B038345A48E08E2L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "toIS").value
    r.put(0x6FB11FF2E6FBD7DEL, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "toMS").value
    r.put(0x9A58E5B573CA4713L, r => (o1: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "mkString").value
    r.put(0x660AEC3EEA0C9D90L, _ => (o1: Any) => org.sireum.Jen.Internal.HashMapImpl.apply(X(o1))) // methodKey(T, "org.sireum.Jen.Internal.HashMapImpl", "apply").value
    r.put(0x0F952A110D0B81C7L, _ => (o1: Any) => org.sireum.Jen.Internal.HashMapImpl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.HashMapImpl", "unapply").value
    r.put(0x99AEEE6BBE77854DL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "generate").value
    r.put(0x86280BC4DCFACCAFL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "foreach").value
    r.put(0x8BDE7E903E33B02EL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "find").value
    r.put(0x4F1EB6621917951CL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "exists").value
    r.put(0xE4CA216636081972L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "contains").value
    r.put(0xABAE042438BA9CB3L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "forall").value
    r.put(0x811C99A8C7E98EB8L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "countIf").value
    r.put(0x75FC216AF9010241L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "reduce").value
    r.put(0x30CF9E057876A7A9L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "reduceLeft").value
    r.put(0x6AECFDBD0DC05B16L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "filter").value
    r.put(0xC4607AB7229834FEL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "withFilter").value
    r.put(0x27A25BFA5887E355L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "map").value
    r.put(0x3D0513F0ABB0F2CFL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "flatMap").value
    r.put(0x11FFD4AFB1DEC7EEL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "flatten").value
    r.put(0x02408E93586F1D1AL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "take").value
    r.put(0xE392FA57E8A696B1L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "drop").value
    r.put(0x217F11225A51381CL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "takeWhile").value
    r.put(0xC69A95F93716AE2CL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "dropWhile").value
    r.put(0xB155F75224B3C891L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "zip").value
    r.put(0x6AF05897016C210CL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "product").value
    r.put(0x322DC3DB2119D55BL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "++").value
    r.put(0xEFB8B4859AA0424BL, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "toIS").value
    r.put(0xD6C33A68D82BE896L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "toMS").value
    r.put(0x7D1755F1B26D68F0L, r => (o1: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "mkString").value
    r.put(0x6E2C5C63EF656547L, _ => (o1: Any) => org.sireum.Jen.Internal.Filtered.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.Filtered", "unapply").value
    r.put(0x8E8A2A6712AC66E0L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "generate").value
    r.put(0x440550A4E1856327L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "foreach").value
    r.put(0xF2072D08B3F5E98AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "find").value
    r.put(0x8FEBFA6F104F0B39L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "exists").value
    r.put(0xBAB6084195EDAB85L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "contains").value
    r.put(0x9D9F9164BB23CD41L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "forall").value
    r.put(0x2EE1E258005A62DCL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "countIf").value
    r.put(0xC5E7EE4916B1FAEEL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "reduce").value
    r.put(0x240D2D7B7240498CL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "reduceLeft").value
    r.put(0x30AB954FF351629DL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "filter").value
    r.put(0x3765A676C911EE3DL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "withFilter").value
    r.put(0x0979DF9B78D23772L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "map").value
    r.put(0x280FBCFDCD74B944L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "flatMap").value
    r.put(0xFDADA8D53B647B10L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "flatten").value
    r.put(0xE9EECCC51DCACF6DL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "take").value
    r.put(0x00BE5E5B87E7749CL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "drop").value
    r.put(0xE531F629B95832E5L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "takeWhile").value
    r.put(0xE8E2695620C38055L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "dropWhile").value
    r.put(0x817F1753B9332D2FL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "zip").value
    r.put(0xFEBDE34E5FD7A2B7L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "product").value
    r.put(0xBDA7FC2B92B07E68L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "++").value
    r.put(0xF5B3BE96FAF4390EL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "toIS").value
    r.put(0x979D6DB62113685BL, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "toMS").value
    r.put(0x50B16868C6334E68L, r => (o1: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "mkString").value
    r.put(0x2A5B2E6E018C1E33L, _ => (o1: Any) => org.sireum.Jen.Internal.Mapped.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.Mapped", "unapply").value
    r.put(0xB534F6C2FC7592EFL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "generate").value
    r.put(0x5C74DD9336D1A3D8L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "foreach").value
    r.put(0x4BF1689C7033D058L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "find").value
    r.put(0x01143985B828661CL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "exists").value
    r.put(0x5FA6556E41BBFA0BL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "contains").value
    r.put(0xAC5F447183E97466L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "forall").value
    r.put(0x5B2ADFC8E8A0579CL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "countIf").value
    r.put(0x1A5150C063AD5FA3L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "reduce").value
    r.put(0xD5A9F61361666569L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "reduceLeft").value
    r.put(0xB407736C7FF9BFEAL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "filter").value
    r.put(0x59CCC5476E38554CL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "withFilter").value
    r.put(0x47E59AE6E10E0486L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "map").value
    r.put(0xBCD9F51B9D88EC2AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "flatMap").value
    r.put(0x0CAB36BAF33109B5L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "flatten").value
    r.put(0x3B6464D220F52B20L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "take").value
    r.put(0xC9C07080B0888DCDL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "drop").value
    r.put(0x2038B74307A89789L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "takeWhile").value
    r.put(0xC56791793F718BD0L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "dropWhile").value
    r.put(0xB674E783D3F192F7L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "zip").value
    r.put(0xA09C65AE20496250L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "product").value
    r.put(0x01890CB538884929L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "++").value
    r.put(0xE71ED3205CDB851AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "toIS").value
    r.put(0xEA1B4B643BA25439L, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "toMS").value
    r.put(0x7E9282B8A2C7CDCBL, r => (o1: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "mkString").value
    r.put(0xA8C036FF8C109F32L, _ => (o1: Any) => org.sireum.Jen.Internal.FlatMapped.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.FlatMapped", "unapply").value
    r.put(0xFBD94D3EFECEEED9L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "generate").value
    r.put(0x02A662DF626C1D3AL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "foreach").value
    r.put(0x9B0D24BE8E1E899AL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "find").value
    r.put(0x41CC133770A1EB2EL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "exists").value
    r.put(0x392831669F9D9960L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "contains").value
    r.put(0xC4DD89835729C6FCL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "forall").value
    r.put(0x56A3C9D99A03AFF0L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "countIf").value
    r.put(0xA2D0830943CEBE32L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "reduce").value
    r.put(0x0548EAE641D40DDDL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "reduceLeft").value
    r.put(0x7E479BB5F7D250DDL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "filter").value
    r.put(0x3B0E3AE589C9FAE4L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "withFilter").value
    r.put(0x451A1A1F42AABE5AL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "map").value
    r.put(0xFEC085164D6083F5L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "flatMap").value
    r.put(0xBE289F50B57E60D1L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "flatten").value
    r.put(0xD02E153A0F183A4DL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "take").value
    r.put(0x215C2BE3EC5FD3B7L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "drop").value
    r.put(0xF97D44CC1ECA8E1EL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "takeWhile").value
    r.put(0x4D32DB7A1AADAA6CL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "dropWhile").value
    r.put(0x682136A9BF391C8AL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "zip").value
    r.put(0x84A31CB24C12EF73L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "product").value
    r.put(0xFA98AA5F5E3B44E6L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "++").value
    r.put(0xDE54103ADD7DCD6AL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "toIS").value
    r.put(0x2656A0F32157E4FCL, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "toMS").value
    r.put(0x45C525AE595F4296L, r => (o1: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "mkString").value
    r.put(0x82E349E3FEAAD511L, _ => (o1: Any) => org.sireum.Jen.Internal.Sliced.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.Sliced", "unapply").value
    r.put(0x153F96BFDDE97355L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "generate").value
    r.put(0x80E39F1C072CF7F6L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "foreach").value
    r.put(0x484DEC2ECF5C842EL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "find").value
    r.put(0x0D52E1622D63F7C8L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "exists").value
    r.put(0x4B3B092C7A334102L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "contains").value
    r.put(0x40BA1FD0BD63AD2AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "forall").value
    r.put(0x536077DBDDABD446L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "countIf").value
    r.put(0x09B9ACC2014CD9B0L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "reduce").value
    r.put(0x2533D2DEF7FD7CF6L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "reduceLeft").value
    r.put(0x2CDE2014CFA6202DL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "filter").value
    r.put(0xC763125C92152D1DL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "withFilter").value
    r.put(0x2C1D65D8BBA219A8L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "map").value
    r.put(0x3F874AEED9643ED0L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "flatMap").value
    r.put(0xC70351054A57DDAEL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "flatten").value
    r.put(0x668FB670F8A9361AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "take").value
    r.put(0x915D1EC1962628A7L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "drop").value
    r.put(0xE3FC1C49B6E5B7CDL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "takeWhile").value
    r.put(0x0C6143B958CC5759L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "dropWhile").value
    r.put(0x1F9E08EA24CCBD2EL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "zip").value
    r.put(0x7A2C2AABCAEDCB3FL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "product").value
    r.put(0x263F5431CD61743EL, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "++").value
    r.put(0x9D927EA132B610D8L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "toIS").value
    r.put(0x13F5222944843B17L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "toMS").value
    r.put(0x3A2C64FEDF811FA3L, r => (o1: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "mkString").value
    r.put(0xA87B3DE2919D6FC9L, _ => (o1: Any) => org.sireum.Jen.Internal.TakeWhile.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.TakeWhile", "unapply").value
    r.put(0x84C9A233D46F1C96L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "generate").value
    r.put(0xD55279C0FD5A01BAL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "foreach").value
    r.put(0x27DF2CFC60A3A4C4L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "find").value
    r.put(0x783DE0928D6CC66CL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "exists").value
    r.put(0xD0535266FC6CD69AL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "contains").value
    r.put(0x19775856366B34ABL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "forall").value
    r.put(0x44B5B01408CA590DL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "countIf").value
    r.put(0x92878BE853AC804AL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "reduce").value
    r.put(0x5CD4F92E061010EAL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "reduceLeft").value
    r.put(0x00887C012A7B23A9L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "filter").value
    r.put(0xFD5A0CB19C740B92L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "withFilter").value
    r.put(0x9AEC724ED46A8BA1L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "map").value
    r.put(0x71794754A4F24076L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "flatMap").value
    r.put(0xF7514158835C9C1AL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "flatten").value
    r.put(0x772B7BD57C878DCBL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "take").value
    r.put(0xF4B2D729C63A05D6L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "drop").value
    r.put(0x4DDADF88A418182EL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "takeWhile").value
    r.put(0xD6BC6F161CC85A8AL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "dropWhile").value
    r.put(0xD286D91BDA445F1BL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "zip").value
    r.put(0x93FB6BEC2817DD44L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "product").value
    r.put(0xEEB6215A6570FE07L, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "++").value
    r.put(0xBF9C47398940727AL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "toIS").value
    r.put(0x4128ACD75B3156EBL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "toMS").value
    r.put(0x62132A1BED6EC37EL, r => (o1: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "mkString").value
    r.put(0x15286882C909AED2L, _ => (o1: Any) => org.sireum.Jen.Internal.DropWhile.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.DropWhile", "unapply").value
    r.put(0xF813C1B074DB24D7L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "generate").value
    r.put(0xB3A27F84403A4B62L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "foreach").value
    r.put(0xAD76D57EAD77EB7EL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "find").value
    r.put(0x4D2E6913967FD086L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "exists").value
    r.put(0x88412BE6DA70F484L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "contains").value
    r.put(0x8D7AEE3CBA82084CL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "forall").value
    r.put(0xC1F46ED9528598ADL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "countIf").value
    r.put(0x6435A035BF819A1AL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "reduce").value
    r.put(0x10AEE2475D5B5F2AL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "reduceLeft").value
    r.put(0x09334021DB91D9EEL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "filter").value
    r.put(0x17BC2B131F622BF9L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "withFilter").value
    r.put(0x7F7205B920D675EAL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "map").value
    r.put(0xC219B588734A0591L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "flatMap").value
    r.put(0x9FF33F59CE5918DAL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "flatten").value
    r.put(0x564D035F5D38E070L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "take").value
    r.put(0xF1F37A3F98D569F8L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "drop").value
    r.put(0xF07B8CAECD0FF451L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "takeWhile").value
    r.put(0xEFF8A5A35B681778L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "dropWhile").value
    r.put(0x8DAD8B8EBE272B5EL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "zip").value
    r.put(0x1981AFA730A2DC2EL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "product").value
    r.put(0x5C777C2EBADDFA8FL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "++").value
    r.put(0x96ED7BD49D7D1F46L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "toIS").value
    r.put(0x56132E9CC5668068L, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "toMS").value
    r.put(0xFDA10A97EE3FD99CL, r => (o1: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "mkString").value
    r.put(0x87AE6F7EB1B57247L, _ => (o1: Any) => org.sireum.Jen.Internal.ZipWithIndexed.apply(X(o1))) // methodKey(T, "org.sireum.Jen.Internal.ZipWithIndexed", "apply").value
    r.put(0x0361FB9B511A7D5AL, _ => (o1: Any) => org.sireum.Jen.Internal.ZipWithIndexed.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.ZipWithIndexed", "unapply").value
    r.put(0x2C86FE836F05CB27L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "generate").value
    r.put(0xDAD82D3C1550A2FAL, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "foreach").value
    r.put(0x22954832D4DE9778L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "find").value
    r.put(0xE1429CB297D342BCL, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "exists").value
    r.put(0x039574D293A0E1A8L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "contains").value
    r.put(0xA441204ABDC09D60L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "forall").value
    r.put(0x8B2610335F8ED7B6L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "countIf").value
    r.put(0x7D451546EA00B441L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "reduce").value
    r.put(0xA1C47947A35090C2L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "reduceLeft").value
    r.put(0x4085B67D5EEAC26AL, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "filter").value
    r.put(0x2D48C4C18A199213L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "withFilter").value
    r.put(0xBCFBEE82FC3E25D3L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "map").value
    r.put(0x8453B96A4467505DL, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "flatMap").value
    r.put(0x1F167370BB05D90FL, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "flatten").value
    r.put(0x9A8146D177268C30L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "take").value
    r.put(0x4A6F3569257F7940L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "drop").value
    r.put(0xE747EB0997FBBC61L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "takeWhile").value
    r.put(0x8FF12D7362084178L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "dropWhile").value
    r.put(0x93CB4E0B5C680C43L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "zip").value
    r.put(0xDF2498801EE4A062L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "product").value
    r.put(0x623A542B363554DEL, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "++").value
    r.put(0x531088986BCE1C0FL, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "toIS").value
    r.put(0xD7B222919E9BB0F6L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "toMS").value
    r.put(0x0BF72FEBA43B67D2L, r => (o1: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "mkString").value
    r.put(0x9FA34680DEAFF91DL, _ => (o1: Any) => org.sireum.Jen.Internal.Zipped.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.Zipped", "unapply").value
    r.put(0x8EB223876C49ACFCL, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "generate").value
    r.put(0x5B132FE14C56C805L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "foreach").value
    r.put(0x16CD59785FEAA918L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "find").value
    r.put(0x72D429843ED865F3L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "exists").value
    r.put(0x05D02F8BF21C1384L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "contains").value
    r.put(0xBC89531366096A90L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "forall").value
    r.put(0x78374D6D345A32F6L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "countIf").value
    r.put(0xC2E239A9CC2D17F4L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "reduce").value
    r.put(0xA4F43AB87A38DBBEL, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "reduceLeft").value
    r.put(0xDBB2E6B561AEE143L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "filter").value
    r.put(0xC6602ED8334A3C2DL, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "withFilter").value
    r.put(0x476A10888CABEC9CL, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "map").value
    r.put(0xA7686EF834E3E3DDL, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "flatMap").value
    r.put(0x49AF211454BACE02L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "flatten").value
    r.put(0x1E0BF199A14D4009L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "take").value
    r.put(0xD775EBE1703ED459L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "drop").value
    r.put(0x417AC1CE7E492EB7L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "takeWhile").value
    r.put(0x783BE7851D55D8F5L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "dropWhile").value
    r.put(0x8233431B6F9264B9L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "zip").value
    r.put(0xAD75B50B8DF10465L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "product").value
    r.put(0x38E328B0E314A142L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "++").value
    r.put(0x982B0B27B439CEDDL, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "toIS").value
    r.put(0x5A136BA39A1740F7L, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "toMS").value
    r.put(0x29DD6E61F97D725AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "mkString").value
    r.put(0x0940D60810E8C100L, _ => (o1: Any) => org.sireum.Jen.Internal.Concat.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.Concat", "unapply").value
    r.put(0x098653871134C443L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "generate").value
    r.put(0x97B0AF10BA9C0766L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "foreach").value
    r.put(0x71DDB98A410EFED9L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "find").value
    r.put(0x5A51B624D930C680L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "exists").value
    r.put(0xE865E7BEF6F3902AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "contains").value
    r.put(0xE334946DA058B374L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "forall").value
    r.put(0xAD11F57DE4481226L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "countIf").value
    r.put(0x73CBC04CC82F3FF1L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "reduce").value
    r.put(0x4E2E83DB3332B4ECL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "reduceLeft").value
    r.put(0x1A02049B53089038L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "filter").value
    r.put(0x1C129193F75DBF74L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "withFilter").value
    r.put(0xED1CEAADAD772A46L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "map").value
    r.put(0x002F0A7E6A593344L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "flatMap").value
    r.put(0xE99C1B94FED5C2ECL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "flatten").value
    r.put(0xD940DDBCAA6F39D6L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "take").value
    r.put(0x513DE56CC38FD47EL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "drop").value
    r.put(0xD8D35CA2D1F1F48DL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "takeWhile").value
    r.put(0xFC2CFC53877D02AAL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "dropWhile").value
    r.put(0xF51536054523745BL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "zip").value
    r.put(0x3CC757C07819CB79L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "product").value
    r.put(0xC35FC8307EFE8786L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "++").value
    r.put(0xB8C89D9BCB7CEA30L, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "toIS").value
    r.put(0x751CFFB6094E1A3AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "toMS").value
    r.put(0xB98780F96B57FCABL, r => (o1: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "mkString").value
    r.put(0xF52D015D607A591AL, _ => (o1: Any) => org.sireum.Jen.Internal.Product.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Jen.Internal.Product", "unapply").value
    r.put(0x3E88062635BE5EDBL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "generate").value
    r.put(0x495C6BDE892E1ED4L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "foreach").value
    r.put(0x95C69B5FE1E1E47BL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "find").value
    r.put(0x53E424AEC85FAE4FL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "exists").value
    r.put(0x674EEADFA0DBFF4DL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "contains").value
    r.put(0xD4817CB4CC0298C6L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "forall").value
    r.put(0xB8F200C307E74C53L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "countIf").value
    r.put(0xE9DC53052A285817L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "reduce").value
    r.put(0x4C39D8DE285973C0L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "reduceLeft").value
    r.put(0x42711B9EC9B8E506L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "filter").value
    r.put(0x59ADD0E0AE178ECCL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "withFilter").value
    r.put(0x69BE5F2E06729631L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "map").value
    r.put(0x28D9413173C8409BL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "flatMap").value
    r.put(0x36E9F9DA28CC3F6AL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "flatten").value
    r.put(0x4ABF016C27D6B9CDL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "take").value
    r.put(0x061152B40AF5BA70L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "drop").value
    r.put(0x8DCE8CA4B0CE3DA4L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "takeWhile").value
    r.put(0x1EF8B9DDE43D0B0BL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "dropWhile").value
    r.put(0xB51C51AA8A4117B0L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "zip").value
    r.put(0xB8EDDCE2C63EB85BL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "product").value
    r.put(0x2A50D6AD1819C9FEL, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "++").value
    r.put(0x9A9B8FE9E6CAC2C0L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "toIS").value
    r.put(0xEC81F495C1E6D003L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "toMS").value
    r.put(0xF74FFA40444D9B26L, r => (o1: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Jen.Internal.Product", "mkString").value
    r.put(0x9C5603298642ED72L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).toObject(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "toObject").value
    r.put(0x5E8B34403BCC0CB3L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).toArray(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "toArray").value
    r.put(0x698ACCB558C3A003L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).toNumber(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "toNumber").value
    r.put(0x27F0D993848DFFA6L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).toString(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "toString").value
    r.put(0xB8FF3E553903D8B0L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).toBoolean(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "toBoolean").value
    r.put(0xF3A2ECDF5B529952L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).kind(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "kind").value
    r.put(0xC22A2BB0C785894AL, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).fromObject(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "fromObject").value
    r.put(0x53C860CD8C369FB6L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).fromArray(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "fromArray").value
    r.put(0xC6051226CD92564DL, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).fromNumber(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "fromNumber").value
    r.put(0xCC279B8E91A5B907L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).fromString(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "fromString").value
    r.put(0x73AB0161DB5580E4L, r => (o1: Any) => X[org.sireum.Json.JsonAstBinding[_]](r).fromBoolean(X(o1))) // methodKey(F, "org.sireum.Json.JsonAstBinding", "fromBoolean").value
    r.put(0xCA5E0875B932CD5DL, _ => (o1: Any) => org.sireum.Json.ErrorMsg.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Json.ErrorMsg", "unapply").value
    r.put(0x56E26D563BF315E2L, _ => (o1: Any) => org.sireum.Json.Parser.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Json.Parser", "unapply").value
    r.put(0x4E19C2F7ADD81171L, r => (o1: Any) => X[org.sireum.Json.Parser](r).`offset_=`(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "offset_=").value
    r.put(0xAD39E620EF798E20L, r => (o1: Any) => X[org.sireum.Json.Parser](r).`errorOpt_=`(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "errorOpt_=").value
    r.put(0xFF7341AF2B97E770L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISZ(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISZ").value
    r.put(0x8D967A8B48655170L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISZ8(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISZ8").value
    r.put(0x04D1C09BBF1672EFL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISZ16(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISZ16").value
    r.put(0x96FE06ABD08A088DL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISZ32(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISZ32").value
    r.put(0xDDDA0A0AE5B6D2DFL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISZ64(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISZ64").value
    r.put(0x0FAFA1CDE8738C88L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISN(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISN").value
    r.put(0x8907C70AFED150A9L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISN8(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISN8").value
    r.put(0xF4FCFEA27D7210A4L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISN16(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISN16").value
    r.put(0xBAC56AA446EBA086L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISN32(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISN32").value
    r.put(0xB8727F4F9568A0EBL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISN64(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISN64").value
    r.put(0x9AB7B59BA5DA1A80L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISS8(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISS8").value
    r.put(0xBACEC8F8F1B72FA8L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISS16(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISS16").value
    r.put(0xDE7F8481FCCA975CL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISS32(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISS32").value
    r.put(0x4A7CA7754CBE3D58L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISS64(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISS64").value
    r.put(0x77518DCDFA6E753EL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISU8(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISU8").value
    r.put(0xD8EF413A59D0FDEBL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISU16(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISU16").value
    r.put(0xCC86356DA9BAE352L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISU32(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISU32").value
    r.put(0x15BDB54A71AE6C47L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseISU64(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseISU64").value
    r.put(0x7827579FA764A398L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSZ(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSZ").value
    r.put(0x41526DC054060956L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSZ8(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSZ8").value
    r.put(0xBD3F2C8814B4E239L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSZ16(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSZ16").value
    r.put(0x649539CC4819A986L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSZ32(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSZ32").value
    r.put(0x5920E78B75E63ED1L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSZ64(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSZ64").value
    r.put(0x0D40065D7044FE44L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSN(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSN").value
    r.put(0x7299B3A300E75DF4L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSN8(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSN8").value
    r.put(0x2F8F920D68F846EFL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSN16(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSN16").value
    r.put(0x97376B9F0B8C7F14L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSN32(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSN32").value
    r.put(0x23D550DDE7C674BDL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSN64(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSN64").value
    r.put(0x13801348F7BA9BD9L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSS8(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSS8").value
    r.put(0xFD012F8E68BC0BF2L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSS16(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSS16").value
    r.put(0x235F6CECC9B04997L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSS32(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSS32").value
    r.put(0x916BF0CF34424114L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSS64(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSS64").value
    r.put(0xE656E4E63B5ECAD2L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSU8(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSU8").value
    r.put(0x0613446AA7D28F38L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSU16(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSU16").value
    r.put(0x7EB9D84694322BEBL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSU32(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSU32").value
    r.put(0x10A16149426F9597L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMSU64(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMSU64").value
    r.put(0x328AFA9635B39407L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseOption(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseOption").value
    r.put(0xC580BB3BC7C1078FL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseMOption(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseMOption").value
    r.put(0x05ADA4FDEDA28201L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseSet(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseSet").value
    r.put(0x851D13F6F8F95EB5L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseHashSet(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseHashSet").value
    r.put(0x21F60E4D73305C6DL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseHashSSet(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseHashSSet").value
    r.put(0xDB5C231DA73EF276L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseStack(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseStack").value
    r.put(0x66277EA29D4E30EAL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseBag(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseBag").value
    r.put(0x9118398205012AC6L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseHashBag(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseHashBag").value
    r.put(0xA51B5C68E6B4AC6CL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseHashSBag(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseHashSBag").value
    r.put(0xFA08DCDCE07A9AE7L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parsePoset(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parsePoset").value
    r.put(0xBAE41018ADE45B50L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseUnionFind(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseUnionFind").value
    r.put(0xAFE993A878710DFEL, r => (o1: Any) => X[org.sireum.Json.Parser](r).at(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "at").value
    r.put(0xAE78A06E361B6741L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseObjectType(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseObjectType").value
    r.put(0x89E9C039BE69F5D4L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseObjectTypes(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseObjectTypes").value
    r.put(0xBB50AE4860490B86L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseObjectKey(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseObjectKey").value
    r.put(0x08E90248F807EAD4L, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseObjectKeys(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseObjectKeys").value
    r.put(0xBB33841067865BAEL, r => (o1: Any) => X[org.sireum.Json.Parser](r).parseConstant(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "parseConstant").value
    r.put(0x91F65BF4A31EFF50L, r => (o1: Any) => X[org.sireum.Json.Parser](r).computeLineColumn(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "computeLineColumn").value
    r.put(0xDE2273147470129BL, r => (o1: Any) => X[org.sireum.Json.Parser](r).errorIfEof(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "errorIfEof").value
    r.put(0x31B8815CCC30C582L, r => (o1: Any) => X[org.sireum.Json.Parser](r).incOffset(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "incOffset").value
    r.put(0x0D402D3FC57F9A7BL, r => (o1: Any) => X[org.sireum.Json.Parser](r).isDigit(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "isDigit").value
    r.put(0x9A2DE6EFE68F6D4EL, r => (o1: Any) => X[org.sireum.Json.Parser](r).isWhitespace(X(o1))) // methodKey(F, "org.sireum.Json.Parser", "isWhitespace").value
    r.put(0x10E6FED5D7EC3F59L, _ => (o1: Any) => org.sireum.MBox.apply(X(o1))) // methodKey(T, "org.sireum.MBox", "apply").value
    r.put(0xDF42214D89E9FB2DL, _ => (o1: Any) => org.sireum.MBox.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox", "unapply").value
    r.put(0x3F9E049682FE132CL, r => (o1: Any) => X[org.sireum.MBox[_]](r).`value_=`(X(o1))) // methodKey(F, "org.sireum.MBox", "value_=").value
    r.put(0x4380B1DC3E6004A2L, _ => (o1: Any) => org.sireum.MBox2.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox2", "unapply").value
    r.put(0x366206617A5D71D4L, r => (o1: Any) => X[org.sireum.MBox2[_, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox2", "value1_=").value
    r.put(0xB5B9B6C0175F4325L, r => (o1: Any) => X[org.sireum.MBox2[_, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox2", "value2_=").value
    r.put(0xB597FF5FACEECF68L, _ => (o1: Any) => org.sireum.MBox3.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox3", "unapply").value
    r.put(0x839E76F1B157869BL, r => (o1: Any) => X[org.sireum.MBox3[_, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox3", "value1_=").value
    r.put(0x2B8BB11A7C5A2878L, r => (o1: Any) => X[org.sireum.MBox3[_, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox3", "value2_=").value
    r.put(0xA1A1D81DE2521BB0L, r => (o1: Any) => X[org.sireum.MBox3[_, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox3", "value3_=").value
    r.put(0x638006497E6C44E9L, _ => (o1: Any) => org.sireum.MBox4.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3)) => MSome((o0, o1, o2, o3))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox4", "unapply").value
    r.put(0x3C563B50B7F54C1BL, r => (o1: Any) => X[org.sireum.MBox4[_, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox4", "value1_=").value
    r.put(0x3A60A48132B59A79L, r => (o1: Any) => X[org.sireum.MBox4[_, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox4", "value2_=").value
    r.put(0xB281B56867FE432BL, r => (o1: Any) => X[org.sireum.MBox4[_, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox4", "value3_=").value
    r.put(0x7722203028CFDDD6L, r => (o1: Any) => X[org.sireum.MBox4[_, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox4", "value4_=").value
    r.put(0x84A18D41CBFC5490L, _ => (o1: Any) => org.sireum.MBox5.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4)) => MSome((o0, o1, o2, o3, o4))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox5", "unapply").value
    r.put(0x192785A9481E7992L, r => (o1: Any) => X[org.sireum.MBox5[_, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox5", "value1_=").value
    r.put(0xE9045E4E664417BAL, r => (o1: Any) => X[org.sireum.MBox5[_, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox5", "value2_=").value
    r.put(0x8A3ECD6EB6F8DE93L, r => (o1: Any) => X[org.sireum.MBox5[_, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox5", "value3_=").value
    r.put(0x3928933399F50528L, r => (o1: Any) => X[org.sireum.MBox5[_, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox5", "value4_=").value
    r.put(0x8DA5CB7D6FB6AA85L, r => (o1: Any) => X[org.sireum.MBox5[_, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox5", "value5_=").value
    r.put(0xC45CE302317197E9L, _ => (o1: Any) => org.sireum.MBox6.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5)) => MSome((o0, o1, o2, o3, o4, o5))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox6", "unapply").value
    r.put(0x52013BF83AC0B4C5L, r => (o1: Any) => X[org.sireum.MBox6[_, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox6", "value1_=").value
    r.put(0x3B092D9A0FDDD126L, r => (o1: Any) => X[org.sireum.MBox6[_, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox6", "value2_=").value
    r.put(0x6FE9BA0E2073BEE6L, r => (o1: Any) => X[org.sireum.MBox6[_, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox6", "value3_=").value
    r.put(0x48F21FBA541F8AAFL, r => (o1: Any) => X[org.sireum.MBox6[_, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox6", "value4_=").value
    r.put(0x2EFA8BB7BCE82330L, r => (o1: Any) => X[org.sireum.MBox6[_, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox6", "value5_=").value
    r.put(0x49270AD9AEE2633FL, r => (o1: Any) => X[org.sireum.MBox6[_, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox6", "value6_=").value
    r.put(0x6BC644A5DAA7B5A5L, _ => (o1: Any) => org.sireum.MBox7.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6)) => MSome((o0, o1, o2, o3, o4, o5, o6))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox7", "unapply").value
    r.put(0x1D779094B9018851L, r => (o1: Any) => X[org.sireum.MBox7[_, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox7", "value1_=").value
    r.put(0xB2C17AEFE8EC4AC3L, r => (o1: Any) => X[org.sireum.MBox7[_, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox7", "value2_=").value
    r.put(0x94555BDEFFCB9DFCL, r => (o1: Any) => X[org.sireum.MBox7[_, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox7", "value3_=").value
    r.put(0xFA1D0539D139FF7EL, r => (o1: Any) => X[org.sireum.MBox7[_, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox7", "value4_=").value
    r.put(0xF7F87ACF387012C6L, r => (o1: Any) => X[org.sireum.MBox7[_, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox7", "value5_=").value
    r.put(0x784E80ACD4B494CEL, r => (o1: Any) => X[org.sireum.MBox7[_, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox7", "value6_=").value
    r.put(0xF4DD3CA2FB52C8C9L, r => (o1: Any) => X[org.sireum.MBox7[_, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox7", "value7_=").value
    r.put(0xF60FA9D4F630A710L, _ => (o1: Any) => org.sireum.MBox8.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox8", "unapply").value
    r.put(0xAEAFCACB1308B779L, r => (o1: Any) => X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox8", "value1_=").value
    r.put(0x8005A76695C49443L, r => (o1: Any) => X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox8", "value2_=").value
    r.put(0x24D85246197C7EC8L, r => (o1: Any) => X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox8", "value3_=").value
    r.put(0x92B8B0C27B90A2F3L, r => (o1: Any) => X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox8", "value4_=").value
    r.put(0xF36CBEC5C33D10A9L, r => (o1: Any) => X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox8", "value5_=").value
    r.put(0xBF9CF8B41F7629F9L, r => (o1: Any) => X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox8", "value6_=").value
    r.put(0xDF40F69AF135E066L, r => (o1: Any) => X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox8", "value7_=").value
    r.put(0xBE8AFFCB5DFDEA3AL, r => (o1: Any) => X[org.sireum.MBox8[_, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox8", "value8_=").value
    r.put(0x9D418F4104DC2A6AL, _ => (o1: Any) => org.sireum.MBox9.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox9", "unapply").value
    r.put(0x0BEB3DB7481AE745L, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value1_=").value
    r.put(0x97DCAC8CDB0D06CFL, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value2_=").value
    r.put(0xDB46C6DD4C7B0C7DL, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value3_=").value
    r.put(0xF5366114CB666C66L, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value4_=").value
    r.put(0xD5FEAE9FF5801124L, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value5_=").value
    r.put(0x8D6743417B957D92L, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value6_=").value
    r.put(0xCBCEAAD933379C6EL, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value7_=").value
    r.put(0x09488CD659FA787DL, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value8_=").value
    r.put(0x619768351FB9F112L, r => (o1: Any) => X[org.sireum.MBox9[_, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox9", "value9_=").value
    r.put(0xE15C4A615F1B3B18L, _ => (o1: Any) => org.sireum.MBox10.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox10", "unapply").value
    r.put(0xD7B06E3794AAA725L, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value1_=").value
    r.put(0xAC70B3F9A9FB97FCL, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value2_=").value
    r.put(0x57133E7DF76407BFL, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value3_=").value
    r.put(0xCC5BB5848413521DL, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value4_=").value
    r.put(0x04E500CEACBBA052L, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value5_=").value
    r.put(0x4407EB8AAD1E20F7L, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value6_=").value
    r.put(0xF5C3B0BCC98B453CL, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value7_=").value
    r.put(0xE3F0CF6D5043CCBCL, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value8_=").value
    r.put(0xC9A2FA0FD54EAD4DL, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value9_=").value
    r.put(0x25ECBFC29DF2C129L, r => (o1: Any) => X[org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox10", "value10_=").value
    r.put(0x3D6ECE8ED082B7BEL, _ => (o1: Any) => org.sireum.MBox11.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox11", "unapply").value
    r.put(0x7353679A1F00F4DBL, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value1_=").value
    r.put(0x2617A92B0D621456L, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value2_=").value
    r.put(0x9565F8440ADFA28BL, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value3_=").value
    r.put(0x2E371EFF14883642L, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value4_=").value
    r.put(0x2E512B778A0100A2L, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value5_=").value
    r.put(0xEE024DC9243DF1E6L, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value6_=").value
    r.put(0xDE6EB0D3988730E3L, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value7_=").value
    r.put(0xABE52D61C4B8225FL, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value8_=").value
    r.put(0xE5576D69301390D2L, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value9_=").value
    r.put(0xABA79404FA2C7468L, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value10_=").value
    r.put(0x2A7A0258ED200267L, r => (o1: Any) => X[org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox11", "value11_=").value
    r.put(0xC933C8C370DD2374L, _ => (o1: Any) => org.sireum.MBox12.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox12", "unapply").value
    r.put(0x9DDE92E3C01299E0L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value1_=").value
    r.put(0x266FAC60AC7E6D98L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value2_=").value
    r.put(0x4C8215DF51213AABL, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value3_=").value
    r.put(0x780264E3F7C6A629L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value4_=").value
    r.put(0x1027B3ABCB079D0BL, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value5_=").value
    r.put(0x9874F3144C4DD103L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value6_=").value
    r.put(0x3BA661BBF0F4D0CFL, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value7_=").value
    r.put(0xB94C23AA5848AC87L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value8_=").value
    r.put(0xD5F31B6C050426E8L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value9_=").value
    r.put(0x124283ACD1291AE1L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value10_=").value
    r.put(0x3E51F8CADCC77F53L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value11_=").value
    r.put(0x3205AB5C9C943143L, r => (o1: Any) => X[org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox12", "value12_=").value
    r.put(0x2D384C7B89C9692FL, _ => (o1: Any) => org.sireum.MBox13.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox13", "unapply").value
    r.put(0x3F16FD8A12B00569L, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value1_=").value
    r.put(0x661BDC686EF85B97L, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value2_=").value
    r.put(0x081C1195912D3E3EL, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value3_=").value
    r.put(0xB45C7A23CB6C2051L, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value4_=").value
    r.put(0x7C42C9F3F1685199L, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value5_=").value
    r.put(0x433310604012492FL, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value6_=").value
    r.put(0x2724F960BA553C29L, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value7_=").value
    r.put(0xD532CCE1096DF91CL, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value8_=").value
    r.put(0xE4ABD509BA3294CFL, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value9_=").value
    r.put(0x12444FD25B4B3200L, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value10_=").value
    r.put(0x9A11854FF4D6B9CFL, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value11_=").value
    r.put(0xF8C3F4FDD8F635A2L, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value12_=").value
    r.put(0x329A0DDD19FA331BL, r => (o1: Any) => X[org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox13", "value13_=").value
    r.put(0x32D444222F52CDCFL, _ => (o1: Any) => org.sireum.MBox14.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox14", "unapply").value
    r.put(0x6D43230182921729L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value1_=").value
    r.put(0x6D9BA241BF0B17C0L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value2_=").value
    r.put(0x7EA5101AAA9F9772L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value3_=").value
    r.put(0xC5DFE5421B19A081L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value4_=").value
    r.put(0xC152DFAC34AE310BL, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value5_=").value
    r.put(0x755062B9163B5214L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value6_=").value
    r.put(0x470D012E8D00E8B1L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value7_=").value
    r.put(0xEF3C9CE967E44336L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value8_=").value
    r.put(0xDCFCCCF911C6541AL, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value9_=").value
    r.put(0xEAC74E284CF7E5A6L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value10_=").value
    r.put(0xF63405D1286D63A0L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value11_=").value
    r.put(0xD8320579AD4A8D21L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value12_=").value
    r.put(0x436FDC6099043842L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value13_=").value
    r.put(0x01B01BEFCC891DB3L, r => (o1: Any) => X[org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox14", "value14_=").value
    r.put(0xCE24E34145C0C273L, _ => (o1: Any) => org.sireum.MBox15.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox15", "unapply").value
    r.put(0x7CCD5169B560D961L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value1_=").value
    r.put(0x85397E3143B4F8E7L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value2_=").value
    r.put(0xD7CF78B820505717L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value3_=").value
    r.put(0xB16DC2B0B450C929L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value4_=").value
    r.put(0xD99F8BAAE9CBFE75L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value5_=").value
    r.put(0x9CB859D090368535L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value6_=").value
    r.put(0x1DD42DD3CD0C4B84L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value7_=").value
    r.put(0x2A55688561F4A922L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value8_=").value
    r.put(0x2C38657D5231B701L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value9_=").value
    r.put(0xB9A56806E81DE0E2L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value10_=").value
    r.put(0x594A02AC8716817BL, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value11_=").value
    r.put(0x5853ACD6B46B6A99L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value12_=").value
    r.put(0x9433D3FF2F343226L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value13_=").value
    r.put(0x5915E21AA6DCDFC9L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value14_=").value
    r.put(0xCAC72EEBD6BED640L, r => (o1: Any) => X[org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value15_=`(X(o1))) // methodKey(F, "org.sireum.MBox15", "value15_=").value
    r.put(0x8032B7C9EAB0DD2CL, _ => (o1: Any) => org.sireum.MBox16.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox16", "unapply").value
    r.put(0xFA150D13F2C94D58L, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value1_=").value
    r.put(0x6EE5CFB8CD4736F9L, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value2_=").value
    r.put(0x244DF646543BE54CL, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value3_=").value
    r.put(0x133AD430DA66735BL, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value4_=").value
    r.put(0x7C2860FAE7ACC117L, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value5_=").value
    r.put(0x82DFA6828B071D7CL, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value6_=").value
    r.put(0xC8EDCB512CBB1A5DL, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value7_=").value
    r.put(0xD0D077DB982C9752L, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value8_=").value
    r.put(0x2167AA02601B9F3AL, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value9_=").value
    r.put(0x2676B0C99AC651CBL, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value10_=").value
    r.put(0xD0F7CD6118E09693L, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value11_=").value
    r.put(0xF05A0D95C409FC57L, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value12_=").value
    r.put(0xA93F253F6BD473A7L, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value13_=").value
    r.put(0x52B4E422323A21CEL, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value14_=").value
    r.put(0x32F936B47473496AL, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value15_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value15_=").value
    r.put(0x5F385A6D3C0A8322L, r => (o1: Any) => X[org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value16_=`(X(o1))) // methodKey(F, "org.sireum.MBox16", "value16_=").value
    r.put(0xB4530560FFA83DF7L, _ => (o1: Any) => org.sireum.MBox17.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox17", "unapply").value
    r.put(0xA7E9365FA9C3EBB2L, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value1_=").value
    r.put(0xFA2E2A3D273514E9L, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value2_=").value
    r.put(0x060269A271959A4CL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value3_=").value
    r.put(0x3F47D154F993689FL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value4_=").value
    r.put(0xF56A2B6CD9BCD7C2L, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value5_=").value
    r.put(0xCE2CF08D9BDDB94EL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value6_=").value
    r.put(0x05EAAAE42E5483BDL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value7_=").value
    r.put(0x0957DE444F515017L, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value8_=").value
    r.put(0x41B70F8E19769A8BL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value9_=").value
    r.put(0x8828F73D5582AA26L, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value10_=").value
    r.put(0xCAFDE1702610528DL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value11_=").value
    r.put(0xA16FB7569A08EA94L, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value12_=").value
    r.put(0xB60E378DDAD7777CL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value13_=").value
    r.put(0xCF5AB6740CFFB48CL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value14_=").value
    r.put(0xBDCC54D593111987L, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value15_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value15_=").value
    r.put(0x9B1DEE62974E527EL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value16_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value16_=").value
    r.put(0x39E9D8F5059A64DCL, r => (o1: Any) => X[org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value17_=`(X(o1))) // methodKey(F, "org.sireum.MBox17", "value17_=").value
    r.put(0xAD859AD1EFA30E47L, _ => (o1: Any) => org.sireum.MBox18.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox18", "unapply").value
    r.put(0x090D7781C005C3A9L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value1_=").value
    r.put(0x0F5E5D06B40F96FAL, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value2_=").value
    r.put(0x0C975BAF470429BAL, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value3_=").value
    r.put(0x12E22DA1E261880BL, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value4_=").value
    r.put(0xEBDEB9A762DE8196L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value5_=").value
    r.put(0x926C4224C67B5C03L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value6_=").value
    r.put(0xC721D0EA5A0588B9L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value7_=").value
    r.put(0xAC08B572FA4D27D6L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value8_=").value
    r.put(0x333763F80A856EFBL, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value9_=").value
    r.put(0xCD63253A8E2549A0L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value10_=").value
    r.put(0x14B8EDB242B43A0AL, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value11_=").value
    r.put(0xC44237395A8987FBL, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value12_=").value
    r.put(0x0F9B65C0903D8E29L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value13_=").value
    r.put(0x2569A911B9641A69L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value14_=").value
    r.put(0xDE8C7A08EC204C79L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value15_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value15_=").value
    r.put(0xAE6B8A07BDBB1F29L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value16_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value16_=").value
    r.put(0xF041528747472440L, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value17_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value17_=").value
    r.put(0xC44C8FE951FB79CCL, r => (o1: Any) => X[org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value18_=`(X(o1))) // methodKey(F, "org.sireum.MBox18", "value18_=").value
    r.put(0xE7CBCACEACB00E4CL, _ => (o1: Any) => org.sireum.MBox19.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox19", "unapply").value
    r.put(0x0F8FA3C9A8B12AF7L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value1_=").value
    r.put(0x5653F40D3506D8B4L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value2_=").value
    r.put(0x66E81DDA25E9F581L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value3_=").value
    r.put(0x767C3F73EEE61BFFL, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value4_=").value
    r.put(0x91013A854CBB91C8L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value5_=").value
    r.put(0x485C4F31A59681A8L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value6_=").value
    r.put(0x4899F897D834D7ECL, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value7_=").value
    r.put(0x4D2C663A1A03E838L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value8_=").value
    r.put(0x133D11BAD10A1D97L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value9_=").value
    r.put(0x6F24CC2A2FEC7707L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value10_=").value
    r.put(0xE782308EAD93CC95L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value11_=").value
    r.put(0x2AD7F4F0375491EBL, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value12_=").value
    r.put(0xB4069014F8380749L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value13_=").value
    r.put(0x52B1B83BC692CB92L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value14_=").value
    r.put(0x9EFE73B92B0B6FC6L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value15_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value15_=").value
    r.put(0x03B5FBEE3C103E4EL, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value16_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value16_=").value
    r.put(0x28CE6B25A230252DL, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value17_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value17_=").value
    r.put(0xDB99F2E3693E02ADL, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value18_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value18_=").value
    r.put(0x438BA5580CFCDF54L, r => (o1: Any) => X[org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value19_=`(X(o1))) // methodKey(F, "org.sireum.MBox19", "value19_=").value
    r.put(0xC3C512383AA9AF6BL, _ => (o1: Any) => org.sireum.MBox20.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox20", "unapply").value
    r.put(0xE8019935B589C7D8L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value1_=").value
    r.put(0x541D332CDBBD6118L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value2_=").value
    r.put(0xFC651A798034C3BFL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value3_=").value
    r.put(0x64E64CBCDE72FE90L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value4_=").value
    r.put(0xE776FBD4A5200360L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value5_=").value
    r.put(0x028A1127C1D5863BL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value6_=").value
    r.put(0x8DB7C47659DE6202L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value7_=").value
    r.put(0x7B3F1BA3CB5F8D75L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value8_=").value
    r.put(0x59FA8626555F142DL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value9_=").value
    r.put(0x70E185D24AB5B4EAL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value10_=").value
    r.put(0xAE621230B0CD531DL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value11_=").value
    r.put(0x84B9FD7FD29740F8L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value12_=").value
    r.put(0x0C9E1AA95FE1473BL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value13_=").value
    r.put(0x95935ED8A6483771L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value14_=").value
    r.put(0xA37FCFB62E3BEFFEL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value15_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value15_=").value
    r.put(0xEF1CAD835B9F64A9L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value16_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value16_=").value
    r.put(0x8341ED717D341DDEL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value17_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value17_=").value
    r.put(0x926E15E11D90DB10L, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value18_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value18_=").value
    r.put(0xF9303072FB7E7F3DL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value19_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value19_=").value
    r.put(0x1DA6142576743FFEL, r => (o1: Any) => X[org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value20_=`(X(o1))) // methodKey(F, "org.sireum.MBox20", "value20_=").value
    r.put(0x04FBFBCEC1BAAA1DL, _ => (o1: Any) => org.sireum.MBox21.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox21", "unapply").value
    r.put(0x9CDBD87ED22F242CL, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value1_=").value
    r.put(0x9E4B9F2E7037AE36L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value2_=").value
    r.put(0xC0DD8AF42EB1C64CL, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value3_=").value
    r.put(0xBD2072EDDAA2AA07L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value4_=").value
    r.put(0x52CE055B98055655L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value5_=").value
    r.put(0xBA64BAE360FB2CBBL, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value6_=").value
    r.put(0x244F9E50CB47EA60L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value7_=").value
    r.put(0x0E17DD70B7CE1963L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value8_=").value
    r.put(0x233E30EF8DFEF4BBL, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value9_=").value
    r.put(0x4D38C1BE76CCE7D0L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value10_=").value
    r.put(0x9E83BB1542F7917EL, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value11_=").value
    r.put(0xE81B93F89C509CBBL, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value12_=").value
    r.put(0x5C098CE2795255E9L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value13_=").value
    r.put(0x82080F82FA0DB180L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value14_=").value
    r.put(0x7A1D7B936B6E28A5L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value15_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value15_=").value
    r.put(0x10CCD1F7F2F8DF9DL, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value16_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value16_=").value
    r.put(0x1D78A544A7368BE0L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value17_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value17_=").value
    r.put(0x9BD17ACFF0668870L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value18_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value18_=").value
    r.put(0xD023633A40A8892DL, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value19_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value19_=").value
    r.put(0x37C95BE61C5BA9A8L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value20_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value20_=").value
    r.put(0xA636C9E7303A26E5L, r => (o1: Any) => X[org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value21_=`(X(o1))) // methodKey(F, "org.sireum.MBox21", "value21_=").value
    r.put(0x12DBC75AFD510AB1L, _ => (o1: Any) => org.sireum.MBox22.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14, o15, o16, o17, o18, o19, o20, o21))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MBox22", "unapply").value
    r.put(0xFF903F4250F295C7L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value1_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value1_=").value
    r.put(0x0D9F5D3CFABC4DB3L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value2_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value2_=").value
    r.put(0x24936296286D256AL, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value3_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value3_=").value
    r.put(0xEA08307CCCFC66F4L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value4_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value4_=").value
    r.put(0x6AE86BA70F89198AL, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value5_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value5_=").value
    r.put(0xA0128FC5D077A17AL, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value6_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value6_=").value
    r.put(0xC1EB86C62FA682FAL, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value7_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value7_=").value
    r.put(0xE1834D894BE83805L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value8_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value8_=").value
    r.put(0x31BDF12637115FBDL, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value9_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value9_=").value
    r.put(0x10FA4D207B1A663FL, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value10_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value10_=").value
    r.put(0x043DED22133C4837L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value11_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value11_=").value
    r.put(0x59AF02CB5B6FBE86L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value12_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value12_=").value
    r.put(0x147DD616E6B70D21L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value13_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value13_=").value
    r.put(0xF5084307193E7258L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value14_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value14_=").value
    r.put(0xBA2FAFD998115D05L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value15_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value15_=").value
    r.put(0x9E6A698EC97743D6L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value16_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value16_=").value
    r.put(0x2EDE7977AE4C55CBL, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value17_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value17_=").value
    r.put(0x833F6CE015E37555L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value18_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value18_=").value
    r.put(0xA05EEB9F935AA3EEL, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value19_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value19_=").value
    r.put(0xD6297F381542BF92L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value20_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value20_=").value
    r.put(0x8587197B2FD25C92L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value21_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value21_=").value
    r.put(0x44C002E249EC24A4L, r => (o1: Any) => X[org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]](r).`value22_=`(X(o1))) // methodKey(F, "org.sireum.MBox22", "value22_=").value
    r.put(0xAE1BDA7620D87456L, _ => (o1: Any) => org.sireum.MEither.Left.apply(X(o1))) // methodKey(T, "org.sireum.MEither.Left", "apply").value
    r.put(0xE9B3AF3490A7C8D6L, _ => (o1: Any) => org.sireum.MEither.Left.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MEither.Left", "unapply").value
    r.put(0x07293F91A03D569BL, _ => (o1: Any) => org.sireum.MEither.Right.apply(X(o1))) // methodKey(T, "org.sireum.MEither.Right", "apply").value
    r.put(0x4BFDB2677A12F1AFL, _ => (o1: Any) => org.sireum.MEither.Right.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MEither.Right", "unapply").value
    r.put(0x1D68AB3DDC2149F9L, r => (o1: Any) => X[org.sireum.MJen[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen", "generate").value
    r.put(0x5757E3A8229B845BL, r => (o1: Any) => X[org.sireum.MJen[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen", "foreach").value
    r.put(0x7FDC27E72E445171L, r => (o1: Any) => X[org.sireum.MJen[_]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen", "find").value
    r.put(0x7C0DD08B18166304L, r => (o1: Any) => X[org.sireum.MJen[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen", "exists").value
    r.put(0x6FF4F1347F7290CAL, r => (o1: Any) => X[org.sireum.MJen[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen", "contains").value
    r.put(0x4DF2D866B6F591D3L, r => (o1: Any) => X[org.sireum.MJen[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen", "forall").value
    r.put(0x90BA1557BAC3D921L, r => (o1: Any) => X[org.sireum.MJen[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen", "countIf").value
    r.put(0x135D42CCD7B43473L, r => (o1: Any) => X[org.sireum.MJen[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen", "reduce").value
    r.put(0x935CCB32D63CBDC1L, r => (o1: Any) => X[org.sireum.MJen[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen", "reduceLeft").value
    r.put(0xD187D28E30BC3B1DL, r => (o1: Any) => X[org.sireum.MJen[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen", "filter").value
    r.put(0xB0146683EC7A0C15L, r => (o1: Any) => X[org.sireum.MJen[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen", "withFilter").value
    r.put(0xB818E68F1B1BAB54L, r => (o1: Any) => X[org.sireum.MJen[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen", "map").value
    r.put(0xEEE628345643D997L, r => (o1: Any) => X[org.sireum.MJen[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen", "flatMap").value
    r.put(0x8E9159B484E12778L, r => (o1: Any) => X[org.sireum.MJen[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen", "flatten").value
    r.put(0x3ADB29EA417F191BL, r => (o1: Any) => X[org.sireum.MJen[_]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen", "take").value
    r.put(0xBB0C6EA5729BE049L, r => (o1: Any) => X[org.sireum.MJen[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen", "drop").value
    r.put(0x4342B43B5C4B6AA3L, r => (o1: Any) => X[org.sireum.MJen[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen", "takeWhile").value
    r.put(0x26C89FB00C8B5B4BL, r => (o1: Any) => X[org.sireum.MJen[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen", "dropWhile").value
    r.put(0x834E0DFA340D1A84L, r => (o1: Any) => X[org.sireum.MJen[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen", "zip").value
    r.put(0xDB7A0BEA84AAC3F0L, r => (o1: Any) => X[org.sireum.MJen[_]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen", "product").value
    r.put(0x9936DD47C3136BFCL, r => (o1: Any) => X[org.sireum.MJen[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen", "++").value
    r.put(0x9CBF401D1965FD82L, r => (o1: Any) => X[org.sireum.MJen[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen", "toMS").value
    r.put(0xF8ECA94EC0BC2578L, r => (o1: Any) => X[org.sireum.MJen[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen", "mkString").value
    r.put(0x05589D4775A6D726L, _ => (o1: Any) => org.sireum.MJen.Internal.ISImpl.apply(X(o1))) // methodKey(T, "org.sireum.MJen.Internal.ISImpl", "apply").value
    r.put(0xF909F2169D6F94BEL, _ => (o1: Any) => org.sireum.MJen.Internal.ISImpl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.ISImpl", "unapply").value
    r.put(0x032CE3BEA77AB9B0L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "generate").value
    r.put(0x6B6AB8B334D09E9CL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "foreach").value
    r.put(0x4E9664BEA67240EFL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "find").value
    r.put(0x0146704EB9EA850FL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "exists").value
    r.put(0x69FCD398AB915DE5L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "contains").value
    r.put(0x507E41C33F6D745CL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "forall").value
    r.put(0x8B5B3B0EE88904FDL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "countIf").value
    r.put(0xECB771C3B566964FL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "reduce").value
    r.put(0x63C086A6EA649FEFL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "reduceLeft").value
    r.put(0xC0F5B591A5BC2DD7L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "filter").value
    r.put(0x0871BB690918019BL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "withFilter").value
    r.put(0xB8F35C1D83F4648CL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "map").value
    r.put(0x0DC8093BA5087A20L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "flatMap").value
    r.put(0x71AF1887B1CC80B5L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "flatten").value
    r.put(0xF9F700BAFBDF697EL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "take").value
    r.put(0x48CBE1D00DB85F50L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "drop").value
    r.put(0xC2B1448C2F96519CL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "takeWhile").value
    r.put(0xAB906C28187D9E7FL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "dropWhile").value
    r.put(0xB041C94754E1411EL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "zip").value
    r.put(0x29CB2704A4EADF80L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "product").value
    r.put(0x94B8BE908890883DL, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "++").value
    r.put(0x3781BAD4F478C936L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "toMS").value
    r.put(0x99A67283CFBE0E27L, r => (o1: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "mkString").value
    r.put(0x84F6AE1C7EDA1E97L, _ => (o1: Any) => org.sireum.MJen.Internal.MSImpl.apply(X(o1))) // methodKey(T, "org.sireum.MJen.Internal.MSImpl", "apply").value
    r.put(0x37DAD371250A2160L, _ => (o1: Any) => org.sireum.MJen.Internal.MSImpl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.MSImpl", "unapply").value
    r.put(0x45B56324A199F666L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "generate").value
    r.put(0xAE29242A44EE39B8L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "foreach").value
    r.put(0x9FC8F20D5D9C3E13L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "find").value
    r.put(0x717B9F3D2CDD7F50L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "exists").value
    r.put(0xC72504274B7D8E68L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "contains").value
    r.put(0x78540B9F42704CA1L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "forall").value
    r.put(0x9EFD1A3BB1022CD9L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "countIf").value
    r.put(0x6FC0EC54557BC2E9L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "reduce").value
    r.put(0xBFF57C0097625E64L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "reduceLeft").value
    r.put(0xE833F151253EE926L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "filter").value
    r.put(0x5F75FC09B656CF0CL, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "withFilter").value
    r.put(0x0A3CB512A36AD59FL, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "map").value
    r.put(0xACEB43BFAF59D707L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "flatMap").value
    r.put(0x8E9363C419D17EF0L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "flatten").value
    r.put(0x88A2D9098A98204FL, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "take").value
    r.put(0xAC823DCB7BB9FEFEL, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "drop").value
    r.put(0x6400001D49C4BCF2L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "takeWhile").value
    r.put(0x89C3C089C0968C4DL, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "dropWhile").value
    r.put(0x7EAF6430E747D473L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "zip").value
    r.put(0x6030213F9743229DL, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "product").value
    r.put(0xCD69825AA8D434C3L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "++").value
    r.put(0x4D4CD8E67895BB50L, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "toMS").value
    r.put(0xDE133E7AACCBBDFEL, r => (o1: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "mkString").value
    r.put(0xF69DE3F744017E3AL, _ => (o1: Any) => org.sireum.MJen.Internal.MapImpl.apply(X(o1))) // methodKey(T, "org.sireum.MJen.Internal.MapImpl", "apply").value
    r.put(0xF95969283346BCBAL, _ => (o1: Any) => org.sireum.MJen.Internal.MapImpl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.MapImpl", "unapply").value
    r.put(0xD68CC0DE13291756L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "generate").value
    r.put(0x8C406ABCB4C8F9CDL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "foreach").value
    r.put(0xD4042E67248B9E6DL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "find").value
    r.put(0x647DA97991295675L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "exists").value
    r.put(0xB3EDB54C4104617AL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "contains").value
    r.put(0x360B6ADDB7F3BB11L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "forall").value
    r.put(0x1117A64D6E84487DL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "countIf").value
    r.put(0xFBF67A62871799D5L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "reduce").value
    r.put(0xEEB43EE5FF377A2FL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "reduceLeft").value
    r.put(0x7F159CB7825B0E41L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "filter").value
    r.put(0x4E27EE2165647567L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "withFilter").value
    r.put(0x9F64944BF4352DFBL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "map").value
    r.put(0x270BE98EC7DDB33EL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "flatMap").value
    r.put(0xADC04E4B0655A295L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "flatten").value
    r.put(0x1C703E2D0B412032L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "take").value
    r.put(0xB682D4D786D9F13AL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "drop").value
    r.put(0xA63352E02538B296L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "takeWhile").value
    r.put(0x14208078508A46E4L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "dropWhile").value
    r.put(0x1B8398BF5A743735L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "zip").value
    r.put(0xD58EFE0977636DC0L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "product").value
    r.put(0xE32C401FEE44685DL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "++").value
    r.put(0x767E5E4476CDA2FEL, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "toMS").value
    r.put(0xE0E57FE1584F4BC9L, r => (o1: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "mkString").value
    r.put(0x53158A8A7AC69B71L, _ => (o1: Any) => org.sireum.MJen.Internal.HashMapImpl.apply(X(o1))) // methodKey(T, "org.sireum.MJen.Internal.HashMapImpl", "apply").value
    r.put(0x247118E2F2C9C499L, _ => (o1: Any) => org.sireum.MJen.Internal.HashMapImpl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.HashMapImpl", "unapply").value
    r.put(0xA88174ABC846EA80L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "generate").value
    r.put(0x8C7216D49BDCA9FCL, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "foreach").value
    r.put(0x7D204ACC732226D5L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "find").value
    r.put(0x37634F2D158155B9L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "exists").value
    r.put(0xD9700B5FDD1FBFE6L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "contains").value
    r.put(0xFCC75E9553F96B46L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "forall").value
    r.put(0x9F3413A3740C7B2AL, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "countIf").value
    r.put(0x44F74FDB185E5F83L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "reduce").value
    r.put(0x37C23C6CCA7DC964L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "reduceLeft").value
    r.put(0xF5AC6F85FDFE2345L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "filter").value
    r.put(0x86D9C4E2D8237A23L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "withFilter").value
    r.put(0x75635EDB8BC938ADL, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "map").value
    r.put(0xB51178C60121CF82L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "flatMap").value
    r.put(0xEFD553DA32028422L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "flatten").value
    r.put(0x4F6FEDF2350259C4L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "take").value
    r.put(0xA4E18B33B6572DD3L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "drop").value
    r.put(0xB97C94F9204A9638L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "takeWhile").value
    r.put(0x37C1391B1E18D689L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "dropWhile").value
    r.put(0xC046F31BE5B4C787L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "zip").value
    r.put(0x08B69DA1BEA3A84AL, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "product").value
    r.put(0xE78DDE1D0CB12B29L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "++").value
    r.put(0xADDA902D459832D0L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "toMS").value
    r.put(0xAAE8DA7199A48B11L, r => (o1: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "mkString").value
    r.put(0x3EA3C633194E993BL, _ => (o1: Any) => org.sireum.MJen.Internal.Filtered.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.Filtered", "unapply").value
    r.put(0xCEBA0C174E8108E3L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "generate").value
    r.put(0x5B16126D8999FF81L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "foreach").value
    r.put(0x04F79E066530FF5AL, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "find").value
    r.put(0xA6D3CFD4AF06C8F2L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "exists").value
    r.put(0xB9FE46916ED78381L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "contains").value
    r.put(0xCBF02236C553E1F2L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "forall").value
    r.put(0x5E3BDB1849774C91L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "countIf").value
    r.put(0x54BE225175DCA86FL, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "reduce").value
    r.put(0x79BA04DAC9BC9660L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "reduceLeft").value
    r.put(0xAB625CACB9916707L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "filter").value
    r.put(0xB21F734F104E15C0L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "withFilter").value
    r.put(0x55D9C61143E54D88L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "map").value
    r.put(0x5BE2E147E989980CL, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "flatMap").value
    r.put(0x354362B35DA85EF5L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "flatten").value
    r.put(0xE2B22CAFB076E692L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "take").value
    r.put(0x57B21287A2A5660DL, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "drop").value
    r.put(0x8F28A6AE84DD1478L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "takeWhile").value
    r.put(0x9E2EBB159AAE9EF0L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "dropWhile").value
    r.put(0xC08A6EEFA7262C14L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "zip").value
    r.put(0x1F5848E26D37842EL, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "product").value
    r.put(0x52CA7C8D588F9099L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "++").value
    r.put(0x1F5E0D52912E8FB8L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "toMS").value
    r.put(0xFF13F00BB7F2E722L, r => (o1: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "mkString").value
    r.put(0x46D2B9309B670192L, _ => (o1: Any) => org.sireum.MJen.Internal.Mapped.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.Mapped", "unapply").value
    r.put(0xB727537692A5C708L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "generate").value
    r.put(0x951C1485C09334D5L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "foreach").value
    r.put(0x6D26EDC9402F7404L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "find").value
    r.put(0x6E0BC546140D9B21L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "exists").value
    r.put(0xDD07C1C0ECD6EF31L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "contains").value
    r.put(0x8C67BE2144BDA1ADL, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "forall").value
    r.put(0x82D8BF8282CD9DAAL, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "countIf").value
    r.put(0x670DECDB469CFCF3L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "reduce").value
    r.put(0x16BA2FA6093A57EBL, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "reduceLeft").value
    r.put(0x76A5A7674DD5A0CBL, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "filter").value
    r.put(0xF01FF38E6E3E64F6L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "withFilter").value
    r.put(0xB5DD85DD8B204642L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "map").value
    r.put(0x857AF1693E3B17E3L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "flatMap").value
    r.put(0x9AAB29C5D7C090E9L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "flatten").value
    r.put(0x4189FA7718632EF6L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "take").value
    r.put(0x9D8DA775BC38FBD3L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "drop").value
    r.put(0xB7FC0358C913E343L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "takeWhile").value
    r.put(0x399D328DFBDB024DL, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "dropWhile").value
    r.put(0x51B31E2510DF69F8L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "zip").value
    r.put(0x5DFD6A063BC44C14L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "product").value
    r.put(0xC714D6C8281B8E67L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "++").value
    r.put(0x9C4376AAF2FE2976L, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "toMS").value
    r.put(0x19CA23FDF439F84EL, r => (o1: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "mkString").value
    r.put(0x1AB6CE14DD5BD0FBL, _ => (o1: Any) => org.sireum.MJen.Internal.FlatMapped.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.FlatMapped", "unapply").value
    r.put(0xA5836E52FE39A747L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "generate").value
    r.put(0x4CBFC777EB905C17L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "foreach").value
    r.put(0x9908B502353FF5EAL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "find").value
    r.put(0x8A13626ED4E9B22DL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "exists").value
    r.put(0xC1615D0E6C023C4EL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "contains").value
    r.put(0xB82E43E990210C46L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "forall").value
    r.put(0x3F492F424234210CL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "countIf").value
    r.put(0xB937AE9C05376185L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "reduce").value
    r.put(0xFAD11F68AAA49B92L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "reduceLeft").value
    r.put(0xFFC57223BAA50672L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "filter").value
    r.put(0xA07157293550D58AL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "withFilter").value
    r.put(0x269BDAFC6C61ABFEL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "map").value
    r.put(0xC3A5E0726DAF29E3L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "flatMap").value
    r.put(0x9D87203B63017496L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "flatten").value
    r.put(0x790B96A8F3221716L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "take").value
    r.put(0x72F441DBEADD79CFL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "drop").value
    r.put(0xBD19D514F4D0784BL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "takeWhile").value
    r.put(0x6A9B2D79C8F59237L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "dropWhile").value
    r.put(0x273C72CD90103E4EL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "zip").value
    r.put(0x0BB60073C6B19188L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "product").value
    r.put(0x299E92C4E36FB51AL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "++").value
    r.put(0x418919F736BA8F89L, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "toMS").value
    r.put(0x094D29E0E7C8CEDDL, r => (o1: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "mkString").value
    r.put(0xC01F40AF5C8E4E72L, _ => (o1: Any) => org.sireum.MJen.Internal.Sliced.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.Sliced", "unapply").value
    r.put(0x9C89C731FC80A7FDL, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "generate").value
    r.put(0x90E52F056A570C17L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "foreach").value
    r.put(0x2EA541622C19627AL, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "find").value
    r.put(0x332DACC6D34D5BE4L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "exists").value
    r.put(0x74795DD79F866B86L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "contains").value
    r.put(0x3E2584D3DB07E034L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "forall").value
    r.put(0xA7F99FD361C05500L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "countIf").value
    r.put(0x2C64FB26875F82C2L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "reduce").value
    r.put(0xF6BD1345199FA185L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "reduceLeft").value
    r.put(0xA7D72469E0315D9BL, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "filter").value
    r.put(0x5EEA5FBFE6DF98E4L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "withFilter").value
    r.put(0x97DEFC1BEF572057L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "map").value
    r.put(0x17906C5D3FF9AAFDL, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "flatMap").value
    r.put(0xDF0A240EF847149DL, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "flatten").value
    r.put(0xE3C583C557833526L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "take").value
    r.put(0x50A44585EACC1103L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "drop").value
    r.put(0xED06C0EECF9119F0L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "takeWhile").value
    r.put(0xAB55C8183E4E70B9L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "dropWhile").value
    r.put(0xA36B35900319CE5BL, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "zip").value
    r.put(0x4D39601484001DBEL, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "product").value
    r.put(0xECA1D7A4CB77C831L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "++").value
    r.put(0x1BA3FDD7DB8A9F9FL, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "toMS").value
    r.put(0x8CFA2E8911E546D9L, r => (o1: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "mkString").value
    r.put(0x03F0895E774A6B1FL, _ => (o1: Any) => org.sireum.MJen.Internal.TakeWhile.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.TakeWhile", "unapply").value
    r.put(0x506B61EB25789625L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "generate").value
    r.put(0x61DE0175AC1EB7BAL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "foreach").value
    r.put(0x0570AD1B0695EB2FL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "find").value
    r.put(0x5A3C626722B8880DL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "exists").value
    r.put(0x525F074C813DDB2BL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "contains").value
    r.put(0xB6B3924B2FA183C5L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "forall").value
    r.put(0xD4B05BF8F891D24CL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "countIf").value
    r.put(0x2E1D478EDA652834L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "reduce").value
    r.put(0x2A0B65E14D8333D7L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "reduceLeft").value
    r.put(0xBFA8E6BD1FAF4D60L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "filter").value
    r.put(0x00C980454A206617L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "withFilter").value
    r.put(0x50A3850F32B27CE6L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "map").value
    r.put(0xE39061246AE6579AL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "flatMap").value
    r.put(0x82607C853BEAF13DL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "flatten").value
    r.put(0xD3456EB33E351576L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "take").value
    r.put(0x508635A1DE2A4DE4L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "drop").value
    r.put(0x7D7BB13D79ED391BL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "takeWhile").value
    r.put(0x359E1868A5085A30L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "dropWhile").value
    r.put(0xB12A9F5007965226L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "zip").value
    r.put(0x433A08CBC04D674FL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "product").value
    r.put(0x43253C70D5A1013CL, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "++").value
    r.put(0x288E64F501B8DFF7L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "toMS").value
    r.put(0xF446CEE9672EC329L, r => (o1: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "mkString").value
    r.put(0x4214D7BD7A92B593L, _ => (o1: Any) => org.sireum.MJen.Internal.DropWhile.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.DropWhile", "unapply").value
    r.put(0x90E03A8561991A81L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "generate").value
    r.put(0x6A119623DEB06CA7L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "foreach").value
    r.put(0x3AC4EE1CE7CD5CB0L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "find").value
    r.put(0x3DF301F459073450L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "exists").value
    r.put(0xD1B79D02132FBE00L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "contains").value
    r.put(0x651EECF803DD423DL, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "forall").value
    r.put(0xEE166286ED855931L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "countIf").value
    r.put(0x5C18285D153E6DC9L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "reduce").value
    r.put(0x3F53BDEA02C51C95L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "reduceLeft").value
    r.put(0xA1114CF379124C91L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "filter").value
    r.put(0x974D5D039537C6F0L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "withFilter").value
    r.put(0x5688DB0F04BAA65CL, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "map").value
    r.put(0xEFF9A50435377B5EL, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "flatMap").value
    r.put(0xC6AE9DF145797BD9L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "flatten").value
    r.put(0x168AC40DCF49B69CL, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "take").value
    r.put(0x95C10E3E2BC55EBEL, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "drop").value
    r.put(0xC52ED986B63A8942L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "takeWhile").value
    r.put(0xB5E40F29EBD0FDBDL, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "dropWhile").value
    r.put(0x84FF2B22239B7A56L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "zip").value
    r.put(0xD3800A4267A81D06L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "product").value
    r.put(0x3C4A562F5F0F82E1L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "++").value
    r.put(0xFD9735D8CEF5BAA8L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "toMS").value
    r.put(0x8022DEDA87C4BEC8L, r => (o1: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "mkString").value
    r.put(0x619FFF6FCC290B35L, _ => (o1: Any) => org.sireum.MJen.Internal.ZipWithIndexed.apply(X(o1))) // methodKey(T, "org.sireum.MJen.Internal.ZipWithIndexed", "apply").value
    r.put(0x1926C09AD919F5E9L, _ => (o1: Any) => org.sireum.MJen.Internal.ZipWithIndexed.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.ZipWithIndexed", "unapply").value
    r.put(0xBB468AB194D617A6L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "generate").value
    r.put(0x377624A3A4911036L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "foreach").value
    r.put(0x803E965492C6666AL, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "find").value
    r.put(0xFAACB7EADF0B3F37L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "exists").value
    r.put(0xCB3C0F54A89D0998L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "contains").value
    r.put(0x681DD2910A45724EL, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "forall").value
    r.put(0x6527C77178D85D87L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "countIf").value
    r.put(0xC0639B2824E292D8L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "reduce").value
    r.put(0xE02A046420DC48A6L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "reduceLeft").value
    r.put(0x9FA3EDF29F624D36L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "filter").value
    r.put(0x5661E49DEC060194L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "withFilter").value
    r.put(0xAD8F1B4779095112L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "map").value
    r.put(0xE3B0F88206C8FCC5L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "flatMap").value
    r.put(0x44C78DB98B71C454L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "flatten").value
    r.put(0xC66BE8B74DA8B281L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "take").value
    r.put(0x0EF556E422659A34L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "drop").value
    r.put(0xC361C0DCC0384309L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "takeWhile").value
    r.put(0x6D41E8176CC7DFACL, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "dropWhile").value
    r.put(0x9BB6445E87F33FD5L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "zip").value
    r.put(0xE48879ED6D8A4E65L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "product").value
    r.put(0x0832674DE05FE910L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "++").value
    r.put(0xBA492886985987ACL, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "toMS").value
    r.put(0xD86C1507F2DB4E35L, r => (o1: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "mkString").value
    r.put(0x43C71D655547DE8AL, _ => (o1: Any) => org.sireum.MJen.Internal.Zipped.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.Zipped", "unapply").value
    r.put(0x7018654D62541CBEL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "generate").value
    r.put(0x27D22B74F64F021EL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "foreach").value
    r.put(0x40311B391060F755L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "find").value
    r.put(0xDDA2CEAA044CC7AFL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "exists").value
    r.put(0x79B49D0326B3D84DL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "contains").value
    r.put(0x4D8E7FE47B40DA93L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "forall").value
    r.put(0x443700E84B730A84L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "countIf").value
    r.put(0xA3E70ED1668E5F27L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "reduce").value
    r.put(0x97B7B500E6CE5AE8L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "reduceLeft").value
    r.put(0x25B8DD86D50B4B99L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "filter").value
    r.put(0xD2E51D5DB9A1192CL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "withFilter").value
    r.put(0xC0B84285C53B25C6L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "map").value
    r.put(0x24E8BA15C317BCDBL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "flatMap").value
    r.put(0x5AED13A877A6DC32L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "flatten").value
    r.put(0x631310A62B9577A0L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "take").value
    r.put(0xDDA1412E92760940L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "drop").value
    r.put(0x2039EAD3A4278ADDL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "takeWhile").value
    r.put(0x299D4D30C2B75B8FL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "dropWhile").value
    r.put(0x4FAB5EBD336AADA4L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "zip").value
    r.put(0xE9B47F2520872180L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "product").value
    r.put(0x7DD992C29009CE97L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "++").value
    r.put(0x7DAC448A5283C9E3L, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "toMS").value
    r.put(0xD317ED7C1DDCCDBFL, r => (o1: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "mkString").value
    r.put(0x115E3F9A95B3A6D6L, _ => (o1: Any) => org.sireum.MJen.Internal.Concat.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.Concat", "unapply").value
    r.put(0x5A0063942B613B50L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "generate").value
    r.put(0x7343F5A08E1458AEL, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "foreach").value
    r.put(0x3D108DFE66489EA8L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "find").value
    r.put(0xE73EAA7108510568L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "exists").value
    r.put(0x06724A5BBD4CBA90L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "contains").value
    r.put(0x926FE282D706E1D3L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "forall").value
    r.put(0xBA730233EB264715L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "countIf").value
    r.put(0x8E646687C45FCF21L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "reduce").value
    r.put(0x1823EA48E38762FAL, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "reduceLeft").value
    r.put(0x49E831284A55B8B4L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "filter").value
    r.put(0xB6F6E3012750A9CAL, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "withFilter").value
    r.put(0x696333B199B90212L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "map").value
    r.put(0xE4E8CC123DE62ABFL, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "flatMap").value
    r.put(0x7D59EF57B61BC973L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "flatten").value
    r.put(0xE23DC01EF155E766L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "take").value
    r.put(0xB1964C376CE4F378L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "drop").value
    r.put(0xCCBFFD7B833BD074L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "takeWhile").value
    r.put(0xDCB52D9D3483F7EFL, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "dropWhile").value
    r.put(0x1C7E58D05A03D4D1L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "zip").value
    r.put(0x684D9C3CBCCEE5C8L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "product").value
    r.put(0x9DBCB05C38D0B04FL, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "++").value
    r.put(0x2D6A2A638CECC918L, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "toMS").value
    r.put(0x6AF424D58F210E7FL, r => (o1: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "mkString").value
    r.put(0xD80C64B05A3A7AF2L, _ => (o1: Any) => org.sireum.MJen.Internal.Product.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MJen.Internal.Product", "unapply").value
    r.put(0x1289BDDB0AAE82B4L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).generate(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "generate").value
    r.put(0xC4A7776E8D70DDCAL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "foreach").value
    r.put(0x836EF7790760C579L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).find(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "find").value
    r.put(0x00C4DAB5C3E21339L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).exists(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "exists").value
    r.put(0xC0DC62D4E1F1B826L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "contains").value
    r.put(0xD8B02407E74F9596L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).forall(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "forall").value
    r.put(0x327DD8BCA193747CL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).countIf(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "countIf").value
    r.put(0x54155B4D24B7C37DL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).reduce(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "reduce").value
    r.put(0xFCB0C9B9A5CD52CCL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "reduceLeft").value
    r.put(0x9174316302C7A7E4L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).filter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "filter").value
    r.put(0x7DB21D01AA262F96L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "withFilter").value
    r.put(0x57061255F469E3BFL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).map(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "map").value
    r.put(0xEB58C56F30B6B20FL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "flatMap").value
    r.put(0xAB98E58AEF7FDD62L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).flatten(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "flatten").value
    r.put(0x0D6B8E257282F90DL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).take(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "take").value
    r.put(0x9261791774A13D3DL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).drop(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "drop").value
    r.put(0xD9671B0515E7D3B4L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "takeWhile").value
    r.put(0x1A8DF62D518A3F06L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "dropWhile").value
    r.put(0xD8EBC68DA0BF34B8L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).zip(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "zip").value
    r.put(0x91BFC1F5DAAD96B2L, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).product(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "product").value
    r.put(0xD7FD9DCBBD18949CL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "++").value
    r.put(0xFE287A837C66F7DEL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).toMS(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "toMS").value
    r.put(0x3DEC5CFF90F17A3AL, r => (o1: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).mkString(X(o1))) // methodKey(F, "org.sireum.MJen.Internal.Product", "mkString").value
    r.put(0xE6F41B6AE7B807BEL, r => (o1: Any) => X[org.sireum.MOption[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MOption", "map").value
    r.put(0x4E20AE2185B74238L, r => (o1: Any) => X[org.sireum.MOption[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MOption", "flatMap").value
    r.put(0xBA9F77CAF4AF9F7EL, r => (o1: Any) => X[org.sireum.MOption[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MOption", "forall").value
    r.put(0x0EB321736C08CF5DL, r => (o1: Any) => X[org.sireum.MOption[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MOption", "exists").value
    r.put(0xC95D4FEBFC0EAFD9L, r => (o1: Any) => X[org.sireum.MOption[_]](r).getOrElse(X(X[(() => _)](o1)()))) // methodKey(F, "org.sireum.MOption", "getOrElse").value
    r.put(0x69B3F1A1FF988069L, r => (o1: Any) => X[org.sireum.MOption[_]](r).getOrElseEager(X(o1))) // methodKey(F, "org.sireum.MOption", "getOrElseEager").value
    r.put(0xBC0A35C7EFCA97E2L, r => (o1: Any) => X[org.sireum.MOption[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MOption", "foreach").value
    r.put(0xCB6F5E1D3EEB301DL, _ => (o1: Any) => if (org.sireum.MNone.unapply(X(o1))) MSome(T) else MNone()) // methodKey(T, "org.sireum.MNone", "unapply").value
    r.put(0x92C215D5382AB3B1L, r => (o1: Any) => X[org.sireum.MNone[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MNone", "map").value
    r.put(0x640A2B089CB5821EL, r => (o1: Any) => X[org.sireum.MNone[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MNone", "flatMap").value
    r.put(0x352CEC78F096724BL, r => (o1: Any) => X[org.sireum.MNone[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MNone", "forall").value
    r.put(0xBC0797965AA9DE97L, r => (o1: Any) => X[org.sireum.MNone[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MNone", "exists").value
    r.put(0x34227019476FC35AL, r => (o1: Any) => X[org.sireum.MNone[_]](r).getOrElse(X(X[(() => _)](o1)()))) // methodKey(F, "org.sireum.MNone", "getOrElse").value
    r.put(0x2C1FE944FE5BD975L, r => (o1: Any) => X[org.sireum.MNone[_]](r).getOrElseEager(X(o1))) // methodKey(F, "org.sireum.MNone", "getOrElseEager").value
    r.put(0xE300858ECDE1EEAEL, r => (o1: Any) => X[org.sireum.MNone[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MNone", "foreach").value
    r.put(0xA1B9D317BCABEA11L, _ => (o1: Any) => org.sireum.MSome.apply(X(o1))) // methodKey(T, "org.sireum.MSome", "apply").value
    r.put(0xC21F31D858242B47L, _ => (o1: Any) => org.sireum.MSome.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.MSome", "unapply").value
    r.put(0xBF196EBAA11ACE26L, r => (o1: Any) => X[org.sireum.MSome[_]](r).map(X(o1))) // methodKey(F, "org.sireum.MSome", "map").value
    r.put(0x0B709DA7585A2A16L, r => (o1: Any) => X[org.sireum.MSome[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.MSome", "flatMap").value
    r.put(0x26C7824554596BCEL, r => (o1: Any) => X[org.sireum.MSome[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.MSome", "forall").value
    r.put(0xA8A13AFC476E3B25L, r => (o1: Any) => X[org.sireum.MSome[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.MSome", "exists").value
    r.put(0xB255A5F713274A38L, r => (o1: Any) => X[org.sireum.MSome[_]](r).getOrElse(X(X[(() => _)](o1)()))) // methodKey(F, "org.sireum.MSome", "getOrElse").value
    r.put(0xD0C64824CE838D44L, r => (o1: Any) => X[org.sireum.MSome[_]](r).getOrElseEager(X(o1))) // methodKey(F, "org.sireum.MSome", "getOrElseEager").value
    r.put(0x5A733A62DE4FE625L, r => (o1: Any) => X[org.sireum.MSome[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.MSome", "foreach").value
    r.put(0x468370A0081F2479L, _ => (o1: Any) => org.sireum.Map.apply(X(o1))) // methodKey(T, "org.sireum.Map", "apply").value
    r.put(0x7A38FE2715A3348AL, _ => (o1: Any) => org.sireum.Map.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Map", "unapply").value
    r.put(0x26F2C27D1303CD41L, r => (o1: Any) => X[org.sireum.Map[_, _]](r).`+`(X(o1))) // methodKey(F, "org.sireum.Map", "+").value
    r.put(0x7C52AFE6925BE5A8L, r => (o1: Any) => X[org.sireum.Map[_, _]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Map", "++").value
    r.put(0x9CFEA110524F9C9AL, r => (o1: Any) => X[org.sireum.Map[_, _]](r).get(X(o1))) // methodKey(F, "org.sireum.Map", "get").value
    r.put(0xAE9DA24B2F75EFA3L, r => (o1: Any) => X[org.sireum.Map[_, _]](r).entry(X(o1))) // methodKey(F, "org.sireum.Map", "entry").value
    r.put(0xBA7079B5DC85A0A8L, r => (o1: Any) => X[org.sireum.Map[_, _]](r).indexOf(X(o1))) // methodKey(F, "org.sireum.Map", "indexOf").value
    r.put(0xD0F78B942CB39657L, r => (o1: Any) => X[org.sireum.Map[_, _]](r).`--`(X(o1))) // methodKey(F, "org.sireum.Map", "--").value
    r.put(0xCD5FA8E915D553B6L, r => (o1: Any) => X[org.sireum.Map[_, _]](r).`-`(X(o1))) // methodKey(F, "org.sireum.Map", "-").value
    r.put(0x594F8299064E2ECEL, r => (o1: Any) => X[org.sireum.Map[_, _]](r).contains(X(o1))) // methodKey(F, "org.sireum.Map", "contains").value
    r.put(0x2FBFA327B8B2BDC4L, r => (o1: Any) => X[org.sireum.Map[_, _]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.Map", "isEqual").value
    r.put(0x4CB0DAEE0EA3E967L, r => (o1: Any) => X[org.sireum.ObjPrinter](r).write(X(o1))) // methodKey(F, "org.sireum.ObjPrinter", "write").value
    r.put(0xB70AFF4604121695L, r => (o1: Any) => X[org.sireum.ObjPrinter](r).printMessage(X(o1))) // methodKey(F, "org.sireum.ObjPrinter", "printMessage").value
    r.put(0xC1346C9054C10176L, r => (o1: Any) => X[org.sireum.ObjPrinter](r).printPosition(X(o1))) // methodKey(F, "org.sireum.ObjPrinter", "printPosition").value
    r.put(0x2AF33D7B8D5EE4A5L, r => (o1: Any) => X[org.sireum.ObjPrinter](r).printDocInfo(X(o1))) // methodKey(F, "org.sireum.ObjPrinter", "printDocInfo").value
    r.put(0x7CACBDF347F52EBCL, r => (o1: Any) => X[org.sireum.Option[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Option", "map").value
    r.put(0x19C072D2B4735C98L, r => (o1: Any) => X[org.sireum.Option[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Option", "flatMap").value
    r.put(0x02E057BE4C7561CCL, r => (o1: Any) => X[org.sireum.Option[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Option", "forall").value
    r.put(0x59D079030ADBCFD1L, r => (o1: Any) => X[org.sireum.Option[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Option", "exists").value
    r.put(0x18CE0DE7F2ECB384L, r => (o1: Any) => X[org.sireum.Option[_]](r).getOrElse(X(X[(() => _)](o1)()))) // methodKey(F, "org.sireum.Option", "getOrElse").value
    r.put(0xA05EE525056BE57FL, r => (o1: Any) => X[org.sireum.Option[_]](r).getOrElseEager(X(o1))) // methodKey(F, "org.sireum.Option", "getOrElseEager").value
    r.put(0xFDA69505F916490BL, r => (o1: Any) => X[org.sireum.Option[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Option", "foreach").value
    r.put(0xC86ADFE866BFED41L, _ => (o1: Any) => if (org.sireum.None.unapply(X(o1))) MSome(T) else MNone()) // methodKey(T, "org.sireum.None", "unapply").value
    r.put(0x1FB380EA28D14879L, r => (o1: Any) => X[org.sireum.None[_]](r).map(X(o1))) // methodKey(F, "org.sireum.None", "map").value
    r.put(0x46F8397E30915E21L, r => (o1: Any) => X[org.sireum.None[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.None", "flatMap").value
    r.put(0x0A617B941A326281L, r => (o1: Any) => X[org.sireum.None[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.None", "forall").value
    r.put(0xA9FD7A31D5DBD9B9L, r => (o1: Any) => X[org.sireum.None[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.None", "exists").value
    r.put(0x8107BDEDDF2A8523L, r => (o1: Any) => X[org.sireum.None[_]](r).getOrElse(X(X[(() => _)](o1)()))) // methodKey(F, "org.sireum.None", "getOrElse").value
    r.put(0x1A54FDC06D66AE6BL, r => (o1: Any) => X[org.sireum.None[_]](r).getOrElseEager(X(o1))) // methodKey(F, "org.sireum.None", "getOrElseEager").value
    r.put(0x689452DE36656F3FL, r => (o1: Any) => X[org.sireum.None[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.None", "foreach").value
    r.put(0xBD9F34035158E169L, _ => (o1: Any) => org.sireum.Some.apply(X(o1))) // methodKey(T, "org.sireum.Some", "apply").value
    r.put(0x98AD011FFEFD9B75L, _ => (o1: Any) => org.sireum.Some.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Some", "unapply").value
    r.put(0x0B5B8E5079082393L, r => (o1: Any) => X[org.sireum.Some[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Some", "map").value
    r.put(0xEC0EEDC4D5E4B4F3L, r => (o1: Any) => X[org.sireum.Some[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Some", "flatMap").value
    r.put(0x59130C9FC99CC80DL, r => (o1: Any) => X[org.sireum.Some[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Some", "forall").value
    r.put(0x85AAE4082F2DF842L, r => (o1: Any) => X[org.sireum.Some[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Some", "exists").value
    r.put(0x70AE9E597EF2A8D2L, r => (o1: Any) => X[org.sireum.Some[_]](r).getOrElse(X(X[(() => _)](o1)()))) // methodKey(F, "org.sireum.Some", "getOrElse").value
    r.put(0x522A37BAE32FD80CL, r => (o1: Any) => X[org.sireum.Some[_]](r).getOrElseEager(X(o1))) // methodKey(F, "org.sireum.Some", "getOrElseEager").value
    r.put(0x7BA2E78EE54B8DB2L, r => (o1: Any) => X[org.sireum.Some[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Some", "foreach").value
    r.put(0x857D155E156245FFL, r => (o1: Any) => X[org.sireum.OsProto.Proc](r).commands(X(o1))) // methodKey(F, "org.sireum.OsProto.Proc", "commands").value
    r.put(0x69C5688A1107EBE2L, r => (o1: Any) => X[org.sireum.OsProto.Proc](r).at(X(o1))) // methodKey(F, "org.sireum.OsProto.Proc", "at").value
    r.put(0xEC05B0247CCCDE85L, r => (o1: Any) => X[org.sireum.OsProto.Proc](r).env(X(o1))) // methodKey(F, "org.sireum.OsProto.Proc", "env").value
    r.put(0x6AFDF88BA74FCE37L, r => (o1: Any) => X[org.sireum.OsProto.Proc](r).input(X(o1))) // methodKey(F, "org.sireum.OsProto.Proc", "input").value
    r.put(0x2345F5D064D830D7L, r => (o1: Any) => X[org.sireum.OsProto.Proc](r).timeout(X(o1))) // methodKey(F, "org.sireum.OsProto.Proc", "timeout").value
    r.put(0x7FB1808CC2FD0113L, r => (o1: Any) => X[org.sireum.OsProto.Proc](r).outLineAction(X(o1))) // methodKey(F, "org.sireum.OsProto.Proc", "outLineAction").value
    r.put(0x4D46231C2DA56C66L, r => (o1: Any) => X[org.sireum.OsProto.Proc](r).errLineAction(X(o1))) // methodKey(F, "org.sireum.OsProto.Proc", "errLineAction").value
    r.put(0x3DFB281C9AF3EC49L, _ => (o1: Any) => org.sireum.Poset.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3)) => MSome((o0, o1, o2, o3))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Poset", "unapply").value
    r.put(0xE3FA837A659F98A5L, r => (o1: Any) => X[org.sireum.Poset[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Poset", "++").value
    r.put(0x03D45CAF69E30029L, r => (o1: Any) => X[org.sireum.Poset[_]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.Poset", "isEqual").value
    r.put(0xA4D8676D27566079L, r => (o1: Any) => X[org.sireum.Poset[_]](r).addNode(X(o1))) // methodKey(F, "org.sireum.Poset", "addNode").value
    r.put(0xCFFFCFB2137BF9F1L, r => (o1: Any) => X[org.sireum.Poset[_]](r).childrenOf(X(o1))) // methodKey(F, "org.sireum.Poset", "childrenOf").value
    r.put(0x4F675EB7A4528C95L, r => (o1: Any) => X[org.sireum.Poset[_]](r).parentsOf(X(o1))) // methodKey(F, "org.sireum.Poset", "parentsOf").value
    r.put(0xF7B63DD9AA0CE748L, r => (o1: Any) => X[org.sireum.Poset[_]](r).ancestorsOf(X(o1))) // methodKey(F, "org.sireum.Poset", "ancestorsOf").value
    r.put(0xA426D215C89FFD43L, r => (o1: Any) => X[org.sireum.Poset[_]](r).lub(X(o1))) // methodKey(F, "org.sireum.Poset", "lub").value
    r.put(0xA204C23FF3DC12EAL, r => (o1: Any) => X[org.sireum.Poset[_]](r).descendantsOf(X(o1))) // methodKey(F, "org.sireum.Poset", "descendantsOf").value
    r.put(0xE07C7BE37C58D68AL, r => (o1: Any) => X[org.sireum.Poset[_]](r).glb(X(o1))) // methodKey(F, "org.sireum.Poset", "glb").value
    r.put(0x352C342682A8DB6DL, r => (o1: Any) => X[org.sireum.Poset[_]](r).toST(X(o1))) // methodKey(F, "org.sireum.Poset", "toST").value
    r.put(0x27003546383F6868L, r => (o1: Any) => X[org.sireum.Random.Gen.TestRunner[_]](r).toCompactJson(X(o1))) // methodKey(F, "org.sireum.Random.Gen.TestRunner", "toCompactJson").value
    r.put(0x0806B2EEFB3E2DB5L, r => (o1: Any) => X[org.sireum.Random.Gen.TestRunner[_]](r).fromJson(X(o1))) // methodKey(F, "org.sireum.Random.Gen.TestRunner", "fromJson").value
    r.put(0x14138F285F620224L, r => (o1: Any) => X[org.sireum.Random.Gen.TestRunner[_]](r).test(X(o1))) // methodKey(F, "org.sireum.Random.Gen.TestRunner", "test").value
    r.put(0x9E14DD15AA6DD391L, _ => (o1: Any) => org.sireum.Random.Gen64Impl.apply(X(o1))) // methodKey(T, "org.sireum.Random.Gen64Impl", "apply").value
    r.put(0x782B3A1B2A51D090L, _ => (o1: Any) => org.sireum.Random.Gen64Impl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Random.Gen64Impl", "unapply").value
    r.put(0xF62718D24F94901CL, _ => (o1: Any) => org.sireum.Random.Impl.SplitMix64.apply(X(o1))) // methodKey(T, "org.sireum.Random.Impl.SplitMix64", "apply").value
    r.put(0xA09F873F77C65EA1L, _ => (o1: Any) => org.sireum.Random.Impl.SplitMix64.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Random.Impl.SplitMix64", "unapply").value
    r.put(0xC4C231B4E94FF71BL, r => (o1: Any) => X[org.sireum.Random.Impl.SplitMix64](r).`seed_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.SplitMix64", "seed_=").value
    r.put(0x304020669424EEFDL, _ => (o1: Any) => org.sireum.Random.Impl.Xoshiro256.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3)) => MSome((o0, o1, o2, o3))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Random.Impl.Xoshiro256", "unapply").value
    r.put(0xF8D3FB64DAF5AD87L, r => (o1: Any) => X[org.sireum.Random.Impl.Xoshiro256](r).`seed0_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed0_=").value
    r.put(0x07D1D9A90EA2D934L, r => (o1: Any) => X[org.sireum.Random.Impl.Xoshiro256](r).`seed1_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed1_=").value
    r.put(0x28A7527EDD2F9BE9L, r => (o1: Any) => X[org.sireum.Random.Impl.Xoshiro256](r).`seed2_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed2_=").value
    r.put(0xB5670D87C026461CL, r => (o1: Any) => X[org.sireum.Random.Impl.Xoshiro256](r).`seed3_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.Xoshiro256", "seed3_=").value
    r.put(0xD381A35FD1EB9321L, _ => (o1: Any) => org.sireum.Random.Impl.Xoroshiro128.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3)) => MSome((o0, o1, o2, o3))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Random.Impl.Xoroshiro128", "unapply").value
    r.put(0x655DAF2C48EC4800L, r => (o1: Any) => X[org.sireum.Random.Impl.Xoroshiro128](r).`seed0_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed0_=").value
    r.put(0xCB599029CD09925BL, r => (o1: Any) => X[org.sireum.Random.Impl.Xoroshiro128](r).`seed1_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed1_=").value
    r.put(0xB210CCE0FBABB843L, r => (o1: Any) => X[org.sireum.Random.Impl.Xoroshiro128](r).`seed2_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed2_=").value
    r.put(0xD96CC7FD73F4FD99L, r => (o1: Any) => X[org.sireum.Random.Impl.Xoroshiro128](r).`seed3_=`(X(o1))) // methodKey(F, "org.sireum.Random.Impl.Xoroshiro128", "seed3_=").value
    r.put(0x816E386F7EF2AA76L, _ => (o1: Any) => org.sireum.Set.apply(X(o1))) // methodKey(T, "org.sireum.Set", "apply").value
    r.put(0xD9FFB62902606651L, _ => (o1: Any) => org.sireum.Set.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Set", "unapply").value
    r.put(0x9F9D5CAFC37A2F2DL, r => (o1: Any) => X[org.sireum.Set[_]](r).`+`(X(o1))) // methodKey(F, "org.sireum.Set", "+").value
    r.put(0x810EBF5FEFAADACBL, r => (o1: Any) => X[org.sireum.Set[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Set", "++").value
    r.put(0x43B496D45163D222L, r => (o1: Any) => X[org.sireum.Set[_]](r).`-`(X(o1))) // methodKey(F, "org.sireum.Set", "-").value
    r.put(0x5EB341B1E1672C32L, r => (o1: Any) => X[org.sireum.Set[_]](r).`--`(X(o1))) // methodKey(F, "org.sireum.Set", "--").value
    r.put(0x7C9D7DF7C465A480L, r => (o1: Any) => X[org.sireum.Set[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Set", "contains").value
    r.put(0x78092E8F1272CD27L, r => (o1: Any) => X[org.sireum.Set[_]](r).union(X(o1))) // methodKey(F, "org.sireum.Set", "union").value
    r.put(0x2A9C4141CE6A0336L, r => (o1: Any) => X[org.sireum.Set[_]](r).`\u222A`(X(o1))) // methodKey(F, "org.sireum.Set", "\u222A").value
    r.put(0x42C7B8BB94061C4EL, r => (o1: Any) => X[org.sireum.Set[_]](r).intersect(X(o1))) // methodKey(F, "org.sireum.Set", "intersect").value
    r.put(0xCB8B46C0B64B6D7CL, r => (o1: Any) => X[org.sireum.Set[_]](r).`\u2229`(X(o1))) // methodKey(F, "org.sireum.Set", "\u2229").value
    r.put(0x50FBC26E798B4037L, r => (o1: Any) => X[org.sireum.Set[_]](r).`\\`(X(o1))) // methodKey(F, "org.sireum.Set", "\\").value
    r.put(0x12AE37174837461EL, r => (o1: Any) => X[org.sireum.Set[_]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.Set", "isEqual").value
    r.put(0x7E2652B8DCC3DF6DL, r => (o1: Any) => X[org.sireum.Set[_]](r).indexOf(X(o1))) // methodKey(F, "org.sireum.Set", "indexOf").value
    r.put(0x3BC0F29752B98FFDL, _ => (o1: Any) => org.sireum.Stack.apply(X(o1))) // methodKey(T, "org.sireum.Stack", "apply").value
    r.put(0x3B6DF5F952207A60L, _ => (o1: Any) => org.sireum.Stack.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Stack", "unapply").value
    r.put(0x1EB542E68906C468L, r => (o1: Any) => X[org.sireum.Stack[_]](r).push(X(o1))) // methodKey(F, "org.sireum.Stack", "push").value
    r.put(0xC9663614762AE5E9L, _ => (o1: Any) => org.sireum.UnionFind.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3)) => MSome((o0, o1, o2, o3))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.UnionFind", "unapply").value
    r.put(0x607484EA4698172FL, r => (o1: Any) => X[org.sireum.UnionFind[_]](r).isEqual(X(o1))) // methodKey(F, "org.sireum.UnionFind", "isEqual").value
    r.put(0x5FE84FDCB63A310BL, r => (o1: Any) => X[org.sireum.UnionFind[_]](r).find(X(o1))) // methodKey(F, "org.sireum.UnionFind", "find").value
    r.put(0x4E66F5FB9A61B820L, r => (o1: Any) => X[org.sireum.UnionFind[_]](r).findCompress(X(o1))) // methodKey(F, "org.sireum.UnionFind", "findCompress").value
    r.put(0xEE5BE955EDEEF8CDL, r => (o1: Any) => X[org.sireum.UnionFind[_]](r).toST(X(o1))) // methodKey(F, "org.sireum.UnionFind", "toST").value
    r.put(0x3807C91400AA6467L, _ => (o1: Any) => org.sireum.CoursierFileInfo.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3)) => MSome((o0, o1, o2, o3))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.CoursierFileInfo", "unapply").value
    r.put(0x566D579F4EE7C313L, _ => (o1: Any) => org.sireum.Coursier.Proxy.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5)) => MSome((o0, o1, o2, o3, o4, o5))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Coursier.Proxy", "unapply").value
    r.put(0x23189B2D18BDD3F8L, _ => (o1: Any) => org.sireum.GitHub.Repository.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.GitHub.Repository", "unapply").value
    r.put(0x7540A8A77D21168AL, _ => (o1: Any) => org.sireum.GitHub.Release.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.GitHub.Release", "unapply").value
    r.put(0x92B4F78506AB8A3AL, _ => (o1: Any) => org.sireum.GitHub.Asset.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.GitHub.Asset", "unapply").value
    r.put(0x6834726706939AF3L, _ => (o1: Any) => org.sireum.Init.Plugin.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3)) => MSome((o0, o1, o2, o3))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Init.Plugin", "unapply").value
    r.put(0xD893E6662D1AC24FL, _ => (o1: Any) => org.sireum.Init.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Init", "unapply").value
    r.put(0x394D50D66209F24FL, r => (o1: Any) => X[org.sireum.Init](r).platform(X(o1))) // methodKey(F, "org.sireum.Init", "platform").value
    r.put(0x6AEDD8A12084FD26L, r => (o1: Any) => X[org.sireum.Init](r).installMill(X(o1))) // methodKey(F, "org.sireum.Init", "installMill").value
    r.put(0x90F1FCAD8814A4D0L, r => (o1: Any) => X[org.sireum.Init](r).installFonts(X(o1))) // methodKey(F, "org.sireum.Init", "installFonts").value
    r.put(0x885C121A873E93F3L, r => (o1: Any) => X[org.sireum.Init](r).ideaSandbox(X(o1))) // methodKey(F, "org.sireum.Init", "ideaSandbox").value
    r.put(0xB42ABE770C9ECC42L, r => (o1: Any) => X[org.sireum.Init](r).init(X(o1))) // methodKey(F, "org.sireum.Init", "init").value
    r.put(0xE9DC95EFDFAF94C7L, _ => (o1: Any) => org.sireum.Os.Path.Impl.apply(X(o1))) // methodKey(T, "org.sireum.Os.Path.Impl", "apply").value
    r.put(0x16BF72C5C67B6932L, _ => (o1: Any) => org.sireum.Os.Path.Impl.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Os.Path.Impl", "unapply").value
    r.put(0xB6C51877F05C73AAL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).`/`(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "/").value
    r.put(0x13D04B1E09D02C80L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).`/+`(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "/+").value
    r.put(0x5B8E12A1219BB443L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).call(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "call").value
    r.put(0xA43B7339042D0552L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).chmod(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "chmod").value
    r.put(0x1D1148B386BCC472L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).chmodAll(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "chmodAll").value
    r.put(0x44C1828086D47C7CL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).combineFrom(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "combineFrom").value
    r.put(0x9E619573ADDA00EAL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).copyTo(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "copyTo").value
    r.put(0x01033C7BAC3CF04AL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).copyOverTo(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "copyOverTo").value
    r.put(0xC8515482D9BD130DL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).downloadFrom(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "downloadFrom").value
    r.put(0xC6AF223E0FEF9126L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).mergeFrom(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "mergeFrom").value
    r.put(0xA176A1DA1E5EDADDL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).moveTo(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "moveTo").value
    r.put(0xD2D71B79480A1AC6L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).moveOverTo(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "moveOverTo").value
    r.put(0x21DEF52BDAEFFE47L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).mklink(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "mklink").value
    r.put(0x4D1118DA7AD477C9L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).prependWith(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "prependWith").value
    r.put(0x177BD36757FFA2FCL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).relativize(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "relativize").value
    r.put(0x08F2E776D999DC16L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).sha3(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "sha3").value
    r.put(0xE623FA9514DF669CL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).setLastModified(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "setLastModified").value
    r.put(0x0AE0D3E90AFFFE7EL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).setExecutable(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "setExecutable").value
    r.put(0xC2CC6E9EE4B1AC06L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).setReadable(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "setReadable").value
    r.put(0xFFEC55478F956242L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).setWritable(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "setWritable").value
    r.put(0x4087B80DA941BE05L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).slash(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "slash").value
    r.put(0xBC5E847DF4333665L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).write(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "write").value
    r.put(0xFC5755AC50A8319FL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOver(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOver").value
    r.put(0xA4BF53402F24860AL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppend(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppend").value
    r.put(0xB2BE3981886E837FL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeLineStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeLineStream").value
    r.put(0xDD42F39FE6E0FE8BL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOverLineStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverLineStream").value
    r.put(0x5A2DCA4B5A141339L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendLineStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendLineStream").value
    r.put(0x2301227FA9E074C6L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeLineMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeLineMStream").value
    r.put(0x582F878CEEB752CEL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOverLineMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverLineMStream").value
    r.put(0xCB277F12A7012990L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendLineMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendLineMStream").value
    r.put(0x92E9B0DA7551FBACL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeU8s(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeU8s").value
    r.put(0x331BF5D9ABB8A21BL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOverU8s(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8s").value
    r.put(0x48D985FAB22B187FL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendU8s(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8s").value
    r.put(0x7CA2A23DCB49EF29L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeU8ms(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeU8ms").value
    r.put(0xF77E19B361B2D85AL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOverU8ms(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8ms").value
    r.put(0xA666FC8ECD47F0E2L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendU8ms(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8ms").value
    r.put(0x4ADED046D6CCD621L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeU8Stream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeU8Stream").value
    r.put(0xD396CC4D710D9FD8L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOverU8Stream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8Stream").value
    r.put(0x5B91EB4360AD8C78L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendU8Stream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8Stream").value
    r.put(0x6738BAD017C093AFL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeU8MStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeU8MStream").value
    r.put(0xC6F34800776CB844L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOverU8MStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8MStream").value
    r.put(0x25CC706531185626L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendU8MStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8MStream").value
    r.put(0x5A4ADDEA0F849AD3L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeCStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeCStream").value
    r.put(0xE188E38C78F93CC7L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOverCStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverCStream").value
    r.put(0xB73F34F19212168CL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendCStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendCStream").value
    r.put(0x6C43E2B167A265D0L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeCMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeCMStream").value
    r.put(0x2560A38A9F29D357L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeOverCMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverCMStream").value
    r.put(0xC75D104F605319C1L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendCMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendCMStream").value
    r.put(0x2D6F42350B84D61AL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).zipTo(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "zipTo").value
    r.put(0xD922F6BF9E155CE0L, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).unzipTo(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "unzipTo").value
    r.put(0x9F58CA4F78DAE96AL, r => (o1: Any) => X[org.sireum.Os.Path.Impl](r).unTarGzTo(X(o1))) // methodKey(F, "org.sireum.Os.Path.Impl", "unTarGzTo").value
    r.put(0xE504DC4A1AE9E4F4L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "generate").value
    r.put(0xF192AF21F3DF714BL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "foreach").value
    r.put(0x5C6DA5677F47654CL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "find").value
    r.put(0xC349757229B551F1L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "exists").value
    r.put(0x3412DD80C8EB878FL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "contains").value
    r.put(0x71083CDF2134DCBBL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "forall").value
    r.put(0x37E702954C8331D2L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "countIf").value
    r.put(0x26BBEE9D275BCB76L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "reduce").value
    r.put(0x86A24A56685868D3L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "reduceLeft").value
    r.put(0x12D83D7433BED153L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "filter").value
    r.put(0x9AED3EE3F1595759L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "withFilter").value
    r.put(0xA2D69BC1F60E03EAL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "map").value
    r.put(0x2CEC342FA15D1AB8L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "flatMap").value
    r.put(0x6C59A08C3A25E85CL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "flatten").value
    r.put(0xE11A0A821500A40DL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "take").value
    r.put(0xD55B5BE32BCD4E49L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "drop").value
    r.put(0x24E20A1D88C0B4F0L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "takeWhile").value
    r.put(0xD17AEB090A9CC6CDL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "dropWhile").value
    r.put(0xFF90B9E2E09112E2L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "zip").value
    r.put(0x473580799AAC9C1DL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "product").value
    r.put(0xD337CD5D3B9148C6L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "++").value
    r.put(0x5746756940B4FEA9L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).toIS(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "toIS").value
    r.put(0x5D620090D32C566DL, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "toMS").value
    r.put(0x1CE314095592A545L, r => (o1: Any) => X[org.sireum.Os.Path.Jen[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Os.Path.Jen", "mkString").value
    r.put(0x2F808DF29F9BD8B8L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).generate(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "generate").value
    r.put(0x4F9F8BEC3BF50FBDL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).foreach(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "foreach").value
    r.put(0x600DEA75B6CFDAC7L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).find(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "find").value
    r.put(0x2B9A40744B8D385EL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).exists(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "exists").value
    r.put(0x3055A923C765BFECL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).contains(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "contains").value
    r.put(0xAD28E5CBC6A66099L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).forall(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "forall").value
    r.put(0x70CCE1C74C4241AAL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).countIf(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "countIf").value
    r.put(0xF807E5B57198FC55L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).reduce(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "reduce").value
    r.put(0x7BD687205A7666ECL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).reduceLeft(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "reduceLeft").value
    r.put(0xB7320217DEFFA0C6L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).filter(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "filter").value
    r.put(0xC890C7CB127C22ACL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).withFilter(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "withFilter").value
    r.put(0xFDB2D3A6439BF5F4L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).map(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "map").value
    r.put(0x599777FEF096683FL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).flatMap(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "flatMap").value
    r.put(0x43EBA08FC8BF09D5L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).flatten(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "flatten").value
    r.put(0x0C08B2659BC66A2EL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).take(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "take").value
    r.put(0xE740361A8E5F48AAL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).drop(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "drop").value
    r.put(0xB5196E9D071110A3L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).takeWhile(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "takeWhile").value
    r.put(0x2B738E92EB8043EEL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).dropWhile(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "dropWhile").value
    r.put(0x172994381AEE1BF5L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).zip(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "zip").value
    r.put(0x072F36BC89AAD005L, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).product(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "product").value
    r.put(0x10A32BBBB2EAEF4BL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).`++`(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "++").value
    r.put(0xC7A8C8746CDF7FBBL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).toMS(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "toMS").value
    r.put(0x65FAAAA4F2E97AFEL, r => (o1: Any) => X[org.sireum.Os.Path.MJen[_]](r).mkString(X(o1))) // methodKey(F, "org.sireum.Os.Path.MJen", "mkString").value
    r.put(0xB3693C2306B12ED9L, r => (o1: Any) => X[org.sireum.Os.Proc.LineFilter](r).filter(X(o1))) // methodKey(F, "org.sireum.Os.Proc.LineFilter", "filter").value
    r.put(0x25D4185D3171F924L, _ => (o1: Any) => org.sireum.Os.Proc.FunLineFilter.apply(X(o1))) // methodKey(T, "org.sireum.Os.Proc.FunLineFilter", "apply").value
    r.put(0x439C9310C9507110L, _ => (o1: Any) => org.sireum.Os.Proc.FunLineFilter.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Os.Proc.FunLineFilter", "unapply").value
    r.put(0xF02501562FEAFBC4L, r => (o1: Any) => X[org.sireum.Os.Proc.FunLineFilter](r).filter(X(o1))) // methodKey(F, "org.sireum.Os.Proc.FunLineFilter", "filter").value
    r.put(0x95307A2A007D1C02L, _ => (o1: Any) => org.sireum.Os.Proc.Result.Normal.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2)) => MSome((o0, o1, o2))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Os.Proc.Result.Normal", "unapply").value
    r.put(0x3CDCC31B5CF13A37L, _ => (o1: Any) => org.sireum.Os.Proc.Result.Exception.apply(X(o1))) // methodKey(T, "org.sireum.Os.Proc.Result.Exception", "apply").value
    r.put(0xBC87FE5BCA7D4999L, _ => (o1: Any) => org.sireum.Os.Proc.Result.Exception.unapply(X(o1)) match {
      case scala.Some(o) => MSome(o)
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Os.Proc.Result.Exception", "unapply").value
    r.put(0x09CC8A8CF8586482L, _ => (o1: Any) => org.sireum.Os.Proc.Result.Timeout.unapply(X(o1)) match {
      case scala.Some((o0, o1)) => MSome((o0, o1))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Os.Proc.Result.Timeout", "unapply").value
    r.put(0xF48724F667564882L, _ => (o1: Any) => org.sireum.Os.Proc.unapply(X(o1)) match {
      case scala.Some((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14)) => MSome((o0, o1, o2, o3, o4, o5, o6, o7, o8, o9, o10, o11, o12, o13, o14))
      case _ => MNone()
    }) // methodKey(T, "org.sireum.Os.Proc", "unapply").value
    r.put(0x22AE762A24FDA146L, r => (o1: Any) => X[org.sireum.Os.Proc](r).commands(X(o1))) // methodKey(F, "org.sireum.Os.Proc", "commands").value
    r.put(0xF7D890943AB6E6E0L, r => (o1: Any) => X[org.sireum.Os.Proc](r).at(X(o1))) // methodKey(F, "org.sireum.Os.Proc", "at").value
    r.put(0x43B478AB84A1281FL, r => (o1: Any) => X[org.sireum.Os.Proc](r).env(X(o1))) // methodKey(F, "org.sireum.Os.Proc", "env").value
    r.put(0x8BF2289950C96DC5L, r => (o1: Any) => X[org.sireum.Os.Proc](r).input(X(o1))) // methodKey(F, "org.sireum.Os.Proc", "input").value
    r.put(0x4856DFF2DF605546L, r => (o1: Any) => X[org.sireum.Os.Proc](r).timeout(X(o1))) // methodKey(F, "org.sireum.Os.Proc", "timeout").value
    r.put(0xE4022EB9A069886CL, r => (o1: Any) => X[org.sireum.Os.Proc](r).outLineAction(X(o1))) // methodKey(F, "org.sireum.Os.Proc", "outLineAction").value
    r.put(0x0AC3E43736A8B56FL, r => (o1: Any) => X[org.sireum.Os.Proc](r).errLineAction(X(o1))) // methodKey(F, "org.sireum.Os.Proc", "errLineAction").value
    r.put(0x2FF6E774BD0698A8L, r => (o1: Any) => X[org.sireum.Os.Path](r).`/`(X(o1))) // methodKey(F, "org.sireum.Os.Path", "/").value
    r.put(0x3C8B9B4275598250L, r => (o1: Any) => X[org.sireum.Os.Path](r).`/+`(X(o1))) // methodKey(F, "org.sireum.Os.Path", "/+").value
    r.put(0x082FC7021A4EF670L, r => (o1: Any) => X[org.sireum.Os.Path](r).call(X(o1))) // methodKey(F, "org.sireum.Os.Path", "call").value
    r.put(0xC7CBEB2394D1FC82L, r => (o1: Any) => X[org.sireum.Os.Path](r).chmod(X(o1))) // methodKey(F, "org.sireum.Os.Path", "chmod").value
    r.put(0x222A8ADB3CD3B58AL, r => (o1: Any) => X[org.sireum.Os.Path](r).chmodAll(X(o1))) // methodKey(F, "org.sireum.Os.Path", "chmodAll").value
    r.put(0x80B94D36DCCF759CL, r => (o1: Any) => X[org.sireum.Os.Path](r).combineFrom(X(o1))) // methodKey(F, "org.sireum.Os.Path", "combineFrom").value
    r.put(0xA084752F882FAE81L, r => (o1: Any) => X[org.sireum.Os.Path](r).copyTo(X(o1))) // methodKey(F, "org.sireum.Os.Path", "copyTo").value
    r.put(0x94B11624FED74821L, r => (o1: Any) => X[org.sireum.Os.Path](r).copyOverTo(X(o1))) // methodKey(F, "org.sireum.Os.Path", "copyOverTo").value
    r.put(0x3F50F20B6F8F62ABL, r => (o1: Any) => X[org.sireum.Os.Path](r).downloadFrom(X(o1))) // methodKey(F, "org.sireum.Os.Path", "downloadFrom").value
    r.put(0xA9700CAD1EDA75B0L, r => (o1: Any) => X[org.sireum.Os.Path](r).mergeFrom(X(o1))) // methodKey(F, "org.sireum.Os.Path", "mergeFrom").value
    r.put(0x2D43694D7292606CL, r => (o1: Any) => X[org.sireum.Os.Path](r).moveTo(X(o1))) // methodKey(F, "org.sireum.Os.Path", "moveTo").value
    r.put(0x0C68870FD7214D3BL, r => (o1: Any) => X[org.sireum.Os.Path](r).moveOverTo(X(o1))) // methodKey(F, "org.sireum.Os.Path", "moveOverTo").value
    r.put(0xA6BA777B1FBE47B4L, r => (o1: Any) => X[org.sireum.Os.Path](r).mklink(X(o1))) // methodKey(F, "org.sireum.Os.Path", "mklink").value
    r.put(0x0879FE2B2FB52FF4L, r => (o1: Any) => X[org.sireum.Os.Path](r).prependWith(X(o1))) // methodKey(F, "org.sireum.Os.Path", "prependWith").value
    r.put(0x96414EBF95277030L, r => (o1: Any) => X[org.sireum.Os.Path](r).relativize(X(o1))) // methodKey(F, "org.sireum.Os.Path", "relativize").value
    r.put(0x8EC4D0DCFE2665B0L, r => (o1: Any) => X[org.sireum.Os.Path](r).sha3(X(o1))) // methodKey(F, "org.sireum.Os.Path", "sha3").value
    r.put(0x5A9B1076C58EEB72L, r => (o1: Any) => X[org.sireum.Os.Path](r).setLastModified(X(o1))) // methodKey(F, "org.sireum.Os.Path", "setLastModified").value
    r.put(0x047C78EA95233E8AL, r => (o1: Any) => X[org.sireum.Os.Path](r).setExecutable(X(o1))) // methodKey(F, "org.sireum.Os.Path", "setExecutable").value
    r.put(0xE9111AF76353A94AL, r => (o1: Any) => X[org.sireum.Os.Path](r).setReadable(X(o1))) // methodKey(F, "org.sireum.Os.Path", "setReadable").value
    r.put(0x80E4939BCDDB45ACL, r => (o1: Any) => X[org.sireum.Os.Path](r).setWritable(X(o1))) // methodKey(F, "org.sireum.Os.Path", "setWritable").value
    r.put(0xA1348253E3A5B1E3L, r => (o1: Any) => X[org.sireum.Os.Path](r).slash(X(o1))) // methodKey(F, "org.sireum.Os.Path", "slash").value
    r.put(0xDC4DB49A09541247L, r => (o1: Any) => X[org.sireum.Os.Path](r).write(X(o1))) // methodKey(F, "org.sireum.Os.Path", "write").value
    r.put(0xC311034B333607A4L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOver(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOver").value
    r.put(0xAE51F4DDB35625E1L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppend(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppend").value
    r.put(0xA5B574C8DAF7D0D0L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeLineStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeLineStream").value
    r.put(0xB1E1D93B23AF3D54L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOverLineStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOverLineStream").value
    r.put(0x172A5B19FB1832B0L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppendLineStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppendLineStream").value
    r.put(0x80C840BD5A8452D4L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeLineMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeLineMStream").value
    r.put(0xAC8C72657E18D3B7L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOverLineMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOverLineMStream").value
    r.put(0xEF5B82812CC825ECL, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppendLineMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppendLineMStream").value
    r.put(0xF2E84E31EE7F7596L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeU8s(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeU8s").value
    r.put(0xC9BB4AC0C0A3A88BL, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOverU8s(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOverU8s").value
    r.put(0x04B206DD3BD2B55BL, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppendU8s(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppendU8s").value
    r.put(0xB002EC6DC69A184BL, r => (o1: Any) => X[org.sireum.Os.Path](r).writeU8ms(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeU8ms").value
    r.put(0xDA04F0DE676B52C8L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOverU8ms(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOverU8ms").value
    r.put(0x8A8D8DBD66744422L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppendU8ms(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppendU8ms").value
    r.put(0xC849943DA937A2B7L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeU8Stream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeU8Stream").value
    r.put(0x937BC9C5CFB2C574L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOverU8Stream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOverU8Stream").value
    r.put(0x5A9284ADCA91DC30L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppendU8Stream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppendU8Stream").value
    r.put(0x437B07C4ED800FF8L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeU8MStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeU8MStream").value
    r.put(0xADC6B5E9BE1D2BB2L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOverU8MStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOverU8MStream").value
    r.put(0xDF9324D2F22B94A8L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppendU8MStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppendU8MStream").value
    r.put(0x869210C150BC4217L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeCStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeCStream").value
    r.put(0x74E4DCA0494CCB14L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOverCStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOverCStream").value
    r.put(0x4D2ACE69102F0E97L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppendCStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppendCStream").value
    r.put(0x805E21DFEBA74CB8L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeCMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeCMStream").value
    r.put(0x20829FE1E25FD61EL, r => (o1: Any) => X[org.sireum.Os.Path](r).writeOverCMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeOverCMStream").value
    r.put(0xAB2D728FCFFCE0A2L, r => (o1: Any) => X[org.sireum.Os.Path](r).writeAppendCMStream(X(o1))) // methodKey(F, "org.sireum.Os.Path", "writeAppendCMStream").value
    r.put(0x5BF82ECCE71C4E29L, r => (o1: Any) => X[org.sireum.Os.Path](r).zipTo(X(o1))) // methodKey(F, "org.sireum.Os.Path", "zipTo").value
    r.put(0xC3E14F7AF35CABFEL, r => (o1: Any) => X[org.sireum.Os.Path](r).unzipTo(X(o1))) // methodKey(F, "org.sireum.Os.Path", "unzipTo").value
    r.put(0x659B7BC16EA8D227L, r => (o1: Any) => X[org.sireum.Os.Path](r).unTarGzTo(X(o1))) // methodKey(F, "org.sireum.Os.Path", "unTarGzTo").value
    r
  }

  private lazy val method2Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any](332)
    r.put(0x4D89D302473509F8L, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.contain(X(o1), X(o2))) // methodKey(T, "org.sireum.AssocS.Entries", "contain").value
    r.put(0x178633CC914B88ACL, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.containKey(X(o1), X(o2))) // methodKey(T, "org.sireum.AssocS.Entries", "containKey").value
    r.put(0x4E5075BEB3306304L, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.containValue(X(o1), X(o2))) // methodKey(T, "org.sireum.AssocS.Entries", "containValue").value
    r.put(0x2B283EA5F678304FL, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.add(X(o1), X(o2))) // methodKey(T, "org.sireum.AssocS.Entries", "add").value
    r.put(0x39E044506B21236DL, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.indexOf(X(o1), X(o2))) // methodKey(T, "org.sireum.AssocS.Entries", "indexOf").value
    r.put(0xE94CD6B15156FDB9L, _ => (o1: Any) => (o2: Any) => org.sireum.AssocS.Entries.remove(X(o1), X(o2))) // methodKey(T, "org.sireum.AssocS.Entries", "remove").value
    r.put(0x9CBB5B7098269807L, _ => (o1: Any) => (o2: Any) => org.sireum.ContractUtil.modPos(X(o1), X(o2))) // methodKey(T, "org.sireum.ContractUtil", "modPos").value
    r.put(0xB16CAA123C3555CBL, _ => (o1: Any) => (o2: Any) => org.sireum.ContractUtil.modNeg(X(o1), X(o2))) // methodKey(T, "org.sireum.ContractUtil", "modNeg").value
    r.put(0x2383098E48B3B87DL, _ => (o1: Any) => (o2: Any) => org.sireum.ContractUtil.isAllIS(X(o1), X(o2))) // methodKey(T, "org.sireum.ContractUtil", "isAllIS").value
    r.put(0x562C04357BD2A178L, _ => (o1: Any) => (o2: Any) => org.sireum.ContractUtil.isAllMS(X(o1), X(o2))) // methodKey(T, "org.sireum.ContractUtil", "isAllMS").value
    r.put(0x928FF1D4B1DFA839L, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Internal.addEdge(X(o1), X(o2))) // methodKey(T, "org.sireum.Graph.Internal", "addEdge").value
    r.put(0x4524CDA3F6F6F88DL, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Internal.incoming(X(o1), X(o2))) // methodKey(T, "org.sireum.Graph.Internal", "incoming").value
    r.put(0x53B7E0C7DF3E3F43L, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Internal.outgoing(X(o1), X(o2))) // methodKey(T, "org.sireum.Graph.Internal", "outgoing").value
    r.put(0x82E7E2C688CFAA3EL, _ => (o1: Any) => (o2: Any) => org.sireum.Hash.murmur3a(X(o1), X(o2))) // methodKey(T, "org.sireum.Hash", "murmur3a").value
    r.put(0x1AC38055921AEF7DL, _ => (o1: Any) => (o2: Any) => org.sireum.Hash.t1ha0(X(o1), X(o2))) // methodKey(T, "org.sireum.Hash", "t1ha0").value
    r.put(0xD0A1400B1678D77AL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.parseAst(X(o1), X(o2))) // methodKey(T, "org.sireum.Json", "parseAst").value
    r.put(0xC6680594D4A56EEAL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.printAst(X(o1), X(o2))) // methodKey(T, "org.sireum.Json", "printAst").value
    r.put(0x010B624A430AF0BAL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Printer.printZS(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Printer", "printZS").value
    r.put(0xAF5410FA5B616D41L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Printer.printIS(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Printer", "printIS").value
    r.put(0x25D0DA317E5D35FBL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Printer.printMS(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Printer", "printMS").value
    r.put(0xD84C70D07AC82926L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure0(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure0").value
    r.put(0x454AEAAE4A77333AL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse0(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse0").value
    r.put(0xF4D48D2C4126D9CFL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure1(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure1").value
    r.put(0x71288659B5A09E8BL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse1(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse1").value
    r.put(0x227186A955E14410L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure2(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure2").value
    r.put(0xBB1CE51B459D00D6L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse2(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse2").value
    r.put(0x04BE9BB770622B7EL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure3(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure3").value
    r.put(0x8870E0E25C026407L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse3(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse3").value
    r.put(0xF6EFBE732268B733L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure4(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure4").value
    r.put(0x4E9E44AE5F999FCAL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse4(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse4").value
    r.put(0xE47267F3C78EDFCFL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure5(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure5").value
    r.put(0x3F2D50D13364A694L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse5(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse5").value
    r.put(0x23775F3E38554CADL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure6(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure6").value
    r.put(0x49E08502394FEE48L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse6(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse6").value
    r.put(0xDF00232EAFA2F04DL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure7(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure7").value
    r.put(0x25B03D73B3F177C6L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse7(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse7").value
    r.put(0xBA29A31CBF5FB6DBL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure8(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure8").value
    r.put(0xB4200C3069FBE81BL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse8(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse8").value
    r.put(0x89AA162A6D31B5ABL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure9(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure9").value
    r.put(0xD35CBE3952F38A3EL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse9(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse9").value
    r.put(0x09FC83FBFBE7F6D1L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure10(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure10").value
    r.put(0xB46B1F217C11491BL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse10(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse10").value
    r.put(0x28506A9065B91A22L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure11(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure11").value
    r.put(0xC575BDF8B375292BL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse11(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse11").value
    r.put(0x7D47D74E9CB2EBF3L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure12(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure12").value
    r.put(0x0DFEBDA75B06610DL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse12(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse12").value
    r.put(0xE39A89B42BA113FBL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure13(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure13").value
    r.put(0x64EB0B258E362B14L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse13(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse13").value
    r.put(0x480DDC950C05AE24L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure14(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure14").value
    r.put(0x5D1B0D5149F06A9FL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse14(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse14").value
    r.put(0x9BA4433D213BB061L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure15(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure15").value
    r.put(0x0FBDF5BC5DC63CD4L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse15(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse15").value
    r.put(0x33363802A06752D0L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure16(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure16").value
    r.put(0x969D08A605A46E8AL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse16(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse16").value
    r.put(0xA9C61F7EB9E17BAEL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure17(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure17").value
    r.put(0xBB9C9C5818F3E85CL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse17(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse17").value
    r.put(0x61674FA20301D8C7L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure18(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure18").value
    r.put(0x293C89246B4844DCL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse18(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse18").value
    r.put(0xEF6529BCE0F78F1FL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure19(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure19").value
    r.put(0x0A1B6B553C3B179CL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse19(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse19").value
    r.put(0xFC55C4802594BD73L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure20(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure20").value
    r.put(0x2588F1E72DCCF784L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse20(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse20").value
    r.put(0xFA8C379D312DEA0EL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure21(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure21").value
    r.put(0x6D2DC83063E22AE2L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse21(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse21").value
    r.put(0x7ACE2AEBDF29C79CL, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parsePure22(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parsePure22").value
    r.put(0x7999C4CFCB3F1C88L, _ => (o1: Any) => (o2: Any) => org.sireum.Json.Fun.parse22(X(o1), X(o2))) // methodKey(T, "org.sireum.Json.Fun", "parse22").value
    r.put(0x612CEAFCE4129CA6L, _ => (o1: Any) => (o2: Any) => org.sireum.LibUtil.parCores(X(o1), X(o2))) // methodKey(T, "org.sireum.LibUtil", "parCores").value
    r.put(0x5DA2792530D35966L, _ => (o1: Any) => (o2: Any) => org.sireum.LibUtil.parCoresOpt(X(o1), X(o2))) // methodKey(T, "org.sireum.LibUtil", "parCoresOpt").value
    r.put(0xD6FBE73F1F9665F3L, _ => (o1: Any) => (o2: Any) => org.sireum.LibUtil.mineOptionsWithPrefix(X(o1), X(o2))) // methodKey(T, "org.sireum.LibUtil", "mineOptionsWithPrefix").value
    r.put(0x93D980CAF274553DL, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.addNode(X(o1), X(o2))) // methodKey(T, "org.sireum.Poset.Internal", "addNode").value
    r.put(0xC30153150F7EC2EAL, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.addNodes(X(o1), X(o2))) // methodKey(T, "org.sireum.Poset.Internal", "addNodes").value
    r.put(0x2038FE88D1916323L, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.childrenOf(X(o1), X(o2))) // methodKey(T, "org.sireum.Poset.Internal", "childrenOf").value
    r.put(0x6CBF913BC0DFD772L, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.parentsOf(X(o1), X(o2))) // methodKey(T, "org.sireum.Poset.Internal", "parentsOf").value
    r.put(0x6E87431738B70955L, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.ancestorsOf(X(o1), X(o2))) // methodKey(T, "org.sireum.Poset.Internal", "ancestorsOf").value
    r.put(0x4FC217B48FF6D852L, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.lub(X(o1), X(o2))) // methodKey(T, "org.sireum.Poset.Internal", "lub").value
    r.put(0xAB0AE33B6F0B9982L, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.descendantsOf(X(o1), X(o2))) // methodKey(T, "org.sireum.Poset.Internal", "descendantsOf").value
    r.put(0x3DAA5793705FEED8L, _ => (o1: Any) => (o2: Any) => org.sireum.Poset.Internal.glb(X(o1), X(o2))) // methodKey(T, "org.sireum.Poset.Internal", "glb").value
    r.put(0xE2A2334F047744D1L, _ => (o1: Any) => (o2: Any) => org.sireum.Random.rotl32(X(o1), X(o2))) // methodKey(T, "org.sireum.Random", "rotl32").value
    r.put(0xE5B1FC9A31989748L, _ => (o1: Any) => (o2: Any) => org.sireum.Random.rotl64(X(o1), X(o2))) // methodKey(T, "org.sireum.Random", "rotl64").value
    r.put(0xEA73CBDA8ECB493EL, _ => (o1: Any) => (o2: Any) => org.sireum.Set.Elements.contain(X(o1), X(o2))) // methodKey(T, "org.sireum.Set.Elements", "contain").value
    r.put(0x546627C6E3C63868L, _ => (o1: Any) => (o2: Any) => org.sireum.UnionFind.Internal.find(X(o1), X(o2))) // methodKey(T, "org.sireum.UnionFind.Internal", "find").value
    r.put(0x0D002218A9369CFDL, _ => (o1: Any) => (o2: Any) => org.sireum.UnionFind.Internal.findCompress(X(o1), X(o2))) // methodKey(T, "org.sireum.UnionFind.Internal", "findCompress").value
    r.put(0xE4B8D936C9D8CAB1L, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.andI(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "andI").value
    r.put(0x1A1476F25B111A5EL, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.andE1(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "andE1").value
    r.put(0x3B2F80F532036C22L, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.andE2(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "andE2").value
    r.put(0x789DED1BA67B03D7L, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.orI1(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "orI1").value
    r.put(0x48A884BB5B4FBA1FL, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.orI2(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "orI2").value
    r.put(0x91CB57D0A3455180L, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.implyE(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "implyE").value
    r.put(0x472F0AAA08F546D6L, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sandI(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "sandI").value
    r.put(0xB5261A98D0D7D827L, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sandE1(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "sandE1").value
    r.put(0xD1EF48B22113CD6EL, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sandE2(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "sandE2").value
    r.put(0xCC48284B33C6C1FCL, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sorI1(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "sorI1").value
    r.put(0x8B0141E580B126DAL, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.sorI2(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "sorI2").value
    r.put(0xD2BF1208B8800EDEL, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.prop.simplyE(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.prop", "simplyE").value
    r.put(0x684C1670B4F120B1L, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.pred.allE(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.pred", "allE").value
    r.put(0x8B0B01BC9C6EEE54L, _ => (o1: Any) => (o2: Any) => org.sireum.justification.natded.pred.existsI(X(o1), X(o2))) // methodKey(T, "org.sireum.justification.natded.pred", "existsI").value
    r.put(0x6307744B6E77C1A7L, _ => (o1: Any) => (o2: Any) => org.sireum.Coursier.isRuntimePublishedLocally(X(o1), X(o2))) // methodKey(T, "org.sireum.Coursier", "isRuntimePublishedLocally").value
    r.put(0x1DA92106EB6589D4L, _ => (o1: Any) => (o2: Any) => org.sireum.GitHub.repo(X(o1), X(o2))) // methodKey(T, "org.sireum.GitHub", "repo").value
    r.put(0xF300BD9CE5AC1119L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.javaHomeOpt(X(o1), X(o2))) // methodKey(T, "org.sireum.Os", "javaHomeOpt").value
    r.put(0x862E599A4E9195F8L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.tempFix(X(o1), X(o2))) // methodKey(T, "org.sireum.Os", "tempFix").value
    r.put(0x4DA63867A8230393L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.download(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "download").value
    r.put(0xF22E9998BC9F3381L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.mergeFrom(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "mergeFrom").value
    r.put(0x601C666A8F930DA3L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.mkdir(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "mkdir").value
    r.put(0xC0EEB8B2898E63D2L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.mklink(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "mklink").value
    r.put(0xB82E01BF2363E5BBL, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.relativize(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "relativize").value
    r.put(0x40EF2D61504D6B3DL, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.sha3(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "sha3").value
    r.put(0xB393AE71E3606377L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.setLastModified(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "setLastModified").value
    r.put(0xB7B05B618B2258C6L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.setExecutable(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "setExecutable").value
    r.put(0x45B3D11E1CFADCC7L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.setReadable(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "setReadable").value
    r.put(0x479A12025CF56236L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.setWritable(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "setWritable").value
    r.put(0x384F977029B938BAL, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.temp(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "temp").value
    r.put(0x837B0EC490F1849CL, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.zip(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "zip").value
    r.put(0x396E341024F63799L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.unzip(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "unzip").value
    r.put(0xFFE8DB1E12437074L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Ext.unTarGz(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Ext", "unTarGz").value
    r.put(0xFEA79E6B2C203917L, _ => (o1: Any) => (o2: Any) => org.sireum.Scalafmt.formatFile(X(o1), X(o2))) // methodKey(T, "org.sireum.Scalafmt", "formatFile").value
    r.put(0x49C093B097A01EBDL, r => (o1: Any) => (o2: Any) => X[org.sireum.AssocS[_, _]](r).getOrElse(X(o1), X(X[(() => _)](o2)()))) // methodKey(F, "org.sireum.AssocS", "getOrElse").value
    r.put(0x6C3073DA0AD132D8L, r => (o1: Any) => (o2: Any) => X[org.sireum.AssocS[_, _]](r).getOrElseEager(X(o1), X(o2))) // methodKey(F, "org.sireum.AssocS", "getOrElseEager").value
    r.put(0x08021F8181E0F814L, r => (o1: Any) => (o2: Any) => X[org.sireum.Bag[_]](r).addN(X(o1), X(o2))) // methodKey(F, "org.sireum.Bag", "addN").value
    r.put(0x785EDDA06BFE8C1AL, r => (o1: Any) => (o2: Any) => X[org.sireum.Bag[_]](r).removeN(X(o1), X(o2))) // methodKey(F, "org.sireum.Bag", "removeN").value
    r.put(0x3FE76C85E5F4411CL, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Edge.Plain.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Graph.Edge.Plain", "apply").value
    r.put(0xD1480A237264634EL, _ => (o1: Any) => (o2: Any) => org.sireum.Graph.Internal.Edge.Plain.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Graph.Internal.Edge.Plain", "apply").value
    r.put(0x2BE976A74FF136C8L, r => (o1: Any) => (o2: Any) => X[org.sireum.Graph[_, _]](r).addPlainEdge(X(o1), X(o2))) // methodKey(F, "org.sireum.Graph", "addPlainEdge").value
    r.put(0xE0AD4F19FFDA9091L, r => (o1: Any) => (o2: Any) => X[org.sireum.Graph[_, _]](r).removeEdgeN(X(o1), X(o2))) // methodKey(F, "org.sireum.Graph", "removeEdgeN").value
    r.put(0x6C9F6B0CF10F6977L, r => (o1: Any) => (o2: Any) => X[org.sireum.Graph[_, _]](r).edges(X(o1), X(o2))) // methodKey(F, "org.sireum.Graph", "edges").value
    r.put(0x16C330EA829F6E77L, r => (o1: Any) => (o2: Any) => X[org.sireum.HashBag[_]](r).addN(X(o1), X(o2))) // methodKey(F, "org.sireum.HashBag", "addN").value
    r.put(0x49A9FF8AAB03F510L, r => (o1: Any) => (o2: Any) => X[org.sireum.HashBag[_]](r).removeN(X(o1), X(o2))) // methodKey(F, "org.sireum.HashBag", "removeN").value
    r.put(0xCEC30333071D03DBL, _ => (o1: Any) => (o2: Any) => org.sireum.HashMap.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.HashMap", "apply").value
    r.put(0x7845CD39AB58D63EL, r => (o1: Any) => (o2: Any) => X[org.sireum.HashSBag[_]](r).addN(X(o1), X(o2))) // methodKey(F, "org.sireum.HashSBag", "addN").value
    r.put(0xE31AD83762526B77L, r => (o1: Any) => (o2: Any) => X[org.sireum.HashSBag[_]](r).removeN(X(o1), X(o2))) // methodKey(F, "org.sireum.HashSBag", "removeN").value
    r.put(0xB7B7A30167138B13L, _ => (o1: Any) => (o2: Any) => org.sireum.HashSMap.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.HashSMap", "apply").value
    r.put(0x9EC0B1DA5C9CCE89L, r => (o1: Any) => (o2: Any) => X[org.sireum.Indexable.Pos[_]](r).posOpt(X(o1), X(o2))) // methodKey(F, "org.sireum.Indexable.Pos", "posOpt").value
    r.put(0x9F7CDF54AFAA71DBL, _ => (o1: Any) => (o2: Any) => org.sireum.Indexable.IszDocInfo.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Indexable.IszDocInfo", "apply").value
    r.put(0x6D58B4D33B940EEEL, r => (o1: Any) => (o2: Any) => X[org.sireum.Indexable.IszDocInfo[_]](r).posOpt(X(o1), X(o2))) // methodKey(F, "org.sireum.Indexable.IszDocInfo", "posOpt").value
    r.put(0x041DA465174F906EL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen", "fold").value
    r.put(0x4D751DF563BB3F9DL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen", "foldLeft").value
    r.put(0x8959AC5C355C37CFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen", "slice").value
    r.put(0xA54B03218B1CCBFFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "fold").value
    r.put(0x6E353A74748FB104L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "foldLeft").value
    r.put(0x7FBB2F2B7D80BAEEL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "slice").value
    r.put(0x40BA7BA624C1375BL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "fold").value
    r.put(0x101A1EBE5C403EE1L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "foldLeft").value
    r.put(0xC0D44E9ACAD095DFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "slice").value
    r.put(0x64E6BD2733F64140L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "fold").value
    r.put(0x38B3EE3BC094B1DDL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "foldLeft").value
    r.put(0x8713BAB26393E996L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "slice").value
    r.put(0x40A879D7CA2541AEL, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Filtered.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Jen.Internal.Filtered", "apply").value
    r.put(0x6D72E1EDB6347535L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "fold").value
    r.put(0xA636738B275FCD7EL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "foldLeft").value
    r.put(0xFAF3388C679CFC01L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "slice").value
    r.put(0x9B8DE6A6D6B5BE2AL, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Mapped.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Jen.Internal.Mapped", "apply").value
    r.put(0x09CFEA30136FAEEDL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "fold").value
    r.put(0x86C1BC8BE26BBC47L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "foldLeft").value
    r.put(0xE36598706B63AFDBL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "slice").value
    r.put(0xEEE44E5821CF63E1L, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.FlatMapped.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Jen.Internal.FlatMapped", "apply").value
    r.put(0x2D0172125C239932L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "fold").value
    r.put(0x9FBD18FC1D806BD1L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "foldLeft").value
    r.put(0x3F64F21E5A1466F8L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "slice").value
    r.put(0x4CA9D96227CB4CBEL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "fold").value
    r.put(0x7C360DEA12CF4E24L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "foldLeft").value
    r.put(0xF52245B0C8C6D013L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "slice").value
    r.put(0x14C647E1A8667A59L, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.TakeWhile.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Jen.Internal.TakeWhile", "apply").value
    r.put(0x0E75D4FF81407E86L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "fold").value
    r.put(0xDF7978AA1C9F5975L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "foldLeft").value
    r.put(0x5C42C51FA9270097L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "slice").value
    r.put(0x087F8F5BB125889DL, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.DropWhile.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Jen.Internal.DropWhile", "apply").value
    r.put(0x1A89DE7BE8A08DA4L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "fold").value
    r.put(0xD09713B66C83794FL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "foldLeft").value
    r.put(0xFDA7DE926D86C6B1L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "slice").value
    r.put(0x96A52EFFE75C18D3L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "fold").value
    r.put(0x4F8024E1901E7328L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "foldLeft").value
    r.put(0x4EA66F42272AE270L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "slice").value
    r.put(0x6557C41078F36872L, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Zipped.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Jen.Internal.Zipped", "apply").value
    r.put(0xC19BFE174B8CA38CL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "fold").value
    r.put(0x82B390B17F0FB813L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "foldLeft").value
    r.put(0x3C643385D22826CBL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "slice").value
    r.put(0xEA59DE915A5618E3L, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Concat.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Jen.Internal.Concat", "apply").value
    r.put(0x3DAFE7D542EED8CFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "fold").value
    r.put(0xB08F6F70BE6D80FFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "foldLeft").value
    r.put(0x4F240169B4420ADFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "slice").value
    r.put(0xB82C1BDA097B4B2CL, _ => (o1: Any) => (o2: Any) => org.sireum.Jen.Internal.Product.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Jen.Internal.Product", "apply").value
    r.put(0x26CC544A64D9B260L, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Product", "fold").value
    r.put(0x0D868ACA49D40F2AL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Product", "foldLeft").value
    r.put(0x529DC6529237411DL, r => (o1: Any) => (o2: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Jen.Internal.Product", "slice").value
    r.put(0xD9E8BD1C5C364313L, r => (o1: Any) => (o2: Any) => X[org.sireum.Json.Parser](r).parseEither(X(o1), X(o2))) // methodKey(F, "org.sireum.Json.Parser", "parseEither").value
    r.put(0xF0A3263F13D3C804L, r => (o1: Any) => (o2: Any) => X[org.sireum.Json.Parser](r).parseMEither(X(o1), X(o2))) // methodKey(F, "org.sireum.Json.Parser", "parseMEither").value
    r.put(0x75AB1BC0A5633748L, r => (o1: Any) => (o2: Any) => X[org.sireum.Json.Parser](r).parseMap(X(o1), X(o2))) // methodKey(F, "org.sireum.Json.Parser", "parseMap").value
    r.put(0xD31D9862523512D6L, r => (o1: Any) => (o2: Any) => X[org.sireum.Json.Parser](r).parseHashMap(X(o1), X(o2))) // methodKey(F, "org.sireum.Json.Parser", "parseHashMap").value
    r.put(0xEDB0B04928E95079L, r => (o1: Any) => (o2: Any) => X[org.sireum.Json.Parser](r).parseHashSMap(X(o1), X(o2))) // methodKey(F, "org.sireum.Json.Parser", "parseHashSMap").value
    r.put(0x61D010567024816BL, r => (o1: Any) => (o2: Any) => X[org.sireum.Json.Parser](r).parseGraph(X(o1), X(o2))) // methodKey(F, "org.sireum.Json.Parser", "parseGraph").value
    r.put(0x1238C9E63ABD0792L, r => (o1: Any) => (o2: Any) => X[org.sireum.Json.Parser](r).parseException(X(o1), X(o2))) // methodKey(F, "org.sireum.Json.Parser", "parseException").value
    r.put(0xBE8FBA80009234C2L, r => (o1: Any) => (o2: Any) => X[org.sireum.Json.Parser](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Json.Parser", "slice").value
    r.put(0x26239569EB9F78B4L, _ => (o1: Any) => (o2: Any) => org.sireum.MBox2.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MBox2", "apply").value
    r.put(0xE1647A47813A1867L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen", "fold").value
    r.put(0xC736E72ADF065E1CL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen", "foldLeft").value
    r.put(0x49B4E013B3069183L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen", "slice").value
    r.put(0x1CAA5B26F8EA86C1L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "fold").value
    r.put(0x18CDC7D977A6A510L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "foldLeft").value
    r.put(0x2659AD904C933FBEL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "slice").value
    r.put(0xF4D6E5F049B1AAF9L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "fold").value
    r.put(0xF9A58A914487A8A2L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "foldLeft").value
    r.put(0x2EB78D386B05FA88L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "slice").value
    r.put(0x2A62D75947EF6E92L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "fold").value
    r.put(0xD583C036081A2061L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "foldLeft").value
    r.put(0x85568BE5447F8C5FL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "slice").value
    r.put(0xE9F46A8C7E11192DL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "fold").value
    r.put(0x3F06D70F1D52E356L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "foldLeft").value
    r.put(0xF364398B24C7A027L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "slice").value
    r.put(0x3ACC67C13766A04DL, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Filtered.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MJen.Internal.Filtered", "apply").value
    r.put(0x4EBF495CC2A079F6L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "fold").value
    r.put(0xFFC826DDB864B673L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "foldLeft").value
    r.put(0x944ADCC475E3AC22L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "slice").value
    r.put(0x5875AAC450B6296EL, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Mapped.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MJen.Internal.Mapped", "apply").value
    r.put(0x3A385F2B03C68B95L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "fold").value
    r.put(0xE86C87692152FA04L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "foldLeft").value
    r.put(0xDBBBEC8FD6C79F79L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "slice").value
    r.put(0x579F3426215EB81EL, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.FlatMapped.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MJen.Internal.FlatMapped", "apply").value
    r.put(0x1153F80972B0A671L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "fold").value
    r.put(0x571782490C0755AFL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "foldLeft").value
    r.put(0xB51371D89EB8EF08L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "slice").value
    r.put(0x1CB36C58E310985FL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "fold").value
    r.put(0xFED6B5BFBB723944L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "foldLeft").value
    r.put(0x6518F0B1965489FBL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "slice").value
    r.put(0x0DDBB7AC73BB86BAL, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.TakeWhile.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MJen.Internal.TakeWhile", "apply").value
    r.put(0xC99683DA8906A89AL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "fold").value
    r.put(0xFBA200C66EA07181L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "foldLeft").value
    r.put(0xC69C1C296E741816L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "slice").value
    r.put(0xACA940E29AD22134L, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.DropWhile.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MJen.Internal.DropWhile", "apply").value
    r.put(0x1B9450768B4E93FBL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "fold").value
    r.put(0xBE059E9E79636EA2L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "foldLeft").value
    r.put(0xD2E6A45B4D64984CL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "slice").value
    r.put(0x6701E9995B5CBD3BL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "fold").value
    r.put(0x6986B635B0DFDA64L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "foldLeft").value
    r.put(0x12B16202CDB71E9AL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "slice").value
    r.put(0x3F5A3D2A490E8F11L, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Zipped.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MJen.Internal.Zipped", "apply").value
    r.put(0x83618B195D301F1FL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "fold").value
    r.put(0xF35CA04BE8BFC2E6L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "foldLeft").value
    r.put(0x76BBF2F6647078C8L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "slice").value
    r.put(0xC3F74161DA5A3DA4L, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Concat.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MJen.Internal.Concat", "apply").value
    r.put(0xD4C995E94ABEF8BCL, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "fold").value
    r.put(0x5B917C8D1D8A3526L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "foldLeft").value
    r.put(0x17488483C80B2750L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "slice").value
    r.put(0xE3DF71B549861218L, _ => (o1: Any) => (o2: Any) => org.sireum.MJen.Internal.Product.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.MJen.Internal.Product", "apply").value
    r.put(0xE73BF9A6DBC89FC7L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Product", "fold").value
    r.put(0x48E03C03AC985EF8L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Product", "foldLeft").value
    r.put(0x6B472FE507A3A6E4L, r => (o1: Any) => (o2: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.MJen.Internal.Product", "slice").value
    r.put(0xEBBA5A921655D1ABL, r => (o1: Any) => (o2: Any) => X[org.sireum.Map[_, _]](r).getOrElse(X(o1), X(X[(() => _)](o2)()))) // methodKey(F, "org.sireum.Map", "getOrElse").value
    r.put(0x8D2BD2B523F38C93L, r => (o1: Any) => (o2: Any) => X[org.sireum.Map[_, _]](r).getOrElseEager(X(o1), X(o2))) // methodKey(F, "org.sireum.Map", "getOrElseEager").value
    r.put(0x33107511A8A9A90DL, r => (o1: Any) => (o2: Any) => X[org.sireum.ObjPrinter](r).cache(X(o1), X(o2))) // methodKey(F, "org.sireum.ObjPrinter", "cache").value
    r.put(0xF6743F8BCA07FA13L, r => (o1: Any) => (o2: Any) => X[org.sireum.Poset[_]](r).addParents(X(o1), X(o2))) // methodKey(F, "org.sireum.Poset", "addParents").value
    r.put(0x6F1E7BD23D213918L, r => (o1: Any) => (o2: Any) => X[org.sireum.Poset[_]](r).removeParent(X(o1), X(o2))) // methodKey(F, "org.sireum.Poset", "removeParent").value
    r.put(0xABC8C2831F2FCE95L, r => (o1: Any) => (o2: Any) => X[org.sireum.Poset[_]](r).removeChild(X(o1), X(o2))) // methodKey(F, "org.sireum.Poset", "removeChild").value
    r.put(0xDFA2DB5486B9737DL, r => (o1: Any) => (o2: Any) => X[org.sireum.Poset[_]](r).addChildren(X(o1), X(o2))) // methodKey(F, "org.sireum.Poset", "addChildren").value
    r.put(0xD6042E4C76C9036DL, r => (o1: Any) => (o2: Any) => X[org.sireum.Poset[_]](r).isChildOf(X(o1), X(o2))) // methodKey(F, "org.sireum.Poset", "isChildOf").value
    r.put(0x06CDD5451AE7D4D7L, r => (o1: Any) => (o2: Any) => X[org.sireum.Poset[_]](r).isParentOf(X(o1), X(o2))) // methodKey(F, "org.sireum.Poset", "isParentOf").value
    r.put(0x0273B337856240CFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextCBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextCBetween").value
    r.put(0x54610064CE2B1FB3L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextZBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextZBetween").value
    r.put(0x5DFD5CF7205195A3L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextF32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextF32Between").value
    r.put(0x0AE43149E7A18DCDL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextF64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextF64Between").value
    r.put(0xB6FDEE15FFC58F4BL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextRBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextRBetween").value
    r.put(0x6B9AF4A7179C8D82L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextU8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextU8Between").value
    r.put(0x07E0B84E7B6E22ACL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextU16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextU16Between").value
    r.put(0xFDE58991B33D0CECL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextU32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextU32Between").value
    r.put(0x1417912AB6680986L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextU64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextU64Between").value
    r.put(0x21756EE67C0B7B80L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextN8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextN8Between").value
    r.put(0xFCEE661EB664DF37L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextN16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextN16Between").value
    r.put(0x1A5DFAD1041D9378L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextN32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextN32Between").value
    r.put(0x43FED2D22365FBE6L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextN64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextN64Between").value
    r.put(0xA00E7C63F51ADB3EL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextS8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextS8Between").value
    r.put(0x45CE377FCCF8EAA1L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextS16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextS16Between").value
    r.put(0x49C08EE69F41C23AL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextS32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextS32Between").value
    r.put(0x445188613D4B2EB9L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextS64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextS64Between").value
    r.put(0x07A046CA6A85DD2DL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextZ8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextZ8Between").value
    r.put(0x930AAB6E314B2AD5L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextZ16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextZ16Between").value
    r.put(0x1BE761A3B9B356F6L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextZ32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextZ32Between").value
    r.put(0x26505C6C2A9E2E69L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen](r).nextZ64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen", "nextZ64Between").value
    r.put(0x14409448DA5A4BF4L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextCBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextCBetween").value
    r.put(0xD08E2B5DFD58AD8DL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextZBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextZBetween").value
    r.put(0x3235C5B94A8F9A87L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextF32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextF32Between").value
    r.put(0x503B7ABC2B802176L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextF64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextF64Between").value
    r.put(0x844FEB1158C04564L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextRBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextRBetween").value
    r.put(0xFD7F076894E3F7B2L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextU8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextU8Between").value
    r.put(0xB290FCAC6BC2E35DL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextU16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextU16Between").value
    r.put(0x53F00FE90CFD8D5AL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextU32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextU32Between").value
    r.put(0x616A829A30AB3468L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextU64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextU64Between").value
    r.put(0xE0A74441E3B0CBFBL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextN8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextN8Between").value
    r.put(0x6FCB7B45439580A3L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextN16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextN16Between").value
    r.put(0xD2DEC6F2F27C6509L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextN32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextN32Between").value
    r.put(0x334D3DC616ED854AL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextN64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextN64Between").value
    r.put(0x84E982D02DE4FE72L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextS8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextS8Between").value
    r.put(0x1007F66A7F880012L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextS16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextS16Between").value
    r.put(0xFA32DDB5D61F2060L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextS32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextS32Between").value
    r.put(0x40576DDF27B4F2BAL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextS64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextS64Between").value
    r.put(0x9265F5C1287424ADL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextZ8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextZ8Between").value
    r.put(0xBB7143BBD75CBA25L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextZ16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextZ16Between").value
    r.put(0xA7811BF8AB271D89L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextZ32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextZ32Between").value
    r.put(0xB8CF2AF8220A9D15L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64](r).nextZ64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64", "nextZ64Between").value
    r.put(0x76348B5C013AF62BL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextCBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextCBetween").value
    r.put(0xF600F43938E2AA7BL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextZBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZBetween").value
    r.put(0xB9EE1BFE4227968CL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextF32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextF32Between").value
    r.put(0xE8274867D21CBCBBL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextF64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextF64Between").value
    r.put(0xEDDD587A35FB0078L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextRBetween(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextRBetween").value
    r.put(0x6952B96EAEF6A68DL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextU8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextU8Between").value
    r.put(0xE1CE1B6E560D183FL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextU16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextU16Between").value
    r.put(0x5456861DBA9A3F54L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextU32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextU32Between").value
    r.put(0x23EDCAEB3193BF68L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextU64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextU64Between").value
    r.put(0x01283EDCC7C983EEL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextN8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextN8Between").value
    r.put(0x6ECB0ACC5C0F5CE2L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextN16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextN16Between").value
    r.put(0xAEA432676BF4FFFFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextN32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextN32Between").value
    r.put(0x6936E6C66C6161C9L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextN64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextN64Between").value
    r.put(0xC57A03809A59A291L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextS8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextS8Between").value
    r.put(0xC5ECF5F942E31F65L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextS16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextS16Between").value
    r.put(0x2E72D4BF8F4A5AABL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextS32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextS32Between").value
    r.put(0x3297AFB86FEC5FDAL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextS64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextS64Between").value
    r.put(0x4CF285298439D819L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextZ8Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ8Between").value
    r.put(0x7973643D49727353L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextZ16Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ16Between").value
    r.put(0xF12EE98E548C3293L, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextZ32Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ32Between").value
    r.put(0x412BF8793082D76AL, r => (o1: Any) => (o2: Any) => X[org.sireum.Random.Gen64Impl](r).nextZ64Between(X(o1), X(o2))) // methodKey(F, "org.sireum.Random.Gen64Impl", "nextZ64Between").value
    r.put(0x254E4794134C66CEL, r => (o1: Any) => (o2: Any) => X[org.sireum.UnionFind[_]](r).inSameSet(X(o1), X(o2))) // methodKey(F, "org.sireum.UnionFind", "inSameSet").value
    r.put(0x81CA0BCB569395A3L, r => (o1: Any) => (o2: Any) => X[org.sireum.UnionFind[_]](r).inSameSetCompress(X(o1), X(o2))) // methodKey(F, "org.sireum.UnionFind", "inSameSetCompress").value
    r.put(0x00CE94D4772D3C2DL, r => (o1: Any) => (o2: Any) => X[org.sireum.UnionFind[_]](r).merge(X(o1), X(o2))) // methodKey(F, "org.sireum.UnionFind", "merge").value
    r.put(0xA419F44D4B9054C8L, r => (o1: Any) => (o2: Any) => X[org.sireum.GitHub.Repository](r).submoduleShaOf(X(o1), X(o2))) // methodKey(F, "org.sireum.GitHub.Repository", "submoduleShaOf").value
    r.put(0xE1CDFE6DAFB04D1CL, r => (o1: Any) => (o2: Any) => X[org.sireum.Init](r).ideaDirPath(X(o1), X(o2))) // methodKey(F, "org.sireum.Init", "ideaDirPath").value
    r.put(0x3DE404F7DAD376EFL, r => (o1: Any) => (o2: Any) => X[org.sireum.Init](r).zipName(X(o1), X(o2))) // methodKey(F, "org.sireum.Init", "zipName").value
    r.put(0xC82368A1132E4391L, r => (o1: Any) => (o2: Any) => X[org.sireum.Init](r).downloadPlugins(X(o1), X(o2))) // methodKey(F, "org.sireum.Init", "downloadPlugins").value
    r.put(0x603CA0A438AA20D2L, r => (o1: Any) => (o2: Any) => X[org.sireum.Init](r).extractPlugins(X(o1), X(o2))) // methodKey(F, "org.sireum.Init", "extractPlugins").value
    r.put(0x2BDC90C1840281B1L, r => (o1: Any) => (o2: Any) => X[org.sireum.Os.Path.Jen[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Os.Path.Jen", "fold").value
    r.put(0xA883C33DC3705736L, r => (o1: Any) => (o2: Any) => X[org.sireum.Os.Path.Jen[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Os.Path.Jen", "foldLeft").value
    r.put(0x25C9A6219BB27462L, r => (o1: Any) => (o2: Any) => X[org.sireum.Os.Path.Jen[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Os.Path.Jen", "slice").value
    r.put(0x124B90849498E509L, r => (o1: Any) => (o2: Any) => X[org.sireum.Os.Path.MJen[_]](r).fold(X(o1), X(o2))) // methodKey(F, "org.sireum.Os.Path.MJen", "fold").value
    r.put(0xD1ABFCD4B646E299L, r => (o1: Any) => (o2: Any) => X[org.sireum.Os.Path.MJen[_]](r).foldLeft(X(o1), X(o2))) // methodKey(F, "org.sireum.Os.Path.MJen", "foldLeft").value
    r.put(0x9829504A22CFA7B4L, r => (o1: Any) => (o2: Any) => X[org.sireum.Os.Path.MJen[_]](r).slice(X(o1), X(o2))) // methodKey(F, "org.sireum.Os.Path.MJen", "slice").value
    r.put(0xB1C03B3B08BB65F4L, _ => (o1: Any) => (o2: Any) => org.sireum.Os.Proc.Result.Timeout.apply(X(o1), X(o2))) // methodKey(T, "org.sireum.Os.Proc.Result.Timeout", "apply").value
    r
  }

  private lazy val method3Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any](147)
    r.put(0x6FDEC8AB0C968E16L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.AssocS.Entries.keyIndexOfFrom(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.AssocS.Entries", "keyIndexOfFrom").value
    r.put(0x52BB07D48F688896L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.AssocS.Entries.valueIndexOfFrom(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.AssocS.Entries", "valueIndexOfFrom").value
    r.put(0x228EEDE99FBBA817L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.AssocS.Entries.indexOfFrom(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.AssocS.Entries", "indexOfFrom").value
    r.put(0x9322FB77BCD3AF80L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.AssocS.Entries.addPost(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.AssocS.Entries", "addPost").value
    r.put(0x2E90B1694BECC8C8L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.ContractUtil.isEqualExcept(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.ContractUtil", "isEqualExcept").value
    r.put(0x0DDF533857299A0DL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.ContractUtil.msEqualExcept(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.ContractUtil", "msEqualExcept").value
    r.put(0xE3D2C75414BEA770L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Graph.Internal.addPlainEdge(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Graph.Internal", "addPlainEdge").value
    r.put(0x3509BAD41ADDA52BL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Graph.Internal.removeEdge(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Graph.Internal", "removeEdge").value
    r.put(0x1062A10279C277FFL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Hash.t1ha(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Hash", "t1ha").value
    r.put(0x7A549F77A380FC9AL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISZ(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISZ").value
    r.put(0xDE27ED492146A562L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISZ8(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISZ8").value
    r.put(0x0ADD4FAE1FBA2481L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISZ16(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISZ16").value
    r.put(0x9D2F0A6EC80BCA14L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISZ32(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISZ32").value
    r.put(0xF2B0BEE7107CBC22L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISZ64(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISZ64").value
    r.put(0x3F04208068BA5241L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISN(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISN").value
    r.put(0xAF273B99D89D0E5BL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISN8(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISN8").value
    r.put(0x8C835EEE24B464B2L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISN16(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISN16").value
    r.put(0x880A59C528D3D3D6L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISN32(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISN32").value
    r.put(0xA0D3DCD6A148BEE0L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISN64(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISN64").value
    r.put(0xE6F22D1D0DE536D4L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISS8(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISS8").value
    r.put(0xCE196A5AC6CBE2E1L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISS16(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISS16").value
    r.put(0x757A1235C81C57B9L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISS32(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISS32").value
    r.put(0xC4609F99B0413FA3L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISS64(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISS64").value
    r.put(0x621828EFC6E9D394L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISU8(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISU8").value
    r.put(0x37692C0E092DCB1CL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISU16(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISU16").value
    r.put(0xB1A15623DFF7C679L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISU32(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISU32").value
    r.put(0x96DC1C48FE9E8418L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printISU64(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printISU64").value
    r.put(0x53DFE6EA40267032L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSZ(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSZ").value
    r.put(0x2F07216466A2F82AL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSZ8(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSZ8").value
    r.put(0x6223A47ACA5B9188L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSZ16(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSZ16").value
    r.put(0x8765854089DAE446L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSZ32(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSZ32").value
    r.put(0x2E169F9951290089L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSZ64(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSZ64").value
    r.put(0x559AA9FDAFB3D05EL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSN(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSN").value
    r.put(0x5CD3F87119451B2DL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSN8(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSN8").value
    r.put(0xC8B49CBA2D33CE2DL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSN16(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSN16").value
    r.put(0x98902341DC8D3DD4L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSN32(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSN32").value
    r.put(0x532298512EC61D94L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSN64(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSN64").value
    r.put(0x12F2E8A4D862F771L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSS8(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSS8").value
    r.put(0x0DF082F17A537A88L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSS16(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSS16").value
    r.put(0x2BBEEB62F73F99D6L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSS32(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSS32").value
    r.put(0x20950C5E83C84E76L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSS64(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSS64").value
    r.put(0x53D927C9390B2687L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSU8(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSU8").value
    r.put(0x3476151BA0CEECA9L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSU16(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSU16").value
    r.put(0x5D7DC32D97930E12L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSU32(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSU32").value
    r.put(0x14889A7A77222B2BL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMSU64(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMSU64").value
    r.put(0x8012890D65CE45C7L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printOption(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printOption").value
    r.put(0xD573F1AA3296B637L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printMOption(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printMOption").value
    r.put(0xEE0F8846E2903C0AL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printSet(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printSet").value
    r.put(0xD183C8750EC95EAEL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printHashSet(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printHashSet").value
    r.put(0x60C29056BB3FD64BL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printHashSSet(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printHashSSet").value
    r.put(0xAF86CCDAEEFDB4F4L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printStack(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printStack").value
    r.put(0x380D5FBF6585B914L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printBag(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printBag").value
    r.put(0x594143E98BC20BE4L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printHashBag(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printHashBag").value
    r.put(0xFD5EEDC89772F0CDL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printHashSBag(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printHashSBag").value
    r.put(0x9B5810086AA66F4CL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printPoset(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printPoset").value
    r.put(0xFEE8CC25F80F9BCCL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Printer.printUnionFind(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Printer", "printUnionFind").value
    r.put(0x4D73E8667D8597E5L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.addParents(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Poset.Internal", "addParents").value
    r.put(0xB1343508C6F553D7L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.removeParent(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Poset.Internal", "removeParent").value
    r.put(0x249BB802C7C46A2AL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.addChildren(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Poset.Internal", "addChildren").value
    r.put(0x0B8D1CDFFBF16C64L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.ancestorsCache(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Poset.Internal", "ancestorsCache").value
    r.put(0x1C02775232F5395DL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.ancestorsRec(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Poset.Internal", "ancestorsRec").value
    r.put(0xCB568988509D2993L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.descendantsCache(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Poset.Internal", "descendantsCache").value
    r.put(0xE9CE47CBFFF8DC34L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Poset.Internal.descendantsRec(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Poset.Internal", "descendantsRec").value
    r.put(0xB614309C8FAD008CL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Set.Elements.indexOfFrom(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Set.Elements", "indexOfFrom").value
    r.put(0x04583A070BBF6EC1L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.UnionFind.Internal.merge(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.UnionFind.Internal", "merge").value
    r.put(0x668E264D687C980BL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Coursier.fetch(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Coursier", "fetch").value
    r.put(0x1C60971559E6B847L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.GitHub.Ext.submoduleShaOf(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.GitHub.Ext", "submoduleShaOf").value
    r.put(0x46B352D8800F4CFAL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.chmod(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "chmod").value
    r.put(0x7DAC84684D93AAA4L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.copy(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "copy").value
    r.put(0x1775AC46D28B4F9EL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.move(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "move").value
    r.put(0x7FA7C5BCA7A2CA1FL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.write(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "write").value
    r.put(0x9C94130A1DC57158L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeLineStream(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "writeLineStream").value
    r.put(0xD014C73F8AAC3580L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeU8Stream(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "writeU8Stream").value
    r.put(0x25A99BBB5E3C63AAL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeCStream(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "writeCStream").value
    r.put(0xF88709441D0321E9L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeLineMStream(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "writeLineMStream").value
    r.put(0xBCE34BEF7DFA0590L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeU8MStream(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "writeU8MStream").value
    r.put(0x31DA590A38D5287BL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Ext.writeCMStream(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Ext", "writeCMStream").value
    r.put(0xD8B223B08AD2918DL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Scalafmt.format(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Scalafmt", "format").value
    r.put(0x184C956AEF36DBA4L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Graph.Edge.Data.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Graph.Edge.Data", "apply").value
    r.put(0x16CDC2B2E5E9D85EL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Graph.Internal.Edge.Data.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Graph.Internal.Edge.Data", "apply").value
    r.put(0x0C458229D3378884L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Graph[_, _]](r).addDataEdge(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Graph", "addDataEdge").value
    r.put(0x444C9E8719867F4CL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Graph[_, _]](r).toST(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Graph", "toST").value
    r.put(0x787367F096995B9EL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen", "mkStringWrap").value
    r.put(0xA15BD8534D05E3C8L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.ISImpl[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.ISImpl", "mkStringWrap").value
    r.put(0x963ED3095A0760EFL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.MapImpl[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.MapImpl", "mkStringWrap").value
    r.put(0xD12B484C1464F62CL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.HashMapImpl[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.HashMapImpl", "mkStringWrap").value
    r.put(0x46CEEE35D0A4999EL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.Filtered[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.Filtered", "mkStringWrap").value
    r.put(0x5809E4E98609034BL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.Mapped[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.Mapped", "mkStringWrap").value
    r.put(0x6FA79256BAD2305CL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.FlatMapped[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.FlatMapped", "mkStringWrap").value
    r.put(0x1F20A70027EF46F5L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Jen.Internal.Sliced.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Jen.Internal.Sliced", "apply").value
    r.put(0x366958C953A00A9BL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.Sliced[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.Sliced", "mkStringWrap").value
    r.put(0xDE1B4DBF8E44109FL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.TakeWhile[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.TakeWhile", "mkStringWrap").value
    r.put(0x81C510FD69E84D4FL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.DropWhile[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.DropWhile", "mkStringWrap").value
    r.put(0xA4F1B8CC410EDF1EL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.ZipWithIndexed[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.ZipWithIndexed", "mkStringWrap").value
    r.put(0x5C09C89C1C261A8DL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.Zipped[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.Zipped", "mkStringWrap").value
    r.put(0x48CA3BC611DBA95BL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.Concat[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.Concat", "mkStringWrap").value
    r.put(0x8B927C84275DB772L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Jen.Internal.Product[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Jen.Internal.Product", "mkStringWrap").value
    r.put(0xED2277144E27A2FAL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.ErrorMsg.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.ErrorMsg", "apply").value
    r.put(0xC37210066EC18119L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Json.Parser.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Json.Parser", "apply").value
    r.put(0xEAF1B28C0EBD1142L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.MBox3.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.MBox3", "apply").value
    r.put(0x01A7AF73C280A31DL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen", "mkStringWrap").value
    r.put(0xF3278A167FFC72D6L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.ISImpl[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.ISImpl", "mkStringWrap").value
    r.put(0x37BDEB23C31F7566L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.MSImpl[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.MSImpl", "mkStringWrap").value
    r.put(0x7BF58DDBC33252E0L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.MapImpl[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.MapImpl", "mkStringWrap").value
    r.put(0xA5617F60E2C354EDL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.HashMapImpl[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.HashMapImpl", "mkStringWrap").value
    r.put(0xC27613A6B3517AB3L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.Filtered[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.Filtered", "mkStringWrap").value
    r.put(0xA09E153FB6FD9692L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.Mapped[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.Mapped", "mkStringWrap").value
    r.put(0xDA3586F855F642FBL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.FlatMapped[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.FlatMapped", "mkStringWrap").value
    r.put(0x9008587C42D2459FL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.MJen.Internal.Sliced.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.MJen.Internal.Sliced", "apply").value
    r.put(0x140A902880F8A1C3L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.Sliced[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.Sliced", "mkStringWrap").value
    r.put(0x98CB5F797BCFEC8FL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.TakeWhile[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.TakeWhile", "mkStringWrap").value
    r.put(0xD809B82E86AD5FEFL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.DropWhile[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.DropWhile", "mkStringWrap").value
    r.put(0x92C286595BE13E0DL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.ZipWithIndexed[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.ZipWithIndexed", "mkStringWrap").value
    r.put(0xA1FA8195943CC5D1L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.Zipped[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.Zipped", "mkStringWrap").value
    r.put(0xF342D7048857B3D3L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.Concat[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.Concat", "mkStringWrap").value
    r.put(0x05BE6A5CF5B9FC9BL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.MJen.Internal.Product[_, _]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.MJen.Internal.Product", "mkStringWrap").value
    r.put(0x682510235FFB2F29L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).addMethod(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "addMethod").value
    r.put(0xC774C4E905CD863DL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printISZ(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printISZ").value
    r.put(0x7DF8D8888E89477EL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printSet(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printSet").value
    r.put(0xE6EBD88FE91BC38CL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printHashSet(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printHashSet").value
    r.put(0x9D64A06762FFE355L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printHashSSet(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printHashSSet").value
    r.put(0x473CAD133AED46A0L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printStack(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printStack").value
    r.put(0xD9B7D4E75D513C07L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printBag(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printBag").value
    r.put(0xF8FC1F9FCAC4B392L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printHashBag(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printHashBag").value
    r.put(0x7AA56F850105BB51L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printHashSBag(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printHashSBag").value
    r.put(0x0CFC490B08B4B9D6L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printPoset(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printPoset").value
    r.put(0x851B1A8A32CFFEC4L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.ObjPrinter](r).printUnionFind(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.ObjPrinter", "printUnionFind").value
    r.put(0xC24A2E235997D748L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.GitHub.Repository.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.GitHub.Repository", "apply").value
    r.put(0xDA6C2AA0F5002E98L, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Init.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Init", "apply").value
    r.put(0x820EB4449968AFCDL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Init](r).installJava(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Init", "installJava").value
    r.put(0x3A7F5D7D5E0081EDL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Init](r).installVSCodium(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Init", "installVSCodium").value
    r.put(0xCE6EED424CD35C08L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Init](r).ideaPlugins(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Init", "ideaPlugins").value
    r.put(0x5B2DF5DFB4F876F2L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path.Impl](r).writeU8Parts(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeU8Parts").value
    r.put(0x69E527D13D6CA649L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path.Impl](r).writeOverU8Parts(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8Parts").value
    r.put(0xD4876598C0D4A522L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendU8Parts(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8Parts").value
    r.put(0xF1D3CC5C7F56BBD8L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path.Impl](r).writeU8Partms(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeU8Partms").value
    r.put(0x20F0EB502EDED45DL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path.Impl](r).writeOverU8Partms(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeOverU8Partms").value
    r.put(0xD1095420663E952EL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path.Impl](r).writeAppendU8Partms(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path.Impl", "writeAppendU8Partms").value
    r.put(0x2D7ACDB284BE9041L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path.Jen[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path.Jen", "mkStringWrap").value
    r.put(0x5DAE25747AEE6BA1L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path.MJen[_]](r).mkStringWrap(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path.MJen", "mkStringWrap").value
    r.put(0xC510A095F9E1E58DL, _ => (o1: Any) => (o2: Any) => (o3: Any) => org.sireum.Os.Proc.Result.Normal.apply(X(o1), X(o2), X(o3))) // methodKey(T, "org.sireum.Os.Proc.Result.Normal", "apply").value
    r.put(0x141CAD03B7C461B2L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path](r).writeU8Parts(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path", "writeU8Parts").value
    r.put(0x3CE9773FD4E1E75DL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path](r).writeOverU8Parts(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path", "writeOverU8Parts").value
    r.put(0x3482D1D12596E8E4L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path](r).writeAppendU8Parts(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path", "writeAppendU8Parts").value
    r.put(0xC2E531A0CA90523EL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path](r).writeU8Partms(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path", "writeU8Partms").value
    r.put(0x30F40B8AC19DE4AAL, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path](r).writeOverU8Partms(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path", "writeOverU8Partms").value
    r.put(0xD5C0119A35696389L, r => (o1: Any) => (o2: Any) => (o3: Any) => X[org.sireum.Os.Path](r).writeAppendU8Partms(X(o1), X(o2), X(o3))) // methodKey(F, "org.sireum.Os.Path", "writeAppendU8Partms").value
    r
  }

  private lazy val method4Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any](17)
    r.put(0x06CE4C1C6299A4B7L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.ContractUtil.isEqualExcept2(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.ContractUtil", "isEqualExcept2").value
    r.put(0x462192823D0C380DL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.ContractUtil.msEqualExcept2(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.ContractUtil", "msEqualExcept2").value
    r.put(0x42524DE40C4B0149L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Graph.Internal.addDataEdge(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Graph.Internal", "addDataEdge").value
    r.put(0xB2736A561E861187L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Json.Printer.printEither(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Json.Printer", "printEither").value
    r.put(0x3886F910FDF0DAA8L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Json.Printer.printMEither(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Json.Printer", "printMEither").value
    r.put(0x0CA471B820678974L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Json.Printer.printMap(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Json.Printer", "printMap").value
    r.put(0xE5C789972D34F147L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Json.Printer.printHashMap(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Json.Printer", "printHashMap").value
    r.put(0xA6A85A80E3E456BFL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Json.Printer.printHashSMap(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Json.Printer", "printHashSMap").value
    r.put(0x1034BD30CBA6A2F5L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Json.Printer.printGraph(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Json.Printer", "printGraph").value
    r.put(0x1F8DA6713740AC23L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.MBox4.apply(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.MBox4", "apply").value
    r.put(0xE6761393B87A0B27L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Poset.apply(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Poset", "apply").value
    r.put(0x7459C34E401B215FL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Random.Impl.Xoshiro256.apply(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Random.Impl.Xoshiro256", "apply").value
    r.put(0x8B1A2EB12D18417EL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Random.Impl.Xoroshiro128.apply(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Random.Impl.Xoroshiro128", "apply").value
    r.put(0xC71F3B113F1B0EEEL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.UnionFind.apply(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.UnionFind", "apply").value
    r.put(0x2671667D67ADD78FL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.CoursierFileInfo.apply(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.CoursierFileInfo", "apply").value
    r.put(0x0AC204F233E5D49EL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => org.sireum.Init.Plugin.apply(X(o1), X(o2), X(o3), X(o4))) // methodKey(T, "org.sireum.Init.Plugin", "apply").value
    r.put(0x02E547788D981E95L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => X[org.sireum.Init](r).ideaConfig(X(o1), X(o2), X(o3), X(o4))) // methodKey(F, "org.sireum.Init", "ideaConfig").value
    r
  }

  private lazy val method5Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any](14)
    r.put(0xDA827F8B92A80AC2L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => org.sireum.Coursier.commandPrefix(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(T, "org.sireum.Coursier", "commandPrefix").value
    r.put(0xDE4F36C302DFCBC4L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => org.sireum.Os.Ext.writeU8s(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(T, "org.sireum.Os.Ext", "writeU8s").value
    r.put(0x683DDCC0FF753645L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => org.sireum.Os.Ext.writeU8ms(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(T, "org.sireum.Os.Ext", "writeU8ms").value
    r.put(0x74183BA1134AC95EL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => org.sireum.MBox5.apply(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(T, "org.sireum.MBox5", "apply").value
    r.put(0x9E0FDFFEA6E86BC1L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.ObjPrinter](r).printIS(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.ObjPrinter", "printIS").value
    r.put(0x1D79701D6408C907L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.ObjPrinter](r).printMS(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.ObjPrinter", "printMS").value
    r.put(0x1C23120B4E0C7260L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.ObjPrinter](r).printMap(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.ObjPrinter", "printMap").value
    r.put(0xE8348A9E975D463AL, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.ObjPrinter](r).printHashMap(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.ObjPrinter", "printHashMap").value
    r.put(0xFA866B06D8002275L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.ObjPrinter](r).printHashSMap(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.ObjPrinter", "printHashSMap").value
    r.put(0x0DBB68BCF918CC73L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.ObjPrinter](r).printGraph(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.ObjPrinter", "printGraph").value
    r.put(0x74F0C80EA75C4D63L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.Os.Path.Impl](r).overlayCopy(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.Os.Path.Impl", "overlayCopy").value
    r.put(0xC715DEB4676AE509L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.Os.Path.Impl](r).overlayMove(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.Os.Path.Impl", "overlayMove").value
    r.put(0xE5454CF7FC6199B2L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.Os.Path](r).overlayCopy(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.Os.Path", "overlayCopy").value
    r.put(0x8EFC011C607080A7L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => X[org.sireum.Os.Path](r).overlayMove(X(o1), X(o2), X(o3), X(o4), X(o5))) // methodKey(F, "org.sireum.Os.Path", "overlayMove").value
    r
  }

  private lazy val method6Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any](6)
    r.put(0x4FA7CB1E41AEA038L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.Coursier.resolve(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6))) // methodKey(T, "org.sireum.Coursier", "resolve").value
    r.put(0x953C464460D14B29L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.Coursier.fetchClassifiers(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6))) // methodKey(T, "org.sireum.Coursier", "fetchClassifiers").value
    r.put(0x0A7E12ED73F8644FL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.Graph.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6))) // methodKey(T, "org.sireum.Graph", "apply").value
    r.put(0xE8EF57F0E31EDDF8L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.MBox6.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6))) // methodKey(T, "org.sireum.MBox6", "apply").value
    r.put(0xF6A1101DA2660EB3L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => org.sireum.Coursier.Proxy.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6))) // methodKey(T, "org.sireum.Coursier.Proxy", "apply").value
    r.put(0x05BE5790F8372819L, r => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => X[org.sireum.Init](r).distro(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6))) // methodKey(F, "org.sireum.Init", "distro").value
    r
  }

  private lazy val method7Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any](4)
    r.put(0xD3D12C034D283665L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => org.sireum.CircularQueue.NoDrop.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7))) // methodKey(T, "org.sireum.CircularQueue.NoDrop", "apply").value
    r.put(0xA9BB46281F064D95L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => org.sireum.CircularQueue.DropFront.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7))) // methodKey(T, "org.sireum.CircularQueue.DropFront", "apply").value
    r.put(0xB1C12AC490122F88L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => org.sireum.CircularQueue.DropRear.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7))) // methodKey(T, "org.sireum.CircularQueue.DropRear", "apply").value
    r.put(0x6BE67996FFA234D4L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => org.sireum.MBox7.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7))) // methodKey(T, "org.sireum.MBox7", "apply").value
    r
  }

  private lazy val method8Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xCA514E40F5139C68L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => org.sireum.MBox8.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8))) // methodKey(T, "org.sireum.MBox8", "apply").value
    r
  }

  private lazy val method9Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](2)
    r.put(0x6A2DDA75A4C30EB0L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => org.sireum.MBox9.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9))) // methodKey(T, "org.sireum.MBox9", "apply").value
    r.put(0xBD4A2EF600F586C1L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => org.sireum.GitHub.Asset.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9))) // methodKey(T, "org.sireum.GitHub.Asset", "apply").value
    r
  }

  private lazy val method10Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](2)
    r.put(0x8BB1B93F03C8898BL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => org.sireum.MBox10.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10))) // methodKey(T, "org.sireum.MBox10", "apply").value
    r.put(0xD0E329570385C52DL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => org.sireum.GitHub.Release.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10))) // methodKey(T, "org.sireum.GitHub.Release", "apply").value
    r
  }

  private lazy val method11Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x2B2172B798271894L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => org.sireum.MBox11.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11))) // methodKey(T, "org.sireum.MBox11", "apply").value
    r
  }

  private lazy val method12Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x782BF8EFC1D69E3AL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => org.sireum.MBox12.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12))) // methodKey(T, "org.sireum.MBox12", "apply").value
    r
  }

  private lazy val method13Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x9A8DDB9D2BD004C0L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => org.sireum.MBox13.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13))) // methodKey(T, "org.sireum.MBox13", "apply").value
    r
  }

  private lazy val method14Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xF8242534A881F319L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => org.sireum.MBox14.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14))) // methodKey(T, "org.sireum.MBox14", "apply").value
    r
  }

  private lazy val method15Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](2)
    r.put(0xF4ACF715C69B9ADEL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => org.sireum.MBox15.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15))) // methodKey(T, "org.sireum.MBox15", "apply").value
    r.put(0xFB019D05939EA79BL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => org.sireum.Os.Proc.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15))) // methodKey(T, "org.sireum.Os.Proc", "apply").value
    r
  }

  private lazy val method16Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x0FE1479BF9373CB5L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => org.sireum.MBox16.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15), X(o16))) // methodKey(T, "org.sireum.MBox16", "apply").value
    r
  }

  private lazy val method17Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xAB525661561D8484L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => org.sireum.MBox17.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15), X(o16), X(o17))) // methodKey(T, "org.sireum.MBox17", "apply").value
    r
  }

  private lazy val method18Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xA51A10B8273F879EL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => org.sireum.MBox18.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15), X(o16), X(o17), X(o18))) // methodKey(T, "org.sireum.MBox18", "apply").value
    r
  }

  private lazy val method19Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0xDE5423BF02F065D3L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => (o19: Any) => org.sireum.MBox19.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15), X(o16), X(o17), X(o18), X(o19))) // methodKey(T, "org.sireum.MBox19", "apply").value
    r
  }

  private lazy val method20Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x93254705C04B46D0L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => (o19: Any) => (o20: Any) => org.sireum.MBox20.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15), X(o16), X(o17), X(o18), X(o19), X(o20))) // methodKey(T, "org.sireum.MBox20", "apply").value
    r
  }

  private lazy val method21Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x938D74B129B20BA0L, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => (o19: Any) => (o20: Any) => (o21: Any) => org.sireum.MBox21.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15), X(o16), X(o17), X(o18), X(o19), X(o20), X(o21))) // methodKey(T, "org.sireum.MBox21", "apply").value
    r
  }

  private lazy val method22Map: Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any] = {
    val r = new Long2ObjectOpenHashMap[Option[AnyRef] => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any => Any](1)
    r.put(0x593E3FC00A2EF78AL, _ => (o1: Any) => (o2: Any) => (o3: Any) => (o4: Any) => (o5: Any) => (o6: Any) => (o7: Any) => (o8: Any) => (o9: Any) => (o10: Any) => (o11: Any) => (o12: Any) => (o13: Any) => (o14: Any) => (o15: Any) => (o16: Any) => (o17: Any) => (o18: Any) => (o19: Any) => (o20: Any) => (o21: Any) => (o22: Any) => org.sireum.MBox22.apply(X(o1), X(o2), X(o3), X(o4), X(o5), X(o6), X(o7), X(o8), X(o9), X(o10), X(o11), X(o12), X(o13), X(o14), X(o15), X(o16), X(o17), X(o18), X(o19), X(o20), X(o21), X(o22))) // methodKey(T, "org.sireum.MBox22", "apply").value
    r
  }

  private def illegalReflection(title: String, isInObject: B, owner: String, name: String): Unit = {
    halt(s"$title reflection $owner${if (isInObject) "." else "#"}$name")
  }

  override def info(name: String): Option[Info] = {
    val r = nameMap.get(objectOrTypeKey(name).value)
    if (r == null) None() else Some(r)
  }

  override def classNameOf[T](o: T): Option[String] = {
    o match {
      case o: org.sireum.CircularQueue.Policy.Type => return Some("org.sireum.CircularQueue.Policy.Type")
      case o: org.sireum.Json.ValueKind.Type => return Some("org.sireum.Json.ValueKind.Type")
      case o: org.sireum.CoursierClassifier.Type => return Some("org.sireum.CoursierClassifier.Type")
      case o: org.sireum.Os.Kind.Type => return Some("org.sireum.Os.Kind.Type")
      case o: org.sireum.Os.Path.Kind.Type => return Some("org.sireum.Os.Path.Kind.Type")
      case o: org.sireum.Os.Path.WriteMode.Type => return Some("org.sireum.Os.Path.WriteMode.Type")
      case o: org.sireum.AssocS[_, _] => return Some("org.sireum.AssocS")
      case o: org.sireum.Bag[_] => return Some("org.sireum.Bag")
      case o: org.sireum.CircularQueue.NoDrop[_] => return Some("org.sireum.CircularQueue.NoDrop")
      case o: org.sireum.CircularQueue.DropFront[_] => return Some("org.sireum.CircularQueue.DropFront")
      case o: org.sireum.CircularQueue.DropRear[_] => return Some("org.sireum.CircularQueue.DropRear")
      case o: org.sireum.Either.Left[_, _] => return Some("org.sireum.Either.Left")
      case o: org.sireum.Either.Right[_, _] => return Some("org.sireum.Either.Right")
      case o: org.sireum.Graph.Edge.Plain[_, _] => return Some("org.sireum.Graph.Edge.Plain")
      case o: org.sireum.Graph.Edge.Data[_, _] => return Some("org.sireum.Graph.Edge.Data")
      case o: org.sireum.Graph.Internal.Edges.Set[_] => return Some("org.sireum.Graph.Internal.Edges.Set")
      case o: org.sireum.Graph.Internal.Edges.Bag[_] => return Some("org.sireum.Graph.Internal.Edges.Bag")
      case o: org.sireum.Graph.Internal.Edge.Plain[_] => return Some("org.sireum.Graph.Internal.Edge.Plain")
      case o: org.sireum.Graph.Internal.Edge.Data[_] => return Some("org.sireum.Graph.Internal.Edge.Data")
      case o: org.sireum.Graph[_, _] => return Some("org.sireum.Graph")
      case o: org.sireum.HashBag[_] => return Some("org.sireum.HashBag")
      case o: org.sireum.HashMap[_, _] => return Some("org.sireum.HashMap")
      case o: org.sireum.HashSBag[_] => return Some("org.sireum.HashSBag")
      case o: org.sireum.HashSMap[_, _] => return Some("org.sireum.HashSMap")
      case o: org.sireum.HashSSet[_] => return Some("org.sireum.HashSSet")
      case o: org.sireum.HashSet[_] => return Some("org.sireum.HashSet")
      case o: org.sireum.IndexMap[_, _] => return Some("org.sireum.IndexMap")
      case o: org.sireum.Indexable.Isz[_] => return Some("org.sireum.Indexable.Isz")
      case o: org.sireum.Indexable.IszDocInfo[_] => return Some("org.sireum.Indexable.IszDocInfo")
      case o: org.sireum.Jen.Internal.ISImpl[_, _] => return Some("org.sireum.Jen.Internal.ISImpl")
      case o: org.sireum.Jen.Internal.MapImpl[_, _] => return Some("org.sireum.Jen.Internal.MapImpl")
      case o: org.sireum.Jen.Internal.HashMapImpl[_, _] => return Some("org.sireum.Jen.Internal.HashMapImpl")
      case o: org.sireum.Jen.Internal.Filtered[_] => return Some("org.sireum.Jen.Internal.Filtered")
      case o: org.sireum.Jen.Internal.Mapped[_, _] => return Some("org.sireum.Jen.Internal.Mapped")
      case o: org.sireum.Jen.Internal.FlatMapped[_, _] => return Some("org.sireum.Jen.Internal.FlatMapped")
      case o: org.sireum.Jen.Internal.Sliced[_] => return Some("org.sireum.Jen.Internal.Sliced")
      case o: org.sireum.Jen.Internal.TakeWhile[_] => return Some("org.sireum.Jen.Internal.TakeWhile")
      case o: org.sireum.Jen.Internal.DropWhile[_] => return Some("org.sireum.Jen.Internal.DropWhile")
      case o: org.sireum.Jen.Internal.ZipWithIndexed[_] => return Some("org.sireum.Jen.Internal.ZipWithIndexed")
      case o: org.sireum.Jen.Internal.Zipped[_, _] => return Some("org.sireum.Jen.Internal.Zipped")
      case o: org.sireum.Jen.Internal.Concat[_] => return Some("org.sireum.Jen.Internal.Concat")
      case o: org.sireum.Jen.Internal.Product[_, _] => return Some("org.sireum.Jen.Internal.Product")
      case o: org.sireum.Json.ErrorMsg => return Some("org.sireum.Json.ErrorMsg")
      case o: org.sireum.Json.Parser => return Some("org.sireum.Json.Parser")
      case o: org.sireum.MBox[_] => return Some("org.sireum.MBox")
      case o: org.sireum.MBox2[_, _] => return Some("org.sireum.MBox2")
      case o: org.sireum.MBox3[_, _, _] => return Some("org.sireum.MBox3")
      case o: org.sireum.MBox4[_, _, _, _] => return Some("org.sireum.MBox4")
      case o: org.sireum.MBox5[_, _, _, _, _] => return Some("org.sireum.MBox5")
      case o: org.sireum.MBox6[_, _, _, _, _, _] => return Some("org.sireum.MBox6")
      case o: org.sireum.MBox7[_, _, _, _, _, _, _] => return Some("org.sireum.MBox7")
      case o: org.sireum.MBox8[_, _, _, _, _, _, _, _] => return Some("org.sireum.MBox8")
      case o: org.sireum.MBox9[_, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox9")
      case o: org.sireum.MBox10[_, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox10")
      case o: org.sireum.MBox11[_, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox11")
      case o: org.sireum.MBox12[_, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox12")
      case o: org.sireum.MBox13[_, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox13")
      case o: org.sireum.MBox14[_, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox14")
      case o: org.sireum.MBox15[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox15")
      case o: org.sireum.MBox16[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox16")
      case o: org.sireum.MBox17[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox17")
      case o: org.sireum.MBox18[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox18")
      case o: org.sireum.MBox19[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox19")
      case o: org.sireum.MBox20[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox20")
      case o: org.sireum.MBox21[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox21")
      case o: org.sireum.MBox22[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _] => return Some("org.sireum.MBox22")
      case o: org.sireum.MEither.Left[_, _] => return Some("org.sireum.MEither.Left")
      case o: org.sireum.MEither.Right[_, _] => return Some("org.sireum.MEither.Right")
      case o: org.sireum.MJen.Internal.ISImpl[_, _] => return Some("org.sireum.MJen.Internal.ISImpl")
      case o: org.sireum.MJen.Internal.MSImpl[_, _] => return Some("org.sireum.MJen.Internal.MSImpl")
      case o: org.sireum.MJen.Internal.MapImpl[_, _] => return Some("org.sireum.MJen.Internal.MapImpl")
      case o: org.sireum.MJen.Internal.HashMapImpl[_, _] => return Some("org.sireum.MJen.Internal.HashMapImpl")
      case o: org.sireum.MJen.Internal.Filtered[_] => return Some("org.sireum.MJen.Internal.Filtered")
      case o: org.sireum.MJen.Internal.Mapped[_, _] => return Some("org.sireum.MJen.Internal.Mapped")
      case o: org.sireum.MJen.Internal.FlatMapped[_, _] => return Some("org.sireum.MJen.Internal.FlatMapped")
      case o: org.sireum.MJen.Internal.Sliced[_] => return Some("org.sireum.MJen.Internal.Sliced")
      case o: org.sireum.MJen.Internal.TakeWhile[_] => return Some("org.sireum.MJen.Internal.TakeWhile")
      case o: org.sireum.MJen.Internal.DropWhile[_] => return Some("org.sireum.MJen.Internal.DropWhile")
      case o: org.sireum.MJen.Internal.ZipWithIndexed[_] => return Some("org.sireum.MJen.Internal.ZipWithIndexed")
      case o: org.sireum.MJen.Internal.Zipped[_, _] => return Some("org.sireum.MJen.Internal.Zipped")
      case o: org.sireum.MJen.Internal.Concat[_] => return Some("org.sireum.MJen.Internal.Concat")
      case o: org.sireum.MJen.Internal.Product[_, _] => return Some("org.sireum.MJen.Internal.Product")
      case o: org.sireum.MNone[_] => return Some("org.sireum.MNone")
      case o: org.sireum.MSome[_] => return Some("org.sireum.MSome")
      case o: org.sireum.Map[_, _] => return Some("org.sireum.Map")
      case o: org.sireum.None[_] => return Some("org.sireum.None")
      case o: org.sireum.Some[_] => return Some("org.sireum.Some")
      case o: org.sireum.Poset[_] => return Some("org.sireum.Poset")
      case o: org.sireum.Random.Gen64Impl => return Some("org.sireum.Random.Gen64Impl")
      case o: org.sireum.Random.Impl.SplitMix64 => return Some("org.sireum.Random.Impl.SplitMix64")
      case o: org.sireum.Random.Impl.Xoshiro256 => return Some("org.sireum.Random.Impl.Xoshiro256")
      case o: org.sireum.Random.Impl.Xoroshiro128 => return Some("org.sireum.Random.Impl.Xoroshiro128")
      case o: org.sireum.Set[_] => return Some("org.sireum.Set")
      case o: org.sireum.Stack[_] => return Some("org.sireum.Stack")
      case o: org.sireum.UnionFind[_] => return Some("org.sireum.UnionFind")
      case o: org.sireum.CoursierFileInfo => return Some("org.sireum.CoursierFileInfo")
      case o: org.sireum.Coursier.Proxy => return Some("org.sireum.Coursier.Proxy")
      case o: org.sireum.GitHub.Repository => return Some("org.sireum.GitHub.Repository")
      case o: org.sireum.GitHub.Release => return Some("org.sireum.GitHub.Release")
      case o: org.sireum.GitHub.Asset => return Some("org.sireum.GitHub.Asset")
      case o: org.sireum.Init.Plugin => return Some("org.sireum.Init.Plugin")
      case o: org.sireum.Init => return Some("org.sireum.Init")
      case o: org.sireum.Os.Path.Impl => return Some("org.sireum.Os.Path.Impl")
      case o: org.sireum.Os.Proc.FunLineFilter => return Some("org.sireum.Os.Proc.FunLineFilter")
      case o: org.sireum.Os.Proc.Result.Normal => return Some("org.sireum.Os.Proc.Result.Normal")
      case o: org.sireum.Os.Proc.Result.Exception => return Some("org.sireum.Os.Proc.Result.Exception")
      case o: org.sireum.Os.Proc.Result.Timeout => return Some("org.sireum.Os.Proc.Result.Timeout")
      case o: org.sireum.Os.Proc => return Some("org.sireum.Os.Proc")
      case _ => return None()
    }
  }

  override def invoke0[T, R](owner: String, name: String, receiver: T): R = {
    val isInObject = receiver == null
    val f = method0Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver)))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke1[T, T1, R](owner: String, name: String, receiver: T, o1: T1): R = {
    val isInObject = receiver == null
    val f = method1Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke2[T, T1, T2, R](owner: String, name: String, receiver: T, o1: T1, o2: T2): R = {
    val isInObject = receiver == null
    val f = method2Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke3[T, T1, T2, T3, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3): R = {
    val isInObject = receiver == null
    val f = method3Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke4[T, T1, T2, T3, T4, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4): R = {
    val isInObject = receiver == null
    val f = method4Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke5[T, T1, T2, T3, T4, T5, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5): R = {
    val isInObject = receiver == null
    val f = method5Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke6[T, T1, T2, T3, T4, T5, T6, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6): R = {
    val isInObject = receiver == null
    val f = method6Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke7[T, T1, T2, T3, T4, T5, T6, T7, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7): R = {
    val isInObject = receiver == null
    val f = method7Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke8[T, T1, T2, T3, T4, T5, T6, T7, T8, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8): R = {
    val isInObject = receiver == null
    val f = method8Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke9[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9): R = {
    val isInObject = receiver == null
    val f = method9Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke10[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10): R = {
    val isInObject = receiver == null
    val f = method10Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke11[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11): R = {
    val isInObject = receiver == null
    val f = method11Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke12[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12): R = {
    val isInObject = receiver == null
    val f = method12Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke13[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13): R = {
    val isInObject = receiver == null
    val f = method13Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke14[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14): R = {
    val isInObject = receiver == null
    val f = method14Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke15[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15): R = {
    val isInObject = receiver == null
    val f = method15Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke16[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16): R = {
    val isInObject = receiver == null
    val f = method16Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke17[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17): R = {
    val isInObject = receiver == null
    val f = method17Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke18[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18): R = {
    val isInObject = receiver == null
    val f = method18Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke19[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19): R = {
    val isInObject = receiver == null
    val f = method19Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18)(o19))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke20[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20): R = {
    val isInObject = receiver == null
    val f = method20Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18)(o19)(o20))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke21[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21): R = {
    val isInObject = receiver == null
    val f = method21Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18)(o19)(o20)(o21))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  override def invoke22[T, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](owner: String, name: String, receiver: T, o1: T1, o2: T2, o3: T3, o4: T4, o5: T5, o6: T6, o7: T7, o8: T8, o9: T9, o10: T10, o11: T11, o12: T12, o13: T13, o14: T14, o15: T15, o16: T16, o17: T17, o18: T18, o19: T19, o20: T20, o21: T21, o22: T22): R = {
    val isInObject = receiver == null
    val f = method22Map.get(methodKey(isInObject, owner, name).value)
    if (f == null) {
      illegalReflection("Unavailable", isInObject, owner, name)
    }
    val r: R = X(f(X(receiver))(o1)(o2)(o3)(o4)(o5)(o6)(o7)(o8)(o9)(o10)(o11)(o12)(o13)(o14)(o15)(o16)(o17)(o18)(o19)(o20)(o21)(o22))
    if (r == null) {
      illegalReflection("Invalid", isInObject, owner, name)
    }
    r
  }

  def info0 = Info( // org.sireum.AssocS.Entries
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "uniqueKeys", params = ISZ("entries")),
      Method(isInObject = true, isByName = F, name = "contain", params = ISZ("entries", "kv")),
      Method(isInObject = true, isByName = F, name = "containKey", params = ISZ("entries", "key")),
      Method(isInObject = true, isByName = F, name = "containValue", params = ISZ("entries", "value")),
      Method(isInObject = true, isByName = F, name = "keyIndexOfFrom", params = ISZ("entries", "key", "from")),
      Method(isInObject = true, isByName = F, name = "valueIndexOfFrom", params = ISZ("entries", "value", "from")),
      Method(isInObject = true, isByName = F, name = "indexOfFrom", params = ISZ("entries", "kv", "from")),
      Method(isInObject = true, isByName = F, name = "keys", params = ISZ("entries")),
      Method(isInObject = true, isByName = F, name = "values", params = ISZ("entries")),
      Method(isInObject = true, isByName = F, name = "addPost", params = ISZ("entries", "p", "res")),
      Method(isInObject = true, isByName = F, name = "add", params = ISZ("entries", "p")),
      Method(isInObject = true, isByName = F, name = "indexOf", params = ISZ("entries", "key")),
      Method(isInObject = true, isByName = F, name = "remove", params = ISZ("entries", "p"))
    )
  )

  def info3 = Info( // org.sireum.ContractUtil
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "modPos", params = ISZ("n", "m")),
      Method(isInObject = true, isByName = F, name = "modNeg", params = ISZ("n", "m")),
      Method(isInObject = true, isByName = F, name = "isEqualExcept", params = ISZ("s1", "s2", "i")),
      Method(isInObject = true, isByName = F, name = "isEqualExcept2", params = ISZ("s1", "s2", "i1", "i2")),
      Method(isInObject = true, isByName = F, name = "msEqualExcept", params = ISZ("s1", "s2", "i")),
      Method(isInObject = true, isByName = F, name = "msEqualExcept2", params = ISZ("s1", "s2", "i1", "i2")),
      Method(isInObject = true, isByName = F, name = "isAllIS", params = ISZ("s", "e")),
      Method(isInObject = true, isByName = F, name = "isAllMS", params = ISZ("s", "e"))
    )
  )

  def info4 = Info( // org.sireum.Graph.Internal
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "addEdge", params = ISZ("g", "e")),
      Method(isInObject = true, isByName = F, name = "addPlainEdge", params = ISZ("g", "src", "dst")),
      Method(isInObject = true, isByName = F, name = "addDataEdge", params = ISZ("g", "data", "src", "dst")),
      Method(isInObject = true, isByName = F, name = "removeEdge", params = ISZ("g", "e", "n")),
      Method(isInObject = true, isByName = F, name = "incoming", params = ISZ("g", "dst")),
      Method(isInObject = true, isByName = F, name = "outgoing", params = ISZ("g", "src"))
    )
  )

  def info5 = Info( // org.sireum.Hash
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "murmur3a", params = ISZ("data", "seed")),
      Method(isInObject = true, isByName = F, name = "t1ha0", params = ISZ("data", "seed")),
      Method(isInObject = true, isByName = F, name = "t1ha", params = ISZ("isFirst", "data", "seed"))
    )
  )

  def info6 = Info( // org.sireum.Jen.Internal
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info7 = Info( // org.sireum.Json
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "parseAst", params = ISZ("binding", "input")),
      Method(isInObject = true, isByName = F, name = "printAst", params = ISZ("binding", "v"))
    )
  )

  def info10 = Info( // org.sireum.Json.Printer
    kind = Kind.Object,
    fields = ISZ(
      Field(isInObject = true, isVal = T, kind = Field.Kind.Normal, name = "trueSt"),
      Field(isInObject = true, isVal = T, kind = Field.Kind.Normal, name = "falseSt"),
      Field(isInObject = true, isVal = T, kind = Field.Kind.Normal, name = "nullSt")
    ),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "printB", params = ISZ("b")),
      Method(isInObject = true, isByName = F, name = "printC", params = ISZ("c")),
      Method(isInObject = true, isByName = F, name = "printZ", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printZ8", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printZ16", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printZ32", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printZ64", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printN", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printN8", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printN16", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printN32", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printN64", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printS8", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printS16", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printS32", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printS64", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printU8", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printU16", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printU32", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printU64", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printF32", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printF64", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printR", params = ISZ("n")),
      Method(isInObject = true, isByName = F, name = "printISZ", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISZ8", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISZ16", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISZ32", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISZ64", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISN", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISN8", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISN16", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISN32", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISN64", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISS8", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISS16", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISS32", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISS64", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISU8", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISU16", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISU32", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printISU64", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSZ", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSZ8", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSZ16", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSZ32", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSZ64", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSN", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSN8", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSN16", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSN32", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSN64", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSS8", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSS16", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSS32", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSS64", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSU8", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSU16", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSU32", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printMSU64", params = ISZ("isSimple", "s", "f")),
      Method(isInObject = true, isByName = F, name = "printZS", params = ISZ("isSimple", "s")),
      Method(isInObject = true, isByName = F, name = "printOption", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printMOption", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printEither", params = ISZ("isSimple", "o", "f0", "f1")),
      Method(isInObject = true, isByName = F, name = "printMEither", params = ISZ("isSimple", "o", "f0", "f1")),
      Method(isInObject = true, isByName = F, name = "printMap", params = ISZ("isSimple", "o", "k", "v")),
      Method(isInObject = true, isByName = F, name = "printSet", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printHashMap", params = ISZ("isSimple", "o", "k", "v")),
      Method(isInObject = true, isByName = F, name = "printHashSet", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printHashSMap", params = ISZ("isSimple", "o", "k", "v")),
      Method(isInObject = true, isByName = F, name = "printHashSSet", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printStack", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printBag", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printHashBag", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printHashSBag", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printPoset", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printGraph", params = ISZ("isSimple", "o", "f", "g")),
      Method(isInObject = true, isByName = F, name = "printUnionFind", params = ISZ("isSimple", "o", "f")),
      Method(isInObject = true, isByName = F, name = "printMessage", params = ISZ("o")),
      Method(isInObject = true, isByName = F, name = "printPosition", params = ISZ("o")),
      Method(isInObject = true, isByName = F, name = "printDocInfo", params = ISZ("o")),
      Method(isInObject = true, isByName = F, name = "printString", params = ISZ("s")),
      Method(isInObject = true, isByName = F, name = "printConstant", params = ISZ("s")),
      Method(isInObject = true, isByName = F, name = "printNumber", params = ISZ("s")),
      Method(isInObject = true, isByName = F, name = "printObject", params = ISZ("fields")),
      Method(isInObject = true, isByName = F, name = "printIS", params = ISZ("isSimple", "elements")),
      Method(isInObject = true, isByName = F, name = "printMS", params = ISZ("isSimple", "elements"))
    )
  )

  def info11 = Info( // org.sireum.Json.Fun
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "printPure0", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure0", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print0", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse0", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure1", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure1", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print1", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse1", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure2", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure2", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print2", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse2", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure3", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure3", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print3", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse3", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure4", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure4", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print4", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse4", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure5", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure5", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print5", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse5", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure6", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure6", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print6", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse6", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure7", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure7", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print7", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse7", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure8", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure8", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print8", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse8", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure9", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure9", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print9", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse9", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure10", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure10", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print10", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse10", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure11", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure11", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print11", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse11", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure12", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure12", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print12", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse12", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure13", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure13", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print13", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse13", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure14", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure14", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print14", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse14", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure15", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure15", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print15", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse15", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure16", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure16", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print16", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse16", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure17", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure17", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print17", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse17", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure18", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure18", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print18", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse18", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure19", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure19", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print19", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse19", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure20", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure20", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print20", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse20", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure21", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure21", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print21", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse21", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "printPure22", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parsePure22", params = ISZ("parser", "f")),
      Method(isInObject = true, isByName = F, name = "print22", params = ISZ("f")),
      Method(isInObject = true, isByName = F, name = "parse22", params = ISZ("parser", "f"))
    )
  )

  def info12 = Info( // org.sireum.LibUtil
    kind = Kind.Object,
    fields = ISZ(
      Field(isInObject = true, isVal = T, kind = Field.Kind.Normal, name = "setOptions")
    ),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "parCores", params = ISZ("maxCores", "percentage")),
      Method(isInObject = true, isByName = F, name = "parCoresOpt", params = ISZ("maxCores", "percentageOpt")),
      Method(isInObject = true, isByName = F, name = "mineOptions", params = ISZ("fileContent")),
      Method(isInObject = true, isByName = F, name = "mineOptionsWithPrefix", params = ISZ("prefix", "fileContent"))
    )
  )

  def info13 = Info( // org.sireum.LibUtil.IS
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "unique", params = ISZ("s"))
    )
  )

  def info14 = Info( // org.sireum.Library
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "sharedFiles", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "jvmFiles", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "fontFiles", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "vscodeImageFiles", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "files", params = ISZ())
    )
  )

  def info15 = Info( // org.sireum.MJen.Internal
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info16 = Info( // org.sireum.Map.Entries
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info17 = Info( // org.sireum.OsProto
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info18 = Info( // org.sireum.Poset.Internal
    kind = Kind.Object,
    fields = ISZ(
      Field(isInObject = true, isVal = T, kind = Field.Kind.Normal, name = "emptySet")
    ),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "addNode", params = ISZ("poset", "node")),
      Method(isInObject = true, isByName = F, name = "addNodes", params = ISZ("poset", "nodes")),
      Method(isInObject = true, isByName = F, name = "addParents", params = ISZ("poset", "n", "ns")),
      Method(isInObject = true, isByName = F, name = "removeParent", params = ISZ("poset", "n", "parent")),
      Method(isInObject = true, isByName = F, name = "addChildren", params = ISZ("poset", "n", "ns")),
      Method(isInObject = true, isByName = F, name = "childrenOf", params = ISZ("poset", "n")),
      Method(isInObject = true, isByName = F, name = "parentsOf", params = ISZ("poset", "n")),
      Method(isInObject = true, isByName = F, name = "ancestorsOf", params = ISZ("poset", "n")),
      Method(isInObject = true, isByName = F, name = "ancestorsCache", params = ISZ("poset", "n", "acc")),
      Method(isInObject = true, isByName = F, name = "ancestorsRec", params = ISZ("poset", "m", "acc")),
      Method(isInObject = true, isByName = F, name = "lub", params = ISZ("poset", "ns")),
      Method(isInObject = true, isByName = F, name = "descendantsOf", params = ISZ("poset", "n")),
      Method(isInObject = true, isByName = F, name = "descendantsCache", params = ISZ("poset", "n", "acc")),
      Method(isInObject = true, isByName = F, name = "descendantsRec", params = ISZ("poset", "m", "acc")),
      Method(isInObject = true, isByName = F, name = "glb", params = ISZ("poset", "ns"))
    )
  )

  def info19 = Info( // org.sireum.Random
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "setSeed", params = ISZ("seed")),
      Method(isInObject = true, isByName = T, name = "create64", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "createSeed64", params = ISZ("seed")),
      Method(isInObject = true, isByName = F, name = "rotl32", params = ISZ("x", "k")),
      Method(isInObject = true, isByName = F, name = "rotl64", params = ISZ("x", "k"))
    )
  )

  def info20 = Info( // org.sireum.Random.Impl
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info21 = Info( // org.sireum.Random.Ext
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "instance", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "setSeed", params = ISZ("n"))
    )
  )

  def info22 = Info( // org.sireum.Set.Elements
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "unique", params = ISZ("elements")),
      Method(isInObject = true, isByName = F, name = "contain", params = ISZ("elements", "e")),
      Method(isInObject = true, isByName = F, name = "indexOfFrom", params = ISZ("elements", "e", "from"))
    )
  )

  def info23 = Info( // org.sireum.UnionFind.Internal
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "find", params = ISZ("ds", "e")),
      Method(isInObject = true, isByName = F, name = "findCompress", params = ISZ("ds", "e")),
      Method(isInObject = true, isByName = F, name = "merge", params = ISZ("ds", "e1", "e2"))
    )
  )

  def info24 = Info( // org.sireum.justification
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info25 = Info( // org.sireum.justification.natded
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info26 = Info( // org.sireum.justification.natded.prop
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "andI", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "andE1", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "andE2", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "orI1", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "orI2", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "implyE", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "negE", params = ISZ("p")),
      Method(isInObject = true, isByName = F, name = "bottomE", params = ISZ("p")),
      Method(isInObject = true, isByName = F, name = "sandI", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "sandE1", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "sandE2", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "sorI1", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "sorI2", params = ISZ("p", "q")),
      Method(isInObject = true, isByName = F, name = "simplyE", params = ISZ("p", "q"))
    )
  )

  def info27 = Info( // org.sireum.justification.natded.pred
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "allE", params = ISZ("P", "E")),
      Method(isInObject = true, isByName = F, name = "existsI", params = ISZ("P", "E"))
    )
  )

  def info28 = Info( // org.sireum.Asm
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "eraseNonNative", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "rewriteReleaseFence", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "rewriteSetSecurityManager", params = ISZ("path"))
    )
  )

  def info31 = Info( // org.sireum.Coursier
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "defaultCacheDir", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "commandPrefix", params = ISZ("isResolve", "scalaVersion", "cacheOpt", "mavenRepoUrls", "proxy")),
      Method(isInObject = true, isByName = F, name = "resolve", params = ISZ("scalaVersion", "cacheOpt", "mavenRepoUrls", "deps", "printTree", "proxy")),
      Method(isInObject = true, isByName = F, name = "fetch", params = ISZ("scalaVersion", "deps", "proxy")),
      Method(isInObject = true, isByName = F, name = "fetchClassifiers", params = ISZ("scalaVersion", "cacheOpt", "mavenRepoUrls", "deps", "cls", "proxy")),
      Method(isInObject = true, isByName = F, name = "isRuntimePublishedLocally", params = ISZ("scalaVersion", "version"))
    )
  )

  def info32 = Info( // org.sireum.GitHub
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "repo", params = ISZ("owner", "repo"))
    )
  )

  def info33 = Info( // org.sireum.GitHub.Ext
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = F, name = "latestRelease", params = ISZ("repo")),
      Method(isInObject = true, isByName = F, name = "releases", params = ISZ("repo")),
      Method(isInObject = true, isByName = F, name = "assets", params = ISZ("release")),
      Method(isInObject = true, isByName = F, name = "submoduleShaOf", params = ISZ("repo", "path", "ref"))
    )
  )

  def info34 = Info( // org.sireum.LibJvmUtil
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ()
  )

  def info35 = Info( // org.sireum.LibJvmUtil.Ext
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "create", params = ISZ())
    )
  )

  def info36 = Info( // org.sireum.Os
    kind = Kind.Object,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "cliArgs", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "cwd", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "exit", params = ISZ("code")),
      Method(isInObject = true, isByName = F, name = "env", params = ISZ("name")),
      Method(isInObject = true, isByName = T, name = "envs", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "fileSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "freeMemory", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "home", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isLinux", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isLinuxArm", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isMac", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isMacArm", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isWinArm", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "isWin", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "javaHomeOpt", params = ISZ("kind", "homeOpt")),
      Method(isInObject = true, isByName = F, name = "javaExe", params = ISZ("homeOpt")),
      Method(isInObject = true, isByName = T, name = "kind", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "lineSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "maxMemory", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "numOfProcessors", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "pathSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "pathSepChar", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "path", params = ISZ("value")),
      Method(isInObject = true, isByName = F, name = "printParseableMessages", params = ISZ("reporter")),
      Method(isInObject = true, isByName = F, name = "proc", params = ISZ("commands")),
      Method(isInObject = true, isByName = F, name = "procs", params = ISZ("commands")),
      Method(isInObject = true, isByName = F, name = "prop", params = ISZ("name")),
      Method(isInObject = true, isByName = T, name = "props", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "readIndexableCFrom", params = ISZ("url")),
      Method(isInObject = true, isByName = T, name = "roots", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "scalaHomeOpt", params = ISZ("homeOpt")),
      Method(isInObject = true, isByName = F, name = "scalaScript", params = ISZ("homeOpt")),
      Method(isInObject = true, isByName = F, name = "scalacScript", params = ISZ("homeOpt")),
      Method(isInObject = true, isByName = T, name = "sireumHomeOpt", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "slashDir", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "temp", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "tempFix", params = ISZ("prefix", "suffix")),
      Method(isInObject = true, isByName = F, name = "tempDir", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "tempDirFix", params = ISZ("prefix")),
      Method(isInObject = true, isByName = T, name = "totalMemory", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "uriToPath", params = ISZ("uri"))
    )
  )

  def info43 = Info( // org.sireum.Os.Ext
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "cliArgs", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "fileSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "lineSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "pathSep", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "pathSepChar", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "osKind", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "roots", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "abs", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "canon", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "chmod", params = ISZ("path", "mask", "all")),
      Method(isInObject = true, isByName = F, name = "copy", params = ISZ("path", "target", "over")),
      Method(isInObject = true, isByName = T, name = "detectSireumHome", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "download", params = ISZ("path", "url")),
      Method(isInObject = true, isByName = F, name = "env", params = ISZ("name")),
      Method(isInObject = true, isByName = T, name = "envs", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "exists", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "exit", params = ISZ("code")),
      Method(isInObject = true, isByName = T, name = "freeMemory", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "fromUri", params = ISZ("uri")),
      Method(isInObject = true, isByName = F, name = "isAbs", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isDir", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isFile", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isSymLink", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isExecutable", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isReadable", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "isWritable", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "kind", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "lastModified", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "length", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "list", params = ISZ("path")),
      Method(isInObject = true, isByName = T, name = "maxMemory", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "numOfProcessors", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "mergeFrom", params = ISZ("path", "sources")),
      Method(isInObject = true, isByName = F, name = "md5", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "move", params = ISZ("path", "target", "over")),
      Method(isInObject = true, isByName = F, name = "mkdir", params = ISZ("path", "all")),
      Method(isInObject = true, isByName = F, name = "mklink", params = ISZ("path", "target")),
      Method(isInObject = true, isByName = F, name = "name", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "norm", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "prop", params = ISZ("name")),
      Method(isInObject = true, isByName = T, name = "props", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "properties", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readSymLink", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "relativize", params = ISZ("path", "other")),
      Method(isInObject = true, isByName = F, name = "read", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readU8s", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readU8ms", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readLineStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readU8Stream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readCStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readIndexableCPath", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readIndexableCUrl", params = ISZ("url")),
      Method(isInObject = true, isByName = F, name = "readLineMStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readCMStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "readU8MStream", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "remove", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "removeAll", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "removeOnExit", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "sha1", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "sha3", params = ISZ("path", "numOfBytes")),
      Method(isInObject = true, isByName = F, name = "setLastModified", params = ISZ("path", "millis")),
      Method(isInObject = true, isByName = F, name = "setExecutable", params = ISZ("path", "value")),
      Method(isInObject = true, isByName = F, name = "setReadable", params = ISZ("path", "value")),
      Method(isInObject = true, isByName = F, name = "setWritable", params = ISZ("path", "value")),
      Method(isInObject = true, isByName = T, name = "slashDir", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "size", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "temp", params = ISZ("prefix", "suffix")),
      Method(isInObject = true, isByName = F, name = "tempDir", params = ISZ("prefix")),
      Method(isInObject = true, isByName = T, name = "totalMemory", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "toUri", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "write", params = ISZ("path", "content", "mode")),
      Method(isInObject = true, isByName = F, name = "writeU8s", params = ISZ("path", "u8s", "offset", "len", "mode")),
      Method(isInObject = true, isByName = F, name = "writeU8ms", params = ISZ("path", "u8s", "offset", "len", "mode")),
      Method(isInObject = true, isByName = F, name = "writeLineStream", params = ISZ("path", "lines", "mode")),
      Method(isInObject = true, isByName = F, name = "writeU8Stream", params = ISZ("path", "u8s", "mode")),
      Method(isInObject = true, isByName = F, name = "writeCStream", params = ISZ("path", "cs", "mode")),
      Method(isInObject = true, isByName = F, name = "writeLineMStream", params = ISZ("path", "lines", "mode")),
      Method(isInObject = true, isByName = F, name = "writeU8MStream", params = ISZ("path", "u8s", "mode")),
      Method(isInObject = true, isByName = F, name = "writeCMStream", params = ISZ("path", "cs", "mode")),
      Method(isInObject = true, isByName = F, name = "zip", params = ISZ("path", "target")),
      Method(isInObject = true, isByName = F, name = "unzip", params = ISZ("path", "target")),
      Method(isInObject = true, isByName = F, name = "unTarGz", params = ISZ("path", "target")),
      Method(isInObject = true, isByName = F, name = "parent", params = ISZ("path")),
      Method(isInObject = true, isByName = F, name = "proc", params = ISZ("e"))
    )
  )

  def info44 = Info( // org.sireum.Scalafmt
    kind = Kind.Ext,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = true, isByName = T, name = "version", params = ISZ()),
      Method(isInObject = true, isByName = T, name = "minimalConfig", params = ISZ()),
      Method(isInObject = true, isByName = F, name = "format", params = ISZ("config", "isScript", "content")),
      Method(isInObject = true, isByName = F, name = "formatFile", params = ISZ("config", "file"))
    )
  )

  def info45 = Info( // org.sireum.Unit
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ()
  )

  def info46 = Info( // org.sireum.Nothing
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ()
  )

  def info47 = Info( // org.sireum.AssocS
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "entries")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "keys", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keySet", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "valueSet", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("kvs")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("key", "default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("key", "default")),
      Method(isInObject = false, isByName = F, name = "entry", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "indexOf", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("keys")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("key")),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info48 = Info( // org.sireum.Bag
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "addN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "+#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "removeN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info49 = Info( // org.sireum.StepId
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ()
  )

  def info50 = Info( // org.sireum.CircularQueue
    kind = Kind.RecordTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "max", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "default", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scrub", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "policy", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "enqueue", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "dequeue", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info51 = Info( // org.sireum.CircularQueue.NoDrop
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "max"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "default"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "scrub"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "queue"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "front"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "rear"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "numOfElements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "policy", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "enqueue", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "dequeue", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info52 = Info( // org.sireum.CircularQueue.DropFront
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "max"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "default"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "scrub"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "queue"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "front"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "rear"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "numOfElements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "policy", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "enqueue", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "dequeue", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info53 = Info( // org.sireum.CircularQueue.DropRear
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "max"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "default"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "scrub"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "queue"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "front"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "rear"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "numOfElements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "policy", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "enqueue", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "dequeue", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info54 = Info( // org.sireum.Either
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info55 = Info( // org.sireum.Either.Left
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info56 = Info( // org.sireum.Either.Right
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info57 = Info( // org.sireum.Graph.Edge
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "source", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "dest", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toInternal", params = ISZ("map"))
    )
  )

  def info58 = Info( // org.sireum.Graph.Edge.Plain
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "source"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "dest")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toInternal", params = ISZ("map"))
    )
  )

  def info59 = Info( // org.sireum.Graph.Edge.Data
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "source"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "dest"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "data")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toInternal", params = ISZ("map"))
    )
  )

  def info60 = Info( // org.sireum.Graph.Internal.Edge
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "source", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "dest", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toEdge", params = ISZ("map"))
    )
  )

  def info61 = Info( // org.sireum.Graph.Internal.Edges
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p"))
    )
  )

  def info62 = Info( // org.sireum.Graph.Internal.Edges.Set
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "set")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p"))
    )
  )

  def info63 = Info( // org.sireum.Graph.Internal.Edges.Bag
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "set")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p"))
    )
  )

  def info64 = Info( // org.sireum.Graph.Internal.Edge.Plain
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "source"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "dest")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toEdge", params = ISZ("map"))
    )
  )

  def info65 = Info( // org.sireum.Graph.Internal.Edge.Data
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "source"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "dest"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "data")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toEdge", params = ISZ("map"))
    )
  )

  def info66 = Info( // org.sireum.Graph
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nodes"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nodesInverse"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "incomingEdges"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "outgoingEdges"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nextNodeId"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "multi")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "*", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "--*", params = ISZ("ns")),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("edge")),
      Method(isInObject = false, isByName = F, name = "+@", params = ISZ("edge")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("edge")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("edges")),
      Method(isInObject = false, isByName = F, name = "incoming", params = ISZ("dest")),
      Method(isInObject = false, isByName = F, name = "outgoing", params = ISZ("source")),
      Method(isInObject = false, isByName = F, name = "addEdge", params = ISZ("edge")),
      Method(isInObject = false, isByName = F, name = "addPlainEdge", params = ISZ("source", "dest")),
      Method(isInObject = false, isByName = F, name = "addDataEdge", params = ISZ("data", "source", "dest")),
      Method(isInObject = false, isByName = T, name = "allEdges", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeEdgeN", params = ISZ("edge", "n")),
      Method(isInObject = false, isByName = F, name = "edges", params = ISZ("source", "dest")),
      Method(isInObject = false, isByName = T, name = "numOfNodes", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "numOfEdges", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "toST", params = ISZ("attributes", "f", "g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info67 = Info( // org.sireum.HashBag
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "addN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("s")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "removeN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info68 = Info( // org.sireum.HashMap
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "mapEntries"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "size")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keys", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keySet", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "valueSet", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("entries")),
      Method(isInObject = false, isByName = F, name = "ensureCapacity", params = ISZ("sz")),
      Method(isInObject = false, isByName = F, name = "hashIndex", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "entry", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("keys")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("key")),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other"))
    )
  )

  def info69 = Info( // org.sireum.HashSBag
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "+#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "addN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("es")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("s")),
      Method(isInObject = false, isByName = F, name = "-#", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "removeN", params = ISZ("e", "n")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info70 = Info( // org.sireum.HashSMap
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "keys")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keySet", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "valueSet", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("entries")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "entry", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("keys")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("key")),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other"))
    )
  )

  def info71 = Info( // org.sireum.HashSSet
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info72 = Info( // org.sireum.HashSet
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "map")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "elements", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info73 = Info( // org.sireum.IndexMap
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "s")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("k")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("k")),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "entries", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keys", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "prettyST", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info74 = Info( // org.sireum.Indexable
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "has", params = ISZ("i"))
    )
  )

  def info75 = Info( // org.sireum.Indexable.Pos
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "posOpt", params = ISZ("offset", "length")),
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "has", params = ISZ("i"))
    )
  )

  def info76 = Info( // org.sireum.Indexable.Isz
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "is")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "has", params = ISZ("i"))
    )
  )

  def info77 = Info( // org.sireum.Indexable.IszDocInfo
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "is"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "info")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "has", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "posOpt", params = ISZ("offset", "length"))
    )
  )

  def info78 = Info( // org.sireum.Jen
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info79 = Info( // org.sireum.Jen.Internal.ISImpl
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "s")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info80 = Info( // org.sireum.Jen.Internal.MapImpl
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "m")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info81 = Info( // org.sireum.Jen.Internal.HashMapImpl
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "m")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info82 = Info( // org.sireum.Jen.Internal.Filtered
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info83 = Info( // org.sireum.Jen.Internal.Mapped
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info84 = Info( // org.sireum.Jen.Internal.FlatMapped
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info85 = Info( // org.sireum.Jen.Internal.Sliced
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "start"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "end")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info86 = Info( // org.sireum.Jen.Internal.TakeWhile
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info87 = Info( // org.sireum.Jen.Internal.DropWhile
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info88 = Info( // org.sireum.Jen.Internal.ZipWithIndexed
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info89 = Info( // org.sireum.Jen.Internal.Zipped
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info90 = Info( // org.sireum.Jen.Internal.Concat
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info91 = Info( // org.sireum.Jen.Internal.Product
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info92 = Info( // org.sireum.Json.JsonAstBinding
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "toObject", params = ISZ("fields")),
      Method(isInObject = false, isByName = F, name = "toArray", params = ISZ("elements")),
      Method(isInObject = false, isByName = F, name = "toNumber", params = ISZ("s")),
      Method(isInObject = false, isByName = F, name = "toString", params = ISZ("s")),
      Method(isInObject = false, isByName = T, name = "toNull", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toBoolean", params = ISZ("b")),
      Method(isInObject = false, isByName = F, name = "kind", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "fromObject", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "fromArray", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "fromNumber", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "fromString", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "fromBoolean", params = ISZ("o"))
    )
  )

  def info93 = Info( // org.sireum.Json.ErrorMsg
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "line"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "column"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "message")
    ),
    methods = ISZ()
  )

  def info94 = Info( // org.sireum.Json.Parser
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "input"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "offset"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "errorOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "typesOption")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "errorMessage", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "eof", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseB", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseZ8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseZ16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseZ32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseZ64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseN", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseN8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseN16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseN32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseN64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseS8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseS16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseS32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseS64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseU8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseU16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseU32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseF32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseF64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseR", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseISZ", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISZ8", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISZ16", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISZ32", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISZ64", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISN", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISN8", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISN16", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISN32", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISN64", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISS8", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISS16", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISS32", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISS64", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISU8", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISU16", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISU32", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseISU64", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSZ", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSZ8", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSZ16", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSZ32", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSZ64", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSN", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSN8", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSN16", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSN32", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSN64", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSS8", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSS16", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSS32", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSS64", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSU8", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSU16", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSU32", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMSU64", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseZS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseOption", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMOption", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseEither", params = ISZ("f0", "f1")),
      Method(isInObject = false, isByName = F, name = "parseMEither", params = ISZ("f0", "f1")),
      Method(isInObject = false, isByName = F, name = "parseMap", params = ISZ("k", "v")),
      Method(isInObject = false, isByName = F, name = "parseSet", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseHashMap", params = ISZ("k", "v")),
      Method(isInObject = false, isByName = F, name = "parseHashSet", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseHashSMap", params = ISZ("k", "v")),
      Method(isInObject = false, isByName = F, name = "parseHashSSet", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseStack", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseBag", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseHashBag", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseHashSBag", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parsePoset", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseGraph", params = ISZ("f", "g")),
      Method(isInObject = false, isByName = F, name = "parseUnionFind", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "parseMessage", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parsePosition", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseDocInfo", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "detect", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseObjectType", params = ISZ("expectedType")),
      Method(isInObject = false, isByName = F, name = "parseObjectTypes", params = ISZ("expectedTypes")),
      Method(isInObject = false, isByName = F, name = "parseObjectKey", params = ISZ("expectedKey")),
      Method(isInObject = false, isByName = F, name = "parseObjectKeys", params = ISZ("expectedKeys")),
      Method(isInObject = false, isByName = F, name = "parseObjectBegin", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseObjectNext", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseArrayBegin", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseArrayNext", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseNumber", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseString", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "parseConstant", params = ISZ("text")),
      Method(isInObject = false, isByName = F, name = "computeLineColumn", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "parseException", params = ISZ("i", "msg")),
      Method(isInObject = false, isByName = F, name = "errorIfEof", params = ISZ("i")),
      Method(isInObject = false, isByName = F, name = "incOffset", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "parseWhitespace", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isDigit", params = ISZ("c")),
      Method(isInObject = false, isByName = F, name = "isWhitespace", params = ISZ("c")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "til"))
    )
  )

  def info95 = Info( // org.sireum.MBox
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ()
  )

  def info96 = Info( // org.sireum.MBox2
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2")
    ),
    methods = ISZ()
  )

  def info97 = Info( // org.sireum.MBox3
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3")
    ),
    methods = ISZ()
  )

  def info98 = Info( // org.sireum.MBox4
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4")
    ),
    methods = ISZ()
  )

  def info99 = Info( // org.sireum.MBox5
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5")
    ),
    methods = ISZ()
  )

  def info100 = Info( // org.sireum.MBox6
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6")
    ),
    methods = ISZ()
  )

  def info101 = Info( // org.sireum.MBox7
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7")
    ),
    methods = ISZ()
  )

  def info102 = Info( // org.sireum.MBox8
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8")
    ),
    methods = ISZ()
  )

  def info103 = Info( // org.sireum.MBox9
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9")
    ),
    methods = ISZ()
  )

  def info104 = Info( // org.sireum.MBox10
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10")
    ),
    methods = ISZ()
  )

  def info105 = Info( // org.sireum.MBox11
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11")
    ),
    methods = ISZ()
  )

  def info106 = Info( // org.sireum.MBox12
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12")
    ),
    methods = ISZ()
  )

  def info107 = Info( // org.sireum.MBox13
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13")
    ),
    methods = ISZ()
  )

  def info108 = Info( // org.sireum.MBox14
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14")
    ),
    methods = ISZ()
  )

  def info109 = Info( // org.sireum.MBox15
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15")
    ),
    methods = ISZ()
  )

  def info110 = Info( // org.sireum.MBox16
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16")
    ),
    methods = ISZ()
  )

  def info111 = Info( // org.sireum.MBox17
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17")
    ),
    methods = ISZ()
  )

  def info112 = Info( // org.sireum.MBox18
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18")
    ),
    methods = ISZ()
  )

  def info113 = Info( // org.sireum.MBox19
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value19")
    ),
    methods = ISZ()
  )

  def info114 = Info( // org.sireum.MBox20
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value19"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value20")
    ),
    methods = ISZ()
  )

  def info115 = Info( // org.sireum.MBox21
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value19"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value20"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value21")
    ),
    methods = ISZ()
  )

  def info116 = Info( // org.sireum.MBox22
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value3"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value4"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value5"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value6"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value7"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value8"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value9"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value10"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value11"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value12"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value13"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value14"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value15"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value16"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value17"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value18"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value19"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value20"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value21"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "value22")
    ),
    methods = ISZ()
  )

  def info117 = Info( // org.sireum.MEither
    kind = Kind.RecordTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info118 = Info( // org.sireum.MEither.Left
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info119 = Info( // org.sireum.MEither.Right
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isLeft", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isRight", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "leftOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "left", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "rightOpt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "right", params = ISZ())
    )
  )

  def info120 = Info( // org.sireum.MJen
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info121 = Info( // org.sireum.MJen.Internal.ISImpl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "s")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info122 = Info( // org.sireum.MJen.Internal.MSImpl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "s")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info123 = Info( // org.sireum.MJen.Internal.MapImpl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "m")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info124 = Info( // org.sireum.MJen.Internal.HashMapImpl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "m")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info125 = Info( // org.sireum.MJen.Internal.Filtered
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info126 = Info( // org.sireum.MJen.Internal.Mapped
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info127 = Info( // org.sireum.MJen.Internal.FlatMapped
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("g")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info128 = Info( // org.sireum.MJen.Internal.Sliced
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "start"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "end")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info129 = Info( // org.sireum.MJen.Internal.TakeWhile
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info130 = Info( // org.sireum.MJen.Internal.DropWhile
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "p")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info131 = Info( // org.sireum.MJen.Internal.ZipWithIndexed
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info132 = Info( // org.sireum.MJen.Internal.Zipped
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info133 = Info( // org.sireum.MJen.Internal.Concat
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info134 = Info( // org.sireum.MJen.Internal.Product
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen2")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info135 = Info( // org.sireum.MOption
    kind = Kind.RecordTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "toMS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info136 = Info( // org.sireum.MNone
    kind = Kind.RecordClass,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info137 = Info( // org.sireum.MSome
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info138 = Info( // org.sireum.Map
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "entries")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "keys", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "values", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "keySet", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "valueSet", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("kvs")),
      Method(isInObject = false, isByName = F, name = "get", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("key", "default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("key", "default")),
      Method(isInObject = false, isByName = F, name = "entry", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "indexOf", params = ISZ("key")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("keys")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("key")),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other"))
    )
  )

  def info139 = Info( // org.sireum.ObjPrinter
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "freshNum", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "write", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "cache", params = ISZ("o", "f")),
      Method(isInObject = false, isByName = F, name = "addMethod", params = ISZ("tipe", "isStrictPure", "body")),
      Method(isInObject = false, isByName = F, name = "printISZ", params = ISZ("elementType", "s", "e")),
      Method(isInObject = false, isByName = F, name = "printIS", params = ISZ("indexType", "elementType", "s", "i", "e")),
      Method(isInObject = false, isByName = F, name = "printMS", params = ISZ("indexType", "elementType", "s", "i", "e")),
      Method(isInObject = false, isByName = F, name = "printMap", params = ISZ("keyType", "valueType", "o", "k", "v")),
      Method(isInObject = false, isByName = F, name = "printSet", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printHashMap", params = ISZ("keyType", "valueType", "o", "k", "v")),
      Method(isInObject = false, isByName = F, name = "printHashSet", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printHashSMap", params = ISZ("keyType", "valueType", "o", "k", "v")),
      Method(isInObject = false, isByName = F, name = "printHashSSet", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printStack", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printBag", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printHashBag", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printHashSBag", params = ISZ("elementType", "o", "f")),
      Method(isInObject = false, isByName = F, name = "printPoset", params = ISZ("elementType", "o", "e")),
      Method(isInObject = false, isByName = F, name = "printGraph", params = ISZ("vType", "eType", "o", "v", "e")),
      Method(isInObject = false, isByName = F, name = "printUnionFind", params = ISZ("eType", "o", "e")),
      Method(isInObject = false, isByName = F, name = "printMessage", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "printPosition", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "printDocInfo", params = ISZ("o"))
    )
  )

  def info140 = Info( // org.sireum.Option
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "toIS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info141 = Info( // org.sireum.None
    kind = Kind.DatatypeClass,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toIS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info142 = Info( // org.sireum.Some
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "getOrElse", params = ISZ("default")),
      Method(isInObject = false, isByName = F, name = "getOrElseEager", params = ISZ("default")),
      Method(isInObject = false, isByName = T, name = "get", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toIS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f"))
    )
  )

  def info143 = Info( // org.sireum.OsProto.Path
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ()
  )

  def info144 = Info( // org.sireum.OsProto.Proc.Result
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "out", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "err", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "exitCode", params = ISZ())
    )
  )

  def info145 = Info( // org.sireum.OsProto.Proc
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "commands", params = ISZ("cs")),
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("dir")),
      Method(isInObject = false, isByName = F, name = "env", params = ISZ("m")),
      Method(isInObject = false, isByName = F, name = "input", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "timeout", params = ISZ("millis")),
      Method(isInObject = false, isByName = T, name = "dontInheritEnv", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "redirectErr", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "bufferErr", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "console", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "echoEnv", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "echo", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "standard", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "script", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "outLineAction", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "errLineAction", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "shouldPrintCommands", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "shouldOutputConsole", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isErrAsOut", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "in", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "cmds", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "run", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "runCheck", params = ISZ())
    )
  )

  def info146 = Info( // org.sireum.Poset
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nodes"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nodesInverse"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "parents"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "children"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "emptySet")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("that")),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "addNode", params = ISZ("node")),
      Method(isInObject = false, isByName = T, name = "rootNodes", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "addParents", params = ISZ("node", "nds")),
      Method(isInObject = false, isByName = F, name = "removeParent", params = ISZ("node", "parent")),
      Method(isInObject = false, isByName = F, name = "removeChild", params = ISZ("n", "child")),
      Method(isInObject = false, isByName = F, name = "addChildren", params = ISZ("node", "nds")),
      Method(isInObject = false, isByName = F, name = "childrenOf", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "isChildOf", params = ISZ("node1", "node2")),
      Method(isInObject = false, isByName = F, name = "parentsOf", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "isParentOf", params = ISZ("node1", "node2")),
      Method(isInObject = false, isByName = F, name = "ancestorsOf", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "lub", params = ISZ("nds")),
      Method(isInObject = false, isByName = F, name = "descendantsOf", params = ISZ("node")),
      Method(isInObject = false, isByName = F, name = "glb", params = ISZ("nds")),
      Method(isInObject = false, isByName = F, name = "toST", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info147 = Info( // org.sireum.Random.Gen.TestRunner
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "next", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toCompactJson", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "fromJson", params = ISZ("json")),
      Method(isInObject = false, isByName = F, name = "test", params = ISZ("o"))
    )
  )

  def info148 = Info( // org.sireum.Random.Gen
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "nextB", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextR", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextCBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextRBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ64Between", params = ISZ("min", "max"))
    )
  )

  def info149 = Info( // org.sireum.Random.Gen64
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "genU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextB", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextR", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextCBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextRBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ64Between", params = ISZ("min", "max"))
    )
  )

  def info150 = Info( // org.sireum.Random.Gen64Impl
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "gen")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "genU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextB", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64_01", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextF64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextR", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextCBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextF64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextRBetween", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextS64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextU8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextU64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ8", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ16", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ32", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextZ64", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "nextN8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextN64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextS64Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ8Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ16Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ32Between", params = ISZ("min", "max")),
      Method(isInObject = false, isByName = F, name = "nextZ64Between", params = ISZ("min", "max"))
    )
  )

  def info151 = Info( // org.sireum.Random.Impl.SplitMix64
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "next", params = ISZ())
    )
  )

  def info152 = Info( // org.sireum.Random.Impl.Xoshiro256
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed0"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed3")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "update", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "pp", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "ss", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "p", params = ISZ())
    )
  )

  def info153 = Info( // org.sireum.Random.Impl.Xoroshiro128
    kind = Kind.RecordClass,
    fields = ISZ(
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed0"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed1"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed2"),
      Field(isInObject = false, isVal = F, kind = Field.Kind.Param, name = "seed3")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "update", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "pp", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "ss", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "p", params = ISZ())
    )
  )

  def info154 = Info( // org.sireum.Set
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "elements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "+", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "-", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "--", params = ISZ("is")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("e")),
      Method(isInObject = false, isByName = F, name = "union", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u222A", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "intersect", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\u2229", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "\\", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "indexOf", params = ISZ("e")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info155 = Info( // org.sireum.Stack
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "elements")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "nonEmpty", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "peek", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "push", params = ISZ("e")),
      Method(isInObject = false, isByName = T, name = "pop", params = ISZ())
    )
  )

  def info156 = Info( // org.sireum.UnionFind
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "elements"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "elementsInverse"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "parentOf"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "sizeOf")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "hash", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "isEqual", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "inSameSet", params = ISZ("element1", "element2")),
      Method(isInObject = false, isByName = F, name = "inSameSetCompress", params = ISZ("element1", "element2")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "findCompress", params = ISZ("element")),
      Method(isInObject = false, isByName = F, name = "merge", params = ISZ("element1", "element2")),
      Method(isInObject = false, isByName = F, name = "toST", params = ISZ("f")),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ())
    )
  )

  def info157 = Info( // org.sireum.CoursierFileInfo
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "org"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "module"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "version"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "pathString")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "path", params = ISZ())
    )
  )

  def info158 = Info( // org.sireum.Coursier.Proxy
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "hostOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "nonHostsOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "portOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "protocolOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "protocolUserEnvKeyOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "protocolPasswordEnvKeyOpt")
    ),
    methods = ISZ()
  )

  def info159 = Info( // org.sireum.GitHub.Credential
    kind = Kind.DatatypeTrait,
    fields = ISZ(),
    methods = ISZ()
  )

  def info160 = Info( // org.sireum.GitHub.Repository
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "connection"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "owner"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "name")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "latestRelease", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "releases", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "submoduleShaOf", params = ISZ("path", "ref"))
    )
  )

  def info161 = Info( // org.sireum.GitHub.Release
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "repo"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "id"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "name"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "publishedTime"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isDraft"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isPrerelease"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "tagName"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "commit"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "tarUrl"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "zipUrl")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "assets", params = ISZ())
    )
  )

  def info162 = Info( // org.sireum.GitHub.Asset
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "release"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "id"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "name"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "label"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "state"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "size"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "contentType"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "url"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "downloadCount")
    ),
    methods = ISZ()
  )

  def info163 = Info( // org.sireum.Init.Plugin
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "id"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isCommunity"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isJar"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "version")
    ),
    methods = ISZ()
  )

  def info164 = Info( // org.sireum.Init
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "home"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "kind"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "versions"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "sireumV"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "cache"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "pluginPrefix"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "ideaCacheDir"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Normal, name = "pluginsCacheDir")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "homeBin", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "homeLib", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "homeBinPlatform", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "binfmt", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "distroPlugins", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scalacPluginVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "coursierVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "jacocoVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scalacPlugin", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scalaVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "scalaHome", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "sireumJar", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "maryTtsJar", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "checkstack", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "checkstackVersion", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "javaVersion", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "platform", params = ISZ("k")),
      Method(isInObject = false, isByName = F, name = "installJava", params = ISZ("vs", "useNik", "force")),
      Method(isInObject = false, isByName = F, name = "installScala", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installScalacPlugin", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installCoursier", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installJacoco", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installZ3", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installCVC", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installMaryTTS", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installCheckStack", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installMill", params = ISZ("verbose")),
      Method(isInObject = false, isByName = F, name = "ideaDirPath", params = ISZ("isUltimate", "isServer")),
      Method(isInObject = false, isByName = F, name = "installScripts", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installFonts", params = ISZ("force")),
      Method(isInObject = false, isByName = F, name = "install7zz", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "installVSCodium", params = ISZ("existingInstallOpt", "extensionsDirOpt", "extensions")),
      Method(isInObject = false, isByName = T, name = "isIdeaInUserHome", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "ideaConfig", params = ISZ("isSetup", "isDev", "isUltimate", "projectPathOpt")),
      Method(isInObject = false, isByName = F, name = "ideaPlugins", params = ISZ("isDev", "isUltimate", "projectPathOpt")),
      Method(isInObject = false, isByName = F, name = "ideaSandbox", params = ISZ("isDev")),
      Method(isInObject = false, isByName = F, name = "zipName", params = ISZ("id", "version")),
      Method(isInObject = false, isByName = F, name = "downloadPlugins", params = ISZ("isDev", "pluginFilter")),
      Method(isInObject = false, isByName = F, name = "extractPlugins", params = ISZ("pluginsDir", "pluginFilter")),
      Method(isInObject = false, isByName = F, name = "buildForms", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "distro", params = ISZ("isDev", "buildPackage", "buildIve", "buildVSCodePackage", "isUltimate", "isServer")),
      Method(isInObject = false, isByName = F, name = "basicDeps", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "proyekCompileDeps", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "logikaDeps", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "deps", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "init", params = ISZ("setup"))
    )
  )

  def info165 = Info( // org.sireum.Os.Path.Impl
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "value")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "procString", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "/", params = ISZ("name")),
      Method(isInObject = false, isByName = F, name = "/+", params = ISZ("names")),
      Method(isInObject = false, isByName = F, name = "call", params = ISZ("args")),
      Method(isInObject = false, isByName = T, name = "canon", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "abs", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "chmod", params = ISZ("mask")),
      Method(isInObject = false, isByName = F, name = "chmodAll", params = ISZ("mask")),
      Method(isInObject = false, isByName = F, name = "combineFrom", params = ISZ("sources")),
      Method(isInObject = false, isByName = F, name = "copyTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "copyOverTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "downloadFrom", params = ISZ("url")),
      Method(isInObject = false, isByName = T, name = "exists", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "ext", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isAbs", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isDir", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFile", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isSymLink", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isExecutable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isReadable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isWritable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "kind", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "lastModified", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "length", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "list", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mergeFrom", params = ISZ("sources")),
      Method(isInObject = false, isByName = T, name = "md5", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "moveTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "moveOverTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "mkdir", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mkdirAll", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mklink", params = ISZ("target")),
      Method(isInObject = false, isByName = T, name = "name", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "overlayCopy", params = ISZ("target", "includeDir", "followLink", "pred", "report")),
      Method(isInObject = false, isByName = F, name = "overlayMove", params = ISZ("target", "includeDir", "followLink", "pred", "report")),
      Method(isInObject = false, isByName = F, name = "prependWith", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "properties", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readSymLink", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "relativize", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "read", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLines", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLineStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLineMStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8s", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8ms", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8Stream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8MStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readCStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readIndexableC", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readCMStream", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "remove", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeAll", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeOnExit", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "sha1", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "sha3", params = ISZ("numOfBytes")),
      Method(isInObject = false, isByName = F, name = "setLastModified", params = ISZ("millis")),
      Method(isInObject = false, isByName = F, name = "setExecutable", params = ISZ("executable")),
      Method(isInObject = false, isByName = F, name = "setReadable", params = ISZ("readable")),
      Method(isInObject = false, isByName = F, name = "setWritable", params = ISZ("writable")),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "slash", params = ISZ("args")),
      Method(isInObject = false, isByName = F, name = "touch", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toUri", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "write", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOver", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppend", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeOverU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeOverU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "zipTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "unzipTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "unTarGzTo", params = ISZ("target")),
      Method(isInObject = false, isByName = T, name = "up", params = ISZ())
    )
  )

  def info166 = Info( // org.sireum.Os.Path.Jen
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "path", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toISZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toIS", params = ISZ("init")),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info167 = Info( // org.sireum.Os.Path.MJen
    kind = Kind.MSig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "path", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "string", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "generate", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "foreach", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "find", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "exists", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "contains", params = ISZ("o")),
      Method(isInObject = false, isByName = F, name = "forall", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "count", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "countIf", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "fold", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "foldLeft", params = ISZ("initial", "f")),
      Method(isInObject = false, isByName = F, name = "reduce", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "reduceLeft", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "withFilter", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "map", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatMap", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "flatten", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "slice", params = ISZ("start", "end")),
      Method(isInObject = false, isByName = F, name = "take", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "drop", params = ISZ("n")),
      Method(isInObject = false, isByName = F, name = "takeWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = F, name = "dropWhile", params = ISZ("p")),
      Method(isInObject = false, isByName = T, name = "zipWithIndex", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "zip", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "product", params = ISZ("other")),
      Method(isInObject = false, isByName = F, name = "++", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "head", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "headOption", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toMSZ", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "toMS", params = ISZ("init")),
      Method(isInObject = false, isByName = F, name = "mkStringWrap", params = ISZ("start", "sep", "end")),
      Method(isInObject = false, isByName = F, name = "mkString", params = ISZ("sep"))
    )
  )

  def info168 = Info( // org.sireum.Os.Proc.LineFilter
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("line"))
    )
  )

  def info169 = Info( // org.sireum.Os.Proc.FunLineFilter
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "f")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "filter", params = ISZ("line"))
    )
  )

  def info170 = Info( // org.sireum.Os.Proc.Result
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "out", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "err", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "exitCode", params = ISZ())
    )
  )

  def info171 = Info( // org.sireum.Os.Proc.Result.Normal
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "exitCode"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "out"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "err")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ())
    )
  )

  def info172 = Info( // org.sireum.Os.Proc.Result.Exception
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "err")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "out", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "exitCode", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ())
    )
  )

  def info173 = Info( // org.sireum.Os.Proc.Result.Timeout
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "out"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "err")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "exitCode", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "ok", params = ISZ())
    )
  )

  def info174 = Info( // org.sireum.Os.Proc
    kind = Kind.DatatypeClass,
    fields = ISZ(
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "cmds"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "wd"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "envMap"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldAddEnv"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "in"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isErrAsOut"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldOutputConsole"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isErrBuffered"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldPrintEnv"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldPrintCommands"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "timeoutInMillis"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "shouldUseStandardLib"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "isScript"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "outLineActionOpt"),
      Field(isInObject = false, isVal = T, kind = Field.Kind.Param, name = "errLineActionOpt")
    ),
    methods = ISZ(
      Method(isInObject = false, isByName = F, name = "commands", params = ISZ("cs")),
      Method(isInObject = false, isByName = F, name = "at", params = ISZ("dir")),
      Method(isInObject = false, isByName = F, name = "env", params = ISZ("m")),
      Method(isInObject = false, isByName = F, name = "input", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "timeout", params = ISZ("millis")),
      Method(isInObject = false, isByName = T, name = "dontInheritEnv", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "redirectErr", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "bufferErr", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "console", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "echoEnv", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "echo", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "standard", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "script", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "outLineAction", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "errLineAction", params = ISZ("f")),
      Method(isInObject = false, isByName = F, name = "run", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "runCheck", params = ISZ())
    )
  )

  def info175 = Info( // org.sireum.Os.Path
    kind = Kind.Sig,
    fields = ISZ(),
    methods = ISZ(
      Method(isInObject = false, isByName = T, name = "value", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "procString", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "/", params = ISZ("name")),
      Method(isInObject = false, isByName = F, name = "/+", params = ISZ("names")),
      Method(isInObject = false, isByName = F, name = "call", params = ISZ("args")),
      Method(isInObject = false, isByName = T, name = "canon", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "abs", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "chmod", params = ISZ("mask")),
      Method(isInObject = false, isByName = F, name = "chmodAll", params = ISZ("mask")),
      Method(isInObject = false, isByName = F, name = "combineFrom", params = ISZ("sources")),
      Method(isInObject = false, isByName = F, name = "copyTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "copyOverTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "downloadFrom", params = ISZ("url")),
      Method(isInObject = false, isByName = T, name = "exists", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "ext", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isAbs", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isDir", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isFile", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isSymLink", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isExecutable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isReadable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "isWritable", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "kind", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "lastModified", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "length", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "list", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mergeFrom", params = ISZ("sources")),
      Method(isInObject = false, isByName = T, name = "md5", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "moveTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "moveOverTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "mkdir", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mkdirAll", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "mklink", params = ISZ("target")),
      Method(isInObject = false, isByName = T, name = "name", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "overlayCopy", params = ISZ("target", "includeDir", "followLink", "pred", "report")),
      Method(isInObject = false, isByName = F, name = "overlayMove", params = ISZ("target", "includeDir", "followLink", "pred", "report")),
      Method(isInObject = false, isByName = F, name = "prependWith", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "properties", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readSymLink", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "relativize", params = ISZ("other")),
      Method(isInObject = false, isByName = T, name = "read", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLines", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLineStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readLineMStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8s", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8ms", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8Stream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readU8MStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readCStream", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readIndexableC", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "readCMStream", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "remove", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeAll", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "removeOnExit", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "sha1", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "sha3", params = ISZ("numOfBytes")),
      Method(isInObject = false, isByName = F, name = "setLastModified", params = ISZ("millis")),
      Method(isInObject = false, isByName = F, name = "setExecutable", params = ISZ("executable")),
      Method(isInObject = false, isByName = F, name = "setReadable", params = ISZ("readable")),
      Method(isInObject = false, isByName = F, name = "setWritable", params = ISZ("writable")),
      Method(isInObject = false, isByName = T, name = "size", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "slash", params = ISZ("args")),
      Method(isInObject = false, isByName = F, name = "touch", params = ISZ()),
      Method(isInObject = false, isByName = T, name = "toUri", params = ISZ()),
      Method(isInObject = false, isByName = F, name = "write", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOver", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppend", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendLineStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendLineMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeOverU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8s", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Parts", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeOverU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8ms", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Partms", params = ISZ("content", "offset", "len")),
      Method(isInObject = false, isByName = F, name = "writeU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8Stream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendU8MStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendCStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeOverCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "writeAppendCMStream", params = ISZ("content")),
      Method(isInObject = false, isByName = F, name = "zipTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "unzipTo", params = ISZ("target")),
      Method(isInObject = false, isByName = F, name = "unTarGzTo", params = ISZ("target")),
      Method(isInObject = false, isByName = T, name = "up", params = ISZ())
    )
  )

  @inline def X[T](o: Any): T = o.asInstanceOf[T]

  override def string: String = "LibJvmUtil_Ext"
}