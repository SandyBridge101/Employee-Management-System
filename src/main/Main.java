package main;

import app.EmployeeManagementApp;
import database.EmployeeDatabase;
import javafx.application.Application;
import models.Employee;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        Application.launch(EmployeeManagementApp.class,args);
    }
}
