package com.example.demo.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/demo")
public class DemoController {

    private final MemberRepository memberRepository;

    @RequestMapping
    public ResponseEntity<List<Member>> getDemo() {
        return ResponseEntity.ok(memberRepository.findAll());
    }
}
