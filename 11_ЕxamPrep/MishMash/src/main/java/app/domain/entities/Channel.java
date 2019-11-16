package app.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chanels")
public class Channel extends BaseEntity {
    private String name;
    private String description;
    private Type type;
    private String tags;
    private List<User> followers;

    public Channel() {
        followers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    @ManyToMany
    @JoinTable(name = "chanels_users",
            joinColumns = @JoinColumn(name ="chanel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="followers_id", referencedColumnName = "id")
    )
    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
