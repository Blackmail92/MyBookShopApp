package com.example.MyBookShopApp.data;

import com.example.MyBookShopApp.data.entities.simple.BookFile;
import com.example.MyBookShopApp.data.repos.BookFileRepo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Service
public class ResourceStorage {
    private final BookFileRepo fileRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${download.path}")
    private String downloadPath;

    @Autowired
    public ResourceStorage(BookFileRepo fileRepo) {
        this.fileRepo = fileRepo;
    }

    public String saveNewBookImage(MultipartFile file, String slug) throws IOException {
        String resUri = null;
        if (!file.isEmpty()) {
            if (!new File(uploadPath).exists()) {
                Files.createDirectories(Paths.get(uploadPath));
                Logger.getLogger(this.getClass().getSimpleName()).info("created img folder in " + uploadPath);
            }
        }

        String fileName = slug + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        Path path = Paths.get(uploadPath, fileName);
        resUri = "/book-covers/" + fileName;
        file.transferTo(path); // uploading user file here
        Logger.getLogger(this.getClass().getSimpleName()).info(fileName + " uploaded OK");
        return resUri;
    }

    public Path getFilePath(String hash) {
        BookFile file = fileRepo.findBookFileByHash(hash);
        return Paths.get(file.getPath());
    }

    public MediaType getFileMime(String hash) {
        BookFile file = fileRepo.findBookFileByHash(hash);
        String mime = URLConnection.guessContentTypeFromName(Paths.get(file.getPath()).getFileName().toString());
        return StringUtils.hasLength(mime) ? MediaType.parseMediaType(mime) : MediaType.APPLICATION_OCTET_STREAM;
    }

    public byte[] getFileByteArray(String hash) throws IOException {
        BookFile file = fileRepo.findBookFileByHash(hash);
        Path path = Paths.get(downloadPath, file.getPath());
        return Files.readAllBytes(path);
    }
}
