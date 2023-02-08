package com.example.qr.demoqr.controller;

import com.example.qr.demoqr.utils.GenerateCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/generate")
public class CodeController {

    @PostMapping(value = "/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> codeQr(@RequestBody String barcode)
            throws Exception {
        return ResponseEntity.ok(GenerateCode.generateQRCodeImage(barcode));
    }
}
