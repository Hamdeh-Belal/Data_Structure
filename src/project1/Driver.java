package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	static DoublyLinkedList<Location> dLinkedList;

	public static void main(String[] args) {
		dLinkedList = new DoublyLinkedList<>();
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		TabPane tabPane = new TabPane();
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(stage);
		boolean b = readFile(file);                                        //O(n)
		if (!b) {
			Alert alertFile = new Alert(AlertType.ERROR);
			alertFile.setTitle("File Error !!!");
			alertFile.setHeaderText("Error: Unable to open file");
			alertFile.setContentText("The file you are trying to open does not exist or cannot be read.");
			alertFile.showAndWait();
		}

		tabPane.setPadding(new Insets(10));
		Tab1 tab1 = new Tab1(dLinkedList);
		tab1.setTabPane(tabPane);
		Tab2 tab2 = new Tab2(dLinkedList);
		Tab3 tab3 = new Tab3(dLinkedList);
		Tab4 tab4 = new Tab4(dLinkedList, stage);
		tab1.setTab3(tab3);
		tab1.setNextTab(tab2);

		tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

		Scene scene = new Scene(tabPane, 700, 500);
		stage.setScene(scene);
		stage.show();

	}

	private boolean readFile(File file) {

		if (!file.exists() || file == null)
			return false;
		Scanner input;
		try {
			input = new Scanner(file);
			String data = "";
			if (input.hasNext())
				input.nextLine();
			while (input.hasNext()) {                   // O(n)
				data = input.nextLine();
				String[] dataLine = data.split(",");
				if (dataLine.length == 5) {
					Location location = new Location(dataLine[2]);
					Location tempLoc = dLinkedList.findData(location);
					String name = dataLine[0].trim();
					String age = dataLine[1].trim();
					String date = dataLine[3].trim();
					if (age == "")
						continue;
					if (tempLoc != null) {
						int agee = Integer.parseInt(age);
						Date datee = new Date(date + "");
						char c = dataLine[4].charAt(0);
						tempLoc.insertSorted(new Martyr(name, agee, datee, c));

					} else {
						dLinkedList.insertSorted(location);
						location.insertSorted(new Martyr(dataLine[0].trim(), Integer.parseInt(dataLine[1].trim()),
								new Date(dataLine[3].trim()), dataLine[4].trim().charAt(0)));
					}
				}
			}
			input.close();
			return true;
		} catch (FileNotFoundException e) {
			Alert alertLoc = new Alert(AlertType.ERROR);
			alertLoc.setTitle(" File Error !!!");
			alertLoc.setHeaderText("Error: Unable find Data");
			alertLoc.setContentText("The File you are trying to find open it does not open  , please try again.");
			alertLoc.showAndWait();
		}
		return true;
	}

}