package kg.peaksoft.peaksoftlmsab4.enumPackage;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    INSTRUCTOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
