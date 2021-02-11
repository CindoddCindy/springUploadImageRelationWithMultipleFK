package com.uirmfk.uirmfk.uploadimage.controller;


import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uirmfk.uirmfk.uploadimage.exception.ResourceNotFoundException;
import com.uirmfk.uirmfk.uploadimage.message.ResponseFile;
import com.uirmfk.uirmfk.uploadimage.message.ResponseMessage;
import com.uirmfk.uirmfk.uploadimage.model.FileDB;
import com.uirmfk.uirmfk.uploadimage.model.Post;
import com.uirmfk.uirmfk.uploadimage.repository.FileDBRepository;
import com.uirmfk.uirmfk.uploadimage.repository.PostRepository;
import com.uirmfk.uirmfk.uploadimage.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@Controller
@CrossOrigin("http://localhost:8081")
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @Autowired FileDBRepository fileDBRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            @RequestParam("file") MultipartFile file,
            @Param("text")String text, @Param("names") String names,
            @Param("email") String email, @Param("posts") List<Post> posts) {
        String message = "";
        try {
            storageService.store(file,text,names,email,posts);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }



    @PostMapping("/filedb")
    public FileDB createFileDb(@Valid @RequestBody FileDB fileDB) {
        return fileDBRepository.save(fileDB);
    }

    @PostMapping("/fileDb/{id}")
    public ResponseEntity<Object> createFile(@RequestBody FileDB fileDB) {
        return  storageService.addFile(fileDB);
    }


/*
    @Autowired
    //private FileDbRepoDua fileDbRepoDua;
    private FileDBRepository fileDBRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/{postId}/filedb")
    public Page<FileDB> getAllFilesByPostId(@PathVariable (value = "postId") Long postId,
                                            Pageable pageable) {
        return fileDBRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/filedb")
    public FileDB createFileDb(@PathVariable (value = "postId") Long postId,
                               @Valid @RequestBody FileDB fileDB) {
        return postRepository.findById(postId).map(post -> {
            fileDB.setPost(post);
            return fileDBRepository.save(fileDB);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

 */
}
