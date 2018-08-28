package com.caltex.service;

import java.time.LocalDateTime;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.caltex.dto.CustomerDto;
import com.caltex.dto.ResponseDto;
import com.caltex.model.Customer;
import com.caltex.model.Transaction;
import com.caltex.repository.CustomerRepository;
import com.caltex.repository.TransactionRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private JavaMailSender sender;

	ModelMapper modelMapper = new ModelMapper();
	Customer customer = new Customer();
	// Transaction transaction = new Transaction();
	ResponseDto response = new ResponseDto();
	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	public ResponseDto firstUserTransaction(CustomerDto customerDto) {
		if (customerRepository.findByCustomerEmail(customerDto.getCustomerEmail()) == null) {
			customer = modelMapper.map(customerDto, Customer.class);
			// transaction = modelMapper.map(customerDto.getTransactionDto(),
			// Transaction.class);
			Transaction transaction = new Transaction();
			transaction.setFirstTransaction(LocalDateTime.now());
			transaction.setLatestTransaction(transaction.getFirstTransaction());
			transactionRepository.save(transaction);
			customer.setTransaction(transaction);
			customerRepository.save(customer);

			MimeMessage message = sender.createMimeMessage();
			try {
				logger.info("Sending Registration Email");
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(customer.getCustomerEmail());
				helper.setText("<html><body><h2>Hi " + customer.getCustomerName()
						+ ",<br></h2><h3>Welcome to Caltex Family<b></b>!</br>Thank you for using our services on Caltex...  </br>\r\n"
						+ "<br><b></br></br>See you around</br></h3><h2>Team Caltex :: Nikita </h2><br><br><img src='cid:id101'/><body></html>",
						true);
				helper.setSubject("Welcome to Caltex...!");
				ClassPathResource file1 = new ClassPathResource("caltexImg.png");
				helper.addInline("id101", file1);
				sender.send(message);
				logger.info("Registration Email sent");

			} catch (MailException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			response.setCode(HttpStatus.OK.value());
			response.setMessage("New Customer Registered");
			response.setResponse("First Transaction Confirmed");
			logger.info("New Customer Registered and First Transaction Confirmed..!");
			return response;
		} else {
			response.setCode(HttpStatus.FORBIDDEN.value());
			response.setMessage("Customer Already Exist");
			response.setResponse("Access Denied...");
			return response;
		}
	}

	@Override
	public ResponseDto UserTransaction(CustomerDto customerDto) {
		if (customerRepository.findByCustomerEmail(customerDto.getCustomerEmail()) == null) {
			response.setCode(HttpStatus.FORBIDDEN.value());
			response.setMessage("This Customer do not Exist");
			response.setResponse("Access Denied...");
			return response;
		} else {
			customer = customerRepository.findByCustomerEmail(customerDto.getCustomerEmail());
			Transaction transaction = new Transaction();
			transaction = customer.getTransaction();
			transaction.setLatestTransaction(LocalDateTime.now());
			transaction.setTransactionType("Simple");
			transaction.setTotalTransactions(transaction.getTotalTransactions() + 1);
			transactionRepository.save(transaction);
			customer.setCustomerType("Active");
			customerRepository.save(customer);
			MimeMessage message = sender.createMimeMessage();
			try {
				logger.info("Sending Email");
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setTo(customer.getCustomerEmail());
				helper.setText("<html><body><h2>Hi " + customer.getCustomerName()
						+ ",<br></h2><h3>Welcome Back..!<b></b>!</br>Thank you for using our services on Caltex...  </br>\r\n"
						+ "<br><b></br></br>See you around</br></h3><h2>Team Caltex :: Nikita </h2><br><br><img src='cid:id101'/><body></html>",
						true);
				helper.setSubject("Welcome back to Caltex...!");
				ClassPathResource file1 = new ClassPathResource("caltexImg.png");
				helper.addInline("id101", file1);
				sender.send(message);
				logger.info("Thank you Email sent");

			} catch (MailException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			response.setCode(HttpStatus.OK.value());
			response.setMessage("Thank you for using our services..!");
			response.setResponse("Transaction Confirmed");
			logger.info("Transaction Confirmed");
			return response;
		}
	}

}
