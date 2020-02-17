package com.datapack.uicontroller;

import com.datapack.cipherByte.CipherByte;
import com.datapack.exceptions.InvalidInputException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.PasswordField;
import java.util.Base64;

/**
 * View Controller for 'main_window.fxml'.
 *
 * Copyright (C) 2020  Stephen J Collins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
public class MainWindowController {

    @FXML private PasswordField passwordFieldTop;
    @FXML private PasswordField passwordFieldBottom;
    @FXML private TextArea userTextArea;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        userTextArea.setWrapText(true);
    }

    /**
     * Encrypts user information with the CipherByte algorithm.
     */
    @FXML
    public void encrypt() {

        try {
            validateUI();
        } catch(InvalidInputException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
            return;
        }

        byte[] ciphertext = Base64.getEncoder().encode(
                CipherByte.encrypt(userTextArea.getText().getBytes(), passwordFieldBottom.getText())
        );

        userTextArea.setText(new String(ciphertext));
    }

    /**
     * Decrypts user information with the CipherByte algorithm.
     */
    @FXML
    public void decrypt() {

        try {
            validateUI();
        } catch(InvalidInputException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
            return;
        }

        byte[] plaintext = CipherByte.decrypt(
                Base64.getDecoder().decode(userTextArea.getText().getBytes()), passwordFieldBottom.getText()
        );

        userTextArea.setText(new String(plaintext));
    }

    /**
     * Validates UI to ensure that password fields are matching and not empty. Also
     * ensures the message field is not empty.
     *
     * @throws InvalidInputException: if user data is not valid
     */
    private void validateUI() throws InvalidInputException {

        // check to ensure password fields are not empty
        if(passwordFieldTop.getText().equals("") || passwordFieldBottom.getText().equals("")) {
            throw new InvalidInputException("Password Fields Cannot Be Empty");
        }

        // check to ensure passwords match
        if(!passwordFieldTop.getText().equals(passwordFieldBottom.getText())) {
            throw new InvalidInputException("Passwords Do Not Match");
        }

        // check to ensure text field is not empty
        if(userTextArea.getText().equals("")) {
            throw new InvalidInputException("Message Field Cannot Be Empty");
        }
    }
}
