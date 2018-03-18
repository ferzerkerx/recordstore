package com.ferzerkerx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Configuration
public class AdditionalExceptionHandlersConfig implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(AdditionalExceptionHandlersConfig.class);

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(catchAllUnexpectedExceptions());
    }

    private HandlerExceptionResolver catchAllUnexpectedExceptions() {
        return (httpServletRequest, httpServletResponse, o, e) -> {
            log.error("Unexpected exception", e);
            try {
                httpServletRequest.setAttribute("javax.servlet.error.exception", new RuntimeException()); //This hides the type of exception
                httpServletResponse.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "");
            } catch (IOException ignored) {
                log.error("Handling of {} resulted in Exception {}", e.getClass().getName(), ignored.getMessage());
            }
            return new ModelAndView();
        };
    }
}