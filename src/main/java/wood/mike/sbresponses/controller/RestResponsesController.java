package wood.mike.sbresponses.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wood.mike.sbresponses.model.RandomResponse;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The majority of examples use ResponseEntity, sixteen onwards do not
 */
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
}
