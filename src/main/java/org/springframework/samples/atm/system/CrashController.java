package org.springframework.samples.atm.system;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
class CrashController {

    @GetMapping("/oups")
    public String triggerException(String msg) {
        throw new RuntimeException(msg);
    }

}
