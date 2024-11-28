package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {


    //private int id;

    private String firstName;
    private String lastName;

    private String address;
    private String email;

    /*
            @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    */
    private String phoneNumber;

    private LocalDate enrollmentDate;
    private String user;

/*
        @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
*/
    /* private Set<Enrollment> enrollments = new HashSet<>();*/


}
