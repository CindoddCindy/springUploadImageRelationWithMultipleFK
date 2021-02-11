package com.uirmfk.uirmfk.uploadimage.controller;

import com.uirmfk.uirmfk.uploadimage.exception.ResourceNotFoundException;
import com.uirmfk.uirmfk.uploadimage.model.FileDB;
import com.uirmfk.uirmfk.uploadimage.repository.FileDbRepoDua;
import com.uirmfk.uirmfk.uploadimage.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class FileDbControllerDua {

    @Autowired
    private FileDbRepoDua fileDbRepoDua;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts/{postId}/filedb")
    public Page<FileDB> getAllFilesByPostId(@PathVariable (value = "postId") Long postId,
                                               Pageable pageable) {
        return fileDbRepoDua.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/filedb")
    public FileDB createFileDb(@PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody FileDB fileDB) {
        return postRepository.findById(postId).map(post -> {
            fileDB.setPost(post);
            return fileDbRepoDua.save(fileDB);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
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
