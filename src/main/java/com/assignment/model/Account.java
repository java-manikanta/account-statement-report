package com.assignment.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_number")
    private String accountNumber;

    @OneToMany(mappedBy = "account")
    private Set<Statement> statements;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Set<Statement> getStatements() {
        return statements;
    }

    public void setStatements(Set<Statement> statements) {
        this.statements = statements;
    }
}
