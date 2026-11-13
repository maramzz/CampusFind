package com.campusfind.claimservice.controller;

import com.campusfind.claimservice.models.Claim;
import com.campusfind.claimservice.models.ClaimStatus;
import com.campusfind.claimservice.service.ClaimService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Create a claim
    @PostMapping
    public ResponseEntity<Claim> createClaim(@Valid @RequestBody Claim claim) {
        return ResponseEntity.status(201).body(claimService.createClaim(claim));
    }

    // Get all claims
    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    // Get claim by ID
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    // Get claims by item ID
    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<Claim>> getClaimsByItemId(
            @PathVariable Long itemId) {
        return ResponseEntity.ok(claimService.getClaimsByItemId(itemId));
    }

    // Get claims by student email
    @GetMapping("/student")
    public ResponseEntity<List<Claim>> getClaimsByStudentEmail(
            @RequestParam String email) {
        return ResponseEntity.ok(claimService.getClaimsByStudentEmail(email));
    }

    // Update claim status
    @PutMapping("/{id}/status")
    public ResponseEntity<Claim> updateClaimStatus(
            @PathVariable Long id,
            @RequestParam ClaimStatus status) {
        return ResponseEntity.ok(
                claimService.updateClaimStatus(id, status));
    }

    // Delete a claim
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable Long id) {
        claimService.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }
}