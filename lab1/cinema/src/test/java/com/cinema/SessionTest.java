package com.cinema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {
    private Session session;

    @BeforeEach
    public void setUp() {
        session = new Session("abcd", LocalDateTime.now(), 50, 100.0);
    }

    @Test
    public void testSessionInitializesCorrectNumberOfTickets() {
        assertEquals(50, session.getTickets().size());
        assertEquals(50, session.getAvailableTicketsCount());
    }

    @Test
    public void testBuyTicketsCalculatesTotalSumCorrectly() {
        List<Integer> seatsToBuy = List.of(1, 2, 3);
        double totalSum = session.buyTickets(seatsToBuy);
        
        assertEquals(300.0, totalSum);
        assertTrue(session.getTickets().get(0).isSold());
    }

    @Test
    public void testBuyTicketsThrowsExceptionForInvalidSeat() {
        List<Integer> invalidSeats = List.of(999);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            session.buyTickets(invalidSeats);
        });
        
        assertTrue(exception.getMessage().contains("не знайдено"));
    }

    @Test
    public void testAvailableTicketsCountDecreasesAfterSale() {
        session.buyTickets(List.of(10, 11));
        assertEquals(48, session.getAvailableTicketsCount());
    }
}
