package edu.zc.oj.entity;

/**
 * @author coderPlus-tr
 */
public enum ResultCode {
    /**
     * result entity member result code
     */
    SUCCESS(0),
    CPU_TIME_LIMIT_EXCEEDED(1),
    REAL_TIME_LIMIT_EXCEEDED(2),
    MEMORY_LIMIT_EXCEEDED(3),
    RUNTIME_ERROR(4),
    SYSTEM_ERROR(5);
    private final Integer value;

    ResultCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
