package com.alcanl.app.controller;

import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.User;
import com.alcanl.app.service.LibraryDataService;
import com.alcanl.app.service.dto.FittingInfoSaveDTO;
import com.alcanl.app.service.dto.LibraryToHearingAidsDTO;
import com.alcanl.app.service.dto.LibraryToLibDataDTO;
import com.alcanl.app.service.dto.ParamToHearingAidsDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("api/ear-technic")
public class LibraryServiceController {
    private final LibraryDataService m_libraryDataService;
    private final HttpServletRequest m_httpServletRequest;
    private final LocalDateTime m_requestDateTime;

    public LibraryServiceController(LibraryDataService libraryDataService, HttpServletRequest httpServletRequest, LocalDateTime requestDateTime)
    {
        m_libraryDataService = libraryDataService;
        m_httpServletRequest = httpServletRequest;
        m_requestDateTime = requestDateTime;
    }

    @GetMapping("/hearing/models")
    public HearingAid getHearingAidByModel(@RequestParam(name = "name")String modelName)
    {
        var hearingAidOpt = m_libraryDataService.findHearingAidByModelName(modelName);

        return hearingAidOpt.orElse(null);
    }
    @GetMapping("/library")
    public Library getLibraryByLibId(@RequestParam(name = "id")String libraryName)
    {
        var libraryOpt = m_libraryDataService.findLibraryById(libraryName);

        return libraryOpt.orElse(null);
    }
    @GetMapping("/library/model/info")
    public LibraryToHearingAidsDTO getHearingAidByLibrary(@RequestParam(name = "id")String libraryId)
    {
        return m_libraryDataService.findHearingAidsByLibrary(libraryId);
    }
    @GetMapping("/param/model/info")
    public ParamToHearingAidsDTO getHearingAidByParam(@RequestParam(name = "id")String paramId)
    {
        return m_libraryDataService.findHearingAidsByParam(paramId);
    }
    @GetMapping("/model/library/data")
    public byte[] getLibraryDataByHearingAid(@RequestParam(name = "name")String modelName)
    {

        var libraryDataOpt = m_libraryDataService.findLibraryDataByHearingAidModel(modelName);
        return libraryDataOpt.orElse(null);
    }
    @GetMapping("/model/library/info")
    public LibraryToLibDataDTO getLibraryInfoByHearingAid(@RequestParam(name = "name")String modelName)
    {

        var libraryDataOpt = m_libraryDataService.findLibraryInfoByHearingAidModel(modelName);

        var host = m_httpServletRequest.getRemoteHost();
        var remotePort = m_httpServletRequest.getRemotePort();
        var localPort = m_httpServletRequest.getLocalPort();

        System.out.printf("Client Ip Address : %s/nClient Remote Port: %d/nClient Local Port: %d/nClient Request Time: %s/n", host, remotePort, localPort, m_requestDateTime);

        return libraryDataOpt.orElse(null);
    }
    @PostMapping("/save/user")
    public void saveUser(@RequestBody User user)
    {
        m_libraryDataService.saveUser(user);
    }
    @PostMapping("/save/fitting")
    public void saveFittingInfo(@RequestBody FittingInfoSaveDTO fittingInfoSaveDTO)
    {
        m_libraryDataService.saveFittingInfo(fittingInfoSaveDTO);
    }
}
