package com.example.week8.controller;

import com.example.week8.entity.Agroup;
import com.example.week8.repository.AgroupRepository;
import com.example.week8.service.AgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AgroupController {

    private final AgroupService agroupService;

    @PostMapping("/api/agroup/save")
    public ResponseEntity<?> saveAgroup(@RequestBody Agroup agroup){
        return ResponseEntity.ok().body(agroupService.saveAgroup(agroup));
    }

}
