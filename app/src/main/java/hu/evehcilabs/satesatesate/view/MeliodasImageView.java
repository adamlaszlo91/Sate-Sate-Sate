package hu.evehcilabs.satesatesate.view;

import android.content.Context;
import android.util.AttributeSet;
import hu.evehcilabs.satesatesate.helper.AnimationHelper;

public class MeliodasImageView extends androidx.appcompat.widget.AppCompatImageView {

  private AnimationHelper animationHelper;

  public MeliodasImageView(Context context) {
    super(context);
  }

  public MeliodasImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MeliodasImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public AnimationHelper getAnimationHelper() {
    return animationHelper;
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    animationHelper = new AnimationHelper(this);
  }

  @Override protected void onDetachedFromWindow() {
    animationHelper.destroy();
    super.onDetachedFromWindow();
  }

  // region Animations

  private void startSlideInAnimation() {
    // TODO: Implement
  }

  private void startSlideOutAnimation() {
    // TODO: Implement
  }

  private void stopAllAnimation() {
    // TODO: Implement
  }

  // endregion
}
