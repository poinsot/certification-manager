package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.entity.Certification;
import com.cm.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
