package demo.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class RestOrder {

     // HANDLE NEW ORDER SUBMISSION
     @PostMapping("path")
     public String postMethodName(@RequestBody String entity) {
         //TODO: process POST request


         //TODO: DEDUCT STOCK
         
         return entity;
     }

     // VIEW ORDERS FOR AUTHENTICATED USER
     @PostMapping("path")
     public String postMethodName(@RequestBody String entity) {
         //TODO: process POST request
         
         return entity;
     }
     
     
}
