package br.com.test.service

import br.com.test.CalculatorRequest
import br.com.test.CalculatorServiceGrpc
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class CalculatorServiceTest {

    @Inject
    lateinit var blockingStub: CalculatorServiceGrpc.CalculatorServiceBlockingStub

    @Test
    fun testCalculatorService() {
        val request: CalculatorRequest = CalculatorRequest.newBuilder()
            .addCashFlow(-500.0)
            .addCashFlow(200.0)
            .addCashFlow(300.0)
            .addCashFlow(400.0)
            .build()

        Assertions.assertEquals(
            0.3169080407721607,
            blockingStub.calculate(request).result
        )
    }
}