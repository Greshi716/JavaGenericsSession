package authentication;

public interface Authenticator<T extends  Credentials> {
    boolean authenticate(final T credentials);

    void retryOnFailure(T credentials, int maxAttempts);
}