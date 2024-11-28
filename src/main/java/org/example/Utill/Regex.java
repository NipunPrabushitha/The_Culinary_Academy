package org.example.Utill;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text) {
        String filed = "";
        switch (textField) {
            case NAME:
                filed = "^[A-Za-z\\s\\-]+$";
                break;
            case CONTACT:
                filed = "^\\+?[0-9]{10,15}(\\s|-)?$\n";
                break;
            case ADDRESS:
                filed = "^[a-zA-Z0-9\\s,.\\-#]+$\n";
                break;
            case EMAIL:
                filed = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n";
                break;
        }
        Pattern pattern = Pattern.compile(filed);
        if(text != null){
            if(text.trim().isEmpty()){
                return false;
            }
        }else {
            return true;
        }

        Matcher matcher = pattern.matcher(text);
        if(matcher.matches()) {
            return true;
        }else {
            return false;
        }
    }
    public static boolean setTextColour(TextField location, javafx.scene.control.TextField textField){
        if(!textField.getText().isEmpty() && Regex.isTextFieldValid(location, textField.getText())) {
            textField.setStyle("-fx-border-color: #00FF00;-fx-background-color: #e7dbc0;");
            return true;
        }else {
            textField.setStyle("-fx-border-color: red;-fx-border-radius: 5;-fx-border-image-width: 4;-fx-background-color: #e7dbc0;");
            return false;
        }
    }
}
