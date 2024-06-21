package in.mesagnik.PhonePeMachineCoding.contracts;

import in.mesagnik.PhonePeMachineCoding.model.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {
    private String id;
    private String name;
    private String email;
    private String password;
    private Department department;
    private Float score;
}
