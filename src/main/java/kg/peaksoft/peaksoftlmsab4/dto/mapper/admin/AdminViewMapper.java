package kg.peaksoft.peaksoftlmsab4.dto.mapper.admin;

import kg.peaksoft.peaksoftlmsab4.dto.admin.AdminResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminViewMapper {

    public AdminResponse viewUser(Admin admin) {
        if (admin == null) {
            return null;
        }
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setId(String.valueOf(admin.getId()));
        adminResponse.setFirstName(admin.getFirstName());
        adminResponse.setLastName(admin.getLastName());
        adminResponse.setEmail(admin.getAuthInfo().getEmail());

        return adminResponse;
    }
}
