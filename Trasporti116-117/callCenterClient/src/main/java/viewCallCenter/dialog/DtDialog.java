package viewCallCenter.dialog;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

abstract class DtDialog {
    Dialog<ButtonType> dialog = new Dialog<>();
    GridPane gridPane = new GridPane();

    abstract void createEntity();

    protected void initialize(String title, ButtonType... buttonsType){
        dialog.setTitle(title);
        dialog.getDialogPane().getButtonTypes().addAll(buttonsType);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
    }
}
