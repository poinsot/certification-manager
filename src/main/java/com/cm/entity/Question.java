package com.cm.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="question")
public class Question {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="text")
	private String text;
	
	@OneToMany(mappedBy="question")
	private Set<Response> responses;
	
	@NotNull
	@Column(name="id_certif")
	private Integer id_certif;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_certif", insertable=false, updatable=false)
	private Certification certification;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<Response> getResponses() {
		return responses;
	}

	public void setResponses(Set<Response> responses) {
		this.responses = responses;
	}

	public Integer getId_certif() {
		return id_certif;
	}

	public void setId_certif(Integer id_certif) {
		this.id_certif = id_certif;
	}

	public Certification getCertification() {
		return certification;
	}

	public void setCertification(Certification certification) {
		this.certification = certification;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
}
