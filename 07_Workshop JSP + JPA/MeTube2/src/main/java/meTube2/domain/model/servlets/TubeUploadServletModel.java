package meTube2.domain.model.servlets;

import meTube2.domain.entity.User;

public class TubeUploadServletModel {
    private String title;
    private String author;
    private String description;
    private String youtubeId;
    private UserLoginServletModel user;

    public TubeUploadServletModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public UserLoginServletModel getUser() {
        return user;
    }

    public void setUser(UserLoginServletModel user) {
        this.user = user;
    }
}
