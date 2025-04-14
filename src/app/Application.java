package app;

import authentication.*;
import logging.Logger;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter authentication type (basic / oauth): ");
        final String authType = scanner.nextLine();

        Credentials credentials;
        Authenticator authenticator;

        if ("basic".equalsIgnoreCase(authType)) {
            System.out.println("Enter username: ");
            final String username = scanner.nextLine();

            System.out.println("Enter password: ");
            final String password = scanner.nextLine();

            credentials = new BasicAuthCredentials(username, password);
            authenticator = new BasicAuthAuthenticator(new Logger());

        } else if ("oauth".equalsIgnoreCase(authType)) {
            System.out.println("Enter OAuth token: ");
            final String token = scanner.nextLine();

            credentials = new OAuthCredentials(token);
            authenticator = new OAuthAuthenticator(new Logger());

        } else {
            System.out.println("Unsupported authentication type.");
            return;
        }

        boolean isAuthenticated;

        if (credentials instanceof BasicAuthCredentials) {
            isAuthenticated = authenticator.authenticate(credentials);
            if (!isAuthenticated) {
                System.out.println("Basic Authentication failed. Retrying...");
                authenticator.retryOnFailure(credentials, 3);
            }
        } else {
            isAuthenticated = authenticator.authenticate(credentials);
            if (!isAuthenticated) {
                System.out.println("OAuth Authentication failed. Retrying...");
                authenticator.retryOnFailure(credentials, 3);
            }
        }

        if (isAuthenticated) {
            System.out.println("Authentication succeeded.");
        } else {
            System.out.println("Authentication failed after retries.");
        }

        scanner.close();
    }
}
