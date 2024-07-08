package com.example.application.views.view;

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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import java.util.ArrayList;
import java.util.List;

@PageTitle("view")
@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class ViewView extends Composite<VerticalLayout> {

    public ViewView() {
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
        textField.setLabel("Text field");
        textField.setWidth("min-content");
        textArea.setLabel("Text area");
        textArea.setWidth("100%");
        textArea2.setLabel("Text area");
        textArea2.setWidth("100%");
        formLayout2Col2.setWidth("100%");
        comboBox.setLabel("Combo Box");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        comboBox2.setLabel("Combo Box");
        comboBox2.setWidth("min-content");
        setComboBoxSampleData(comboBox2);
        buttonTertiary.setText("Button");
        buttonTertiary.setWidth("min-content");
        buttonTertiary.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary2.setText("Button");
        buttonTertiary2.setWidth("min-content");
        buttonTertiary2.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        comboBox3.setLabel("Combo Box");
        comboBox3.setWidth("min-content");
        setComboBoxSampleData(comboBox3);
        comboBox4.setLabel("Combo Box");
        comboBox4.setWidth("min-content");
        setComboBoxSampleData(comboBox4);
        buttonTertiary3.setText("Button");
        buttonTertiary3.setWidth("min-content");

        Button buttonInsideLink = new Button("Adicionar Tipo de Telefone");
        buttonInsideLink.addClickListener(event -> openDialog());
        buttonInsideLink.getStyle().set("box-shadow", "0 0 4px rgba(0, 0, 0, 0.2)");
        link.add(buttonInsideLink);

        buttonTertiary3.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonTertiary4.setText("Button");
        buttonTertiary4.setWidth("min-content");
        buttonTertiary4.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        buttonPrimary.setText("Button");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
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
        getContent().add(buttonPrimary);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }


    private void openDialog() {
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
    }

}
