package com.compass.payment.services;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ResourceClosedException;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compass.payment.config.connections.entities.PaymentOrder;
import com.compass.payment.config.connections.entities.PaymentStatus;
import com.compass.payment.config.connections.entities.enums.Status;
import com.compass.payment.dto.PaymentDTO;
import com.compass.payment.dto.PaymentFormDTO;
import com.compass.payment.entities.Payment;
import com.compass.payment.repositories.PaymentRepository;
import com.compass.payment.services.exceptions.DatabaseException;
import com.compass.payment.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentServiceImp implements PaymentService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PaymentRepository paymentRepository;

	@Value("${mq.queues.payment-order}")
	private String orderPayment;

	@Override
	public PaymentDTO insert(PaymentFormDTO paymentObj) {
		Payment payment = paymentRepository.save(mapper.map(paymentObj, Payment.class));
		return mapper.map(payment, PaymentDTO.class);
	}

	@Override
	public List<PaymentDTO> findAll() {
		List<Payment> listAll = paymentRepository.findAll();
		List<PaymentDTO> listDTO = listAll.stream().map(x -> new PaymentDTO(x)).collect(Collectors.toList());
		return listDTO;
	}

	@Override
	public PaymentDTO findById(Long id) {
		Payment payment = paymentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Payment id = " + id));
		return mapper.map(payment, PaymentDTO.class);
	}

	@Override
	public void delete(Long id) {
		try {
			Payment payment = paymentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Resource not found, Payment id = " + id));
			paymentRepository.delete(payment);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceClosedException("Resouce not found, Payment id = " + id);
		} catch (DatabaseException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Status statusMessageRabbit(PaymentOrder paymentOrder) {
		Payment payment = paymentRepository.findById(paymentOrder.getPayment().getId()).orElseThrow(
				() -> new ResourceNotFoundException("Payment not found, ID = " + paymentOrder.getPayment().getId()));

		Status status = Status.PROCESSING_PAYMENT;

		if (payment.getActive()) {
			if (paymentOrder.getPayment().getInstallments() > 0 && paymentOrder.getPayment().getInstallments() <= 12) {
				status = Status.PAYMENT_SUCCESSFUL;
			} else {
				status = Status.PAYMENT_NOT_INSTALLMENT;
			}
			if (paymentOrder.getPayment().getInstallments() == 0) {
				if (!payment.getInstallments()) {
					status = Status.PAYMENT_SUCCESSFUL;
				}
			} else {
				status = Status.PAYMENT_AMOUNT_NOT_AVAIABLE;
			}
			if (payment.getActive() == false) {
				status = Status.PAYMENT_INACTIVE;
			}
		} else {
			status = Status.PAYMENT_NOT_FOUND;
		}
		rabbitTemplate.convertAndSend(orderPayment, new PaymentStatus(paymentOrder.getOrderId(), status));
		return status;
	}
}
