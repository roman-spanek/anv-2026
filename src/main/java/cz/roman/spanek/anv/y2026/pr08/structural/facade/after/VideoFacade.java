package cz.roman.spanek.anv.y2026.pr08.structural.facade.after;

class VideoFacade {
    private VideoEncoder encoder = new VideoEncoder();
    private VideoEffects effects = new VideoEffects();

    public void processVideo(String videoFile) {
        encoder.encode(videoFile);
        effects.addWatermark(videoFile);
    }
}
