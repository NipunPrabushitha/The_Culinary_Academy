package org.example.ViewTm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationTM {
    private int sid;
    private String studentName;
    private String courseId;
    private String courseName;
    private LocalDate date;
    private String duration;
    private double payment;
    private double dueAmount;
    private String Status;
}
