package com.alcanl.app.service.dto;

public class HearingAidDTO {
    public String hearingAidModel;
    public String libraryId;
    public String paramId;
    public HearingAidDTO(String hearingAidModel, String libraryId, String paramId)
    {
        this.hearingAidModel = hearingAidModel;
        this.libraryId = libraryId;
        this.paramId = paramId;
    }
}
