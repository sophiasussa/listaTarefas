package com.example.application.controller;

import java.util.List;

import com.example.application.model.Responsavel;
import com.example.application.repository.DaoResponsavel;

public class ControllerResponsavel {

    DaoResponsavel dao = new DaoResponsavel();

    public boolean inserir(Responsavel responsavel){
        return dao.inserir(responsavel);
    }

    public boolean alterar(Responsavel responsavel){
        return dao.alterar(responsavel);
    }

    public boolean excluir(Responsavel responsavel){
        return dao.excluir(responsavel);
    }

    public List<Responsavel> pesquisarTodos(){
        return dao.pesquisarTodos();
    }

}
