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
import java.util.Arrays
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TirTest {

    @Test
    fun testCreateTirObject() {
        val tir = Tir(cashFlow = listOf(1.0, 2.0), years = 1)
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
    fun testTirCalculate(input: List<Double>, result: Double) {
        val tir = Tir(cashFlow = input, years = input.lastIndex)
        tir.calculate()
        Assertions.assertEquals(result, tir.result)
    }

    fun generateTirData(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(Arrays.asList(-500.0, 300.0), -0.39999999999999997),
            Arguments.of(Arrays.asList(-500.0, 300.0, 400.0), 0.24339811320566043),
            Arguments.of(Arrays.asList(-500.0, 300.0, 400.0, 500.0), 0.5406032365284684)
        )
    }
}