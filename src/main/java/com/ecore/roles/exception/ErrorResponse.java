package com.ecore.roles.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class ErrorResponse {

    private final int status;
    private final String error;

}
