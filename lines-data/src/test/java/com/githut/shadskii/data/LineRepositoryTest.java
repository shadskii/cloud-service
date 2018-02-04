package com.githut.shadskii.data;

import com.github.shadskii.data.Line;
import com.github.shadskii.data.LineRepository;
import com.mongodb.MongoClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {LineRepositoryTest.MongoTestConfig.class})
public class LineRepositoryTest
{
    public static class MongoTestConfig
    {
        @Bean
        public MongoTemplate mongoTemplate() throws Exception
        {
            return new MongoTemplate(new MongoClient("localhost"), "findall");
        }
    }

//    @Autowired
//    private TestEntityManager entityManager;
    @Autowired
    private LineRepository lineRepository;

    @Test
    public void testFindByPageNum()
    {
        Line line0 = new Line(0L, "Hello World!");
//        entityManager.persist(line0);
//        entityManager.flush();

        Line line0Persisted = lineRepository.findByPageNum(0L);

        assertThat(line0Persisted).isEqualTo(line0);
    }
}
