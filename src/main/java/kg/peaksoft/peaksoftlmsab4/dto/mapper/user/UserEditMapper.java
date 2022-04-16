package kg.peaksoft.peaksoftlmsab4.dto.mapper.user;

import kg.peaksoft.peaksoftlmsab4.dto.user.UserRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.User;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserEditMapper {

    public User saveAdmin(UserRequest adminRequest) {
        if (adminRequest == null) {
            return null;
        }

        User user = new User();
        user.setFirstName(adminRequest.getFirstName());
        user.setLastName(adminRequest.getLastName());

        AuthInfo authInfo = new AuthInfo();
        authInfo.setRole(Role.ADMIN);
        authInfo.setEmail(adminRequest.getEmail());
        authInfo.setPassword(adminRequest.getPassword());

        user.setAuthInfo(authInfo);
        return user;

    }
}