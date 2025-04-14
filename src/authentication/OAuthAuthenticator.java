package authentication;

import logging.Logger;

public class OAuthAuthenticator implements Authenticator {

    public static final String VALID_OAUTH_TOKEN = "validOAuthToken";
    private final Logger logger;

    public OAuthAuthenticator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean authenticate(Credentials credentials) {
        if (credentials instanceof OAuthCredentials oauth) {
            final boolean success = VALID_OAUTH_TOKEN.equals(oauth.getToken());
            System.out.println(success ? "OAuth Auth Success" : "OAuth Auth Failed");
            return success;
        }
        return false;
    }

    @Override
    public void retryOnFailure(Credentials credentials, int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {
            this.logger.log("Retrying... Attempt " + (i + 1));
            if (authenticate(credentials)) {
                break;
            }
        }
    }
}
