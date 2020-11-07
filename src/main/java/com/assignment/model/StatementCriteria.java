package com.assignment.model;

public class StatementCriteria {
    private Long accountId;
    private String fromDate;
    private String toDate;
    private String fromAmount;
    private String toAmount;

    public StatementCriteria(Long accountId, String fromDate, String toDate, String fromAmount, String toAmount) {
        this.accountId = accountId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromAmount = fromAmount;
        this.toAmount = toAmount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(String fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getToAmount() {
        return toAmount;
    }

    public void setToAmount(String toAmount) {
        this.toAmount = toAmount;
    }
}
