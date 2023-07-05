package com.example.videozone.service;

import com.example.videozone.model.Video;
import com.example.videozone.repository.VideoRepository;
import com.mongodb.client.gridfs.GridFSFindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.mongodb.client.gridfs.model.GridFSFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

@Service
public class VideoService {
    @Autowired
    private GridFsOperations gridFsOperations;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

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


    public List<Video> getAllVideos() {
        List<Video> videos = videoRepository.findAll();

        List<String> videoIds = new ArrayList<>();
        for (Video video : videos) {
            videoIds.add(video.getId());
        }

        List<String> titles = new ArrayList<>();
        for (String videoId : videoIds) {
            Query query = Query.query(Criteria.where("_id").is(videoId));
            GridFSFile gridFSFile = gridFsTemplate.findOne(query);
            if (gridFSFile != null) {
                titles.add(gridFSFile.getMetadata().getString("title"));
            }
        }

        // Do whatever you want with the titles...

        return videos;
    }

    public List<String> getAllVideoTitles() {
        Query query = new Query();
        GridFSFindIterable files = gridFsTemplate.find(query);

        List<String> titles = new ArrayList<>();
        for (GridFSFile file : files) {
            Document metadata = file.getMetadata();
            if (metadata != null) {
                if (metadata.containsKey("title")) {
                    titles.add(metadata.getString("title"));
                } else {
                    titles.add(file.getFilename());
                }
            } else {
                titles.add(file.getFilename());
            }
        }

        return titles;
    }
}
