package com.demo.qrcodegeneratordemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QRCodeGeneratorDemo extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(QRCodeGeneratorDemo.class.getResource("qr-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 603, 581);
		stage.setTitle("QR Generator Demo!");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}