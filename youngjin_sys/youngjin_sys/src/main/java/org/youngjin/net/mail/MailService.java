package org.youngjin.net.mail;

import java.io.Serializable;

public interface MailService extends Serializable {
	void sendMail();
	void sendMail(String subject, String text, String fromUser, String toUser, String[]toCC);
}
