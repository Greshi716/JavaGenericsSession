package authentication;

import logging.Logger;

public class OAuthAuthenticator implements Authenticator<OAuthCredentials> {

    public static final String VALID_OAUTH_TOKEN = "validOAuthToken";
    private final Logger logger;

    public OAuthAuthenticator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean authenticate(OAuthCredentials oAuthCredentials) {

        final boolean success = VALID_OAUTH_TOKEN.equals(oAuthCredentials.getToken());
        System.out.println(success ? "OAuth Auth Success" : "OAuth Auth Failed");
        return success;
    }

    @Override
    public void retryOnFailure(OAuthCredentials credentials, int maxAttempts) {

        for (int i = 0; i < maxAttempts; i++) {
            this.logger.log("Retrying... Attempt " + (i + 1));
            if (authenticate(credentials)) {
                break;
            }
        }
    }
}
