package authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasicAuthCredentials extends Credentials {
    private final String username;
    private final String password;

}
