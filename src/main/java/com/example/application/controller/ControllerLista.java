package com.example.application.controller;

import java.util.List;

import com.example.application.model.ListaTarefas;
import com.example.application.repository.DaoLista;

public class ControllerLista {

    DaoLista dao = new DaoLista();

    public boolean inserir(ListaTarefas listaTarefas){
        return dao.inserir(listaTarefas);
    }

    public List<ListaTarefas> pesquisarTodos(){
        return dao.pesquisarTodos();
    }

}
