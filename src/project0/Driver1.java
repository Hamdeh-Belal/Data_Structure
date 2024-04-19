package project0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Driver1 extends Application {
	private static MyList<Martyr> list = new MyList<>(16);;
	private static MyList<Martyr> listWestBank = new MyList<>(16);;
	private static MyList<Martyr> listGaza = new MyList<>(16);;
	private static MyList<Martyr> listJerusalem = new MyList<>(16);;
	BorderPane bp2 = new BorderPane();
	BorderPane bp = new BorderPane();
	RadioButton add = new RadioButton("Add new martyr ");
	RadioButton delete = new RadioButton("delete specific martyr by name ");
	RadioButton search = new RadioButton("Search by name ");
	RadioButton display = new RadioButton("display the number of martyrs");

	public static void main(String[] args) throws FileNotFoundException {
		int all = 0;
		FileReader file = new FileReader("C:\\Users\\hp\\Desktop\\JavaII\\WorkSpace2.2\\dataStructure\\btselem.csv");
		Scanner input = new Scanner(file);
//		for (int i = 0; i < 173; i++) {
//			if (i == 0)
//				continue;
//			all++;
//			list.add(new Martyr(input.nextLine()));
//
//		}
		while (input.hasNext()) {
			all++;
			list.add(new Martyr(input.nextLine()));
		}
		int gaza = 0;
		int jerus = 0;
		int west = 0;

		for (int i = 0; i < list.getSize(); i++) {
			if (list.get(i).getLocation().toLowerCase().contains("gaza")) {
				listGaza.add(list.get(i));
				gaza++;
			} else if (list.get(i).getLocation().toLowerCase().contains("jerusalem")) {
				listJerusalem.add(list.get(i));
				jerus++;
			} else {
				listWestBank.add(list.get(i));
				west++;
			}

		}
		System.out.println("west : " + west + " juerusalem :" + jerus + " gaza :" + gaza + "all : "
				+ (west + gaza + jerus) + " all ++ :" + all);

//		list.print();
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		ComboBox<String> eventLocation = new ComboBox<>();
		eventLocation.setPromptText("Select an Event location ");
		eventLocation.getItems().addAll("West Bank", "Gaza", "Jerusalem");
		bp.setTop(eventLocation);
		BorderPane.setAlignment(eventLocation, Pos.CENTER);
		add = new RadioButton("Add new martyr ");
		delete = new RadioButton("delete specific martyr by name ");
		search = new RadioButton("Search by name ");
		display = new RadioButton("display the number of martyrs");
		ToggleGroup toggleGroup = new ToggleGroup();
		add.setToggleGroup(toggleGroup);
		delete.setToggleGroup(toggleGroup);
		search.setToggleGroup(toggleGroup);
		display.setToggleGroup(toggleGroup);

		HBox hb = new HBox(20);
		hb.getChildren().addAll(add, delete, search, display);

		bp.setCenter(bp2);
		bp2.setTop(hb);

		eventLocation.setOnAction(e -> {
			if (eventLocation.getValue().equals("West Bank")) {
				if (add.isSelected()) {
					addHandler addH = new addHandler();
					add.setOnAction(addH);
				}

			} else if (eventLocation.getValue().equals("Gaza")) {
				if (add.isSelected()) {
					addHandler addH = new addHandler();
					add.setOnAction(addH);
				}

			} else {
				if (add.isSelected()) {
					addHandler addH = new addHandler();
					add.setOnAction(addH);
				}
			}

		});

		stage = new Stage();
		Scene scene = new Scene(bp, 700, 400);
		stage.setScene(scene);
		stage.show();
	}

	private Martyr Search(MyList<Martyr> array, String name) {
		if (array != null) {
			for (int i = 0; i < array.getSize(); i++) {
				if (array.get(i).getName().toLowerCase().equals(name.toLowerCase()))
					return array.get(i);
			}
		}

		return null;
	}

	class addHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			GridPane gp = new GridPane();
			gp.add(new Label("Name"), 0, 0);
			TextField nameTx = new TextField();
			gp.add(nameTx, 1, 0);
			gp.add(new Label("age"), 1, 0);

			TextField ageTX = new TextField();
			gp.add(ageTX, 1, 1);
			gp.add(new Label("location"), 2, 0);
			TextField locationTX = new TextField();
			gp.add(locationTX, 2, 1);
			gp.add(new Label("dateOfDeath"), 3, 0);
			TextField dateOfDeathTX = new TextField();
			gp.add(dateOfDeathTX, 3, 1);

			gp.add(new Label("gender"), 4, 0);
			TextField genderTX = new TextField();
			gp.add(genderTX, 4, 1);
			gp.add(new Label("note"), 5, 0);
			TextField noteTX = new TextField();
			gp.add(noteTX, 5, 1);
			bp2.setCenter(gp);

		}

	}
}
