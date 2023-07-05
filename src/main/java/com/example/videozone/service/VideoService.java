package com.example.videozone.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Transactional
    public void uploadVideo(MultipartFile file) {
        try {
            ObjectId fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename());
            // Możesz wykonać dodatkowe operacje, np. zapisanie metadanych pliku w kolekcji "metadata"
        } catch (IOException e) {
            // Obsługa błędu wejścia/wyjścia
            throw new RuntimeException("Wystąpił błąd podczas przesyłania pliku.", e);
        } catch (Exception e) {
            // Obsługa innych wyjątków
            throw new RuntimeException("Wystąpił nieznany błąd.", e);
        }
    }
}
