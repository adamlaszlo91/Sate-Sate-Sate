package hu.evehcilabs.satesatesate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import hu.evehcilabs.androidbase.BaseFragment;
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.databinding.FragmentMainBinding;
import hu.evehcilabs.satesatesate.helper.MediaPlayerHelper;
import hu.evehcilabs.satesatesate.helper.TapCounterHelper;
import hu.evehcilabs.satesatesate.view.MeliodasImageView;
import java.util.ArrayList;
import java.util.Random;

public class MainFragment extends BaseFragment {
  private static final int LOSTVAYNE_CLONE_MULTIPLIER = 4;

  private TapCounterHelper tapCounterHelper;
  private MeliodasImageView meliodasImageView;
  private ArrayList<MeliodasImageView> meliodasClones = new ArrayList<>();
  private MediaPlayerHelper mediaPlayerHelper;
  private Toast currentToast;

  private FragmentMainBinding binding;

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mediaPlayerHelper = new MediaPlayerHelper(getActivity());
    tapCounterHelper = new TapCounterHelper(context);
  }

  @Override public void onDetach() {
    mediaPlayerHelper.destroy();
    super.onDetach();
  }

  @Override public void onStop() {
    stopAllAnimations();
    mediaPlayerHelper.stop();
    super.onStop();
  }

  @Nullable @Override public View onCreateView(
    @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState)
  {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
    binding.setTapCounterHelper(tapCounterHelper);
    tapCounterHelper.updateTapCounterText();
    initMeliodas();
    initActions();
    return binding.getRoot();
  }

  private void initMeliodas() {
    meliodasImageView = new MeliodasImageView(getActivity());
    meliodasImageView.setId(ViewCompat.generateViewId());
    binding.containerMeliodasFigure.addView(meliodasImageView);
    meliodasImageView.setupSizeAndCenterPosition();
  }

  private void initActions() {
    meliodasImageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        tapCounterHelper.onNewTap();
        stopAllAnimations();
        meliodasImageView.setImageResource(R.drawable.chibi_meliodas_by_katelinelaine_dccjqua);
        mediaPlayerHelper.stop();
        float randomActionChange = new Random().nextFloat();
        // TODO: Remove mock value
        randomActionChange = 0.001f;
        if (randomActionChange < 0.01) {
          showToast(getString(R.string.toast_transpork));
          meliodasImageView.setImageResource(R.drawable.hawk_transpork);
          mediaPlayerHelper.play(MediaPlayerHelper.SoundIdentifier.TRANSPORK);
        } else if (randomActionChange < 0.05) {
          showToast(getString(R.string.toast_tanchou));
          mediaPlayerHelper.play(MediaPlayerHelper.SoundIdentifier.TANCHOU);
        } else if (randomActionChange < 0.15) {
          showToast(getString(R.string.toast_sate_sate_sate_multi));
          meliodasImageView.startWiggleAnimation();
          showMeliodasClones();
          mediaPlayerHelper.play(MediaPlayerHelper.SoundIdentifier.SATE_SATE_SATE_MULTI);
        } else if (randomActionChange < 0.25) {
          showToast(getString(R.string.toast_sate_mix));
          meliodasImageView.startSpinningAnimation();
          mediaPlayerHelper.play(MediaPlayerHelper.SoundIdentifier.SATE_SATE_SATE_REMIX);
        } else {
          showToast(getString(R.string.toast_sate_sate_sate));
          meliodasImageView.startWiggleAnimation();
          mediaPlayerHelper.play(MediaPlayerHelper.SoundIdentifier.SATE_SATE_SATE);
        }
      }
    });
  }

  private void stopAllAnimations() {
    meliodasImageView.stopAnimations();
    for (MeliodasImageView meliodasClone : meliodasClones) {
      if (ViewCompat.isAttachedToWindow(meliodasClone)) {
        meliodasClone.stopAnimations();
        binding.containerMeliodasFigure.removeView(meliodasClone);
      }
    }
    meliodasClones.clear();
  }

  private void showToast(String message) {
    if (currentToast != null) {
      currentToast.cancel();
    }
    currentToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
    currentToast.show();
  }

  // region Meliodas clones

  private void showMeliodasClones() {
    initMeliodasClones();
    startMeliodasClonesAnimation();
  }

  private void initMeliodasClones() {
    for (int i = 0; i < LOSTVAYNE_CLONE_MULTIPLIER; i++) {
      MeliodasImageView meliodasClone = new MeliodasImageView(getActivity());
      meliodasClone.setId(ViewCompat.generateViewId());
      binding.containerMeliodasFigure.addView(meliodasClone);
      meliodasClone.setupSizeAndRandomPosition();
      meliodasClones.add(meliodasClone);
    }
  }

  private void startMeliodasClonesAnimation() {
    for (final MeliodasImageView meliodasClone : meliodasClones) {
      meliodasClone.setVisibility(View.INVISIBLE);
      meliodasClone.post(new Runnable() {
        @Override public void run() {
          if (ViewCompat.isAttachedToWindow(meliodasClone)) {
            meliodasClone.setVisibility(View.VISIBLE);
            meliodasClone.startBounceInOutAnimation();
          }
        }
      });
    }
  }

  // endregion
}
