package com.ak.controller;



import com.ak.entity.*;
import com.ak.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestHeader("Authorization") String token, @RequestBody MessageRequest messageRequest) {
        String cleanedToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        if (chatService.sendMessage(cleanedToken, messageRequest.getContent())) {
            return ResponseEntity.ok("Message sent successfully");
        } else {
            return ResponseEntity.status(403).body("Unauthorized or invalid token");
        }
    }




    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = chatService.getMessages();
        return ResponseEntity.ok(messages);
    }
}

