package com.ss.server.dao;


import com.ss.server.entity.SSUser;

/**
 * The interface Ss user dao.
 */
public interface SSUserDao {

    /**
     * Save.
     *
     * @param user the user
     */
    void save(SSUser user);

    /**
     * Get ss user.
     *
     * @param email the email
     * @return the ss user
     */
    SSUser get(String email);

    /**
     * Gets max port.
     *
     * @return the max port
     */
    int getMaxPort();

    /**
     * Gets last flow.
     *
     * @param email the email
     * @return the last flow
     */
    float getLastFlow(String email);

    /**
     * Update flow.
     *
     * @param mac  the mac
     * @param flow the flow
     */
    void updateFlow(String mac, long flow);

}
