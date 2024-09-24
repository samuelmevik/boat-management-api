package com.example.demo.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
    @Query("SELECT u FROM Member u WHERE u.email = ?1")
    Optional<Member> findByEmail(String email);
    
}
