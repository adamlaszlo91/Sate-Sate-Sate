package hu.evehcilabs.satesatesate.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TapCounterUtil {
  private static final String KEY_TAP_COUNT = "KEY_TAP_COUNT";

  public static int getTapCount(Context context) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    return sharedPreferences.getInt(KEY_TAP_COUNT, 0);
  }

  public static int getIncreasedTapCount(Context context) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    int newTapCount = sharedPreferences.getInt(KEY_TAP_COUNT, 0)+1;
    sharedPreferences.edit().putInt(KEY_TAP_COUNT, newTapCount).apply();
    return newTapCount;
  }
}
