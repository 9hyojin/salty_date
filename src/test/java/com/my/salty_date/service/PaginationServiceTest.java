package com.my.salty_date.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("비즈니스로직 - 페이지네이션")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)  //테스트의 무게를 줄여줌
class PaginationServiceTest {

    private final PaginationService paginationService;

    public PaginationServiceTest(@Autowired PaginationService paginationService){
        this.paginationService = paginationService;
    }


    @DisplayName("페이징바 리스트 만들기")
    @MethodSource
    @ParameterizedTest(name = "[{index}] {0},{1} => {2}")  //로그정리
    void makePaginationBar(int currentPageNum, int totalPages, List<Integer> expected){
        //given 아래 stactic 으로 만듬. 이름 동일하게 해야 인식.

        //when
        List<Integer> actual = paginationService.getPaginationBarNum(currentPageNum, totalPages);

        //then
        assertThat(actual).isEqualTo(expected);

    }

    static Stream<Arguments>makePaginationBar(){
        return Stream.of(
                Arguments.arguments(0,13,List.of(0,1,2,3,4)),
                Arguments.arguments(1,13,List.of(0,1,2,3,4)),
                Arguments.arguments(2,13,List.of(0,1,2,3,4)),
                Arguments.arguments(3,13,List.of(1,2,3,4,5)),
                Arguments.arguments(4,13,List.of(2,3,4,5,6)),
                Arguments.arguments(5,13,List.of(3,4,5,6,7)),
                Arguments.arguments(10,13,List.of(8,9,10,11,12)),
                Arguments.arguments(11,13,List.of(9,10,11,12)),
                Arguments.arguments(12,13,List.of(10,11,12))
                );
    }


    @DisplayName("페이징바 길이확인")
    @Test
    void paginationBarNum(){
        //given

        //when
        int barLength = paginationService.currentBarLength();

        //then
        assertThat(barLength).isEqualTo(5);
    }

}
