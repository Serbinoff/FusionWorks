package com.example.CodeFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name = "App_User")
       /* uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })*/
public class AppUser {
 
    @Id
    @GeneratedValue
    @Column(name = "User_Id", nullable = false)
    private Long userId;
    
    @Column(name = "Role_Id", nullable = false)
    @NotNull
    private Long roleId;
 
    @Column(name = "User_Name", length = 36, nullable = false)
    @NotEmpty
    @Size(min=3, max=10)
    private String userName;
 
    @Column(name = "Encryted_Password", length = 128, nullable = false)
    @NotEmpty
    @Size(min=3, max=15)
    private String encrytedPassword;
 
    public AppUser() {}

    public AppUser(Long userId, Long roleId, String userName, String encrytedPassword) {
		this.userId = userId;
		this.roleId = roleId;
		this.userName = userName;
		this.encrytedPassword = encrytedPassword;
	}

	public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getEncrytedPassword() {
        return encrytedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
 
}
