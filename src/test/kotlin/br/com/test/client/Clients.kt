package br.com.test.client

import br.com.test.CalculatorServiceGrpc
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import io.micronaut.grpc.server.GrpcServerChannel

@Factory
class Clients {

    @Bean
    fun blockingStub(
        @GrpcChannel(GrpcServerChannel.NAME)
        channel: ManagedChannel
    ): CalculatorServiceGrpc.CalculatorServiceBlockingStub {
        return CalculatorServiceGrpc.newBlockingStub(channel)
    }
}
