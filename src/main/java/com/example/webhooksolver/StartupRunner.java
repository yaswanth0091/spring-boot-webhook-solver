package com.example.webhooksolver;

import com.example.webhooksolver.service.SolutionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final WebhookSolverApplication solverService;

    public StartupRunner(WebhookSolverApplication solverService) {
        this.solverService = solverService;
    }

    @Override
    public void run(String... args) {
        solverService.solveProblem();
        System.out.println("Problem solved and stored in DB ✅");
    }
}
