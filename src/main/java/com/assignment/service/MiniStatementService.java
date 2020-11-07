package com.assignment.service;

import com.assignment.model.Account;
import com.assignment.model.Statement;
import com.assignment.model.StatementCriteria;
import com.assignment.model.StatementsResult;
import com.assignment.repo.AccountRepository;
import com.assignment.repo.StatementRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MiniStatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private AccountRepository accountRepository;

    Logger logger  =LoggerFactory.getLogger(MiniStatementService.class);

    public StatementsResult getStatementsOnAccount(Long accountId){

        StatementsResult statementsResult = new StatementsResult();
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()){
            statementsResult.setAccount(optionalAccount.get());
            statementsResult.setStatements(statementRepository.findByAccountId(accountId));
        }
        statementsResult.getAccount().setStatements(null);
        return statementsResult;
    }

    public StatementsResult getStatementOnCriteria(StatementCriteria statementCriteria) {
        StatementsResult statementsResult = getStatementsOnStatementCriteria(statementCriteria);
        statementsResult.getAccount().setStatements(null);
        return statementsResult;
    }

    private StatementsResult getStatementsOnStatementCriteria(StatementCriteria statementCriteria){

        try {
            StatementsResult statementsResult = new StatementsResult();
            if (statementCriteria.getAccountId() == null) {
                throw new RuntimeException("Invalid request without accountId system cannot fetch statements");
            }

            Optional<Account> optionalAccount = accountRepository.findById(statementCriteria.getAccountId());
            if (!optionalAccount.isPresent()) {
                throw new RuntimeException("Invalid accountId has provided " + statementCriteria.getAccountId());
            }

            statementsResult.setAccount(optionalAccount.get());
            if (StringUtils.isNotEmpty(statementCriteria.getFromDate()) && StringUtils.isNotEmpty(statementCriteria.getToDate())) {
                Date fromDate = getDateFromRequestString(statementCriteria.getFromDate());
                Date toDate = getDateFromRequestString(statementCriteria.getToDate());
                logger.info("date range based :: filtered statements size : {} {} ", fromDate,toDate);

                if (toDate.getTime() >= fromDate.getTime()) {
                    List<Statement> statements = statementRepository.findByAccountId(optionalAccount.get().getId());
                    List<Statement> filteredStatements = new ArrayList<>();
                    for (Statement statement : statements) {
                        if (fromDate.compareTo(getDateFromDatabaseString(statement.getDate())) <= 0 &&
                                toDate.compareTo(getDateFromDatabaseString(statement.getDate())) >= 0) {
                            filteredStatements.add(statement);
                        }
                    }
                    statementsResult.setStatements(filteredStatements);
                    logger.info("date range based :: filtered statements size : {}", filteredStatements.size());
                    return statementsResult;
                }
                throwRuntimeException();
            }

            if (StringUtils.isNotEmpty(statementCriteria.getFromAmount()) && StringUtils.isNotEmpty(statementCriteria.getToAmount())) {
                if (Long.parseLong(statementCriteria.getFromAmount()) < Long.parseLong(statementCriteria.getToAmount())) {
                    List<Statement> statements = statementRepository.findByAccountId(optionalAccount.get().getId());
                    List<Statement> filteredStatements = statements.stream().filter(statement ->
                            (Long.parseLong(statementCriteria.getFromAmount()) <= Long.parseLong(statement.getAmount()))
                                    &&
                                    (Long.parseLong(statementCriteria.getToAmount()) >= Long.parseLong(statement.getAmount()))
                    ).collect(Collectors.toList());

                    logger.info("amount range based :: filtered statements size : {}", filteredStatements.size());
                    statementsResult.setStatements(filteredStatements);
                    return statementsResult;
                }
                throwRuntimeException();
            }

            return getStatementsOnAccount(optionalAccount.get().getId());

        } catch (ParseException e) {
            throw new RuntimeException("System mal-function {}" + e.getMessage());
        }
    }

    private Date getDateFromRequestString(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    private Date getDateFromDatabaseString(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    private void throwRuntimeException(){
        throw new RuntimeException("Invalid request, system cannot get statements based on given request parameters");
    }
}
