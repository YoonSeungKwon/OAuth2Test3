package yoon.test.oAuthTest3.api;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.oAuthTest3.service.MemberService;
import yoon.test.oAuthTest3.vo.request.MemberDto;
import yoon.test.oAuthTest3.vo.response.MemberResponse;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/join")
    public ResponseEntity<?> memberJoin(MemberDto dto, HttpServletResponse response) throws IOException {
        MemberResponse memberResponse = memberService.join(dto);
        response.sendRedirect("/");
        return ResponseEntity.ok(memberResponse);
    }

    @GetMapping("/logout")
    public String logOut(){
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        memberService.logout(principal);
        return "redirect:/";
    }

}
