package com.agenda.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ImagesService {
	private final WebClient webClient;

	public ImagesService(WebClient.Builder webClientBuilder, @Value("${imgur.api-url}") String apiUrl) {
		this.webClient = webClientBuilder.baseUrl(apiUrl).build();
	}

	public String uploadImage(byte[] imageData) {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("image", new ByteArrayResource(imageData) {
			@Override
			public String getFilename() {
				return "image.jpg";
			}
		});

		// Faz a requisição ao Imgur
		return webClient.post().uri("/3/upload")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
				.body(BodyInserters
				.fromMultipartData(parts))
				.retrieve()
				.bodyToMono(String.class)
				.map(this::extractImgurLink)
				.block();
	}
	
	private String extractImgurLink(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            return jsonNode.path("data").path("link").asText();
        } catch (IOException e) {
            return "Error extracting Imgur link: " + e.getMessage();
        }
    }

}
