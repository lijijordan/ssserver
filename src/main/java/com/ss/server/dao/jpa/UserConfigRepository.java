package com.ss.server.dao.jpa;

import com.ss.server.entity.UserConfig;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface UserConfigRepository extends PagingAndSortingRepository<UserConfig, Long> {

    /**
     * Find by mac user config.
     *
     * @param mac the mac
     * @return the user config
     */
    UserConfig findByMac(String mac);
}
