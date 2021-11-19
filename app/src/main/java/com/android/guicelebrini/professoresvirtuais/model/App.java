package com.android.guicelebrini.professoresvirtuais.model;

public class App {
    private String name;
    private String developer;
    private String description;
    private String image;
    private String printURL;
    private String downloadLink;
    private String firebaseId;
    private String videoID;

    public App(){}

    public App(String name, String developer, String description, String image, String printURL, String downloadLink, String firebaseId) {
        this.name = name;
        this.developer = developer;
        this.description = description;
        this.image = image;
        this.printURL = printURL;
        this.downloadLink = downloadLink;
        this.firebaseId = firebaseId;
    }

    @Override
    public String toString() {
        return "App{" +
                "name='" + name + '\'' +
                ", developer='" + developer + '\'' +
                ", description='" + description + '\'' +
                ", imagem='" + image + '\'' +
                ", downloadLink='" + downloadLink + '\'' +
                ", firebaseId='" + firebaseId + '\'' +
                '}';
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

    public String getImage() {
        return image;
    }

    public void setImage(String imagem) {
        this.image = imagem;
    }

    public String getPrintURL() {
        return printURL;
    }

    public void setPrintURL(String printURL) {
        this.printURL = printURL;
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

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }
}
