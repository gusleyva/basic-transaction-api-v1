package com.basic.transaction.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.transaction.repositories.TransactionRepository;
import com.basic.transaction.repositories.entities.Transaction;
import com.basic.transaction.repositories.entities.TransactionAccumulated;
import com.basic.transaction.repositories.entities.TransactionWeeklyReport;
import com.basic.transaction.services.dto.TransactionAccumulatedDto;
import com.basic.transaction.services.dto.TransactionDto;
import com.basic.transaction.services.dto.TransactionWeeklyReportDto;
import com.basic.transaction.services.mappers.TransactionMapper;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private TransactionMapper mapper;
	
	public TransactionDto getById(String id) {
		Transaction entity = repository.findById(id).orElse(null);
		TransactionDto dto = mapper.toTransactionDto(entity);
		return dto;
	}
	
	public TransactionDto getByIdAndUserId(String id, Long userId) {
		Transaction entity = repository.findByTransactionAndUserId(id, userId);
		TransactionDto dto = mapper.toTransactionDto(entity);
		return dto;
	}
	
	public List<TransactionDto> getByUserId(Long userId) {
		List<Transaction> transactions = repository.findTransactionsByUserId(userId);
		return mapper.toTransactionDto(transactions);
	}
	
	public List<TransactionDto> getAll(){
		List<Transaction> entities = repository.findAll();
		List<TransactionDto> dtos = mapper.toTransactionDto(entities);
		return dtos;
	}
	
	public TransactionDto create(TransactionDto dto) {
		if(dto.getId() != null) {
			return null;
		}
		
		Transaction entity = mapper.toTransactionEntity(dto);
		entity.setId(getTransactionId());
		Transaction created = repository.save(entity);
		TransactionDto createdDto = mapper.toTransactionDto(created);
		return createdDto;
	}
	
	public TransactionDto update(String id, TransactionDto dto) {
		if(!repository.existsById(id) || !dto.getId().equals(id)) {
			return null;
		}
		
		Transaction entity = mapper.toTransactionEntity(dto);
		Transaction updated = repository.save(entity);
		TransactionDto updatedDto =  mapper.toTransactionDto(updated);
		return updatedDto;
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}
	
	public TransactionAccumulatedDto getAccumulatedTransactionAmountByUserId(Long userId) {
		TransactionAccumulated entity = repository.getAccumulatedTransactionAmounts(userId);
		TransactionAccumulatedDto dto = mapper.toTransactionAccumulatedDto(entity);
		return dto;
	}
	
	public List<TransactionWeeklyReportDto> getTransactionWeeklyReportByUserId(Long userId) {
		List<TransactionWeeklyReport> transactionWeeklyReport = repository.getTransactionWeeklyReport(userId);
		return mapper.toTransactionWeeklyReportDto(transactionWeeklyReport);
	}
	
	public TransactionDto getRandomTransaction() {
		List<TransactionDto> listTransactionsDtos = getAll();
		int max = listTransactionsDtos.size() - 1;
		int randomPosition = (int)(System.currentTimeMillis() % max);
		return listTransactionsDtos.get(randomPosition);
	}
	
	
	protected String getTransactionId() {
		StringBuilder transactionId = new StringBuilder();
		
		String currentDate = String.valueOf(new Date().getTime());
		transactionId.append(currentDate.substring(currentDate.length() - 8));
		
		String uuid = UUID.randomUUID().toString();
		String[] arrayUuId = uuid.split("-");
		for(String sectionUuId: arrayUuId) {
			transactionId.append("-").append(sectionUuId);
		}
		return transactionId.toString();
	}
}
