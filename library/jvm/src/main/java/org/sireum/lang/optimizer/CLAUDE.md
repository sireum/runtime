# Bytecode Optimization

## Bytecode Optimizers (BitsOptimizer, ZUnboxer)

`BitsOptimizer.java` and `ZUnboxer.java` live in `runtime/library/jvm/src/main/java/org/sireum/lang/optimizer/`. They run after scalac during `proyek compile` (and `proyek test`).

**Quick testing without full rebuild** — compile the optimizer from source and run on target `.class` files:

```bash
CACHE=lib/cache/https/repo1.maven.org/maven2/org/ow2/asm
ASM_CP="$CACHE/asm/9.9.1/asm-9.9.1.jar:$CACHE/asm-tree/9.9.1/asm-tree-9.9.1.jar:$CACHE/asm-util/9.9.1/asm-util-9.9.1.jar:$CACHE/asm-analysis/9.9.1/asm-analysis-9.9.1.jar"
javac -cp "$ASM_CP:bin/sireum.jar" -d tmp/<session>/out <optimizer>.java TestDriver.java
java -cp "tmp/<session>/out:$ASM_CP:bin/sireum.jar" TestDriver <class-files>
```

Test driver calls `new BitsOptimizer(true).transformClassBytes(original)` (or `ZUnboxer`) + `CheckClassAdapter.verify`.

- CRITICAL: Put `tmp/<session>/out` FIRST in classpath — old optimizer in the jar shadows the freshly compiled one.
- CRITICAL: After rebuilding `sireum.jar`, `proyek compile` JVM still uses the old jar. Recompile with `--recompile <module>` via `SIREUM_NATIVE=false bin/sireum` CLI.
