package com.orange.blood.heroes.srv.combat.heroes.exception;


import com.orange.blood.heroes.srv.combat.heroes.domain.exception.HeroUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleMethodArgumentNotValid() {
        // Cria um mock de MethodArgumentNotValidException
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);

        // Configura o mock de BindingResult
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(List.of(new FieldError("objectName", "field", "defaultMessage")));

        // Cria um mock de HttpHeaders e HttpStatus
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Chama o método e verifica o resultado
        ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(exception, headers, status, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O combate é inválido e não foi computado", ((Map<String, String>) response.getBody()).get("message"));
    }

    @Test
    void handleMethodArgumentTypeMismatch() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentTypeMismatch(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O combate é inválido e não foi computado", ((Map<String, String>) response.getBody()).get("message"));
    }

    @Test
    void handleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
        ResponseEntity<String> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody());
    }

    @Test
    void heroUnavailableException() {
        HeroUnavailableException exception = new HeroUnavailableException("O herói não está em condições de combater no momento");
        ResponseEntity<String> response = globalExceptionHandler.heroUnavailableException(exception);

        assertEquals(HttpStatus.GONE, response.getStatusCode());
        assertEquals("O herói não está em condições de combater no momento", response.getBody());
    }
}
