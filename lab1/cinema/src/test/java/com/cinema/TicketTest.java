package com.cinema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {
    private Ticket ticket;
    @BeforeEach
    public void setUp() {
        ticket = new Ticket("abc", 100.0, 10);
    }

    @Test
    public void testSellTicketSuccess() {
        ticket.sell();
        assertTrue(ticket.isSold(), "квиток має бути проданий");
    }

    @Test
    public void testSellAlreadySoldTicketThrowsException() {
        ticket.sell();
        assertThrows(IllegalStateException.class, () -> {
            ticket.sell();
        });
    }

    @Test
    public void testSetPriceIgnoresNegativeValue() {
        ticket.setPrice(-50.0);
        assertEquals(100.0, ticket.getPrice(), "ціна не може бути від'ємною");
    }
}
