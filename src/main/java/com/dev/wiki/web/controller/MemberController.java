package com.dev.wiki.web.controller;

import com.dev.wiki.domain.member.Member;
import com.dev.wiki.service.member.MemberService;
import com.dev.wiki.web.dto.SignUpDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/sign-up")
    public String showSignUpPage(Model model) {
        model.addAttribute("signUpDto", new SignUpDto());
        return "member/signup";
    }

    @PostMapping("/member/sign-up")
    @PreAuthorize("isAnonymous()")
    public String doSignUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || memberService.isDuplicateInfo(signUpDto)) {
            model.addAttribute("signUpDto", signUpDto);
            return "member/signup";
        }
        memberService.join(signUpDto);
        return "redirect:/member/login";
    }

    @GetMapping("/member/login")
    public String showLoginPage() {
        return "member/login";
    }

}
