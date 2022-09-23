package org.demo.testmodule.entity

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "dictionary_values")
class DictionaryValueEntity (
    override val id: UUID = UUID.randomUUID(),
    val name: String? = null,
    @OneToOne(mappedBy = "dictionaryValue", fetch = FetchType.LAZY)
    val testEntity: TestEntity? = null
) : AbstractEntity<UUID>(id)