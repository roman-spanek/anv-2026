package cz.roman.spanek.anv.y2026.pr08.structural.facade.before;

public class VideoApp {
    public static void main(String[] args) {
        VideoEncoder encoder = new VideoEncoder();
        AudioMixer mixer = new AudioMixer();
        Uploader uploader = new Uploader();

        String fileName = "funny_cat_video.mp4";
        encoder.encode(fileName);
        mixer.mix("funny_cat_audio.mp3");
        uploader.upload("YouTube", fileName);
    }
}