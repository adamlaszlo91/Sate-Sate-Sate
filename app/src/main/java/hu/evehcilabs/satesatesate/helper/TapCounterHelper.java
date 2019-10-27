package hu.evehcilabs.satesatesate.helper;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.preference.PreferenceManager;
import hu.evehcilabs.satesatesate.R;
import java.lang.ref.WeakReference;
import java.util.Locale;

public class TapCounterHelper extends BaseObservable {
  private static final String KEY_TAP_COUNT = "KEY_TAP_COUNT";

  private WeakReference<Context> contextWeakReference;
  private String tapCounterText;
  private int tapCounter;

  public TapCounterHelper(Context context) {
    contextWeakReference = new WeakReference<>(context);
    tapCounter = readTapCount();
  }

  @Bindable public String getTapCounterText() {
    return tapCounterText;
  }

  private int readTapCount() {
    SharedPreferences sharedPreferences =
      PreferenceManager.getDefaultSharedPreferences(contextWeakReference.get());
    return sharedPreferences.getInt(KEY_TAP_COUNT, 0);
  }

  private void writeTapCount() {
    SharedPreferences sharedPreferences =
      PreferenceManager.getDefaultSharedPreferences(contextWeakReference.get());
    sharedPreferences.edit().putInt(KEY_TAP_COUNT, tapCounter).apply();
  }

  public void updateTapCounterText() {
    tapCounterText = String.format(Locale.getDefault(), "%s\n%d",
                                   contextWeakReference.get().getString(R.string.text_tap_meliodas),
                                   tapCounter);
    notifyPropertyChanged(hu.evehcilabs.satesatesate.BR.tapCounterText);
  }

  public void onNewTap() {
    tapCounter++;
    writeTapCount();
    updateTapCounterText();
  }
}
