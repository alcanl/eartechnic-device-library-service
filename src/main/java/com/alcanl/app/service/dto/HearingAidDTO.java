package com.alcanl.app.service.dto;

public class HearingAidDTO {
    private String m_modelName;
    private int m_modelNumber;
    private String m_libraryId;
    private String m_defaultParamId;
    private String m_activeParamId;
    private long m_equalizerValuesId;
    private int m_WdrcChannelCount;
    private int m_FrequencyChannelCount;
    public HearingAidDTO(String hearingAidModel, String libraryId, String defaultParamId, int wdrcChannelCount,
                         int frequencyChannelCount, long equalizerValuesId, int modelNumber, String activeParamId)
    {
        m_modelName = hearingAidModel;
        m_libraryId = libraryId;
        m_defaultParamId = defaultParamId;
        m_WdrcChannelCount = wdrcChannelCount;
        m_FrequencyChannelCount = frequencyChannelCount;
        m_equalizerValuesId = equalizerValuesId;
        m_modelNumber = modelNumber;
        m_activeParamId = activeParamId;
    }
    public HearingAidDTO() {}
    public int getWdrcChannelCount()
    {
        return m_WdrcChannelCount;
    }
    public void setWdrcChannelCount(int count)
    {
        m_WdrcChannelCount = count;
    }
    public int getFrequencyChannelCount()
    {
        return m_FrequencyChannelCount;
    }
    public void setFrequencyChannelCount(int count)
    {
        m_FrequencyChannelCount = count;
    }
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
    public void setEqualizerValuesId(long equalizerValuesId)
    {
        m_equalizerValuesId = equalizerValuesId;
    }
    public long getEqualizerValuesId()
    {
        return m_equalizerValuesId;
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
