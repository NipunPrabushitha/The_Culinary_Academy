package org.example.Utill;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text) {
        String filed = "";
        switch (textField) {
            case NUMBERS:
                filed = "^[0-9]+$";
                break;
            case NAME:
                filed = "^[A-Za-z\\s\\-]+$";
                break;
            case CONTACT:
                filed = "^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$";
                break;
            case ADDRESS:
                filed = "^[A-Za-z\\s\\-]+$";
                break;
            case EMAIL:
                filed = "(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$";
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