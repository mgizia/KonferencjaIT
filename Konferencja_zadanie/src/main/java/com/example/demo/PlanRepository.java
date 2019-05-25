package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

 //   @Transactional
  //  void deleteByDone(boolean done);

}

