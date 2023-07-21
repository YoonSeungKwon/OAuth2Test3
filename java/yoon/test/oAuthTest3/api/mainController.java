package yoon.test.oAuthTest3.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class mainController {

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/api/member/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/api/member/join")
    public String joinPage(){
        return "join";
    }

    @GetMapping("/user/member")
    public String userPage(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("name", auth);
        return "user";
    }
}
