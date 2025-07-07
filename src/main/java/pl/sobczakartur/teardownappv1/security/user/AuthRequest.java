package pl.sobczakartur.teardownappv1.security.user;


import lombok.Getter;

@Getter
public class AuthRequest {
    private String username;
    private String password;
}