package by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions;

public class DataInsertionErrorException extends RuntimeException {
    public DataInsertionErrorException() {
        super();
    }

    public DataInsertionErrorException(String message) {
        super(message);
    }

    public DataInsertionErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataInsertionErrorException(Throwable cause) {
        super(cause);
    }

    protected DataInsertionErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
