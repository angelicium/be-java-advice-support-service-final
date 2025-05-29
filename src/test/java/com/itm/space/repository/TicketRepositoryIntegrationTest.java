package com.itm.space.repository;

import com.itm.space.domain.entity.Ticket;
import com.itm.space.domain.entity.TicketCategory;
import com.itm.space.domain.entity.User;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Timestamp;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
public class TicketRepositoryIntegrationTest {

    @Container
    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14-alpine");

    @DynamicPropertySource
    static void configure (DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none");
        registry.add("spring.liquibase.enabled", () -> true);
    }
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void shouldSaveAndRetrieveTicket() {
        Ticket ticket = ticketInitializer();

        ticketRepository.save(ticket);
        Ticket retrievedTicket = ticketRepository.findById(ticket.getId()).orElseThrow();

      assertNotNull(retrievedTicket);
      assertEquals(ticket.getId(), retrievedTicket.getId());
      assertEquals(ticket.getDescription(), retrievedTicket.getDescription());
      assertEquals(ticket.getCreatedAt(), retrievedTicket.getCreatedAt());
    }

    @Test
    public void shouldUpdateAndRetrieveTicket() {
        Ticket ticket = ticketInitializer();

        Boolean isExisted = ticketRepository.existsById(ticket.getId());
        if (!isExisted) {
            throw new RuntimeException("объект не найден");
        }
        ticketRepository.save(ticket);

        Ticket updatedTicket = ticketRepository.findById(ticket.getId()).orElseThrow();

        assertEquals(ticket, updatedTicket);
    }

    private Ticket ticketInitializer() {
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setTitle("Ticket Title");
        ticket.setDescription("Ticket Description");
        ticket.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        ticket.setCategory(new TicketCategory());
        ticket.setClosedAt(new Timestamp(System.currentTimeMillis()));
        ticket.setPriority(1);
        ticket.setStatus(1);
        ticket.setEscalatedAt(new Timestamp(System.currentTimeMillis()));
        ticket.setSlaDeadline(new Timestamp(System.currentTimeMillis()));
        ticket.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        ticket.setUser(new User());
        return ticket;
    }
}
