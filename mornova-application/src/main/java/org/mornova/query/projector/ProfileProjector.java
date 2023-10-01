package org.mornova.query.projector;

import org.mornova.query.query.UserProfileQuery;
import org.mornova.query.result.ProfileQR;

import java.util.List;

public interface ProfileProjector {
  List<ProfileQR> findAll(UserProfileQuery query);
}
