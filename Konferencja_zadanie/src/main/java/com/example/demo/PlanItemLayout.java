package com.example.demo;


import com.vaadin.data.Binder;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;


public class PlanItemLayout extends HorizontalLayout {
    private final CheckBox deleted;
    private final TextField user;
    private final TextField lecture;


    public PlanItemLayout(Plan plan) {
        deleted = new CheckBox();
        user = new TextField();
        lecture = new TextField();


        user.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        lecture.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        addComponent(deleted);
       // addComponentsAndExpand(user);
        addComponentsAndExpand(lecture);
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        Binder<Plan> binder = new Binder<>(Plan.class);
        binder.bindInstanceFields(this);
        binder.setBean(plan);

    }

}
