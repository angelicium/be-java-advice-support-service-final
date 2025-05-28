package com.itm.space.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket_status")
public class TicketStatus {

    @Id
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

}
