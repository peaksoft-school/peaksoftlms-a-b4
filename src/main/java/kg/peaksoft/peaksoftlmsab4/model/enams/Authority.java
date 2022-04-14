package kg.peaksoft.peaksoftlmsab4.model.enams;

import org.springframework.security.core.GrantedAuthority;

public enum

Authority implements GrantedAuthority {
    STUDENT,
    INSTRUCTOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}