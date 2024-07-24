package com.alcanl.app.controller;

import com.alcanl.app.service.LibraryDataService;
import com.alcanl.app.service.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import static com.alcanl.app.global.Global.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("api/ear-technic")
@Scope("request")
public class LibraryServiceController {
    private final LibraryDataService m_libraryDataService;
    private final HttpServletRequest m_httpServletRequest;
    private final LocalDateTime m_requestDateTime;

    private void logInfo(String request)
    {
        var host = m_httpServletRequest.getRemoteHost();
        var remotePort = m_httpServletRequest.getRemotePort();
        var localPort = m_httpServletRequest.getLocalPort();

        log.info("\n{}\nClient Ip Address : {}\nClient Remote Port: {}\nClient Local Port: {}\nClient Request Time: {}\n\n",
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
        logInfo("getHearingAidByModel");
        return ResponseEntity.of(m_libraryDataService.findHearingAidByModelName(modelName));

    }
    @GetMapping("/library")
    public ResponseEntity<LibraryDTO> getLibraryByLibId(@RequestParam(name = "id")String libraryName)
    {
        logInfo("getLibraryByLibId");
        return ResponseEntity.of(m_libraryDataService.findLibraryById(libraryName));

    }
    @GetMapping("/model/library/info")
    public LibraryToHearingAidsDTO getHearingAidByLibrary(@RequestParam(name = "id")String libraryId)
    {
        logInfo("getHearingAidByLibrary");
        return m_libraryDataService.findHearingAidsByLibrary(libraryId);
    }
    @GetMapping("/model/param/info")
    public ParamToHearingAidsDTO getHearingAidByParam(@RequestParam(name = "id")String paramId)
    {
        logInfo("getHearingAidByParam");
        return m_libraryDataService.findHearingAidsByParam(paramId);
    }
    @GetMapping("/model/library/data")
    public byte[] getLibraryDataByHearingAid(@RequestParam(name = "name")String modelName)
    {
        logInfo("getLibraryDataByHearingAid");
        var libraryDataOpt = m_libraryDataService.findLibraryDataByHearingAidModel(modelName);
        return libraryDataOpt.orElse(null);
    }
    @GetMapping("/library/model/info")
    public ResponseEntity<LibraryDTO> getLibraryInfoByHearingAid(@RequestParam(name = "name")String modelName)
    {
        logInfo("getLibraryInfoByHearingAid");
       return  ResponseEntity.of(m_libraryDataService.findLibraryInfoByHearingAidModel(
                modelName));

    }
    @PostMapping("/save/user")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO)
    {
        logInfo("saveUser");

        if (userDTO.getPassword().length() < 8 || !isValid(userDTO.getEMail()))
            return ResponseEntity.badRequest().build();

        return m_libraryDataService.saveUser(userDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @PostMapping("/save/fitting")
    public ResponseEntity<FittingInfoDTO> saveFittingInfo(@RequestBody FittingInfoDTO fittingInfoDTO)
    {
        logInfo("saveFittingInfo");
        return m_libraryDataService.saveFittingInfo(fittingInfoDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @PostMapping("/save/param")
    public ResponseEntity<ParamDTO> saveParam(@RequestBody ParamDTO paramDTO)
    {
        logInfo("saveParam");
        return m_libraryDataService.saveParam(paramDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/save/library")
    public ResponseEntity<LibraryDTO> saveLibrary(@RequestBody LibraryDTO libraryDTO)
    {
        logInfo("saveLibrary");
        return m_libraryDataService.saveLibrary(libraryDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @PostMapping("/save/hearing")
    public ResponseEntity<HearingAidDTO> saveHearingAid(@RequestBody HearingAidDTO hearingAidDTO)
    {
        logInfo("saveHearingAid");
        return m_libraryDataService.saveHearingAid(hearingAidDTO)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());

    }
    @GetMapping("/user/info")
    public ResponseEntity<UserDTO> getUser(@RequestParam("email") String eMail, @RequestParam("password") String password)
    {
        logInfo("getUserByEMailAndPassword");
        return ResponseEntity.of(m_libraryDataService.findUserByEmailAndPassword(eMail, password));
    }
    @GetMapping("/param/library")
    public Iterable<ParamDTO> getParamsByLibrary(@RequestBody LibraryDTO libraryDTO)
    {
        logInfo("getParamsByLibrary");
        return m_libraryDataService.findParamsByLibrary(libraryDTO);
    }
    @GetMapping("/param/model")
    public ResponseEntity<ParamDTO> getParamByHearingAidModelName(@RequestParam("name") String modelName)
    {
        logInfo("getParamByHearingAid");
        return ResponseEntity.of(m_libraryDataService.findParamByHearingAid(modelName));
    }
    @GetMapping("/param/model/number")
    public ResponseEntity<ParamDTO> getDefaultParamByHearingAidModelNumber(@RequestParam("num")int modelNumber)
    {
        logInfo("getDefaultParamByHearingAidModelNumber");
        return ResponseEntity.of(m_libraryDataService.findDefaultParamByHearingAidModelNumber(modelNumber));

    }
    @GetMapping("/param/model/data")
    public ResponseEntity<byte[]> getParamDataByHearingAid(@RequestParam("name") String modelName)
    {
        logInfo("getParamDataByHearingAid");
        return ResponseEntity.of(m_libraryDataService.findDefaultParamDataByHearingAid(modelName));
    }
    @GetMapping("/user/param/active")
    public ResponseEntity<byte[]> getActiveParamDataByUser(@RequestParam("email")String eMail,
                                                           @RequestParam("password")String password)
    {
        logInfo("getActiveParamDataByUser");
        var userDTO = new UserDTO();
        userDTO.setEMail(eMail);
        userDTO.setPassword(password);

        return ResponseEntity.of(m_libraryDataService.findActiveParamDataByUser(userDTO));
    }
    @GetMapping("/model/number")
    public ResponseEntity<HearingAidDTO> getHearingAidByModelNumber(@RequestParam("num")int modelNumber)
    {
        logInfo("getHearingAidByModelNumber");
        return ResponseEntity.of(m_libraryDataService.findHearingAidByModelNumber(modelNumber));
    }
    @GetMapping("/model/number/name")
    public ResponseEntity<String> getHearingAidModelNameByModelNumber(@RequestParam("number")int modelNumber)
    {
        logInfo("getHearingAidModelNameByModelNumber");
        return ResponseEntity.of(m_libraryDataService.findHearingAidModelNameByModelNumber(modelNumber));
    }
    @GetMapping("/model/name/number")
    public ResponseEntity<Integer> getHearingAidModelNumberByModelName(@RequestParam("name")String modelName)
    {
        logInfo("getHearingAidModelNumberByModelName");
        return ResponseEntity.of(m_libraryDataService.findHearingAidModelNumberByModelName(modelName));
    }
    @GetMapping("/model/equalizer/values")
    public ResponseEntity<EqualizerValuesDTO> getEqualizerValuesByHearingAidModelNumber(@RequestParam("number")int modelNumber)
    {
        logInfo("getEqualizerValuesByHearingAidModelNumber");
        return ResponseEntity.of(m_libraryDataService.findEqualizerValuesByHearingAidModelNumber(modelNumber));
    }
    @GetMapping("/equalizer/values")
    public ResponseEntity<EqualizerValuesDTO> getEqualizerValuesById(@RequestParam("id")long equalizerValuesId)
    {
        logInfo("getEqualizerValuesByHearingAidModelNumber");
        return ResponseEntity.of(m_libraryDataService.findEqualizerValuesById(equalizerValuesId));
    }
}
