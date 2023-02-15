package com.demo.qrcodegeneratordemo.qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.function.IntFunction;
import com.google.zxing.qrcode.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.util.stream.IntStream;

public class QRGenerator {

	public void renderQRCodeToImageView(String userData,String filePath) throws WriterException, IOException {
		final QRCodeWriter qrCodeWriter = new QRCodeWriter();
		final BitMatrix qrMatrix = qrCodeWriter.encode(userData, BarcodeFormat.QR_CODE, 150, 150
				, Collections.singletonMap(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M));
		Path qrPath2 = FileSystems.getDefault().getPath(filePath);
		MatrixToImageWriter.writeToPath(qrMatrix, "png", qrPath2);


	}

}
