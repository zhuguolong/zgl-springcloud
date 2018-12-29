package com.zgl.zuul.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhugu
 * @version 1.0
 * 2018/12/21 11:30
 */
public class PreRequestFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PreRequestFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        Map<String, Object> paramsMap = paramsMap(request.getParameterMap());
        log.info("请求接口：[{}][{}]，请求参数：{}", method, requestURI, paramsMap.toString());
        return null;
    }

    /**
     * 获取请求参数
     * @param reqMap request.getParameterMap()
     * @return
     */
    private static Map<String, Object> paramsMap(Map reqMap) {
        Map<String, Object> returnMap = new HashMap<>();

        if (reqMap != null) {

            Iterator entries = reqMap.entrySet().iterator();

            Map.Entry entry;
            String name;
            StringBuilder value;

            while (entries.hasNext()) {
                value = new StringBuilder();
                entry = (Map.Entry) entries.next();
                name = (String) entry.getKey();
                Object valueObj = entry.getValue();
                if (null == valueObj) {
                    value = new StringBuilder();
                } else if (valueObj instanceof String[]) {
                    String[] values = (String[]) valueObj;
                    for (String value1 : values) {
                        value.append(value1).append(",");
                    }
                    value = new StringBuilder("[" + value.substring(0, value.length() - 1) + "]");
                } else {
                    value = new StringBuilder(valueObj.toString());
                }
                if (value.length() > 2000) {
                    value = new StringBuilder("大数据已截取");
                }
                returnMap.put(name, value.toString());
            }
        }
        return returnMap;
    }
}
