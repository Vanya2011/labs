package com.cinema;

import java.util.Objects;

public class Ticket {
    private String movieName;
    private double price;
    private int seatNumber;
    private boolean isSold;

    public Ticket(String movieName, double price, int seatNumber) {
        this.movieName = movieName;
        this.price = price;
        this.seatNumber = seatNumber;
        this.isSold = false; 
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isSold() {
        return isSold;
    }

    public void sell() {
        if (this.isSold) {
            throw new IllegalStateException("квиток вже продано");
        }
        this.isSold = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Ticket ticket = (Ticket) o;
        
        return Double.compare(ticket.price, price) == 0 &&
               seatNumber == ticket.seatNumber &&
               isSold == ticket.isSold &&
               Objects.equals(movieName, ticket.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieName, price, seatNumber, isSold);
    }
}
