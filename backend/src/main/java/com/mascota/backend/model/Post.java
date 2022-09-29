package com.mascota.backend.model;
import javax.persistence.*;
@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(unique = true, nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "TEXT")
    private String descr;
    private double recompensa;
    @JoinColumn(name = "FK_User", nullable = false, updatable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    public Post (){

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public double getRecompensa() {
        return recompensa;
    }
    public void setRecompensa(double recompensa) {
        this.recompensa = recompensa;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
