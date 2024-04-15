package com.alcanl.app.controller;

import com.alcanl.app.repository.dal.LibraryServiceDataHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("/library")
    public ResponseEntity<File> getLibrary(String hearingAidModelName)
    {
        var hearingAidOpt = m_libraryServiceDataHelper.findLibraryByHearingAidModelName(hearingAidModelName);

        if (hearingAidOpt.isPresent())
            return ResponseEntity.of(hearingAidOpt.map(hearingAid -> hearingAid.library.libFile));

        return ResponseEntity.notFound().build();
    }

}
