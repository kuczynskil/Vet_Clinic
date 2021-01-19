package pl.grizzlysoftware.vetclinic.appuser;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Getter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "d{4}")
    @Column(length = 4)
    private String loginID;
    @Pattern(regexp = "d{4}")
    @Column(length = 4)
    private String loginPIN;

    private String name;
    private String surname;
}
