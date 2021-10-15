package com.example.models;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tags {
//    @GeneratedValue(strategy = GenerationType.AUTO)// todo не работает автогенерация при сохранении
    @Id
    private Long id;

    private String tag;

    Tags(){

    }

    public Tags(Long id, String tag) {
        this.id = id;
        System.err.println(this.id);
        this.tag = tag;
    }

    public Tags(String tag) {
        this.tag = tag;
        System.err.println(this.id);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return id + " " + tag;
    }
}
