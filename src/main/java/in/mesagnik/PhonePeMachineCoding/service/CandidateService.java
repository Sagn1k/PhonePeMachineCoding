package in.mesagnik.PhonePeMachineCoding.service;

import in.mesagnik.PhonePeMachineCoding.contracts.CandidateBO;
import in.mesagnik.PhonePeMachineCoding.contracts.CandidateDTO;
import in.mesagnik.PhonePeMachineCoding.dao.CandidateDao;
import in.mesagnik.PhonePeMachineCoding.dao.CandidateDaoImpl;
import in.mesagnik.PhonePeMachineCoding.exceptions.InvalidInputException;

public class CandidateService {
    private final CandidateDao candidateDao;

    public CandidateService() {
        this.candidateDao = new CandidateDaoImpl();
    }

    public String registerCandidate(CandidateDTO candidateDTO) throws Exception {
        validateInput(candidateDTO);

        CandidateBO candidateBO = CandidateBO.builder()
                .email(candidateDTO.getEmail())
                .department(candidateDTO.getDepartment())
                .password(candidateDTO.getPassword())
                .name(candidateDTO.getName())
                .build();

        String id = candidateDao.save(candidateBO);
        System.out.println("Candidate saved with id " + id);
        return id;
    }

    private void validateInput(CandidateDTO candidateDTO) throws InvalidInputException {
        if (candidateDTO.getEmail() == null)
            throw new InvalidInputException("Email is required to register a candidate");
        //more such checks like valid email, nulls

    }

    public CandidateDTO getLeader() {
        CandidateBO candidateBO = candidateDao.getCandidateForTopScore();
        return CandidateDTO.builder()
                .id(candidateBO.getId())
                .email(candidateBO.getEmail())
                .department(candidateBO.getDepartment())
                .score(candidateBO.getScore())
                .name(candidateBO.getName())
                .build();
    }

    public void updateCandidateScore(String candidateId, float finalScore) {
        CandidateBO candidateBO = candidateDao.getCandidateById(candidateId);
        if (candidateBO.getScore() != null) {
            candidateBO.setScore(candidateBO.getScore() + finalScore);
        } else {
            candidateBO.setScore(finalScore);
        }

        candidateDao.updateCandidateScore(candidateBO);
    }
}

