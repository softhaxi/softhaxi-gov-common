package com.softhaxi.marves.core.domain.messaging;

import java.io.Serializable;
import java.util.UUID;

public class DispositionAction implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected UUID id;
    protected Disposition disposition;
    protected String actionCode;
    protected String actionName;
    
}
