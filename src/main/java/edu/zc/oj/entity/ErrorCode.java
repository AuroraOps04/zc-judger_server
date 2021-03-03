package edu.zc.oj.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author coderPlus-tr
 */

public enum ErrorCode {
    /**
     * result entity member error code
     */
    SUCCESS(0),
    INVALID_CONFIG(-1),
    FORK_FAILED(-2),
    PTHREAD_FAILED(-3),
    WAIT_FAILED(-4),
    ROOT_REQUIRED(-5),
    LOAD_SECCOMP_FAILED(-6),
    SETRLIMIT_FAILED(-7),
    DUP2_FAILED(-8),
    SETUID_FAILED(-9),
    EXECVE_FAILED(-10),
    SPJ_ERROR(-11);
    private final Integer value;

    ErrorCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    @JsonValue
    public String toString(){
        return String.valueOf(value);
    }
}
