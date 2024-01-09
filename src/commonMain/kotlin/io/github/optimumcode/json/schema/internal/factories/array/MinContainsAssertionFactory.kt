package io.github.optimumcode.json.schema.internal.factories.array

import io.github.optimumcode.json.schema.internal.JsonSchemaAssertion
import io.github.optimumcode.json.schema.internal.LoadingContext
import io.github.optimumcode.json.schema.internal.factories.AbstractAssertionFactory
import io.github.optimumcode.json.schema.internal.factories.number.util.compareTo
import io.github.optimumcode.json.schema.internal.util.integerOrNull
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

internal object MinContainsAssertionFactory : AbstractAssertionFactory("minContains") {
  override fun createFromProperty(
    element: JsonElement,
    context: LoadingContext,
  ): JsonSchemaAssertion {
    require(element is JsonPrimitive && !element.isString) { "$property must be an integer" }
    val maxItemsValue = requireNotNull(element.integerOrNull) { "$property must be a valid integer" }
    require(maxItemsValue >= 0) { "$property must be a non-negative integer" }
    return CountContainsAssertion(
      path = context.schemaPath,
      expected = maxItemsValue,
      operationName = "at least",
      actualCount = { it.annotationCollector.annotated(ContainsAssertionFactory.ANNOTATION) },
    ) { expected, actual -> actual >= expected }
  }
}