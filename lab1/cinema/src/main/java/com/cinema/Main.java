package com.cinema;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema("Multiplex");
        DataService dataService = new DataService();
        
        cinema.addSession(new Session("Дюна 2", LocalDateTime.now().plusHours(2), 50, 150.0));
        cinema.addSession(new Session("Матриця", LocalDateTime.now().plusDays(1), 30, 120.0));

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;


        while (isRunning) {
            System.out.println("\nоберіть дію:");
            System.out.println("1. показати всі сеанси та доступні квитки");
            System.out.println("2. купити квиток");
            System.out.println("3. показати загальний дохід кінотеатру");
            System.out.println("4. експортувати дані у csv файл");
            System.out.println("0. вийти");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showSessions(cinema);
                    break;
                case "2":
                    buyTicketMenu(cinema, scanner);
                    break;
                case "3":
                    System.out.println("загальний дохід " + cinema.getTotalRevenue() + " грн");
                    break;
                case "4":
                    exportData(cinema, dataService);
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("спробуйте ще раз");
            }
        }
        
        scanner.close();
    }

    private static void showSessions(Cinema cinema) {
        System.out.println("\n--- Розклад сеансів ---");
        List<Session> sessions = cinema.getSessions();
        
        if (sessions.isEmpty()) {
            System.out.println("Наразі сеансів немає");
            return;
        }

        for (int i = 0; i < sessions.size(); i++) {
            Session s = sessions.get(i);
            System.out.printf("%d. фільм: %s | час: %s | доступно місць: %d | ціна: %.2f грн\n", 
                    (i+1), 
                    s.getMovieName(), 
                    s.getStartTime(), 
                    s.getAvailableTicketsCount(),
                    s.getTickets().get(0).getPrice());
        }
    }

    private static void buyTicketMenu(Cinema cinema, Scanner scanner) {
        showSessions(cinema);
        if (cinema.getSessions().isEmpty()) return;

        System.out.print("\nвведіть номер сеансу: ");
        try {
            int sessionIndex = Integer.parseInt(scanner.nextLine()) - 1;
            Session selectedSession = cinema.getSessions().get(sessionIndex);

            System.out.print("введіть номер місця для купівлі: ");
            int seatNumber = Integer.parseInt(scanner.nextLine());

            double cost = selectedSession.buyTickets(List.of(seatNumber));
            System.out.println("ви купили квиток на фільм '" + selectedSession.getMovieName() + "' до сплати:" + cost + " грн");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("невірний номер сеансу");
        } catch (NumberFormatException e) {
            System.out.println("вводьте лише числа");
        } catch (Exception e) {
            System.out.println("помилка купівлі" + e.getMessage());
        }
    }

    private static void exportData(Cinema cinema, DataService dataService) {
        String fileName = "sessions_export.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            dataService.exportSessions(cinema.getSessions(), writer);
            System.out.println("дані експортовано у файл:" + System.getProperty("user.dir") + "\\" + fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}