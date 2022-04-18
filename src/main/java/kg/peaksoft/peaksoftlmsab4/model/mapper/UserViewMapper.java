package kg.peaksoft.peaksoftlmsab4.model.mapper;

import kg.peaksoft.peaksoftlmsab4.api.payload.UserResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserViewMapper {

    public UserResponse viewUser(UserEntity user) {
        if (user == null) {
            return null;
        }

        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getAuthInfo().getEmail());
        return userResponse;
    }
}
