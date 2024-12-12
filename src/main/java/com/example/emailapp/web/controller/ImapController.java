package com.example.emailapp.web.controller;

import com.example.emailapp.domain.HttpResponse;
import com.example.emailapp.service.ImapService;
import com.example.emailapp.web.mapper.EmailMapper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/read")
@RequiredArgsConstructor
public class ImapController {
    private final EmailMapper emailMapper;
    private final ImapService imapService;

    @GetMapping("/{account}/{folderName}")
    public ResponseEntity<HttpResponse> readEmails(@PathVariable String account,
                                                   @PathVariable String folderName)
            throws Exception {
        var messages = imapService.getEmails(account, folderName);
        System.out.println(messages.length + "I am here");
        return ResponseEntity.ok(HttpResponse.builder()
                .httpStatus(HttpStatus.OK)
                .code(200)
                .timeStamp(LocalDateTime.now().toString())
                .path(String.format("/api/v1/read/%s/%s", account, folderName))
                .data(Map.of("List of emails", Arrays.stream(messages).map((temp) -> {
                    try {
                        return emailMapper.toReceivedEmail(temp);
                    } catch (MessagingException e) {
                        throw new RuntimeException(e);
                    }
                }).toList()))
                .build());
    }
}
