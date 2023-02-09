package com.example.qr.demoqr.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class GenerateCode {

    private static final String PATH="C:\\QR\\qrCreated.jpg";

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        ByteArrayOutputStream stream = QRCode
                .from(barcodeText)
                .withSize(250, 250)
                .stream();
        ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

        return ImageIO.read(bis);
    }

    public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static void exportQRCode(String message) throws WriterException, IOException {
        BitMatrix bitMatrix= new MultiFormatWriter().encode(message,BarcodeFormat.QR_CODE,500,500);
        MatrixToImageWriter.writeToPath(bitMatrix,"jpg", Path.of(PATH));
    }

    public static void exportQrCodeLogo(String message) throws IOException, WriterException {
        BitMatrix bitMatrix= new MultiFormatWriter().encode(message,BarcodeFormat.QR_CODE,400,400);
        MatrixToImageConfig imageConfig=new MatrixToImageConfig(MatrixToImageConfig.BLACK,MatrixToImageConfig.WHITE);

        BufferedImage qrImage=MatrixToImageWriter.toBufferedImage(bitMatrix,imageConfig);
        BufferedImage logoImage=ImageIO.read(new File("C:\\QR\\pencil.png"));
        int finalImageHeight=qrImage.getHeight() - logoImage.getHeight();
        int finalImageWidth=qrImage.getWidth() - logoImage.getWidth();

        BufferedImage finalImage= new BufferedImage(qrImage.getHeight(),qrImage.getWidth(),BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics= (Graphics2D) finalImage.getGraphics();
        graphics.drawImage(qrImage,0,0,null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        graphics.drawImage(logoImage,(int) Math.round(finalImageWidth / 2),(int) Math.round(finalImageHeight / 2), null);
        ImageIO.write(finalImage,"png",new File("C:\\QR\\qrWithLogo.png"));
    }

}
