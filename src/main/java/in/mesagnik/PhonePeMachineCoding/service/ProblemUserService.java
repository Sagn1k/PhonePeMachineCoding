package in.mesagnik.PhonePeMachineCoding.service;

import in.mesagnik.PhonePeMachineCoding.model.Problem;
import in.mesagnik.PhonePeMachineCoding.model.ProblemUserMapping;
import in.mesagnik.PhonePeMachineCoding.model.enums.UserProblemStatus;
import in.mesagnik.PhonePeMachineCoding.strategy.ScoreStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class ProblemUserService {
    private static Map<String, ProblemUserMapping> problemUserMappingStore;
    private final ProblemService problemService;

    private final CandidateService candidateService;

    public ProblemUserService() {
        problemUserMappingStore = new HashMap<>();
        this.problemService = new ProblemService();
        this.candidateService = new CandidateService();
    }

    public void solveProblem(String candidateId, String problemId, float timeTaken, ScoreStrategy scoreStrategy) {
        //validations to check valid candidate and problem

        Problem problem = problemService.getProblem(problemId);
        float finalScore = scoreStrategy.calculateScore(problem, timeTaken);

        ProblemUserMapping problemUserMapping = ProblemUserMapping.builder()
                .id(UUID.randomUUID().toString())
                .problemId(problemId)
                .candidateId(candidateId)
                .score(finalScore)
                .status(UserProblemStatus.SOLVED)
                .timeToSolve(timeTaken)
                .build();

        problemUserMappingStore.put(problemUserMapping.getId(), problemUserMapping);

        problemService.updateProblem(problemId, timeTaken);

        candidateService.updateCandidateScore(candidateId, finalScore);

    }

    public List<Problem> getSolvedProblemsListForUser(String candidateId) {
        List<ProblemUserMapping> problemUserMappings = problemUserMappingStore.values().stream().collect(Collectors.toList());
        List<ProblemUserMapping> problemUserMappingsForCandidate = problemUserMappings.stream()
                .filter(problemUserMapping -> problemUserMapping.getCandidateId() == candidateId &&
                        problemUserMapping.getStatus().equals(UserProblemStatus.SOLVED))
                .collect(Collectors.toList());

        if (problemUserMappingsForCandidate == null)
            return new ArrayList<>();

        List<Problem> problemsForUser = problemService.getProblemsForIds(problemUserMappingsForCandidate.stream().map(ProblemUserMapping::getProblemId).collect(Collectors.toList()));
        return problemsForUser;
    }
}

