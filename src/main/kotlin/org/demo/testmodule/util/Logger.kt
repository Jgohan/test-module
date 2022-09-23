package org.demo.testmodule.util

import org.slf4j.LoggerFactory

inline fun <reified T> lazyLoggerFor() = lazy { LoggerFactory.getLogger(T::class.java) }