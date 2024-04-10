import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class GuiListView1 extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("ListView Demo1");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------

    private final TextField txfName = new TextField();
    private final TextField sog = new TextField();
    private final ListView<String> lvwNames = new ListView<>();
    private final ArrayList<String> names = new ArrayList<>();
    private final Button insert = new Button("Insert");
    private final Button knapSøg = new Button("Søg");
    private final Button sortere = new Button("Sortere");
    Label fejl = new Label();

    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);
        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        // add a label to the pane (at col=0, row=0)
        Label lblName = new Label("Name:");
        pane.add(lblName, 0, 0);

        // add a label to the pane (at col=0, row=1)
        Label lblNames = new Label("Names:");
        pane.add(lblNames, 0, 1);
        GridPane.setValignment(lblNames, VPos.TOP);

        // add a text field to the pane (at col=1, row=0)
        pane.add(txfName, 1, 0);
        pane.add(sog, 2, 1);

        pane.add(insert, 2, 0);
//        pane.add(sortere, 3, 0);
        pane.add(knapSøg, 3, 1);
        insert.setOnAction(event -> this.InsertAktion());
        knapSøg.setOnAction(event -> this.KnapSøgAktion());
//        sortere.setOnAction(event -> this.sortereKnap());

        // add a listView to the pane(at col=1, row=1)
        pane.add(lvwNames, 1, 1);
        lvwNames.setPrefWidth(200);
        lvwNames.setPrefHeight(200);

        ChangeListener<String> listener = (ov, o, n) -> this.selectionChanged();
        lvwNames.getSelectionModel().selectedItemProperty().addListener(listener);

        this.initNames();
        lvwNames.getItems().setAll(names);

    }

    // -------------------------------------------------------------------------

    private ArrayList<String> initNames() {
        names.add("Jane");
        names.add("Eva");
        names.add("Lene");
        names.add("Mette");
        names.add("Tine");
        names.add("Line");
        names.add("Lone");
        names.add("Alberte");
        names.add("Pia");
        names.add("Aaaaaa");
        Collections.sort(names);
        return names;
    }

    // -------------------------------------------------------------------------

    private void selectionChanged() {
        String selected = lvwNames.getSelectionModel().getSelectedItem();
        txfName.setText(selected);
    }

//    private void sortereKnap() {
//        Collections.sort(names);
//        lvwNames.getItems().setAll(names);
//    }

    private void InsertAktion() {
        String navn = txfName.getText().trim();
        String navn1 = navn.substring(0,1).toUpperCase(Locale.ROOT)+navn.substring(1).toLowerCase(Locale.ROOT);
        names.add(navn1);
        Collections.sort(names);
        lvwNames.getItems().setAll(names);
        txfName.setText("");
    }



    private void KnapSøgAktion() {

        for (int i = 0; i < names.size() ; i++) {
            String p = sog.getText().trim();
            if (names.get(i).equalsIgnoreCase(p)){
                lvwNames.getItems().setAll(names.get(i));
            }else if (p.isEmpty()){
                {
                    lvwNames.getItems().setAll(names);
                }
            }

            }
        }
//        int i = 0;
//        while (i< names.size()){
//            if (names.get(i).equalsIgnoreCase(""+sog)){
//                lvwNames.getItems().removeAll(names);
////                return test.get(i);
////                lvwNames.getItems().setAll(names.get(i));
//
//            }else i++;
//        }
    }

