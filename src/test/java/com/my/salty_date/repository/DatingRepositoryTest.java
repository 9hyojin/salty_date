package com.my.salty_date.repository;

import com.my.salty_date.constant.DatingStatus;
import com.my.salty_date.entity.Dating;
import com.my.salty_date.entity.QDating;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class DatingRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    DatingRepository datingRepository;

    @Test
    @DisplayName("데이트 저장 테스트")
    public void createDatingTest(){
        Dating dating = new Dating();
        dating.setDatingTitle("테스트 데이트");
        dating.setDatingWriter("테스트 데이트 글쓴이");
        dating.setDatingPassword("테스트 데이트 비밀번호");
        dating.setDatingAddress("테스트 데이트 주소");
        dating.setDatingContent("테스트 데이트 내용");
        dating.setDatingStatus(DatingStatus.OK);
        Dating savedDating = datingRepository.save(dating);
        System.out.println(savedDating);
    }

    public void createDatingList(){
        for(int i=1; i<=10; i++){
            Dating dating = new Dating();
            dating.setDatingTitle("데이트 제목"+i);
            dating.setDatingWriter("데이트 글쓴이" + i);
            dating.setDatingPassword("데이트 비밀번호" + i);
            dating.setDatingAddress("데이트 주소" + i);
            dating.setDatingContent("데이트 내용" + i);
            dating.setDatingStatus(DatingStatus.OK);
        }
    }

    @Test
    @DisplayName("데이트 조회 테스트")
    public void findByDatingTest(){
        this.createDatingList();
        List<Dating> datingList = datingRepository.findByDatingTitle("데이트 제목1");
        for(Dating dating : datingList){
            System.out.println(dating.toString());
        }

    }

    @Test
    @DisplayName("데이트제목 or 데이트 글쓴이 테스트")
    public void findByDatingTitleOrDatingWriterTest(){
        this.createDatingList();
        List<Dating> datingList = datingRepository.findByDatingTitleOrDatingWriter("테스트 데이트 1", "테스트 데이트 글쓴이 1");
        for(Dating dating : datingList){
            System.out.println(dating.toString());
        }
    }


    @Test
    @DisplayName("QueryDSL 조회 테스트 1")
    public void queryDslTest(){
        this.createDatingList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QDating qDating = QDating.dating;
        JPAQuery<Dating> query = queryFactory.selectFrom(qDating)
                .where(qDating.datingStatus.eq(DatingStatus.OK))
                .where(qDating.datingTitle.like("%"+"데이트 제목"+"%"))
                .orderBy(qDating.datingAddress.desc());

        List<Dating> datingList = query.fetch();

        for(Dating dating : datingList){
            System.out.println(dating.toString());
        }
    }

}