package com.example.walking_skeleton_backend.repository;

import com.example.walking_skeleton_backend.model.MovingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovingRequestRepository extends JpaRepository<MovingRequest, Long> {
}
