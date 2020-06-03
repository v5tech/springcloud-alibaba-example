package com.samples.integration.common.enums;

public enum  RspStatusEnum {
    /**
     * SUCCESS
     */
    SUCCESS(200,"成功"),
    /**
     * Fail rsp status enum.
     */
    FAIL(999,"失败"),
    /**
     * Exception rsp status enum.
     */
    EXCEPTION(500,"系统异常");

    private int code;

    private String message;

    RspStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}