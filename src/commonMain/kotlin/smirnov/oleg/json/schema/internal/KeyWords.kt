package smirnov.oleg.json.schema.internal

/**
 * Schema keywords that can be used during schema parsing.
 * Depending on the schema version the corresponding JSON property will be extracted from Schema definition.
 */
enum class KeyWords {
  REF, ID, ANCHOR, DEFINITIONS
}