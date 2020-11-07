package com.assignment.model;

import java.util.List;

public class StatementsResult {
    private Account account;
    private List<Statement> statements;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}
