package pl.grizzlysoftware.vetclinic.appuser;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
@Getter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "d{4}")
    private String loginID;
    @Pattern(regexp = "d{4}")
    private String loginPIN;
}
