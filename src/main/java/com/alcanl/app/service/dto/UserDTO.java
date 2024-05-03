package com.alcanl.app.service.dto;

import com.alcanl.app.repository.entity.FittingInfo;
import com.alcanl.app.repository.entity.HearingAid;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDTO {

    private String m_firstName;

    private String m_eMail;

    private String m_password;

    private String m_lastName;

    private LocalDate m_dateOfBirth;

    private HearingAid m_hearingAid;

    private Set<FittingInfo> m_fittingInfo;

    public String getFirstName()
    {
        return m_firstName;
    }

    public void setFirstName(String firstName)
    {
        m_firstName = firstName;
    }

    public String getEMail()
    {
        return m_eMail;
    }

    public void setEMail(String eMail)
    {
        m_eMail = eMail;
    }

    public String getPassword()
    {
        return m_password;
    }

    public void setPassword(String password)
    {
        m_password = password;
    }

    public LocalDate getDateOfBirth()
    {
        return m_dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        m_dateOfBirth = dateOfBirth;
    }

    public String getLastName()
    {
        return m_lastName;
    }

    public void setLastName(String lastName)
    {
        m_lastName = lastName;
    }

    public HearingAid getHearingAid()
    {
        return m_hearingAid;
    }

    public void setHearingAid(HearingAid hearingAid)
    {
        m_hearingAid = hearingAid;
    }

    public Set<FittingInfo> getFittingInfo()
    {
        return m_fittingInfo;
    }
    public void setFittingInfo(Set<FittingInfo> fittingInfo)
    {
        m_fittingInfo = fittingInfo;
    }
    public void addFittingInfo()
    {
        if (m_fittingInfo == null)
            m_fittingInfo = new HashSet<>();

        FittingInfo fittingInfo = new FittingInfo();
        m_fittingInfo.add(fittingInfo);
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof UserDTO userDTO && m_eMail.equals(userDTO.m_eMail) && m_password.equals(userDTO.m_password);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(m_eMail, m_password);
    }
    @Override
    public String toString()
    {
        return StringUtils.capitalize(m_firstName) + " " + StringUtils.capitalize(m_lastName);
    }
}
