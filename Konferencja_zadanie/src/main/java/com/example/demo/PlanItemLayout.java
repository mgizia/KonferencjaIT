package com.example.demo;


import com.vaadin.data.Binder;
import com.vaadin.ui.*;
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

       // binder.addValueChangeListener(event -> changeListener.todoChange(plan));

       /* deleted.addValueChangeListener(valueChangeEvent -> {
            if(deleted.getValue() == false){
            deleted.setValue(true) ; }
        else if(deleted.getValue() == true){
            deleted.setValue(false);
        }}
        );
*/




    }

}
