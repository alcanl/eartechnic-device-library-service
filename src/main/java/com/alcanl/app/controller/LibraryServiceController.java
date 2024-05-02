package com.alcanl.app.controller;

import com.alcanl.app.repository.entity.User;
import com.alcanl.app.service.LibraryDataService;
import com.alcanl.app.service.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("api/ear-technic")
@Scope("request")
public class LibraryServiceController {
    private final LibraryDataService m_libraryDataService;
    private final HttpServletRequest m_httpServletRequest;
    private final LocalDateTime m_requestDateTime;

    private void printInfo(String request)
    {
        var host = m_httpServletRequest.getRemoteHost();
        var remotePort = m_httpServletRequest.getRemotePort();
        var localPort = m_httpServletRequest.getLocalPort();

        System.out.printf("%s\nClient Ip Address : %s\nClient Remote Port: %d\nClient Local Port: %d\nClient Request Time: %s\n\n",
                request, host, remotePort, localPort, m_requestDateTime);

    }
    public LibraryServiceController(LibraryDataService libraryDataService, HttpServletRequest httpServletRequest,
                                    LocalDateTime requestDateTime)
    {
        m_libraryDataService = libraryDataService;
        m_httpServletRequest = httpServletRequest;
        m_requestDateTime = requestDateTime;
    }

    @GetMapping("/hearing/models")
    public HearingAidDTO getHearingAidByModel(@RequestParam(name = "name")String modelName)
    {
        printInfo("getHearingAidByModel");
        var hearingAidDTOOpt = m_libraryDataService.findHearingAidByModelName(modelName);

        return hearingAidDTOOpt.orElse(null);
    }
    @GetMapping("/library")
    public LibraryDTO getLibraryByLibId(@RequestParam(name = "id")String libraryName)
    {
        printInfo("getLibraryByLibId");
        var libraryOpt = m_libraryDataService.findLibraryById(libraryName);

        return libraryOpt.orElse(null);
    }
    @GetMapping("/library/model/info")
    public LibraryToHearingAidsDTO getHearingAidByLibrary(@RequestParam(name = "id")String libraryId)
    {
        printInfo("getHearingAidByLibrary");
        return m_libraryDataService.findHearingAidsByLibrary(libraryId);
    }
    @GetMapping("/param/model/info")
    public ParamToHearingAidsDTO getHearingAidByParam(@RequestParam(name = "id")String paramId)
    {
        printInfo("getHearingAidByParam");
        return m_libraryDataService.findHearingAidsByParam(paramId);
    }
    @GetMapping("/model/library/data")
    public byte[] getLibraryDataByHearingAid(@RequestParam(name = "name")String modelName)
    {
        printInfo("getLibraryDataByHearingAid");
        var libraryDataOpt = m_libraryDataService.findLibraryDataByHearingAidModel(modelName);
        return libraryDataOpt.orElse(null);
    }
    @GetMapping("/model/library/info")
    public LibraryDTO getLibraryInfoByHearingAid(@RequestParam(name = "name")String modelName)
    {
        printInfo("getLibraryInfoByHearingAid");
        var libraryDataOpt = m_libraryDataService.findLibraryInfoByHearingAidModel(
                modelName);

        return libraryDataOpt.orElse(null);
    }
    @PostMapping("/save/user")
    public void saveUser(@RequestBody User user)
    {
        printInfo("saveUser");
        m_libraryDataService.saveUser(user);
    }
    @PostMapping("/save/fitting")
    public void saveFittingInfo(@RequestBody FittingInfoSaveDTO fittingInfoSaveDTO)
    {
        printInfo("saveFittingInfo");
        m_libraryDataService.saveFittingInfo(fittingInfoSaveDTO);
    }
}
