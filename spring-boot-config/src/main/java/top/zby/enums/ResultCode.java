package top.zby.enums;

public enum ResultCode {
    SUCCESS(200),
    FAIL(500),
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_ACCEPTABLE(406),
    REQUEST_TIMEOUT(408),
    CONFLICT(409),
    UNSUPPORTED_MEDIA_TYPE(415),
    TOO_MANY_REQUESTS(429);

    private int code;
    ResultCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
