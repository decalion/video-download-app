package com.icaballero.videodowloader.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;


/**
 * RestTemplateInterceptor
 * @author Ismael Caballero Hernandez
 *
 */
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor  {

	
	private Logger log = LoggerFactory.getLogger(RestTemplateInterceptor.class);
	

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		log.debug("[TRACING REQUEST] - START");
		
        log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
        
        ClientHttpResponse response = execution.execute(request, body);
        
        InputStreamReader isr = new InputStreamReader(
          response.getBody(), StandardCharsets.UTF_8);
        
        String bodyresponse = new BufferedReader(isr).lines()
            .collect(Collectors.joining("\n"));
        log.debug("Response body: {}", bodyresponse);
      
        
        log.debug("[TRACING REQUEST] - END");
        
       
        
        return response;
	}

}
