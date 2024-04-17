package com.alcanl.app.controller;

import com.alcanl.app.repository.dal.LibraryServiceDataHelper;
import com.alcanl.app.repository.entity.HearingAid;
import com.alcanl.app.repository.entity.Library;
import com.alcanl.app.repository.entity.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("api/lib")
public class LibraryServiceController {
    private final LibraryServiceDataHelper m_libraryServiceDataHelper;

    public LibraryServiceController(LibraryServiceDataHelper libraryServiceDataHelper)
    {
        m_libraryServiceDataHelper = libraryServiceDataHelper;
    }
    @GetMapping("/hearing")
    public ResponseEntity<HearingAid> getHearingAid(@RequestParam(name = "model")String hearingAidModelName)
    {
        var hearingAidOpt = m_libraryServiceDataHelper.findLibraryByHearingAidModelName(hearingAidModelName);

        return ResponseEntity.of(hearingAidOpt);
    }
    @GetMapping("/library")
    public Library getLibrary(@RequestParam(name = "name")String libraryName)
    {
        var libraryOpt = m_libraryServiceDataHelper.findLibraryById(libraryName);

        return libraryOpt.orElse(null);
    }

}
