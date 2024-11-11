package com.ak.service;



import com.ak.entity.*;

import com.ak.repository.MessageRepository;
import com.ak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AuthService authService;

    public boolean sendMessage(String token, String content) {
        User sender = authService.getUserByToken(token);
        if (sender == null) {
            return false;
        }

        // Create and save message
        Message message = new Message(content, sender, LocalDateTime.now());
        messageRepository.save(message);
        return true;
    }

    public List<Message> getMessages() {
        // Retrieve messages in reverse chronological order
        return messageRepository.findAllByOrderByTimestampDesc();
    }
}

