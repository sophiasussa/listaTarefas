package com.example.application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.application.model.CategoriaTarefa;

public class DaoCategoria {

    public boolean inserir(CategoriaTarefa categoriaTarefa){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String insert = "INSERT INTO categoriatarefa (descricao) VALUE (?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
            preparedStatement1.setString(1, categoriaTarefa.getDescricao());

            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
			e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(CategoriaTarefa categoriaTarefa){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String update = "UPDATE categoriatarefa set descricao = ? where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setString(1, categoriaTarefa.getDescricao());
            preparedStatement1.setInt(2, categoriaTarefa.getId());
            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(CategoriaTarefa categoriaTarefa){
        try{
            Connection connection = DBConnection.getInstance().getConnection();
            String delete = "DELETE from categoriatarefa where id = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(delete);
            preparedStatement1.setInt(1, categoriaTarefa.getId());
            int resultado = preparedStatement1.executeUpdate();
            if(resultado > 0){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public CategoriaTarefa pesquisar(int id) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			String consulta = "SELECT * from categoriatarefa where id = ?";
			CategoriaTarefa categoriaTarefa = new CategoriaTarefa();
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				categoriaTarefa.setId(resultSet.getInt("id"));
				categoriaTarefa.setDescricao(resultSet.getString("descricao"));
			}
			return categoriaTarefa;
		} catch (Exception e) {
            e.printStackTrace();
			return null;
		}
	}

    public List<CategoriaTarefa> pesquisarTodos() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String consulta = "SELECT * from categoriatarefa";
            List<CategoriaTarefa> lista = new ArrayList<CategoriaTarefa>();
            CategoriaTarefa categoriaTarefa;
            PreparedStatement preparedStatement = connection.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categoriaTarefa = new CategoriaTarefa();
                categoriaTarefa.setId(resultSet.getInt("id"));
                categoriaTarefa.setDescricao(resultSet.getString("descricao"));
                lista.add(categoriaTarefa);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
