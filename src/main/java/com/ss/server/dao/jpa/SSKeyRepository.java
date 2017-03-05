package com.ss.server.dao.jpa;

import com.ss.server.entity.SSKey;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface SSKeyRepository extends PagingAndSortingRepository<SSKey, Long> {
    /**
     * Find by key ss key.
     *
     * @param key the key
     * @return the ss key
     */
    SSKey findByKey(String key);
}
