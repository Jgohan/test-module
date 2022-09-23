package org.demo.testmodule.controller

import org.demo.testmodule.model.TestModel
import org.demo.testmodule.service.TestService
import org.demo.testmodule.util.lazyLoggerFor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/tests")
class TestController(
    @Autowired private val testService: TestService
) {

    companion object {
        private val logger by lazyLoggerFor<TestController>()
    }

    @GetMapping
    fun getTests(): ResponseEntity<Iterable<TestModel>?> =
        testService.getTests()
            .also { logger.info("Get all tests") }

    @GetMapping("/{id}")
    fun getTest(@PathVariable id: UUID): ResponseEntity<TestModel?> =
        testService.getTest(id)
            .also { logger.info("Get test $id") }

    @PostMapping
    fun createTest(@RequestBody newTestModel: TestModel): ResponseEntity<String> =
        testService.createTest(newTestModel)
            .also { logger.info("Create test - ${it.body}") }

    @PatchMapping("/{id}")
    fun patchTest(
        @PathVariable id: UUID,
        @RequestBody updatedModelFields: Map<String, Any>
    ): ResponseEntity<String> =
        testService.patchTest(id, updatedModelFields)
            .also { logger.info("Update test $id - ${it.body}") }

    @DeleteMapping("/{id}")
    fun deleteTest(@PathVariable id: UUID): ResponseEntity<String> =
        testService.deleteTest(id)
            .also { logger.info("Delete test $id - ${it.body}") }

}