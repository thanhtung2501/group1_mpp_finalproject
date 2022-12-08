package mpp.business.exception;

import java.io.Serializable;

public class AddBookCopyException extends RuntimeException implements Serializable {
    public AddBookCopyException() {
        super();
    }

    public AddBookCopyException(String msg) {
        super(msg);
    }

    public AddBookCopyException(Throwable t) {
        super(t);
    }
}
