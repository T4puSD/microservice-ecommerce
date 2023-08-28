package com.tapusd.gateway.fallback;

import com.tapusd.gateway.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping()
    public Mono<Response> fallbackResponse() {
        return Mono.just(new Response()
                        .setStatus(HttpStatus.NOT_FOUND.value())
                        .setMessage("Service Not Found!")
                );
    }
}
