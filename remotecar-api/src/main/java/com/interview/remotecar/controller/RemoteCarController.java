package com.interview.remotecar.controller;

import com.interview.remotecar.model.Node;
import com.interview.remotecar.service.RemoteCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by naveen on 16/12/17.
 */

@RestController
@RequestMapping(value = "/remotecar")
public class RemoteCarController {

    //5,5:RFLFRFLF
    private static final Pattern INPUT_VALIDATION_PATTERN = Pattern
            .compile("(\\\\d{1}), (\\\\d{1}):(A-Z)");

    @Value("${input}")
    private String input;

    @Autowired
    private RemoteCarService remoteCarService;


    /**
     * Validates the input data, and finds the position for the input direction
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/move", method = RequestMethod.GET)
    public ResponseEntity<String> move() {
        if (StringUtils.isEmpty(input)) {
            throw new IllegalArgumentException("Please enter valid input values");
        }


        final Node response = remoteCarService.carPosition(input);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }


    /**
     * Simple ping method. Call this to check that the service is up and running. No
     * input is required.
     *
     * @param req      http the request.
     * @param response the http response.
     * @return Response body of "true".
     */
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public boolean test(HttpServletRequest req, HttpServletResponse response) {
        return true;
    }
}
