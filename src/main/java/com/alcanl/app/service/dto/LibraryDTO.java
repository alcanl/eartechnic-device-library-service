package com.alcanl.app.service.dto;

import org.hibernate.service.spi.ServiceException;

import java.util.Arrays;
import java.util.Objects;

public class LibraryDTO {
    private String m_libraryName;
    private byte [] m_libraryData;

    public LibraryDTO() {}
    public LibraryDTO(byte[] libraryData, String libraryName)
    {
        m_libraryName = libraryName;
        m_libraryData = libraryData;
    }
    public void setLibraryName(String libraryName)
    {
        m_libraryName = libraryName;
    }
    public String getLibraryName()
    {
        return m_libraryName;
    }
    public void setLibraryData(byte[] libraryData)
    {
        if (libraryData.length == 0)
            throw new ServiceException("Library data is empty");

        m_libraryData = libraryData;
    }

    public byte[] getLibraryData()
    {
        return m_libraryData;
    }

    @Override
    public boolean equals(Object other)
    {
        return other instanceof LibraryDTO lib && m_libraryName.equals(lib.m_libraryName) && Arrays.equals(m_libraryData, lib.m_libraryData);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(m_libraryName, Arrays.hashCode(m_libraryData));
    }
}
