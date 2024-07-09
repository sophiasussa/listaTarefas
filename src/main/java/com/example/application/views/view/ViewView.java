package com.example.application.views.view;

import com.example.application.controller.ControllerCategoria;
import com.example.application.controller.ControllerPrioridade;
import com.example.application.controller.ControllerResponsavel;
import com.example.application.controller.ControllerStatus;
import com.example.application.model.CategoriaTarefa;
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
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
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
        ComboBox comboBox = new ComboBox();
        ComboBox comboBox2 = new ComboBox();
        Button buttonTertiary = new Button();
        Button buttonTertiary2 = new Button();
        ComboBox comboBox3 = new ComboBox();
        ComboBox comboBox4 = new ComboBox();
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
        comboBox2.setPlaceholder("Status");
        comboBox2.setWidth("min-content");
        buttonTertiary.setText("Adicionar Responsavel");
        buttonTertiary.setWidth("min-content");
        buttonTertiary.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary2.setText("Adicionar Status");
        buttonTertiary2.setWidth("min-content");
        buttonTertiary2.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        comboBox3.setPlaceholder("Prioridade");
        comboBox3.setWidth("min-content");
        comboBox4.setPlaceholder("Categoria");
        comboBox4.setWidth("min-content");
        buttonTertiary3.setText("Adicionar Prioridade");
        buttonTertiary3.setWidth("min-content");

 /*       Button buttonInsideLink = new Button("Adicionar Tipo de Telefone");
        buttonInsideLink.addClickListener(event -> openDialog());
        buttonInsideLink.getStyle().set("box-shadow", "0 0 4px rgba(0, 0, 0, 0.2)");
        link.add(buttonInsideLink);*/

        buttonTertiary3.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary4.setText("Adicionar Categoria");
        buttonTertiary4.setWidth("min-content");
        buttonTertiary4.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setWidth("100%");
        buttonLayout.add(buttonPrimary);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonPrimary.setText("Salvar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.getStyle().set("border-radius", "20px");
        
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

 /*    private void openDialog() {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px"); 
        dialog.setHeight("600px");

        FormLayout formLayout = new FormLayout();
        TextField nomeField = new TextField("Nome");

        Grid<TipoTelefone> grid = new Grid<>(TipoTelefone.class);
        grid.setColumns("nome");

        List<TipoTelefone> tiposDeTelefone = controller.pesquisarTodos();
        grid.setItems(tiposDeTelefone);

        Editor<TipoTelefone> editor = grid.getEditor();
        Binder<TipoTelefone> binder = new Binder<>(TipoTelefone.class);
        editor.setBinder(binder);

        TextField nomeEditor = new TextField();
        binder.forField(nomeEditor).bind(TipoTelefone::getNome, TipoTelefone::setNome);
        grid.getColumnByKey("nome").setEditorComponent(nomeEditor);

        grid.addItemDoubleClickListener(event -> editor.editItem(event.getItem()));
        editor.addCloseListener(event -> grid.getDataProvider().refreshItem(event.getItem()));
        
        grid.addComponentColumn(tipoTelefone -> {
            Button alterarButton = new Button("Alterar", new Icon(VaadinIcon.COG));
            alterarButton.addClickListener(e -> {
                if (editor.isOpen()) {
                    editor.save();
                    TipoTelefone editedTipoTelefone = editor.getItem();
                    if (controller.alterar(editedTipoTelefone)) {
                        Notification notification = new Notification(
                                "Tipo de Telefone atualizado com sucesso.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                        
                        tiposDeTelefone.clear();
                        tiposDeTelefone.addAll(controller.pesquisarTodos());
                        grid.getDataProvider().refreshAll();
                    } else {
                        Notification notification = new Notification(
                                "Erro ao atualizar. Verifique se todos os dados foram preenchidos.", 3000);
                        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        notification.setPosition(Notification.Position.MIDDLE);
                        notification.open();
                    }
                } else {
                    editor.editItem(tipoTelefone);
                    nomeEditor.focus();
                }
            });
            return alterarButton;
        }).setHeader("Alterar");
        
        editor.addSaveListener(event -> {
            grid.getDataProvider().refreshItem(event.getItem());
        });
    }*/

}
