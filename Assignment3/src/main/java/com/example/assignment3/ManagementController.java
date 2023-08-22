package com.example.assignment3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.*;

public class ManagementController {

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Label message;

    @FXML
    private TextField name;

    @FXML
    private Button submit;

    @FXML
    private Rectangle box1, box2, box3, box4, box5, box6, box7, box8, box9;

    @FXML
    private Label text1, text2, text3, text4, text5, text6, text7, text8, text9;

    private ArrayList<String> nameList;
    private ArrayList<Color> colorList;
    private ArrayList<Rectangle> boxList;

    @FXML
    public void initialize() {
        nameList = new ArrayList<>(Arrays.asList(null, null, null, null, null, null, null, null, null));
        colorList = new ArrayList<>();
        boxList = new ArrayList<>(Arrays.asList(box1, box2, box3, box4, box5, box6, box7, box8, box9));
    }

    @FXML
    void click(ActionEvent event) {

        try {
            String inputName = name.getText().replaceAll(" ", "").toLowerCase();
            Color selectedColor = colorPicker.getValue();

            if (inputName.isEmpty() && colorList.size() < 9) { //If user don't enter the name
                message.setText("You should enter your name");
                return;
            }

            if (nameList.contains(inputName) && colorList.size() < 9) {
                message.setText("This name already exists");
                return;
            }

            if (colorList.contains(selectedColor) && colorList.size() < 9) {
                message.setText("Colour already exists");
                return;
            }

            int empty = emptyIndex();  // call the emptyOrNot method. If the return value is -1, there's no seat
            if (empty == -1) {
                message.setText("Sorry, no more places available");
                return;
            }

            nameList.add(inputName);
            colorList.add(selectedColor);
            Rectangle selectedBox = boxList.get(empty);
            selectedBox.setFill(selectedColor);
            Label selectedText = getTextLabel(selectedBox);
            selectedText.setText(inputName);

            if (colorList.size() == 9) {  // when the last seat is booked
                message.setText("The seats are fully booked!");
            } else {   // when there's no problem
                message.setText("Successfully registered");
            }
        } catch (Exception e) {
            Utilities.createErrorAlert("Error", e.getMessage()).show();
        }
    }

    private ArrayList<Integer> randNumList = new ArrayList<>();  // This is to store the used random number

    private int emptyIndex() {  // It will check if the seat is available and return the index number

        List<Integer> emptySeats = new ArrayList<>(); // This is to store the empty seat

        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i) == null) {
                emptySeats.add(i);
            }
        }

        if (!emptySeats.isEmpty()) {   // If there's an empty seat, this if statement will execute
            if (randNumList.size() == emptySeats.size()) {
                return -1;
            }

            Random random = new Random();
            int attemptNum = 0;

            while (attemptNum < emptySeats.size()) {
                int randomNum = random.nextInt(emptySeats.size());  // Generate the random number within the emptySeats size
                int emptyIndex = emptySeats.get(randomNum);
                if(nameList.get(emptyIndex) == null) {   // If the seat of the index number is empty & randNum was not used
                    if(!randNumList.contains(randomNum)) {
                        randNumList.add(randomNum);   //  This random number will be stored in the randNumList and the index number will be returned
                        return emptyIndex;
                    }
                }
                attemptNum++;
            }
        }
        return -1;
    }

    // This method will return the appropriate text(name) according to the box position
    private Label getTextLabel(Rectangle box) {
        if (box == box1)
            return text1;
        if (box == box2)
            return text2;
        if (box == box3)
            return text3;
        if (box == box4)
            return text4;
        if (box == box5)
            return text5;
        if (box == box6)
            return text6;
        if (box == box7)
            return text7;
        if (box == box8)
            return text8;
        if (box == box9)
            return text9;
        return null;
    }
}
