package com.example.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import java.sql.ResultSet;

public class musicPlayerController {
    @FXML
    private VBox vboxMenu;
    @FXML
    private ComboBox<String> genero;
    @FXML
    private ComboBox<String> artista;
    @FXML
    private ComboBox<String> musica;
    @FXML
    private ImageView imgAlbum;
    @FXML
    private Label musicaEmReproducao;
    @FXML
    private Label album;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnAntes;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextField campoDeBusca;
    @FXML
    private void playMusic(){
    }
    @FXML
    private void previousMusic() {
    }

    @FXML
    private void pauseMusic() {
    }

    @FXML
    private void nextMusic() {
    }

    @FXML
    private void campoDeBusca() {
    }

    private final String url = "jdbc:mysql://localhost:3306/musicplayer";
    private final String user = "root";
    private final String psw = "";

    @FXML
    private void onMenuHamburguerClicked() {
        boolean isVisible = artista.isVisible();
        artista.setVisible(!isVisible);
        genero.setVisible(!isVisible);
        musica.setVisible(!isVisible);
    }
    @FXML
    public void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadComboBoxes();

        genero.setOnAction(event -> {
            loadArtista();
            updateMusic();
        });

        artista.setOnAction(event -> {
            loadMusica();
            updateMusic();
        });

        musica.setOnAction(event -> updateMusic());

        btnPlay.setOnAction(event -> playMusic());
        btnAntes.setOnAction(event -> previousMusic());
        btnPause.setOnAction(event -> pauseMusic());
        btnNext.setOnAction(event -> nextMusic());
        campoDeBusca.setOnAction(event -> campoDeBusca());

    }

    private Connection connect() {
        try {
            return DriverManager.getConnection(url, user, psw);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadData() {
        Connection connection = connect();
        if (connection != null) {
        }
    }

    private void updateMusic() {
        String selectedArtista = artista.getValue();
        String selectedMusica = musica.getValue();

        if (selectedArtista != null && selectedMusica != null) {
            String query = "select albuns.nome as album_nome, albuns.imagem as album_imagem " +
                    "from artista " +
                    "join albuns on artista.id = albuns.artista_id " +
                    "join musica on albuns.id = musica.album_id " +
                    "where artista.nome = ? and musica.nome = ?";;

            try (Connection connection = DriverManager.getConnection(url, user, psw);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, selectedArtista);
                statement.setString(2, selectedMusica);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nomeAlbum = resultSet.getString("album_nome");
                    String caminhoImagem = resultSet.getString("album_imagem");

                    album.setText(nomeAlbum);
                    try {
                        Image image = new Image(new File(caminhoImagem).toURI().toString());
                        imgAlbum.setImage(image);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }

                String nomeMusica = selectedMusica;
                musicaEmReproducao.setText(nomeMusica);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadComboBoxes() {
        loadGenero();
        //loadArtista();
        //loadMusica();
    }

    private void loadGenero() {
        String query = "select distinct nome from genero order by nome";

        try (Connection connection = DriverManager.getConnection(url, user, psw);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String value = resultSet.getString("nome");
                genero.getItems().add(value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadArtista() {
        artista.getItems().clear();
        String selectedGenero = genero.getValue();
        String query;
        if (selectedGenero != null) {
            query = String.format("select distinct artista.nome from " +
                    "artista join genero join albuns on artista.id = albuns.artista_id and genero.id = albuns" +
                    ".genero_id where genero.nome = '%s' order by artista.nome", selectedGenero);
        } else {
            query = "select distinct nome from artista order by nome";
        }

        try (Connection connection = DriverManager.getConnection(url, user, psw);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String value = resultSet.getString("nome");
                artista.getItems().add(value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadMusica() {
        musica.getItems().clear();
        String query;
        String selectedArtista = artista.getValue();
        if (selectedArtista != null) {
            query = String.format("select distinct musica.nome from artista " +
                    "join musica join genero join albuns on artista.id = albuns.artista_id and genero.id = albuns" +
                    ".genero_id and albuns.id = musica.album_id where artista.nome = '%s' order by musica.nome",
                    selectedArtista);
        } else {
            query = "select distinct nome from albuns order by nome";
        }

        try (Connection connection = DriverManager.getConnection(url, user, psw);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String value = resultSet.getString("nome");
                musica.getItems().add(value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateAlbum() {
        String selectedArtista = artista.getValue();
        String selectedMusica = musica.getValue();
        System.out.println("ok");

        if (selectedArtista != null && selectedMusica != null) {
            String query = "SELECT imagem FROM albuns WHERE nome = ? AND artista_id IN " +
                    "(SELECT id FROM artista WHERE nome = ?)";

            try (Connection connection = DriverManager.getConnection(url, user, psw);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, selectedMusica);
                statement.setString(2, selectedArtista);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String imagemPath = resultSet.getString("imagem");
                    imgAlbum.setImage(new Image("file:" + imagemPath));
                    System.out.println(musicaEmReproducao);
                    musicaEmReproducao.setText(selectedMusica);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}