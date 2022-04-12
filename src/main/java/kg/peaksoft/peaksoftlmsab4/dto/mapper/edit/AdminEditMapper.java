package kg.peaksoft.peaksoftlmsab4.dto.mapper.edit;

import kg.peaksoft.peaksoftlmsab4.dto.request.AdminRequest;
import kg.peaksoft.peaksoftlmsab4.entity.Admin;
import kg.peaksoft.peaksoftlmsab4.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.enumPackage.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
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