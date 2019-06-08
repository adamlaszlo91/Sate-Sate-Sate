package hu.evehcilabs.satesatesate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import hu.evehcilabs.androidbase.BaseFragment;
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.view.MeliodasImageView;

public class MainFragment extends BaseFragment {
  private static final int LOSTVAYNE_CLONE_MULTIPLIER = 4;

  private ConstraintLayout meliodasFigureContainer;
  private MeliodasImageView meliodasImageView;
  private MeliodasImageView[] meliodasClones;

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
    meliodasFigureContainer = view.findViewById(R.id.container_meliodas_figure);
    meliodasImageView = view.findViewById(R.id.image_meliodas);
  }

  private void initActions() {
    meliodasImageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //meliodasImageView.getAnimationHelper().reStartWiggleAnimation();
        makeMeliodasClonesAppear();
      }
    });
  }

  // region Meliodas clones

  private void makeMeliodasClonesAppear() {
    initMeliodasClones();
    startMeliodasClonesAnimation();
  }

  private void initMeliodasClones() {
    meliodasClones = new MeliodasImageView[LOSTVAYNE_CLONE_MULTIPLIER];
    for (int i = 0; i < LOSTVAYNE_CLONE_MULTIPLIER; i++) {
      final MeliodasImageView meliodasClone = new MeliodasImageView(getActivity());
      meliodasClone.setId(ViewCompat.generateViewId());
      meliodasFigureContainer.addView(meliodasClone);
      meliodasClone.setupSizeAndRandomPosition();
      meliodasClone.setVisibility(View.INVISIBLE);
      meliodasClones[i] = meliodasClone;
    }
  }

  private void startMeliodasClonesAnimation() {
    for (int i = 0; i < LOSTVAYNE_CLONE_MULTIPLIER; i++) {
      final MeliodasImageView meliodasClone = meliodasClones[i];
      meliodasClone.post(new Runnable() {
        @Override public void run() {
          meliodasClone.reStartBounceInOutAnimation();
          meliodasClone.setVisibility(View.VISIBLE);
        }
      });
    }
  }

  // endregion

  // region Animation

  private void startBackgroundAnimation() {
    // TODO: Implement
  }

  private void stopBackgroundAnimation() {
    // TODO: Implement
  }

  // endregion
}
