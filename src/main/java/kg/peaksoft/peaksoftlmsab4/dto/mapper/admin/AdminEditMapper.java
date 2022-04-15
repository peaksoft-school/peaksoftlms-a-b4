package kg.peaksoft.peaksoftlmsab4.dto.mapper.admin;

import kg.peaksoft.peaksoftlmsab4.dto.admin.AdminRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.Admin;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class AdminEditMapper {

    public Admin saveAdmin(AdminRequest adminRequest) {
        if (adminRequest == null) {
            return null;
        }

        Admin admin = new Admin();
        admin.setFirstName(adminRequest.getFirstName());
        admin.setLastName(adminRequest.getLastName());

        AuthInfo authInfo = new AuthInfo();
        authInfo.setRole(Role.ADMIN);
        authInfo.setEmail(adminRequest.getEmail());
        authInfo.setPassword(adminRequest.getPassword());

        admin.setAuthInfo(authInfo);
        return admin;

    }
}