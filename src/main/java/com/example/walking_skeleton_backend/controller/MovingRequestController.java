package com.example.walking_skeleton_backend.controller;

import com.example.walking_skeleton_backend.model.MovingRequest;
import com.example.walking_skeleton_backend.service.MovingRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/moving-requests")
public class MovingRequestController {

    @Autowired
    private MovingRequestService service;

    @PostMapping
    public ResponseEntity<MovingRequest> createMoveRequest(@RequestBody MovingRequest request) {
        MovingRequest savedRequest = service.createMoveRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
    }

    @GetMapping
    public ResponseEntity<List<MovingRequest>> getAllMoveRequests() {
        return ResponseEntity.ok(service.getAllMoveRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovingRequest> getMoveRequestById(@PathVariable Long id) {
        return service.getMoveRequestById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoveRequest(@PathVariable Long id) {
        service.deleteMoveRequest(id);
        return ResponseEntity.noContent().build();
    }
}
