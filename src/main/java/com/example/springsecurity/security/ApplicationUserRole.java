package com.example.springsecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.springsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet())
    ,ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ,STUDENT_WRITE));
    private final Set<ApplicationUserPermission>  permissions ;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
    public  Set< SimpleGrantedAuthority> getGrantedAuthorities () {
       Set<SimpleGrantedAuthority> permission = getPermissions().stream()
                .map(permissions->new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());
       permission.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
       return  permission ;

    }
}
