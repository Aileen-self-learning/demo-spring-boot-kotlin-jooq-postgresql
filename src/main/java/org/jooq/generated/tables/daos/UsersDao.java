/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.daos;


import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.generated.tables.Users;
import org.jooq.generated.tables.records.UsersRecord;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersDao extends DAOImpl<UsersRecord, org.jooq.generated.tables.pojos.Users, Integer> {

    /**
     * Create a new UsersDao without any configuration
     */
    public UsersDao() {
        super(Users.USERS, org.jooq.generated.tables.pojos.Users.class);
    }

    /**
     * Create a new UsersDao with an attached configuration
     */
    public UsersDao(Configuration configuration) {
        super(Users.USERS, org.jooq.generated.tables.pojos.Users.class, configuration);
    }

    @Override
    public Integer getId(org.jooq.generated.tables.pojos.Users object) {
        return object.getUid();
    }

    /**
     * Fetch records that have <code>uid BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.generated.tables.pojos.Users> fetchRangeOfUid(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(Users.USERS.UID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>uid IN (values)</code>
     */
    public List<org.jooq.generated.tables.pojos.Users> fetchByUid(Integer... values) {
        return fetch(Users.USERS.UID, values);
    }

    /**
     * Fetch a unique record that has <code>uid = value</code>
     */
    public org.jooq.generated.tables.pojos.Users fetchOneByUid(Integer value) {
        return fetchOne(Users.USERS.UID, value);
    }

    /**
     * Fetch a unique record that has <code>uid = value</code>
     */
    public Optional<org.jooq.generated.tables.pojos.Users> fetchOptionalByUid(Integer value) {
        return fetchOptional(Users.USERS.UID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.generated.tables.pojos.Users> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Users.USERS.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<org.jooq.generated.tables.pojos.Users> fetchByName(String... values) {
        return fetch(Users.USERS.NAME, values);
    }

    /**
     * Fetch records that have <code>password BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.generated.tables.pojos.Users> fetchRangeOfPassword(String lowerInclusive, String upperInclusive) {
        return fetchRange(Users.USERS.PASSWORD, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<org.jooq.generated.tables.pojos.Users> fetchByPassword(String... values) {
        return fetch(Users.USERS.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>auth BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<org.jooq.generated.tables.pojos.Users> fetchRangeOfAuth(String lowerInclusive, String upperInclusive) {
        return fetchRange(Users.USERS.AUTH, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>auth IN (values)</code>
     */
    public List<org.jooq.generated.tables.pojos.Users> fetchByAuth(String... values) {
        return fetch(Users.USERS.AUTH, values);
    }
}
