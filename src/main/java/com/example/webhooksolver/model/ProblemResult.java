package com.example.webhooksolver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProblemResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sqlQuery;
    private String solution;

    public ProblemResult() {}

    public ProblemResult(String sqlQuery, String solution) {
        this.sqlQuery = sqlQuery;
        this.solution = solution;
    }

    public Long getId() {
        return id;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public String getSolution() {
        return solution;
    }
}
