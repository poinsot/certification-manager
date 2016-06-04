package com.cm.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cm.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	
	public final static String FIND_NUMBER_QUESTION_FOR_TAKING_CERTIF_QUERY = "SELECT q FROM Question q WHERE q.id_certif = ?1";
	
	@Query(FIND_NUMBER_QUESTION_FOR_TAKING_CERTIF_QUERY)
	public List<Question> findAllQuestionForACertification(@Param("1")Integer id_certif);
}
