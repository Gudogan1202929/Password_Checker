package com.example.assigmentsecu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;

public class HelloApplication extends Application {
    static String comments = "";
    static double entropy;
    static double variance;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        System.out.println(MyWay.passwordChecker(password));
//        launch();
    }

    public static void checkPasswordStrength(String password) {
        double possibilities = calculatePossibilities(password);
        double mean = calculateMean(password);
         variance = calculateVariance(password, mean);
         entropy = calculateEntropy(possibilities, password.length());
    }

    private static double calculateEntropy(double possibilities, int length) {
        System.out.println();
        return Math.log(Math.pow(possibilities , length))/ Math.log(2);
    }

    private static double calculatePossibilities(String password) {
        double possibilities = 0;
        boolean hasDigit = false, hasLower = false, hasUpper = false, hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch) && !hasDigit) {
                possibilities += 10;
                hasDigit = true;
            } else if (Character.isLowerCase(ch) && !hasLower) {
                possibilities += 26;
                hasLower = true;
            } else if (Character.isUpperCase(ch) && !hasUpper) {
                possibilities += 26;
                hasUpper = true;
            } else if (!Character.isLetterOrDigit(ch) && !hasSpecial) {
                possibilities += 32;
                hasSpecial = true;
            }
        }

        if (!hasDigit){
            comments+= "Your password doesn't have a digit\n";
        }
        if (!hasLower){
            comments+= "Your password doesn't have a lower case\n";
        }
        if (!hasUpper){
            comments+= "Your password doesn't have a upper case\n";
        }
        if (!hasSpecial){
            comments+= "Your password doesn't have a special character\n";
        }
        return possibilities;
    }


    private static double calculateMean(String password) {
        double sum = 0;
        for (char ch : password.toCharArray()) {
            if(Character.isDigit(ch)) {
                sum += (int) ch+48;
            }else {
                sum += (int)ch;
            }
        }
        return sum / password.length();
    }

    private static double calculateVariance(String password, double mean) {
        double varianceSum = 0;
        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                varianceSum += Math.pow((int) ch + 48 - mean, 2);
            }else {
                varianceSum += Math.pow((int) ch - mean, 2);
            }
        }
        return varianceSum / password.length();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Password Strength Checker");
        Label passwordLabel = new Label("Enter your password:");
        TextField passwordField = new TextField();
        Button checkButton = new Button("Check");
        Label resultLabel = new Label();
        checkButton.setOnAction(e -> {
            variance = 0;
            entropy = 0;
            comments = "";
            String password = passwordField.getText();
            //
            checkPasswordStrength(password);
            if (password.length() < 8){
                comments+= "Your password is too short\n";
            }
            comments+= "Your password is: ";

            if (entropy > 90 && variance >= 500) {
                comments += "Strong";
            } else if ((entropy >= 40 && entropy <= 89 && variance >= 70 && variance < 500)) {
                comments += "Medium";
            } else {
                comments += "Weak";
            }

            comments += "\nEntropy: " + entropy + "\nVariance: " + variance;
            resultLabel.setText(comments);
        });
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(passwordLabel, passwordField, checkButton, resultLabel);
        vbox.setPrefSize(400, 240);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}