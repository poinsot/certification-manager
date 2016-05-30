package com.cm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.cm.service.RandomCodeGenerator;

@Entity
@Table(name="trainer")
@Component
public class Trainer {
	private static final int LENGHT_CODE_ACTIVATION = 64;
    	
	// ATTRIBUTES
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
    
    // GETTERS AND SETTERS

	public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Integer getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getPwd() {
        return pwd;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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
	
	// TODO To be removed
    @Override
    public String toString() {
        return ""
        		+ "Trainer[id=" + id + ", lastname=" + lastname + ", firstname=" + firstname +
        		", mail=" + mail + ", pwd=" + pwd + ", validation_code="+ validation_code + "]";
    }
    
    
}