package org.example.Assignment;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterInvoiceTest {
    @Test
    // Test for lowValueInvoices function in FilterInvoice.java
    public void filterInvoiceTest(){
        // Initialize database, DAO, and invoices
        QueryInvoicesDAO dao = mock(QueryInvoicesDAO.class);
        Database db = new Database();

        // Initialize filter
        FilterInvoice filter = new FilterInvoice(db, dao);

        // Apply filter to lowValueInvoices
        List<Invoice> lowValueInvoices = filter.lowValueInvoices();

        // Check that lowValueInvoices is not null and all values are less than 100
        assertNotNull(lowValueInvoices);
        assertTrue(lowValueInvoices.stream().allMatch(i -> i.getValue() < 100));
    }
}
