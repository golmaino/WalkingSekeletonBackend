package com.example.walking_skeleton_backend;

import com.example.walking_skeleton_backend.controller.MovingRequestController;
import com.example.walking_skeleton_backend.model.MovingRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class MovingRequestControllerTest {

    private MovingRequestController controller;

    @BeforeEach
    void setUp() {
        controller = new MovingRequestController();
    }

    @Test
    void shouldCreateMoveRequest() {

        MovingRequest request = new MovingRequest(null, "John", "Doe", "Main Street 10", "Green Avenue 5", LocalDate.of(2025, 4, 1));


        ResponseEntity<String> response = controller.createMoveRequest(request);


        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Move request successfully created", response.getBody());
    }

    @Test
    void shouldReturnAllMoveRequests() {

        MovingRequest request1 = new MovingRequest(null, "Alice", "Smith", "Street A", "Street B", LocalDate.of(2025, 5, 1));
        MovingRequest request2 = new MovingRequest(null, "Bob", "Johnson", "Street X", "Street Y", LocalDate.of(2025, 6, 1));
        controller.createMoveRequest(request1);
        controller.createMoveRequest(request2);

        ResponseEntity<List<MovingRequest>> response = controller.getMoveRequests();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
}
