package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {
    public BD() {
    }

    public Users Register(String code, String name, String charge, String nit, String cellphone) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/profiles";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");


            // Sentencia INSERT
            String sql = "INSERT INTO users (code, name, charge, nit, cellphone) VALUES (?, ?, ?, ?, ?)";

            // Preparar la sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, charge);
            preparedStatement.setString(4, nit);
            preparedStatement.setString(5, cellphone);

            // Ejecutar la sentencia
            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("Registrado realizado de manera exitosa.");
                return new Users(code, name, charge, nit , cellphone);
            } else {
                System.out.println("No se puedo realizar el registro");
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Users(null, null, null, null, null);
    }

    public Users Edit(String code, String name, String charge, String nit, String cellphone) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/profiles";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        String consult = "UPDATE users SET name = ?, charge = ?, nit = ?, cellphone = ? WHERE code = ?";
        PreparedStatement preparedStatement = connection2.prepareStatement(consult);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, charge);
        preparedStatement.setString(3, nit);
        preparedStatement.setString(4, cellphone);
        preparedStatement.setString(5, code);

        int files = preparedStatement.executeUpdate();
        if (files > 0) {
            System.out.println("Usuario actualizado de manera exitosa");
            return new Users(code, name, charge, nit, cellphone);
        } else {
            System.out.println("No se encontro el usuario para actualizar");
        }

        preparedStatement.close();
        connection2.close();
        return new Users(null, null, null, null, null);
    }

    public List<Users> Select_consult() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/profiles";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM users");

        List<Users> list = new ArrayList<>();

        while (resultSet2.next()) {
            String code = resultSet2.getString("code");
            String name = resultSet2.getString("name");
            String charge = resultSet2.getString("charge");
            String nit = resultSet2.getString("nit");
            String cellphone = resultSet2.getString("cellphone");


            if (!nit.equals("5625")) {

                Users users = new Users(code, name, charge, nit, cellphone);

                list.add(users);
            }
        }
        return list;
    }

    public int Delete(String code) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/profiles";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM users WHERE code = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, code);
        int f = prepareStatement.executeUpdate();

        System.out.println("Usuario eliminado correctamente");
        return f;
    }
    }


