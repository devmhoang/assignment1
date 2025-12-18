package demo.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api")
public class RestTest {
     @GetMapping("/ping")
     public String getPong() {
         return "pong";
     }
     
}
