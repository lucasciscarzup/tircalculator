package br.com.test.model

import br.com.test.CalculatorRequest
import java.util.UUID
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull
import kotlin.math.absoluteValue

@Entity
data class Tir(
    @Id
    val id: UUID = UUID.randomUUID(),
    @ElementCollection
    val cashFlow: List<Double>,
    @NotNull
    val years: Int,
    @NotNull
    var result: Double = 0.0
) {

    companion object {
        fun fromProto(request: CalculatorRequest) : Tir {
            return Tir(cashFlow = request.cashFlowList, years = request.cashFlowList.lastIndex)
        }
    }

    fun calculate() {
        val maxAttempts = 20
        val precision = 1E-7

        var x0 = 0.1
        var values = cashFlow.toDoubleArray()

        for (i in 0 until maxAttempts) {
            val factor = 1.0 + x0
            var denominator = factor
            var fValue = values[0]
            var fDerivative = 0.0

            for (k in 1 until values.size) {
                val value = values[k]
                fValue += value / denominator
                denominator *= factor
                fDerivative -= k * value / denominator
            }

            val x1 = x0 - fValue / fDerivative
            if ((x1 - x0).absoluteValue <= precision) {
                result = x1 * 100.0
                break
            }

            x0 = x1
        }
    }
}
