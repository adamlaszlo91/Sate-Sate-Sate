package hu.evehcilabs.satesatesate.helper;

import android.content.Context;
import android.media.MediaPlayer;
import hu.evehcilabs.satesatesate.R;

public class MediaPlayerHelper {
  MediaPlayer sateSateSateMediaPlayer;
  MediaPlayer sateSateSateMultiMediaPlayer;

  public MediaPlayerHelper(Context context) {
    sateSateSateMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate);
    sateSateSateMultiMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate_multi);
  }

  public void destroy() {
    if (sateSateSateMediaPlayer.isPlaying()) {
      sateSateSateMediaPlayer.stop();
    }
    sateSateSateMediaPlayer.release();
    if (sateSateSateMultiMediaPlayer.isPlaying()) {
      sateSateSateMultiMediaPlayer.stop();
    }
    sateSateSateMultiMediaPlayer.release();
  }

  // region Sate sate sate

  public void startSateSateSate() {
    // The audio is not perfect, we need to seek into it
    sateSateSateMediaPlayer.seekTo(700);
    sateSateSateMediaPlayer.start();
  }

  public void pauseSateSateSate() {
    if (sateSateSateMediaPlayer.isPlaying()) {
      sateSateSateMediaPlayer.pause();
    }
  }

  // endregion

  // region Sate sate sate multi

  public void startSateSateSateMulti() {
    // The audio is not perfect, we need to seek into it
    sateSateSateMultiMediaPlayer.seekTo(700);
    sateSateSateMultiMediaPlayer.start();
  }

  public void pauseSateSateSateMulti() {
    if (sateSateSateMultiMediaPlayer.isPlaying()) {
      sateSateSateMultiMediaPlayer.pause();
    }
  }

  // endregion
}
