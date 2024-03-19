package com.eyalmiz.transaction.service;

import com.eyalmiz.transaction.Model.Transaction;
import com.eyalmiz.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public List<Transaction> getTransactions() {
        return repository.getAllTransactions();
    }

    public Transaction getTransactionById(int id) {
        return repository.findById(id);
    }

    public String deleteTransaction(int id) {
        if (repository.delete(id)) {
            return "Transaction removed !! " + id;
        } else {
            return "notDelete";
        }
    }

    public Transaction updateTransaction(Transaction transaction) {
        return repository.update(transaction);
    }

    public List<Transaction> getFromAmount(Double number) {
        return getTransactions().stream().filter(transaction -> transaction.getAmount() >= number).collect(Collectors.toList());
    }

    private List<Transaction> getUntilAmount(Double number) {
        return getTransactions().stream().filter(transaction -> transaction.getAmount() <= number).collect(Collectors.toList());
    }

    public List<Transaction> searchDate(Date fromDate, Date untilDate) {
        System.out.println(fromDate + " " + untilDate);
        return getTransactions().stream().filter(transaction -> {
                    Date date = transaction.getDate();
                    return (date.after(fromDate) && date.before(untilDate)) || (date.equals(fromDate) || date.equals(untilDate));
                }
        ).collect(Collectors.toList());
    }

    public List<Transaction> getPositiveAmount() {
        return getFromAmount(0.0);
    }

    public List<Transaction> getNegativeAmount() {
        return getUntilAmount(0.0);
    }

    public double sumAmounts() {
        return getTransactions().stream().mapToDouble(Transaction::getAmount).sum();
    }
}
