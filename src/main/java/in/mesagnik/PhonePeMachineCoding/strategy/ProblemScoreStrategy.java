package in.mesagnik.PhonePeMachineCoding.strategy;

import in.mesagnik.PhonePeMachineCoding.model.Problem;

public class ProblemScoreStrategy implements ScoreStrategy {
    @Override
    public Float calculateScore(Problem problem, float timeTaken) {
        int baseScore = problem.getScore();
        return (float) baseScore;
    }
}

