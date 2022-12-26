package com.dev.wiki.service.member;

import com.dev.wiki.domain.member.AuthMember;
import com.dev.wiki.domain.member.Member;
import com.dev.wiki.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailServices implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getAuthLevel() == 7) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        return new AuthMember(member, authorities);
    }
}
