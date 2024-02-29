package com.example.amrit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML private TableView<lab2table> tableView;
    @FXML private Label messageLabel;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField emailField;


    @FXML
    protected void onFetchDataButtonClick() {
        Connection connection = HelloApplication.getConnection();
        List<lab2table> dataList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM lab2table");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");

                lab2table example = new lab2table(id, name, age, email);
                dataList.add(example);
            }

            tableView.getItems().clear();
            tableView.getItems().addAll(dataList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void onViewDataButtonClick() {
        Connection connection = HelloApplication.getConnection();
        List<lab2table> dataList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM lab2table");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");

                lab2table example = new lab2table(id, name, age, email);
                dataList.add(example);
            }

            tableView.getItems().clear();
            tableView.getItems().addAll(dataList);

            TableColumn<lab2table, Integer> idColumn = new TableColumn<>("ID");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<lab2table, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            TableColumn<lab2table, Integer> ageColumn = new TableColumn<>("Age");
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            TableColumn<lab2table, String> emailColumn = new TableColumn<>("Email");
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

            tableView.getColumns().setAll(idColumn, nameColumn, ageColumn, emailColumn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onInsertButtonClick() {
        Connection connection = HelloApplication.getConnection();
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String email = emailField.getText();

        try {
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO lab2table (name, age, email) VALUES ('%s', %d, '%s')", name, age, email);
            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                messageLabel.setText("Data inserted successfully.");
                onViewDataButtonClick(); // Refresh TableView
            } else {
                messageLabel.setText("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onUpdateButtonClick() {
        Connection connection = HelloApplication.getConnection();
        lab2table selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int id = selectedItem.getId();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String email = emailField.getText();

            try {
                Statement statement = connection.createStatement();
                String sql = String.format("UPDATE lab2table SET name='%s', age=%d, email='%s' WHERE id=%d", name, age, email, id);
                int rowsAffected = statement.executeUpdate(sql);
                if (rowsAffected > 0) {
                    messageLabel.setText("Data updated successfully.");
                    onViewDataButtonClick(); // Refresh TableView
                } else {
                    messageLabel.setText("Failed to update data.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Please select a row to update.");
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        Connection connection = HelloApplication.getConnection();
        lab2table selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            int id = selectedItem.getId();

            try {
                Statement statement = connection.createStatement();
                String sql = String.format("DELETE FROM lab2table WHERE id=%d", id);
                int rowsAffected = statement.executeUpdate(sql);
                if (rowsAffected > 0) {
                    messageLabel.setText("Data deleted successfully.");
                    onViewDataButtonClick(); // Refresh TableView
                } else {
                    messageLabel.setText("Failed to delete data.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Please select a row to delete.");
        }
    }

}
