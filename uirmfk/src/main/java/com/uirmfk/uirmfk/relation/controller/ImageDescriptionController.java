package com.uirmfk.uirmfk.relation.controller;

import com.uirmfk.uirmfk.relation.model.ImageDescription;
import com.uirmfk.uirmfk.relation.repository.ImageDescriptionRepository;
import com.uirmfk.uirmfk.uploadimage.exception.ResourceNotFoundException;
import com.uirmfk.uirmfk.uploadimage.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ImageDescriptionController {

    @Autowired
    private ImageDescriptionRepository imageDescriptionRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    @GetMapping("/files/{fileDbId}/imageDescription")
    public Page<ImageDescription> getAllDescriptionByFileId(@PathVariable (value = "fileDbId") String fileDbId,
                                                         Pageable pageable) {
        return imageDescriptionRepository.findByFileDbId(fileDbId, pageable);
    }

    @PostMapping("/files/{fileDbId}/imageDescription")
    public ImageDescription createDescription(@PathVariable (value = "fileDbId") String fileDbId,
                                 @Valid @RequestBody ImageDescription imageDescription) {
        return fileDBRepository.findById(fileDbId).map(fileDB -> {
            imageDescription.setFileDB(fileDB);
            return imageDescriptionRepository.save(imageDescription);
        }).orElseThrow(() -> new ResourceNotFoundException("ImageId " + fileDbId + " not found"));
    }
/*
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }

 */
}
