# Runtime Library

* Parse tree structure and serialization (`toCompact`/`fromCompact`) are described in `library/shared/src/main/scala/org/sireum/parser/CLAUDE.md`, only refer to it when working with `NGrammar`, `PredictiveTable`, `LexerDfas`, or `ParseTree`
* Direct IS/MS construction from raw arrays is described in `library/shared/src/main/scala/org/sireum/CLAUDE.md`, only refer to it when optimizing IS/MS allocation in plain Scala
* Bytecode optimizer (BitsOptimizer, ZUnboxer) quick-testing procedure is described in `library/jvm/src/main/java/org/sireum/lang/optimizer/CLAUDE.md`, only refer to it when editing the optimizers
