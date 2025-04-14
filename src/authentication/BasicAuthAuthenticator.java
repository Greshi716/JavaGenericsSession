package authentication;

import logging.Logger;

public class BasicAuthAuthenticator implements Authenticator {

    public static final String VALID_USER_NAME = "basicUser";
    public static final String VALID_PASSWORD = "validPassword";
    protected final Logger logger;

    public BasicAuthAuthenticator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean authenticate(Credentials credentials) {
        if (credentials instanceof BasicAuthCredentials basic) {
            final boolean success = VALID_USER_NAME.equals(basic.getUsername()) &&
                    VALID_PASSWORD.equals(basic.getPassword());
            this.logger.log(success ? "Basic Auth Success" : " Basic Auth Failed");
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