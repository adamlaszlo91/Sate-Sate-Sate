package hu.evehcilabs.satesatesate.helper;

import android.content.Context;
import android.media.MediaPlayer;
import hu.evehcilabs.satesatesate.R;

public class MediaPlayerHelper {
  private MediaPlayer sateSateSateMediaPlayer;
  private MediaPlayer sateSateSateMultiMediaPlayer;
  private MediaPlayer tanchouMediaPlayer;
  private MediaPlayer sateSateSateRemixMediaPlayer;

  public MediaPlayerHelper(Context context) {
    sateSateSateMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate);
    sateSateSateMultiMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate_multi);
    tanchouMediaPlayer = MediaPlayer.create(context, R.raw.ban_taisho);
    sateSateSateRemixMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate_remix);
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
    if (tanchouMediaPlayer.isPlaying()) {
      tanchouMediaPlayer.stop();
    }
    tanchouMediaPlayer.release();
    if (sateSateSateRemixMediaPlayer.isPlaying()) {
      sateSateSateRemixMediaPlayer.stop();
    }
    sateSateSateRemixMediaPlayer.release();
  }

  // region Sate sate sate

  public void startSateSateSate() {
    // The audio is not perfect, we need to seek into it
    // TODO: Edit audio later
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
    // TODO: Edit audio later
    sateSateSateMultiMediaPlayer.seekTo(700);
    sateSateSateMultiMediaPlayer.start();
  }

  public void pauseSateSateSateMulti() {
    if (sateSateSateMultiMediaPlayer.isPlaying()) {
      sateSateSateMultiMediaPlayer.pause();
    }
  }

  // endregion

  // region Tanchou

  public void startTanchou() {
    tanchouMediaPlayer.seekTo(0);
    tanchouMediaPlayer.start();
  }

  public void pauseTanchou() {
    if (tanchouMediaPlayer.isPlaying()) {
      tanchouMediaPlayer.pause();
    }
  }

  // endregion

  // region Sate sate sate remix

  public void startSateSateSateRemix() {
    sateSateSateRemixMediaPlayer.seekTo(0);
    sateSateSateRemixMediaPlayer.start();
  }

  public void pauseSateSateSateRemix() {
    if (sateSateSateRemixMediaPlayer.isPlaying()) {
      sateSateSateRemixMediaPlayer.pause();
    }
  }

  // endregion
}
