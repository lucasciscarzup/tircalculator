package br.com.test.model

import br.com.test.CalculatorRequest
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TirTest {

    @Test
    fun testCreateTirObject() {
        val tir = Tir(cashFlow = listOf(BigDecimal.ONE, BigDecimal.TEN), years = 1)
        Assertions.assertNotNull(tir)
    }

    @Test
    fun testCreateTirObjectFromProto() {
        val tirRequest = spyk<CalculatorRequest>()
        Assertions.assertNotNull(Tir.fromProto(tirRequest))
        verify { tirRequest.cashFlowList }
    }

    @ParameterizedTest
    @MethodSource("generateTirData")
    fun testTirCalculate(input: List<BigDecimal>, result: String) {
        val tir = Tir(cashFlow = input, years = input.lastIndex)
        tir.calculate()
        Assertions.assertEquals(result, tir.result.toString())
    }

    fun generateTirData(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(listOf(BigDecimal(-500.0), BigDecimal(300.0)), "-0.4000"),
            Arguments.of(listOf(BigDecimal(-500.0), BigDecimal(300.0), BigDecimal(400.0)), "0.2434"),
            Arguments.of(listOf(BigDecimal(-500.0), BigDecimal(300.0), BigDecimal(400.0), BigDecimal(500.0)), "0.5406")
        )
    }
}