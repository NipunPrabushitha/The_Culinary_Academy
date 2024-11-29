package org.example.ViewTm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
@NoArgsConstructor
@Data
@AllArgsConstructor

public class PaymentTM {
    private Long id;
    private String duration;
    private LocalDate date;
    private LocalDate regDate;
    private int sid;
    private String studentName;
    private String courseId;
    private String courseName;
    private double payment;
    private double dueAmount;
    private String Status;
}
