package hu.evehcilabs.satesatesate.helper;

import android.content.Context;
import android.media.MediaPlayer;
import hu.evehcilabs.satesatesate.R;

public class MediaPlayerHelper {
  MediaPlayer sateSateSateMediaPlayer;

  public MediaPlayerHelper(Context context) {
    sateSateSateMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate);
  }

  public void destroy() {
    if (sateSateSateMediaPlayer.isPlaying()) {
      sateSateSateMediaPlayer.stop();
    }
    sateSateSateMediaPlayer.release();
  }

  // region Sate sate sate

  public void restartSateSateSate() {
    pauseSateSateSate();
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
}
