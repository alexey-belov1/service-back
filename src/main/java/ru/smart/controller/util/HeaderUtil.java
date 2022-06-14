package ru.smart.controller.util;

import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

    public static HttpHeaders createSuccessAlert(String entity, String param) {
        return createAlert("success", entity, param);
    }

    public static HttpHeaders createWarningAlert(String entity, String param) {
        return createAlert("warning", entity, param);
    }

    private static HttpHeaders createAlert(String status, String entity, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-app-alert", status);
        headers.set("x-app-entity", entity);
        headers.set("X-app-param", param);
        return headers;
    }
}
