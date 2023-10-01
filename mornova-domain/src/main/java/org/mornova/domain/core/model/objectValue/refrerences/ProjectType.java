package org.mornova.domain.core.model.objectValue.refrerences;

import org.mornova.domain.core.model.objectValue.MapToString;

public enum ProjectType implements MapToString {
    UNKNOWN,AGRICOLE,TRANSPORT;

    @Override
    public String mapToString() {
        return this.name();
    }
}
