package com.example.demo6.security;


import com.example.demo6.dao.MemberDao;
import com.example.demo6.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

// 아이디를 가지고 db 에서 사용자 정보를 읽어와 스프링 시큐리티에 넘겨주는 클래스
@Component
public class Demo6UserDetailService implements UserDetailsService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member m = memberDao.loadLoginData(username).orElseThrow(()-> new UsernameNotFoundException("사용자가 없습니다"));
        // 아이디, 비밀번호, 권한, 계정 블록 여부 등을 담은 스프링 시큐리티 표준 UserDetails 를 리턴
        return User.builder().username(m.getUsername()).password(m.getPassword()).roles(m.getRole().name())
                .accountLocked(m.isLock()).build();
    }
}
