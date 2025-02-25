package org.example.Assignment;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ThrowExceptionWhenBadInvoiceTest {

    @Test
    public void testThrowExceptionWhenBadInvoice(){
        // Mock FilterInvoice and SAP
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);

        // Create SAP_BasedInvoiceSender instance with mocked FilterInvoice and SAP
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(filter, sap);

        // Create stub and put into list
        Invoice i0 = new Invoice("Sam", 25);
        Invoice i1 = new Invoice("Sam", 125);
        List<Invoice> invoices = Arrays.asList(i0, i1);

        // Apply filter and return low valued invoices
        when(filter.lowValueInvoices()).thenReturn(invoices);

        // Throw FailToSendSAPInvoiceException when value > 100
        doThrow(new FailToSendSAPInvoiceException("Failed to send invoice.")).when(sap).send(i1);

        // Send invoices to sender
       List<Invoice> failed = sender.sendLowValuedInvoices();

        // Verify both invoices were sent
        verify(sap).send(i0);
        verify(sap).send(i1);

        // Ensure failed only has one invoice, i1
        assertEquals(1, failed.size());
        assertTrue(failed.contains(i1));
    }
}
