package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;

import models.Employee;
import database.EmployeeDatabase;

public class EmployeeManagementApp extends Application {
    private EmployeeDatabase<UUID> database = new EmployeeDatabase<>();
    private ObservableList<Employee<UUID>> employeeList = FXCollections.observableArrayList();
    private ListView<Employee<UUID>> employeeListView = new ListView<>(employeeList);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Management System");

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        employeeListView.setPrefHeight(200);
        root.getChildren().add(employeeListView);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField departmentField = new TextField();
        departmentField.setPromptText("Department");
        TextField salaryField = new TextField();
        salaryField.setPromptText("Salary");
        TextField ratingField = new TextField();
        ratingField.setPromptText("Rating");
        TextField experienceField = new TextField();
        experienceField.setPromptText("Experience");

        HBox form = new HBox(5, nameField, departmentField, salaryField, ratingField, experienceField);
        root.getChildren().add(form);

        Button addButton = new Button("Add Employee");
        Button removeButton = new Button("Remove Selected");
        Button updateButton = new Button("Update Selected");

        TextField searchField = new TextField();
        searchField.setPromptText("Search name");
        Button searchButton = new Button("Search by Name");

        TextField deptFilterField = new TextField();
        deptFilterField.setPromptText("Filter Department");
        Button deptFilterButton = new Button("Filter by Department");

        TextField minSalaryField = new TextField();
        minSalaryField.setPromptText("Min Salary");
        TextField maxSalaryField = new TextField();
        maxSalaryField.setPromptText("Max Salary");
        Button salaryFilterButton = new Button("Filter by Salary Range");

        TextField minRatingField = new TextField();
        minRatingField.setPromptText("Min Rating");
        Button ratingFilterButton = new Button("Filter by Rating");

        Button sortExperienceButton = new Button("Sort by Experience");
        Button sortSalaryButton = new Button("Sort by Salary");
        Button sortRatingButton = new Button("Sort by Performance");
        Button refreshButton = new Button("Refresh List");

        HBox buttonsRow1 = new HBox(5, addButton, removeButton, updateButton);
        HBox buttonsRow2 = new HBox(5, searchField, searchButton, deptFilterField, deptFilterButton);
        HBox buttonsRow3 = new HBox(5, minSalaryField, maxSalaryField, salaryFilterButton);
        HBox buttonsRow4 = new HBox(5, minRatingField, ratingFilterButton, sortExperienceButton, sortSalaryButton, sortRatingButton);
        HBox buttonsRow5 = new HBox(5,refreshButton);

        root.getChildren().addAll(buttonsRow1, buttonsRow2, buttonsRow3, buttonsRow4,buttonsRow5);

        addButton.setOnAction(e -> {
            try {
                UUID id = UUID.randomUUID();
                String name = nameField.getText();
                String dept = departmentField.getText();
                double salary = Double.parseDouble(salaryField.getText());
                double rating = Double.parseDouble(ratingField.getText());
                int experience = Integer.parseInt(experienceField.getText());
                Employee<UUID> emp = new Employee<>(id, name, dept, salary, rating, experience, true);
                database.addEmployee(emp);
                refreshEmployeeList();
            } catch (Exception ex) {
                showAlert("Input Error", "Please enter valid employee data.");
            }
        });

        removeButton.setOnAction(e -> {
            Employee<UUID> selected = employeeListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                database.removeEmployee(selected.getEmployeeId());
                refreshEmployeeList();
            }
        });

        updateButton.setOnAction(e -> {
            Employee<UUID> selected = employeeListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    String name = nameField.getText();
                    String dept = departmentField.getText();
                    double salary = Double.parseDouble(salaryField.getText());
                    double rating = Double.parseDouble(ratingField.getText());
                    int experience = Integer.parseInt(experienceField.getText());
                    selected.setName(name);
                    selected.setDepartment(dept);
                    selected.setSalary(salary);
                    selected.setPerformanceRating(rating);
                    selected.setYearsOfExperience(experience);
                    refreshEmployeeList();
                } catch (Exception ex) {
                    showAlert("Update Error", "Please enter valid updated data.");
                }
            }
        });

        searchButton.setOnAction(e -> {
            String name = searchField.getText();
            List<Employee<UUID>> results = database.searchByName(name);
            employeeList.setAll(results);
        });

        deptFilterButton.setOnAction(e -> {
            String dept = deptFilterField.getText();
            List<Employee<UUID>> results = database.searchByDepartment(dept);
            employeeList.setAll(results);
        });


        salaryFilterButton.setOnAction(e -> {
            try {
                double min = Double.parseDouble(minSalaryField.getText());
                double max = Double.parseDouble(maxSalaryField.getText());
                List<Employee<UUID>> results = database.filterBySalaryRange(min, max);
                employeeList.setAll(results);
            } catch (Exception ex) {
                showAlert("Filter Error", "Invalid salary range.");
            }
        });

        ratingFilterButton.setOnAction(e -> {
            try {
                double minRating = Double.parseDouble(minRatingField.getText());
                List<Employee<UUID>> results = database.filterByPerformance(minRating);
                employeeList.setAll(results);
            } catch (Exception ex) {
                showAlert("Filter Error", "Invalid rating value.");
            }
        });



        sortExperienceButton.setOnAction(e -> {
            List<Employee<UUID>> sorted = database.sortByExperience();
            employeeList.setAll(sorted);
        });

        sortSalaryButton.setOnAction(e -> {
            List<Employee<UUID>> sorted = database.sortBySalary();
            employeeList.setAll(sorted);
        });

        sortRatingButton.setOnAction(e -> {
            List<Employee<UUID>> sorted = database.sortByPerformance();
            employeeList.setAll(sorted);
        });

        refreshButton.setOnAction(e ->{
            refreshEmployeeList();
        });

        Scene scene = new Scene(root, 1100, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void refreshEmployeeList() {
        employeeList.setAll(database.getAllEmployees());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}