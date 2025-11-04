package wood.mike.sbresponses.model;

public record RandomResponse(String name) {

    public static RandomResponse of(String name) {
        return new RandomResponse(name);
    }
}
