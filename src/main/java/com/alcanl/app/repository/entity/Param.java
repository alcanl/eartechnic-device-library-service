package com.alcanl.app.repository.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "param_info")
public class Param {
    @Id
    @Column(name = "param_id")
    public String paramId;

    @Column(name = "param_file", nullable = false)
    public byte[] paramData;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "libId", nullable = false)
    public Library library;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "defaultParam", cascade = CascadeType.ALL)
    public Set<HearingAid> hearingAid;

    @Override
    public boolean equals(Object other)
    {
        return other instanceof Param p && p.paramId.equals(paramId);
    }
    @Override
    public int hashCode()
    {
        return paramId.hashCode();
    }
}
