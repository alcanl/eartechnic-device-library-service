package com.alcanl.app.service.dto;

import java.util.Arrays;
import java.util.Objects;

public class ParamDTO {
    private String m_paramName;
    private byte[] m_paramData;
    private String m_libraryName;
    public ParamDTO() {}
    public ParamDTO(String paramName, byte[] paramData, String libraryName)
    {
        m_paramName = paramName;
        m_paramData = paramData;
        m_libraryName = libraryName;
    }

    public String getLibrary()
    {
        return m_libraryName;
    }

    public void setLibrary(String library)
    {
        m_libraryName = library;
    }

    public byte[] getParamData()
    {
        return m_paramData;
    }

    public void setParamData(byte[] paramData)
    {
        m_paramData = paramData;
    }

    public String getParamName()
    {
        return m_paramName;
    }

    public void setParamName(String paramName)
    {
        m_paramName = paramName;
    }
    @Override
    public boolean equals(Object other)
    {
        return other instanceof ParamDTO param && m_paramName.equals(param.m_paramName) &&
                Arrays.equals(m_paramData, param.m_paramData);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(m_paramName, Arrays.hashCode(m_paramData));
    }
}
