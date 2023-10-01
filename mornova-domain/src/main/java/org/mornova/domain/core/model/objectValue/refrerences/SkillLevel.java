package org.mornova.domain.core.model.objectValue.refrerences;

import org.mornova.domain.core.model.objectValue.MapToString;

public enum SkillLevel implements MapToString {
    JUNIOR,CONFIRM,EXPERT;

    @Override
    public String mapToString() {
        return this.name();
    }
}
