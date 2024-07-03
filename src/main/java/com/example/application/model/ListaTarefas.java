package com.example.application.model;

public class ListaTarefas {
    private int id;
    private int data_tarefa;
    private String descricao_tarefa;
    private String observacao;
    private Status status;
    private Prioridade prioridade;
    private CategoriaTarefa categoriaTarefa;
    private Responsavel responsavel;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getData_tarefa() {
        return data_tarefa;
    }
    public void setData_tarefa(int data_tarefa) {
        this.data_tarefa = data_tarefa;
    }
    public String getDescricao_tarefa() {
        return descricao_tarefa;
    }
    public void setDescricao_tarefa(String descricao_tarefa) {
        this.descricao_tarefa = descricao_tarefa;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public Prioridade getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
    public CategoriaTarefa getCategoriaTarefa() {
        return categoriaTarefa;
    }
    public void setCategoriaTarefa(CategoriaTarefa categoriaTarefa) {
        this.categoriaTarefa = categoriaTarefa;
    }
    public Responsavel getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }
}
