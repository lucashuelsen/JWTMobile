package com.example.jwtmobile.bo

import com.example.jwtmobile.util.IPriceItemHolder
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class SAPPriceItemHolder(
    val nAmount: BigDecimal?,
    val nValue: BigDecimal
) : IPriceItemHolder {
    override fun getUnitValue(): BigDecimal {
        return nValue
    }

    override fun getTotalValue(): BigDecimal {
        return nValue.multiply(nAmount, MathContext.DECIMAL64)
            .setScale(2, RoundingMode.HALF_DOWN)
    }

    override fun getTaxValue(): BigDecimal {
        return getTotalValue().multiply(BigDecimal(1.10, MathContext.DECIMAL64))
            .setScale(2, RoundingMode.HALF_DOWN)
    }

    override fun getAmount(): BigDecimal {
        return nAmount?: BigDecimal.ZERO
    }
}