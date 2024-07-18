/**
 * Enum for [SubmitEntity.type]
 *
 * @author vpavlov
 * @version 1.0
 */
enum class ESubmitType(
    /**
     * Code for DB storing
     */
    val code: Int
) {
    /**
     * Represents INCOME [SubmitEntity].<br></br>
     * Has value [Constants.INCOME_CODE]
     */
    INCOME(Constants.INCOME_CODE),

    /**
     * Represents OUTCOME [SubmitEntity].<br></br>
     * Has value [Constants.OUTCOME_CODE]
     */
    OUTCOME(Constants.OUTCOME_CODE);

    /**
     * Represents constants codes for [ESubmitType] to store in DB
     */
    private object Constants {
        const val INCOME_CODE: Int = 0

        const val OUTCOME_CODE: Int = 1
    }

    companion object {
        /**
         * Gets a [ESubmitType] by its code
         *
         * @param code code to get [ESubmitType] for
         * @return found [ESubmitType]
         */
        fun getFromCode(code: Int): ESubmitType? {
            return when (code) {
                Constants.INCOME_CODE -> {
                    INCOME
                }

                Constants.OUTCOME_CODE -> {
                    OUTCOME
                }

                else -> {
                    null
                }
            }
        }
    }
}
