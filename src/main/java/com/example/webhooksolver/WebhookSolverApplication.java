package com.example.webhooksolver.service;

import com.example.webhooksolver.model.ProblemResult;
import com.example.webhooksolver.repository.ProblemResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WebhookSolverApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProblemResultRepository repository;

    public void solveProblem() {
        String sqlQuery = """
            SELECT e1.EMP_ID,
                   e1.FIRST_NAME,
                   e1.LAST_NAME,
                   d.DEPARTMENT_NAME,
                   COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT
            FROM EMPLOYEE e1
            JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
            LEFT JOIN EMPLOYEE e2 
                   ON e1.DEPARTMENT = e2.DEPARTMENT 
                  AND e2.DOB > e1.DOB
            GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
            ORDER BY e1.EMP_ID DESC
        """;

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sqlQuery);

        // Store solution in DB as JSON
        ProblemResult result = new ProblemResult(sqlQuery, results.toString());
        repository.save(result);
    }
}
