package in.mesagnik.PhonePeMachineCoding.model;

import in.mesagnik.PhonePeMachineCoding.model.enums.DifficultyLevel;
import in.mesagnik.PhonePeMachineCoding.model.enums.ProblemTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Problem {
    private String id;
    private String name;
    private String description;
    private int score;
    private List<ProblemTag> tags;
    private DifficultyLevel difficultyLevel;
    private int numberOfLikes;
    private int numberOfUsersSolved;
    private float avgTimeTaken;
    private TimeUnit avgTimeTakenUnit;

    // more attributes like solution, testcases, etc
}