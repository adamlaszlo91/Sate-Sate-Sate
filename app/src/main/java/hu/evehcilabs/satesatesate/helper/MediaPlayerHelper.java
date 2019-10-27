package hu.evehcilabs.satesatesate.helper;

import android.content.Context;
import android.media.MediaPlayer;
import hu.evehcilabs.satesatesate.R;

public class MediaPlayerHelper {
  private MediaPlayer sateSateSateMediaPlayer;
  private MediaPlayer sateSateSateMultiMediaPlayer;
  private MediaPlayer tanchouMediaPlayer;
  private MediaPlayer sateSateSateRemixMediaPlayer;
  private MediaPlayer transporkMediaPlayer;

  public MediaPlayerHelper(Context context) {
    sateSateSateMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate);
    sateSateSateMultiMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate_multi);
    tanchouMediaPlayer = MediaPlayer.create(context, R.raw.ban_taisho);
    sateSateSateRemixMediaPlayer = MediaPlayer.create(context, R.raw.sate_sate_sate_remix);
    transporkMediaPlayer = MediaPlayer.create(context, R.raw.hawk_transpork);
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
    if (transporkMediaPlayer.isPlaying()) {
      transporkMediaPlayer.stop();
    }
    transporkMediaPlayer.release();
  }

  // region Sate sate sate

  public void startSateSateSate() {
    sateSateSateMediaPlayer.seekTo(0);
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
    sateSateSateMultiMediaPlayer.seekTo(0);
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

  // region Transpork

  public void startTranspork() {
    transporkMediaPlayer.seekTo(0);
    transporkMediaPlayer.start();
  }

  public void pauseTranspork() {
    if (transporkMediaPlayer.isPlaying()) {
      transporkMediaPlayer.pause();
    }
  }

  // endregion
}
