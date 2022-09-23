package org.demo.testmodule.entity

import java.time.LocalDate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "documents")
class DocumentEntity (
    override val id: UUID = UUID.randomUUID(),
    val documentDate: LocalDate? = null,

    @OneToOne(mappedBy = "document", fetch = FetchType.LAZY)
    val testEntity: TestEntity? = null
) : AbstractEntity<UUID>(id)