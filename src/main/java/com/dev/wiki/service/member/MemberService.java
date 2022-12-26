package com.dev.wiki.service.member;

import com.dev.wiki.domain.member.Member;
import com.dev.wiki.domain.member.MemberRepository;
import com.dev.wiki.web.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    /** 회원가입 로직
     * @param signUpDto 회원가입 입력 정보
     */
    public Member join(SignUpDto signUpDto) {
        Member newMember = modelMapper.map(signUpDto, Member.class);
        newMember.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return memberRepository.save(newMember);
    }

    public boolean isDuplicateInfo(SignUpDto signUpDto) {
        boolean usernameChk = memberRepository.existsByNickname(signUpDto.getUsername());
        boolean nicknameChk = memberRepository.existsByNickname(signUpDto.getNickname());
        boolean emailChk = memberRepository.existsByEmail(signUpDto.getEmail());

        return usernameChk && nicknameChk && emailChk;
    }

}
