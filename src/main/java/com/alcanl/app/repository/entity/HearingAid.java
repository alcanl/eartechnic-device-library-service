package com.alcanl.app.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hearing_aid")
public class HearingAid {
    @Id
    @Column(name = "hearing_aid_model_name")
    public String modelName;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "hearingAid", cascade = CascadeType.ALL)
    public Library library;

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
