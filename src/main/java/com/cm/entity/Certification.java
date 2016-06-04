package com.cm.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="certification")
public class Certification {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@NotNull
	@Column(name="percent_success")
	private Integer percent_success;
	
	@NotNull
	@Column(name="nb_question")
	private Integer nb_question;
	
	@NotNull
	@Column(name="duration")
	private Integer duration;
	
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="id_trainer")
	private Integer id_trainer;
	
	@NotNull
	@Column(name="title")
	private String title;

	@Transient
	@ManyToOne
	@JoinColumn(name="id_trainer", insertable=false, updatable=false)
	private Trainer trainer;
	
	
	@Transient
	@OneToMany(mappedBy="certification")
	private List<Question> questions;
	
	
	public Integer getPercent_success() {
		return percent_success;
	}

	public void setPercent_success(Integer percent_success) {
		this.percent_success = percent_success;
	}

	public Integer getNb_question() {
		return nb_question;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	

	

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setNb_question(Integer nb_question) {
		this.nb_question = nb_question;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId_trainer() {
		return id_trainer;
	}

	public void setId_trainer(Integer id_trainer) {
		this.id_trainer = id_trainer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Certification [id=" + id + ", percent_success=" + percent_success + ", nb_question=" + nb_question
				+ ", duration=" + duration + ", description=" + description + ", id_trainer=" + id_trainer + ", title="
				+ title + "]";
	}
	
	 
}
