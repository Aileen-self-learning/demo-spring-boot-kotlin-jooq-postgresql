/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.interfaces;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface IUsers extends Serializable {

    /**
     * Setter for <code>public.users.uid</code>.
     */
    public void setUid(Integer value);

    /**
     * Getter for <code>public.users.uid</code>.
     */
    public Integer getUid();

    /**
     * Setter for <code>public.users.name</code>.
     */
    public void setName(String value);

    /**
     * Getter for <code>public.users.name</code>.
     */
    public String getName();

    /**
     * Setter for <code>public.users.password</code>.
     */
    public void setPassword(String value);

    /**
     * Getter for <code>public.users.password</code>.
     */
    public String getPassword();

    /**
     * Setter for <code>public.users.auth</code>.
     */
    public void setAuth(String value);

    /**
     * Getter for <code>public.users.auth</code>.
     */
    public String getAuth();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IUsers
     */
    public void from(IUsers from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IUsers
     */
    public <E extends IUsers> E into(E into);
}
