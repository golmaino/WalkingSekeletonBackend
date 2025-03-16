package com.example.walking_skeleton_backend;

import com.example.walking_skeleton_backend.controller.MovingRequestController;
import com.example.walking_skeleton_backend.model.MovingRequest;
import com.example.walking_skeleton_backend.service.MovingRequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovingRequestControllerTest {

    @Mock
    private MovingRequestService service;

    @InjectMocks
    private MovingRequestController controller;

    @Test
    void shouldCreateMoveRequest() {
        MovingRequest request = new MovingRequest(null, "John", "Doe", "Main Street 10", "Green Avenue 5", LocalDate.of(2025, 4, 1));
        MovingRequest savedRequest = new MovingRequest(1L, "John", "Doe", "Main Street 10", "Green Avenue 5", LocalDate.of(2025, 4, 1));

        when(service.createMoveRequest(request)).thenReturn(savedRequest);

        ResponseEntity<MovingRequest> response = controller.createMoveRequest(request);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(service, times(1)).createMoveRequest(request);
    }

    @Test
    void shouldReturnAllMoveRequests() {
        List<MovingRequest> requests = Arrays.asList(
                new MovingRequest(1L, "Alice", "Smith", "Street A", "Street B", LocalDate.of(2025, 5, 1)),
                new MovingRequest(2L, "Bob", "Johnson", "Street X", "Street Y", LocalDate.of(2025, 6, 1))
        );

        when(service.getAllMoveRequests()).thenReturn(requests);

        ResponseEntity<List<MovingRequest>> response = controller.getAllMoveRequests();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(service, times(1)).getAllMoveRequests();
    }

    @Test
    void shouldReturnMoveRequestById() {
        MovingRequest request = new MovingRequest(1L, "John", "Doe", "Main Street 10", "Green Avenue 5", LocalDate.of(2025, 4, 1));
        when(service.getMoveRequestById(1L)).thenReturn(Optional.of(request));

        ResponseEntity<MovingRequest> response = controller.getMoveRequestById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(service, times(1)).getMoveRequestById(1L);
    }

    @Test
    void shouldReturnNotFoundForNonExistingRequest() {
        when(service.getMoveRequestById(99L)).thenReturn(Optional.empty());

        ResponseEntity<MovingRequest> response = controller.getMoveRequestById(99L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void shouldDeleteMoveRequest() {
        doNothing().when(service).deleteMoveRequest(1L);

        ResponseEntity<Void> response = controller.deleteMoveRequest(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(service, times(1)).deleteMoveRequest(1L);
    }
}
