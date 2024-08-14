package com.it355.projekat.controller;

import com.it355.projekat.entity.Member;
import com.it355.projekat.entity.UserEntity;
import com.it355.projekat.entity.enums.MemberType;
import com.it355.projekat.service.MemberService;
import com.it355.projekat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final UserService userService;

    // PAGES START
    @GetMapping("")
    public String getMembers(Model model) {
        model.addAttribute(
                "members", memberService.findAll()
                .stream()
                .sorted(Comparator.comparing(Member::getType))
                .collect(Collectors.toList())
        );
        return "member/members";
    }

    @GetMapping("/member-profile")
    public String getMemberProfile(Model model) {
        UserEntity user = userService.getLoggedInUser();
        model.addAllAttributes(Map.of(
                "types", MemberType.values(),
                "loggedInMember", memberService.findByUserId(user.getId())
        ));
        return "member/member-profile";
    }
    // PAGES END

    @PostMapping("/save")
    public String savePremiumMember(@RequestParam("userId") Integer userId) {
        memberService.savePremiumMember(userId);
        return "redirect:/watches";
    }
}
