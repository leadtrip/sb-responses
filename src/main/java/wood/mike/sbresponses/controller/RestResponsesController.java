package wood.mike.sbresponses.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wood.mike.sbresponses.model.BikeResponse;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * These examples all use ResponseEntity
 */
@RestController
public class BikeController {

    @GetMapping("/one")
    public String one() {
        return "bike";
    }

    @GetMapping("/two")
    public ResponseEntity<BikeResponse> two() {
        return ResponseEntity.ok().body(BikeResponse.of("nice bike"));
    }


    @GetMapping("/three")
    public ResponseEntity<String> three() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/four")
    public ResponseEntity<BikeResponse> four() {
        return new ResponseEntity<>(BikeResponse.of("nice bike"), HttpStatus.OK);
    }

    @GetMapping("/five")
    public ResponseEntity<BikeResponse> five() {
        return new ResponseEntity<>(MultiValueMap.fromSingleValue(Map.of("k1", "v1")), HttpStatus.OK);
    }

    @GetMapping("/six")
    public ResponseEntity<BikeResponse> six() {
        return new ResponseEntity<>(BikeResponse.of("nice bike"), MultiValueMap.fromMultiValue(Map.of("k1", List.of("v1", "v2"))), HttpStatus.OK);
    }

    @GetMapping("/seven")
    public ResponseEntity<BikeResponse> seven() {
        return ResponseEntity.of((Optional.empty()));
    }

    @GetMapping("/eight")
    public ResponseEntity<BikeResponse> eight() {
        return ResponseEntity.created(URI.create("/bike")).build();
    }

    @GetMapping("/nine")
    public ResponseEntity<BikeResponse> nine() {
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/ten")
    public ResponseEntity<BikeResponse> ten() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/eleven")
    public ResponseEntity<BikeResponse> eleven() {
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/twelve")
    public ResponseEntity<BikeResponse> twelve() {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/thirteen")
    public ResponseEntity<BikeResponse> thirteen() {
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping("/fourteen")
    public ResponseEntity<BikeResponse> fourteen() {
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/fifteen")
    public ResponseEntity<BikeResponse> fifteen() {
        return ResponseEntity.ok().headers(httpHeaders -> {
            httpHeaders.add(HttpHeaders.ACCEPT, "application/json");
            httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        }).body(BikeResponse.of("nice bike"));
    }
}
