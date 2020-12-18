package br.com.test.model

import br.com.test.CalculatorRequest
import java.util.UUID
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull
import kotlin.math.absoluteValue
import kotlin.math.pow

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
            return Tir(cashFlow = request.cashFlowList, years = request.cashFlowCount)
        }
    }

    fun calculate() {
        var initialInvestment = cashFlow.get(0).absoluteValue
        var cashFlowSum: Double = 0.0
        for(i in 1 until years) {
            cashFlowSum += cashFlow.get(i).pow(1.0/i)
        }

        result = cashFlowSum/initialInvestment - 1

//        var x0 = 0.1
//        var values = cashFlow.toDoubleArray()
//
//        for (i in 0 until 20) {
//            val factor = 1.0 + x0
//            var denominator = factor
//            var fValue = values[0]
//            var fDerivative = 0.0
//
//            for (k in 1 until values.size) {
//                val value = values[k]
//                fValue += value / denominator
//                denominator *= factor
//                fDerivative -= k * value / denominator
//            }
//
//            val x1 = x0 - fValue / fDerivative
//            if (Math.abs(x1 - x0) <= 1E-7) {
//                result = x1
//            }
//
//            x0 = x1
//        }
    }
}
