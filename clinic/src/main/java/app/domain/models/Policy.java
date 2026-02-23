package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class Policy {

    private long id;
    private Company company;
    private String policyNumber;
    private boolean active;
    private Date expirationDate;

}
