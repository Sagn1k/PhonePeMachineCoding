package in.mesagnik.PhonePeMachineCoding.dao;

import in.mesagnik.PhonePeMachineCoding.contracts.CandidateBO;

public interface CandidateDao {
    String save(CandidateBO candidateBO);

    CandidateBO getCandidateById(String candidateId);

    void updateCandidateScore(CandidateBO candidateBO);

    CandidateBO getCandidateForTopScore();
}
