package com.alcanl.app.service.dto;

public class HearingAidDTO {
    private String m_hearingAidModel;
    private String m_libraryId;
    private String m_paramId;
    public HearingAidDTO(String hearingAidModel, String libraryId, String paramId)
    {
        m_hearingAidModel = hearingAidModel;
        m_libraryId = libraryId;
        m_paramId = paramId;
    }
    public HearingAidDTO()
    {

    }

    public String getLibraryId()
    {
        return m_libraryId;
    }

    public void setLibraryId(String libraryId)
    {
        this.m_libraryId = libraryId;
    }

    public String getHearingAidModel()
    {
        return m_hearingAidModel;
    }

    public void setHearingAidModel(String hearingAidModel)
    {
        this.m_hearingAidModel = hearingAidModel;
    }

    public String getParamId()
    {
        return m_paramId;
    }

    public void setParamId(String paramId)
    {
        this.m_paramId = paramId;
    }
    @Override
    public boolean equals(Object other)
    {
        return other instanceof HearingAidDTO ha && ha.m_hearingAidModel.equals(m_hearingAidModel);
    }
    public int hashCode()
    {
        return m_hearingAidModel.hashCode();
    }
}
