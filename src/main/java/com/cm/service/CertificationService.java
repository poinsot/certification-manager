package com.cm.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Service;

import com.cm.CertificationManagerApplication;
import com.cm.entity.Certification;
import com.cm.exception.TrainerNotFoundException;
import com.cm.repository.CertificationRepository;
import com.cm.repository.TrainerRepository;

@Service
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
public class CertificationService {
	
	private static final Logger LOGGER = Logger.getLogger(CertificationService.class.getName());

	@Autowired
	CertificationRepository certificationRepository;
	
	@Autowired
	TrainerRepository trainerRepository;

	@Transactional
	public Certification createCertification(Certification certification) {
		if(certification == null){
			throw new IllegalArgumentException("certification is null");
		}
		LOGGER.info(certification.toString());
		
		if(certification.getId_trainer() == null){
			throw new IllegalArgumentException("id_trainer of certification is null");
		}
		if(certification.getTitle() == null){
			throw new IllegalArgumentException("Title of certification is null");
		}
		if(certification.getDuration() == null){
			throw new IllegalArgumentException("Duration of certification is null");
		}
		if(certification.getNb_question() == null){
			throw new IllegalArgumentException("Nb_question of certification is null");
		}
		if(certification.getPercent_success() == null){
			throw new IllegalArgumentException("Percent_success of certification is null");
		}
		
		if(trainerRepository.findOne(certification.getId_trainer()) == null){
			throw new TrainerNotFoundException();
		}
		return certificationRepository.save(certification);
	}
	
	@Transactional
	public Certification findCertification(String id){
		if(id == null){
			throw new IllegalArgumentException("id is null");
		}
		Integer idNumber;
		idNumber = Integer.valueOf(id);
		return certificationRepository.findOne(idNumber);
	}
	
	@Transactional
	public List<Certification> findAllCertifications(){
		return certificationRepository.findAll();
	}
}
