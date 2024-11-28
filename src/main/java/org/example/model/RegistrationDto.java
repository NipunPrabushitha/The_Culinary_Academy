package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDto {

    private Long id;

    private LocalDate enrollmentDate;

    private Double Payment;

    private Double DueAmount;

    private String studentName;

    private String ProgramName;

    private String duration;

}
