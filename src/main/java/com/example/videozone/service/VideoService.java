package com.example.videozone.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoService {
    @Autowired
    private GridFsTemplate gridFsTemplate;

    public void uploadVideo(MultipartFile file) {
        try {
            ObjectId fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename());
            // Możesz wykonać dodatkowe operacje, np. zapisanie metadanych pliku w kolekcji "metadata"
        } catch (IOException e) {
            // Obsługa błędu
        }
    }
}

