package project1;

import java.util.Date;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Tab1 extends Tab {
	public DoublyLinkedList<Location> DLinkedList;
	private TextField locationTxt;
	private TextField newLocationTxt;

	private Button addBt;
	private ComboBox<String> upDeleteBT;
	private GridPane insertGP;
	private Label resLabel;
	private TabPane tabPane;
	private BorderPane bPane;
	private Tab2 secondTab;
	private TableView<Martyr> tableView;
	private Tab3 tab3;

	public Tab1(DoublyLinkedList<Location> DLinkedList) {
		super("Location");
		setDLinkedList(DLinkedList);

		bPane = new BorderPane();
		bPane.setPadding(new Insets(10));
		HBox hbBt = new HBox(10);
		insertGP = new GridPane();
		ToggleGroup toggleGroup = new ToggleGroup();

		insertGP.setHgap(20);
		insertGP.setVgap(20);

		RadioButton insertBt = new RadioButton("insert Location");
		insertBt.setToggleGroup(toggleGroup);
		RadioButton updateBt = new RadioButton("Update / Delete Location");
		updateBt.setToggleGroup(toggleGroup);
		insertGP.add(updateBt, 2, 1);
		RadioButton searchBt = new RadioButton("Search for Location");
		searchBt.setToggleGroup(toggleGroup);
		insertGP.add(searchBt, 3, 1);

		hbBt.getChildren().addAll(insertBt, updateBt, searchBt);

		bPane.setTop(hbBt);

		addBt = new Button(" Insert ");

		upDeleteBT = new ComboBox<>();
		resLabel = new Label("");

		insertBt.setOnAction(n -> { // insert ******
			if (insertBt.isSelected()) {
				insertGP = new GridPane();
				locationTxt = new TextField();
				resLabel.setText("");
				insertGP.add(resLabel, 1, 0);
				insertGP.add(new Label("New Location :"), 0, 2);
				locationTxt.setPromptText("Enter Location ");
				insertGP.add(locationTxt, 2, 2);
				bPane.setCenter(insertGP);
				insertGP.add(addBt, 1, 3);
			}
			addBt.setOnAction(k -> { // add button *****************
				if (insertBt.isSelected()) {
					if (locationTxt.getText() != null && locationTxt.getText() != "") {
						if (DLinkedList != null)
							DLinkedList.insertSorted(new Location(locationTxt.getText().trim()));
						resLabel.setText("");
						resLabel.setText("Location " + locationTxt.getText().trim() + " insert successfully !");
					}
				}
			});
		});

		updateBt.setOnAction(n -> { // update ***************
			if (updateBt.isSelected()) {
				insertGP = new GridPane();
				insertGP.setHgap(10);
				insertGP.setVgap(10);
				locationTxt = new TextField();
				newLocationTxt = new TextField();
				upDeleteBT = new ComboBox<>();

				upDeleteBT.getItems().addAll(" Update ", " Delete ");
				insertGP.add(upDeleteBT, 3, 2);
				insertGP.add(new Label("select the operation :"), 0, 2);
				Label newLocLable = new Label("New Location");

				upDeleteBT.setOnAction(d -> { // inside update combo box
					if (upDeleteBT.getValue().equals(" Update ")) {
						insertGP.getChildren().remove(addBt);
						insertGP.getChildren().remove(locationTxt);
						resLabel = new Label("");
						locationTxt = new TextField();

						insertGP.add(resLabel, 1, 0);
						addBt = new Button("update");
						insertGP.add(addBt, 1, 5);
						insertGP.add(new Label("Enter Location : "), 0, 3);
						locationTxt.setPromptText("Enter Location ");
						insertGP.add(locationTxt, 1, 3);
						insertGP.add(newLocLable, 0, 4);
						newLocationTxt.setPromptText("Enter new Location");
						insertGP.add(newLocationTxt, 1, 4);

						addBt.setOnAction(w -> { // add *************
							if (upDeleteBT.getValue().equals(" Update ")) {
								Location temp = DLinkedList.findData(new Location(locationTxt.getText().trim()));
								if (newLocationTxt.getText() != null && temp != null) {
									temp.setLocation(newLocationTxt.getText().trim()); // update text !
									resLabel = new Label("");
									insertGP.add(resLabel, 1, 0);
									resLabel.setText(
											"Location " + locationTxt.getText().trim() + " updated successfully !");
								} else {
									resLabel = new Label("");
									insertGP.add(resLabel, 1, 0);
									resLabel.setText(
											"Location " + locationTxt.getText().trim() + " does not exist. !!");

								}

							}
						});

					} else if (upDeleteBT.getValue().equals(" Delete ")) {
						insertGP.getChildren().remove(addBt);
						insertGP.getChildren().remove(locationTxt);
						insertGP.getChildren().remove(newLocationTxt);
						insertGP.getChildren().remove(newLocLable);
						resLabel = new Label("");
						insertGP.add(resLabel, 1, 0);
						locationTxt = new TextField();

						addBt = new Button("delete");
						insertGP.add(addBt, 1, 5);
						insertGP.add(new Label("Enter Location : "), 0, 3);
						locationTxt.setPromptText("Enter Location ");
						insertGP.add(locationTxt, 1, 3);

						addBt.setOnAction(s -> { // add *************
							if (updateBt.isSelected()) {
								if (upDeleteBT.getValue().equals(" Delete ")) {
									Location temp = DLinkedList
											.deleteSorted(new Location(locationTxt.getText().trim()));
									if (temp == null) {
										resLabel = new Label("");
										insertGP.add(resLabel, 1, 0);
										resLabel.setText(
												"Location " + locationTxt.getText().trim() + " does not exist. !!");
									} else {
										resLabel = new Label("");
										insertGP.add(resLabel, 1, 0);

										resLabel.setText(
												"Location " + locationTxt.getText().trim() + " deleted successfully !");
									}
								}
							}

						});

					}
				});
				bPane.setCenter(insertGP);
			}
		});

		setContent(bPane);

		searchBt.setOnAction(s -> {
			if (searchBt.isSelected()) {
				insertGP = new GridPane();
				insertGP.setHgap(10);
				insertGP.setVgap(10);
				TextField name = new TextField();
				Label res = new Label("   ");
				insertGP.add(res, 0, 4);
				name.setPromptText("Enter Name .................");
				insertGP.add(new Label("Enter Location name : "), 0, 0);
				insertGP.add(name, 1, 0);
				Button searBt = new Button(" Search ");
				insertGP.add(searBt, 1, 1);
				searBt.setOnAction(b -> {

					DNode<Location> dNode = DLinkedList.findNode(new Location(name.getText().trim()));
					Location temp = null;
					if (dNode != null)
						temp = dNode.getData();

					if (temp == null) {
						Alert alertLoc = new Alert(AlertType.ERROR);
						alertLoc.setTitle("location Error !!!");
						alertLoc.setHeaderText("Error: Unable find location");
						alertLoc.setContentText(
								"The location you are trying to find it does not exist , please try again.");
						alertLoc.showAndWait();
					} else {
						insertTableView(temp); // sent location to second tab bro
						tab3.setDNode(dNode); // also send dnode to lab3
						secondTab.setLocation(temp);
						tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
							if (newValue != null) {
								if (secondTab != null)
									secondTab.setRowData(newValue);
								int tabIndex = tabPane.getTabs().indexOf(this);
								tabPane.getSelectionModel().select(tabIndex + 1);
							}
						});

					}
				});

				bPane.setCenter(insertGP);
			}
		});

	}

	public DoublyLinkedList<Location> getDLinkedList() {
		return DLinkedList;
	}

	public void setDLinkedList(DoublyLinkedList<Location> dLinkedList) {
		DLinkedList = dLinkedList;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public void setNextTab(Tab2 secondTab) {
		this.secondTab = secondTab;
	}

	public void setTab3(Tab3 tab3) {
		this.tab3 = tab3;
	}

	private void insertTableView(Location location) { // put data in table view ****************
		tableView = new TableView<>();
		LinkedList<Martyr> singleList = location.getMartyrList();
		Node<Martyr> curr = singleList.getHead().getNext();
		while (curr != null) {
			tableView.getItems().add(curr.getData());
			curr = curr.getNext();
		}
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
}
