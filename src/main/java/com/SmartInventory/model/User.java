package com.SmartInventory.model;

import com.SmartInventory.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="Profiles")
    private Set<Integer> profiles = new HashSet<>();

    public User(Long id, String name, String userName, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        addProfile(Profile.USER);
    }

    public User(){
        addProfile(Profile.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream()
                .map(x-> Profile.toEnum(x))
                .collect(Collectors.toSet());
    }

    public void setProfiles(Set<Integer> profiles) {
        this.profiles = profiles;
    }

    public void addProfile(Profile profile){
        profiles.add(profile.getCode());
    }

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



    
}
