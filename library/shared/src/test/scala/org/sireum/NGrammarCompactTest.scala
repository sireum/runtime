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

import org.sireum.U32._
import org.sireum.parser._
import org.sireum.test._

class NGrammarCompactTest extends TestSuite {

  def emptyPT: PredictiveTable = PredictiveTable(
    k = 1,
    nameMap = HashSMap.empty[String, U32],
    rules = HashSMap.empty[U32, PredictiveNode]
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
        k = 1,
        nameMap = HashSMap.empty[String, U32] + "rule" ~> u32"0" + "TOK" ~> u32"1",
        rules = HashSMap.empty[U32, PredictiveNode] +
          u32"0" ~> PredictiveNode.Leaf(0)
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: Branch with entries, no default
    * - {
      val pt = PredictiveTable(
        k = 1,
        nameMap = HashSMap.empty[String, U32] + "rule" ~> u32"0" + "A" ~> u32"1" + "B" ~> u32"2",
        rules = HashSMap.empty[U32, PredictiveNode] +
          u32"0" ~> PredictiveNode.Branch(
            entries = HashSMap.empty[U32, PredictiveNode] +
              u32"1" ~> PredictiveNode.Leaf(0) +
              u32"2" ~> PredictiveNode.Leaf(1),
            defaultOpt = None()
          )
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: Branch with entries AND default
    * - {
      val pt = PredictiveTable(
        k = 1,
        nameMap = HashSMap.empty[String, U32] + "rule" ~> u32"0" + "A" ~> u32"1",
        rules = HashSMap.empty[U32, PredictiveNode] +
          u32"0" ~> PredictiveNode.Branch(
            entries = HashSMap.empty[U32, PredictiveNode] +
              u32"1" ~> PredictiveNode.Leaf(0),
            defaultOpt = Some(PredictiveNode.Leaf(1))
          )
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: Branch with empty entries and no default
    * - {
      val pt = PredictiveTable(
        k = 1,
        nameMap = HashSMap.empty[String, U32] + "rule" ~> u32"0",
        rules = HashSMap.empty[U32, PredictiveNode] +
          u32"0" ~> PredictiveNode.Branch(
            entries = HashSMap.empty[U32, PredictiveNode],
            defaultOpt = None()
          )
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: nested Branch nodes
    * - {
      val pt = PredictiveTable(
        k = 2,
        nameMap = HashSMap.empty[String, U32] + "rule" ~> u32"0" + "A" ~> u32"1" + "B" ~> u32"2" + "C" ~> u32"3",
        rules = HashSMap.empty[U32, PredictiveNode] +
          u32"0" ~> PredictiveNode.Branch(
            entries = HashSMap.empty[U32, PredictiveNode] +
              u32"1" ~> PredictiveNode.Branch(
                entries = HashSMap.empty[U32, PredictiveNode] +
                  u32"2" ~> PredictiveNode.Leaf(0) +
                  u32"3" ~> PredictiveNode.Leaf(1),
                defaultOpt = None()
              ),
            defaultOpt = Some(PredictiveNode.Leaf(2))
          )
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
      val ng = NGrammar(ruleMap = IS[U32, NRule](), pt = emptyPT)
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Elements with NElement.Str
    * - {
      val ng = NGrammar(
        ruleMap = IS[U32, NRule](
          NRule.Elements(
            name = "program",
            num = u32"0",
            isSynthetic = F,
            elements = ISZ(NElement.Str(value = "hello", num = u32"10"))
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
        ruleMap = IS[U32, NRule](
          NRule.Elements(
            name = "rule1",
            num = u32"0",
            isSynthetic = F,
            elements = ISZ(NElement.Ref(isTerminal = T, ruleName = "ID", num = u32"5"))
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
        ruleMap = IS[U32, NRule](
          NRule.Elements(
            name = "rule2",
            num = u32"0",
            isSynthetic = T,
            elements = ISZ(NElement.Ref(isTerminal = F, ruleName = "expr", num = u32"3"))
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
        ruleMap = IS[U32, NRule](
          NRule.Alts(
            name = "choice",
            num = u32"0",
            isSynthetic = F,
            alts = ISZ(u32"1", u32"2", u32"3")
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
        ruleMap = IS[U32, NRule](
          NRule.Elements(
            name = "complex",
            num = u32"0",
            isSynthetic = F,
            elements = ISZ(
              NElement.Str(value = "(", num = u32"10"),
              NElement.Ref(isTerminal = T, ruleName = "ID", num = u32"11"),
              NElement.Ref(isTerminal = F, ruleName = "body", num = u32"15"),
              NElement.Str(value = ")", num = u32"16")
            )
          )
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: multiple rules (both Elements and Alts) with non-trivial PredictiveTable
    * - {
      val pt = PredictiveTable(
        k = 1,
        nameMap = HashSMap.empty[String, U32] +
          "stmt" ~> u32"0" + "ifStmt" ~> u32"1" + "exprStmt" ~> u32"2" +
          "IF" ~> u32"3" + "ID" ~> u32"4",
        rules = HashSMap.empty[U32, PredictiveNode] +
          u32"0" ~> PredictiveNode.Branch(
            entries = HashSMap.empty[U32, PredictiveNode] +
              u32"3" ~> PredictiveNode.Leaf(0) +
              u32"4" ~> PredictiveNode.Leaf(1),
            defaultOpt = None()
          )
      )
      val ng = NGrammar(
        ruleMap = IS[U32, NRule](
          NRule.Alts(name = "stmt", num = u32"0", isSynthetic = F, alts = ISZ(u32"1", u32"2")),
          NRule.Elements(
            name = "ifStmt",
            num = u32"1",
            isSynthetic = F,
            elements = ISZ(
              NElement.Str(value = "if", num = u32"3"),
              NElement.Ref(isTerminal = F, ruleName = "expr", num = u32"5")
            )
          ),
          NRule.Elements(
            name = "exprStmt",
            num = u32"2",
            isSynthetic = F,
            elements = ISZ(NElement.Ref(isTerminal = T, ruleName = "ID", num = u32"4"))
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
        k = 1,
        nameMap = HashSMap.empty[String, U32] + "r" ~> u32"0",
        rules = HashSMap.empty[U32, PredictiveNode] + u32"0" ~> PredictiveNode.Leaf(0)
      )
      val ng = NGrammar(
        ruleMap = IS[U32, NRule](
          NRule.Elements(name = "r", num = u32"0", isSynthetic = F, elements = ISZ())
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
        ruleMap = IS[U32, NRule](
          NRule.Alts(name = "empty", num = u32"0", isSynthetic = T, alts = ISZ())
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // NGrammar: NRule.Elements with empty elements list
    * - {
      val ng = NGrammar(
        ruleMap = IS[U32, NRule](
          NRule.Elements(name = "eps", num = u32"0", isSynthetic = T, elements = ISZ())
        ),
        pt = emptyPT
      )
      val decoded = NGrammar.fromCompact(ng.toCompact)
      assert(decoded == ng)
    }

    // PredictiveTable: Branch with default being a Branch (nested default)
    * - {
      val pt = PredictiveTable(
        k = 2,
        nameMap = HashSMap.empty[String, U32] + "rule" ~> u32"0" + "A" ~> u32"1" + "B" ~> u32"2",
        rules = HashSMap.empty[U32, PredictiveNode] +
          u32"0" ~> PredictiveNode.Branch(
            entries = HashSMap.empty[U32, PredictiveNode] +
              u32"1" ~> PredictiveNode.Leaf(0),
            defaultOpt = Some(PredictiveNode.Branch(
              entries = HashSMap.empty[U32, PredictiveNode] +
                u32"2" ~> PredictiveNode.Leaf(1),
              defaultOpt = Some(PredictiveNode.Leaf(2))
            ))
          )
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

    // PredictiveTable: multiple rules
    * - {
      val pt = PredictiveTable(
        k = 1,
        nameMap = HashSMap.empty[String, U32] + "r1" ~> u32"0" + "r2" ~> u32"1" + "A" ~> u32"2" + "B" ~> u32"3",
        rules = HashSMap.empty[U32, PredictiveNode] +
          u32"0" ~> PredictiveNode.Branch(
            entries = HashSMap.empty[U32, PredictiveNode] +
              u32"2" ~> PredictiveNode.Leaf(0),
            defaultOpt = None()
          ) +
          u32"1" ~> PredictiveNode.Branch(
            entries = HashSMap.empty[U32, PredictiveNode] +
              u32"3" ~> PredictiveNode.Leaf(0),
            defaultOpt = Some(PredictiveNode.Leaf(1))
          )
      )
      val decoded = PredictiveTable.fromCompact(pt.toCompact)
      assert(decoded == pt)
    }

  }
}
