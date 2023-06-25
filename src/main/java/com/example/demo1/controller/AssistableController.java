package com.example.demo1.controller;


import com.example.demo1.bussinessLogic.AccountService;
import com.example.demo1.entities.BankAccountVerificationRequest;
import com.example.demo1.entities.DataApi;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
public class AssistableController {

    private final RestTemplate restTemplate;
    private final AccountService accountService;


    public AssistableController( RestTemplate restTemplate, AccountService accountService) {

        this.restTemplate = restTemplate;
        this.accountService = accountService;
    }





    @PostMapping("/bank-account-verification")
    public ResponseEntity<String> verifyBankAccount(@RequestBody BankAccountVerificationRequest request) {
        try {
            HttpEntity<MultiValueMap<String, String>> httpEntity = accountService.getRequestParameters(request);

            ResponseEntity<String> responseEntity = restTemplate.exchange(accountService.getUrl(), HttpMethod.POST, httpEntity, String.class);

            return responseEntity;
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                // Handle 401 Unauthorized error
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(accountService.getErrorMessage());
            } else {
                String responseBody = ex.getResponseBodyAsString();
                return new ResponseEntity<>(responseBody, ex.getStatusCode());
            }
        } catch (Exception exception) {
            return new ResponseEntity<>("An Error occurred : "+ exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/testApi")
    public ResponseEntity<String> testApi(@RequestBody DataApi dataApi){
        String url = "https://jsonplaceholder.typicode.com/posts";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("title", dataApi.getTitle());
        requestBody.add("body", dataApi.getBody());
        requestBody.add("userId", String.valueOf(dataApi.getUserId()));

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

        return responseEntity;
    }
}
