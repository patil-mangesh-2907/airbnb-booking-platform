package in.codehidder.airbnb.exception;

public class DuplicateHotelContactException extends RuntimeException {
    public DuplicateHotelContactException(String msg) {
        super(msg);
    }
}
