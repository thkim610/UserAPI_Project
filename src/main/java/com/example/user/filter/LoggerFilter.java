package com.example.user.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //요청, 응답에 대한 내용을 읽어버리면 다시 읽을 수 없기 때문에 캐싱이 가능한 래퍼클래스로 형변환
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        //요청 정보 로깅
        // 요청으로 들어온 body의 내용을 출력.
        String requestBody = new String(req.getContentAsByteArray());
        String requestURI = req.getRequestURI();
        String reqMethod = req.getMethod();

        log.info(">>>>> uri : {} , method : {} ,  body : {}", reqMethod, requestURI, requestBody);

        //응답 정보 로깅
        //응답으로 내보내는 body의 내용을 출력.
        String responseBody = new String(res.getContentAsByteArray());

        log.info("<<<<< uri : {} , method : {} , body : {}", reqMethod, requestURI, responseBody);

        res.copyBodyToResponse();// 한번 읽었더라도 복사해둔 내용을 다시 한번 응답에 덮어씌워줌.


    }
}
