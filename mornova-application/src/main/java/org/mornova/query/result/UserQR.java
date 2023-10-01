package org.mornova.query.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserQR extends Auditable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

}
