package com.cm.mail;

import static org.junit.Assert.assertEquals;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cm.AppConfig;
import com.cm.service.FakeMailSenderService;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class MailSenderServiceTest {
	
	@Rule
    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP);
	
	@Autowired
	private FakeMailSenderService mailSenderService;
	
	@Test
    public void recieveComfirmationMail() throws MessagingException {
        mailSenderService.sendConfirmationCodeCandidate("to@localhost.com", "confirmation-code");
        assertEquals("http://localhost:8080/candidate/confirm/confirmation-code", GreenMailUtil.getBody(greenMail.getReceivedMessages()[0]));
        assertEquals("Confirm your account", greenMail.getReceivedMessages()[0].getSubject());
        assertEquals("to@localhost.com", greenMail.getReceivedMessages()[0].getRecipients(RecipientType.TO)[0].toString());
    }
	
	@Test(expected=RuntimeException.class)
	public void confirmationCodeEmpty() {
		mailSenderService.sendConfirmationCodeCandidate("to@localhost.com", "");
	}
	
	@Test(expected=RuntimeException.class)
	public void invalidEmailAdress() {
		mailSenderService.sendConfirmationCodeCandidate("invalid-email-adress", "confirmation-code");
	}

}
