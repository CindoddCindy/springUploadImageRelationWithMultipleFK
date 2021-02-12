package com.uirmfk.uirmfk.uploadimage.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.uirmfk.uirmfk.relation.model.AuditModel;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "files")
public class FileDB extends AuditModel {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;


    @NotNull
    private String text;

    @NotNull
    private String names;

    @NotNull
    private String email;


/*
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;

 */


    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }


    public FileDB(String text, String names, String email) {
        this.text = text;
        this.names = names;
        this.email = email;
    }



    public String getId() {//id tadinya string di ganti ke long
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(targetEntity = Post.class, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> post) {
        this.posts = posts;
    }

    public FileDB(String name, String type, byte[] data, String text, String names, String email, List<Post> posts) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.text = text;
        this.names = names;
        this.email = email;
        this.posts=posts;

    }
}
