package com.contact.agenda.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class CustomExceptionResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime errorDate;

    private HttpStatus status;

    private String errorTitle;

    private String errorMsg;

    public CustomExceptionResponse(LocalDateTime errorDate, HttpStatus status, String errorTitle, String errorMsg) {
        this.errorDate = errorDate;
        this.status = status;
        this.errorTitle = errorTitle;
        this.errorMsg = errorMsg;
    }

    public LocalDateTime getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(LocalDateTime errorDate) {
        this.errorDate = errorDate;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}