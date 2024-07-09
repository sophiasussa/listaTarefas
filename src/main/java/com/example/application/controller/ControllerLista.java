package com.example.application.controller;

import java.util.List;

import com.example.application.model.ListaTarefas;
import com.example.application.repository.DaoLista;

public class ControllerLista {

    DaoLista dao = new DaoLista();

    public boolean inserir(ListaTarefas listaTarefas){
        return dao.inserir(listaTarefas);
    }

    public boolean alterar(ListaTarefas listaTarefas){
        return dao.alterar(listaTarefas);
    }   

    public boolean excluir(ListaTarefas listaTarefas){
        return dao.excluir(listaTarefas);
    }   

    public List<ListaTarefas> pesquisarPorDataTarefa(int data_tarefa){
        return dao.pesquisarPorDataTarefa(data_tarefa);
    }
     
    public List<ListaTarefas> pesquisarPorResponsavel(String responsavel){
        return dao.pesquisarPorResponsavel(responsavel);
    }

    public List<ListaTarefas> pesquisarTodos(){
        return dao.pesquisarTodos();
    }
}
