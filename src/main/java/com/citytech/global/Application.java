package com.citytech.global;

import com.citytech.global.common.sendmail.EmailService;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}