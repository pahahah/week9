package com.example.zerobase;

import com.example.zerobase.domain.ZerobaseCourse;
import com.example.zerobase.domain.ZerobaseCourseRepository;
import com.example.zerobase.domain.ZerobaseCourseStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Homework {
    private final ZerobaseCourseRepository repository;

    // TODO: 테스트가 통과할 수 있도록 아래 메서드 들을 작성하세요.

    public Optional<ZerobaseCourse> getZerobaseCourse(Long id) {
        // TODO: id 가 일치하며, hidden = false 인 강의만 조회되어야 함

        ZerobaseCourse zerobaseCourse = repository.findById(id);

        if (zerobaseCourse != null && !zerobaseCourse.isHidden()) {
            return Optional.of(zerobaseCourse);
        }

        return Optional.empty();
    }

    public List<ZerobaseCourse> getZerobaseCourseListWithStatus(ZerobaseCourseStatus status) {
        // TODO: status가 일치하고, hidden = false 인 강의들이 조회되어야 함

        List<ZerobaseCourse> result = new ArrayList<>();
        List<ZerobaseCourse> courseList = repository.findAll();

        for (ZerobaseCourse course : courseList) {
            if (course.getStatus().equals(status) && !course.isHidden()) {
                result.add(course);
            }
        }

        return result;
    }

    public List<ZerobaseCourse> getOpenZerobaseCourseList(LocalDate targetDt) {
        // TODO: status = "OPEN" 이고, hidden = false 이며,
        //  startAt <= targetDt && targetDt <= endAt 인 강의만 조회되어야함.

        List<ZerobaseCourse> result = new ArrayList<>();
        List<ZerobaseCourse> courseList = getZerobaseCourseListWithStatus(ZerobaseCourseStatus.OPEN);

        for (ZerobaseCourse course : courseList) {
            if (course.getStartAt().isBefore(targetDt) && course.getEndAt().isAfter(targetDt)) {
                result.add(course);
            }
        }

        return result;
    }
}
