package com.example.walking_skeleton_backend;

import com.example.walking_skeleton_backend.model.MovingRequest;
import com.example.walking_skeleton_backend.repository.MovingRequestRepository;
import com.example.walking_skeleton_backend.service.MovingRequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovingRequestServiceTest {

    @Mock
    private MovingRequestRepository repository;

    @InjectMocks
    private MovingRequestService service;

    @Test
    void shouldCreateMoveRequest() {
        MovingRequest request = new MovingRequest(null, "John", "Doe", "Main Street 10", "Green Avenue 5", LocalDate.of(2025, 4, 1));
        MovingRequest savedRequest = new MovingRequest(1L, "John", "Doe", "Main Street 10", "Green Avenue 5", LocalDate.of(2025, 4, 1));

        when(repository.save(request)).thenReturn(savedRequest);

        MovingRequest result = service.createMoveRequest(request);

        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(request);
    }

    @Test
    void shouldReturnAllMoveRequests() {
        List<MovingRequest> requests = Arrays.asList(
                new MovingRequest(1L, "Alice", "Smith", "Street A", "Street B", LocalDate.of(2025, 5, 1)),
                new MovingRequest(2L, "Bob", "Johnson", "Street X", "Street Y", LocalDate.of(2025, 6, 1))
        );

        when(repository.findAll()).thenReturn(requests);

        List<MovingRequest> result = service.getAllMoveRequests();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void shouldReturnMoveRequestById() {
        MovingRequest request = new MovingRequest(1L, "John", "Doe", "Main Street 10", "Green Avenue 5", LocalDate.of(2025, 4, 1));

        when(repository.findById(1L)).thenReturn(Optional.of(request));

        Optional<MovingRequest> result = service.getMoveRequestById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldDeleteMoveRequest() {
        doNothing().when(repository).deleteById(1L);

        service.deleteMoveRequest(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
