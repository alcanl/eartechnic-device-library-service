package com.alcanl.app.repository.entity;

import jakarta.persistence.*;

import java.io.File;
import java.util.Set;

@Entity
@Table(name = "library_info")
public class Library {
    @Id
    @Column(name = "library_id")
    public String libId;

    @Column(name = "library_file", nullable = false)
    public File libFile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paramId", cascade = CascadeType.ALL)
    public Set<Param> params;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "modelName", cascade = CascadeType.ALL)
    public Set<HearingAid> hearingAid;

    @Override
    public boolean equals(Object other)
    {
        return other instanceof Library lib && libId.equals(lib.libId);
    }

    @Override
    public int hashCode()
    {
        return libId.hashCode();
    }
}
