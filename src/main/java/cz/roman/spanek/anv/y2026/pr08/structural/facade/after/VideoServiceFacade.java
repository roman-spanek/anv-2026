package cz.roman.spanek.anv.y2026.pr08.structural.facade.after;

class VideoServiceFacade {
    private VideoFacade encoder;
    private AudioMixer mixer;
    private Uploader uploader;

    public VideoServiceFacade() {
        this.encoder = new VideoFacade();
        this.mixer = new AudioMixer();
        this.uploader = new Uploader();
    }

    public void processAndUploadVideo(String videoFile, String audioFile, String destination) {
        encoder.processVideo(videoFile);
        mixer.mix(audioFile);
        uploader.upload(destination, videoFile);
    }
}