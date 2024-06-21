package in.mesagnik.PhonePeMachineCoding.strategy;

import in.mesagnik.PhonePeMachineCoding.model.Problem;

/**
 * This will introduce polymorphism.
 * So that we can include multiple strategies here and we can maintain their individual implementations.
 * In this way it becomes extensible for adding more strategies.
 */
public interface ScoreStrategy {
    Float calculateScore(Problem problem, float timeTaken);
}