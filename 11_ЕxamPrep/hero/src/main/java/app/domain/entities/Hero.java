package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "heroes")
public class Hero extends BaseEntity {
    private String name;
    private HeroClass heroClass;
    private Integer level;

    public Hero() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "hero_class")
    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroType) {
        this.heroClass = heroType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
