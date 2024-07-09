package com.example.application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.application.model.CategoriaTarefa;
import com.example.application.model.ListaTarefas;
import com.example.application.model.Prioridade;
import com.example.application.model.Responsavel;
import com.example.application.model.Status;

public class DaoLista {

	public boolean inserir(ListaTarefas listaTarefas) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			String insert = "INSERT INTO listatarefas (id, data_tarefa, descricao_tarefa, observacao, idPrioridade, idCategoriaTarefa, idResponsavel, idStatus) values"
					+ "(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement1 = connection.prepareStatement(insert);
			preparedStatement1.setInt(1, listaTarefas.getId());
			preparedStatement1.setInt(2, listaTarefas.getData_tarefa());
			preparedStatement1.setString(3, listaTarefas.getDescricao_tarefa());
			preparedStatement1.setString(4, listaTarefas.getObservacao());
			preparedStatement1.setInt(5, listaTarefas.getPrioridade().getId());
			preparedStatement1.setInt(6, listaTarefas.getCategoriaTarefa().getId());
            preparedStatement1.setInt(7, listaTarefas.getResponsavel().getId());
            preparedStatement1.setInt(8, listaTarefas.getStatus().getId());
			int resultado = preparedStatement1.executeUpdate();
			if (resultado > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean alterar(ListaTarefas listaTarefas) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			String update = "UPDATE listatarefas set data_tarefa = ?, descricao_tarefa = ?, observacao  = ?, idPrioridade = ?, idCategoriaTarefa = ?, idResponsavel = ?, idStatus = ? where id = ?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(update);
			preparedStatement1.setInt(8, listaTarefas.getId());
			preparedStatement1.setInt(1, listaTarefas.getData_tarefa());
			preparedStatement1.setString(2, listaTarefas.getDescricao_tarefa());
			preparedStatement1.setString(3, listaTarefas.getObservacao());
			preparedStatement1.setInt(4, listaTarefas.getPrioridade().getId());
			preparedStatement1.setInt(5, listaTarefas.getCategoriaTarefa().getId());
            preparedStatement1.setInt(6, listaTarefas.getResponsavel().getId());
            preparedStatement1.setInt(7, listaTarefas.getStatus().getId());
			int resultado = preparedStatement1.executeUpdate();
			if (resultado > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluir(ListaTarefas listaTarefas) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			String delete = "DELETE from listatarefas where id = ?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(delete);
			preparedStatement1.setInt(1, listaTarefas.getId());
			int resultado = preparedStatement1.executeUpdate();
			if (resultado > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public List<ListaTarefas> pesquisarPorDataTarefa(int dataTarefa) {
		List<ListaTarefas> lista = new ArrayList<>();
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			String consulta = "SELECT * FROM listaTarefas WHERE data_tarefa = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setInt(1, dataTarefa);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				ListaTarefas listaTarefa = new ListaTarefas();
				listaTarefa.setId(resultSet.getInt("id"));
				listaTarefa.setData_tarefa(resultSet.getInt("data_tarefa"));
				listaTarefa.setDescricao_tarefa(resultSet.getString("descricao_tarefa"));
				listaTarefa.setObservacao(resultSet.getString("observacao"));
				Prioridade prioridade = new DaoPrioridade().pesquisar(resultSet.getInt("idPrioridade"));
				CategoriaTarefa categoriaTarefa = new DaoCategoria().pesquisar(resultSet.getInt("idCategoriaTarefa"));
				Responsavel responsavel = new DaoResponsavel().pesquisar(resultSet.getInt("idResponsavel")); 
				Status status = new DaoStatus().pesquisar(resultSet.getInt("idStatus"));                  
				listaTarefa.setPrioridade(prioridade);
				listaTarefa.setCategoriaTarefa(categoriaTarefa);
				listaTarefa.setResponsavel(responsavel);
				listaTarefa.setStatus(status);
				lista.add(listaTarefa);
			}
			
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ListaTarefas> pesquisarPorResponsavel(int idResponsavel) {
		List<ListaTarefas> lista = new ArrayList<>();
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			String consulta = "SELECT * FROM listaTarefas WHERE id_responsavel = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			preparedStatement.setInt(1, idResponsavel);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				ListaTarefas listaTarefa = new ListaTarefas();
				listaTarefa.setId(resultSet.getInt("id"));
				listaTarefa.setData_tarefa(resultSet.getInt("data_tarefa"));
				listaTarefa.setDescricao_tarefa(resultSet.getString("descricao_tarefa"));
				listaTarefa.setObservacao(resultSet.getString("observacao"));
				Prioridade prioridade = new DaoPrioridade().pesquisar(resultSet.getInt("idPrioridade"));
				CategoriaTarefa categoriaTarefa = new DaoCategoria().pesquisar(resultSet.getInt("idCategoriaTarefa"));
				Responsavel responsavel = new DaoResponsavel().pesquisar(resultSet.getInt("idResponsavel")); 
				Status status = new DaoStatus().pesquisar(resultSet.getInt("idStatus"));                  
				listaTarefa.setPrioridade(prioridade);
				listaTarefa.setCategoriaTarefa(categoriaTarefa);
				listaTarefa.setResponsavel(responsavel);
				listaTarefa.setStatus(status);
				lista.add(listaTarefa);
			}
			
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ListaTarefas> pesquisarTodos() {
		List<ListaTarefas> lista = new ArrayList<>();
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			String consulta = "SELECT * from listaTarefas";
			ListaTarefas listaTarefa;
			PreparedStatement preparedStatement = connection.prepareStatement(consulta);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				listaTarefa = new ListaTarefas();
				listaTarefa.setId(resultSet.getInt("id"));
				listaTarefa.setData_tarefa(resultSet.getInt("data_tarefa"));
				listaTarefa.setDescricao_tarefa(resultSet.getString("descricao_tarefa"));
				listaTarefa.setObservacao(resultSet.getString("observacao"));
				Prioridade prioridade = new DaoPrioridade().pesquisar(resultSet.getInt("idPrioridade"));
				CategoriaTarefa categoriaTarefa = new DaoCategoria().pesquisar(resultSet.getInt("idCategoriaTarefa"));
				Responsavel responsavel = new DaoResponsavel().pesquisar(resultSet.getInt("idResponsavel")); 
				Status status = new DaoStatus().pesquisar(resultSet.getInt("idStatus"));                  
				listaTarefa.setPrioridade(prioridade);
				listaTarefa.setCategoriaTarefa(categoriaTarefa);
				listaTarefa.setResponsavel(responsavel);
				listaTarefa.setStatus(status);
				lista.add(listaTarefa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
