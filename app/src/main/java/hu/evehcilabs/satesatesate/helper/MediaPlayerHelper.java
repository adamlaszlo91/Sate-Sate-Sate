package hu.evehcilabs.satesatesate.helper;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import hu.evehcilabs.satesatesate.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MediaPlayerHelper {

  public enum SoundIdentifier {
    SATE_SATE_SATE, SATE_SATE_SATE_MULTI, TANCHOU, SATE_SATE_SATE_REMIX;
  }

  private static HashMap<SoundIdentifier, Integer> soundResourceMap =
    new HashMap<SoundIdentifier, Integer>() {{
      put(SoundIdentifier.SATE_SATE_SATE, R.raw.sate_sate_sate);
      put(SoundIdentifier.SATE_SATE_SATE_MULTI, R.raw.sate_sate_sate_multi);
      put(SoundIdentifier.TANCHOU, R.raw.ban_taisho);
      put(SoundIdentifier.SATE_SATE_SATE_REMIX, R.raw.sate_sate_sate_remix);
    }};

  private HashMap<SoundIdentifier, Integer> soundIdentifierMap = new HashMap<>();
  private ArrayList<SoundIdentifier> loadedSounds = new ArrayList<>();
  private Integer currentStreamId = null;
  private SoundPool soundPool;

  public MediaPlayerHelper(Context context) {
    soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
      @Override public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        if (status == 0) {
          for (Map.Entry<SoundIdentifier, Integer> entry : soundIdentifierMap.entrySet()) {
            if (entry.getValue() == sampleId) {
              loadedSounds.add(entry.getKey());
              break;
            }
          }
        }
      }
    });
    loadSounds(context);
  }

  private void loadSounds(Context context) {
    for (SoundIdentifier soundIdentifier : soundResourceMap.keySet()) {
      //noinspection ConstantConditions
      soundIdentifierMap.put(soundIdentifier,
                             soundPool.load(context, soundResourceMap.get(soundIdentifier), 1));
    }
  }

  private boolean isSoundLoaded(SoundIdentifier soundIdentifier) {
    return loadedSounds.contains(soundIdentifier);
  }

  public void destroy() {
    stop();
    soundPool.release();
    soundPool = null;
  }

  public void play(SoundIdentifier soundIdentifier) {
    if (isSoundLoaded(soundIdentifier)) {
      //noinspection ConstantConditions
      currentStreamId =
        soundPool.play(soundIdentifierMap.get(soundIdentifier), 1.0f, 1.0f, 1, 0, 1.0f);
    }
  }

  public void stop() {
    if (currentStreamId != null) {
      soundPool.stop(currentStreamId);
      currentStreamId = null;
    }
  }
}
