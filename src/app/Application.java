package app;

import authentication.*;
import logging.Logger;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Logger logger = new Logger();

        System.out.println("Enter authentication type (basic / oauth / cookie): ");
        final String authType = scanner.nextLine();

        if ("basic".equalsIgnoreCase(authType)) {
            System.out.println("Enter username: ");
            final String username = scanner.nextLine();
            System.out.println("Enter password: ");
            final String password = scanner.nextLine();

            final BasicAuthCredentials credentials = new BasicAuthCredentials(username, password);
            final Authenticator<BasicAuthCredentials> authenticator = new BasicAuthAuthenticator(logger);

            processAuthentication(authenticator, credentials);

        } else if ("oauth".equalsIgnoreCase(authType)) {
            System.out.println("Enter OAuth token: ");
            final String token = scanner.nextLine();

            final OAuthCredentials credentials = new OAuthCredentials(token);
            final Authenticator<OAuthCredentials> authenticator = new OAuthAuthenticator(logger);

            processAuthentication(authenticator, credentials);

        } else {
            System.out.println("Unsupported authentication type.");
        }

        scanner.close();
    }

    private static <T extends Credentials> void processAuthentication(Authenticator<T> authenticator, T credentials) {
        final boolean isAuthenticated = authenticator.authenticate(credentials);

        if (!isAuthenticated) {
            System.out.println("Authentication failed. Retrying...");
            authenticator.retryOnFailure(credentials, 3);
        }
        if (authenticator.authenticate(credentials)) {
            System.out.println("Authentication succeeded.");
        } else {
            System.out.println("Authentication failed after retries.");
        }
    }
}