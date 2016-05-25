package com.cm.service;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.junit.Rule;
import org.springframework.stereotype.Service;

import com.cm.exception.ConfirmationCodeEmptyException;
import com.cm.exception.InvalidEmailAdressException;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

/**
 * Certification Manager Email Sender, this service is used to send e-mail.
 * @author Ronan
 *
 */
@Service
public class FakeMailSenderService {
	private static final String EMAIL_SUBJECT = "Confirm your account";
	private static final String CANDIDATE_CONFIRM_PATH = "http://localhost:8080/candidate/confirm/";
	private static final String SENDER_EMAIL_ADRESS = "from@localhost.com";
	
	@Rule
    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP);

	/**
	 * Send a confirmation code to new users by e-mail.
	 * @param to, new user e-mail
	 * @param confirmationCode, unique confirmation code
	 * @throws InvalidEmailAdressException  if to is not a well-formed e-mail adress
	 * @throws ConfirmationCodeEmptyException if confirmationCode is empty
	 */
	public void sendConfirmationCodeCandidate(String to, String confirmationCode) throws InvalidEmailAdressException {
		EmailValidator emailValidator = new EmailValidator();
		if (! emailValidator.isValid(to, null)) {
			throw new InvalidEmailAdressException("to is not a well-formed e-mail adress");
		}
		if (confirmationCode.length() < 1) {
			throw new ConfirmationCodeEmptyException("confirmation code length must be > 1");
		}
		StringBuilder msg = new StringBuilder();
		msg.append(CANDIDATE_CONFIRM_PATH);
		msg.append(confirmationCode);
		GreenMailUtil.sendTextEmail(to, SENDER_EMAIL_ADRESS, EMAIL_SUBJECT, msg.toString(), ServerSetupTest.SMTP);
	}
}
