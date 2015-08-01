package com.poornama.api.objects;

import com.poornama.api.security.PasswordHash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userName"}))
public class User {
    /**
     * Automatic value generation is specified using
     * annotations.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "userName")
    private String userName;

    private String displayName;

    private String password;

    /**
     * Many to one relationship between users and user roles is
     * imposed here using annotations.
     */
    @ManyToOne
    @JoinColumn(name = "userRoleId")
    private UserRole userRole;

    public String getUserName() {
        // Returns username
        return userName;
    }

    public void setUserName(String userName) {
        // Sets username to the local variable
        this.userName = userName;
    }

    public String getDisplayName() {
        // Returns display name from local variable
        return displayName;
    }

    public void setDisplayName(String displayName) {
        // Sets display name to the local variable
        this.displayName = displayName;
    }

    public UserRole getUserRole() {
        // Returns user role from local variable
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        // Sets user role to the local variable
        this.userRole = userRole;
    }

    public int getId() {
        // Returns id from local variable
        return id;
    }

    public void setId(int id) {
        // Sets id to the local variable
        this.id = id;
    }

    public String getPassword() {
        // Returns password from local variable
        return password;
    }

    public void setPassword(String password) {
        // Initialize PasswordHash class
        PasswordHash passwordHash = new PasswordHash();
        // Generate the hashed password using password and username
        String hashedPassword = passwordHash.getHash(this.getUserName(), password);
        // Sets password to the local variable
        this.password = hashedPassword;
    }

}
