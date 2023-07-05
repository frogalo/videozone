package com.example.videozone.controller;

import com.example.videozone.model.Video;
import com.example.videozone.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @GetMapping("/titles")
    public ResponseEntity<List<String>> getAllVideoTitles() {
        List<String> titles = videoService.getAllVideoTitles();
        return ResponseEntity.ok(titles);
    }

}
