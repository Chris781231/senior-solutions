package location;

public class LocationParser {

    public Location parse(String text) throws IllegalArgumentException {
        String[] parts = text.split(",");

        checkParam(parts);

        double lat = parseCoordParts(parts[1]);
        double lon = parseCoordParts(parts[2]);

        return new Location(parts[0], lat, lon);
    }

    public boolean isOnEquator(Location location) {
        return location.getLat() == 0;
    }

    public boolean isOnPrimeMeridian(Location location) {
        return location.getLon() == 0;
    }

    private void checkParam(String[] parts) throws IllegalArgumentException {
        if (parts.length != 3) { throw new IllegalArgumentException("Params must be 3 element separated by commas"); }
    }

    private double parseCoordParts(String coordPart) {
        try {
            return Double.parseDouble(coordPart);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("The second and third params must be floating numbers", nfe);
        }
    }
}
