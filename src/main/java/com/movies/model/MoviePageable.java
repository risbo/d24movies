package com.movies.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class MoviePageable{
    private Integer pageStart;
    private Integer pageEnd;
    private Map<String, Long> directors;
}
