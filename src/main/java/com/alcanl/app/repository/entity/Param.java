package com.alcanl.app.repository.entity;

import jakarta.persistence.*;

import java.io.File;

@Entity
@Table(name = "param_info")
public class Param {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
    public long id;

    @Column(name = "param_name", nullable = false)
    public String name;

    @Column(name = "param_file", nullable = false)
    public File params;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libId", nullable = false)
    public Library library;

    @Override
    public boolean equals(Object other)
    {
        return other instanceof Param p && p.id == id;
    }
    @Override
    public int hashCode()
    {
        return Long.hashCode(id);
    }
}
