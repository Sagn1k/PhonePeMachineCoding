package in.mesagnik.PhonePeMachineCoding;


import in.mesagnik.PhonePeMachineCoding.contracts.CandidateDTO;
import in.mesagnik.PhonePeMachineCoding.model.Problem;
import in.mesagnik.PhonePeMachineCoding.model.enums.Department;
import in.mesagnik.PhonePeMachineCoding.model.enums.DifficultyLevel;
import in.mesagnik.PhonePeMachineCoding.model.enums.ProblemTag;
import in.mesagnik.PhonePeMachineCoding.service.CandidateService;
import in.mesagnik.PhonePeMachineCoding.service.ProblemService;
import in.mesagnik.PhonePeMachineCoding.service.ProblemUserService;
import in.mesagnik.PhonePeMachineCoding.strategy.ProblemScoreStrategy;
import in.mesagnik.PhonePeMachineCoding.strategy.ScoreStrategy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis() % 1000;

        CandidateService candidateService = new CandidateService();
        ProblemService problemService = new ProblemService();
        ProblemUserService problemUserService = new ProblemUserService();

        CandidateDTO candidate1 = CandidateDTO.builder()
                .email("sagnikbhowmick@gmail.com")
                .department(Department.TECH)
                .name("sagnik")
                .password("qwerty")
                .build();

        CandidateDTO candidate2 = CandidateDTO.builder()
                .email("sgnk@gmail.com")
                .department(Department.PAYMENT)
                .name("sgnk")
                .password("qwerty")
                .build();

        Problem problem1 = Problem.builder()
                .name("Graph problem")
                .description("description")
                .score(100)
                .tags(Arrays.asList(ProblemTag.GRAPH, ProblemTag.DP))
                .difficultyLevel(DifficultyLevel.MEDIUM)
                .build();

        Problem problem2 = Problem.builder()
                .name("String problem")
                .description("description")
                .score(100)
                .tags(Arrays.asList(ProblemTag.STRINGS, ProblemTag.DP))
                .difficultyLevel(DifficultyLevel.MEDIUM)
                .build();

        Problem problem3 = Problem.builder()
                .name("Hashmap problem")
                .description("description")
                .score(100)
                .tags(Arrays.asList(ProblemTag.HASHMAP, ProblemTag.ARRAYS))
                .difficultyLevel(DifficultyLevel.HARD)
                .build();

        try {
            String candidateId1 = candidateService.addUser(candidate1);
            System.out.println("Candidate 1 registered with ID: " + candidateId1);

            String candidateId2 = candidateService.addUser(candidate2);
            System.out.println("Candidate 2 registered with ID: " + candidateId2);

            String problemId1 = problemService.addProblem(problem1);
            System.out.println("Problem 1 added with ID: " + problemId1);

            String problemId2 = problemService.addProblem(problem2);
            System.out.println("Problem 2 added with ID: " + problemId2);

            String problemId3 = problemService.addProblem(problem3);
            System.out.println("Problem 3 added with ID: " + problemId3);

            ScoreStrategy scoreStrategy1 = new ProblemScoreStrategy();
            problemUserService.solveProblem(candidateId1, problemId1, 110, scoreStrategy1);
            System.out.println("Problem 1 solved by Candidate 1");

            problemUserService.solveProblem(candidateId1, problemId2, 166, scoreStrategy1);
            System.out.println("Problem 2 solved by Candidate 1");

            problemUserService.solveProblem(candidateId1, problemId3, 145, scoreStrategy1);
            System.out.println("Problem 3 solved by Candidate 1");

            problemUserService.solveProblem(candidateId2, problemId2, 110, scoreStrategy1);
            System.out.println("Problem 2 solved by Candidate 2");

            CandidateDTO leader = candidateService.getLeader();
            System.out.println("Leader: " + leader.getName() + " with ID: " + leader.getId());

            Comparator<Problem> scoreComparator = Comparator.comparingInt(Problem::getScore);
            List<Problem> problems = problemService.fetchProblems(ProblemTag.HASHMAP, DifficultyLevel.HARD, scoreComparator);
            System.out.println("Hard problems with HashMap tag: " + problems.size() + " found");

            List<Problem> problemList = problemUserService.getSolvedProblemsListForUser(candidateId2);
            System.out.println("Candidate 2 solved " + problemList.size() + " problems");


            List<Problem> mostLikedProblemList = problemService.getTopNLikedProblems(2);
            System.out.println("Top liked problems are: " + mostLikedProblemList.size());

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            long endTime = System.currentTimeMillis() % 1000;

            System.out.println("Total execution time of the application: " + (endTime - startTime) + " milliseconds");
        }
    }
}
