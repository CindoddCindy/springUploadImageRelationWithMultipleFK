package com.uirmfk.uirmfk.uploadimage.services;


import java.io.IOException;
import java.util.stream.Stream;

import com.uirmfk.uirmfk.uploadimage.model.FileDB;
import com.uirmfk.uirmfk.uploadimage.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file, String text, String names, String email) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), text.toString(),names.toString(),email.toString());
        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }


    /**
     * Create a new role along with users
     */

    @Transactional
    public ResponseEntity<Object> addFile(FileDB fileDB) {

        FileDB newFileDb = new FileDB();
        newFileDb.setNames(fileDB.getNames());
        newFileDb.setEmail(fileDB.getEmail());
        newFileDb.setText(fileDB.getText());
        newFileDb.setName(fileDB.getName());
        newFileDb.setData(fileDB.getData());
        newFileDb.setType(fileDB.getType());
        FileDB savedFileDb = fileDBRepository.save(newFileDb);
        if (fileDBRepository.findById(savedFileDb.getId()).isPresent()) {
            return ResponseEntity.accepted().body("Successfully Created Role and Users");
        } else
            return ResponseEntity.unprocessableEntity().body("Failed to Create specified Role");
    }

}
