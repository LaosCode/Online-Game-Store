package alex.com.store.service;

import alex.com.store.dto.request.EditUserRequest;
import alex.com.store.model.User;
import alex.com.store.repository.UserRepository;
import alex.com.store.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findUserByEmail(userPrincipal.getEmail());
    }

    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void editUserData(EditUserRequest request) {
        User user = getAuthenticatedUser();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setCity(request.getCity());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPostIndex(request.getPostIndex());
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User showUserById(int id) {
        return userRepository.getReferenceById(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
