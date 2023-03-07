package com.example.week8.security;

import com.example.week8.entity.Account;
import com.example.week8.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class userDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //로그인 때 입력한 아이디
        Account account = accountRepository.findByName(username).orElseThrow(
                () -> new IllegalArgumentException("userDetailsService 해당하는 아이디 없습니다.")
        );
        System.out.println("userDetailsService username : "+ username);
        return UserDetailsImpl.builder()
                .username(account.getName())
                .password(account.getPassword())
                .role(account.getRole())
                .build();
    }

}
