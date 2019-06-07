package hu.evehcilabs.satesatesate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import hu.evehcilabs.androidbase.BaseFragment;
import hu.evehcilabs.satesatesate.R;

public class MainFragment extends BaseFragment {

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Nullable @Override public View onCreateView(
    @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fragment_main, container, false);

    return view;
  }

  // region Animation

  private void startBackgroundAnimation() {
    // TODO: Implement
  }

  private void stopBackgroundAnimation() {
    // TODO: Implement
  }

  // endregion
}
