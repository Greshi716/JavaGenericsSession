package authentication;

import logging.Logger;

public class BasicAuthAuthenticator extends CoreAuthenticator<BasicAuthCredentials> {

    public static final String VALID_USER_NAME = "basicUser";
    public static final String VALID_PASSWORD = "validPassword";
    protected final Logger logger;

    public BasicAuthAuthenticator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean authenticate(BasicAuthCredentials basicAuthCredentials) {
        final boolean success = VALID_USER_NAME.equals(basicAuthCredentials.getUsername()) &&
                VALID_PASSWORD.equals(basicAuthCredentials.getPassword());
        this.logger.log(success ? "Basic Auth Success" : " Basic Auth Failed");
        return success;
    }

}