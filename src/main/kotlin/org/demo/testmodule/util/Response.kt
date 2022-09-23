package org.demo.testmodule.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class Response {
    companion object {
        //Success
        fun <B> ok(body: B) = ResponseEntity(body, HttpStatus.OK)
        fun <B> created(body: B) = ResponseEntity(body, HttpStatus.CREATED)
        fun <B> noContent() = ResponseEntity<B>(null, HttpStatus.NO_CONTENT)
        //Client Error
        fun <B> conflict(body: B?) = ResponseEntity(body, HttpStatus.CONFLICT)
    }
}