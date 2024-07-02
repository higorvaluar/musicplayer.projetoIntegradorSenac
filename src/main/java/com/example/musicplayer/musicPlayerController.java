package com.example.musicplayer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import java.sql.ResultSet;
import javafx.util.Duration;

public class musicPlayerController {
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
    private ProgressBar progressBar;
    @FXML
    private TextField campoDeBusca;
    @FXML
    private Label tempoDecorrido;
    @FXML
    private Label tempoRestante;
    @FXML
    private Label labelCaminho;

    private MediaPlayer mediaPlayer;
    private Duration duration;
    private String ultimaMediaTocada;
    private double ultimoTempoPausado;

    private final String url = "jdbc:mysql://localhost:3306/musicplayer";
    private final String user = "root";
    private final String psw = "";

    @FXML
    private void playMusic(){
        String playMusica = labelCaminho.getText();
        File file = new File(playMusica);
        Media media = new Media(file.toURI().toString());

        if (mediaPlayer == null || !playMusica.equals(ultimaMediaTocada)) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }
            mediaPlayer = new MediaPlayer(media);
            resetPlayer();
            mediaPlayer.setOnReady(() -> {
                if (ultimoTempoPausado > 0) {
                    mediaPlayer.seek(new Duration(ultimoTempoPausado * 1000));
                }
                duration = mediaPlayer.getMedia().getDuration();
                mediaPlayer.play();
                updateProgressBar();
            });
            ultimaMediaTocada = playMusica;
            ultimoTempoPausado = 0;
        } else {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
                mediaPlayer.play();
            }
        }
        mediaPlayer.play();
    }

    @FXML
    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            ultimoTempoPausado = mediaPlayer.getCurrentTime().toSeconds();
        }
    }

    private void updateProgressBar() {
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            Duration currentTime = mediaPlayer.getCurrentTime();
            double progress = currentTime.toMillis() / duration.toMillis();
            progressBar.setProgress(progress);

            tempoDecorrido.setText(formatTime(currentTime));
            tempoRestante.setText(formatTime(duration.subtract(currentTime)));
        });
    }

    private String formatTime(Duration time){
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds() % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    @FXML
    private void campoDeBusca() {
        String pesquisa = campoDeBusca.getText();

        if (pesquisa != null) {
            String query = "select musica.musica as musica_caminho, albuns.nome as album_nome, albuns.imagem as " +
                    "album_imagem " +
                    "from artista " +
                    "join albuns on artista.id = albuns.artista_id " +
                    "join musica on albuns.id = musica.album_id " +
                    "where musica.nome = ?";

            try (Connection connection = DriverManager.getConnection(url, user, psw);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, pesquisa);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nomeAlbum = resultSet.getString("album_nome");
                    String caminhoImagem = resultSet.getString("album_imagem");
                    String caminhoMusica = resultSet.getString("musica_caminho");

                    album.setText(nomeAlbum);
                    try {
                        Image image = new Image(new File(caminhoImagem).toURI().toString());
                        imgAlbum.setImage(image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    labelCaminho.setText(caminhoMusica);
                }

                String nomeMusica = pesquisa;
                musicaEmReproducao.setText(nomeMusica);
                if (nomeMusica.equals(null)) {
                    mediaPlayer.stop();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


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
        btnPause.setOnAction(event -> pauseMusic());
        campoDeBusca.setOnAction(event -> campoDeBusca());

    }

    private void resetPlayer() {
        progressBar.setProgress(0);
        tempoDecorrido.setText(formatTime(Duration.ZERO));
        tempoRestante.setText(formatTime(Duration.ZERO));
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
            String query = "select musica.musica as musica_caminho, albuns.nome as album_nome, albuns.imagem as " +
                    "album_imagem " +
                    "from artista " +
                    "join albuns on artista.id = albuns.artista_id " +
                    "join musica on albuns.id = musica.album_id " +
                    "where artista.nome = ? and musica.nome = ?";

            try (Connection connection = DriverManager.getConnection(url, user, psw);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, selectedArtista);
                statement.setString(2, selectedMusica);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String nomeAlbum = resultSet.getString("album_nome");
                    String caminhoImagem = resultSet.getString("album_imagem");
                    String caminhoMusica = resultSet.getString("musica_caminho");

                    album.setText(nomeAlbum);
                    try {
                        Image image = new Image(new File(caminhoImagem).toURI().toString());
                        imgAlbum.setImage(image);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    labelCaminho.setText(caminhoMusica);
                }

                String nomeMusica = selectedMusica;
                musicaEmReproducao.setText(nomeMusica);
                if (nomeMusica.equals(null)) {
                    mediaPlayer.stop();
                }


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
}