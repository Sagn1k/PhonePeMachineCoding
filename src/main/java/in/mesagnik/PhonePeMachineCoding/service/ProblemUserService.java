package in.mesagnik.PhonePeMachineCoding.service;

import com.google.common.base.Preconditions;
import in.mesagnik.PhonePeMachineCoding.exceptions.InvalidInputException;
import in.mesagnik.PhonePeMachineCoding.model.Problem;
import in.mesagnik.PhonePeMachineCoding.model.ProblemUserMapping;
import in.mesagnik.PhonePeMachineCoding.model.enums.UserProblemStatus;
import in.mesagnik.PhonePeMachineCoding.strategy.ScoreStrategy;
import org.apache.commons.lang3.StringUtils;

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
        Preconditions.checkArgument(!StringUtils.isEmpty(candidateId), "candidate Id is missing in request");
        Preconditions.checkArgument(!StringUtils.isEmpty(problemId), "problem Id is missing in request");

        Optional<Problem> problemOptional = problemService.getProblem(problemId);

        //Check if Problem is present or not, if not present then throw an error
        if (problemOptional.isEmpty()) throw new InvalidInputException("Given ProblemId doesn't exist");

        float finalScore = scoreStrategy.calculateScore(problemOptional.get(), timeTaken);

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
        List<ProblemUserMapping> problemUserMappings = problemUserMappingStore.values().stream().toList();
        List<ProblemUserMapping> problemUserMappingsForCandidate = problemUserMappings.stream()
                .filter(problemUserMapping -> Objects.equals(problemUserMapping.getCandidateId(), candidateId) &&
                        problemUserMapping.getStatus().equals(UserProblemStatus.SOLVED)).toList();

        return problemService.getProblemsForIds(problemUserMappingsForCandidate.stream().map(ProblemUserMapping::getProblemId).collect(Collectors.toList()));
    }
}

