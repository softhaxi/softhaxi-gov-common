package com.softhaxi.marves.core.domain.messaging;

import java.io.Serializable;
import java.util.UUID;

import com.softhaxi.marves.core.domain.account.User;

public class Disposition implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5707614614666372104L;
    protected UUID id;
    protected User assignee;
    protected Letter letter;
    protected String notes;
    protected String pic;
    protected boolean isRead;
}
