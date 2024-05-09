package com.nicovegasr.auth_service.auth.application.exceptions;

import com.nicovegasr.auth_service.auth.domain.exceptions.UserException;

public class AlgorithmError extends UserException {
    public AlgorithmError(String error) {
        super("Error with cipher algorithm password." + error);
    }
}
