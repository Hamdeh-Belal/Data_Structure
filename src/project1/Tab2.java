package project1;

import java.util.Date;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Tab2 extends Tab {

	private DoublyLinkedList<Location> dLinkedList;

	private Martyr marRow;

	private TextField nameTxt;
	private TextField ageTxt;
	private TextField dateTxt;
	private TextField genderTxt;
	private TextField newName;

	private Button addBt;
	private ComboBox<String> upDeleteBT;
	private GridPane insertGP;
	private Label resLabel;
	private BorderPane bPane;

	private TableView<Martyr> tableView;
	private Location location;
	private LinkedList<Martyr> singleList;
	private HBox hbBt;
	private VBox vbBt;

	public Tab2(DoublyLinkedList<Location> dLinkedList) {
		super("Martyrs");
		setDLinkedList(dLinkedList);
		if (dLinkedList != null) {
			location = dLinkedList.getHead().getNext().getData();
			singleList = location.getMartyrList();

		}
		bPane = new BorderPane();
		bPane.setPadding(new Insets(10));
		hbBt = new HBox(10);
		vbBt = new VBox(10);

		insertGP = new GridPane();
		insertGP.setHgap(20);
		insertGP.setVgap(20);

		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton insertBt = new RadioButton("insert Martyr");
		insertBt.setToggleGroup(toggleGroup);
		RadioButton updateBt = new RadioButton("Update / Delete Martyr");
		updateBt.setToggleGroup(toggleGroup);
		insertGP.add(updateBt, 2, 1);
		RadioButton searchBt = new RadioButton("Search for Martyr ");
		searchBt.setToggleGroup(toggleGroup);
		insertGP.add(searchBt, 3, 1);
		hbBt.getChildren().addAll(insertBt, updateBt, searchBt);
		bPane.setTop(vbBt);
		addBt = new Button(" Insert ");
		upDeleteBT = new ComboBox<>();
		resLabel = new Label("");
		vbBt.getChildren().addAll(hbBt);

		insertBt.setOnAction(s -> { // insert ****************
			if (insertBt.isSelected()) {
				vbBt.getChildren().remove(upDeleteBT);

				insertGP = new GridPane();
				addBt = new Button("insert");
				nameTxt = new TextField();
				ageTxt = new TextField();
				dateTxt = new TextField();
				genderTxt = new TextField();
				resLabel.setText("");
				insertGP.add(new Label("New Martyr name :"), 0, 2);
				nameTxt.setPromptText("Enter Martyr name ....... ");
				insertGP.add(new Label("New Martyr age :"), 0, 3);
				insertGP.add(new Label("New Martyr Date:"), 0, 4);
				insertGP.add(new Label("New Martyr gender:"), 0, 5);
				ageTxt.setPromptText("age as int :) ");
				dateTxt.setPromptText("month/day/year  ");
				genderTxt.setPromptText("M  /  F ");
				insertGP.add(ageTxt, 2, 3);
				insertGP.add(dateTxt, 2, 4);
				insertGP.add(genderTxt, 2, 5);

				insertGP.add(nameTxt, 2, 2);
				bPane.setCenter(insertGP);
				insertGP.add(addBt, 1, 6);
			}
			addBt.setOnAction(k -> {

				if (insertBt.isSelected()) {
					if (nameTxt.getText() != null && genderTxt.getText() != null && ageTxt.getText() != null
							&& dateTxt.getText() != null) {
						if (singleList != null) {
							int age = Integer.parseInt(ageTxt.getText().trim());
							char gender = genderTxt.getText().trim().charAt(0);
							Date date = new Date(dateTxt.getText().trim());
							Martyr tempmer = new Martyr(nameTxt.getText().trim(), age, date, gender);
							singleList.insertSorted(tempmer);
							resLabel.setText(tempmer.toString());

						}

					}
				}
			});
		});

		updateBt.setOnAction(l -> { // update bt ******************
			if (updateBt.isSelected()) {
				insertGP = new GridPane();
				addBt = new Button("insert");
				nameTxt = new TextField();
				ageTxt = new TextField();
				dateTxt = new TextField();
				genderTxt = new TextField();
				resLabel.setText("");
				nameTxt.setPromptText("Enter Martyr name ....... ");
				ageTxt.setPromptText("age as int :) ");
				dateTxt.setPromptText("month/day/year  ");
				genderTxt.setPromptText("M  /  F ");

				bPane.setCenter(insertGP);
				upDeleteBT = new ComboBox<>();
				upDeleteBT.getItems().addAll(" Update ", " Delete ");
				vbBt.getChildren().add(upDeleteBT);

				upDeleteBT.setOnAction(j -> { // combo box ****************
					if (upDeleteBT.getValue().equals(" Update ")) {
						insertGP = new GridPane();
						bPane.setCenter(insertGP);
						resLabel = new Label("   ");
						insertGP.add(resLabel, 1, 7);
						nameTxt = new TextField();
						newName = new TextField();
						addBt = new Button("update");
						insertGP.add(addBt, 1, 5);
						insertGP.add(new Label("Enter name : "), 0, 3);
						nameTxt.setPromptText("Enter name ..... ");
						insertGP.add(new Label("Enter new name : "), 0, 4);
						newName.setPromptText("Enter new name ..... ");

						insertGP.add(nameTxt, 1, 3);
						insertGP.add(newName, 1, 4);

						addBt.setOnAction(w -> { // add *************
							if (upDeleteBT.getValue().equals(" Update ")) {
								findMatyr(nameTxt.getText().trim());
								if (marRow != null && newName.getText() != null) {
									marRow.setName(newName.getText().trim());
									resLabel.setText(marRow.toString() + "");
								} else {
									Alert alertLoc = new Alert(AlertType.ERROR);
									alertLoc.setTitle("name Error !!!");
									alertLoc.setHeaderText("Error: Unable find name");
									alertLoc.setContentText(
											"The name you are trying to find it does not exist , please try again.");
									alertLoc.showAndWait();
								}
							}
						});
					} else if (upDeleteBT.getValue().equals(" Delete ")) {
						insertGP = new GridPane();
						bPane.setCenter(insertGP);
						resLabel = new Label("   ");
						insertGP.add(resLabel, 1, 7);
						nameTxt = new TextField();
						newName = new TextField();
						addBt = new Button("delete");
						insertGP.add(addBt, 1, 5);
						insertGP.add(new Label("Enter name : "), 0, 3);
						nameTxt.setPromptText("Enter name ..... ");
						insertGP.add(nameTxt, 1, 3);

						addBt.setOnAction(w -> {
							if (upDeleteBT.getValue().equals(" Delete ")) {
								Martyr temps = singleList.deleteSorted(new Martyr(nameTxt.getText().trim()));
								if (temps != null) {
									resLabel.setText(temps.toString() + " has been deleted !");
								} else {
									Alert alertLoc = new Alert(AlertType.ERROR);
									alertLoc.setTitle("name Error !!!");
									alertLoc.setHeaderText("Error: Unable find name");
									alertLoc.setContentText(
											"The name you are trying to find it does not exist , please try again.");
									alertLoc.showAndWait();
								}
							}
						});
					}

				});
			}
		});

		searchBt.setOnAction(i -> { // search bt ********************
			if (searchBt.isSelected()) {
				vbBt.getChildren().remove(upDeleteBT);
				insertGP = new GridPane();
				bPane.setCenter(insertGP);
				addBt = new Button("Search");
				insertGP.setHgap(10);
				insertGP.setVgap(10);
				TextField name = new TextField();
				Label res = new Label("   ");
				insertGP.add(res, 0, 4);
				name.setPromptText("Enter Name");
				insertGP.add(new Label("Enter new Martyr name : "), 0, 0);
				insertGP.add(name, 1, 0);
				insertGP.add(addBt, 1, 1);

				addBt.setOnAction(p -> {

					if (name.getText() != null) {
						tableView = new TableView<>();
						Node<Martyr> curr = singleList.getHead().getNext();
						while (curr != null) {
							boolean names = curr.getData().getName().contains(name.getText().trim());

							if (names) {
								tableView.getItems().add(curr.getData());
							}
							curr = curr.getNext();
						}
						tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // add data to table view
																								// ****

						TableColumn<Martyr, String> nameColumn = new TableColumn<>("Name");
						nameColumn.setMinWidth(30);
						nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

						TableColumn<Martyr, Integer> ageColumn = new TableColumn<>("Age");
						ageColumn.setMinWidth(30);
						ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

						TableColumn<Martyr, Date> dateColumn = new TableColumn<>("Date");
						dateColumn.setMinWidth(30);
						dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfDeath"));

						TableColumn<Martyr, Character> genderColumn = new TableColumn<>("Gender");
						genderColumn.setMinWidth(30);
						genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

						tableView.getColumns().addAll(nameColumn, ageColumn, dateColumn, genderColumn);
						bPane.setCenter(tableView);

					}
				});
			}
		});

		setContent(bPane);

	}

	public void setRowData(Martyr marRow) {
		this.marRow = marRow;
	}

	public void setDLinkedList(DoublyLinkedList<Location> dLinkedList) {
		this.dLinkedList = dLinkedList;
	}

	private void findMatyr(String name) {

		Martyr tempMar = singleList.findData(new Martyr(name));
		if (tempMar == null)
			marRow = null;
		else
			marRow = tempMar;

	}

	public void setLocation(Location location) {
		if (location != null) {
			this.location = location;
			singleList = location.getMartyrList();
		}
	}

}
