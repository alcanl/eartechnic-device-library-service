package com.alcanl.app.repository.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "equalizer_value_info")
public class EqualizerValues implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equalizer_values_id")
    public long equalizerValuesId;

    @Lob
    @Column(name = "equalizer_values_array", nullable = false)
    public List<Integer> values;

    @Column(name = "equalizer_values_name", unique = true, nullable = false)
    public String equalizerValuesName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equalizerValues", cascade = CascadeType.ALL)
    public Set<HearingAid> hearingAid;
}
