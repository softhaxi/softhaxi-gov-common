package com.softhaxi.marves.core.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.softhaxi.marves.core.properties.FileStorageProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final Path storagePath;

    @Autowired
    public FileStorageService(FileStorageProperties properties) {
        this.storagePath = Paths.get(properties.getPath()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.storagePath);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public String store(String folder, String rename, MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if(filename.contains("..")) {
            throw new RuntimeException("[store] File contains invalid path sequence" + filename);
        }

        Path targetFolder = Paths.get(this.storagePath.toString(), folder);
        logger.info("[store] Target folder path.." + targetFolder.toString());
        if(!targetFolder.toFile().exists()) {
            try {
                Files.createDirectories(targetFolder);
            } catch(Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        rename = String.format("%s.%s", rename, filename.substring(filename.lastIndexOf(".") + 1));
        Path targetPath = targetFolder.resolve(rename);
        logger.info("[store] Target path.." + targetPath.toString());
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        //return targetPath.toString();
        return Paths.get(folder, rename).toString();
    }
}
