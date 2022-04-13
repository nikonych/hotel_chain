package com.example.hotel_8;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdminWorkersController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddnewWorker;

    @FXML
    private Button Client_list;

    @FXML
    private Button Logout;

    @FXML
    private AnchorPane back;

    @FXML
    private TableColumn<Workers, String> email;

    @FXML
    private Button hotelinfo;

    @FXML
    private TableView<Workers> list;

    @FXML
    private TableColumn<Workers, String> name;

    @FXML
    private TableColumn<Workers, String> passport;

    @FXML
    private TableColumn<Workers, String> post;

    @FXML
    private Button profile;

    @FXML
    private TableColumn<Workers, Integer> salary;

    @FXML
    private Button search;

    @FXML
    private TextField searchText;

    @FXML
    private Text titlename;

    @FXML
    private Button workerinfo;

    @FXML
    private Button workers;

    ObservableList<Workers> oblist = FXCollections.observableArrayList();

    ResultSet rs;

    @FXML
    void initialize() {
        titlename.setText(Data_work.name);




        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(searchText.getText().equals(""))
                        rs = Data_work.conn.createStatement().executeQuery("select * from Workers where Workers.HotelID = " + Data_work.hotelID);
                    else
                        rs = Data_work.conn.createStatement().executeQuery("select * from Workers where Workers.Fullname = '" + searchText.getText() + "' and Workers.HotelID = " + Data_work.hotelID);
                    while (rs.next()){
                        oblist.add(new Workers(rs.getInt("WorkerID"), rs.getString("Fullname"), rs.getString("Passport"), rs.getInt("Salary"), rs.getString("Post"), rs.getString("Email") ));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                name.setCellValueFactory(new PropertyValueFactory<>("name"));
                passport.setCellValueFactory(new PropertyValueFactory<>("passport"));
                salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                post.setCellValueFactory(new PropertyValueFactory<>("post"));
                email.setCellValueFactory(new PropertyValueFactory<>("email"));

                list.setItems(oblist);
            }
        }).start();

        search.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                list.getItems().clear();
//                                    SignInBut.getScene().getWindow().hide();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(searchText.getText().equals(""))
                                rs = Data_work.conn.createStatement().executeQuery("select * from Workers where Workers.HotelID = " + Data_work.hotelID);
                            else
                                rs = Data_work.conn.createStatement().executeQuery("select * from Workers where Workers.Fullname = '" + searchText.getText() + "' and Workers.HotelID = " + Data_work.hotelID);
                            while(rs.next()){
                                oblist.add(new Workers(rs.getInt("WorkerID"), rs.getString("Fullname"), rs.getString("Passport"), rs.getInt("Salary"), rs.getString("Post"), rs.getString("Email") ));
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        name.setCellValueFactory(new PropertyValueFactory<>("name"));
                        passport.setCellValueFactory(new PropertyValueFactory<>("passport"));
                        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                        post.setCellValueFactory(new PropertyValueFactory<>("post"));
                        email.setCellValueFactory(new PropertyValueFactory<>("email"));

                        list.setItems(oblist);
                    }
                }).start();


            }
        });

        AddnewWorker.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
//                                    SignInBut.getScene().getWindow().hide();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addnewWorker.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("ABC");
                    stage.setScene(new Scene(root1));
                    stage.show();

                    stage.setOnHiding(new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent event) {
                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    list.getItems().clear();

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                if(searchText.getText().equals(""))
                                                    rs = Data_work.conn.createStatement().executeQuery("select * from Workers where Workers.HotelID = " + Data_work.hotelID);
                                                else
                                                    rs = Data_work.conn.createStatement().executeQuery("select * from Workers where Workers.Fullname = '" + searchText.getText() + "' and Workers.HotelID = " + Data_work.hotelID);
                                                while (rs.next()){
                                                    oblist.add(new Workers(rs.getInt("WorkerID"), rs.getString("Fullname"), rs.getString("Passport"), rs.getInt("Salary"), rs.getString("Post"), rs.getString("Email") ));
                                                }
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }

                                            name.setCellValueFactory(new PropertyValueFactory<>("name"));
                                            passport.setCellValueFactory(new PropertyValueFactory<>("passport"));
                                            salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                                            post.setCellValueFactory(new PropertyValueFactory<>("post"));
                                            email.setCellValueFactory(new PropertyValueFactory<>("email"));

                                            list.setItems(oblist);
                                        }
                                    }).start();
                                }
                            });
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        Logout.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
//                                    SignInBut.getScene().getWindow().hide();
                try {
                    root = FXMLLoader.load(getClass().getResource("log-in.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();


            }
        });

        hotelinfo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
//                                    SignInBut.getScene().getWindow().hide();
                try {
                    root = FXMLLoader.load(getClass().getResource("adminHotel.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();


            }
        });

        workerinfo.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
//                                    SignInBut.getScene().getWindow().hide();
                try {
                    root = FXMLLoader.load(getClass().getResource("adminWorkerInfo.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    System.out.println("baddd");
                }
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();


            }
        });

        Client_list.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
//                                    SignInBut.getScene().getWindow().hide();
                try {
                    root = FXMLLoader.load(getClass().getResource("adminClientsInfo.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    System.out.println("baddd");
                }
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();


            }
        });

        profile.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
//                                    SignInBut.getScene().getWindow().hide();
                try {
                    root = FXMLLoader.load(getClass().getResource("adminProfile.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    System.out.println("baddd");
                }
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add("style.css");
                stage.setScene(scene);
                stage.show();


            }
        });
    }

}
