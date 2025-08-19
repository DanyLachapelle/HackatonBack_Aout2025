package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getAudioFile;

public class GetAudioFileQuery {
    private Long userId;

    public GetAudioFileQuery(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
