package com.example.application.controller;

import java.util.List;

import com.example.application.model.Prioridade;
import com.example.application.repository.DaoPrioridade;

public class ControllerPrioridade {

    DaoPrioridade dao = new DaoPrioridade();

    public boolean inserir(Prioridade prioridade){
        return dao.inserir(prioridade);
    }

    public boolean alterar(Prioridade prioridade){
        return dao.alterar(prioridade);
    }

    public boolean excluir(Prioridade prioridade){
        return dao.excluir(prioridade);
    }

    public Prioridade pesquisar(int id){
        return dao.pesquisar(id);
    }

    public List<Prioridade> pesquisarTodos(){
        return dao.pesquisarTodos();
    }

}
