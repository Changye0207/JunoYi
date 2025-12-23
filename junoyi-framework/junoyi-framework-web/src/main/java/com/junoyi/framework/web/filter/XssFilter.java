package com.junoyi.framework.web.filter;

import com.junoyi.framework.web.properties.XssProperties;
import com.junoyi.framework.web.xss.XssHttpServletRequestWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * XSS 过滤器
 *
 * @author Fan
 */
public class XssFilter extends OncePerRequestFilter {

    private final XssProperties xssProperties;
    private final PathMatcher pathMatcher = new AntPathMatcher();

    public XssFilter(XssProperties xssProperties) {
        this.xssProperties = xssProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 判断是否需要过滤
        if (shouldSkip(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 使用 XSS 包装器
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);
        filterChain.doFilter(xssRequest, response);
    }

    /**
     * 判断是否跳过 XSS 过滤
     */
    private boolean shouldSkip(HttpServletRequest request) {
        // 未启用
        if (!xssProperties.isEnable()) return true;

        // 排除的请求方法
        String method = request.getMethod();
        if (xssProperties.getExcludeMethods().stream()
                .anyMatch(m -> m.equalsIgnoreCase(method))) {
            return true;
        }

        // GET 请求默认跳过（通常不携带请求体）
        if (HttpMethod.GET.matches(method)) return true;

        // 排除的 URL
        String uri = request.getRequestURI();
        return xssProperties.getExcludeUrls().stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, uri));
    }
}
