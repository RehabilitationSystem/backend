package com.example.logger_security.utils;

import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;

public class MdcUtil {
    //追踪id
    public static final String TRACE_ID = "traceId";
    //区分线程
    public static final String SPAN_ID = "spanId";

    public static String generateUuId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    public static void setContextMap(Map<String, String> context) {
        MDC.setContextMap(context);
    }

    public static void removeTraceId() {
        MDC.remove(TRACE_ID);
        MDC.remove(SPAN_ID);
    }

    public static void clear() {
        MDC.clear();
    }

    // 每个线程都会产生一个属于自己的ID
    public static void initSpanId() {
        MDC.put(SPAN_ID, generateUuId());
    }
}
