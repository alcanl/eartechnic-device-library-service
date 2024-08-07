package com.alcanl.app.repository.entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long userId;

    @Column(name = "first_name", nullable = false)
    public String firstName;

    @Column(name = "e_mail", nullable = false, unique = true)
    public String eMail;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "last_name", nullable = false)
    public String lastName;

    @Column(name = "birth_date", nullable = false)
    public LocalDate dateOfBirth;

    @Column(name = "nationality_number", nullable = false)
    public String nationalityNumber;

    @Column(name = "phone_number", nullable = false)
    public String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hearing_aid_model_name", nullable = false)
    public HearingAid hearingAid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fittingId", cascade = CascadeType.ALL)
    public Set<FittingInfo> fittingInfo;

    @Override
    public boolean equals(Object other)
    {
        return other instanceof User u && userId == u.userId && u.eMail.equals(eMail) && u.password.equals(password);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(userId, eMail, password);
    }
    @Override
    public String toString()
    {
        return StringUtils.capitalize(firstName) + " " + StringUtils.capitalize(lastName);
    }
}
