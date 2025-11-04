package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerConfigService {

    private UtilService utilService;

    @Autowired
    public ServerConfigService(UtilService utilService) {
        this.utilService = utilService;
    }

    public UtilService getUtilService() {
        return utilService;
    }
}
