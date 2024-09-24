package com.example.demo.boat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository  extends JpaRepository<Boat, Long> {

    List<Boat> findByMemberEmail(String memberEmail);

}
