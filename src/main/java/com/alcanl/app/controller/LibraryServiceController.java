package com.alcanl.app.controller;

import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.service.LibraryDataService;
import com.alcanl.app.service.dto.LibraryToHearingAidsDTO;
import com.alcanl.app.service.dto.ParamToHearingAidsDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpHeaders;


@RestController
@RequestMapping("api/lib")
public class LibraryServiceController {
    private final LibraryDataService m_libraryDataService;

    public LibraryServiceController(LibraryDataService libraryDataService)
    {
        m_libraryDataService = libraryDataService;
    }

    @GetMapping("/hearing/models")
    public HearingAid getHearingAidByModel(@RequestParam(name = "model")String hearingAidModelName)
    {
        var hearingAidOpt = m_libraryDataService.findHearingAidByModelName(hearingAidModelName);

        return hearingAidOpt.orElse(null);
    }
    @GetMapping("/library")
    public Library getLibraryByLibId(@RequestParam(name = "name")String libraryName)
    {
        var libraryOpt = m_libraryDataService.findLibraryById(libraryName);

        return libraryOpt.orElse(null);
    }
    @GetMapping("/hearing/models/lib")
    public LibraryToHearingAidsDTO getHearingAidByLibrary(@RequestParam(name = "libId")String libraryId)
    {
        return m_libraryDataService.findHearingAidsByLibrary(libraryId);
    }
    @GetMapping("/hearing/models/param")
    public ParamToHearingAidsDTO getHearingAidByParam(@RequestParam(name = "name")String paramId)
    {
        return m_libraryDataService.findHearingAidsByParam(paramId);
    }
    @GetMapping("/library/model")
    public byte[] getLibraryByHearingAid(@RequestParam(name = "name")String modelName)
    {

        var libraryDataOpt = m_libraryDataService.findLibraryDataByHearingAidModel(modelName);
        return libraryDataOpt.orElse(null);
    }

}
