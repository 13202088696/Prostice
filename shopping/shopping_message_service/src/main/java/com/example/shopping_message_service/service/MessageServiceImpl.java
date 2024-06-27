package com.example.shopping_message_service.service;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponseBody;
import com.google.gson.Gson;
import com.itbaizhan.shopping_common.result.BaseResult;
import com.itbaizhan.shopping_common.service.MessageService;
import darabonba.core.client.ClientOverrideConfiguration;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@DubboService
@Service
public class MessageServiceImpl implements MessageService {
    @Value("${message.accessKeyId}")
    private String accessKeyId;
    @Value("${message.accessKeySecret}")
    private String accessKeySecret;

    @Override
    public BaseResult sendMessage(String phoneNumber, String code) throws ExecutionException, InterruptedException {

        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(accessKeyId)
                .accessKeySecret(accessKeySecret)
                .build()
        );

        AsyncClient client = AsyncClient.builder()
                .region("cn-beijing-finance-1")
                .credentialsProvider(provider)
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                )
                .build();

        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .signName("阿里云短信测试")
                .templateCode("SMS_154950909")
                .phoneNumbers("13202088696")
                .templateParam( "{\"code\":" +"'"+ code +"'"+ "}")
                .build();

        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        SendSmsResponse resp = response.get();

        SendSmsResponseBody respBody = resp.getBody();

        client.close();
        if("OK".equals(respBody.getCode())){
            return new BaseResult(200,respBody.getCode(),respBody.getMessage());
        }else {
            return new BaseResult(500,respBody.getCode(),respBody.getMessage());
        }

    }
}
