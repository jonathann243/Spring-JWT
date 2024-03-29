package com.example.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
// Creation role en y  mettant de permission
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));
    private final Set<ApplicationUserPermission> permissions;

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
     //Gen rol use
    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set <SimpleGrantedAuthority> permisions = getPermissions().stream().
                map(permissions-> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());
        permisions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permisions;
    }


}
