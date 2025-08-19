package school.token.groupe7_hackatonback_aout2025.application.features.file.query.getContentByFile;

public class GetContentByFileOutput {
    private String content;

    public GetContentByFileOutput() {
    }

    public GetContentByFileOutput(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
