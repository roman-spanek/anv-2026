package cz.roman.spanek.anv.y2026.pr08.structural.facade.after;

class AudioMixer {
    public void mix(String audioFile) {
        System.out.println("Mixing audio: " + audioFile);
    }

    public void normalize(String audioFile) {
        System.out.println("Normalizing audio: " + audioFile);
    }
}