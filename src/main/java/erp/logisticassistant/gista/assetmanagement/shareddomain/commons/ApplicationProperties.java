package erp.logisticassistant.gista.assetmanagement.shareddomain.commons;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties
public class ApplicationProperties {
    private Spring spring;
    private Server server;
    private Keycloak keycloak;
    private Sap sap;
    private Gista gista;
    private Asset asset;

    @Data
    public static class Gista {
        private Keycloak keycloak;
        private Mail mail;
        private Config config;
        private Asset asset;

        @Data
        public static class Keycloak {
            private Management management;
            @Data
            public static class Management {
                private String username;
                private Client client;
                @Data
                public static class Client {
                    private Map<String, List<String>> integrations;
                }
            }
        }

        @Data
        public static class Mail {
            private String help;
        }

        @Data
        public static class Config {
            private String [] authenticatedUrls;
        }

        @Data
        public static class Asset {
            private Role role;
            @Data
            public static class Role {
                private List<String> admin;
                private List<String> kp;
                private List<String> txb;
                private List<String> kanwil;
                private List<String> audit;
                private List<String> support;
            }
        }
    }

    @Data
    public static class Spring {
        private Profiles profile;
        private Application application;
        private Datasource datasource;
        private Flyway flyway;
        private Kafka kafka;
        private Boolean maintenanceMode;
        private Security security;

        @Data
        public static class Security {
            private String[] authWhiteList;
            private String[] allowOriginList;
            private String[] requestMethodList;
            private String[] allowHeaderList;
            private String[] exposeHeaderList;
            private List<String> intranetIpPrefix;
        }

        @Data
        public static class Profiles {
            private String active;
        }
        @Data
        public static class Application {
            private String id;
            private String name;
            private String version;
        }
        @Data
        public static class Datasource {
            private String url;
            private String username;
            private String password;
        }
        @Data
        public static class Flyway {
            private Boolean enable;
        }
        @Data
        public static class Kafka {
            @Value("${retry-Prefix}") private String retryPrefix;
            private Topic topic;
            private Group group;
            private Producer producer;
            private Consumer consumer;

            @Data
            public static class Topic {
                private String menu;
            }
            @Data
            public static class Group {
                private String menu;
            }

            @Data
            public static class Producer{
                private String deathLetterTopic;
            }

            @Data
            public static class Consumer{
                private int maxRetry;
                private Topic topic;

                @Data
                public static class Topic{
                    private String retry;
                    private String groupId;
                    private Identity identity;

                    @Data
                    public static class Identity{
                        private String application;
                        private String applicationRole;
                        private String compositeRole;
                        private String assosiateRole;
                        private String accessRole;
                        private String menu;
                        private String user;
                    }
                }
            }
        }
    }

    @Data
    public static class Server {
        private Servlet servlet;

        @Data
        public static class Servlet {
            private String contextPath;
        }
    }

    @Data
    public static class Keycloak {
        @Value("${auth-server-url}") private String authServerUrl;
        private String realm;
        private String resource;

    }

    @Data
    public static class Sap{
        private String directory;

        private File file;

        @Data
        public static class File {
            private Path path;

            @Data
            public static class Path {
                private String material;
                private String materialGroup;
                private String service;
                private String witholdingTax;
                private String vendor;
                private String costCenter;
                private String productCode;
                private String purchasingGroup;
                private String taxCode;
                private String coa;
                private String coaMapping;
                private String currency;
            }
        }
        
    }

    @Data
    public static class Asset {
        private Attachment attachment;
        @Data
        public static class Attachment{
            private Submission submission;

            @Data
            public static class Submission{
                private String documentType;
                private String transactionType;
            }
        }
    }

}
