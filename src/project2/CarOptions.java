/**
 * 
 */
package project2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Belal
 *
 */
public class CarOptions extends Tab {

	private DoublyLinkedList<Brand> dLinkedList;
	private LinkedList<Car> sLinkedList;
	private RadioButton addRT;
	private RadioButton deleteRT;
	private RadioButton updateRT;
	private RadioButton searchRT;
	private BorderPane bPane;
	private HBox hb;
	private Button cancel;
	private TableView<Car> tableView;

	/**
	 * @param dLinkedList
	 */
	public CarOptions(DoublyLinkedList<Brand> dLinkedList) {
		super(" Car ");
		this.dLinkedList = dLinkedList;

		bPane = new BorderPane();
		bPane.setPadding(new Insets(15));
		setContent(bPane);
		hb = new HBox(15);
		TextField brandTxt = new TextField();
		Button searchBBt = new Button(" Search ");

		cancel = new Button(" Cancel ");

		hb.getChildren().addAll(new Label("Enter Brand Name : "), brandTxt, searchBBt);
		bPane.setTop(hb);
		searchBBt.setOnAction(q -> {
			if (brandTxt.getText() != "") {
				Brand tempB = dLinkedList.findData(new Brand(brandTxt.getText().trim() + ""));
				if (tempB != null) {
					sLinkedList = tempB.getCarList();

					ImageView imageView = new ImageView(new Image("C:\\BelalWorkSpace\\DataStructure\\carImage.png"));
					imageView.setPreserveRatio(true);
					imageView.fitWidthProperty().bind(bPane.widthProperty().divide(1.5));
					imageView.fitHeightProperty().bind(bPane.heightProperty().divide(1.5));

					bPane.setCenter(imageView);
					BorderPane.setAlignment(imageView, Pos.CENTER);

					ToggleGroup toggleGroup = new ToggleGroup();

					addRT = new RadioButton(" Add ");
					addRT.setToggleGroup(toggleGroup);
					deleteRT = new RadioButton(" Delete ");
					deleteRT.setToggleGroup(toggleGroup);
					updateRT = new RadioButton(" Update ");
					updateRT.setToggleGroup(toggleGroup);
					searchRT = new RadioButton(" Search ");
					searchRT.setToggleGroup(toggleGroup);

					Button showData = new Button(" Show Data ");

					hb = new HBox(10);
					hb.getChildren().addAll(addRT, deleteRT, updateRT, searchRT, showData, cancel);
					bPane.setTop(hb);

					/*
					 * Action's
					 */
					cancel.setOnAction(s -> {
						hb.getChildren().removeAll(addRT, deleteRT, updateRT, searchRT, showData, cancel);
						hb.getChildren().addAll(new Label("Enter Brand Name : "), brandTxt, searchBBt);
						bPane.setCenter(imageView);
						bPane.setBottom(null);

					});
					addRT.setOnAction(e -> {
						if (addRT.isSelected()) {
							bPane.setBottom(null);
							GridPane gPane = new GridPane();
							gPane.setVgap(15);
							gPane.setHgap(15);
							bPane.setCenter(gPane);
							/*
							 * adding Labels
							 */

							gPane.add(new Label("Enter New Car Model :"), 0, 1);
							gPane.add(new Label("Enter New Car Year :"), 0, 2);
							gPane.add(new Label("Enter New Car Color :"), 0, 3);
							gPane.add(new Label("Enter New Car Price :"), 0, 4);

							/*
							 * Adding TextFeild
							 */

							TextField carTxt = new TextField();
							carTxt.setPromptText("Enter New Car Model ... ");
							gPane.add(carTxt, 1, 1);

							TextField yearTxt = new TextField();
							yearTxt.setPromptText("Enter New Car Year ... ");
							gPane.add(yearTxt, 1, 2);

							TextField colorTxt = new TextField();
							colorTxt.setPromptText("Enter New Car Color ... ");
							gPane.add(colorTxt, 1, 3);

							TextField priceTxt = new TextField();
							priceTxt.setPromptText("Enter New Car Price ... ");
							gPane.add(priceTxt, 1, 4);

							/*
							 * add Button
							 */
							Button addBt = new Button(" Add ");
							gPane.add(addBt, 2, 6);

							/*
							 * Action on Button
							 */
							addBt.setOnAction(i -> {
								if (carTxt.getText() != "" && yearTxt.getText() != "" && colorTxt.getText() != ""
										&& priceTxt.getText() != "") {
									Car car = new Car(carTxt.getText().trim(), yearTxt.getText().trim(),
											colorTxt.getText().trim(), priceTxt.getText().trim());
									Car tempC = sLinkedList.findData(car);
									if (tempC == null) {
										sLinkedList.insertSorted(car);
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Add Successful");
										alert.setContentText(
												"The Car " + car.toString() + " \n successfully adding to the list.");
										alert.showAndWait();
									} else {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Adding Unsuccessfully");
										alert.setContentText(
												"The Car you Try to Add it is Already exist ! , please try Again");
										alert.showAndWait();
									}
								} else {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Add Unsuccessfully");
									alert.setContentText("The The Text Field Not Correctly fill , please try Again");
									alert.showAndWait();
								}
							});
						}
					});

					deleteRT.setOnAction(s -> {
						if (deleteRT.isSelected()) {
							bPane.setBottom(null);
							GridPane gPane = new GridPane();
							gPane.setVgap(15);
							gPane.setHgap(15);
							bPane.setCenter(gPane);
							/*
							 * adding Labels
							 */

							gPane.add(new Label("Enter Car Model :"), 0, 1);
							gPane.add(new Label("Enter Car Year :"), 0, 2);
							gPane.add(new Label("Enter Car Color :"), 0, 3);
							gPane.add(new Label("Enter Car Price :"), 0, 4);

							/*
							 * Adding TextFeild
							 */

							TextField carTxt = new TextField();
							carTxt.setPromptText("Enter Car Model ... ");
							gPane.add(carTxt, 1, 1);

							TextField yearTxt = new TextField();
							yearTxt.setPromptText("Enter Car Year ... ");
							gPane.add(yearTxt, 1, 2);

							TextField colorTxt = new TextField();
							colorTxt.setPromptText("Enter Car Color ... ");
							gPane.add(colorTxt, 1, 3);

							TextField priceTxt = new TextField();
							priceTxt.setPromptText("Enter Car Price ... ");
							gPane.add(priceTxt, 1, 4);

							/*
							 * delete Button
							 */
							Button deleteBt = new Button(" Delete ");
							gPane.add(deleteBt, 1, 6);

							/*
							 * Action on Button
							 */
							deleteBt.setOnAction(i -> {
								if (carTxt.getText() != "" && yearTxt.getText() != "" && colorTxt.getText() != ""
										&& priceTxt.getText() != "") {
									Car car = new Car(carTxt.getText().trim(), yearTxt.getText().trim(),
											colorTxt.getText().trim(), priceTxt.getText().trim());
									Car tempC = sLinkedList.findData(car);
									if (tempC != null) {
										sLinkedList.deleteSorted(tempC);
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Delete Successful");
										alert.setContentText("The Car " + car.toString() + " successfully deleting .");
										alert.showAndWait();
									} else {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Delete Unsuccessfully");
										alert.setContentText(
												"The Car you Try to Delete it is Does NOT exist ! , please try Again");
										alert.showAndWait();
									}
								} else {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Delete Unsuccessfully");
									alert.setContentText("The The Text Field Not Correctly fill , please try Again");
									alert.showAndWait();
								}
							});
						}
					});

					updateRT.setOnAction(e -> {
						if (updateRT.isSelected()) {
							bPane.setBottom(null);
							GridPane gPane = new GridPane();
							gPane.setVgap(15);
							gPane.setHgap(15);
							bPane.setCenter(gPane);
							/*
							 * adding Labels
							 */
							gPane.add(new Label("Enter Old Car Model :"), 0, 1);
							gPane.add(new Label("Enter Old Car Year :"), 0, 2);
							gPane.add(new Label("Enter Old Car Color :"), 0, 3);
							gPane.add(new Label("Enter Old Car Price :"), 0, 4);

							gPane.add(new Label("Enter New Car Model :"), 2, 1);
							gPane.add(new Label("Enter New Car Year :"), 2, 2);
							gPane.add(new Label("Enter New Car Color :"), 2, 3);
							gPane.add(new Label("Enter New Car Price :"), 2, 4);

							/*
							 * Adding TextFeild
							 */
							TextField carTxt = new TextField();
							carTxt.setPromptText("Enter Old Car Model ... ");
							gPane.add(carTxt, 1, 1);

							TextField yearTxt = new TextField();
							yearTxt.setPromptText("Enter Old Car Year ... ");
							gPane.add(yearTxt, 1, 2);

							TextField colorTxt = new TextField();
							colorTxt.setPromptText("Enter Old Car Color ... ");
							gPane.add(colorTxt, 1, 3);

							TextField priceTxt = new TextField();
							priceTxt.setPromptText("Enter Old Car Price ... ");
							gPane.add(priceTxt, 1, 4);
							// *****************************************************
							TextField newCarTxt = new TextField();
							newCarTxt.setPromptText("Enter New Car Model ... ");
							gPane.add(newCarTxt, 4, 1);

							TextField newYearTxt = new TextField();
							newYearTxt.setPromptText("Enter New Car Year ... ");
							gPane.add(newYearTxt, 4, 2);

							TextField newColorTxt = new TextField();
							newColorTxt.setPromptText("Enter New Car Color ... ");
							gPane.add(newColorTxt, 4, 3);

							TextField newPriceTxt = new TextField();
							newPriceTxt.setPromptText("Enter New Car Price ... ");
							gPane.add(newPriceTxt, 4, 4);
							/*
							 * update Button
							 */
							Button updateBt = new Button(" Update ");
							gPane.add(updateBt, 1, 6);

							/*
							 * Action on Button
							 */
							updateBt.setOnAction(i -> {
								if (carTxt.getText() != "" && yearTxt.getText() != "" && colorTxt.getText() != ""
										&& priceTxt.getText() != "" && newCarTxt.getText() != ""
										&& newYearTxt.getText() != "" && newColorTxt.getText() != ""
										&& newPriceTxt.getText() != "") {

									Car tempO = sLinkedList
											.findData(new Car(carTxt.getText().trim(), yearTxt.getText().trim(),
													colorTxt.getText().trim(), priceTxt.getText().trim()));
									Car tempN = sLinkedList
											.findData(new Car(newCarTxt.getText().trim(), newYearTxt.getText().trim(),
													newColorTxt.getText().trim(), newPriceTxt.getText().trim()));

									if (tempO != null && tempN == null) {
										tempO.UpdateAll(newCarTxt.getText().trim(), newYearTxt.getText().trim(),
												newColorTxt.getText().trim(), newPriceTxt.getText().trim());
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("UPdate Successful");
										alert.setContentText("The Car " + tempO.toString()
												+ " successfully Update to \n " + tempN + ".");
										alert.showAndWait();

									} else if (tempO == null) {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Updating Unsuccessfully");
										alert.setContentText(
												"The Car you Try to Update it is Not exist ! , please try Again");
										alert.showAndWait();
									} else if (tempN != null) {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Updating Unsuccessfully");
										alert.setContentText(
												"The new Value of Car is Already exist ! , please try Again");
										alert.showAndWait();
									}
								} else {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Update Unsuccessfully");
									alert.setContentText("The The Text Field Not Correctly fill , please try Again");
									alert.showAndWait();
								}
							});
						}
					});

					searchRT.setOnAction(e -> {
						if (searchRT.isSelected()) {
							bPane.setBottom(null);
							GridPane gPane = new GridPane();
							gPane.setVgap(15);
							gPane.setHgap(15);
							bPane.setCenter(gPane);
							/*
							 * adding Labels
							 */

							gPane.add(new Label("Enter Car Model :"), 0, 1);
							gPane.add(new Label("Enter Car Year :"), 0, 2);
							gPane.add(new Label("Enter Car Color :"), 0, 3);
							gPane.add(new Label("Enter Car Price :"), 0, 4);

							/*
							 * Adding TextFeild
							 */

							TextField carTxt = new TextField();
							carTxt.setPromptText("Enter Car Model ... ");
							gPane.add(carTxt, 1, 1);

							TextField yearTxt = new TextField();
							yearTxt.setPromptText("Enter Car Year ... ");
							gPane.add(yearTxt, 1, 2);

							TextField colorTxt = new TextField();
							colorTxt.setPromptText("Enter Car Color ... ");
							gPane.add(colorTxt, 1, 3);

							TextField priceTxt = new TextField();
							priceTxt.setPromptText("Enter Car Price ... ");
							gPane.add(priceTxt, 1, 4);

							/*
							 * search Button
							 */
							Button seachBt = new Button(" Search ");
							gPane.add(seachBt, 1, 6);

							/*
							 * Action on Button
							 */
							seachBt.setOnAction(i -> {
								if (carTxt.getText() != "" && yearTxt.getText() != "" && colorTxt.getText() != ""
										&& priceTxt.getText() != "") {
									Car car = new Car(carTxt.getText().trim(), yearTxt.getText().trim(),
											colorTxt.getText().trim(), priceTxt.getText().trim());
									Car tempC = sLinkedList.findData(car);
									if (tempC != null) {
										TextArea txtArea = new TextArea();
										txtArea.setEditable(false);
										txtArea.setText(tempC.toString());
										bPane.setBottom(txtArea);
									} else {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Search Unsuccessfully");
										alert.setContentText(
												"The Car you Try to Find it Does Not exist ! , please try Again");
										alert.showAndWait();
									}
								} else {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Search Unsuccessfully");
									alert.setContentText("The The Text Field Not Correctly fill , please try Again");
									alert.showAndWait();
								}
							});
						}
					});

					showData.setOnAction(e -> {
						if (dLinkedList != null) {
							addRT.setSelected(false);
							deleteRT.setSelected(false);
							updateRT.setSelected(false);
							searchRT.setSelected(false);
							bPane.setBottom(null);
							showData();
						}
					});

					setContent(bPane);

				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Search Unsuccessfully");
					alert.setContentText("The Bread you Try to Find it Does Not exist ! , please try Again");
					alert.showAndWait();
				}

			}
		});

	}

	private void showData() {

		tableView = new TableView<>();
		Node<Car> curr = sLinkedList.getHead().getNext();
		while (curr != null) {
			tableView.getItems().addAll(curr.getData());
			curr = curr.getNext();
		}

		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Car, String> modelColumn = new TableColumn<>("Model");
		modelColumn.setMinWidth(40);
		modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

		TableColumn<Car, String> yearColumn = new TableColumn<>("Year");
		yearColumn.setMinWidth(40);
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

		TableColumn<Car, String> colorColumn = new TableColumn<>("Color");
		colorColumn.setMinWidth(40);
		colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

		TableColumn<Car, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setMinWidth(40);
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		tableView.getColumns().addAll(modelColumn, yearColumn, colorColumn, priceColumn);

		bPane.setCenter(tableView);

	}

}
