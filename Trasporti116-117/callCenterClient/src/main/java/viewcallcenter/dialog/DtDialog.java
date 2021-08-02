package viewcallcenter.dialog;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;

abstract class DtDialog {
    public static final int HGAP = 10;
    public static final int VGAP = 10;
    public static final int INS_TOP = 20;
    public static final int INS_RIGHT = 150;
    public static final int INS_BOTTOM = 10;
    public static final int INS_LEFT = 10;
    private Dialog<ButtonType> dialog = new Dialog<>();
    private GridPane gridPane = new GridPane();

    abstract void createEntity();

    protected void initialize(final String title, final ButtonType... buttonsType) {
        dialog.setTitle(title);
        dialog.getDialogPane().getButtonTypes().addAll(buttonsType);
        gridPane.setHgap(HGAP);
        gridPane.setVgap(VGAP);
        gridPane.setPadding(new Insets(INS_TOP, INS_RIGHT, INS_BOTTOM, INS_LEFT));
    }

    public Dialog<ButtonType> getDtDialog() {
        return dialog;
    }

    public GridPane getDtGridPane() {
        return gridPane;
    }
}
