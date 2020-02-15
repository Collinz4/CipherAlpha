package com.datapack.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launcher class for application.
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
public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Cipher Alpha");
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/main_window.fxml"))));
        stage.setMinHeight(600);
        stage.setMinWidth(700);
        stage.show();
    }

    public static void run() {
        launch();
    }
}
