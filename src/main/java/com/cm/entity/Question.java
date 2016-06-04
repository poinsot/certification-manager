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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="question")
public class Question {

	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="text")
	private String text;
	
	@NotNull
	@Column(name="id_certif")
	private Integer id_certif;
	
	@OneToMany(mappedBy="question")
	private Set<Response> responses;
	@JsonIgnore
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
	
	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Question [text=" + text + ", responses=" + responses + ", id_certif=" + id_certif + "]";
	}

	
	
	
}
