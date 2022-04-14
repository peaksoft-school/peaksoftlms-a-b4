package kg.peaksoft.peaksoftlmsab4.dto.mapper.view;

import kg.peaksoft.peaksoftlmsab4.dto.response.AdminResponse;
import kg.peaksoft.peaksoftlmsab4.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminViewMapper {

    public AdminResponse convertToAdminResponse(Admin admin) {
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
