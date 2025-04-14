package authentication;

import logging.Logger;

public abstract class CoreAuthenticator<T extends Credentials> {

    private Logger logger;

    public abstract boolean authenticate(final T credentials);

   public void retryOnFailure(T credentials, int maxAttempts){
        for (int i = 1; i <= maxAttempts; i++) {
            if (authenticate(credentials)) {
                logger.log("Authentication succeeded on attempt " + i);
                return;
            } else {
                logger.log("Authentication failed on attempt " + i);
            }
        }
    }
}
