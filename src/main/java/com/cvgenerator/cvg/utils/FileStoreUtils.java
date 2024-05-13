package com.cvgenerator.cvg.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class FileStoreUtils {

    public String uploadFile(MultipartFile photoFile) {
        try {
            log.info("Starting file upload ... ");
            String directoryPath = System.getProperty("user.home") + File.separator + "cv_gen";
            File directoryFile = new File(directoryPath);
            if (!directoryFile.exists()) {
                directoryFile.mkdirs();
            } else {
                log.info("Directory already exists");
            }
            String photoFilePath = directoryPath + File.separator + UUID.randomUUID() + "_" + photoFile.getOriginalFilename();
            File photo = new File(photoFilePath);
            photoFile.transferTo(photo);
            return photoFilePath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
