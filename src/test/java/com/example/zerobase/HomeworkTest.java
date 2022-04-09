package com.example.zerobase;

import com.example.zerobase.domain.ZerobaseCourse;
import com.example.zerobase.domain.ZerobaseCourseMockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HomeworkTest {

    Map<Long, ZerobaseCourse> mockData = createMock();

    Homework homework = new Homework(new ZerobaseCourseMockRepository(mockData));

    @Test
    @DisplayName("IN_PROGRESS 상태의 강의가 모두 조회된다.")
    public void getCourse__IN_PROGRESS() {
        List<ZerobaseCourse> inProgressMockList = homework.getZerobaseCourse("IN_PROGRESS");
        assertThat(inProgressMockList.size()).isEqualTo(2L);
    }

    @Test
    @DisplayName("CLOSE 상태의 강의가 모두 조회된다.")
    public void getCourse__CLOSE() {
        List<ZerobaseCourse> inProgressMockList = homework.getZerobaseCourse("CLOSE");
        assertThat(inProgressMockList.size()).isEqualTo(1L);
    }

    @Test
    @DisplayName("OPEN 상태의 강의가 모두 조회된다.")
    public void getCourse__OPEN() {
        List<ZerobaseCourse> inProgressMockList = homework.getZerobaseCourse("OPEN");
        assertThat(inProgressMockList.size()).isEqualTo(2L);
    }


    @Test
    @DisplayName("현재 시간 기준 OPEN 상태의 강의가 모두 조회된다.")
    public void getCourse__current_OPEN() {
        List<ZerobaseCourse> inProgressMockList = homework.getOpenZerobaseCourse(LocalDate.now());
        assertThat(inProgressMockList.size()).isEqualTo(1L);
    }


    @Test
    @DisplayName("유효하지 않은 id를 넣었을때 비어있는 옵셔널이 리턴된다.")
    public void getInvalidCourse() {
        Optional<ZerobaseCourse> zerobaseCourse = homework.getZerobaseCourse(-1L);
        assertThat(zerobaseCourse.isPresent()).isFalse();
    }


    private Map<Long, ZerobaseCourse> createMock() {
        return Map.ofEntries(
                Map.entry(1L, ZerobaseCourse
                        .builder()
                        .id(1L)
                        .name("Java 백엔드 개발자 취업 (2기)")
                        .status("IN_PROGRESS")
                        .startAt(LocalDate.now().minusMonths(1))
                        .endAt(LocalDate.now().plusMonths(1))
                        .build()),
                Map.entry(2L, ZerobaseCourse
                        .builder()
                        .id(2L)
                        .name("프론트엔드 개발자되기")
                        .status("IN_PROGRESS")
                        .startAt(LocalDate.now().minusMonths(2))
                        .endAt(LocalDate.now().plusMonths(1))
                        .build()),
                Map.entry(3L, ZerobaseCourse
                        .builder()
                        .id(3L)
                        .name("바로 써먹는 데이터 분석")
                        .status("CLOSE")
                        .startAt(LocalDate.now().minusMonths(2))
                        .endAt(LocalDate.now().minusMonths(1))
                        .build()),
                Map.entry(4L, ZerobaseCourse
                        .builder()
                        .id(4L)
                        .name("직접 만드는 파이썬 자동화 48일")
                        .status("OPEN")
                        .startAt(LocalDate.now().minusMonths(1))
                        .endAt(LocalDate.now().plusMonths(1))
                        .build()),
                Map.entry(5L, ZerobaseCourse
                        .builder()
                        .id(5L)
                        .name("Java 백엔드 개발자 취업 (1기)")
                        .status("OPEN")
                        .startAt(LocalDate.now().plusMonths(1))
                        .endAt(LocalDate.now().plusMonths(2))
                        .build()),
                Map.entry(6L, ZerobaseCourse
                        .builder()
                        .id(6L)
                        .name("테스트용 강의")
                        .status("OPEN")
                        .startAt(LocalDate.now().minusMonths(1))
                        .endAt(LocalDate.now().plusMonths(2))
                        .hidden(true)
                        .build())
        );
    }
}