package com.swisscom.cloud.sb.test.httpserver

class HttpServerConfig {
    private final int httpPort
    private int httpsPort

    private String username
    private String password
    private AuthenticationType authenticationType = AuthenticationType.NONE
    private String clientSideKeyStorePath
    private String clientSideKeyStorePassword

    static enum AuthenticationType {
        NONE, SIMPLE, DIGEST
    }

    private HttpServerConfig(int httpPort) { this.httpPort = httpPort }

    static HttpServerConfig create(int port) {
        new HttpServerConfig(port)
    }

    HttpServerConfig withHttpsPort(int port) {
        this.httpsPort = port
        return this
    }

    HttpServerConfig withSimpleHttpAuthentication(String username, String password) {
        this.authenticationType = authenticationType.SIMPLE
        this.username = username
        this.password = password
        return this
    }

    HttpServerConfig withDigestAuthentication(String user, String password) {
        this.authenticationType = authenticationType.DIGEST
        this.username = user
        this.password = password
        return this
    }

    HttpServerConfig withClientSideCertificate(String keyStorePath, String keyStorePassword) {
        this.clientSideKeyStorePath = keyStorePath
        this.clientSideKeyStorePassword = keyStorePassword
        return this
    }

    int getHttpPort() {
        return httpPort
    }

    String getUsername() {
        return username
    }

    String getPassword() {
        return password
    }

    AuthenticationType getAuthenticationType() {
        return authenticationType
    }

    int getHttpsPort() {
        return httpsPort
    }

    String getClientSideKeyStorePath() {
        return clientSideKeyStorePath
    }

    String getClientSideKeyStorePassword() {
        return clientSideKeyStorePassword
    }
}
