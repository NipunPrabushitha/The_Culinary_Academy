package org.example.ViewTm;
import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseTM {
    private String ProgramID;
    private String ProgramName;
    private double fee;
    private String Duration;
}
