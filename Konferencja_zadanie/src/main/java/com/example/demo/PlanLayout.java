package com.example.demo;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class PlanLayout extends VerticalLayout {

    @Autowired
    PlanRepository repo;

    @PostConstruct
    void init(){
       update();
    }

    private void setTodos(List<Plan> plans) {
        removeAllComponents();

        plans.forEach(plan -> addComponent(new PlanItemLayout(plan)));
    }

    public void add(Plan plan) {
        repo.save(plan);
        update();
    }

    private void update() {
        setTodos(repo.findAll());
    }

    public void deleteCompleted() {
      repo.deleteByDeleted(true);
        update();
    }
}
