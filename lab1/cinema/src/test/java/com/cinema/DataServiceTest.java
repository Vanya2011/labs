package com.cinema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; 

public class DataServiceTest {

    private DataService dataService;

    @BeforeEach
    public void setUp() {
        dataService = new DataService();
    }

    @Test
    public void testExportSessionsUsesWriterAndSortsData() throws IOException {
        Writer mockWriter = mock(Writer.class);

        List<Session> sessions = new ArrayList<>();
        sessions.add(new Session("abcd", LocalDateTime.of(2026, 3, 11, 15, 0), 50, 150.0));

        dataService.exportSessions(sessions, mockWriter);

        verify(mockWriter).write("MovieName,StartTime,TotalSeats,TicketPrice\n");
        
        verify(mockWriter).write(Mockito.contains("abcd"));
        
        verify(mockWriter, times(1)).flush();
    }

    @Test
    public void testImportSessionsWithMockReader() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);

        when(mockReader.readLine())
                .thenReturn("MovieName,StartTime,TotalSeats,TicketPrice") 
                .thenReturn("abc,2026-03-11T18:00,100,250.00") 
                .thenReturn(null);

        List<Session> importedSessions = dataService.importSessions(mockReader);

        assertEquals(1, importedSessions.size(), "має імпортувати 1 сеанс");
        assertEquals("abc", importedSessions.get(0).getMovieName());
        assertEquals(100, importedSessions.get(0).getTickets().size());
        assertEquals(250.0, importedSessions.get(0).getTickets().get(0).getPrice());
    }
}