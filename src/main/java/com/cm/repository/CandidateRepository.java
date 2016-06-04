package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cm.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	public final static String FIND_BY_ADDRESS_QUERY = "SELECT c FROM Candidate c WHERE c.mail = ?1";
	public final static String FIND_BY_ID_CARD_QUERY = "SELECT c FROM Candidate c WHERE c.id_card_number = ?1";
	public final static String FIND_BY_VALIDATION_CODE_QUERY = "SELECT c FROM Candidate c WHERE c.validation_code = ?1";
	public final static String UPDATE_INSCRIPTION_VALIDATION_QUERY = "UPDATE Candidate c SET c.inscription_validate = ?1 WHERE c.validation_code = ?2";
	
	/**
	 * Find candidate by email.
	 * @param email
	 * @return Liste of candidate that match the @param
	 */
	@Query(FIND_BY_ADDRESS_QUERY)
	public Candidate findByAddress(@Param("1")String email);

	/**
	 * Find candidate by id_card_number.
	 * @param id_card_number
	 * @return Liste of candidate that match the @param
	 */
	@Query(FIND_BY_ID_CARD_QUERY)
	public Candidate findByIdCard(@Param("1")String id_card_number);

	

	/**
	 * Find candidate by activation_code.
	 * @param activation_code
	 * @return Liste of candidate that match the @param
	 */
	@Query(FIND_BY_VALIDATION_CODE_QUERY)
	public Candidate findByValidationCode(@Param("1")String activation_code);

	
	/**
	 * Update a candidate : set his inscription_validate to 1 
	 * @param validation_code
	 */
	@Modifying(clearAutomatically = true)
	@Query(UPDATE_INSCRIPTION_VALIDATION_QUERY)
	public void updateInscriptionValidateOfACandidate(@Param("1")Integer inscription_validate_to_change, @Param("2")String validation_code);

}
