package org.mornova.repository.jpa.transformer;

import org.hibernate.transform.ResultTransformer;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.query.result.UserQR;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//https://vladmihalcea.com/spring-jpa-dto-projection/
public class UserQRResultTransformer  implements ResultTransformer<UserQR> {
    @Override
    public UserQR transformTuple(Object[] tuple, String[] aliases) {
        UserId userId=(UserId) tuple[0];
       return new UserQR(userId.mapToString(), (String) tuple[1],(String) tuple[2],(String) tuple[3], new ArrayList<>(List.of((String) tuple[4])));
    }

    @Override
    public List<UserQR> transformList(List<UserQR> users) {
     return new ArrayList<>(
             users.stream()
             .collect(Collectors.toMap(
                     UserQR::getId,
                     Function.identity(),
                     (existingUser, newUser) -> {
                         existingUser.getRoles().addAll(newUser.getRoles());
                         return existingUser;
                     }))
             .values()
     );
    }
}
