package com.example.springboot.exception;

import com.example.springboot.constant.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class AlreadyExistsException extends AbstractThrowableProblem {
    private static final long serialVersionUID = 4775907845387588528L;

    public AlreadyExistsException(String message) {
        super(ErrorConstants.DEFAULT_TYPE, message, Status.CONFLICT);
    }
}
