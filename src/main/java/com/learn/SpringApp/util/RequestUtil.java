package com.learn.SpringApp.util;

import com.learn.SpringApp.model.AppRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

public class RequestUtil {

    private static Logger log = LoggerFactory.getLogger(RequestUtil.class);

    public static void log(HttpServletRequest servletRequest, String requestObj, Long StartTime, Long EndTime,
                           ResponseEntity Response, String Error) {

        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");

        AppRequest request = new AppRequest();
        request.setRequestURL(servletRequest.getRequestURI());
        request.setRequestMethod(servletRequest.getMethod());
        request.setRequestBody(requestObj);
        request.setStartTime(df.format(StartTime));
        request.setEndTime(df.format(EndTime));
        request.setTimeTaken(EndTime - StartTime);
        request.setResponse(/*Response.getBody().toString()*/null);
        request.setResponseStatus(Response.getStatusCode().toString());
        request.setError(Error);
        log.info(request.toString());
    }
}