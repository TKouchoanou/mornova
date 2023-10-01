package org.mornova.domain.core.model.objectValue.refrerences;

import org.mornova.domain.core.model.objectValue.MapToString;

public enum ProjectStatus implements MapToString {
    STARTED,
    IN_REDACTION;

    @Override
    public String mapToString() {
        return this.name();
    }
}
