package hu.evehcilabs.satesatesate.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.ColorRes;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import hu.evehcilabs.androidbase.BaseActivity;
import hu.evehcilabs.androidbase.TransactionSettings;
import hu.evehcilabs.satesatesate.BuildConfig;
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.databinding.ActivityMainBinding;
import hu.evehcilabs.satesatesate.fragment.MainFragment;
import hu.evehcilabs.satesatesate.helper.ChangelogHelper;
import java.util.Locale;

public class MainActivity extends BaseActivity {

  private ActivityMainBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    if (savedInstanceState == null) {
      gotoMainFragment();
    }
  }

  @Override protected void onResume() {
    super.onResume();
    String changelogEntry =
      new ChangelogHelper().getUnseenEntryOfAppVersion(this, BuildConfig.VERSION_NAME);
    if (changelogEntry != null) {
      showChangelogEntry(changelogEntry);
    }
  }

  private void showChangelogEntry(String changelogEntry) {
    changelogEntry = appendVersionToChangelogEntry(changelogEntry);
    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    alertDialog.setTitle(getString(R.string.dialog_title_whats_new));
    alertDialog.setMessage(changelogEntry);
    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.button_ok),
                          new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                              dialog.dismiss();
                            }
                          });
    alertDialog.show();
  }

  private String appendVersionToChangelogEntry(String changelogEntry) {
    return String.format(Locale.ENGLISH, "v%s+%d\n\n%s", BuildConfig.VERSION_NAME,
                         BuildConfig.VERSION_CODE, changelogEntry);
  }

  public void setActionAndStatusBarColors(@ColorRes int color) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setBackgroundDrawable(
        new ColorDrawable(ResourcesCompat.getColor(getResources(), color, null)));
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(ResourcesCompat.getColor(getResources(), color, null));
    }
  }

  private void gotoMainFragment() {
    TransactionSettings settings = new TransactionSettings();
    settings.setRoot(true);
    settings.setFragment(MainFragment.newInstance());
    settings.setTargetViewGroupId(binding.containerFragment.getId());
    addFragment(settings);
  }
}
