package com.swisscom.cloud.sb.broker.util.httpserver

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@CompileStatic
class Controller {
    @RequestMapping("/")
    String index() {
        return "Hello"
    }
}
