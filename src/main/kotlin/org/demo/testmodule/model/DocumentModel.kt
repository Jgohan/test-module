package org.demo.testmodule.model

import java.time.LocalDate
import java.util.UUID

data class DocumentModel(
    val id: UUID? = null,
    val documentDate: LocalDate? = null
)