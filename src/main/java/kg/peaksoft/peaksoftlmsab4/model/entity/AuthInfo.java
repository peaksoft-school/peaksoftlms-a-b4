package kg.peaksoft.peaksoftlmsab4.model.entity;

import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "auth_info")
@Getter
@Setter
public class AuthInfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public AuthInfo(String email,
                    String password,
                    Role role, boolean isAccountNonExpired,
                    boolean isAccountNonLocked,
                    boolean isCredentialsNonExpired,
                    boolean isEnabled) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public AuthInfo() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

}
