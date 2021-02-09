package com.uirmfk.uirmfk.relation.model;
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

    public Item() {
    }

    public Item(String nameItem, String emailItem) {
        this.nameItem = nameItem;
        this.emailItem = emailItem;
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
