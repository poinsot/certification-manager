package com.cm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cm.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	public final static String FIND_BY_ADDRESS_QUERY = "SELECT c FROM Candidate c WHERE c.mail = ?1";
	public final static String FIND_BY_ID_CARD_QUERY = "SELECT c FROM Candidate c WHERE c.id_card_number = ?1";
	
	/**
	 * Find persons by email.
	 */
	@Query(FIND_BY_ADDRESS_QUERY)
	public List<Candidate> findByAddress(@Param("1")String email);

	/**
	 * Find persons by id_card_number.
	 */
	@Query(FIND_BY_ID_CARD_QUERY)
	public List<Candidate> findByIdCard(@Param("1")String id_card_number);

}
