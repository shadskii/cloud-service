package com.github.shadskii.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends MongoRepository<Line, String>
{
    @Query
    Line findByPageNum(Long pageNum);
}
