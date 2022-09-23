package org.demo.testmodule.service

import org.demo.testmodule.mapper.TestMapper
import org.demo.testmodule.model.TestModel
import org.demo.testmodule.repository.TestRepository
import org.demo.testmodule.util.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class TestService(
    @Autowired private val testRepository: TestRepository,
    @Autowired private val testMapper: TestMapper
) {

    fun getTests(): ResponseEntity<Iterable<TestModel>?> {
        val testEntities = testRepository.findAll().map(testMapper::toModel)

        return if (testEntities.isNotEmpty()) {
            Response.ok(testEntities)
        } else {
            Response.noContent()
        }
    }

    fun getTest(id: UUID): ResponseEntity<TestModel?> {
        return testRepository.findByIdOrNull(id)
            ?.let { Response.ok(testMapper.toModel(it)) }
            ?: Response.noContent()
    }

    @Transactional
    fun createTest(newTestModel: TestModel): ResponseEntity<String> {
        val newTestEntity = testMapper.toEntity(newTestModel)
        testRepository.save(newTestEntity)
        return Response.created("Test ${newTestEntity.id} was created")
    }

    @Transactional
    fun patchTest(
        id: UUID,
        updatedModelFields: Map<String, Any>
    ): ResponseEntity<String> {
        return testRepository.findByIdOrNull(id)
            ?.let { testEntity ->
                updatedModelFields["testName"]?.let { if (it is String) testEntity.testName = it }
                testRepository.save(testEntity)
                Response.ok("Test $id was updated")
            }
            ?: Response.conflict("Test $id wasn't found")
    }

    @Transactional
    fun deleteTest(id: UUID): ResponseEntity<String> {
        return if (testRepository.existsById(id)) {
            testRepository.deleteById(id)
            Response.ok("Test $id was deleted")
        } else {
            Response.conflict("Test $id wasn't found")
        }
    }

}