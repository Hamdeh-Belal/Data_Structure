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
public class BrandOptions extends Tab {

	private DoublyLinkedList<Brand> dLinkedList;
	private RadioButton addRT;
	private RadioButton deleteRT;
	private RadioButton updateRT;
	private RadioButton searchRT;
	private BorderPane bPane;
	private TableView<Brand> tableView;

	/**
	 * @param dLinkedList
	 */
	public BrandOptions(DoublyLinkedList<Brand> dLinkedList) {
		super(" Brand ");
		this.dLinkedList = dLinkedList;

		bPane = new BorderPane();
		bPane.setPadding(new Insets(10));

		ToggleGroup toggleGroup = new ToggleGroup();

		ImageView imageView = new ImageView(new Image("C:\\BelalWorkSpace\\DataStructure\\brandImage.jpg"));
		imageView.setPreserveRatio(true);
		imageView.fitWidthProperty().bind(bPane.widthProperty().divide(1.5));
		imageView.fitHeightProperty().bind(bPane.heightProperty().divide(1.5));

		bPane.setCenter(imageView);
		BorderPane.setAlignment(imageView, Pos.CENTER);

		addRT = new RadioButton(" Add ");
		addRT.setToggleGroup(toggleGroup);
		deleteRT = new RadioButton(" Delete ");
		deleteRT.setToggleGroup(toggleGroup);
		updateRT = new RadioButton(" Update ");
		updateRT.setToggleGroup(toggleGroup);
		searchRT = new RadioButton(" Search ");
		searchRT.setToggleGroup(toggleGroup);

		Button showData = new Button(" Show Data ");

		HBox hbRT = new HBox(10);
		hbRT.getChildren().addAll(addRT, deleteRT, updateRT, searchRT, showData);
		bPane.setTop(hbRT);

		/*
		 * Action's
		 */

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

				gPane.add(new Label("Enter New Brand :"), 0, 1);

				/*
				 * Adding TextFeild
				 */

				TextField brandTxt = new TextField();
				brandTxt.setPromptText("Enter New Brand ... ");
				gPane.add(brandTxt, 1, 1);

				/*
				 * add Button
				 */
				Button addBt = new Button(" Add ");
				gPane.add(addBt, 1, 3);

				/*
				 * Action on Button
				 */
				addBt.setOnAction(i -> {
					if (brandTxt.getText() != "") {
						Brand brand = new Brand(brandTxt.getText().trim() + "");
						Brand tempB = dLinkedList.findData(brand);
						if (tempB == null) {
							dLinkedList.insertSorted(brand);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Add Successful");
							alert.setContentText(
									"The Brand " + brandTxt.getText().trim() + " \n successfully adding to the list.");
							alert.showAndWait();
						} else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Adding Unsuccessfully");
							alert.setContentText("The Bread you Try to Add it is Already exist ! , please try Again");
							alert.showAndWait();
						}
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

				gPane.add(new Label("Enter Brand Name :"), 0, 1);

				/*
				 * Adding TextFeild
				 */

				TextField brandTxt = new TextField();
				brandTxt.setPromptText("Enter Brand ... ");
				gPane.add(brandTxt, 1, 1);

				/*
				 * delete Button
				 */
				Button deleteBt = new Button(" Delete ");
				gPane.add(deleteBt, 1, 3);

				/*
				 * Action on Button
				 */
				deleteBt.setOnAction(i -> {
					if (brandTxt.getText() != "") {
						Brand tempB = dLinkedList.findData(new Brand(brandTxt.getText().trim() + ""));
						if (tempB != null) {
							dLinkedList.deleteSorted(tempB);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Delete Successful");
							alert.setContentText("The Brand " + tempB.toString() + " successfully deleting .");
							alert.showAndWait();
						} else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Delete Unsuccessfully");
							alert.setContentText(
									"The Bread you Try to Delete it is Does NOT exist ! , please try Again");
							alert.showAndWait();
						}
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

				gPane.add(new Label("Enter New Old Brand :"), 0, 1);
				gPane.add(new Label("Enter New New Brand :"), 0, 2);

				/*
				 * Adding TextFeild
				 */

				TextField oldBrandTxt = new TextField();
				TextField NewBrandTxt = new TextField();
				oldBrandTxt.setPromptText("Enter old Brand ... ");
				NewBrandTxt.setPromptText("Enter New Brand ... ");
				gPane.add(oldBrandTxt, 1, 1);
				gPane.add(NewBrandTxt, 1, 2);
				/*
				 * update Button
				 */
				Button updateBt = new Button(" Update ");
				gPane.add(updateBt, 1, 4);

				/*
				 * Action on Button
				 */
				updateBt.setOnAction(i -> {
					if (oldBrandTxt.getText() != "" && NewBrandTxt.getText() != "") {
						Brand tempO = dLinkedList.findData(new Brand(oldBrandTxt.getText().trim() + ""));
						Brand tempN = dLinkedList.findData(new Brand(NewBrandTxt.getText().trim() + ""));

						if (tempO != null && tempN == null) {
							tempO.setBrand(NewBrandTxt.getText().trim() + "");
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("UPdate Successful");
							alert.setContentText("The Brand " + oldBrandTxt.getText().trim()
									+ " successfully Update to \n " + NewBrandTxt.getText().trim() + ".");
							alert.showAndWait();

						} else if (tempO == null) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Updating Unsuccessfully");
							alert.setContentText("The Bread you Try to Update it is Not exist ! , please try Again");
							alert.showAndWait();
						} else if (tempN != null) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Updating Unsuccessfully");
							alert.setContentText("The new Value of Brand is Already exist ! , please try Again");
							alert.showAndWait();
						}
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

				gPane.add(new Label("Enter Brand :"), 0, 1);

				/*
				 * Adding TextFeild
				 */

				TextField brandTxt = new TextField();
				brandTxt.setPromptText("Enter Brand Name ... ");
				gPane.add(brandTxt, 1, 1);

				/*
				 * search Button
				 */
				Button seachBt = new Button(" Search ");
				gPane.add(seachBt, 1, 3);

				/*
				 * Action on Button
				 */
				seachBt.setOnAction(i -> {
					if (brandTxt.getText() != "") {
						Brand tempB = dLinkedList.findData(new Brand(brandTxt.getText().trim() + ""));
						if (tempB != null) {
							TextArea txtArea = new TextArea();
							txtArea.setEditable(false);
							txtArea.setText(tempB.toString());
							bPane.setBottom(txtArea);
						} else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Search Unsuccessfully");
							alert.setContentText("The Bread you Try to Find it Does Not exist ! , please try Again");
							alert.showAndWait();
						}
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
	}

	private void showData() {

		tableView = new TableView<>();
		DNode<Brand> curr = dLinkedList.getHead().getNext();
		while (curr != null) {
			tableView.getItems().add(curr.getData());
			curr = curr.getNext();
		}

		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn<Brand, String> brandColumn = new TableColumn<>("Brand Name");
		brandColumn.setMinWidth(40);
		brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

		tableView.getColumns().add(brandColumn);

		bPane.setCenter(tableView);

	}

}
