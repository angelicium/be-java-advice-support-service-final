package com.itm.space.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TicketCategory category;

    // @ManyToOne пока что связи нет
    // @JoinColumn(name = "priority_id", nullable = false)
    @Column(name = "priority_id", nullable = false)
    private Integer priority;

    // @OneToOne пока что связи нет
    // @JoinColumn(name = "status_id", nullable = false)
    @Column(name = "status_id", nullable = false)
    private Integer status;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "sla_deadline", nullable = false)
    private Timestamp slaDeadline;


    @Column(name = "escalated_at", nullable = false)
    private Timestamp escalatedAt;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "closed_at", nullable = false)
    private Timestamp closedAt;
}
