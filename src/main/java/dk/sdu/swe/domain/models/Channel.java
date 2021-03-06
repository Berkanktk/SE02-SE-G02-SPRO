package dk.sdu.swe.domain.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(name = "epg_id")
    private Long epgIdentifier;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String logo;

    public Channel(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }

    public Channel() {
    }

    public Long getEpgId() {
        return epgIdentifier;
    }

    public void setEpgId(Long epgIdentifier) {
        this.epgIdentifier = epgIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getId() {
        return this.id;
    }
}
