package in.mesagnik.PhonePeMachineCoding.model;

import in.mesagnik.PhonePeMachineCoding.model.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    private String id;
    private String name;
    private String email;
    private String password;
    private float score;
    private Department department;

}