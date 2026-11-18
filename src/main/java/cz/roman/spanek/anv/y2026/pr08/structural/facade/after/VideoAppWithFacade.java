package cz.roman.spanek.anv.y2026.pr08.structural.facade.after;

public class VideoAppWithFacade {
    public static void main(String[] args) {
        VideoServiceFacade videoService = new VideoServiceFacade();

        videoService.processAndUploadVideo(
                "funny_cat_video.mp4",
                "funny_cat_audio.mp3",
                "YouTube"
        );
    }
}