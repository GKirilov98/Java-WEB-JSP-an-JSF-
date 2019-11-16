package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "tubes")
public class Tube extends BaseEntity {
    private String title;
    private String Author;
    private String description;
    private String youtubeId;
    private int views;
    private User uploader;

    public Tube() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @Column(columnDefinition = "TEXT")
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @ManyToOne
    @JoinColumn(name = "uploader_id", referencedColumnName = "id")
    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
