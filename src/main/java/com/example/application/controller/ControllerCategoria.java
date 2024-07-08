package com.example.application.controller;

import java.util.List;

import com.example.application.model.CategoriaTarefa;
import com.example.application.repository.DaoCategoria;

public class ControllerCategoria {

    DaoCategoria dao = new DaoCategoria();

    public boolean inserir(CategoriaTarefa categoriaTarefa){
        return dao.inserir(categoriaTarefa);
    }

    public boolean alterar(CategoriaTarefa categoriaTarefa){
        return dao.alterar(categoriaTarefa);
    }

    public boolean excluir(CategoriaTarefa categoriaTarefa){
        return dao.excluir(categoriaTarefa);
    }

    public List<CategoriaTarefa> pesquisarTodos(){
        return dao.pesquisarTodos();
    }

}
