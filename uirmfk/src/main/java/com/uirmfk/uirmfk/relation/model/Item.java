package com.uirmfk.uirmfk.relation.model;
import com.uirmfk.uirmfk.uploadimage.model.FileDB;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameItem;
    private String emailItem;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<User> users;

    @OneToMany(targetEntity = ImageDescription.class, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ImageDescription> imageDescriptions;


    public Item() {
    }

    public Item(String nameItem, String emailItem) {
        this.nameItem = nameItem;
        this.emailItem = emailItem;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<ImageDescription> getImageDescriptions() {
        return imageDescriptions;
    }

    public void setImageDescriptions(List<ImageDescription> imageDescriptions) {
        this.imageDescriptions = imageDescriptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getEmailItem() {
        return emailItem;
    }

    public void setEmailItem(String emailItem) {
        this.emailItem = emailItem;
    }

}
