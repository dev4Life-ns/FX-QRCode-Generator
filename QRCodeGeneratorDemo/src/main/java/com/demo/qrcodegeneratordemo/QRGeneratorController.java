package com.demo.qrcodegeneratordemo;

import com.demo.qrcodegeneratordemo.qr.QRGenerator;
import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.Objects;

public class QRGeneratorController
{
	@FXML private Label userSelectionLabel;

	@FXML private JFXComboBox<String> qrTypes;
	@FXML private TextField nameTF,addressTF,cityTF,stateTF,zipcodeTF,
			phoneTF,emailTF;

	@FXML private VBox contactVbox, emailVbox, phoneVbox;
	@FXML private JFXButton contactBtn,emailBtn,phoneBtn;
	@FXML private ImageView finalQRImage;

	 QRGenerator generator = new QRGenerator();
	final String QR_CODE_IMAGE_PATH = "src/main/resources/images/qrcode.png";
	
	/* ************ Class Methods***************** */

	//sets label that displays current qr option
	@FXML private void displayUserSelection(){
	String userSelection = qrTypes.getValue();
	userSelectionLabel.setText(userSelection);
	}
	
	//LISTEN FOR USER SELECTION AND DISPLAY APPROPRIATE FIELDS
	@FXML
	private void attachComboBoxListener() {
		ObservableList<String> qrOptions = FXCollections.observableArrayList("Contact QR", "Email QR", "Phone QR");
		qrTypes.setItems(qrOptions);

		qrTypes.getSelectionModel().selectedItemProperty()
			.addListener((observable, oldValue, newValue) -> {
				displayUserSelection();
				grabUserSelection();
			});
	}


	//switch statement for displaying appropriate v boxes and buttons
	public void grabUserSelection(){
		switch (userSelectionLabel.getText()){
			case "Contact QR" -> {
				contactVbox.setVisible(true);
				contactBtn.setVisible(true);
				emailVbox.setVisible(false);
				phoneVbox.setVisible(false);
			}
			case "Email QR" -> {
				emailVbox.setVisible(true);
				emailBtn.setVisible(true);
				phoneVbox.setVisible(false);
				contactVbox.setVisible(false);
			}
			case "Phone QR"-> {
				phoneVbox.setVisible(true);
				phoneBtn.setVisible(true);
				contactVbox.setVisible(false);
				emailVbox.setVisible(false);
			}

		}
	}

	
	//generate customer data button handler
	@FXML public void handleGenerateCustomerData() throws IOException, WriterException {
	   var results =   nameTF.getText() + ","
		             + addressTF.getText() +
		             "," + cityTF.getText() +
		             "," + stateTF.getText() +
		             "," + zipcodeTF.getText();

	   generator.renderQRCodeToImageView(results,QR_CODE_IMAGE_PATH);
	   displayQRCode();
	}


	//email button method
	@FXML private void handleGenerateEmailData() throws IOException, WriterException {
	// email input must adhere to the emailRegex pattern
	String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
	String email = emailTF.getText();

	// listener to determine if user entered the correct format.
	// Upon invalid input the text-field is cleared and an error
    emailTF.textProperty().addListener((observable, oldValue, newValue) -> {
		if(!newValue.matches(emailRegex) || newValue.isEmpty()) {
			emailTF.setPromptText("Invalid Email Input!");
			emailTF.setStyle("-fx-prompt-text-fill:cyan");

		}
    });
		generator.renderQRCodeToImageView(email, QR_CODE_IMAGE_PATH);
		displayQRCode();

	}

	// phone number button method
	//.phoneRegex pattern used to validate phone number format
	@FXML private void handleGeneratePhoneData() throws IOException, WriterException {
		//international phone number format
		var phoneRegex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		String phone = phoneTF.getText();

		//implement action listener
		phoneTF.textProperty().addListener((observable, oldValue, newValue) -> {
			if(!newValue.matches(phoneRegex) || newValue.isEmpty()) {
			phoneTF.setPromptText("Invalid Phone Number Input!");
			phoneTF.setStyle("-fx-prompt-text-fill:cyan");		}
		});

		generator.renderQRCodeToImageView(phone,QR_CODE_IMAGE_PATH);
		displayQRCode();
	}

	//renders qr image imageView.
	// resource path required to properly display image otherwise
	//an imageio exception is thrown
	@FXML public void displayQRCode() throws FileNotFoundException {
	Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/qrcode.png")));
	finalQRImage.setImage(image);
  	}
		}

