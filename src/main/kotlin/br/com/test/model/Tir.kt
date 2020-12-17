package br.com.test.model

import br.com.test.CalculatorRequest
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull
import kotlin.math.pow

@Entity
data class Tir(
    @Id
    val id: UUID = UUID.randomUUID(),
    @NotNull
    val cashFlow: Double,
    @NotNull
    val initialInvestment: Double,
    @NotNull
    val year: Int,
    @NotNull
    var value: Double = 0.0
) {
    companion object {
        fun fromProto(request: CalculatorRequest) : Tir {
            return Tir(cashFlow = request.cashFlow, initialInvestment = request.initialInvestment, year = request.year)
        }
    }

    fun calculate() {
        val x = cashFlow/initialInvestment
        value = x.pow(1 / year.toDouble()) - 1.0
    }
}
