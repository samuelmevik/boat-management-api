package com.example.demo.boat;

import java.util.List;

import org.springframework.stereotype.Service;

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
}