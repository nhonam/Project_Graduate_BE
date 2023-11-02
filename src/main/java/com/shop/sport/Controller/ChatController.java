package com.shop.sport.Controller;

import com.shop.sport.Response.Response;
//import org.python.util.PythonInterpreter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    Response response = Response.getInstance();

    @GetMapping("/input")
    public ResponseEntity<Object> getAllProductInCart(
    ) {
        try {
            PythonInterpreter interpreter = new PythonInterpreter();

            System.out.println("Java runs python code using jython");
//            interpreter.exec("import random");
//            interpreter.exec("import json");
            interpreter.exec("import sys");
            interpreter.exec("sys.path.append('c:\\users\\nhona\\appdata\\local\\programs\\python\\python37\\lib\\site-packages')");
            interpreter.exec("import torch");

            interpreter.execfile("src/main/java/ChatBotAi/chat.py");
            System.out.println("bot_name: " + interpreter.get("bot_name"));
            System.out.println("result: " + interpreter.get("result"));
            interpreter.close();

            return response.generateResponse("get cart by id user successfully", HttpStatus.OK, "listCart");
        } catch (Error e) {
            return response.generateResponse("get cart by id user failed"+e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }


    }
}
