package com.alcanl.app.service.dto;

public class HearingAidDTO {
    private String m_modelName;
    private int m_modelNumber;
    private String m_libraryId;
    private String m_defaultParamId;
    private String m_activeParamId;
    public HearingAidDTO(String hearingAidModel, String libraryId, String paramId)
    {
        m_modelName = hearingAidModel;
        m_libraryId = libraryId;
        m_defaultParamId = paramId;
    }
    public HearingAidDTO() {}
    public String getActiveParamId()
    {
        return m_activeParamId;
    }
    public void setActiveParamId(String activeParamId)
    {
        m_activeParamId = activeParamId;
    }
    public int getModelNumber()
    {
        return m_modelNumber;
    }
    public void setModelNumber(int modelNumber)
    {
        m_modelNumber = modelNumber;
    }

    public String getLibraryId()
    {
        return m_libraryId;
    }

    public void setLibraryId(String libraryId)
    {
        m_libraryId = libraryId;
    }

    public String getModelName()
    {
        return m_modelName;
    }

    public void setModelName(String hearingAidModel)
    {
        m_modelName = hearingAidModel;
    }

    public String getDefaultParamId()
    {
        return m_defaultParamId;
    }

    public void setDefaultParamId(String paramId)
    {
        m_defaultParamId = paramId;
    }
    @Override
    public boolean equals(Object other)
    {
        return other instanceof HearingAidDTO ha && ha.m_modelName.equals(m_modelName);
    }
    @Override
    public int hashCode()
    {
        return m_modelName.hashCode();
    }
}
