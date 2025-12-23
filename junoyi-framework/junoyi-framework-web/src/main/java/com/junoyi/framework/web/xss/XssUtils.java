package com.junoyi.framework.web.xss;

import com.junoyi.framework.core.utils.StringUtils;

/**
 * XSS 过滤工具类
 *
 * @author Fan
 */
public class XssUtils {

    private XssUtils() {}

    /**
     * 过滤 XSS 脚本内容
     *
     * @param value 待处理内容
     * @return 过滤后的内容
     */
    public static String clean(String value) {
        if (StringUtils.isBlank(value)) return value;

        // 转义 HTML 特殊字符
        value = value.replace("&", "&amp;");
        value = value.replace("<", "&lt;");
        value = value.replace(">", "&gt;");
        value = value.replace("\"", "&quot;");
        value = value.replace("'", "&#39;");

        // 过滤 javascript: 协议
        value = value.replaceAll("(?i)javascript:", "");
        // 过滤 vbscript: 协议
        value = value.replaceAll("(?i)vbscript:", "");
        // 过滤 expression
        value = value.replaceAll("(?i)expression\\s*\\(", "");
        // 过滤 onXxx 事件
        value = value.replaceAll("(?i)on\\w+\\s*=", "");

        return value;
    }

    /**
     * 判断是否包含 XSS 攻击内容
     *
     * @param value 待检测内容
     * @return true 包含 XSS 内容
     */
    public static boolean containsXss(String value) {
        if (StringUtils.isBlank(value)) return false;

        // 检测 script 标签
        if (value.matches("(?i).*<script.*>.*</script>.*")) return true;
        // 检测 javascript: 协议
        if (value.matches("(?i).*javascript:.*")) return true;
        // 检测 vbscript: 协议
        if (value.matches("(?i).*vbscript:.*")) return true;
        // 检测 onXxx 事件
        if (value.matches("(?i).*on\\w+\\s*=.*")) return true;
        // 检测 expression
        if (value.matches("(?i).*expression\\s*\\(.*")) return true;
        // 检测常见标签
        if (value.matches("(?i).*<(iframe|frame|object|embed|img|svg|link|style|meta).*>.*")) return true;

        return false;
    }
}
