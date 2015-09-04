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

/**
 * @author dedunu
 */
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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
        // Initialize PasswordHash class
        PasswordHash passwordHash = new PasswordHash();
        // Generate the hashed password using password and username
        String hashedPassword = passwordHash.getHash(this.getUserName(), password);
        // Sets password to the local variable
        this.password = hashedPassword;
	}

	/**
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


}
