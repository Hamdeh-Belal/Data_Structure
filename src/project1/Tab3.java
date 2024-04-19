package project1;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Tab3 extends Tab {

	private DoublyLinkedList<Location> dLinkedList;
	private Location location;
	private Label currLocation;
	private LinkedList<Martyr> singleList;
	private BorderPane bPane;
	private HBox hbBt;
	private VBox vbBt;
	private HBox hbLoc;
	private GridPane gPane;
	private Button searchBt;
	private TextArea txtArea;
	private Button prevLoc;
	private Button nextLoc;
	private DNode<Location> dNode;

	public Tab3(DoublyLinkedList<Location> dLinkedList) {
		super("Statistics");
		setdLinkedList(dLinkedList);
		if (location == null) {
			dNode = dLinkedList.getHead().getNext();
			location = dNode.getData();
			singleList = location.getMartyrList();
		}

		currLocation = new Label("current Location : " + location.getLocation());
		bPane = new BorderPane();
		bPane.setPadding(new Insets(10));
		gPane = new GridPane();
		bPane.setCenter(gPane);
		RadioButton ageBt = new RadioButton("Age");
		RadioButton genderBt = new RadioButton("Gender");
		RadioButton dateBt = new RadioButton("Date");
		hbBt = new HBox(10);
		hbBt.getChildren().addAll(ageBt, genderBt, dateBt);
		vbBt = new VBox(10);
		vbBt.getChildren().add(hbBt);
		bPane.setTop(vbBt);
		searchBt = new Button();
		ToggleGroup toggleGroup = new ToggleGroup();
		ageBt.setToggleGroup(toggleGroup);
		genderBt.setToggleGroup(toggleGroup);
		dateBt.setToggleGroup(toggleGroup);
		hbLoc = new HBox(10);
		prevLoc = new Button("<--Previous");
		nextLoc = new Button(" Next -->");
		hbLoc.getChildren().addAll(currLocation, prevLoc, new Label("           "), nextLoc);
		prevLoc.setAlignment(Pos.CENTER_RIGHT);
		nextLoc.setAlignment(Pos.CENTER_LEFT);
		vbBt.getChildren().add(hbLoc);

		ageBt.setOnAction(z -> {
			if (ageBt.isSelected()) {

				gPane = new GridPane();
				bPane.setCenter(gPane);
				gPane.setHgap(10);
				gPane.setVgap(10);
				if (singleList != null) {
					TextField ageTxt = new TextField();
					ageTxt.setPromptText("Enter Age .......");
					gPane.add(new Label("Enter Age :"), 0, 1);
					gPane.add(ageTxt, 1, 1);
					searchBt = new Button(" Show ");
					gPane.add(searchBt, 3, 1);

					txtArea = new TextArea();
					txtArea.setEditable(false);
					bPane.setBottom(txtArea);

					searchBt.setOnAction(i -> {
						if (ageTxt.getText() != null) {
							int age = 1;
							age = Integer.parseInt(ageTxt.getText().trim());
							if (age >= 0) {
								int count = 0;
								Node<Martyr> curr = singleList.getHead().getNext();
								while (curr != null) {
									if (curr.getData().getAge() == age)
										count++;
									curr = curr.getNext();
								}
								txtArea.setText("The Number of Martyr has age " + age + " : " + count + " ! ");
							} else {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Input Error !!!");
								alert.setHeaderText("Error: Input age Not correct !!");
								alert.setContentText(
										"The input age must be greater than or equle 0 , please try again.");
								alert.showAndWait();
							}
						}
					});

				}
			}
		});

		genderBt.setOnAction(z -> {
			if (genderBt.isSelected()) {
				gPane = new GridPane();
				bPane.setCenter(gPane);
				gPane.setHgap(10);
				gPane.setVgap(10);
				if (singleList != null) {
					TextField genderTxt = new TextField();
					genderTxt.setPromptText("Enter Gender M /F");
					gPane.add(new Label("Enter Gender :"), 0, 1);
					gPane.add(genderTxt, 1, 1);
					searchBt = new Button(" Show ");
					gPane.add(searchBt, 3, 1);

					txtArea = new TextArea();
					txtArea.setEditable(false);
					bPane.setBottom(txtArea);

					searchBt.setOnAction(i -> {
						if (genderTxt.getText() != null) {

							char gender = 'M';
							gender = genderTxt.getText().trim().charAt(0);
							if (gender == 'm' || gender == 'M' || gender == 'f' || gender == 'F') {
								switch (gender) {
								case 'm':
									gender = 'M';
									break;
								case 'f':
									gender = 'F';
									break;
								}
								int count = 0;
								Node<Martyr> curr = singleList.getHead().getNext();
								while (curr != null) {
									if (curr.getData().getGender() == gender)
										count++;
									curr = curr.getNext();
								}
								txtArea.setText("The Number of Martyr has age " + gender + " : " + count + " ! ");

							} else {

								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Input Error !!!");
								alert.setHeaderText("Error: Input gender Not correct !!");
								alert.setContentText("The input gender must be M or F , please try again.");
								alert.showAndWait();
							}
						}
					});

				}
			}
		});

		dateBt.setOnAction(z -> {
			if (dateBt.isSelected()) {
				gPane = new GridPane();
				bPane.setCenter(gPane);
				gPane.setHgap(10);
				gPane.setVgap(10);
				if (singleList != null) {
					searchBt = new Button(" Show ");
					gPane.add(searchBt, 3, 2);

					txtArea = new TextArea();
					txtArea.setEditable(false);
					bPane.setBottom(txtArea);

					searchBt.setOnAction(i -> {

						Node<Martyr> curr = singleList.getHead().getNext();
						if (curr != null) {
							int count = 0;
							int textCo = 0;
							int max = 0;
							Date maxDate = null;
							Date date = curr.getData().getDateOfDeath();
							while (curr != null) {
								if (curr.getNext() == null) {
									count++;
									break;
								}
								if (date.equals(curr.getNext().getData().getDateOfDeath())) {
									count++;
									textCo++;
								} else {
									count++;
									if (count > max) {
										maxDate = date;
										max = count;
										count = 0;
										date = curr.getNext().getData().getDateOfDeath();
									} else {
										count = 0;
										date = curr.getNext().getData().getDateOfDeath();

									}
								}

								curr = curr.getNext();
							}
							if (maxDate != null) {
								SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
								String formattedDate = dateFormat.format(maxDate);
								txtArea.setText("The date that has the maximum number of martyrs : " + formattedDate
										+ " , and it repeat : " + max + " !");

							} else {

								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle(" Error !!!");
								alert.setHeaderText("Error: The list is empty !");
								alert.setContentText(
										"The Location don't has any martyr , please try another location .");
								alert.showAndWait();

							}
						}
					});

				}
			}
		});

		prevLoc.setOnAction(s -> {
			if (dNode.getPrev() != null && dNode.getPrev().getData() != null) {
				dNode = dNode.getPrev();
				location = dNode.getData();
				singleList = location.getMartyrList();
				currLocation.setText("current Location : " + location.getLocation());
			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Location Error !!!");
				alert.setHeaderText("Error: This is the first location in the list !!");
				alert.showAndWait();
			}

		});

		nextLoc.setOnAction(s -> {
			if (dNode.getNext() != null) {
				dNode = dNode.getNext();
				location = dNode.getData();
				singleList = location.getMartyrList();

				currLocation.setText("current Location : " + location.getLocation());

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Location Error !!!");
				alert.setHeaderText("Error: This is the last location in the list !!");
				alert.showAndWait();
			}

		});

		setContent(bPane);
	}

	public void setdLinkedList(DoublyLinkedList<Location> dLinkedList) {
		this.dLinkedList = dLinkedList;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setDNode(DNode<Location> dNode) {
		if (dNode != null)
			this.dNode = dNode;
		location = dNode.getData();
		singleList = location.getMartyrList();
		currLocation.setText("current Location : " + location.getLocation());

	}

}
