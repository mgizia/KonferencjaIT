package com.example.demo;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


@SpringUI
public class PlanUI extends UI {

    private VerticalLayout root;
    File plik = new File("powiadomienia.txt");
    List<Customer> customersList = new ArrayList<Customer>();
    List<String> loginList = new ArrayList<String>();
    HorizontalLayout logLayout = new HorizontalLayout();
    private String info;
    private String infoText;
    private Customer customerLogged;
    private ComboBox customersComboBox = new ComboBox("Twój login");
    private Label logInfo = new Label(infoText);
    private  int lecture;
    private String lectureString;
    private boolean logIn;
    private String chosen;
    final TextField selectedPath = new TextField();
    Button add = new Button("Rezerwuj");
    Label reservInfo = new Label();

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
        infoText=("Zarejestruj się, jeśli zapisujesz się pierwszy raz lub wybierz swoją nazwę użytkownika z listy");
        selectedPath.setVisible(false);
        add.setVisible(false);

    }


    private void addLogLayout() {
            VerticalLayout custLay = lodInLayout();
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
                    sendMessage(info);
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

    private VerticalLayout lodInLayout() {
        VerticalLayout customersLayout = new VerticalLayout();
        customersLayout.setWidth("80%");

        //pierwszy użytkownik
       addAndUpdate("name","email");

        Button logInButton = new Button("Zaloguj się");
        logInButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        logInButton.setIcon(VaadinIcons.PLUS);

        logInButton.addClickListener(clickEvent -> {
            String logName = (String) customersComboBox.getValue() ;
            if(logName.equals("")==false){
                logIn = true;
                logLayout.setVisible(false);
                selectedPath.setVisible(true);
                add.setVisible(true);

                whoIsLoggedIn(logName);
                infoText="Jestes zalogowany jako: " + customerLogged.getName() ;
                addLogInfo(infoText);
                addTodoList();
                addDeleteButton();
            }

        });

        customersLayout.addComponent(customersComboBox);
        customersLayout.addComponent(logInButton);

        return customersLayout;

    }

    private void whoIsLoggedIn(String login) {
       for(int i = 0 ; i < customersList.size() ; i++){
        if(login.equals(customersList.get(i).getName())){
           int idLog = customersList.get(i).getId();
           String nameLog = customersList.get(i).getName();
           String emailLog = customersList.get(i).getEmail();
           customerLogged = new Customer(idLog, nameLog, emailLog);
        }
       }

    }

    private void addLogInfo(String infoText) {

        logInfo.setCaption(infoText);
        logInfo.addStyleName(ValoTheme.LABEL_H2);
        logInfo.setVisible(true);

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

         addLogInfo(infoText);
         addLogLayout();

    /*   final TextField selectedPath2 = new TextField();
        grid.addItemClickListener(new ItemClickListener<Lecture>() {
            @Override
            public void itemClick(Grid.ItemClick<Lecture> itemClick) {
                if(itemClick.getColumn().toString().equals("Ścieżka tematyczna nr 1")){
                    if(itemClick.getMouseEventDetails().isDoubleClick()){
                        // Notification.show("Value" ;
                        selectedPath2.setCaption("Wybrano termin:  " + itemClick.getItem().getPath1() + ". Podaj nr ścieżki tematycznej (1, 2 lub 3)");
                    }

                }
                else if (itemClick.getColumn().toString().equals("Ścieżka tematyczna nr 2")){
                    if(itemClick.getMouseEventDetails().isDoubleClick()){
                        // Notification.show("Value" ;
                        selectedPath2.setCaption("Wybrano termin:  " + itemClick.getItem().getPath2() + ". Podaj nr ścieżki tematycznej (1, 2 lub 3)");
                    }
                }
                else if(itemClick.getColumn().toString().equals("Ścieżka tematyczna nr 3")){
                    if(itemClick.getMouseEventDetails().isDoubleClick()){
                        // Notification.show("Value" ;
                        selectedPath2.setCaption("Wybrano termin:  " + itemClick.getItem().getPath3() + ". Podaj nr ścieżki tematycznej (1, 2 lub 3)");
                    }

                }
            }
        });

     */


        add.addStyleName(ValoTheme.BUTTON_PRIMARY);
        add.setIcon(VaadinIcons.PLUS);
        add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        grid.addItemClickListener(new ItemClickListener<Lecture>() {
            @Override
            public void  itemClick(Grid.ItemClick<Lecture> event){
                if(event.getMouseEventDetails().isDoubleClick()){
                    chosen = event.getItem().getPath1();
                    selectedPath.setCaption("Wybrano termin:  " + event.getItem().getPath1() + ". Podaj nr ścieżki tematycznej (1, 2 lub 3)");
                }
            }
        });

        add.addClickListener(clickEvent -> {
           reservationId(Integer.parseInt(selectedPath.getValue()));

           planLayout.add(new Plan(customerLogged.getName(),lectureString));
            selectedPath.clear();
            selectedPath.focus();
        });

        selectedPath.focus();
        root.addComponent(grid);
        root.addComponents(selectedPath,add,reservInfo);
    }

    private void reservationId(int selPath) {

        Lecture lect = new Lecture();

        String mess;
       int a = lect.getId(chosen);
       if (selPath == 1 || selPath == 2 || selPath ==3){
           lecture = selPath*100 + a;
           mess="Wybrano sciezke nr " + selPath + " w terminie: " + chosen;
           reservInfo.setCaption(mess);

       }
       else{
           mess="Nie ma takiej sciezki. Rezerwacja nieudana. Spróbuj ponownie. ";
           reservInfo.setCaption(mess);
         //  sendMessage(mess);
       }
        lectureString = Integer.toString(lecture);
       lectureString = selPath + "," + chosen;
        sendMessage(mess);
    }

    private void sendMessage(String mess) {

        if(logIn == true){
            try{
            Writer output = new BufferedWriter(new FileWriter("powiadomienia.txt", true));
            output.append( customerLogged.getName() + "," + customerLogged.getEmail() + "," + mess +";\r\n" );
            output.close();
             } catch (Exception e){
            System.out.println("Błąd wejscia - wyjscia");
            }
        }
    }

    private void addHeader() {
        Label header = new Label("Konferencja IT");
        header.addStyleName(ValoTheme.LABEL_H1);

        String info1 = "Poniżej przedstawiony jest kalendarz konferencji IT. Zaloguj się, aby móc dokonać rejestracji";
        String info2 = "Możesz dokonać wielu rezerwacji, jednak pamiętaj, aby wybrane prelekcje nie odbywały się w tym samym teminie";
        String info3 = "Po dokonaniu rezerwacji na podany adres e-mail zostanie wysłane potwierdzenia";
        String info4 = "Będąc zalogowanym możesz zrezygnować ze swoich rezerwacji";

        Label header2 = new Label(info1);
        Label header2a = new Label(info2);
        Label header2b = new Label(info3);
        Label header2c = new Label(info4);

        header2.addStyleName(ValoTheme.LABEL_SMALL);
        header2a.addStyleName(ValoTheme.LABEL_SMALL);
        header2b.addStyleName(ValoTheme.LABEL_SMALL);
        header2c.addStyleName(ValoTheme.LABEL_SMALL);

        root.addComponents(header, header2, header2a, header2b, header2c);
        logInfo.setVisible(false);
        root.addComponent(logInfo);
    }

    private void setupLayout() {
      root =  new VerticalLayout();
      root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
       setContent(root);
    }

    private void addTodoList() {
        planLayout.setWidth("80%");
        Label userRes = new Label("Moje rezerwacje: ");
        userRes.addStyleName(ValoTheme.LABEL_H4);
        root.addComponents(userRes, planLayout);
    }

    private void addDeleteButton() {
        root.addComponent(new Button("Rezygnuję z zaznaczonych rezerwacji", clickEvent ->  {
            planLayout.deleteCompleted();
        }));
    }

}
