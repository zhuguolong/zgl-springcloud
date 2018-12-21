package com.zgl.common.utils;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhugu
 * 2018/12/21 22:40
 */
public class ResultWrap {
    public static Map<String, Object> ok(String message) {
        return ImmutableMap.of(
                Constss.RESP_CODE, ResultCode.SUCCESS,
                Constss.RESP_MESSAGE, message
        );
    }

    /**
     * 正确返回给定的消息和数据结果。
     *
     * @param message 返回的消息 。
     * @param result 返回的结果。
     * @return 返回一个Map表示正确返回给定的消息和数据结果。
     */
    public static Map<String, Object> ok(@NotNull String message, @NotNull Object result) {
        return ImmutableMap.of(
                Constss.RESP_CODE, ResultCode.SUCCESS,
                Constss.RESP_MESSAGE, message,
                Constss.RESULT, result
        );
    }

    public static Map<String, Object> err(Logger logger, String respCode, String respMesg, String result) {
        Map<String, Object> map = new HashMap<>();

        map.put(Constss.RESULT, result);
        map.put(Constss.RESP_CODE, respCode);
        map.put(Constss.RESP_MESSAGE, respMesg);

        logger.error("{}", map);
        return map;
    }

    public static Map<String, Object> err(Logger logger, String respCode, String respMesg) {
        return err(logger, respCode, respMesg, null);
    }
}
