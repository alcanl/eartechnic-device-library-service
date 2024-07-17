package com.alcanl.app.service.dto;

import java.util.Objects;

public class FittingInfoDTO {

    private String m_savedParamId;
    private long m_userId;
    private String m_fittingDate;

    public FittingInfoDTO(String paramId, long userId, String fittingDate)
    {
        m_savedParamId = paramId;
        m_userId = userId;
        m_fittingDate = fittingDate;
    }

    public void setUserId(long userId)
    {
        m_userId = userId;
    }
    public long getUserId()
    {
        return m_userId;
    }

    public String getFittingDate()
    {
        return m_fittingDate;
    }

    public void setFittingDate(String fittingDate)
    {
        m_fittingDate = fittingDate;
    }
    public String getParamId()
    {
        return m_savedParamId;
    }
    public void setParamId(String paramId)
    {
        m_savedParamId = paramId;
    }
    @Override
    public boolean equals(Object other)
    {
        return other instanceof FittingInfoDTO fi && m_savedParamId.equals(fi.m_savedParamId) && m_fittingDate.equals(fi.m_fittingDate);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(m_userId, m_savedParamId);
    }
}
