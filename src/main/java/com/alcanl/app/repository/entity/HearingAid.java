package com.alcanl.app.repository.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "hearing_aid")
public class HearingAid {
    @Id
    @Column(name = "hearing_aid_model_name")
    public String modelName;

    @Column(name = "model_number", unique = true, nullable = false)
    public int modelNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "libId", nullable = false)
    public Library library;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paramId", nullable = false)
    public Param defaultParam;

    @Column(name = "active_param_id")
    public String activeParamId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hearingAid", cascade = CascadeType.ALL)
    public Set<User> users;

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
