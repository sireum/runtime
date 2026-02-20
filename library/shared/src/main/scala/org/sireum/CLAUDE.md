# Shared Runtime Library

## Direct IS/MS Construction from Raw Arrays

In plain Scala files, avoid `ISZ[V](arr.toIndexedSeq: _*)` (copies through varargs). Construct directly:

```scala
// IS: new IS[I, V](companion, rawArray, length, boxer)
val rawArr = new Array[scala.Int](n)  // scala.Int for U32/S32, scala.Long for U64/S64, AnyRef for reference types
val is = new IS[Z, U32](Z, rawArr, Z.MP(n), U32.fromZ(0).boxer)

// MS: new MS[I, V](companion, rawArray, length, boxer)
val ms = new MS[Z, U32](Z, rawArr, Z.MP(n), U32.fromZ(0).boxer)
```

- `companion`: index type companion (e.g., `Z` for `ISZ`/`MSZ`)
- `boxer`: from `HasBoxer.boxer` on any instance (e.g., `U32.fromZ(0).boxer`). For `@datatype`/`@sig`/traits: use `$internal.IdentityBoxer` with `Array[AnyRef]`.
- `length`: `Z.MP(n)` (wraps a Long as Z)
