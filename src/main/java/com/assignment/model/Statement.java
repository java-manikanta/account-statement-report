package com.assignment.model;

import javax.persistence.*;

@Table(name = "statement")
@Entity
public class Statement {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private long id;

    @Column(name = "date_field")
    private String date;

    @Column(name = "amount")
    private String amount;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
