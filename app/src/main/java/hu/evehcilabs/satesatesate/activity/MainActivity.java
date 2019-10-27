package hu.evehcilabs.satesatesate.activity;

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
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.databinding.ActivityMainBinding;
import hu.evehcilabs.satesatesate.fragment.MainFragment;

public class MainActivity extends BaseActivity {

  private ActivityMainBinding binding;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    if (savedInstanceState == null) {
      gotoMainFragment();
    }
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
