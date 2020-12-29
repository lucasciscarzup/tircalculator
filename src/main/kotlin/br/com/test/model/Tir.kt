package br.com.test.model

import br.com.test.CalculatorRequest
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
data class Tir(
    @Id
    val id: UUID = UUID.randomUUID(),
    @ElementCollection
    val cashFlow: List<BigDecimal>,
    @NotNull
    val years: Int,
    @NotNull
    var result: BigDecimal = BigDecimal.ZERO
) {

    companion object {
        fun fromProto(request: CalculatorRequest) : Tir {
            var cashFlowList: MutableList<BigDecimal> = mutableListOf()
            request.cashFlowList.map { cf -> cashFlowList.add(cf.toBigDecimal()) }

            return Tir(cashFlow = cashFlowList, years = cashFlowList.size)
        }
    }

    fun calculate() {
        val maxAttempts = 20
        val precision = BigDecimal(1E-7)

        var x0 = BigDecimal(0.1)
        var values = cashFlow

        for (i in 0 until maxAttempts) {
            val factor = x0.plus(BigDecimal.ONE)
            var denominator = factor
            var fValue = values[0]
            var fDerivative = BigDecimal.ZERO

            for (k in 1 until values.size) {
                val value = values[k]
                fValue += value.divide(denominator, 10, RoundingMode.HALF_UP)
                denominator *= factor
                fDerivative -= BigDecimal(k).multiply(value).divide(denominator, 10, RoundingMode.HALF_UP)
            }

            val x1 = x0 - fValue.divide(fDerivative, 10, RoundingMode.HALF_UP)
            if ((x1 - x0).abs() <= precision) {
                result = x1.setScale(4, RoundingMode.HALF_UP)
                break
            }

            x0 = x1
        }
    }
}
