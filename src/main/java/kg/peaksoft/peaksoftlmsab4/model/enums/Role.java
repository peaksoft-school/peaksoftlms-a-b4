package kg.peaksoft.peaksoftlmsab4.model.enums;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    INSTRUCTOR,
    STUDENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
