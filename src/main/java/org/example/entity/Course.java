package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Id
        private String programId;

        private String programName;

        private String duration;

        private double fee;

    @OneToMany(mappedBy = "course", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Registration> registrations = new ArrayList<>();


    public Course(String courseId) {

    }
}
