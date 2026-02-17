# Runtime Library

## Parse Tree Structure (NGrammar.parse/parseRec)

The LL(k) parser (`NGrammar.parse/parseRec`) produces a tree of `ParseTree.Leaf` and `ParseTree.Node` values.

### ParseTree.Leaf (tokens)
Created by `LexerDfas.lex` for each token. Fields:
- `text: String` — the matched source text (empty `""` for synthetic EOF)
- `ruleName: String` — the lexer rule name from the grammar (e.g., `"ID"`, `"INT"`, `"LBRACE"`); for string literals, the quoted form (e.g., `"'val'"`); for EOF, `"EOF"`
- `tipe: S32` — unique token type ID from `PredictiveTable.nameMap`
- `isHidden: B` — `T` for whitespace/comment tokens (skipped by `LexerDfas.tokens` when `skipHidden = T`)
- `posOpt: Option[Position]` — source position

`Leaf` also extends `Token`, so `num` is an alias for `tipe`, and `toLeaf` returns `this`.

### ParseTree.Node (grammar rules)
Created by `NGrammar.parse/parseRec` for non-terminal rules. Fields:
- `children: ISZ[ParseTree]` — child nodes (Leaf or Node)
- `ruleName: String` — the grammar rule name (e.g., `"file"`, `"exp3"`, `"infixSuffix"`)
- `tipe: S32` — the rule's unique ID from `PredictiveTable.nameMap` (same namespace as token types)
- `posOpt: Option[Position]` — computed from first/last child positions

### Synthetic Rules (isSynthetic)
Grammar normalization (`Grammar.normalize`) converts `*`, `+`, `?` into synthetic recursive rules named `baseName$N` (e.g., `exp3$0`, `program$1`). These have `isSynthetic = T` in the `NRule`.

**Key behavior**: When `NRule.isSynthetic = T`, the parser **does not wrap** the children in a `ParseTree.Node`. Instead, children are inlined flat into the parent. This means:
- `rule*` / `rule+` / `rule?` do NOT produce their own nodes in the parse tree
- Their matched children appear directly as children of the enclosing non-synthetic rule
- For example, `exp3: exp2 infixSuffix*` produces a single `exp3` Node whose children are `[exp2_node, infixSuffix_node, infixSuffix_node, ...]`

### Two NRule Kinds
- `NRule.Elements` — a sequence of elements (single production). If non-synthetic, wraps children in `ParseTree.Node(trees, name, num)`.
- `NRule.Alts` — a choice among alternatives (multi-production). If non-synthetic, wraps the chosen alternative's result in `ParseTree.Node(trees, name, num)`. If synthetic, delegates directly to the chosen alternative without wrapping.

### Name/Type ID Mapping
`PredictiveTable.nameMap: HashSMap[String, S32]` maps both token names and rule names to unique `S32` IDs. `reverseNameMap` provides the inverse. String literal tokens use quoted keys like `"'val'"`. The same `S32` value appears in both `ParseTree.tipe` and `NRule.num`.
