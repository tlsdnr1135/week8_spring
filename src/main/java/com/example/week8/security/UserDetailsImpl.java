package com.example.week8.security;

import com.example.week8.enums.AccountRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private AccountRoleEnum role;


    @Builder
    public UserDetailsImpl(String username, String password, AccountRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        AccountRoleEnum accountRole = this.role;
        String authority = accountRole.getAccountRole();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);


        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

//        Collection<GrantedAuthority>  sd = List.of(simpleGrantedAuthority);
        return authorities;

    }

    public AccountRoleEnum getRole(){
        return role;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
