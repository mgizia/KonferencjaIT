package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

 /*   public List<Customer> findAll() {
        return jdbcTemplate.query(
               // "SELECT id, user, lecture, deleted FROM plan",
             //   (rs, rowNum) -> new Customer(rs.getLong("id"),
                 //       rs.getString("user"), rs.getString("lecture")));
    }

    public void update(Customer customer) {
        jdbcTemplate.update(
                "UPDATE plan SET user=?, lecture=? deleted=? WHERE id=?",
                customer.getName(), customer.getEmail(), customer.getId()
                );
    }*/
}
