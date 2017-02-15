package com.coolnimesh43.persistence.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "persistence", ignoreInvalidFields = false)
public class PersistenceProperties {

    private final Security security = new Security();

    private final Async async = new Async();

    public Security getSecurity() {
        return security;
    }

    public Async getAsync() {
        return async;
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

    public static class Async {
        private int corePoolSize;
        private int maxPoolSize;
        private int queueCapacity;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }

    }
}
