package com.campusfind.claimservice.service;

import com.campusfind.claimservice.models.Claim;
import com.campusfind.claimservice.models.ClaimStatus;
import com.campusfind.claimservice.repository.ClaimRepository;
import com.campusfind.claimservice.exceptions.ClaimNotFoundException;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    // Create a claim
    public Claim createClaim(Claim claim) {
        claim.setStatus(ClaimStatus.PENDING);
        return claimRepository.save(claim);
    }

    // Get all claims
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    // Get a claim by ID
    public Claim getClaimById(Long id) {
        return claimRepository.findById(id)
        		.orElseThrow(() -> new ClaimNotFoundException(id));
    }

    // Get claims by item ID
    public List<Claim> getClaimsByItemId(Long itemId) {
        return claimRepository.findByItemId(itemId);
    }

    // Get claims by student email
    public List<Claim> getClaimsByStudentEmail(String email) {
        return claimRepository.findByStudentEmail(email);
    }

    // Update claim status
    public Claim updateClaimStatus(Long id, ClaimStatus status) {
        Claim claim = getClaimById(id);
        claim.setStatus(status);
        return claimRepository.save(claim);
    }

    // Delete a claim
    public void deleteClaim(Long id) {
        Claim claim = getClaimById(id);
        claimRepository.delete(claim);
    }
}