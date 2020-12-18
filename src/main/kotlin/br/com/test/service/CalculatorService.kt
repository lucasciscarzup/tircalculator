package br.com.test.service

import br.com.test.CalculatorReply
import br.com.test.CalculatorRequest
import br.com.test.CalculatorServiceGrpcKt
import br.com.test.model.Tir
import javax.inject.Singleton

@Singleton
@Suppress("unused")
class CalculatorService : CalculatorServiceGrpcKt.CalculatorServiceCoroutineImplBase() {

    override suspend fun calculate(request: CalculatorRequest): CalculatorReply {
        val tir = Tir.fromProto(request)
        tir.calculate()

        return CalculatorReply.newBuilder().setResult(tir.result).build()
    }
}