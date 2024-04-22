package by.dmitry_skachkov.user_service.core.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class UserSecurity implements GrantedAuthority {

    private String uuid;
    private String role;

    public UserSecurity() {
    }

    public UserSecurity(String uuid, String role) {
        this.uuid = uuid;

        this.role = role;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return "ROLE_" + role;
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSecurity that = (UserSecurity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, role);
    }
}