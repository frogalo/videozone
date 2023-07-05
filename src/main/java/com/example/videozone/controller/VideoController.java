package com.example.videozone.controller;

import com.example.videozone.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        // Logika obsługi przesłanego pliku
        // Przekazanie pliku do VideoService w celu umieszczenia w bazie danych
        videoService.uploadVideo(file);

        return ResponseEntity.ok("Plik został pomyślnie przesłany.");
    }
}
