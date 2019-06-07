package hu.evehcilabs.satesatesate.activity;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import hu.evehcilabs.androidbase.BaseActivity;
import hu.evehcilabs.androidbase.TransactionSettings;
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.fragment.MainFragment;

public class MainActivity extends BaseActivity {

  protected ConstraintLayout fragmentContainer;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();

    if (savedInstanceState == null) {
      gotoMainFragment();
    }
  }

  private void initViews() {
    fragmentContainer = findViewById(R.id.container_fragment);
  }

  private void gotoMainFragment() {
    TransactionSettings settings = new TransactionSettings();
    settings.setRoot(true);
    settings.setFragment(MainFragment.newInstance());
    settings.setTargetViewGroupId(fragmentContainer.getId());
    addFragment(settings);
  }
}
