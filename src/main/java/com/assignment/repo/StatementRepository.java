package com.assignment.repo;

import com.assignment.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {
    List<Statement> findByAccountId(Long accountId);
}
