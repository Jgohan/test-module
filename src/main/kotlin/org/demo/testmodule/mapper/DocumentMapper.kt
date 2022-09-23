package org.demo.testmodule.mapper

import org.demo.testmodule.entity.DocumentEntity
import org.demo.testmodule.model.DocumentModel
import org.springframework.stereotype.Component

@Component
class DocumentMapper : ModelEntityMapper<DocumentModel, DocumentEntity> {

    override fun toModel(entity: DocumentEntity) =
        DocumentModel(
            id = entity.id,
            documentDate = entity.documentDate
        )

    override fun toEntity(model: DocumentModel) =
        DocumentEntity(documentDate = model.documentDate)

}