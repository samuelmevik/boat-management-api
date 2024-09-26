package com.example.demo.boat;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.member.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoatService {
    private final BoatRepository boatRepository;


    public List<Boat> getBoats(String memberEmail) {
        return boatRepository.findByMemberEmail(memberEmail);
    }

    public Boat createBoat(Boat boat) {
        return boatRepository.save(boat);
    }

    public void deleteBoat(Long boatId, Member member) {
        Boat boat = boatRepository.findById(boatId)
            .orElseThrow(() -> new IllegalStateException("Boat not found"));
        if (!boat.getMember().equals(member)) {
            throw new IllegalStateException("Boat does not belong to member");
        }
        boatRepository.delete(boat);
    }
}