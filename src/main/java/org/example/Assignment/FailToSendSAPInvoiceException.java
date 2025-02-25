package org.example.Assignment;

public class FailToSendSAPInvoiceException extends RuntimeException {
    public FailToSendSAPInvoiceException(String msg){
        super(msg);
    }
}
