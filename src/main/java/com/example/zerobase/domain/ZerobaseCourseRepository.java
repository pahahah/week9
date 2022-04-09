package com.example.zerobase.domain;

import java.util.List;

public interface ZerobaseCourseRepository {

    ZerobaseCourse findById(Long id);

    List<ZerobaseCourse> findAll();
}
