package com.example.userdetails.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Component
import java.util.function.Supplier

@Component
class CoroutineUtils {
    // Existing suspend function for Kotlin callers
    suspend fun <T> withIOContext(block: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            block()
        }
    }

    // New function for Java callers
    fun <T> withIOContext(block: Supplier<T>): T {
        return runBlocking(Dispatchers.IO) {
            block.get()
        }
    }
}