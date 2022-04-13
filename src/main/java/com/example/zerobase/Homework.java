package com.example.zerobase;

import com.example.zerobase.domain.ZerobaseCourse;
import com.example.zerobase.domain.ZerobaseCourseRepository;
import com.example.zerobase.domain.ZerobaseCourseStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Homework {
    private final ZerobaseCourseRepository repository;

    // TODO: 테스트가 통과할 수 있도록 아래 메서드 들을 작성하세요.

    public Optional<ZerobaseCourse> getZerobaseCourse(Long id) {
        // TODO: id 가 일치하며, hidden = false 인 강의만 조회되어야 함
        ZerobaseCourse zerobaseCourse = repository.findById(id);

        return Optional.ofNullable(zerobaseCourse)
                .filter(course -> !zerobaseCourse.isHidden());
//        if (zerobaseCourse != null && !zerobaseCourse.isHidden()) {
//            return Optional.of(zerobaseCourse);
//        }
//
//        return Optional.empty();
    }


    public List<ZerobaseCourse> getZerobaseCoursesBy(ZerobaseCourseStatus status) {
        // TODO: status가 일치하고, hidden = false 인 강의들이 조회되어야 함
        List<ZerobaseCourse> allCourses = repository.findAll().stream()
                .filter(zerobaseCourse -> !zerobaseCourse.isHidden() && zerobaseCourse.getStatus().equals(status))
                .collect(Collectors.toList());
//        List<ZerobaseCourse> allCourses = repository.findAll();
//
//        for (ZerobaseCourse course : allCourses) {
//            if (course.getStatus().equals(status) && !course.isHidden()) {
//                result.add(course);
//            }
//        }
        return allCourses;

    }


    public List<ZerobaseCourse> getOpenZerobaseCourseList(LocalDate targetDt) {
        // TODO: status = "OPEN" 이고, hidden = false 이며,
        //  startAt <= targetDt && targetDt <= endAt 인 강의만 조회되어야함.

        List<ZerobaseCourse> allCourses = getZerobaseCoursesBy(ZerobaseCourseStatus.OPEN).stream()
                .filter(course -> course.getStartAt().isBefore(targetDt) && course.getEndAt().isAfter(targetDt))
                .collect(Collectors.toList());


        return allCourses;

    }



}
