package com.coolnimesh43.persistence.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "persistence", ignoreInvalidFields = false)
public class PersistenceProperties {

    private final Security security = new Security();

    public Security getSecurity() {
        return security;
    }

    public static class Security {
        private final XAuth xauth = new XAuth();

        public XAuth getXauth() {
            return xauth;
        }

        public static class XAuth {
            private String token = "IamAweS0Me";
            private int validityPeriod = 8640000;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getValidityPeriod() {
                return validityPeriod;
            }

            public void setValidityPeriod(int validityPeriod) {
                this.validityPeriod = validityPeriod;
            }

        }
    }
}
