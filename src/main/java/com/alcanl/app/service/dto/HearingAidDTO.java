package com.alcanl.app.service.dto;

public class HearingAidDTO {
    private String m_modelName;
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

    public String getLibraryId()
    {
        return m_libraryId;
    }

    public void setLibraryId(String libraryId)
    {
        this.m_libraryId = libraryId;
    }

    public String getModelName()
    {
        return m_modelName;
    }

    public void setModelName(String hearingAidModel)
    {
        this.m_modelName = hearingAidModel;
    }

    public String getDefaultParamId()
    {
        return m_defaultParamId;
    }

    public void setDefaultParamId(String paramId)
    {
        this.m_defaultParamId = paramId;
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
