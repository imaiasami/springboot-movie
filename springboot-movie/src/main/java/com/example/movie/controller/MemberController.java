package com.example.movie.controller;

import com.example.movie.model.dto.member.JoinMemberForm;
import com.example.movie.model.dto.member.LoginMemberForm;
import com.example.movie.model.entity.member.Member;
import com.example.movie.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("members")
@Controller
public class MemberController {

    private final MemberMapper memberMapper;

    // 회원가입 폼 이동
    @GetMapping("joinMember")
    public String joinMemberForm(Model model) {
        model.addAttribute("joinMember", new JoinMemberForm());
        return "members/joinMemberForm";
    }

    // 회원가입
    @PostMapping("joinMember")
    public String joinMember(@Validated @ModelAttribute("joinMember") JoinMemberForm joinMemberForm, BindingResult result) {
        // 회원가입 폼 유효성 검사
        if (result.hasErrors()) {
            return "members/joinMemberForm";
        }

        // 동일한 아이디로 가입된 회원이 있는지 체크
        Member findMember = memberMapper.findMemberById(joinMemberForm.getMember_id());
        if (findMember != null) {
            result.reject("joinError", "이미 사용중인 아이디 입니다.");
        }

        // 회원가입 처리
        memberMapper.joinMember(joinMemberForm.toMember(joinMemberForm));

        return "redirect:/";
    }

    // 로그인 폼 이동
    @GetMapping("loginMember")
    public String loginMemberForm(Model model) {
        model.addAttribute("loginMember", new LoginMemberForm());
        return "members/loginMemberForm";
    }

    // 로그인
    @PostMapping("loginMember")
    public String loginMember(@Validated @ModelAttribute("loginMember") LoginMemberForm loginMemberForm, BindingResult result, HttpServletRequest request) {
        // 로그인 폼 유효성 검사
        if (result.hasErrors()) {
            return "members/loginMemberForm";
        }

        // 패스워드 비교
        Member findMember = memberMapper.findMemberById(loginMemberForm.getMember_id());
        if (findMember == null || !findMember.getPassword().equals(loginMemberForm.getPassword())) {
            result.reject("loginError", "아이디 또는 패스워드가 다릅니다.");
            return "members/loginMemberForm";
        }
        // 로그인 성공 처리
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", findMember);

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

}
