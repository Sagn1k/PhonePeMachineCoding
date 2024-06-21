package in.mesagnik.PhonePeMachineCoding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contest {
    private String id;
    private List<Problem> problemList;
}
