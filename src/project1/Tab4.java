package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Tab4 extends Tab {

	private PrintWriter printwriter;
	private Stage stage;
	private DoublyLinkedList<Location> dLinkedList;

	public Tab4(DoublyLinkedList<Location> dLinkedList, Stage stage) {
		super("Save File");
		setDLinkedList(dLinkedList);
		setStage(stage);
		BorderPane bPane = new BorderPane();
		setContent(bPane);
		setDLinkedList(dLinkedList);
		Button Choose = new Button("Choose File");
		Button save = new Button("Save");
		bPane.setCenter(Choose);
		Choose.setOnAction(o -> {
			FileChooser fileChooser = new FileChooser();
			File file = fileChooser.showOpenDialog(stage);
			bPane.setCenter(save);

			save.setOnAction(s -> {
				try {
					printwriter = new PrintWriter(file);
					printwriter.print("Name,Age,Event location - District,Date of death,Gender\n");

					DNode<Location> dCurr = dLinkedList.getHead().getNext();
					while (dCurr != null) {
						LinkedList<Martyr> sLinkedList = dCurr.getData().getMartyrList();
						Node<Martyr> sCurr = sLinkedList.getHead().getNext();
						if (sLinkedList == null)
							continue;
						while (sCurr != null) {
							SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
							String formattedDate = dateFormat.format(sCurr.getData().getDateOfDeath());
							printwriter.println(sCurr.getData().getName() + "," + sCurr.getData().getAge() + ","
									+ dCurr.getData().getLocation() + "," + formattedDate + ","
									+ sCurr.getData().getGender());

							sCurr = sCurr.getNext();

						}
						dCurr = dCurr.getNext();
					}

					printwriter.close();
				} catch (FileNotFoundException e) {
					Alert alertLoc = new Alert(AlertType.ERROR);
					alertLoc.setTitle(" File Error !!!");
					alertLoc.setHeaderText("Error: Unable save Data");
					alertLoc.setContentText("The File you are trying to find it other File , please try again.");
					alertLoc.showAndWait();
					bPane.setCenter(Choose);

				}
			});
		});

	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private void setDLinkedList(DoublyLinkedList<Location> dLinkedList) {
		this.dLinkedList = dLinkedList;
	}
}
