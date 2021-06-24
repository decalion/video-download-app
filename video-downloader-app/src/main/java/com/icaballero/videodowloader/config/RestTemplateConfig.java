package com.icaballero.videodowloader.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class
 * 
 * @author Ismael Caballero Hernandez
 *
 */
@Configuration
public class RestTemplateConfig {

	
	/**
	 * RestTemplate Configuration
	 * @return
	 */
	@Bean
	public RestTemplate getRestTemplate() {

		
		// --- INTERCEPTOR
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
		RestTemplate restTemplate = new RestTemplate(factory);

		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateInterceptor());
		restTemplate.setInterceptors(interceptors);
		
		
		// -- MESSAGE CONVERTERS
//		 HttpMessageConverter<?> stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		
//		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
//		messageConverters.add(converter);  
//		messageConverters.add(stringHttpMessageConverter);
//		restTemplate.setMessageConverters(messageConverters);
//		
		return restTemplate;
	}

}
