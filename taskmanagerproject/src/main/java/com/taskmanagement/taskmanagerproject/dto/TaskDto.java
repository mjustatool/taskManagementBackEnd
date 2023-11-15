package com.taskmanagement.taskmanagerproject.dto;

import java.time.LocalDate;
import com.taskmanagement.taskmanagerproject.entity.TaskStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record TaskDto(
                int id,
                LocalDate date_debut,
                LocalDate date_fin,
                @Enumerated(EnumType.STRING) TaskStatus status,
                String label) {

}