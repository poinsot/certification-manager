package com.cm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="responses")
public class Response {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="text")
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_question", insertable=false, updatable=false)
	private Question question;
	
	@NotNull
	@Column(name="id_question")
	private Integer id_question;
	
	@NotNull
	@Column(name="is_correct")
	private Integer is_correct;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getId_question() {
		return id_question;
	}

	public void setId_question(Integer id_question) {
		this.id_question = id_question;
	}

	

	public Integer getIs_correct() {
		return is_correct;
	}

	public void setIs_correct(Integer is_correct) {
		this.is_correct = is_correct;
	}

	public void setIsCorrect(Integer isCorrect) {
		this.is_correct = isCorrect;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Response [text=" + text + ", id_question=" + id_question + ", is_correct=" + is_correct + "]";
	}
	
	
	
	
}
