package com.softhaxi.marves.core.domain.logging;

import java.io.Serializable;
import java.sql.Timestamp;

import com.softhaxi.marves.core.domain.account.User;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class LoginHistory implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -370269220487786557L;
    protected String id;
    protected User user;
    protected Timestamp inTime;
    protected String inClient;
    protected String inIPAddress;
    protected Timestamp outTime;
    protected String outClient;
    protected String outIPAddress;
}
