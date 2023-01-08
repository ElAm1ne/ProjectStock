package com.example.ProjectStock.Repository;
import com.example.ProjectStock.Modele.StockSearchHistory;
import com.example.ProjectStock.Modele.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
