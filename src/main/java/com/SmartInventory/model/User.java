package com.SmartInventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class User extends AbstractEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(unique=true)
    private String userName;

    @JsonIgnore
    @NotEmpty
    private String password;

    @NotEmpty
    private boolean admin;

    public User(Long id, String name, String userName, String password, boolean admin) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }

    public User(){}



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }






    
}
