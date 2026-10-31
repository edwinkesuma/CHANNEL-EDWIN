package com.edwinkesuma.sbi_assignment.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        String error,
        LocalDateTime timestamp
) {
}