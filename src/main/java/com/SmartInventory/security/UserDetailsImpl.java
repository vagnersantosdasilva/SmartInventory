package com.SmartInventory.security;

import com.SmartInventory.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private String userName;
    private String password;
    private String displayName;
    private Boolean admin;
    private Collection<? extends  GrantedAuthority> authorities;

    public UserDetailsImpl(User user){
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.displayName = user.getName();
        this.authorities = user.getProfiles()
                .stream()
                .map(e ->new SimpleGrantedAuthority(e.getDescription()))
                .collect(Collectors.toList());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO: implementar regras de níveis de acesso
        //if (admin) return AuthorityUtils.createAuthorityList()
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getDisplayName(){
        return displayName;
    }
}
