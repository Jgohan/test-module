package org.demo.testmodule.mapper

import org.demo.testmodule.entity.TestEntity
import org.demo.testmodule.model.TestModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TestMapper(
    @Autowired private val documentMapper: DocumentMapper,
    @Autowired private val dictionaryValueMapper: DictionaryValueMapper
) : ModelEntityMapper<TestModel, TestEntity> {

    override fun toModel(entity: TestEntity) =
        TestModel(
            id = entity.id,
            document = entity.document?.let { documentMapper.toModel(it) },
            dictionaryValue = entity.dictionaryValue?.let { dictionaryValueMapper.toModel(it) },
            sortOrder = entity.sortOrder,
            testName = entity.testName
        )

    override fun toEntity(model: TestModel) =
        TestEntity(
            document = model.document?.let { documentMapper.toEntity(it) },
            dictionaryValue = model.dictionaryValue?.let { dictionaryValueMapper.toEntity(it) },
            sortOrder = model.sortOrder
        ).apply {
            model.testName?.let { this.testName = it }
        }

}