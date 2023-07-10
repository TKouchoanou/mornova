package org.mornova.domain.core.model.objectValue.ids;

import java.io.Serializable;

public interface BaseId <ID> extends Serializable {
    ID value();
}
