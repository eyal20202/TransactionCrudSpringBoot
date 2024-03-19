package com.eyalmiz.transaction.cach;

import com.eyalmiz.transaction.Enum.TransactionTypeEnum;
import com.eyalmiz.transaction.Model.Transaction;
import com.eyalmiz.transaction.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class LoadCache {
    @Autowired
    public TransactionRepository transactionRepository;

    @PostConstruct
    public void loadData() throws IOException {
        readFile();
        transactionRepository.getAllTransactions().forEach(System.out::println);
    }

    public void readFile() throws IOException, RuntimeException {




        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("/static/transactions.csv").getInputStream()))) {
            String line = br.readLine();
            if (line == null) throw new IllegalArgumentException("File is empty");
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            while ((line = br.readLine()) != null) {
                String[] items = line.split(",");
                try {
                    if (items.length > 5) throw new ArrayIndexOutOfBoundsException();
                    Transaction transaction = new Transaction();
                    transaction.setDescription(items[2]);
                    transaction.setAmount(Double.parseDouble(items[1]));
                    transaction.setType(items[0].equalsIgnoreCase("credit") ? TransactionTypeEnum.CREDIT : TransactionTypeEnum.DEBIT);
                    Date date = formatter.parse(items[3]);
                    transaction.setDate(date);
                    transactionRepository.save(transaction);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException |
                         ParseException e) {
                    // Caught errors indicate a problem with data format -> Print warning and continue
                    System.out.println("Invalid line: " + line);
                }
            }
        }
    }
}
