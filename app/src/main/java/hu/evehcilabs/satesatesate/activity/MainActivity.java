package hu.evehcilabs.satesatesate.activity;

import android.os.Bundle;
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

  private void gotoMainFragment() {
    TransactionSettings settings = new TransactionSettings();
    settings.setRoot(true);
    settings.setFragment(MainFragment.newInstance());
    settings.setTargetViewGroupId(binding.containerFragment.getId());
    addFragment(settings);
  }
}
