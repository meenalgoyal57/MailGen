package com.MailGenAI.MailGen;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/MailGen")
@AllArgsConstructor
public class MailGenController {

    private MailGenService mailGenService;

//    public MailGenController(MailGenService mailGenService) {
//        this.mailGenService = mailGenService;
//    }

    @PostMapping("/generate")
    public ResponseEntity<String> GenerateEmail(@RequestBody MailRequest mailRequest){
        String response = MailGenService.generateEmailReply(mailRequest);
        return ResponseEntity.ok(response);
    }
}
