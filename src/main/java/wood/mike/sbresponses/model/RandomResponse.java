package wood.mike.sbresponses.model;

public record BikeResponse(String name) {

    public static BikeResponse of(String name) {
        return new BikeResponse(name);
    }
}
