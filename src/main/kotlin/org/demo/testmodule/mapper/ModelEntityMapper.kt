package org.demo.testmodule.mapper

interface ModelEntityMapper<M, E> {
    fun toModel(entity: E): M
    fun toEntity(model: M): E
}