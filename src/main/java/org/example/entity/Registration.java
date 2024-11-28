package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String duration;

    private LocalDate RegistrationDate;

    private LocalDate PaymentDate;

    private Double payment;

    private Double dueAmount;

    private String studentName;

    private String programName;

    private String status;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "program_id", nullable = false)
    private Course course;



}
