package org.demo.testmodule.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.demo.testmodule.entity.TestEntity
import java.util.*

@Repository
interface TestRepository : CrudRepository<TestEntity, UUID> {

}