package com.github.shadskii.data;

import com.github.shadskii.data.Line;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LineRepository extends MongoRepository<Line, String>
{
    Line findByPageNum(Long pageNum);
}
