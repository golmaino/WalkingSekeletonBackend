package com.example.walking_skeleton_backend.service;

import com.example.walking_skeleton_backend.model.MovingRequest;
import com.example.walking_skeleton_backend.repository.MovingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovingRequestService {
    private final MovingRequestRepository repository;

    public MovingRequestService(MovingRequestRepository repository) {
        this.repository = repository;
    }

    public MovingRequest createMoveRequest(MovingRequest moveRequest) {
        return repository.save(moveRequest);
    }

    public List<MovingRequest> getAllMoveRequests() {
        return repository.findAll();
    }

    public Optional<MovingRequest> getMoveRequestById(Long id) {
        return repository.findById(id);
    }

    public void deleteMoveRequest(Long id) {
        repository.deleteById(id);
    }
}
