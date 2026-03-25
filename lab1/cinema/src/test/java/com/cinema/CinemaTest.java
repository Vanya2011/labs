package com.cinema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CinemaTest {
    private Cinema cinema;

    @BeforeEach
    public void setUp() {
        cinema = new Cinema("cinemaname");
    }

    @Test
    public void testAddNullSessionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.addSession(null);
        });
    }

    @Test
    public void testGetTotalRevenueCalculatesCorrectly() {
        Session session1 = new Session("фільм 1", LocalDateTime.now(), 10, 100.0);
        Session session2 = new Session("фільм 2", LocalDateTime.now(), 10, 200.0);
        
        cinema.addSession(session1);
        cinema.addSession(session2);
        
        session1.buyTickets(List.of(1, 2));
        session2.buyTickets(List.of(5));
        
        assertEquals(400.0, cinema.getTotalRevenue());
    }
}
