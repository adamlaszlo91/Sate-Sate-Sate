package hu.evehcilabs.satesatesate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import hu.evehcilabs.androidbase.BaseFragment;
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.view.MeliodasImageView;

public class MainFragment extends BaseFragment {

  private MeliodasImageView meliodasImageView;

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Nullable @Override public View onCreateView(
    @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    initViews(view);
    initActions();
    return view;
  }

  private void initViews(View view) {
    meliodasImageView = view.findViewById(R.id.image_meliodas);
  }

  private void initActions() {
    meliodasImageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        meliodasImageView.getAnimationHelper().reStartWiggleAnimation();
      }
    });
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
