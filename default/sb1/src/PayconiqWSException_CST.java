public class PayconiqWSException_CST extends Exception{

    private static final long serialVersionUID = 6882224835211494632L;

    private final String errorResponse;

    public PayconiqWSException_CST(String message, String errorResponse, Throwable cause) {
        super(message, cause);
        this.errorResponse = errorResponse;
    }

    public PayconiqWSException_CST(String errorResponse) {
        this(null, errorResponse, null);
    }

    public PayconiqWSException_CST(String message, Throwable cause) {
        this(message, null, cause);
    }


    public String getErrorResponse() {
        return this.errorResponse;
    }

    @Override
    public String toString() {
        return "PayconiqWSException_CST{" +
                "errorResponse='" + errorResponse + '\'' +
                "} " + super.toString();
    }
}
