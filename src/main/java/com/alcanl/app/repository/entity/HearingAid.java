package com.alcanl.app.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hearing_aid")
public class HearingAid {
    @Id
    @Column(name = "hearing_aid_model_name")
    public String modelName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libId", nullable = false)
    public Library library;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paramId", nullable = false)
    public Param param;

    @Override
    public boolean equals(Object other)
    {
        return other instanceof HearingAid ha && ha.modelName.equals(modelName);
    }

    @Override
    public int hashCode()
    {
        return modelName.hashCode();
    }
}
