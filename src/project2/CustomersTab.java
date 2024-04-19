/**
 * 
 */
package project2;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * @author Belal Hamdeh
 *
 */
public class CustomersTab extends Tab {

	private DoublyLinkedList<Brand> dLinkedList;
	private CircularQueue<Customers> queue;
	private LinkedList<Car> sLinkedList;
	private LinkedList<Car> sTempLinkedList;
	private BorderPane bPane;
	private HBox hb;
	private ComboBox<String> brandCB;
	private ComboBox<String> modelCB;
	private ComboBox<String> yearCB;
	private ComboBox<String> colorCB;
	private ComboBox<String> priceCB;
	private Button selectBt;
	private TextField custNameTxt;
	private TextField custMobileTxt;

	public CustomersTab(DoublyLinkedList<Brand> dLinkedList, CircularQueue<Customers> queue) {
		super(" Customer ");
		this.dLinkedList = dLinkedList;
		this.queue = queue;
		selectBt = new Button(" Select ");
		bPane = new BorderPane();
		bPane.setPadding(new Insets(15));

		Button start = new Button(" START ");
		hb = new HBox(15);

		GridPane gPane = new GridPane();
		gPane.add(new Label("Enter Customer Name :      "), 0, 0);
		gPane.add(new Label("Enter Customer Mobile Num :"), 0, 1);

		custNameTxt = new TextField();
		custMobileTxt = new TextField();
		gPane.add(custNameTxt, 2, 0);
		gPane.add(custMobileTxt, 2, 1);

		/*
		 * add Combo Box's
		 */
		brandCB = new ComboBox<>();
		modelCB = new ComboBox<>();
		yearCB = new ComboBox<>();
		colorCB = new ComboBox<>();
		priceCB = new ComboBox<>();

		Pane pane = new Pane();
		pane.getChildren().add(start);
		setContent(pane);
		start.setOnAction(y -> {
			setContent(bPane);

			bPane.setTop(gPane);

			/*
			 * add data to Combo Box's
			 */

			bPane.setCenter(hb);
			addCB(brandCB, modelCB, yearCB, colorCB, priceCB);
		});

	}

	private void addCB(ComboBox<String> brandCB, ComboBox<String> modelCB, ComboBox<String> yearCB,
			ComboBox<String> colorCB, ComboBox<String> priceCB) {
		/*
		 * add data to CB
		 */

		ObservableList<String> brandOB = FXCollections.observableArrayList();
		DNode<Brand> currBrand = dLinkedList.getHead().getNext();
		while (currBrand != null) {
			brandOB.add(currBrand.getData().getBrand() + "");
			currBrand = currBrand.getNext();
		}
		brandCB.setItems(brandOB);

		hb.getChildren().add(brandCB);

		brandCB.setOnAction(e -> {
			Brand tempBrand = dLinkedList.findData(new Brand(brandCB.getValue() + ""));
			sLinkedList = tempBrand.getCarList();

			ObservableList<String> modelOB = FXCollections.observableArrayList();
			Node<Car> currModel = sLinkedList.getHead().getNext();
			while (currModel != null) {
				modelOB.add(currModel.getData().getModel());
				currModel = currModel.getNext();
			}
			modelCB.setItems(modelOB);

			hb.getChildren().add(modelCB);

			modelCB.setOnAction(s -> {
				ObservableList<String> yearOB = FXCollections.observableArrayList();
				sTempLinkedList = new LinkedList<>();

				Node<Car> currTempModel = sLinkedList.getHead().getNext();
				while (currTempModel != null) {
					if (currTempModel.getData().getModel().equals(modelCB.getValue())) {
						sTempLinkedList.insertSorted(currTempModel.getData());
						yearOB.add(currTempModel.getData().getYear() + "");
					}

					currTempModel = currTempModel.getNext();
				}
				sLinkedList = sTempLinkedList;
				yearCB.setItems(yearOB);
				hb.getChildren().add(yearCB);

				yearCB.setOnAction(p -> {

					ObservableList<String> colorOB = FXCollections.observableArrayList();
					sTempLinkedList = new LinkedList<>();
					Node<Car> currTempyear = sLinkedList.getHead().getNext();
					while (currTempyear != null) {
						if ((currTempyear.getData().getYear() + "").equals(yearCB.getValue())) {
							sTempLinkedList.insertSorted(currTempyear.getData());
							colorOB.add(currTempyear.getData().getColor() + "");
						}

						currTempyear = currTempyear.getNext();
					}
					sLinkedList = sTempLinkedList;

					colorCB.setItems(colorOB);
					hb.getChildren().add(colorCB);

					colorCB.setOnAction(w -> {

						ObservableList<String> priceOB = FXCollections.observableArrayList();
						sTempLinkedList = new LinkedList<>();
						Node<Car> currTempColor = sLinkedList.getHead().getNext();
						while (currTempColor != null) {
							if ((currTempColor.getData().getColor() + "").equals(colorCB.getValue())) {
								sTempLinkedList.insertSorted(currTempColor.getData());
								priceOB.add(currTempColor.getData().getPrice() + "");
							}

							currTempColor = currTempColor.getNext();
						}
						sLinkedList = sTempLinkedList;

						priceCB.setItems(priceOB);
						hb.getChildren().add(priceCB);

					});
					priceCB.setOnAction(u -> {
						sTempLinkedList = new LinkedList<>();
						Node<Car> currTempPrice = sLinkedList.getHead().getNext();
						while (currTempPrice != null) {
							if ((currTempPrice.getData().getPrice() + "").equals(priceCB.getValue())) {
								sTempLinkedList.insertSorted(currTempPrice.getData());
							}

							currTempPrice = currTempPrice.getNext();
						}
						sLinkedList = sTempLinkedList;
						bPane.setBottom(selectBt);
						selectBt.setOnAction(d -> {

							if (custNameTxt.getText() != "" && custMobileTxt.getText() != "") {
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								String formattedDate = sdf.format(date);
								Customers customer = new Customers(custNameTxt.getText().trim(),
										custMobileTxt.getText().trim(), brandCB.getValue().trim(),
										modelCB.getValue().trim(), yearCB.getValue().trim(), colorCB.getValue().trim(),
										priceCB.getValue().trim(), formattedDate, "InProcess".trim());
								queue.enqueue(customer);

								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Adding Customers done Successful");
								alert.setContentText("The Customer " + customer.toString() + " successfully.");
								alert.showAndWait();
							}

						});
					});

				});

			});

		});

	}

}
