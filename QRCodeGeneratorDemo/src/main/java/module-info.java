module com.demo.qrcodegeneratordemo {
	requires javafx.controls;
	requires javafx.fxml;

	requires com.jfoenix;
	requires jfxtras.controls;                                                                                                                                                                 
	requires jfxtras.common;
	requires materialfx.all;
	requires de.jensd.fx.glyphs.fontawesome;
	
	requires com.google.zxing;
	requires com.google.zxing.javase;
	requires java.desktop;


	opens com.demo.qrcodegeneratordemo to javafx.fxml;
	exports com.demo.qrcodegeneratordemo;

}