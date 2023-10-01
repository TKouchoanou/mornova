package org.mornova.query.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Auditable {
    private String createdAt;
    private String updatedAt;

}
