package ua.transkyy.couch.currrency.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/example")
    public String exampleGet() {
        return "OK!";
    }
}
