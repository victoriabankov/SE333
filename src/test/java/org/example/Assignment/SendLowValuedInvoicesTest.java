package org.example.Assignment;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class SendLowValuedInvoicesTest {

    @Test
    public void testWhenLowInvoicesSent(){
        // Mock FilterInvoice and SAP
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);

        // Create SAP_BasedInvoiceSender instance with mocked FilterInvoice and SAP
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(filter, sap);

        // Create stub and put into list
        Invoice i0 = new Invoice("Sam", 25);
        List<Invoice> low = Arrays.asList(i0);

        // Apply filter and return low valued invoices
        when(filter.lowValueInvoices()).thenReturn(low);

        // Send invoices to sender
        sender.sendLowValuedInvoices();

       // Verify send called with low valued invoice
        verify(sap).send(i0);
    }

    @Test
    public void testWhenNoInvoicesSent() {
        // Mock FilterInvoice and SAP
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);

        // Create SAP_BasedInvoiceSender instance with mocked FilterInvoice and SAP
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(filter, sap);

        // Create stub and put into list (empty list in this case)
        List<Invoice> empty = Arrays.asList();

        // Apply filter and return low valued invoices
        when(filter.lowValueInvoices()).thenReturn(empty);

        // Send invoices to sender
        sender.sendLowValuedInvoices();

        // Verify send called with low valued invoice
        verify(sap, never()).send(any());
    }
}
