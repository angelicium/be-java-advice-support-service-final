package com.itm.space.repository;

import com.itm.space.BaseIntegrationTest;
import com.itm.space.domain.entity.Ticket;
import com.itm.space.domain.entity.TicketCategory;
import com.itm.space.domain.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.UUID;

public class TicketRepositoryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;

    private User testUser;
    private TicketCategory testCategory;

    @BeforeEach
    public void setUp() {
        testCategory = TicketCategory.builder()
                .id(1)
                .name("testCategory")
                .description("testCategoryDescription")
                .build();
        ticketCategoryRepository.save(testCategory);

        testUser = User.builder()
                .name("testUser")
                .email("testUser@test.com")
                .build();
        userRepository.save(testUser);
    }

    @Test
    @DisplayName("Тест на добавление сущности Ticket в БД")
    public void shouldSaveAndRetrieveTicket() {
        Ticket ticket = ticketInit();


        ticketRepository.save(ticket);


        Ticket retrievedTicket = ticketRepository.findById(ticket.getId()).orElseThrow();
        assertNotNull(retrievedTicket);
        assertEquals(ticket.getId(), retrievedTicket.getId());
        assertEquals(ticket.getTitle(), retrievedTicket.getTitle());
        assertEquals(ticket.getDescription(), retrievedTicket.getDescription());
    }

    @Test
    @DisplayName("Тест на обновление сущности Ticket в БД")
    public void shouldUpdateAndRetrieveTicket() {

        Ticket ticket = ticketInit();

        ticketRepository.save(ticket);


        ticket.setTitle("Updated Ticket Title");
        ticket.setDescription("Updated Ticket Description");
        ticketRepository.save(ticket);


        Ticket updatedTicket = ticketRepository.findById(ticket.getId()).orElseThrow();
        assertEquals("Updated Ticket Title", updatedTicket.getTitle());
        assertEquals("Updated Ticket Description", updatedTicket.getDescription());
    }

    private Ticket ticketInit(){
        return Ticket.builder()
                .id(UUID.randomUUID())
                .user(userRepository.findAll().getFirst())
                .category(testCategory)
                .priority(1)
                .status(1)
                .title("Ticket Title")
                .description("Ticket Description")
                .slaDeadline(new Timestamp(System.currentTimeMillis()))
                .escalatedAt(new Timestamp(System.currentTimeMillis()))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .closedAt(null)
                .build();
    }
}
