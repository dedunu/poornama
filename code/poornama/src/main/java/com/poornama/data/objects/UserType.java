package com.poornama.data.objects;

import javax.persistence.*;

/**
 * Created by dedunu on 10/22/14.
 */
@Entity
@Table
public class UserType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

