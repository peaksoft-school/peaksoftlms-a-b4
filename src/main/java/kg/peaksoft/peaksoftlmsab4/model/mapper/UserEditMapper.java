package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.UserRequest;
import kg.peaksoft.peaksoftlmsab4.model.entity.AuthInfo;
import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class UserEditMapper {

    public UserEntity saveAdmin(UserRequest adminRequest) {
        if (adminRequest == null) {
            return null;
        }

        UserEntity user = new UserEntity();
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