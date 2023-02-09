package com.example.qr.demoqr.controller;

import com.example.qr.demoqr.utils.GenerateCode;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/generate")
public class CodeController {

    @PostMapping(value = "/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> codeQr(@RequestBody String barcode)
            throws Exception {
        return ResponseEntity.ok(GenerateCode.generateQRCodeImage(barcode));
    }

    @GetMapping("/qr/export/{message}")
    public ResponseEntity<String> exportQr(@PathVariable String message) throws IOException, WriterException {
        GenerateCode.exportQRCode(message);
        return ResponseEntity.ok("Qr created with successfully");
    }

    @GetMapping("/qr/export/logo/{message}")
    public ResponseEntity<String> exportQrLogo(@PathVariable String message) throws IOException, WriterException {
        GenerateCode.exportQrCodeLogo(message);
        return ResponseEntity.ok("Qr created with successfully");
    }
}
