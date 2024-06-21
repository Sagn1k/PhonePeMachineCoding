package in.mesagnik.PhonePeMachineCoding.dao;

import in.mesagnik.PhonePeMachineCoding.contracts.CandidateBO;
import in.mesagnik.PhonePeMachineCoding.exceptions.InvalidInputException;
import in.mesagnik.PhonePeMachineCoding.model.Candidate;
import org.apache.commons.lang3.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CandidateDaoImpl implements CandidateDao {
    private static Map<String, Candidate> candidateStore = new HashMap<>();

    @Override
    public String save(CandidateBO candidateBO) {
        Candidate candidate = Candidate.builder()
                .id(UUID.randomUUID().toString())
                .name(candidateBO.getName())
                .email(candidateBO.getEmail())
                .department(candidateBO.getDepartment())
                .password(candidateBO.getPassword())
                .build();
        candidateStore.put(candidate.getId(), candidate);
        return candidate.getId();
    }

    @Override
    public CandidateBO getCandidateById(String candidateId) {
        Candidate candidate = candidateStore.getOrDefault(candidateId, null);

        if (ObjectUtils.isNotEmpty(candidate)) {
            return CandidateBO.builder()
                    .id(candidateId)
                    .name(candidate.getName())
                    .email(candidate.getEmail())
                    .password(candidate.getPassword())
                    .department(candidate.getDepartment())
                    .score(candidate.getScore())
                    .build();
        } else throw new InvalidInputException("Candidate do not exist for the given candidateId");
    }

    @Override
    public void updateCandidateScore(CandidateBO candidateBO) {
        Candidate candidate = candidateStore.get(candidateBO.getId());
        candidate.setScore(candidate.getScore());
        candidateStore.put(candidate.getId(), candidate);
    }

    @Override
    public CandidateBO getCandidateForTopScore() {
        List<Candidate> candidateList = candidateStore.values().stream().collect(Collectors.toList());
        Candidate candidate = candidateList.stream()
                .max(Comparator.comparingDouble(Candidate::getScore))
                .orElse(null);
        if (candidate != null)
            return CandidateBO.builder()
                    .id(candidate.getId())
                    .name(candidate.getName())
                    .email(candidate.getEmail())
                    .password(candidate.getPassword())
                    .department(candidate.getDepartment())
                    .score(candidate.getScore())
                    .build();
        else
            return null;

    }


}
