package com.example.application.views.add;

import com.example.application.controller.ControllerCategoria;
import com.example.application.controller.ControllerLista;
import com.example.application.controller.ControllerPrioridade;
import com.example.application.controller.ControllerResponsavel;
import com.example.application.controller.ControllerStatus;
import com.example.application.data.SamplePerson;
import com.example.application.model.CategoriaTarefa;
import com.example.application.model.ListaTarefas;
import com.example.application.model.Prioridade;
import com.example.application.model.Responsavel;
import com.example.application.model.Status;
import com.example.application.services.SamplePersonService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Visualizar Tarefas")
@Menu(icon = "line-awesome/svg/eye-solid.svg", order = 1)
@Route(value = "my-view")
@Uses(Icon.class)
public class AddView extends Composite<VerticalLayout> {
    ControllerPrioridade controller = new ControllerPrioridade();
    ControllerCategoria controller1 = new ControllerCategoria();
    ControllerResponsavel controller2 = new ControllerResponsavel();
    ControllerStatus controller3 = new ControllerStatus();
    ControllerLista controller4 = new ControllerLista();
    Grid<ListaTarefas> basicGrid = new Grid(ListaTarefas.class);

    public AddView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        TextField textField = new TextField();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.END);
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("90px");
        layoutRow.setAlignItems(Alignment.END);
        layoutRow.setJustifyContentMode(JustifyContentMode.END);
        textField.setPlaceholder("Pesquisar");
        textField.setWidth("min-content");
        buttonPrimary.setIcon(new Icon(VaadinIcon.SEARCH));
        buttonPrimary.getStyle().set("border-radius", "50%");
        buttonPrimary.getStyle().set("box-shadow", "0 0 8px rgba(0, 0, 0, 0.2)");
        buttonPrimary.getStyle().set("cursor", "pointer");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary.addClickListener(event -> {
            String searchTerm = textField.getValue().trim();
            
            if (searchTerm.isEmpty()) {
                List<ListaTarefas> todosClientes = controller4.pesquisarTodos();
                basicGrid.setItems(todosClientes);
            } else if (searchTerm.matches("\\d+")) {
                int dataTarefa = Integer.parseInt(searchTerm);
                List<ListaTarefas> resultado = controller4.pesquisarPorDataTarefa(dataTarefa);
                basicGrid.setItems(resultado != null ? resultado : Collections.emptyList());
            } else {
                List<ListaTarefas> resultado = controller4.pesquisarPorResponsavel(searchTerm);
                basicGrid.setItems(resultado != null ? resultado : Collections.emptyList());
            }
        });

        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        basicGrid.getElement().getStyle().set("box-shadow", "0 0 6px rgba(0, 0, 0, 0.1)");
        basicGrid.getElement().getStyle().set("border-radius", "8px"); 
        basicGrid.getElement().getStyle().set("overflow", "hidden"); 

        Grid.Column<ListaTarefas> idColumn = basicGrid.getColumnByKey("id");
            if (idColumn != null) {
                idColumn.setVisible(false);
        }

        basicGrid.setColumns("data_tarefa", "descricao_tarefa", "observacao");

        basicGrid.addColumn(listaTarefa -> {
            Prioridade prioridade = listaTarefa.getPrioridade();
            return prioridade != null ? prioridade.getDescricao() : "";
        }).setHeader("Prioridade").setKey("prioridade");

        basicGrid.addColumn(listaTarefa -> {
            CategoriaTarefa categoriaTarefa = listaTarefa.getCategoriaTarefa();
            return categoriaTarefa != null ? categoriaTarefa.getDescricao() : "";
        }).setHeader("Categoria da Tarefa").setKey("categoriaTarefa");

        basicGrid.addColumn(listaTarefa -> {
            Responsavel responsavel = listaTarefa.getResponsavel();
            return responsavel != null ? responsavel.getNome() : "";
        }).setHeader("Responsável").setKey("responsavel");

        basicGrid.addColumn(listaTarefa -> {
            Status status = listaTarefa.getStatus();
            return status != null ? status.getDescricao() : "";
        }).setHeader("Status").setKey("status");

        basicGrid.addComponentColumn(listaTarefa -> {
            MenuBar menuBar = new MenuBar();
            MenuItem menuItem = menuBar.addItem("...");
            SubMenu subMenu = menuItem.getSubMenu();
            subMenu.addItem("Editar", event -> abrirDialogoEdicao(listaTarefa));
            subMenu.addItem("Excluir", event -> {
                boolean sucesso = controller4.excluir(listaTarefa); 
                if (sucesso) {
                    Notification.show("Tarefa excluída com sucesso!");
                    basicGrid.setItems(controller4.pesquisarTodos());
                } else {
                    Notification.show("Erro ao excluir tarefa.");
                }
            });
            return menuBar;
        }).setHeader("Opções");
        

        basicGrid.setItems(controller4.pesquisarTodos());
        getContent().add(layoutRow);
        layoutRow.add(textField);
        layoutRow.add(buttonPrimary);
        getContent().add(basicGrid);
    }

    private void setComboBoxData(ComboBox<Responsavel> comboBox) {
        List<Responsavel> responsaveis = controller2.pesquisarTodos();
        comboBox.setItems(responsaveis);
        comboBox.setItemLabelGenerator(responsavel -> responsavel.getNome());
    }

    private void setComboBoxStatusData(ComboBox<Status> comboBox1) {
        List<Status> status = controller3.pesquisarTodos();
        comboBox1.setItems(status);
        comboBox1.setItemLabelGenerator(statu -> statu.getDescricao());
    }

    private void setComboBoxPrioridadeData(ComboBox<Prioridade> comboBox2) {
        List<Prioridade> prioridades = controller.pesquisarTodos();
        comboBox2.setItems(prioridades);
        comboBox2.setItemLabelGenerator(prioridade -> prioridade.getDescricao());
    }

    private void setComboBoxCategoriaData(ComboBox<CategoriaTarefa> comboBox3) {
        List<CategoriaTarefa> categorias = controller1.pesquisarTodos();
        comboBox3.setItems(categorias);
        comboBox3.setItemLabelGenerator(categoria -> categoria.getDescricao());
    }


    private void abrirDialogoEdicao(ListaTarefas listaTarefas) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnOutsideClick(false);

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);

        TextField dataField = new TextField("Data");
        dataField.setValue(String.valueOf(listaTarefas.getData_tarefa()));

        TextArea descricaoField = new TextArea("Descrição");
        descricaoField.setValue(listaTarefas.getDescricao_tarefa());

        TextArea observacaoField = new TextArea("Observação");
        observacaoField.setValue(listaTarefas.getObservacao());

        ComboBox<Prioridade> prioridade = new ComboBox<>("Prioridade");
        setComboBoxPrioridadeData(prioridade);
        prioridade.setValue(listaTarefas.getPrioridade());

        ComboBox<CategoriaTarefa> categoria = new ComboBox<>("Categoria");
        setComboBoxCategoriaData(categoria);
        categoria.setValue(listaTarefas.getCategoriaTarefa());

        ComboBox<Responsavel> responsavel = new ComboBox<>("Responsavel");
        setComboBoxData(responsavel);
        responsavel.setValue(listaTarefas.getResponsavel());

        ComboBox<Status> status = new ComboBox<>("Status");
        setComboBoxStatusData(status);
        status.setValue(listaTarefas.getStatus());

        HorizontalLayout camposLayout = new HorizontalLayout(dataField, descricaoField, observacaoField);
        layout.add(camposLayout);

        HorizontalLayout camposLayout2 = new HorizontalLayout(prioridade, categoria, responsavel, status);
        layout.add(camposLayout2);

        Button salvarButton = new Button("Salvar", event -> {
            listaTarefas.setData_tarefa(Integer.parseInt(dataField.getValue()));
            listaTarefas.setDescricao_tarefa(descricaoField.getValue());
            listaTarefas.setObservacao(observacaoField.getValue());
            listaTarefas.setPrioridade(prioridade.getValue());
            listaTarefas.setCategoriaTarefa(categoria.getValue());
            listaTarefas.setResponsavel(responsavel.getValue());
            listaTarefas.setStatus(status.getValue());

           boolean sucesso = controller4.alterar(listaTarefas);
            if (sucesso) {
                Notification.show("Tarefa atualizada com sucesso!");
                basicGrid.setItems(controller4.pesquisarTodos());
                dialog.close();
            } else {
                Notification.show("Erro ao atualizar tarefa.");
            }
        });

        Button cancelarButton = new Button("Cancelar", event -> dialog.close());

        layout.add(new HorizontalLayout(salvarButton, cancelarButton));
        dialog.add(layout);
        dialog.open();
    }

}
