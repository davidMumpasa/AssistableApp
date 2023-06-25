package com.example.demo1.bussinessLogic;


import com.example.demo1.entities.BankAccountVerificationRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@Setter
@Getter
public class AccountService {
    public String errorMessage;
    public String url;


    public HttpEntity getRequestParameters(BankAccountVerificationRequest request){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("memberkey", request.getMemberKey());
        requestBody.add("password", request.getPassword());
        requestBody.add("accountNumber", request.getAccountNumber());
        requestBody.add("idNumber", String.valueOf(request.getIdNumber()));
        requestBody.add("accountType", request.getAccountType());
        requestBody.add("branchCode", request.getBranchCode());
        requestBody.add("yourReference", request.getYourReference());

        errorMessage = "{\n\n\"Status\":\"Failure\",\n\"Error\":\"Insufficient funds, Please fund account or contact support @ support@pbverify.co.za.\",\n\n}";
        url = "https://veriid.com/PBVerify/webservice/pbverify-bank-account-verification-v3";

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);

        return httpEntity;
    }


}
