package org.demo.testmodule.model

import java.util.UUID

data class TestModel(
    val id: UUID? = null,
    val document: DocumentModel? = null,
    val dictionaryValue: DictionaryValueModel? = null,
    val sortOrder: String? = null,
    val testName: String? = null
)