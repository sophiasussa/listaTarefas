package com.example.application.controller;

import java.util.List;

import com.example.application.model.Status;
import com.example.application.repository.DaoStatus;

public class ControllerStatus {

    DaoStatus dao = new DaoStatus();

    public boolean inserir(Status status){
        return dao.inserir(status);
    }

    public boolean alterar(Status status){
        return dao.alterar(status);
    }

    public boolean excluir(Status status){
        return dao.excluir(status);
    }

    public Status pesquisar(int id){
        return dao.pesquisar(id);
    }

    public List<Status> pesquisarTodos(){
        return dao.pesquisarTodos();
    }

}
