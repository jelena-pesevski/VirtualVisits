package org.unibl.etf.virtualvisits.models.responses;

import java.util.List;

public class AttendVisitResponse {

    private List<String> imagesUrls;

    private String videoUrl;

    private String ytLink;

    private long endingTimeInMillis;

    public List<String> getImagesUrls() {
        return imagesUrls;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getYtLink() {
        return ytLink;
    }

    public void setYtLink(String ytLink) {
        this.ytLink = ytLink;
    }

    public long getEndingTimeInMillis() {
        return endingTimeInMillis;
    }

    public void setEndingTimeInMillis(long endingTimeInMillis) {
        this.endingTimeInMillis = endingTimeInMillis;
    }
}
