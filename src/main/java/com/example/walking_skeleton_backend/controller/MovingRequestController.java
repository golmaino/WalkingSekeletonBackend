package com.example.walking_skeleton_backend.controller;

import com.example.walking_skeleton_backend.model.MovingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/moving-requests")
public class MovingRequestController {
    private final List<MovingRequest> moveRequests = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> createMoveRequest(@Valid @RequestBody MovingRequest movingRequest) {
        moveRequests.add(movingRequest);
        return ResponseEntity.status(201).body("Move request successfully created");
    }

    @GetMapping
    public ResponseEntity<List<MovingRequest>> getMoveRequests() {
        return ResponseEntity.ok(moveRequests);
    }
}
