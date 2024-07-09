package com.example.application.views.view;

import com.example.application.controller.ControllerCategoria;
import com.example.application.controller.ControllerLista;
import com.example.application.controller.ControllerPrioridade;
import com.example.application.controller.ControllerResponsavel;
import com.example.application.controller.ControllerStatus;
import com.example.application.model.CategoriaTarefa;
import com.example.application.model.ListaTarefas;
import com.example.application.model.Prioridade;
import com.example.application.model.Responsavel;
import com.example.application.model.Status;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Adicionar Tarefa")
@Menu(icon = "line-awesome/svg/plus-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class ViewView extends Composite<VerticalLayout> {
    ControllerPrioridade controller = new ControllerPrioridade();
    ControllerCategoria controller1 = new ControllerCategoria();
    ControllerResponsavel controller2 = new ControllerResponsavel();
    ControllerStatus controller3 = new ControllerStatus();
    ControllerLista controller4 = new ControllerLista();

    public ViewView() {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.getStyle().set("flex-grow", "1");

        VerticalLayout layout1 = new VerticalLayout();
        layout1.setWidth("100%");
        layout1.getStyle().set("flex-grow", "1");

        layout1.getStyle().set("border", "1px solid #ccc");
        layout1.getStyle().set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.1)");
        layout1.getStyle().set("padding", "16px");
        layout1.getStyle().set("border-radius", "8px");
        layout1.getStyle().set("background-color", "#fff");

        FormLayout formLayout2Col = new FormLayout();
        TextField textField = new TextField();
        TextArea textArea = new TextArea();
        TextArea textArea2 = new TextArea();
        FormLayout formLayout2Col2 = new FormLayout();
        ComboBox<Responsavel> comboBox = new ComboBox();
        ComboBox<Status> comboBox2 = new ComboBox();
        Button buttonTertiary = new Button();
        Button buttonTertiary2 = new Button();
        ComboBox<Prioridade> comboBox3 = new ComboBox();
        ComboBox<CategoriaTarefa> comboBox4 = new ComboBox();
        Button buttonTertiary3 = new Button();
        Button buttonTertiary4 = new Button();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        textField.setPlaceholder("Data");
        textField.setWidth("min-content");
        textArea.setPlaceholder("Observação");
        textArea.setWidth("100%");
        textArea2.setPlaceholder("Descrição");
        textArea2.setWidth("100%");
        formLayout2Col2.setWidth("100%");
        comboBox.setPlaceholder("Responsavel");
        comboBox.setWidth("min-content");
        setComboBoxData(comboBox);

        comboBox2.setPlaceholder("Status");
        comboBox2.setWidth("min-content");
        setComboBoxStatusData(comboBox2);

        buttonTertiary.setText("Adicionar Responsavel");
        buttonTertiary.setWidth("min-content");
        buttonTertiary.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary.addClickListener(event -> openDialog());

        buttonTertiary2.setText("Adicionar Status");
        buttonTertiary2.setWidth("min-content");
        buttonTertiary2.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary2.addClickListener(event -> openDialog2());

        comboBox3.setPlaceholder("Prioridade");
        comboBox3.setWidth("min-content");
        setComboBoxPrioridadeData(comboBox3);

        comboBox4.setPlaceholder("Categoria");
        comboBox4.setWidth("min-content");
        setComboBoxCategoriaData(comboBox4);

        buttonTertiary3.setText("Adicionar Prioridade");
        buttonTertiary3.setWidth("min-content");
        buttonTertiary3.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary3.addClickListener(event -> openDialog3());

        buttonTertiary4.setText("Adicionar Categoria");
        buttonTertiary4.setWidth("min-content");
        buttonTertiary4.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary4.addClickListener(event -> openDialog4());

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth("100%");
        buttonLayout.add(buttonPrimary);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonPrimary.setText("Salvar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.getStyle().set("border-radius", "20px");

        buttonPrimary.addClickListener(event -> {
            ListaTarefas listaTarefas = new ListaTarefas();
            listaTarefas.setData_tarefa(Integer.parseInt(textField.getValue()));
            listaTarefas.setDescricao_tarefa(textArea2.getValue());
            listaTarefas.setObservacao(textArea.getValue());
            Prioridade prioridadeSelecionado = (Prioridade) comboBox3.getValue();
            Responsavel responsavelSelecionado = (Responsavel) comboBox.getValue();
            CategoriaTarefa categoriaSelecionado = (CategoriaTarefa) comboBox4.getValue();
            Status statusSelecionado = (Status) comboBox2.getValue();

            if (prioridadeSelecionado != null && responsavelSelecionado != null && categoriaSelecionado != null && statusSelecionado != null) {
                listaTarefas.setPrioridade(prioridadeSelecionado);
                listaTarefas.setResponsavel(responsavelSelecionado);
                listaTarefas.setCategoriaTarefa(categoriaSelecionado);
                listaTarefas.setStatus(statusSelecionado);
                if (controller4.inserir(listaTarefas) == true) {
                    Notification notification = new Notification(
                            "Tarefa salvo com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                } else {
                    Notification notification = new Notification(
                            "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            } else {
                Notification notification = new Notification(
                        "Por favor, selecione prioridade, responsavel, status e categoria", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
                return;
            }
        });
        
        getContent().add(formLayout2Col);
        formLayout2Col.add(textField);
        formLayout2Col.add(textArea);
        getContent().add(textArea2);
        getContent().add(formLayout2Col2);
        formLayout2Col2.add(comboBox);
        formLayout2Col2.add(comboBox2);
        formLayout2Col2.add(buttonTertiary);
        formLayout2Col2.add(buttonTertiary2);
        formLayout2Col2.add(comboBox3);
        formLayout2Col2.add(comboBox4);
        formLayout2Col2.add(buttonTertiary3);
        formLayout2Col2.add(buttonTertiary4);
        layout1.add(formLayout2Col, textArea2, formLayout2Col2);
        mainLayout.add(layout1, buttonLayout);
        getContent().add(mainLayout);
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

    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px"); 
        dialog.setHeight("600px");

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("Nome");

        Grid<Responsavel> grid = new Grid<>(Responsavel.class);
        grid.setColumns("nome");

        List<Responsavel> responsaveis = controller2.pesquisarTodos();
        grid.setItems(responsaveis);

        Editor<Responsavel> editor = grid.getEditor();
        Binder<Responsavel> binder = new Binder<>(Responsavel.class);
        editor.setBinder(binder);

        TextField nomeEditor = new TextField();
        binder.forField(nomeEditor).bind(Responsavel::getNome, Responsavel::setNome);
        grid.getColumnByKey("nome").setEditorComponent(nomeEditor);

        grid.addItemDoubleClickListener(event -> editor.editItem(event.getItem()));
        editor.addCloseListener(event -> grid.getDataProvider().refreshItem(event.getItem()));
        
        grid.addComponentColumn(responsavel -> {
            Button alterarButton = new Button("Alterar", new Icon(VaadinIcon.COG));
            alterarButton.addClickListener(e -> {
                if (editor.isOpen()) {
                    editor.save();
                    Responsavel editedResponsavel = editor.getItem();
                    if (controller2.alterar(editedResponsavel)) {
                        Notification notification = new Notification(
                                "Responsavel atualizado com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                        
                        responsaveis.clear();
                        responsaveis.addAll(controller2.pesquisarTodos());
                        grid.getDataProvider().refreshAll();
                    } else {
                        Notification notification = new Notification(
                                "Erro ao atualizar. Verifique se todos os dados foram preenchidos.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    editor.editItem(responsavel);
                    nomeEditor.focus();
                }
            });
            return alterarButton;
        }).setHeader("Alterar");
        
        editor.addSaveListener(event -> {
            grid.getDataProvider().refreshItem(event.getItem());
        });

        grid.addComponentColumn(responsavel -> {
            Button deletarButton = new Button(new Icon(VaadinIcon.TRASH));
            deletarButton.addClickListener(e -> {
                if (controller2.excluir(responsavel)) {
                    Notification notification = new Notification(
                            "Responsavel deletado com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                    
                    responsaveis.clear();
                    responsaveis.addAll(controller2.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao deletar. Tente novamente.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            });
            return deletarButton;
        }).setHeader("Deletar");
    

        Button confirmarButton = new Button("Salvar", event -> {
            if(nomeField.isEmpty()){
                Notification notification = new Notification(
                    "Erro: O nome não pode estar vazio.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                Responsavel responsavel = new Responsavel();
                responsavel.setNome(nomeField.getValue());
                if (controller2.inserir(responsavel) == true) {
                    Notification notification = new Notification(
                            "Responsavel salvo com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    nomeField.clear();
                    responsaveis.clear();
                    responsaveis.addAll(controller2.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            }
        });
        Button cancelarButton = new Button("Fechar", event -> dialog.close());

        cancelarButton.getStyle()
            .set("background-color", "#FF0000")  
            .set("color", "#FFFFFF")  
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        confirmarButton.getStyle()
            .set("background-color", "#228B22")
            .set("color", "#FFFFFF")
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        HorizontalLayout buttonLayout = new HorizontalLayout(cancelarButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(true);

        formLayout.add(nomeField, confirmarButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, grid, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openDialog2() {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px"); 
        dialog.setHeight("600px");

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("descricao");

        Grid<Status> grid = new Grid<>(Status.class);
        grid.setColumns("descricao");

        List<Status> statuss = controller3.pesquisarTodos();
        grid.setItems(statuss);

        Editor<Status> editor = grid.getEditor();
        Binder<Status> binder = new Binder<>(Status.class);
        editor.setBinder(binder);

        TextField nomeEditor = new TextField();
        binder.forField(nomeEditor).bind(Status::getDescricao, Status::setDescricao);
        grid.getColumnByKey("descricao").setEditorComponent(nomeEditor);

        grid.addItemDoubleClickListener(event -> editor.editItem(event.getItem()));
        editor.addCloseListener(event -> grid.getDataProvider().refreshItem(event.getItem()));
        
        grid.addComponentColumn(statu -> {
            Button alterarButton = new Button("Alterar", new Icon(VaadinIcon.COG));
            alterarButton.addClickListener(e -> {
                if (editor.isOpen()) {
                    editor.save();
                    Status editedStatus = editor.getItem();
                    if (controller3.alterar(editedStatus)) {
                        Notification notification = new Notification(
                                "Status atualizado com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                        
                        statuss.clear();
                        statuss.addAll(controller3.pesquisarTodos());
                        grid.getDataProvider().refreshAll();
                    } else {
                        Notification notification = new Notification(
                                "Erro ao atualizar. Verifique se todos os dados foram preenchidos.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    editor.editItem(statu);
                    nomeEditor.focus();
                }
            });
            return alterarButton;
        }).setHeader("Alterar");
        
        editor.addSaveListener(event -> {
            grid.getDataProvider().refreshItem(event.getItem());
        });

        grid.addComponentColumn(statu -> {
            Button deletarButton = new Button(new Icon(VaadinIcon.TRASH));
            deletarButton.addClickListener(e -> {
                if (controller3.excluir(statu)) {
                    Notification notification = new Notification(
                            "Status deletado com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                    
                    statuss.clear();
                    statuss.addAll(controller3.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao deletar. Tente novamente.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            });
            return deletarButton;
        }).setHeader("Deletar");
    

        Button confirmarButton = new Button("Salvar", event -> {
            if(nomeField.isEmpty()){
                Notification notification = new Notification(
                    "Erro: A descrição não pode estar vazio.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                Status status = new Status();
                status.setDescricao(nomeField.getValue());
                if (controller3.inserir(status) == true) {
                    Notification notification = new Notification(
                            "Responsavel salvo com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    nomeField.clear();
                    statuss.clear();
                    statuss.addAll(controller3.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            }
        });
        Button cancelarButton = new Button("Fechar", event -> dialog.close());

        cancelarButton.getStyle()
            .set("background-color", "#FF0000")  
            .set("color", "#FFFFFF")  
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        confirmarButton.getStyle()
            .set("background-color", "#228B22")
            .set("color", "#FFFFFF")
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        HorizontalLayout buttonLayout = new HorizontalLayout(cancelarButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(true);

        formLayout.add(nomeField, confirmarButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, grid, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openDialog3() {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px"); 
        dialog.setHeight("600px");

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("descricao");

        Grid<Prioridade> grid = new Grid<>(Prioridade.class);
        grid.setColumns("descricao");

        List<Prioridade> prioridades = controller.pesquisarTodos();
        grid.setItems(prioridades);

        Editor<Prioridade> editor = grid.getEditor();
        Binder<Prioridade> binder = new Binder<>(Prioridade.class);
        editor.setBinder(binder);

        TextField nomeEditor = new TextField();
        binder.forField(nomeEditor).bind(Prioridade::getDescricao, Prioridade::setDescricao);
        grid.getColumnByKey("descricao").setEditorComponent(nomeEditor);

        grid.addItemDoubleClickListener(event -> editor.editItem(event.getItem()));
        editor.addCloseListener(event -> grid.getDataProvider().refreshItem(event.getItem()));
        
        grid.addComponentColumn(prioridade -> {
            Button alterarButton = new Button("Alterar", new Icon(VaadinIcon.COG));
            alterarButton.addClickListener(e -> {
                if (editor.isOpen()) {
                    editor.save();
                    Prioridade editedPrioridade = editor.getItem();
                    if (controller.alterar(editedPrioridade)) {
                        Notification notification = new Notification(
                                "Prioridade atualizado com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                        
                        prioridades.clear();
                        prioridades.addAll(controller.pesquisarTodos());
                        grid.getDataProvider().refreshAll();
                    } else {
                        Notification notification = new Notification(
                                "Erro ao atualizar. Verifique se todos os dados foram preenchidos.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    editor.editItem(prioridade);
                    nomeEditor.focus();
                }
            });
            return alterarButton;
        }).setHeader("Alterar");
        
        editor.addSaveListener(event -> {
            grid.getDataProvider().refreshItem(event.getItem());
        });

        grid.addComponentColumn(prioridade -> {
            Button deletarButton = new Button(new Icon(VaadinIcon.TRASH));
            deletarButton.addClickListener(e -> {
                if (controller.excluir(prioridade)) {
                    Notification notification = new Notification(
                            "Prioridade deletado com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                    
                    prioridades.clear();
                    prioridades.addAll(controller.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao deletar. Tente novamente.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            });
            return deletarButton;
        }).setHeader("Deletar");
    

        Button confirmarButton = new Button("Salvar", event -> {
            if(nomeField.isEmpty()){
                Notification notification = new Notification(
                    "Erro: A descrição não pode estar vazio.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                Prioridade prioridade = new Prioridade();
                prioridade.setDescricao(nomeField.getValue());
                if (controller.inserir(prioridade) == true) {
                    Notification notification = new Notification(
                            "Prioridade salvo com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    nomeField.clear();
                    prioridades.clear();
                    prioridades.addAll(controller.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            }
        });
        Button cancelarButton = new Button("Fechar", event -> dialog.close());

        cancelarButton.getStyle()
            .set("background-color", "#FF0000")  
            .set("color", "#FFFFFF")  
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        confirmarButton.getStyle()
            .set("background-color", "#228B22")
            .set("color", "#FFFFFF")
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        HorizontalLayout buttonLayout = new HorizontalLayout(cancelarButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(true);

        formLayout.add(nomeField, confirmarButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, grid, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private void openDialog4() {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px"); 
        dialog.setHeight("600px");

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("descricao");

        Grid<CategoriaTarefa> grid = new Grid<>(CategoriaTarefa.class);
        grid.setColumns("descricao");

        List<CategoriaTarefa> categorias = controller1.pesquisarTodos();
        grid.setItems(categorias);

        Editor<CategoriaTarefa> editor = grid.getEditor();
        Binder<CategoriaTarefa> binder = new Binder<>(CategoriaTarefa.class);
        editor.setBinder(binder);

        TextField nomeEditor = new TextField();
        binder.forField(nomeEditor).bind(CategoriaTarefa::getDescricao, CategoriaTarefa::setDescricao);
        grid.getColumnByKey("descricao").setEditorComponent(nomeEditor);

        grid.addItemDoubleClickListener(event -> editor.editItem(event.getItem()));
        editor.addCloseListener(event -> grid.getDataProvider().refreshItem(event.getItem()));
        
        grid.addComponentColumn(categoria -> {
            Button alterarButton = new Button("Alterar", new Icon(VaadinIcon.COG));
            alterarButton.addClickListener(e -> {
                if (editor.isOpen()) {
                    editor.save();
                    CategoriaTarefa editedCategoria = editor.getItem();
                    if (controller1.alterar(editedCategoria)) {
                        Notification notification = new Notification(
                                "Categoria atualizado com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                        
                        categorias.clear();
                        categorias.addAll(controller1.pesquisarTodos());
                        grid.getDataProvider().refreshAll();
                    } else {
                        Notification notification = new Notification(
                                "Erro ao atualizar. Verifique se todos os dados foram preenchidos.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    editor.editItem(categoria);
                    nomeEditor.focus();
                }
            });
            return alterarButton;
        }).setHeader("Alterar");
        
        editor.addSaveListener(event -> {
            grid.getDataProvider().refreshItem(event.getItem());
        });

        grid.addComponentColumn(categoria -> {
            Button deletarButton = new Button(new Icon(VaadinIcon.TRASH));
            deletarButton.addClickListener(e -> {
                if (controller1.excluir(categoria)) {
                    Notification notification = new Notification(
                            "Categoria deletado com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                    
                    categorias.clear();
                    categorias.addAll(controller1.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao deletar. Tente novamente.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            });
            return deletarButton;
        }).setHeader("Deletar");
    

        Button confirmarButton = new Button("Salvar", event -> {
            if(nomeField.isEmpty()){
                Notification notification = new Notification(
                    "Erro: A descrição não pode estar vazio.", 3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.open();
            } else {
                CategoriaTarefa categoriaTarefa = new CategoriaTarefa();
                categoriaTarefa.setDescricao(nomeField.getValue());
                if (controller1.inserir(categoriaTarefa) == true) {
                    Notification notification = new Notification(
                            "Categoria salvo com sucesso.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();

                    nomeField.clear();
                    categorias.clear();
                    categorias.addAll(controller1.pesquisarTodos());
                    grid.getDataProvider().refreshAll();
                } else {
                    Notification notification = new Notification(
                            "Erro ao salvar. Verifique se todos os dados foram preenchidos.", 3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                    notification.setPosition(Notification.Position.MIDDLE);
                    notification.open();
                }
            }
        });
        Button cancelarButton = new Button("Fechar", event -> dialog.close());

        cancelarButton.getStyle()
            .set("background-color", "#FF0000")  
            .set("color", "#FFFFFF")  
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        confirmarButton.getStyle()
            .set("background-color", "#228B22")
            .set("color", "#FFFFFF")
            .set("border-radius", "10px")
            .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.2)")
            .set("cursor", "pointer");

        HorizontalLayout buttonLayout = new HorizontalLayout(cancelarButton);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setPadding(false);
        buttonLayout.setSpacing(true);

        formLayout.add(nomeField, confirmarButton);

        VerticalLayout dialogLayout = new VerticalLayout(formLayout, grid, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }

}
