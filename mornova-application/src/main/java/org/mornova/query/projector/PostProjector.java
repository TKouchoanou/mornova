package org.mornova.query.projector;

import org.mornova.query.query.UserPostQuery;
import org.mornova.query.result.ProjectPostQR;
import org.mornova.query.result.SimplePostQR;

import java.util.List;

public interface PostProjector {
    List<ProjectPostQR> findProjectPost(UserPostQuery post);
    List<SimplePostQR> findSimplePost(UserPostQuery post);
}
