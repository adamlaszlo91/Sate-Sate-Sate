package hu.evehcilabs.satesatesate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import hu.evehcilabs.androidbase.BaseFragment;
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.helper.MediaPlayerHelper;
import hu.evehcilabs.satesatesate.util.TapCounterUtil;
import hu.evehcilabs.satesatesate.view.MeliodasImageView;
import java.util.ArrayList;
import java.util.Random;

public class MainFragment extends BaseFragment {
  private static final int LOSTVAYNE_CLONE_MULTIPLIER = 4;

  private ConstraintLayout meliodasFigureContainer;
  private MeliodasImageView meliodasImageView;
  private ArrayList<MeliodasImageView> meliodasClones = new ArrayList<>();
  private TextView tapMeliodasTextView;
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
    tapMeliodasTextView = view.findViewById(R.id.text_tap_meliodas);
    tapCounterTextNeedsUpdate(TapCounterUtil.getTapCount(getActivity()));
    initMeliodas();
  }

  private void initMeliodas() {
    meliodasImageView = new MeliodasImageView(getActivity());
    meliodasImageView.setId(ViewCompat.generateViewId());
    meliodasFigureContainer.addView(meliodasImageView);
    meliodasImageView.setupSizeAndCenterPosition();
  }

  private void initActions() {
    meliodasImageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        tapCounterTextNeedsUpdate(TapCounterUtil.getIncreasedTapCount(getActivity()));
        stopAllAnimations();
        pauseAllSounds();
        float randomActionChange = new Random().nextFloat();
        if (randomActionChange < 0.05) {
          showToast(getString(R.string.toast_tanchou));
          mediaPlayerHelper.startTanchou();
        } else if (randomActionChange < 0.15) {
          showToast(getString(R.string.toast_sate_sate_sate_multi));
          meliodasImageView.startWiggleAnimation();
          showMeliodasClones();
          mediaPlayerHelper.startSateSateSateMulti();
        } else if (randomActionChange < 0.25) {
          showToast(getString(R.string.toast_sate_mix));
          meliodasImageView.startSpinningAnimation();
          mediaPlayerHelper.startSateSateSateRemix();
        } else {
          showToast(getString(R.string.toast_sate_sate_sate));
          meliodasImageView.startWiggleAnimation();
          mediaPlayerHelper.startSateSateSate();
        }
      }
    });
  }

  private void tapCounterTextNeedsUpdate(int tapCount) {
    tapMeliodasTextView.setText(
      String.format("%s\n%d", getString(R.string.text_tap_meliodas), tapCount));
  }

  private void stopAllAnimations() {
    meliodasImageView.stopAnimations();
    synchronized (meliodasClones) {
      for (MeliodasImageView meliodasClone : meliodasClones) {
        if (ViewCompat.isAttachedToWindow(meliodasClone)) {
          meliodasClone.stopAnimations();
          meliodasFigureContainer.removeView(meliodasClone);
        }
      }
      meliodasClones.clear();
    }
  }

  private void pauseAllSounds() {
    mediaPlayerHelper.pauseSateSateSate();
    mediaPlayerHelper.pauseSateSateSateMulti();
    mediaPlayerHelper.pauseTanchou();
    mediaPlayerHelper.pauseSateSateSateRemix();
  }

  private void showToast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  // region Meliodas clones

  private void showMeliodasClones() {
    initMeliodasClones();
    startMeliodasClonesAnimation();
  }

  private void initMeliodasClones() {
    synchronized (meliodasClones) {
      for (int i = 0; i < LOSTVAYNE_CLONE_MULTIPLIER; i++) {
        MeliodasImageView meliodasClone = new MeliodasImageView(getActivity());
        meliodasClone.setId(ViewCompat.generateViewId());
        meliodasFigureContainer.addView(meliodasClone);
        meliodasClone.setupSizeAndRandomPosition();
        meliodasClones.add(meliodasClone);
      }
    }
  }

  private void startMeliodasClonesAnimation() {
    synchronized (meliodasClones) {
      for (final MeliodasImageView meliodasClone : meliodasClones) {
        meliodasClone.setVisibility(View.INVISIBLE);
        meliodasClone.post(new Runnable() {
          @Override public void run() {
            meliodasClone.setVisibility(View.VISIBLE);
            meliodasClone.startBounceInOutAnimation();
          }
        });
      }
    }
  }

  // endregion
}
