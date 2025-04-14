package authentication;

import logging.Logger;

public class CookieAuth extends BasicAuthAuthenticator{

    public CookieAuth(Logger logger) {
        super(logger);
    }

    public void retryOnFailure(BasicAuthCredentials credentials, int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {
            logger.log(credentials.toString());
            if (authenticate(credentials)) {
                break;
            }
        }
    }
}
