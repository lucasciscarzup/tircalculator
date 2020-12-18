package br.com.test.model

import br.com.test.CalculatorRequest
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
class TirTest {

    @Test
    fun testCreateTirObject() {
        val tir = Tir(cashFlow = listOf(1.0, 2.0), years = 2)
        Assertions.assertNotNull(tir)
    }

    @Test
    fun testCreateTirObjectFromProto() {
        val tirRequest = CalculatorRequest.newBuilder().addCashFlow(1.0).build()
        Assertions.assertNotNull(Tir.fromProto(tirRequest))
    }
}