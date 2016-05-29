package com.cm.mail;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import com.cm.CertificationManagerApplication;
import com.cm.exception.ConfirmationCodeEmptyException;
import com.cm.exception.InvalidEmailAdressException;
import com.cm.exception.UnauthorizedEntityException;
import com.cm.service.MailSenderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=CertificationManagerApplication.class)
public class SendReceiveMailTest {
	private static final Logger LOGGER = Logger.getLogger(SendReceiveMailTest.class.getName());

	@Autowired
	private MailSenderService mss;

	@Test
	public void sendComfirmationMail() {
		LOGGER.info("BEFORE sending confirmation code to trainer");
		mss.sendConfirmationCode("likilo@mswork.ru", "trainer-code", "Trainer");
		LOGGER.info("BEFORE sending confirmation code to candidate");
		mss.sendConfirmationCode("likilo@mswork.ru", "candidate-code", "Candidate");
	}

	@Test
	public void sendReceivedTrainer() throws InterruptedException {
		mss.sendConfirmationCode("trainer-cm@mswork.ru", "trainer-code", "Trainer");
		Thread.sleep(10000);
		TempMail receivedMails = new TempMail("trainer-cm@mswork.ru");
		LOGGER.info(String.format("receivedMails.getLastMailSubject() %s", receivedMails.getLastMailSubject()));
		assertEquals("Confirm your account", receivedMails.getLastMailSubject());
		LOGGER.info(String.format("receivedMails.getLastMailSender() %s", receivedMails.getLastMailSender()));
		assertEquals("certificationmanager2016@gmail.com", receivedMails.getLastMailSender());
		assertEquals("http://localhost:8080/trainer/confirm/trainer-code", receivedMails.getLastMailBody().substring(0, 50));
	}
	
	@Test
	public void sendReceivedCandidate() throws InterruptedException {
		mss.sendConfirmationCode("candidate-cm@mswork.ru", "candidate-code", "Candidate");
		Thread.sleep(10000);
		TempMail receivedMails = new TempMail("candidate-cm@mswork.ru");
		assertEquals("Confirm your account", receivedMails.getLastMailSubject());
		assertEquals("certificationmanager2016@gmail.com", receivedMails.getLastMailSender());
		assertEquals("http://localhost:8080/candidate/confirm/candidate-code", receivedMails.getLastMailBody().substring(0, 54));
	}

	@Test(expected=ConfirmationCodeEmptyException.class)
	public void confirmationCodeEmpty() {
		mss.sendConfirmationCode("to@localhost.com", "", "Candidate");
	}

	@Test(expected=InvalidEmailAdressException.class)
	public void invalidEmailAdress() {
		mss.sendConfirmationCode("invalid-email-adress", "confirmation-code", "Trainer");
	}

	@Test(expected=UnauthorizedEntityException.class)
	public void invalidEntity() {
		mss.sendConfirmationCode("to@localhost.com", "confirmation-code", Object.class.getName());
	}

	private class TempMail {
		private String md5;
		private String urlJSON;
		private JSONArray mails;

		public TempMail(String tempMailAdress) {
			super();
			this.md5 = DigestUtils.md5DigestAsHex(tempMailAdress.getBytes());
			LOGGER.info(String.format("md5: %s", md5));
			LOGGER.info(tempMailAdress);
			this.urlJSON = new StringBuilder("http://api.temp-mail.ru/request/mail/id/")
					.append(this.md5)
					.append("/format/json/")
					.toString();
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(urlJSON, HttpMethod.GET, null, String.class);

			this.mails = new JSONArray(response.getBody());
		}

		public JSONObject getLastMail() {
			return new JSONObject(mails.get(0).toString());
		}

		public String getLastMailSubject() {
			return getLastMail().get("mail_subject").toString();
		}

		public String getLastMailSender() {
			return getLastMail().get("mail_from").toString();
		}

		public String getLastMailBody() {
			return getLastMail().get("mail_text_only").toString();
		}
	}

}