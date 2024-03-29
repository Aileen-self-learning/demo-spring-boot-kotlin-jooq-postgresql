/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.pojos;


import org.jooq.generated.tables.interfaces.IUsers;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Users implements IUsers {

    private static final long serialVersionUID = 1L;

    private Integer uid;
    private String  name;
    private String  password;
    private String  auth;

    public Users() {}

    public Users(IUsers value) {
        this.uid = value.getUid();
        this.name = value.getName();
        this.password = value.getPassword();
        this.auth = value.getAuth();
    }

    public Users(
        Integer uid,
        String  name,
        String  password,
        String  auth
    ) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.auth = auth;
    }

    /**
     * Getter for <code>public.users.uid</code>.
     */
    @Override
    public Integer getUid() {
        return this.uid;
    }

    /**
     * Setter for <code>public.users.uid</code>.
     */
    @Override
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * Getter for <code>public.users.name</code>.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.users.name</code>.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.users.password</code>.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for <code>public.users.password</code>.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for <code>public.users.auth</code>.
     */
    @Override
    public String getAuth() {
        return this.auth;
    }

    /**
     * Setter for <code>public.users.auth</code>.
     */
    @Override
    public void setAuth(String auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Users (");

        sb.append(uid);
        sb.append(", ").append(name);
        sb.append(", ").append(password);
        sb.append(", ").append(auth);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IUsers from) {
        setUid(from.getUid());
        setName(from.getName());
        setPassword(from.getPassword());
        setAuth(from.getAuth());
    }

    @Override
    public <E extends IUsers> E into(E into) {
        into.from(this);
        return into;
    }
}
