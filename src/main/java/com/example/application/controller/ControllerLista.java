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

    public List<ListaTarefas> pesquisarTodos(){
        return dao.pesquisarTodos();
    }

}
