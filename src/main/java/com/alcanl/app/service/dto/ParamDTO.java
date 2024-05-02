package com.alcanl.app.service.dto;

import java.util.Arrays;
import java.util.Objects;

public class ParamDTO {
    private String m_paramId;
    private byte[] m_paramData;
    private String m_libraryName;
    public ParamDTO() {}
    public ParamDTO(String paramName, byte[] paramData, String libraryName)
    {
        m_paramId = paramName;
        m_paramData = paramData;
        m_libraryName = libraryName;
    }

    public String getLibraryName()
    {
        return m_libraryName;
    }

    public void setLibraryName(String libraryName)
    {
        m_libraryName = libraryName;
    }

    public byte[] getParamData()
    {
        return m_paramData;
    }

    public void setParamData(byte[] paramData)
    {
        m_paramData = paramData;
    }

    public String getParamId()
    {
        return m_paramId;
    }

    public void setParamId(String paramName)
    {
        m_paramId = paramName;
    }
    @Override
    public boolean equals(Object other)
    {
        return other instanceof ParamDTO param && m_paramId.equals(param.m_paramId) &&
                Arrays.equals(m_paramData, param.m_paramData);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(m_paramId, Arrays.hashCode(m_paramData));
    }
}
