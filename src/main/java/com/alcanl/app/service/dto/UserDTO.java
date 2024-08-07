package com.alcanl.app.service.dto;

import com.alcanl.app.repository.entity.FittingInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDTO {

    private long m_userId;

    private String m_firstName;

    private String m_eMail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String m_password;

    private String m_lastName;

    private String m_dateOfBirth;

    private String m_nationalityNumber;

    private String m_phoneNumber;

    private int m_hearingAidModelNumber;

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

    public String getDateOfBirth()
    {
        return m_dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth)
    {
        m_dateOfBirth = dateOfBirth;
    }

    public String getNationalityNumber() { return m_nationalityNumber; }
    public void setNationalityNumber( String nationalityNumber ) { m_nationalityNumber = nationalityNumber;}

    public String getPhoneNumber() { return m_phoneNumber; }
    public void setPhoneNumber( String phoneNumber ) { m_phoneNumber = phoneNumber;}

    public String getLastName()
    {
        return m_lastName;
    }

    public void setLastName(String lastName)
    {
        m_lastName = lastName;
    }

    public int getHearingAidModelNumber()
    {
        return m_hearingAidModelNumber;
    }
    public long getUserId() { return m_userId; }
    public void setUserId(long id) { m_userId = id; }

    public void setHearingAidModelNumber(int hearingAidModelNumber)
    {
        m_hearingAidModelNumber = hearingAidModelNumber;
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
