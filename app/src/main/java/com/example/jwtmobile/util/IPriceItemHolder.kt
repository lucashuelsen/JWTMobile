package com.example.jwtmobile.util

import java.math.BigDecimal

interface IPriceItemHolder {
    fun getUnitValue() : BigDecimal
    fun getTotalValue() : BigDecimal
    fun getTaxValue() : BigDecimal
    fun getAmount() : BigDecimal
}