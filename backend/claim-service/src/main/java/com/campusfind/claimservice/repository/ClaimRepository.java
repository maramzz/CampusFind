package com.campusfind.claimservice.repository;

import com.campusfind.claimservice.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByItemId(Long itemId);

    List<Claim> findByStudentEmail(String studentEmail);
}