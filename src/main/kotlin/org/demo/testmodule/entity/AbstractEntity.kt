package org.demo.testmodule.entity

import java.util.Objects
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractEntity<ID>(
    @Id
    val id: ID
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbstractEntity<*>) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }
}