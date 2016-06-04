package com.cm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.cm.service.RandomCodeGenerator;

@Entity
@Table(name="candidate")
public class Candidate {
	private static final int LENGHT_CODE_ACTIVATION = 64;
	
//	@Autowired
//	RandomCodeGenerator codeValidationGenerator;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	
	@NotNull
	@Size(min=1, max=100)
	@Column(name="lastname")
	private String lastname;
	
	@NotNull
	@Size(min=1, max=100)
	@Column(name="firstname")
	private String firstname;
	
	@NotNull
	@Column(name="id_card_number")
	private String id_card_number;
	
	@NotNull
	@Size(min=1, max=100)
	@Column(name="mail")
	private String mail;
	
	
	@NotNull
	@Size(min=8, max=100)
	@Column(name="pwd")
	private String pwd;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="birthdate")
	private Date birthdate;
	
	@NotNull
	@Column(name="inscription_validate")
	private Integer inscription_validate = 0; 
	
	@NotNull
	@Column(name="validation_code")
	private String validation_code = RandomCodeGenerator.generateCode(LENGHT_CODE_ACTIVATION);

	
	@Transient
	private List<Certification> listCertif;
	
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getId_card_number() {
		return id_card_number;
	}

	public void setId_card_number(String id_card_number) {
		this.id_card_number = id_card_number;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public Integer getInscription_validate() {
		return inscription_validate;
	}

	public void setInscription_validate(Integer inscription_validate) {
		this.inscription_validate = inscription_validate;
	}

	public String getValidation_code() {
		return validation_code;
	}
	
	

	public List<Certification> getListCertif() {
		return listCertif;
	}

	public void setListCertif(List<Certification> listCertif) {
		this.listCertif = listCertif;
	}
	
	

	@Override
	public String toString() {
		return "Candidate [lastname=" + lastname + ", firstname=" + firstname + ", id_card_number=" + id_card_number
				+ ", mail=" + mail + ", pwd=" + pwd + ", birthdate=" + birthdate + "]";
	}
	
	
}
