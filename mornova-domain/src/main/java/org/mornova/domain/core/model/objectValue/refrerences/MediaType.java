package org.mornova.domain.core.model.objectValue.refrerences;

import org.mornova.domain.core.model.objectValue.MapToString;

public enum MediaType implements MapToString {
    URL,PHOTO,VIDEO;

    @Override
    public String mapToString() {
        return this.name();
    }
}
