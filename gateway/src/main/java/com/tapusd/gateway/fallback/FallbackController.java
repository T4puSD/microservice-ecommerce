package com.tapusd.gateway.fallback;

import com.tapusd.gateway.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/todos")
    public ResponseEntity<Response> fallbackTodoResponse() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Response()
                        .setStatus(HttpStatus.NOT_FOUND.value())
                        .setMessage("Service Not Found!")
                );
    }
}
