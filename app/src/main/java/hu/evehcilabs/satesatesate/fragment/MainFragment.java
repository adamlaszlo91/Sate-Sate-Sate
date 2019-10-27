package hu.evehcilabs.satesatesate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import hu.evehcilabs.androidbase.BaseFragment;
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.activity.MainActivity;
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
    pauseAllSounds();
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
        setActionAndStatusBarColors(R.color.colorPrimary);
        stopAllAnimations();
        meliodasImageView.setImageResource(R.drawable.chibi_meliodas_by_katelinelaine_dccjqua);
        pauseAllSounds();
        float randomActionChange = new Random().nextFloat();
        if (randomActionChange < 0.01) {
          setActionAndStatusBarColors(R.color.hawk_skin);
          showToast(getString(R.string.toast_transpork));
          meliodasImageView.setImageResource(R.drawable.hawk_transpork);
          mediaPlayerHelper.startTranspork();
        } else if (randomActionChange < 0.05) {
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

  private void pauseAllSounds() {
    mediaPlayerHelper.pauseSateSateSate();
    mediaPlayerHelper.pauseSateSateSateMulti();
    mediaPlayerHelper.pauseTanchou();
    mediaPlayerHelper.pauseSateSateSateRemix();
    mediaPlayerHelper.pauseTranspork();
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

  private void setActionAndStatusBarColors(@ColorRes int color) {
    if (getActivity() instanceof MainActivity) {
      ((MainActivity)getActivity()).setActionAndStatusBarColors(color);
    }
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
