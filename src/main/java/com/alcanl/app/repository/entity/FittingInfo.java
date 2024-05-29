package com.alcanl.app.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fitting_info")
public class FittingInfo {

    @Id
    @Column(name = "fitting_info_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long fittingId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    public User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "fittingInfo", cascade = CascadeType.ALL)
    public Param savedParam;

    @Column(name = "fitting_info_date")
    public LocalDateTime fittingDate = LocalDateTime.now();

    @Override
    public boolean equals(Object other)
    {
        return other instanceof FittingInfo fi && fittingId == fi.fittingId;
    }
    @Override
    public int hashCode()
    {
        return Long.hashCode(fittingId);
    }
}
