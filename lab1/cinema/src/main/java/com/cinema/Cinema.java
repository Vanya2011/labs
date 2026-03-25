package com.cinema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Cinema {
    private String name;
    private List<Session> sessions;

    public Cinema(String name) {
        this.name = name;
        this.sessions = new ArrayList<>();
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public List<Session> getSessions() { return sessions; }

    public void addSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Сеанс не може бути порожнім");
        }
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public double getTotalRevenue() {
        return sessions.stream()
                .flatMap(session -> session.getTickets().stream())
                .filter(Ticket::isSold)
                .mapToDouble(Ticket::getPrice)
                .sum(); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(name, cinema.name) &&
               Objects.equals(sessions, cinema.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sessions);
    }
}
