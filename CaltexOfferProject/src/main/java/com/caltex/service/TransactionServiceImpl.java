package com.caltex.service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.caltex.model.Customer;
import com.caltex.model.Transaction;
import com.caltex.repository.CustomerRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private JavaMailSender sender;

	Transaction transaction = new Transaction();
	Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);

		return cal.getTime();
	}

	@Scheduled(cron = "1 * * * * *")
	public void scheduledTask() {
		logger.info("Starting a cron scheduler..!");
		List<Customer> customerList = customerRepository.findAll();

		for (Customer customer : customerList) {
			transaction = customer.getTransaction();

			LocalDateTime now = LocalDateTime.now();
			LocalDateTime twoMinBehind = now.minusMinutes(2);
			LocalDateTime latest = transaction.getLatestTransaction();


			if (twoMinBehind.compareTo(latest) > 0) {
				if (transaction.getTransactionType().equals("Simple")) {
					transaction.setTransactionType("Offered");
					mailSending(customer);
					logger.info("12 days gone... Sending offer...");

				} else if (transaction.getTransactionType().equals("Offered")) {
					transaction.setTransactionType("Alerted");
					logger.info("12 more days gone after offer... Sending Alert for expiration of Offer...");
					mailSending(customer);
				} else {
					transaction.setTransactionType("Critical");
					customer.setCustomerType("InActive");
					logger.info("No Transaction made.. Setting this user as InActive");
				}
			}

		}
		logger.info("Scheduling work completed");

	}

	public void mailSending(Customer customer) {
		MimeMessage message = sender.createMimeMessage();
		try {
			logger.info("Sending Email of Offer");
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(customer.getCustomerEmail());
			if (customer.getTransaction().getTransactionType().equals("Offered")) {
				helper.setText("<html><body><h2>Hi " + customer.getCustomerName()
						+ ",</h2><br>We have been missing you...<br> We are giving you an offer of 40% cashback on your next transaction.</br>See you around</br></h3><h2>Team Caltex :: Nikita </h2><br><br><img src='cid:id101'/><body></html>",
						true);
			} else if (customer.getTransaction().getTransactionType().equals("Alerted")) {
				helper.setText("<html><body><h2>Hi " + customer.getCustomerName()
						+ ",</h2><br>We have been missing you...<br> Our offer of 40% cashback will expire in few days. Avail the benefit now. </br>See you around</br></h3><h2>Team Caltex :: Nikita </h2><br><br><img src='cid:id101'/><body></html>",
						true);
			}
			helper.setSubject("Urgent: Caltex Services [Limited Period Offer]");
			ClassPathResource file1 = new ClassPathResource("caltexImg.png");
			helper.addInline("id101", file1);
			sender.send(message);
			logger.info("Offer Email sent");

		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
