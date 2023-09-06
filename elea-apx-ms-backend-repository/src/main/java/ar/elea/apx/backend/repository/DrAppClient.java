package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.domain.CheckFileDto;

public interface DrAppClient {
    CheckFileDto getCheckFile(String token);
    byte[] getCodeBook(String token);
}
