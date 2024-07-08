package com.example.application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.application.model.Prioridade;


public class DaoPrioridade {

    public boolean inserir(Prioridade prioridade){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO prioridade (nome) VALUE (?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
            preparedStatement1.setString(1, prioridade.getDescricao());

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

    public boolean alterar(Prioridade prioridade){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String update = "UPDATE prioridade set nome = ? where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setString(1, prioridade.getDescricao());
            preparedStatement1.setInt(2, prioridade.getId());
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

    public boolean excluir(Prioridade prioridade){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String delete = "DELETE from prioridade where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(delete);
            preparedStatement1.setInt(1, prioridade.getId());
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

    public List<Prioridade> pesquisarTodos() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String consulta = "SELECT * from prioridade";
            List<Prioridade> lista = new ArrayList<Prioridade>();
            Prioridade prioridade;
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                prioridade = new Prioridade();
                prioridade.setId(resultSet.getInt("id"));
                prioridade.setDescricao(resultSet.getString("descrição"));
                lista.add(prioridade);
            }
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

}
