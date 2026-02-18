/*
 Copyright (c) 2017-2026,Robby, Kansas State University
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

import org.sireum.S32._
import org.sireum.parser._
import org.sireum.test._

class NGrammarCompactTest extends TestSuite {

  def emptyPT: PredictiveTable = PredictiveTable(
    k = s32"1",
    nameMap = HashSMap.empty[String, S32],
    rules = IS[S32, PredictiveNode]()
  )

  val tests = Tests {

    // ---- PredictiveTable compact ----

    // PredictiveTable: empty table roundtrip
    * - {
      val pt = emptyPT
      val compact = pt.toCompact
      val decoded = PredictiveTable.fromCompact(compact)
      assert(decoded == pt)
    }

    // PredictiveTable: table with Leaf nodes only
    * - {
      val pt = PredictiveTable(
        k = s32"1",
        nameMap = HashSMap.empty[String, S32] + "rule" ~> s32"0" + "TOK" ~> s32"1",
        rules = IS[S32, PredictiveNode](PredictiveNode.Leaf(s32"0"))
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: Branch with entries, no default
    // nameMap: "rule"->0, "A"->1, "B"->2 (size 3)
    // entries: slot 1->Leaf(0), slot 2->Leaf(1), slot 0->sentinel
    * - {
      val pt = PredictiveTable(
        k = s32"1",
        nameMap = HashSMap.empty[String, S32] + "rule" ~> s32"0" + "A" ~> s32"1" + "B" ~> s32"2",
        rules = IS[S32, PredictiveNode](
          PredictiveNode.Branch(
            entries = IS[S32, PredictiveNode](
              PredictiveNode.sentinel,
              PredictiveNode.Leaf(s32"0"),
              PredictiveNode.Leaf(s32"1")
            ),
            defaultOpt = None()
          ))
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: Branch with entries AND default
    // nameMap: "rule"->0, "A"->1 (size 2)
    // entries: slot 0->Leaf(1) (default-filled), slot 1->Leaf(0) (explicit)
    * - {
      val pt = PredictiveTable(
        k = s32"1",
        nameMap = HashSMap.empty[String, S32] + "rule" ~> s32"0" + "A" ~> s32"1",
        rules = IS[S32, PredictiveNode](
          PredictiveNode.Branch(
            entries = IS[S32, PredictiveNode](
              PredictiveNode.Leaf(s32"1"),
              PredictiveNode.Leaf(s32"0")
            ),
            defaultOpt = Some(PredictiveNode.Leaf(s32"1"))
          ))
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: Branch with empty entries and no default
    // nameMap: "rule"->0 (size 1)
    // entries: slot 0->sentinel
    * - {
      val pt = PredictiveTable(
        k = s32"1",
        nameMap = HashSMap.empty[String, S32] + "rule" ~> s32"0",
        rules = IS[S32, PredictiveNode](
          PredictiveNode.Branch(
            entries = IS[S32, PredictiveNode](
              PredictiveNode.sentinel
            ),
            defaultOpt = None()
          ))
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: nested Branch nodes
    // nameMap: "rule"->0, "A"->1, "B"->2, "C"->3 (size 4)
    // outer: slot 1->inner Branch (explicit), slots 0,2,3->Leaf(2) (default-filled)
    // inner: slot 2->Leaf(0), slot 3->Leaf(1), slots 0,1->sentinel
    * - {
      val innerBranch = PredictiveNode.Branch(
        entries = IS[S32, PredictiveNode](
          PredictiveNode.sentinel,
          PredictiveNode.sentinel,
          PredictiveNode.Leaf(s32"0"),
          PredictiveNode.Leaf(s32"1")
        ),
        defaultOpt = None()
      )
      val pt = PredictiveTable(
        k = s32"2",
        nameMap = HashSMap.empty[String, S32] + "rule" ~> s32"0" + "A" ~> s32"1" + "B" ~> s32"2" + "C" ~> s32"3",
        rules = IS[S32, PredictiveNode](
          PredictiveNode.Branch(
            entries = IS[S32, PredictiveNode](
              PredictiveNode.Leaf(s32"2"),
              innerBranch,
              PredictiveNode.Leaf(s32"2"),
              PredictiveNode.Leaf(s32"2")
            ),
            defaultOpt = Some(PredictiveNode.Leaf(s32"2"))
          ))
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: toCompactST produces correct format
    * - {
      val pt = emptyPT
      val st = pt.toCompactST
      val rendered = st.render
      assert(ops.StringOps(rendered).startsWith("PredictiveTable.fromCompact(\""))
      // Verify the string inside can be decoded
      val inner = ops.StringOps(rendered).substring(29, rendered.size - 2)
      val decoded = PredictiveTable.fromCompact(inner)
      assert(decoded == pt)
    }

    // ---- NGrammar compact with NElement variants ----

    // NGrammar: empty ruleMap
    * - {
      val ng = NGrammar(ruleMap = IS[S32, NRule](), pt = emptyPT)
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Elements with NElement.Str
    * - {
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Elements(
            name = "program",
            num = s32"0",
            isSynthetic = F,
            elements = ISZ(NElement.Str(value = "hello", num = s32"10"))
          )
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Elements with NElement.Ref (isTerminal = T)
    * - {
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Elements(
            name = "rule1",
            num = s32"0",
            isSynthetic = F,
            elements = ISZ(NElement.Ref(isTerminal = T, ruleName = "ID", num = s32"5"))
          )
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Elements with NElement.Ref (isTerminal = F)
    * - {
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Elements(
            name = "rule2",
            num = s32"0",
            isSynthetic = T,
            elements = ISZ(NElement.Ref(isTerminal = F, ruleName = "expr", num = s32"3"))
          )
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Alts
    * - {
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Alts(
            name = "choice",
            num = s32"0",
            isSynthetic = F,
            alts = IS[S32, S32](s32"1", s32"2", s32"3")
          )
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Elements with multiple NElement variants
    * - {
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Elements(
            name = "complex",
            num = s32"0",
            isSynthetic = F,
            elements = ISZ(
              NElement.Str(value = "(", num = s32"10"),
              NElement.Ref(isTerminal = T, ruleName = "ID", num = s32"11"),
              NElement.Ref(isTerminal = F, ruleName = "body", num = s32"15"),
              NElement.Str(value = ")", num = s32"16")
            )
          )
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: multiple rules (both Elements and Alts) with non-trivial PredictiveTable
    // nameMap: "stmt"->0, "ifStmt"->1, "exprStmt"->2, "IF"->3, "ID"->4 (size 5)
    * - {
      val pt = PredictiveTable(
        k = s32"1",
        nameMap = HashSMap.empty[String, S32] +
          "stmt" ~> s32"0" + "ifStmt" ~> s32"1" + "exprStmt" ~> s32"2" +
          "IF" ~> s32"3" + "ID" ~> s32"4",
        rules = IS[S32, PredictiveNode](
          PredictiveNode.Branch(
            entries = IS[S32, PredictiveNode](
              PredictiveNode.sentinel,
              PredictiveNode.sentinel,
              PredictiveNode.sentinel,
              PredictiveNode.Leaf(s32"0"),
              PredictiveNode.Leaf(s32"1")
            ),
            defaultOpt = None()
          ))
      )
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Alts(name = "stmt", num = s32"0", isSynthetic = F, alts = IS[S32, S32](s32"1", s32"2")),
          NRule.Elements(
            name = "ifStmt",
            num = s32"1",
            isSynthetic = F,
            elements = ISZ(
              NElement.Str(value = "if", num = s32"3"),
              NElement.Ref(isTerminal = F, ruleName = "expr", num = s32"5")
            )
          ),
          NRule.Elements(
            name = "exprStmt",
            num = s32"2",
            isSynthetic = F,
            elements = ISZ(NElement.Ref(isTerminal = T, ruleName = "ID", num = s32"4"))
          )
        ),
        pt = pt
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: toCompactST produces correct format and roundtrips
    * - {
      val pt = PredictiveTable(
        k = s32"1",
        nameMap = HashSMap.empty[String, S32] + "r" ~> s32"0",
        rules = IS[S32, PredictiveNode](PredictiveNode.Leaf(s32"0"))
      )
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Elements(name = "r", num = s32"0", isSynthetic = F, elements = ISZ())
        ),
        pt = pt
      )
      val st = ng.toCompactST
      val rendered = st.render
      assert(ops.StringOps(rendered).startsWith("NGrammar.fromCompact(\""))
      // Extract the Base64 string and verify roundtrip
      val inner = ops.StringOps(rendered).substring(22, rendered.size - 2)
      val decoded = NGrammar.fromCompact(inner)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Alts with empty alts list
    * - {
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Alts(name = "empty", num = s32"0", isSynthetic = T, alts = IS[S32, S32]())
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Elements with empty elements list
    * - {
      val ng = NGrammar(
        ruleMap = IS[S32, NRule](
          NRule.Elements(name = "eps", num = s32"0", isSynthetic = T, elements = ISZ())
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // PredictiveTable: Branch with default being a Branch (nested default)
    // nameMap: "rule"->0, "A"->1, "B"->2 (size 3)
    // inner: slot 0->Leaf(2) (default), slot 1->Leaf(2) (default), slot 2->Leaf(1) (explicit)
    // outer: slot 0->inner (default fill), slot 1->Leaf(0) (explicit), slot 2->inner (default fill)
    * - {
      val innerBranch = PredictiveNode.Branch(
        entries = IS[S32, PredictiveNode](
          PredictiveNode.Leaf(s32"2"),
          PredictiveNode.Leaf(s32"2"),
          PredictiveNode.Leaf(s32"1")
        ),
        defaultOpt = Some(PredictiveNode.Leaf(s32"2"))
      )
      val pt = PredictiveTable(
        k = s32"2",
        nameMap = HashSMap.empty[String, S32] + "rule" ~> s32"0" + "A" ~> s32"1" + "B" ~> s32"2",
        rules = IS[S32, PredictiveNode](
          PredictiveNode.Branch(
            entries = IS[S32, PredictiveNode](
              innerBranch,
              PredictiveNode.Leaf(s32"0"),
              innerBranch
            ),
            defaultOpt = None()
          ))
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: multiple rules
    // nameMap: "r1"->0, "r2"->1, "A"->2, "B"->3 (size 4)
    // rule 0: slot 2->Leaf(0), rest sentinel
    // rule 1: slot 3->Leaf(0), rest->Leaf(1) (default)
    * - {
      val pt = PredictiveTable(
        k = s32"1",
        nameMap = HashSMap.empty[String, S32] + "r1" ~> s32"0" + "r2" ~> s32"1" + "A" ~> s32"2" + "B" ~> s32"3",
        rules = IS[S32, PredictiveNode](
          PredictiveNode.Branch(
            entries = IS[S32, PredictiveNode](
              PredictiveNode.sentinel,
              PredictiveNode.sentinel,
              PredictiveNode.Leaf(s32"0"),
              PredictiveNode.sentinel
            ),
            defaultOpt = None()
          ),
          PredictiveNode.Branch(
            entries = IS[S32, PredictiveNode](
              PredictiveNode.Leaf(s32"1"),
              PredictiveNode.Leaf(s32"1"),
              PredictiveNode.Leaf(s32"1"),
              PredictiveNode.Leaf(s32"0")
            ),
            defaultOpt = Some(PredictiveNode.Leaf(s32"1"))
          ))
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

  }
}
