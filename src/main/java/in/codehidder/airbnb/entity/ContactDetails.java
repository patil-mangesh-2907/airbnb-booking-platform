package in.codehidder.airbnb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ContactDetails {
    @Column(name = "contact_phone")
    private String phone;

    @Column(name = "contact_email")
    private String email;
}
