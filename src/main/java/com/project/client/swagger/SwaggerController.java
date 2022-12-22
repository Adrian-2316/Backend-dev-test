package com.project.client.swagger;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SwaggerController {

  @GetMapping(value = "/")
  @Operation(summary = "Redirect to Swagger URL.")
  public void redirect(final HttpServletResponse response) throws IOException {
    response.sendRedirect("/swagger-ui/index.html");
  }
}
