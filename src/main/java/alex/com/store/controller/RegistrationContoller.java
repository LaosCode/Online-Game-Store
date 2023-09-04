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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/registration")
public class RegistrationContoller {
    private final RegistrationService registrationService;


    @GetMapping()
    public String showRegistrationPage(@ModelAttribute("userRequest") UserRequest userRequest) {
        return "auth/registration";
    }

    @PostMapping()
    public String registration(@ModelAttribute("userRequest") @Valid UserRequest userRequest,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }

        MessageResponse messageResponse = registrationService.register(userRequest);
        model.addAttribute(messageResponse.getResponse(), messageResponse.getMessage());
        return "auth/registration";
    }


}
