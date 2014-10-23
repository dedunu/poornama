package com.poornama.data.objects;

import com.poornama.api.security.PasswordHash;

import javax.persistence.*;

/**
 * Created by dedunu on 10/21/14.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userName"}))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "userName")
    private String userName;
    private String displayName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PasswordHash passwordHash = new PasswordHash();
        String hashedPassword = passwordHash.getHash(this.getUserName(), password);
        this.password = hashedPassword;
    }


}
