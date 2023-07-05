package com.example.videozone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.videozone.model.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
    // Metody dostępu do danych związanych z filmami
}
