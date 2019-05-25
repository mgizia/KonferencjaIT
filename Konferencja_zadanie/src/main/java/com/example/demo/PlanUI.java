package com.example.demo;


import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Arrays;


@SpringUI
public class PlanUI extends UI {

    private VerticalLayout root;

    List<Lecture>plan = Arrays.asList(
            new Lecture(1,111,211,311),
            new Lecture(2,112,212,312),
            new Lecture(3,121,221,321),
            new Lecture(4,122,222,322)
    );

    @Autowired
    PlanLayout planLayout;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addGrid();
        addForm();
        addTodoList();
        addDeleteButton();

    }

    private void addGrid() {
        Grid<Lecture>grid = new Grid();
        grid.setItems(plan);
        grid.addColumn(Lecture::getPath1).setCaption("Ścieżka tematyczna nr 1");
        grid.addColumn(Lecture::getPath2).setCaption("Ścieżka tematyczna nr 2");
        grid.addColumn(Lecture::getPath3).setCaption("Ścieżka tematyczna nr 3");
        grid.setSizeFull();
        root.addComponent(grid);
    }

    private void addDeleteButton() {
        root.addComponent(new Button("Delete completed", clickEvent ->  {
            planLayout.deleteCompleted();
        }));
    }

    private void addForm() {
       HorizontalLayout formLayout =  new HorizontalLayout();
       formLayout.setWidth("80%");

       TextField task = new TextField();
       Button add = new Button("Add");
       add.addStyleName(ValoTheme.BUTTON_PRIMARY);
       add.setIcon(VaadinIcons.PLUS);

       formLayout.addComponent(task);
       formLayout.addComponent(add);

       add.addClickListener(clickEvent -> {
           planLayout.add(new Plan(task.getValue()));
           task.clear();
           task.focus();
       });

       task.focus();
       add.setClickShortcut(ShortcutAction.KeyCode.ENTER);
       root.addComponent(formLayout);
    }

    private void addHeader() {
        Label header = new Label("Konferencja IT");
        header.addStyleName(ValoTheme.LABEL_H1);
        root.addComponent(header);
    }

    private void setupLayout() {
      root =  new VerticalLayout();
      root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
       setContent(root);
    }

    private void addTodoList() {
        planLayout.setWidth("80%");
        root.addComponent(planLayout);
    }
}
