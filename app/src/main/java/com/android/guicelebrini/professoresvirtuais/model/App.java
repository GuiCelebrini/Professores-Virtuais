package com.android.guicelebrini.professoresvirtuais.model;

public class App {
    private String name;
    private String developer;
    private String description;
    private String imagem;
    private String downloadLink;
    private String firebaseId;

    public App(){}

    public App(String name, String developer, String description, String imagem, String downloadLink, String firebaseId) {
        this.name = name;
        this.developer = developer;
        this.description = description;
        this.imagem = imagem;
        this.downloadLink = downloadLink;
        this.firebaseId = firebaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }
}
