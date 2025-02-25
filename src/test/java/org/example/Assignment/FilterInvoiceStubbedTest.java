package org.example.Assignment;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FilterInvoiceStubbedTest {
    @Test
    //Stubbed test for lowValueInvoices function in FilterInvoice.java
    public void filterInvoiceStubbedTest(){
        // Initialize database, DAO, and filter
        Database db = new Database();
        QueryInvoicesDAO dao = new QueryInvoicesDAO(db);
        FilterInvoice filter = new FilterInvoice(db, dao);

        //
        Invoice i0 = new Invoice("Sam", 25);
        Invoice i1 = new Invoice("Sam", 50);
        Invoice i2 = new Invoice("Sam", 125);

        // Save invoices to DAO
        dao.save(i0);
        dao.save(i1);
        dao.save(i2);

        // Apply filter to lowValueInvoices
        List<Invoice> lowValueInvoices = filter.lowValueInvoices();

        // Check that lowValueInvoices is not null and all values are less than 100
        assertNotNull(lowValueInvoices);
        assertTrue(lowValueInvoices.stream().allMatch(i -> i.getValue() < 100));
        assertEquals(2, lowValueInvoices.size());
    }
}
