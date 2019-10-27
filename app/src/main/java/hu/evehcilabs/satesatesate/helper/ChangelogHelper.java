package hu.evehcilabs.satesatesate.helper;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;
import hu.evehcilabs.satesatesate.R;
import java.util.HashMap;

public class ChangelogHelper {

  private static final String PARAM_APP_VERSION_OF_SEEN_ENTRY = "PARAM_APP_VERSION_OF_SEEN_ENTRY";
  private static HashMap<String, Integer> changelogEntries = new HashMap<>();

  static {
    changelogEntries.put("1.1.0", R.string.changelog_entry_1_1_0);
  }

  public @Nullable String getUnseenEntryOfAppVersion(
    @NonNull Context context, @NonNull String versionName)
  {
    if (isAppVersionEntrySeen(context, versionName)) {
      return null;
    } else {
      saveAppVersionOfSeenEntry(context, versionName);
      return extractEntry(context, versionName);
    }
  }

  private @Nullable String extractEntry(@NonNull Context context, @NonNull String versionName) {
    if (changelogEntries.containsKey(versionName)) {
      Integer resourceIdentifier = changelogEntries.get(versionName);
      if (resourceIdentifier != null) {
        return context.getString(resourceIdentifier);
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  private boolean isAppVersionEntrySeen(
    @NonNull Context context, @NonNull String versionName)
  {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    String appVersionOfSeenEntry = preferences.getString(PARAM_APP_VERSION_OF_SEEN_ENTRY, "");
    return appVersionOfSeenEntry.equals(versionName);
  }

  private void saveAppVersionOfSeenEntry(
    @NonNull Context context, @NonNull String versionName)
  {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
    preferences.edit().putString(PARAM_APP_VERSION_OF_SEEN_ENTRY, versionName).apply();
  }
}
