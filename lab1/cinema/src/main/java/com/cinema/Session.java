package com.cinema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Session {
    private String movieName;
    private LocalDateTime startTime; 
    private List<Ticket> tickets;

    public Session(String movieName, LocalDateTime startTime, int totalSeats, double ticketPrice) {
        this.movieName = movieName;
        this.startTime = startTime;
        this.tickets = new ArrayList<>();
        
        for (int i = 1; i <= totalSeats; i++) {
            this.tickets.add(new Ticket(movieName, ticketPrice, i));
        }
    }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public List<Ticket> getTickets() { return tickets; }

    public double buyTickets(List<Integer> seatNumbers) {
        double totalSum = 0;
        for (int seat : seatNumbers) {
            Ticket ticketToBuy = null;
            for (Ticket t : tickets) {
                if (t.getSeatNumber() == seat) {
                    ticketToBuy = t;
                    break;
                }
            }
            
            if (ticketToBuy == null) {
                throw new IllegalArgumentException("квиток з місцем " + seat + " не знайдено");
            }
            
            ticketToBuy.sell();
            totalSum += ticketToBuy.getPrice();
        }
        return totalSum;
    }

    public long getAvailableTicketsCount() {
        return tickets.stream()
                      .filter(t -> !t.isSold())
                      .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(movieName, session.movieName) &&
               Objects.equals(startTime, session.startTime) &&
               Objects.equals(tickets, session.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, startTime, tickets);
    }
}
