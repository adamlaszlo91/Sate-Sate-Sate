package hu.evehcilabs.satesatesate.fragment;

import android.content.Context;
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
import hu.evehcilabs.satesatesate.helper.MediaPlayerHelper;
import hu.evehcilabs.satesatesate.view.MeliodasImageView;
import java.util.Random;

public class MainFragment extends BaseFragment {
  private static final int LOSTVAYNE_CLONE_MULTIPLIER = 4;

  private ConstraintLayout meliodasFigureContainer;
  private MeliodasImageView meliodasImageView;
  private MeliodasImageView[] meliodasClones = new MeliodasImageView[LOSTVAYNE_CLONE_MULTIPLIER];
  private MediaPlayerHelper mediaPlayerHelper;

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mediaPlayerHelper = new MediaPlayerHelper(getActivity());
  }

  @Override public void onDetach() {
    mediaPlayerHelper.destroy();
    super.onDetach();
  }

  @Override public void onStop() {
    stopAllAnimations();
    pauseAllSounds();
    super.onStop();
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
        stopAllAnimations();
        pauseAllSounds();
        float randomActionChange = new Random().nextFloat();
        if (randomActionChange < 0.5) {
          meliodasImageView.startWiggleAnimation();
          mediaPlayerHelper.startSateSateSate();
        } else {
          makeMeliodasClonesAppear();
          meliodasImageView.startWiggleAnimation();
          mediaPlayerHelper.startSateSateSateMulti();
        }
      }
    });
  }

  private void stopAllAnimations() {
    meliodasImageView.stopAnimations();
    for (MeliodasImageView meliodasImageView : meliodasClones) {
      if (meliodasImageView != null) {
        meliodasImageView.stopAnimations();
        meliodasFigureContainer.removeView(meliodasImageView);
      }
    }
  }

  private void pauseAllSounds() {
    mediaPlayerHelper.pauseSateSateSate();
    mediaPlayerHelper.pauseSateSateSateMulti();
  }

  // region Meliodas clones

  private void makeMeliodasClonesAppear() {
    initMeliodasClones();
    startMeliodasClonesAnimation();
  }

  private void initMeliodasClones() {
    for (int i = 0; i < meliodasClones.length; i++) {
      final MeliodasImageView meliodasClone = new MeliodasImageView(getActivity());
      meliodasClone.setId(ViewCompat.generateViewId());
      meliodasFigureContainer.addView(meliodasClone);
      meliodasClone.setupSizeAndRandomPosition();
      meliodasClone.setVisibility(View.INVISIBLE);
      meliodasClones[i] = meliodasClone;
    }
  }

  private void startMeliodasClonesAnimation() {
    for (int i = 0; i < meliodasClones.length; i++) {
      final MeliodasImageView meliodasClone = meliodasClones[i];
      meliodasClone.post(new Runnable() {
        @Override public void run() {
          meliodasClone.startBounceInOutAnimation();
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
