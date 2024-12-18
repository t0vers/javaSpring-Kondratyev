package org.example.module7.domain;

import lombok.Data;
import org.example.module7.config.UserRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Document("user")
public class User implements UserDetails {
    @Id
    String id;
    String username;
    String email;
    @Field
    Set<UserRole> roleSet;
    String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleSet;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
