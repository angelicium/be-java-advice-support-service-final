package com.itm.space.repository;

import com.itm.space.BaseIntegrationTest;
import com.itm.space.domain.entity.TicketStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketStatusRepositoryIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    @AfterEach
    void cleanUpDB(){
        ticketStatusRepository.deleteAll();
    }

    @Test
    @DisplayName("Тест на добавление сущности TicketStatus в БД")
    void shouldSaveAndRetrieveTicketStatus() {
        TicketStatus newStatus = new TicketStatus();
        newStatus.setId(1);
        newStatus.setName("NEW");
        newStatus.setDescription("Новый тикет");

        TicketStatus savedStatus = ticketStatusRepository.save(newStatus);
        Optional<TicketStatus> foundStatus = ticketStatusRepository.findById(savedStatus.getId());

        assertTrue(foundStatus.isPresent());
        assertEquals("NEW", foundStatus.get().getName());
        assertEquals("Новый тикет", foundStatus.get().getDescription());
    }

    @Test
    @DisplayName("Тест на обновление сущности TicketStatus в БД")
    void shouldUpdateTicketStatus() {
        TicketStatus status = new TicketStatus();
        status.setId(1);
        status.setName("NEW");
        status.setDescription("Новый тикет");
        TicketStatus savedStatus = ticketStatusRepository.save(status);

        savedStatus.setName("UPDATED");
        savedStatus.setDescription("Обновленный тикет");
        ticketStatusRepository.save(savedStatus);

        Optional<TicketStatus> updatedStatus = ticketStatusRepository.findById(savedStatus.getId());

        assertTrue(updatedStatus.isPresent());
        assertEquals("UPDATED", updatedStatus.get().getName());
        assertEquals("Обновленный тикет", updatedStatus.get().getDescription());
    }

}