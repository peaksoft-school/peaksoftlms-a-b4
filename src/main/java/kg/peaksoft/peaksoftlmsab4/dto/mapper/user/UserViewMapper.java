package kg.peaksoft.peaksoftlmsab4.dto.mapper.user;

import kg.peaksoft.peaksoftlmsab4.dto.user.UserResponse;
import kg.peaksoft.peaksoftlmsab4.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserViewMapper {

    public UserResponse viewUser(User user) {
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