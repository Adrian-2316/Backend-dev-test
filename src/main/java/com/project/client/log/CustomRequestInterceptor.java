package com.project.client.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Component
@Slf4j
public class CustomRequestInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(CustomRequestInterceptor.class);

  /**
   * This method is called before the request is processed by the controller.
   *
   * @param request HttpServletRequest.
   * @param response HttpServletResponse.
   * @param handler Object.
   * @return boolean.
   */
  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    long startTime = Instant.now().toEpochMilli();
    logger.info(
        "Request URL::%s:: Start Time=%s"
            .formatted(request.getRequestURL().toString(), Instant.now()));
    request.setAttribute("startTime", startTime);
    return true;
  }

  /**
   * This method is called after the request is completed and the response is sent to the client.
   *
   * @param request HttpServletRequest.
   * @param response HttpServletResponse.
   * @param handler Object .
   * @param ex Exception.
   */
  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    long startTime = (Long) request.getAttribute("startTime");
    logger.info(
        "Request URL::%s:: Time Taken=%d"
            .formatted(
                request.getRequestURL().toString(), Instant.now().toEpochMilli() - startTime));
  }
}
