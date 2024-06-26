package com.example.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.sql.*;

import java.sql.ResultSet;


public class musicPlayerController {
    @FXML
    private ComboBox<String> genero;
    @FXML
    private ComboBox<String> artista;
    @FXML
    private ComboBox<String> albuns;
    @FXML
    private ImageView imgAlbum;
    @FXML
    private Label musica;
    @FXML
    private Label album;

    private String url = "jdbc:mysql://localhost:3306/musicplayer";
    private String user = "root";
    private String psw = "";

    @FXML
    public void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadComboBoxes();

        genero.setOnAction(event -> updateMusic());
        artista.setOnAction(event -> updateMusic());
        albuns.setOnAction(event -> updateMusic());
    }

    private void updateMusic() {
        String selectedArtista = artista.getValue();
        String selectedAlbum = albuns.getValue();

        if (selectedArtista != null && selectedAlbum != null) {
            String query = "SELECT nome FROM musica WHERE album_id = " +
                    "(SELECT id FROM albuns WHERE nome = ? AND artista_id = " +
                    "(SELECT id FROM artista WHERE nome = ?))";

            try (Connection connection = DriverManager.getConnection(url, user, psw);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, selectedAlbum);
                statement.setString(2, selectedArtista);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nomeMusica = resultSet.getString("nome");
                    musica.setText(nomeMusica);
                } else {
                    musica.setText("Música não encontrada");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadComboBoxes() {
        loadGenero();
        loadArtista();
        loadAlbuns();
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
        String query = "select distinct nome from artista order by nome";

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

    private void loadAlbuns() {
        String query = "select distinct nome from albuns order by nome";

        try (Connection connection = DriverManager.getConnection(url, user, psw);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String value = resultSet.getString("nome");
                albuns.getItems().add(value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void updateAlbum() {
        String selectedArtista = artista.getValue();
        String selectedAlbum = albuns.getValue();

        if (selectedArtista != null && selectedAlbum != null) {
            String query = "SELECT imagem FROM albuns WHERE nome = ? AND artista_id IN " +
                    "(SELECT id FROM artista WHERE nome = ?)";

            try (Connection connection = DriverManager.getConnection(url, user, psw);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, selectedAlbum);
                statement.setString(2, selectedArtista);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String imagemPath = resultSet.getString("imagem");
                    imgAlbum.setImage(new Image("file:" + imagemPath));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}