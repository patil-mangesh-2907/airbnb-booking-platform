package in.codehidder.airbnb.exception;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(String msg) {
        super(msg);
    }
}
