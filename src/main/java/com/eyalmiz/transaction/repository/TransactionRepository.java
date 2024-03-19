package com.eyalmiz.transaction.repository;

import com.eyalmiz.transaction.Model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactionList;
    public static int id = 0;
    public TransactionRepository(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getAllTransactions() {
        return transactionList;
    }

    public Transaction findById(int id) {
        for (Transaction transaction : transactionList) {
            if (transaction.getId() == (id)) {
                return transaction;
            }
        }
        return null;
    }

    public Transaction save(Transaction transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(id);
        id++;
        newTransaction.setType(transaction.getType());
        newTransaction.setDate(transaction.getDate());
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setDescription(transaction.getDescription());
        transactionList.add(newTransaction);
        return newTransaction;
    }

    public Boolean delete(Integer id) {
        return transactionList.removeIf(x -> x.getId().equals(id));
    }

    public Transaction update(Transaction transaction) {
        int idx = 0;
        int id = 0;
        for (int i = 0; i < transactionList.size(); i++) {
            if (Objects.equals(transactionList.get(i).getId(), transaction.getId())) {
                id = transaction.getId();
                idx = i;
                break;
            }
        }

        Transaction transaction1 = new Transaction();
        transaction1.setId(id);
        transaction1.setAmount(transaction.getAmount());
        transaction1.setType(transaction.getType());
        transaction1.setDescription(transaction.getDescription());
        transactionList.set(idx, transaction1);
        return transaction1;
    }





}
