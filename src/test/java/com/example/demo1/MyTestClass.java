package com.example.demo1;

import com.example.demo1.bussinessLogic.AccountService;
import com.example.demo1.controller.AssistableController;
import com.example.demo1.entities.DataApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MyTestClass {

    @Test
    void testApi() {
        // Mock the RestTemplate
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        AccountService accountService = new AccountService();

        // Create an instance of your controller or class that contains the testApi method
        AssistableController yourController = new AssistableController(restTemplate,accountService);

        // Create a sample DataApi object for testing
        DataApi dataApi = new DataApi();
        dataApi.setTitle("Test Title");
        dataApi.setBody("Test Body");
        dataApi.setUserId(1);

        // Create the expected response from the external API
        String expectedResponse = "Success";

        // Mock the exchange method of the RestTemplate
        when(restTemplate.exchange(
                any(String.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                any(Class.class)
        )).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        // Call the testApi method
        ResponseEntity<String> responseEntity = yourController.testApi(dataApi);

        // Assert that the response matches the expected response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
}
