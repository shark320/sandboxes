package org.example

import java.time.LocalDate

sealed class DateRangeSelection( val titleRes: Int)  {

    data object ThisMonth: DateRangeSelection(10){
        override fun getFromDate(): LocalDate = LocalDate.now().withDayOfMonth(1)

        override fun getToDate(): LocalDate {
            val today = LocalDate.now()
            return today.withDayOfMonth(today.lengthOfMonth())
        }
    }
    data object LastMonth: DateRangeSelection(20) {
        override fun getFromDate(): LocalDate = LocalDate.now().minusMonths(1).withDayOfMonth(1)

        override fun getToDate(): LocalDate {
            val today = LocalDate.now().minusMonths(1)
            return today.withDayOfMonth(today.lengthOfMonth())
        }

    }
    data object AllTime: DateRangeSelection(30){
        override fun getFromDate(): LocalDate? = null

        override fun getToDate(): LocalDate? = null

    }


    abstract fun getFromDate(): LocalDate?

    abstract fun getToDate(): LocalDate?

    fun getTag(): String? {
        return this::class.simpleName
    }

    companion object{
        fun values(): List<DateRangeSelection>{
            return listOf(ThisMonth, LastMonth, AllTime)
        }

        fun getFromTag(tag: String?): DateRangeSelection?{
            return values().find { it::class.simpleName == tag }
        }
    }


}

fun main() {
    val lastMonth = DateRangeSelection.LastMonth
    println(lastMonth.getTag())
    println(DateRangeSelection.getFromTag(lastMonth.getTag())?.getTag())
}