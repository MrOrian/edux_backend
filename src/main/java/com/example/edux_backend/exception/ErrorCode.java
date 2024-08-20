package com.example.edux_backend.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Loi khong mong muon"),
    INVALID_KEY(1000, "Loi!"),
    USER_EXITED(1001, "User da ton tai!"),
    USER_NOT_EXITED(1002,"User khong ton tai!"),
    USER_INVALID(1003, "Mat khau phai co it nhat 8 ki tu!"),
    SUBJECT_EXITED(1004, "Mon hoc da ton tai!")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
