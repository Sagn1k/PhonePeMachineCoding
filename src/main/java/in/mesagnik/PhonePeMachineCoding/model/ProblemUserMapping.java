package in.mesagnik.PhonePeMachineCoding.model;

import in.mesagnik.PhonePeMachineCoding.model.enums.UserProblemStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemUserMapping {
    private String id;
    private String problemId;
    private String candidateId;
    private Float timeToSolve;
    private Float score;
    private UserProblemStatus status;
    // can add more attributes like attemptedSolutions, etc
}
