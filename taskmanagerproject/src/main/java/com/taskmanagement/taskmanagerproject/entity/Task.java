package com.taskmanagement.taskmanagerproject.entity;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Date Debut cannot be null")
    private LocalDate date_debut;
    @NotNull(message = "Date Fin cannot be null")
    private LocalDate date_fin;
    @NotNull(message = "Task status cannot be null")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @NotNull(message = "Label cannot be null")
    private String label;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedUser;
}
