package com.shop.sport.Utils;

import org.python.util.PythonInterpreter;
public class ChatBot {
    public static String Chat(String input) {
        PythonInterpreter interpreter = new PythonInterpreter();
        System.out.println("Java runs python code using jython");

        interpreter.execfile("src/main/java/ChatBotAI/chat.py");
        System.out.println("Java runs python code using jython");

        System.out.println("x: " + interpreter.get("bot_name"));
//        System.out.println("x: " + interpreter.get("y"));
        return "a";
    }
}