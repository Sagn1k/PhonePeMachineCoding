package in.mesagnik.PhonePeMachineCoding.service;

import in.mesagnik.PhonePeMachineCoding.model.Problem;
import in.mesagnik.PhonePeMachineCoding.model.enums.DifficultyLevel;
import in.mesagnik.PhonePeMachineCoding.model.enums.ProblemTag;

import java.util.*;
import java.util.stream.Collectors;

public class ProblemService {
    private static Map<String, Problem> problemStore;

    public ProblemService() {
        problemStore = new HashMap<>();
    }

    public String addProblem(Problem problem) {
        //validations
        String id = UUID.randomUUID().toString();
        problem.setId(id);
        problemStore.put(id, problem);
        return id;
    }

    public Optional<Problem> getProblem(String problemId) {
        return Optional.of(problemStore.getOrDefault(problemId, null));
    }

    public List<Problem> getTopProblems(ProblemTag problemTag, int limit) {
        List<Problem> problems = problemStore.values().stream().toList();

        return problems.stream()
                .filter(problem -> problem.getTags().contains(problemTag))
                .limit(limit).collect(Collectors.toList());
    }

    public List<Problem> getProblems(ProblemTag problemTag, DifficultyLevel difficultyLevel, Comparator<Problem> comparator) {
        List<Problem> problems = problemStore.values().stream().toList();
        List<Problem> filteredProblems = new ArrayList<>();
        for (Problem problem : problems) {
            if (problem.getTags().contains(problemTag) && problem.getDifficultyLevel().equals(difficultyLevel))
                filteredProblems.add(problem);
        }
        filteredProblems.sort(comparator);
        return filteredProblems;
    }


    public void updateProblem(String problemId, float timeTaken) {
        Problem problem = problemStore.get(problemId);
        problem.setNumberOfUsersSolved(problem.getNumberOfUsersSolved() + 1);
        float avgTime = (problem.getAvgTimeTaken() * (problem.getNumberOfUsersSolved() - 1) + timeTaken) / problem.getNumberOfUsersSolved();

        problem.setAvgTimeTaken(avgTime);
        problemStore.put(problem.getId(), problem);
    }

    public List<Problem> getProblemsForIds(List<String> problemIds) {
        List<Problem> problems = new ArrayList<>();
        for (String problemId : problemIds) {
            Problem fetchedProblem = problemStore.getOrDefault(problemId, null);
            if (fetchedProblem != null)
                problems.add(fetchedProblem);
        }
        return problems;
    }
}
