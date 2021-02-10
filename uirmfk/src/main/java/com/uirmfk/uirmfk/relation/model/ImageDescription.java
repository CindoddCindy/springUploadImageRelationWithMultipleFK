package com.uirmfk.uirmfk.relation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.uirmfk.uirmfk.uploadimage.model.FileDB;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "image_description")

public class ImageDescription extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    @Lob
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fileDb_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FileDB fileDB;

    public ImageDescription(@NotNull String text) {
        this.text = text;
    }

    public ImageDescription() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public FileDB getFileDB() {
        return fileDB;
    }

    public void setFileDB(FileDB fileDB) {
        this.fileDB = fileDB;
    }
}
