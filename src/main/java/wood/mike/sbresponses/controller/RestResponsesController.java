package wood.mike.sbresponses.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import wood.mike.sbresponses.model.RandomResponse;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The majority of examples use ResponseEntity, sixteen onwards do not
 */
@Slf4j
@RestController
public class RestResponsesController {

    @GetMapping("/one")
    public String one() {
        return "hi from one";
    }

    @GetMapping("/two")
    public ResponseEntity<RandomResponse> two() {
        return ResponseEntity.ok().body(RandomResponse.of("hi from two"));
    }


    @GetMapping("/three")
    public ResponseEntity<String> three() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/four")
    public ResponseEntity<RandomResponse> four() {
        return new ResponseEntity<>(RandomResponse.of("howdy from four"), HttpStatus.OK);
    }

    @GetMapping("/five")
    public ResponseEntity<RandomResponse> five() {
        return new ResponseEntity<>(MultiValueMap.fromSingleValue(Map.of("k1", "v1")), HttpStatus.OK);
    }

    @GetMapping("/six")
    public ResponseEntity<RandomResponse> six() {
        return new ResponseEntity<>(RandomResponse.of("sup from six"), MultiValueMap.fromMultiValue(Map.of("k1", List.of("v1", "v2"))), HttpStatus.OK);
    }

    @GetMapping("/seven")
    public ResponseEntity<RandomResponse> seven() {
        return ResponseEntity.of((Optional.empty()));
    }

    @GetMapping("/eight")
    public ResponseEntity<RandomResponse> eight() {
        return ResponseEntity.created(URI.create("/this/url/was/created")).build();
    }

    @GetMapping("/nine")
    public ResponseEntity<RandomResponse> nine() {
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/ten")
    public ResponseEntity<RandomResponse> ten() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/eleven")
    public ResponseEntity<RandomResponse> eleven() {
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/twelve")
    public ResponseEntity<RandomResponse> twelve() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/thirteen")
    public ResponseEntity<RandomResponse> thirteen() {
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/fourteen")
    public ResponseEntity<RandomResponse> fourteen() {
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/fifteen")
    public ResponseEntity<RandomResponse> fifteen() {
        return ResponseEntity.ok().headers(httpHeaders -> {
            httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        }).body(RandomResponse.of("hola from fifteen"));
    }

    @GetMapping("/sixteen")
    public RandomResponse sixteen() {
        return new RandomResponse("marmalade");
    }

    @GetMapping("/seventeen")
    public Map<String, String> seventeen() {
        return Map.of("name", "bob", "age", "17");
    }

    @GetMapping("/eighteen")
    public List<Map<String, String>> eighteen() {
        return List.of(
                Map.of("name", "ken", "age", "18"),
                Map.of("name", "sue", "age", "18")
        );
    }

    @GetMapping("/nineteen")
    public HttpEntity<String> nineteen() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, "application/json");
        return new HttpEntity<>("hi from nineteen", headers);
    }

    @GetMapping("/twenty")
    public HttpHeaders twenty() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("name", "hi from twenty");
        return headers;
    }

    @GetMapping("/twentyone")
    public ErrorResponse twentyone() {
        return ErrorResponse.create(new RuntimeException("bad stuff in twentyone"), HttpStatus.INTERNAL_SERVER_ERROR, "this was never going to end well");
    }

    @GetMapping("/twentytwo")
    public ProblemDetail twentytwo() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, "bad gateway from twentytwo");
    }

    @GetMapping("/twentythree")
    public DeferredResult<String> twentythree() {
        log.info("starting twentythree in thread {}", Thread.currentThread().getName());
        DeferredResult<String> deferredResult = new DeferredResult<>(1000L);
        deferredResult.onCompletion(()-> log.info("finished twentythree in thread {}", Thread.currentThread().getName()));
        deferredResult.onTimeout(() -> {
            deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("twentythree did not complete in time"));
        });

        new Thread(() -> {
            log.info("fetching result in new thread - {}", Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error("InterruptedException", e);
            }
            deferredResult.setResult("hi from twentythree");
        }).start();

        return deferredResult;
    }

    @GetMapping("/twentyfour")
    public ResponseBodyEmitter twentyfour() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        emitter.onCompletion(()-> log.info("finished twentyfour in thread {}", Thread.currentThread().getName()));
        new Thread(() -> {
            try {
                emitter.send("first message from twentyfour in thread " + Thread.currentThread().getName());
                Thread.sleep(1000L);
                emitter.send("second message from twentyfour");
                emitter.complete();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        return emitter;
    }
}
