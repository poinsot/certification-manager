package com.cm.service;

import java.util.Properties;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.cm.exception.ConfirmationCodeEmptyException;
import com.cm.exception.InvalidEmailAdressException;
import com.cm.exception.UnauthorizedEntityException;

/**
 * Certification Manager Email Sender, this service is used to send e-mail.
 * @author Ronan
 *
 */
@Service
@PropertySource("classpath:gmail.properties")
public class MailSenderService {
	private static final String EMAIL_SUBJECT = "Confirm your account";
	private static final String CANDIDATE_CONFIRM_PATH = "http://localhost:8080/candidate/confirm/";
	private static final String TRAINER_CONFIRM_PATH = "http://localhost:8080/trainer/confirm/";
	private static final String SENDER_EMAIL_ADRESS = "certificationmanager2016@gmail.com";
//	private static final Logger LOGGER = Logger.getLogger(MailSenderService.class.getName());
	
	@Value("${host}")
    private String host;

    @Value("${port}")
    private Integer port;
    
    @Value("${gmail.password}")
    private String password;
    
    @Value("${gmail.username}")
    private String username;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setPassword(password);
        javaMailSender.setUsername(username);

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "false");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "false");
        return properties;
    }
	
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * Send a confirmation code to new users by e-mail.
	 * @param to, new user e-mail
	 * @param confirmationCode, unique confirmation code
	 * @param entity, a string representing the entity, Trainer or Candidate
	 * @throws InvalidEmailAdressException if to is not a well-formed e-mail adress
	 * @throws ConfirmationCodeEmptyException if confirmationCode is empty
	 */
	public void sendConfirmationCode(String to, String confirmationCode, String entity) throws InvalidEmailAdressException {
		EmailValidator emailValidator = new EmailValidator();
		if (! emailValidator.isValid(to, null)) {
			throw new InvalidEmailAdressException("to is not a well-formed e-mail adress");
		}
		if (confirmationCode.length() < 1) {
			throw new ConfirmationCodeEmptyException("confirmation code length must be > 1");
		}
		if (! ((entity.equals("Trainer") || entity.equals("Candidate")))) {
			throw new UnauthorizedEntityException("entity should be Trainer or Candidate");
		}
		StringBuilder body = new StringBuilder();
		if (entity.equals("Trainer")) {
			body.append(TRAINER_CONFIRM_PATH);
		}
		if (entity.equals("Candidate")) {
			body.append(CANDIDATE_CONFIRM_PATH);
		}
		body.append(confirmationCode);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(EMAIL_SUBJECT);
		message.setText(body.toString());
		message.setFrom(SENDER_EMAIL_ADRESS);
		mailSender.send(message);
	}
}
