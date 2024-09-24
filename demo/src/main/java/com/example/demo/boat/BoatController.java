package com.example.demo.boat;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.boat.requests.CreateBoatRequest;
import com.example.demo.config.JwtService;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/boat")
@RequiredArgsConstructor
public class BoatController {

    private final JwtService jwtService;
    private final BoatService boatService;
    private final MemberRepository memberRepository;

    @GetMapping
    public ResponseEntity<List<Boat>> getMemberBoats(Object any, HttpServletRequest request) {
        String email = jwtService.emailFromRequest(request);
        return ResponseEntity.ok(boatService.getBoats(email));
    }

    @PostMapping
    public ResponseEntity<Boat> createBoat(@RequestBody CreateBoatRequest body, HttpServletRequest request) {
        String email = jwtService.emailFromRequest(request);
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isEmpty()) {
            throw new IllegalStateException("Member not found");
        }
        Boat boat = Boat.builder()
            .name(body.getName())
            .type(body.getType())
            .member(member.get())
            .build();
        return ResponseEntity.ok(boatService.createBoat(boat));
    }
}