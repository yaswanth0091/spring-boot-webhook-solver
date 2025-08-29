package com.example.webhooksolver.service;

import org.springframework.stereotype.Service;

@Service
public class SolutionService {

    public String getFinalQuery() {
        return "SELECT e1.EMP_ID, " +
               "e1.FIRST_NAME, " +
               "e1.LAST_NAME, " +
               "d.DEPARTMENT_NAME, " +
               "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT " +
               "FROM EMPLOYEE e1 " +
               "JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID " +
               "LEFT JOIN EMPLOYEE e2 " +
               "ON e1.DEPARTMENT = e2.DEPARTMENT " +
               "AND e2.DOB > e1.DOB " +
               "GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME " +
               "ORDER BY e1.EMP_ID DESC;";
    }
}
