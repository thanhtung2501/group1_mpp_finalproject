package mpp.business.exception;

import java.io.Serializable;

public class CheckoutBookException extends RuntimeException implements Serializable {

    public CheckoutBookException() {
        super();
    }

    public CheckoutBookException(String msg) {
        super(msg);
    }

    public CheckoutBookException(Throwable t) {
        super(t);
    }
}
