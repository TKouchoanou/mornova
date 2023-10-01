package org.mornova.query.query;

import lombok.*;

import java.util.List;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllUserQuery {
    List<String> ids;
}
