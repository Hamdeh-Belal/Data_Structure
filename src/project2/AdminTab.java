/**
 * 
 */
package project2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * @author Belal Hamdeh
 *
 */
public class AdminTab extends Tab {

	private static DoublyLinkedList<Brand> dLinkedList;
	private static CircularQueue<Customers> queue;
	private static CStack<Customers> stack;
	private LinkedList<Car> sLinkedList;

	/**
	 * @param arg0
	 */
	public AdminTab(DoublyLinkedList<Brand> dLinkedList, CircularQueue<Customers> queue, CStack<Customers> stack) {
		super(" Admin ");
		this.dLinkedList = dLinkedList;
		this.queue = queue;
		this.stack = stack;
		sLinkedList = new LinkedList<>();
		Button start = new Button("Start !");
		start.setAlignment(Pos.CENTER);
		Pane pane = new Pane();
		pane.getChildren().add(start);
		setContent(pane);
		start.setOnAction(w -> {
			if (dLinkedList != null && queue != null && stack != null && (!queue.isEmpty())) {

				Pane paneTxt = new Pane();
				BorderPane bPane = new BorderPane();
				setContent(bPane);
				bPane.setPadding(new Insets(15));
				/*
				 * Button's
				 */
				Button check = new Button(" Check ");

				Button toQueue = new Button("Confirm Sale");
				Button toStack = new Button("Cancel Sale");
				toStack.setStyle("-fx-background-color: red; -fx-text-fill: white;");
				TextArea txtArea = new TextArea(queue.getFront().toString());

				txtArea.setEditable(false);
				HBox hb = new HBox(10);
				hb.getChildren().addAll(toStack, toQueue);
				hb.setAlignment(Pos.CENTER);

				paneTxt.getChildren().add(txtArea);
				bPane.setTop(new Label(" "));
				BorderPane.setAlignment(paneTxt, Pos.CENTER);
				bPane.setCenter(paneTxt);
				bPane.setRight(check);
				BorderPane.setAlignment(check, Pos.CENTER);
				bPane.setBottom(hb);
				check.setOnAction(o -> {
					if (!(queue.isEmpty())) {
						Customers cust = queue.getFront();
						Brand brand = dLinkedList.findData(new Brand(cust.getBrandName()));

						if (brand != null) {
							sLinkedList = brand.getCarList();
//							Car tempC = new Car(cust.getModel().trim(), cust.getYear(),cust.getColor().trim(), cust.getPrice());
							Car tempC = cust.getCar();

							Car car = sLinkedList.findData(tempC);

							if (car != null) {
								toQueue.setOnAction(e -> {
									if (!queue.isEmpty()) {
										Customers temp = queue.dequeue();
										temp.setOrderStatus("Finished");
										stack.push(temp);

										sLinkedList.deleteSorted(temp.getCar());

										if (!queue.isEmpty())
											txtArea.setText(queue.getFront().toString() + "");

										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Confirm Successfully");
										alert.setContentText("The Customer Order Successfully Confirmed !");
										alert.showAndWait();
									}
								});

								toStack.setOnAction(e -> {
									if (!queue.isEmpty()) {
										Customers temp = queue.dequeue();
										queue.enqueue(temp);
										txtArea.setText(queue.getFront().toString() + "");

										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setTitle("Cancel Successfully");
										alert.setContentText("The Customer Order Successfully Canceled !");
										alert.showAndWait();
									}
								});
							} else {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Check Unsuccessfully");
								alert.setContentText("The Car Does not exist , please try Again");
								alert.showAndWait();
								toStack.setOnAction(e -> {
									if (!queue.isEmpty()) {
										Customers temp = queue.dequeue();
										queue.enqueue(temp);
										txtArea.setText(queue.getFront().toString() + "");

										Alert alert1 = new Alert(AlertType.INFORMATION);
										alert1.setTitle("Cancel Successfully");
										alert1.setContentText("The Customer Order Successfully Canceled !");
										alert1.showAndWait();
									}
								});
							}

						} else {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Check Unsuccessfully");
							alert.setContentText("The Brand Does not exist , please try Again");
							alert.showAndWait();

							toStack.setOnAction(e -> {
								if (!queue.isEmpty()) {
									Customers temp = queue.dequeue();
									queue.enqueue(temp);
									txtArea.setText(queue.getFront().toString() + "");

									Alert alert2 = new Alert(AlertType.INFORMATION);
									alert2.setTitle("Cancel Successfully");
									alert2.setContentText("The Customer Order Successfully Canceled !");
									alert2.showAndWait();
								}
							});
						}
					} else {
						toQueue.setDisable(true);
						toStack.setDisable(true);
						Alert alert4 = new Alert(AlertType.ERROR);
						alert4.setTitle("Confirm Unsuccessfully");
						alert4.setContentText("The Queue is Empty !");
						alert4.showAndWait();

					}
				});
			}
		});

	}

}
