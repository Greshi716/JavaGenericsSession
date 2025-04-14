package authentication;

public interface Authenticator {
    boolean authenticate(final Credentials credentials);

    void retryOnFailure(Credentials credentials, int maxAttempts);
}