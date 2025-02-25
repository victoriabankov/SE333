package org.example.Assignment;

import org.junit.internal.runners.statements.Fail;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

// Class responsible for sending low-valued invoices to the SAP system
public class SAP_BasedInvoiceSender {

    private final FilterInvoice filter;  // Dependency for filtering invoices
    private final SAP sap;  // Dependency for sending invoices to the SAP system

    // Constructor that uses dependency injection to initialize the filter and sap objects
    public SAP_BasedInvoiceSender(FilterInvoice filter, SAP sap) {
        this.filter = filter;
        this.sap = sap;
    }

    // Method to send all low-valued invoices to the SAP system
    public List<Invoice> sendLowValuedInvoices() {
        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();
        List<Invoice> failedValueInvoices = new ArrayList<>();
        for (Invoice invoice : lowValuedInvoices) {  // Iterates through each invoice in the list
            try{
                sap.send(invoice);  // Sends the current invoice to the SAP system
            } catch(FailToSendSAPInvoiceException e){
                failedValueInvoices.add(invoice);
            }
        }
        return failedValueInvoices;
    }
}
