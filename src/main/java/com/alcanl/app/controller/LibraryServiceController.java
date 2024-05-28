package com.alcanl.app.controller;

import com.alcanl.app.service.LibraryDataService;
import com.alcanl.app.service.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import static com.alcanl.app.global.Global.*;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/model")
    public ResponseEntity<HearingAidDTO> getHearingAidByModel(@RequestParam(name = "name")String modelName)
    {
        printInfo("getHearingAidByModel");
        return ResponseEntity.of(m_libraryDataService.findHearingAidByModelName(modelName));

    }
    @GetMapping("/library")
    public ResponseEntity<LibraryDTO> getLibraryByLibId(@RequestParam(name = "id")String libraryName)
    {
        printInfo("getLibraryByLibId");
        return ResponseEntity.of(m_libraryDataService.findLibraryById(libraryName));

    }
    @GetMapping("/model/library/info")
    public LibraryToHearingAidsDTO getHearingAidByLibrary(@RequestParam(name = "id")String libraryId)
    {
        printInfo("getHearingAidByLibrary");
        return m_libraryDataService.findHearingAidsByLibrary(libraryId);
    }
    @GetMapping("/model/param/info")
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
    @GetMapping("/library/model/info")
    public ResponseEntity<LibraryDTO> getLibraryInfoByHearingAid(@RequestParam(name = "name")String modelName)
    {
        printInfo("getLibraryInfoByHearingAid");
       return  ResponseEntity.of(m_libraryDataService.findLibraryInfoByHearingAidModel(
                modelName));

    }
    @PostMapping("/save/user")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO)
    {
        printInfo("saveUser");

        if (userDTO.getPassword().length() < 8 || !isValid(userDTO.getEMail()))
            return ResponseEntity.badRequest().build();

        return m_libraryDataService.saveUser(userDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @PostMapping("/save/fitting")
    public FittingInfoDTO saveFittingInfo(@RequestBody FittingInfoDTO fittingInfoDTO)
    {
        printInfo("saveFittingInfo");
        m_libraryDataService.saveFittingInfo(fittingInfoDTO);

        return fittingInfoDTO;
    }
    @PostMapping("/save/param")
    public ResponseEntity<ParamDTO> saveParam(@RequestBody ParamDTO paramDTO)
    {
        printInfo("saveParam");
        return m_libraryDataService.saveParam(paramDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/save/library")
    public ResponseEntity<LibraryDTO> saveLibrary(@RequestBody LibraryDTO libraryDTO)
    {
        printInfo("saveLibrary");
        return m_libraryDataService.saveLibrary(libraryDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @PostMapping("/save/hearing")
    public ResponseEntity<HearingAidDTO> saveHearingAid(@RequestBody HearingAidDTO hearingAidDTO)
    {
        printInfo("saveHearingAid");
        return m_libraryDataService.saveHearingAid(hearingAidDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @GetMapping("/user/info")
    public ResponseEntity<UserDTO> getUser(@RequestParam("email") String eMail, @RequestParam("password") String password)
    {
        printInfo("getUserByEMailAndPassword");
        return ResponseEntity.of(m_libraryDataService.findUserByEmailAndPassword(eMail, password));
    }
    @GetMapping("/param/library")
    public Iterable<ParamDTO> getParamsByLibrary(@RequestBody LibraryDTO libraryDTO)
    {
        printInfo("getParamsByLibrary");
        return m_libraryDataService.findParamsByLibrary(libraryDTO);
    }
    @GetMapping("/param/model")
    public ResponseEntity<ParamDTO> getParamByHearingAidModelName(@RequestParam("name") String modelName)
    {
        printInfo("getParamByHearingAid");
        return ResponseEntity.of(m_libraryDataService.findParamByHearingAid(modelName));
    }
    @GetMapping("/param/model/number")
    public ResponseEntity<ParamDTO> getDefaultParamByHearingAidModelNumber(@RequestParam("num")int modelNumber)
    {
        printInfo("getDefaultParamByHearingAidModelNumber");
        return ResponseEntity.of(m_libraryDataService.findDefaultParamByHearingAidModelNumber(modelNumber));

    }
    @GetMapping("/param/model/data")
    public ResponseEntity<byte[]> getParamDataByHearingAid(@RequestParam("name") String modelName)
    {
        printInfo("getParamDataByHearingAid");
        return ResponseEntity.of(m_libraryDataService.findDefaultParamDataByHearingAid(modelName));
    }
    @GetMapping("/user/param/active")
    public ResponseEntity<byte[]> getActiveParamDataByUser(@RequestParam("email")String eMail,
                                                           @RequestParam("password")String password)
    {
        printInfo("getActiveParamDataByUser");
        var userDTO = new UserDTO();
        userDTO.setEMail(eMail);
        userDTO.setPassword(password);

        return ResponseEntity.of(m_libraryDataService.findActiveParamDataByUser(userDTO));
    }
    @GetMapping("/model")
    public ResponseEntity<HearingAidDTO> getHearingAidByModelNumber(@RequestParam("num")int modelNumber)
    {
        printInfo("getHearingAidByModelNumber");
        return ResponseEntity.of(m_libraryDataService.findHearingAidByModelNumber(modelNumber));
    }
    @GetMapping("/model/number/name")
    public ResponseEntity<String> getHearingAidModelNameByModelNumber(@RequestParam("number")int modelNumber)
    {
        printInfo("getHearingAidModelNameByModelNumber");
        return ResponseEntity.of(m_libraryDataService.findHearingAidModelNameByModelNumber(modelNumber));
    }
    @GetMapping("/model/name/number")
    public ResponseEntity<Integer> getHearingAidModelNumberByModelName(@RequestParam("name")String modelName)
    {
        printInfo("getHearingAidModelNumberByModelName");
        return ResponseEntity.of(m_libraryDataService.findHearingAidModelNumberByModelName(modelName));
    }
}
