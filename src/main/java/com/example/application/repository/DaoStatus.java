package com.example.application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.application.model.Status;

public class DaoStatus {

    public boolean inserir(Status status){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO status (nome) VALUE (?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
            preparedStatement1.setString(1, status.getDescricao());

            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean alterar(Status status){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String update = "UPDATE status set nome = ? where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setString(1, status.getDescricao());
            preparedStatement1.setInt(2, status.getId());
            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    public boolean excluir(Status status){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String delete = "DELETE from status where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(delete);
            preparedStatement1.setInt(1, status.getId());
            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    public List<Status> pesquisarTodos() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String consulta = "SELECT * from status";
            List<Status> lista = new ArrayList<Status>();
            Status status;
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setDescricao(resultSet.getString("descrição"));
                lista.add(status);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }
}
