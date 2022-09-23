package org.demo.testmodule.mapper

import org.demo.testmodule.entity.DictionaryValueEntity
import org.demo.testmodule.model.DictionaryValueModel
import org.springframework.stereotype.Component

@Component
class DictionaryValueMapper : ModelEntityMapper<DictionaryValueModel, DictionaryValueEntity> {

    override fun toModel(entity: DictionaryValueEntity) =
        DictionaryValueModel(
            id =  entity.id,
            name = entity.name
        )

    override fun toEntity(model: DictionaryValueModel) =
        DictionaryValueEntity(name = model.name)

}