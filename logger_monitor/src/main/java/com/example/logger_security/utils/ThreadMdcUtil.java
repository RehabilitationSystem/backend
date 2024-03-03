package com.example.logger_security.utils;


import java.util.Map;
import java.util.concurrent.Callable;

public class ThreadMdcUtil {

    public static void setTraceId(String traceId) {
        MdcUtil.setTraceId(traceId);
        MdcUtil.initSpanId();
    }

    public static void resetTraceId() {
        MdcUtil.setTraceId(MdcUtil.generateUuId());
        MdcUtil.initSpanId();
    }

    public static void setTraceIdIfAbsent() {
        if (MdcUtil.getTraceId() == null) {
            MdcUtil.setTraceId(MdcUtil.generateUuId());
        }
        MdcUtil.initSpanId();
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MdcUtil.clear();
            } else {
                MdcUtil.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MdcUtil.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MdcUtil.clear();
            } else {
                MdcUtil.setContextMap(context);
            }
            //设置traceId
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MdcUtil.clear();
            }
        };
    }
}
