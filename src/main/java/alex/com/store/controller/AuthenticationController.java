package alex.com.store.controller;

import alex.com.store.dto.request.UserRequest;
import alex.com.store.dto.response.MessageResponse;
import alex.com.store.service.AuthenticationService;
import alex.com.store.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final RegistrationService registrationService;

    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }


}
