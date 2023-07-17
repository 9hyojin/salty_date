package com.my.salty_date.repository;

import com.my.salty_date.entity.Dating;


import com.my.salty_date.entity.QDating;
import com.my.salty_date.entity.QFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

import java.util.List;


public class DatingRepositoryImpl implements DatingRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QDating qDating = QDating.dating;
    private final QFile qFile = QFile.file;

    public DatingRepositoryImpl() {
        // 기본 생성자 추가
        this.queryFactory = null; // queryFactory 초기화 코드
    }

    @PersistenceContext
    private EntityManager entityManager;

    public DatingRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Dating> findAllWithFiles() {
        assert queryFactory != null;
        return queryFactory
                .selectFrom(qDating)
                .leftJoin(qDating.fileList, qFile).fetchJoin()
                .fetch();
    }


}
