package com.ss.server.dao.jpa;

import com.ss.server.entity.Kcptun;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface KcptunRepository extends PagingAndSortingRepository<Kcptun, Long> {
    
}
