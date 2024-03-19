package com.eyalmiz.transaction.controller;

import com.eyalmiz.transaction.Model.Transaction;
import com.eyalmiz.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("/")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return service.saveTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> findAllTransactions() {
        return service.getTransactions();
    }

    @GetMapping("{id}")
    public Transaction findTransactionById(@PathVariable int id) {
        return service.getTransactionById(id);
    }

    @PutMapping
    public Transaction updateTransaction(@RequestBody Transaction transaction) {
        return service.updateTransaction(transaction);
    }

    @DeleteMapping("{id}")
    public String deleteTransaction(@PathVariable int id) {
        return service.deleteTransaction(id);
    }

    @GetMapping("/getPositiveAmount")
    public List<Transaction> getPositiveAmount() {
        return service.getPositiveAmount();
    }

    @GetMapping("/getNegativeAmount")
    public List<Transaction> getNegativeAmount() {
        return service.getNegativeAmount();
    }

    @GetMapping("/getFromAmount")
    public List<Transaction> getFromAmount(@RequestParam Double number) {
        return service.getFromAmount(number);
    }

    @GetMapping("/searchDate")
    public List<Transaction> searchDate(@RequestParam("fromDate")
                                            @DateTimeFormat(pattern = "dd.MM.yyyy") Date fromDate,
                                        @RequestParam("untilDate")
                                        @DateTimeFormat(pattern = "dd.MM.yyyy") Date untilDate) {
        return service.searchDate(fromDate, untilDate);
    }
    @GetMapping("/sumAmounts")
    public Double sumAmounts() {
        return service.sumAmounts();
    }

}
