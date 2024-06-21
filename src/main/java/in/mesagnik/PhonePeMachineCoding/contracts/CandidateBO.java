package in.mesagnik.PhonePeMachineCoding.contracts;

import in.mesagnik.PhonePeMachineCoding.model.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateBO {
    private String id;
    private String name;
    private String email;
    private String password;
    private Float score;
    private Department department;
}
