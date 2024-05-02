package com.alcanl.app.service.dto;

import com.alcanl.app.repository.entity.FittingInfo;
import com.alcanl.app.repository.entity.HearingAid;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;
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
