package hu.evehcilabs.satesatesate.helper;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class AnimationHelper {

  private View view;
  private Animation wiggleAnimationStart;
  private Animation wiggleAnimationMiddle;
  private Animation wiggleAnimationEnd;

  public AnimationHelper(View view) {
    this.view = view;
  }

  public void destroy() {
    stopWiggleAnimation();
    view = null;
  }

  // region Wiggle animation

  public void reStartWiggleAnimation() {
    stopWiggleAnimation();
    initWiggleAnimations();
    initWiggleAnimationListeners();
    view.startAnimation(wiggleAnimationStart);
  }

  private void initWiggleAnimations() {
    wiggleAnimationStart =
      new RotateAnimation(0.f, -30.0f, view.getWidth()/2.0f, view.getHeight()/2.0f);
    wiggleAnimationStart.setDuration(250);
    wiggleAnimationStart.setRepeatCount(0);

    wiggleAnimationMiddle =
      new RotateAnimation(-30.f, 30.0f, view.getWidth()/2.0f, view.getHeight()/2.0f);
    wiggleAnimationMiddle.setDuration(500);
    wiggleAnimationMiddle.setRepeatMode(Animation.REVERSE);
    wiggleAnimationMiddle.setRepeatCount(4);

    wiggleAnimationEnd = new RotateAnimation(30.f, 0f, view.getWidth()/2.0f, view.getHeight()/2.0f);
    wiggleAnimationEnd.setDuration(250);
    wiggleAnimationEnd.setRepeatCount(0);
  }

  private void initWiggleAnimationListeners() {
    wiggleAnimationStart.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {

      }

      @Override public void onAnimationEnd(Animation animation) {
        view.startAnimation(wiggleAnimationMiddle);
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
    wiggleAnimationMiddle.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {

      }

      @Override public void onAnimationEnd(Animation animation) {
        view.startAnimation(wiggleAnimationEnd);
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
  }

  private void stopWiggleAnimation() {
    if (wiggleAnimationStart != null) {
      wiggleAnimationStart.setAnimationListener(null);
      wiggleAnimationStart.cancel();
      wiggleAnimationStart = null;
    }
    if (wiggleAnimationMiddle != null) {
      wiggleAnimationMiddle.setAnimationListener(null);
      wiggleAnimationMiddle.cancel();
      wiggleAnimationMiddle = null;
    }
    if (wiggleAnimationEnd != null) {
      wiggleAnimationEnd.setAnimationListener(null);
      wiggleAnimationEnd.cancel();
      wiggleAnimationEnd = null;
    }
  }

  // endregion;
}
