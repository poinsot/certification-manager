package com.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cm.entity.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Integer>{

}
