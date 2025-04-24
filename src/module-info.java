
module EmployeeManagementSystem {
    requires javafx.controls;
    requires javafx.graphics;
    requires junit;

    //requires org.junit.jupiter.engine;
    //opens employee_management_system to junit;
    opens database to junit;
    opens test to junit;



    opens app;
}