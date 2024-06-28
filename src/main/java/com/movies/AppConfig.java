package com.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class AppConfig {

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class, args);
	}

	@Bean
	public RestTemplate restTemplate(ObjectMapper objectMapper) {
		RestTemplate restTemplate = new RestTemplate();

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();


		converter.setSupportedMediaTypes(
				Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));

		restTemplate.setMessageConverters(Arrays.asList(converter, new FormHttpMessageConverter()));
		return restTemplate;
	}
}
