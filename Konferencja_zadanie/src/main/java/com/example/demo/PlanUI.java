package com.example.demo;


import ch.qos.logback.core.joran.spi.ConsoleTarget;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


@SpringUI
public class PlanUI extends UI {

    private VerticalLayout root;
    List<Customer> customersList = new ArrayList<Customer>();
    List<String> loginList = new ArrayList<String>();

    private String info;
    private Customer customer;
    private static ComboBox customersComboBox = new ComboBox("Twój login");;



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

      //  addForm();
       // addTodoList();
       // addDeleteButton();
        addLogInfo();
        addLogLayout();
    }

    private void addLogLayout() {
        HorizontalLayout logLayout = new HorizontalLayout();
        VerticalLayout custLay = addCustomers();
        VerticalLayout userLay = addUserForm();

        logLayout.addComponent(userLay);
        logLayout.addComponent(custLay);

        root.addComponent(logLayout);

    }
    private boolean check(String login, String email){
        int lista_lenght = customersList.size();
        boolean check = false;

        if(login != "" && email != ""){
            for(int i = 0; i < lista_lenght; i++) {
                if (login.equals(customersList.get(i).getName()) && email.equals(customersList.get(i).getEmail())==false ) {
                   // System.out.println("Nazwa użytkownika zajęta");
                    info = "Nazwa użytkownika zajęta";
                    check = false;

                } else if (login.equals(customersList.get(i).getName()) && email.equals(customersList.get(i).getEmail())) {
                  //  System.out.println("Użytkownik istnieje.");
                    info = "Użytkownik" + login + "istnieje. Zaloguj się";
                    check = false;

                }
                else if(login.equals(customersList.get(i).getName()) == false && email.equals(customersList.get(i).getEmail())){
                    info = "Użytkownik " + customersList.get(i).getName() + " użył podanego adresu e-mail. Zaloguj się";
                    check = false;
                }//else if(login != customersList.get(i).getName() && email != customersList.get(i).getEmail()) {
                else{
                 //   System.out.println("Rejestracja udana");
                    info = "Rejestracja udana";
                    check = true;

                }
            }
        }
        else{
            info = "Podaj login i hasło";
        }


        return check;
    }

   private void addAndUpdate(String name, String email){

       int lista_lenght = customersList.size();
       int id;

           if(customersList.isEmpty() == true){
               id = 0;
           }
           else{
               id = lista_lenght+1;
           }
           Customer newCustomer = new Customer(id,name,email);
           customersList.add(newCustomer);
           loginList.add(newCustomer.getName());

           customersComboBox.setItems(loginList);



   }
    private VerticalLayout addCustomers() {
        VerticalLayout customersLayout = new VerticalLayout();
        customersLayout.setWidth("80%");

        addAndUpdate("name","email");

        Button logIn = new Button("Zaloguj się");
        logIn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        logIn.setIcon(VaadinIcons.PLUS);
        logIn.addClickListener(clickEvent -> {

           //zmieni sie wyglad

        });

        customersLayout.addComponent(customersComboBox);
        customersLayout.addComponent(logIn);

        return customersLayout;

    }

    private void addLogInfo() {
        Label logInfo = new Label("Zarejestruj się, jeśli zapisujesz się pierwszy raz lub wybierz swoją nazwę użytkownika z listy");
        logInfo.addStyleName(ValoTheme.LABEL_H2);
        root.addComponent(logInfo);
    }

    private VerticalLayout addUserForm() {
        VerticalLayout userLayout =  new VerticalLayout();
        userLayout.setWidth("80%");

        TextField login = new TextField();
        TextField email = new TextField();
        Button addUser = new Button("Zapisz się");

        addUser.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addUser.setIcon(VaadinIcons.PLUS);

        login.setCaption("Podaj login");
        email.setCaption("Podaj email");

        userLayout.addComponents(login,email,addUser);

        Label infoLabel = new Label();

        addUser.addClickListener(clickEvent -> {

           if(check(login.getValue(), email.getValue())==true){
               addAndUpdate(login.getValue(),email.getValue());
           }

            infoLabel.setCaption(info);
            userLayout.addComponent(infoLabel);
            login.clear();
            login.focus();
            email.clear();
            email.focus();
        });

        login.focus();
        addUser.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        return userLayout;
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
}
