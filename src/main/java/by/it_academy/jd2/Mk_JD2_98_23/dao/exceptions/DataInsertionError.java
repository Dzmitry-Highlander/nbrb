package by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions;

public class DataInsertionError extends RuntimeException {
    public DataInsertionError() {
        super();
    }

    public DataInsertionError(String message) {
        super(message);
    }

    public DataInsertionError(String message, Throwable cause) {
        super(message, cause);
    }

    public DataInsertionError(Throwable cause) {
        super(cause);
    }

    protected DataInsertionError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
