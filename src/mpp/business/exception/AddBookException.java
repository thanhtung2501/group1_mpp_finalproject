package mpp.business.exception;

import java.io.Serializable;

public class AddBookException extends RuntimeException implements Serializable {
    public AddBookException() {
        super();
    }

    public AddBookException(String msg) {
        super(msg);
    }

    public AddBookException(Throwable t) {
        super(t);
    }
}
