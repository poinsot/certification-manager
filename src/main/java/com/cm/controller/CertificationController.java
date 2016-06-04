package com.cm.controller;

import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cm.entity.Candidate;
import com.cm.entity.Certification;
import com.cm.exception.CandidateNotFoundException;
import com.cm.exception.CertificationNotFoundException;
import com.cm.service.CandidateService;
import com.cm.service.CertificationService;
import com.cm.service.QuestionService;

@Controller
@RequestMapping(path="candidate/{id}")
public class CertificationController {
	
	private static final Logger LOGGER = Logger.getLogger(CertificationController.class.getName());

	@Autowired
	CertificationService certificationService;
	@Autowired
	CandidateService candidateService;
	@Autowired
	QuestionService questionService;
	
	
	Candidate candidate;
	Certification certification;
	
	@RequestMapping(path="/take/{id_certif}", 
			method={RequestMethod.GET})
	public String certificationDescription(@PathVariable String id, @PathVariable String id_certif, Model model, HttpServletRequest req){
		if(!verifId(id, req)){
			return "redirect:/";
		}
		candidate = candidateService.findById(id);
		certification = certificationService.findCertification(id_certif);
		if(certification == null){
			throw new CertificationNotFoundException();
		}
		model.addAttribute("certification", certification);
		model.addAttribute("candidate", candidate);
		return "certificationDescription";
	}
	
	
	@RequestMapping(path="/take/{id_certif}/takingCertif", 
			method={RequestMethod.GET})
	public String takingCertif(@PathVariable String id, @PathVariable String id_certif, Model model){
//		Certification certification = certificationService.findCertification(id_certif);
//		Candidate candidate = candidateService.findById(id);
		if(candidate == null)
			throw new CandidateNotFoundException();
		if(certification == null){
			throw new CertificationNotFoundException();
		}
		
		return "takingCertif";
	}

	private boolean verifId(String id, HttpServletRequest req) {
		Candidate temp = candidateService.findById(id);
		if(temp == null){
			throw new CandidateNotFoundException();
		}
		if(temp.getInscription_validate() == 0)
			return false;
		Cookie[] listCookie = req.getCookies();
		for (Cookie cookie : listCookie) {
			if(cookie.getName().equals("CandidateLogged") && !cookie.getValue().equals(temp.getMail())){
				return false;
			}
		}
		return true;
		
	}

}
