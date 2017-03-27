package com.ss.server.dao.jpa;

import com.ss.server.entity.GuideSentence;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository can be used to delegate CRUD operations against the data source: http://goo.gl/P1J8QH
 */
public interface GuideSentenceRepository extends PagingAndSortingRepository<GuideSentence, Long> {

    /**
     * Find top by order by create time desc guide sentence.
     *
     * @return the guide sentence
     */
    GuideSentence findTopByOrderByCreateTimeDesc();

}
