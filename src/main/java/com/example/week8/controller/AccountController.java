package com.example.week8.controller;


import com.example.week8.dto.account.join.AccountReqDto;
import com.example.week8.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    //회원가입
    @PostMapping("/api/account/join")
    public ResponseEntity<String> accountJoin(@RequestBody AccountReqDto accountReqDto){
        System.out.println("Controller accountJoin안");
        return ResponseEntity.ok().body(accountService.accountJoin(accountReqDto));
    }





//    //테스트 요청(전체 권한)
//    @GetMapping("/api/account/all")
//    public String allApi(){
//        System.out.println("Controller allApi 안");
//        return "allApi 요청";
//    }
//
//    //테스트 요청(로그인 - 전체 권한)
//    @GetMapping("/api/account/test")
//    public String testApi(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        //SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("Controller testAPI 안");
//        System.out.println(userDetails.getUsername());
//        System.out.println(userDetails.getAuthorities());
//        return "testApi";
//    }
//
//    //테스트 요청(유저 권한)
//    @GetMapping("/api/account/user")
//    public String testUserApi(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        System.out.println("Controller testUserApi 안");
//        System.out.println(userDetails.getUsername());
//        System.out.println(userDetails.getAuthorities());
//        return "testUserApi";
//    }
//
//    //테스트 요청(어드민 권한)
//    @GetMapping("/api/account/admin")
//    public String testAdminApi(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        System.out.println("Controller testAdminApi 안");
//        System.out.println(userDetails.getUsername());
//        System.out.println(userDetails.getAuthorities());
//        return "testAdminApi";
//    }

}
