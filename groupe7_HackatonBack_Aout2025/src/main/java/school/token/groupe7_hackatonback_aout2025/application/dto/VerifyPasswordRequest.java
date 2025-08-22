package school.token.groupe7_hackatonback_aout2025.application.dto;


public class VerifyPasswordRequest {
    private String password;
    private Long userId;

    public VerifyPasswordRequest() {}
    public VerifyPasswordRequest(String password, Long userId) {
        this.password = password;
        this.userId = userId;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

