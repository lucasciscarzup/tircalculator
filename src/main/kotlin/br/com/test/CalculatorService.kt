package br.com.test

import javax.inject.Singleton

@Singleton
class CalculatorService : CalculatorServiceGrpcKt.CalculatorServiceCoroutineImplBase() {

    override suspend fun calculate(request: CalculatorRequest): CalculatorReply {
        return CalculatorReply.newBuilder().setMessage("Test").build()
    }
}