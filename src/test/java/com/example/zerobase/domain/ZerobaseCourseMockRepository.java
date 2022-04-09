package com.example.zerobase.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ZerobaseCourseMockRepository implements ZerobaseCourseRepository {

    private final Map<Long, ZerobaseCourse> memoryData;

    public ZerobaseCourseMockRepository(Map<Long, ZerobaseCourse> memoryData) {
        this.memoryData = memoryData;
    }

    public ZerobaseCourse findById(Long id) {
        return memoryData.getOrDefault(id, null);
    }

    public List<ZerobaseCourse> findAll() {
        return memoryData.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
