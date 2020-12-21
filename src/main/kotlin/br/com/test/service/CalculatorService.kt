package br.com.test.service

import br.com.test.CalculatorReply
import br.com.test.CalculatorRequest
import br.com.test.CalculatorServiceGrpcKt
import br.com.test.model.Tir
import br.com.test.repository.TirRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Suppress("unused")
class CalculatorService: CalculatorServiceGrpcKt.CalculatorServiceCoroutineImplBase() {

    @Inject
    lateinit var tirRepository: TirRepository

    override suspend fun calculate(request: CalculatorRequest, ): CalculatorReply {
        val tir = Tir.fromProto(request)
        tir.calculate()
        tirRepository.save(tir)

        return CalculatorReply.newBuilder().setResult(tir.result).build()
    }
}