package com.bruceycode.My_Rest_Api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class ValueDtSourceConfig {

        @Value("${server.port}")
        private int serverPort;

        @Value("${spring.datasource.url}")
        private String datasourceUrl;

        @Value("${spring.datasource.username}")
        private String datasourceUsername;

        @Value("${spring.datasource.password}")
        private String datasourcePassword;

        // Getters and Setters
        public int getServerPort() {
            return serverPort;
        }

        public void setServerPort(int serverPort) {
            this.serverPort = serverPort;
        }

        public String getDatasourceUrl() {
            return datasourceUrl;
        }

        public void setDatasourceUrl(String datasourceUrl) {
            this.datasourceUrl = datasourceUrl;
        }

        public String getDatasourceUsername() {
            return datasourceUsername;
        }

        public void setDatasourceUsername(String datasourceUsername) {
            this.datasourceUsername = datasourceUsername;
        }

        public String getDatasourcePassword() {
            return datasourcePassword;
        }

        public void setDatasourcePassword(String datasourcePassword) {
            this.datasourcePassword = datasourcePassword;
        }
    }
