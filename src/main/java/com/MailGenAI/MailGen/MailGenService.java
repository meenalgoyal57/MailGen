package com.MailGenAI.MailGen;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MailGenService {

    private final WebClient webClient;
    private final String apiKey;

    public MailGenService(WebClient.Builder webClientBuilder,
                         @Value("${gemini.api.url}") String baseUrl,
                          @Value("${gemini.api.key}") String geminiApiKey) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.apiKey = geminiApiKey;
    }

    public static String generateEmailReply(MailRequest mailRequest) {
        //Build prompt
        String prompt = buildPrompt(mailRequest);


        //Prepare raw json body
        String requestBody = String.format("""
                {
                    "contents": [
                      {
                        "parts": [
                          {
                            "text": "%s"
                          }
                        ]
                      }
                    ]
                  }
                """,prompt);


        // Send Request

        //Response extract
        return "Good";
    }

    private static String buildPrompt(MailRequest mailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email.");
        if (mailRequest.getTone() != null && !mailRequest.getTone().isEmpty()) {
            prompt.append("Use a").append(mailRequest.getTone()).append("tone.");
        }
        prompt.append("Orginal Email: \n").append(mailRequest.getEmailContent());
        return prompt.toString();
    }
}

