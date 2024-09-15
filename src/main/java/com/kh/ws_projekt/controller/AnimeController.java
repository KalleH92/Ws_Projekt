package com.kh.ws_projekt.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    private final WebClient animeWebClientConfig;

    public AnimeController(WebClient.Builder webClient) {
        this.animeWebClientConfig = webClient
                .baseUrl("https://api.jikan.moe/v4/")
                .build();
    }

    @GetMapping("/{id}")
        public Mono<String> fetchAnimeApi(@PathVariable("id") String id) {

        return animeWebClientConfig.get()
                .uri(anime -> anime
                        .path("anime/" + id)
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class);

    }
}
