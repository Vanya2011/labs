package com.cinema;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DataService {
    public void exportSessions(List<Session> sessions, Writer writer) throws IOException {
        if (sessions == null || writer == null) {
            throw new IllegalArgumentException("параметри не можуть бути null");
        }

        List<Session> sortedSessions = sessions.stream()
                .sorted(Comparator.comparing(Session::getMovieName))
                .collect(Collectors.toList());

        writer.write("MovieName,StartTime,TotalSeats,TicketPrice\n");

        for (Session s : sortedSessions) {
            double price = s.getTickets().isEmpty() ? 0 : s.getTickets().get(0).getPrice();
            
            String line = String.format("%s,%s,%d,%.2f\n",
                    s.getMovieName(),
                    s.getStartTime().toString(),
                    s.getTickets().size(),
                    price);
            writer.write(line);
        }
        writer.flush();
    }

    public List<Session> importSessions(BufferedReader reader) throws IOException {
        List<Session> importedSessions = new ArrayList<>();
        String line = reader.readLine();
        
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            
            if (parts.length == 4) {
                String movieName = parts[0];
                LocalDateTime startTime = LocalDateTime.parse(parts[1]); 
                int totalSeats = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3].replace(",", ".")); 

                importedSessions.add(new Session(movieName, startTime, totalSeats, price));
            }
        }
        return importedSessions;
    }
}
