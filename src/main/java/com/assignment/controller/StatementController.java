package com.assignment.controller;

import com.assignment.model.StatementCriteria;
import com.assignment.model.StatementsResult;
import com.assignment.service.MiniStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/api")
public class StatementController {

    @Autowired
    private MiniStatementService miniStatementService;

    @GetMapping(value = "/account-report/{accountId}")
    public StatementsResult getStatements(@PathVariable(name = "accountId") long accountId){
        return miniStatementService.getStatementsOnAccount(accountId);
    }

    @GetMapping(value = "/account/statement-report/{accountId}")
    public StatementsResult getStatementsOnCriteria(
            @PathVariable(name = "accountId") long accountId,
            @RequestParam(name = "fromDate",required = false) String fromDate ,
                                        @RequestParam(name = "toDate", required = false) String toDate ,
                                        @RequestParam(name = "fromAmount", required = false) String fromAmount,
                                        @RequestParam(name = "toAmount",required = false) String toAmount) {
        StatementCriteria statementCriteria = new StatementCriteria(accountId, fromDate, toDate, fromAmount, toAmount);
        return miniStatementService.getStatementOnCriteria(statementCriteria);
    }
}
