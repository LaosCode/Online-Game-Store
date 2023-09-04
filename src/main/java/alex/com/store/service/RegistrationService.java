package alex.com.store.service;

import alex.com.store.constants.ErrorMessage;
import alex.com.store.dto.request.UserRequest;
import alex.com.store.dto.response.MessageResponse;
import alex.com.store.model.Role;
import alex.com.store.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MessageResponse register(UserRequest userRequest) {
        System.out.println(userRequest);
        if (userRequest.getPassword() != null && !userRequest.getPassword().equals(userRequest.getPassword2())) {
            return new MessageResponse("passwordError", ErrorMessage.PASS_ERROR);
        }
        if (userService.findUserByEmail(userRequest.getEmail()) != null) {
            return new MessageResponse("emailError", ErrorMessage.MAIL_IN_USE);
        }
        User user = modelMapper.map(userRequest, User.class);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return new MessageResponse("success", "New account was created");
    }
}
