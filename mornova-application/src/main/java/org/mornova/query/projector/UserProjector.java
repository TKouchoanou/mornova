package org.mornova.query.projector;

import org.mornova.query.query.AllUserQuery;
import org.mornova.query.result.UserQR;

import java.util.List;

public interface UserProjector {
    List<UserQR> findAll(AllUserQuery query);
}
