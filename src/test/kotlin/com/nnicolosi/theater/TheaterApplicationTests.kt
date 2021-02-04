package com.nnicolosi.theater

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TheaterApplicationTests {

    @Test
    fun contextLoads() {
        assert(true).equals(true)
    }
}
