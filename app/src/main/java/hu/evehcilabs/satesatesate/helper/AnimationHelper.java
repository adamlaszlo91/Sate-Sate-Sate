package hu.evehcilabs.satesatesate.helper;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.*;
import java.util.Random;

import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_0;
import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_180;
import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_90;

public class AnimationHelper {

  private View view;
  private Animation wiggleAnimationStart;
  private Animation wiggleAnimationMiddle;
  private Animation wiggleAnimationEnd;
  private Animation bounceInOutAnimation;
  private Animation spinningAnimation;

  public AnimationHelper(View view) {
    this.view = view;
  }

  public void stopAndRelease() {
    stopAnimations();
    view = null;
  }

  public void stopAnimations() {
    stopWiggleAnimation();
    stopBounceInOutAnimation();
    stopSpinningAnimation();
    view.clearAnimation();
  }

  // region Wiggle animation

  public void startWiggleAnimation() {
    initWiggleAnimations();
    initWiggleAnimationListeners();
    view.startAnimation(wiggleAnimationStart);
  }

  private void initWiggleAnimations() {
    wiggleAnimationStart =
      new RotateAnimation(0.f, -20.0f, view.getWidth()/2.0f, view.getHeight()*0.75f);
    wiggleAnimationStart.setDuration(175);
    wiggleAnimationStart.setRepeatCount(0);

    wiggleAnimationMiddle =
      new RotateAnimation(-20.f, 20.0f, view.getWidth()/2.0f, view.getHeight()*0.75f);
    wiggleAnimationMiddle.setDuration(350);
    wiggleAnimationMiddle.setRepeatMode(Animation.REVERSE);
    wiggleAnimationMiddle.setRepeatCount(2);

    wiggleAnimationEnd =
      new RotateAnimation(20.f, 0f, view.getWidth()/2.0f, view.getHeight()*0.75f);
    wiggleAnimationEnd.setDuration(175);
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

  // endregion

  // region Bounce in animation

  public void startBounceInOutAnimation() {
    initBounceInOutAnimation();
    initBounceInOutAnimationListener();
    view.startAnimation(bounceInOutAnimation);
  }

  private void initBounceInOutAnimation() {
    float fromXDelta;
    float toXDelta;
    float fromYDelta;
    float toYDelta;
    switch ((int)view.getRotation()) {
      case ROTATION_DEGREE_0:
        toXDelta = 0;
        fromXDelta = 0;
        toYDelta = view.getHeight()*0.25F;
        fromYDelta = view.getHeight();
        break;
      case ROTATION_DEGREE_90:
        toYDelta = 0;
        fromYDelta = 0;
        toXDelta = -view.getHeight()*0.25F;
        fromXDelta = -view.getHeight();
        break;
      case ROTATION_DEGREE_180:
        toXDelta = 0;
        fromXDelta = 0;
        toYDelta = -view.getHeight()*0.25F;
        fromYDelta = -view.getHeight();
        break;
      default:
        toYDelta = 0;
        fromYDelta = 0;
        toXDelta = view.getHeight()*0.25F;
        fromXDelta = view.getHeight();
    }
    TranslateAnimation animation =
      new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
    animation.setRepeatCount(1);
    animation.setRepeatMode(Animation.REVERSE);
    animation.setDuration(800);
    animation.setInterpolator(new OvershootInterpolator());
    animation.setStartOffset(new Random().nextInt(1000));
    bounceInOutAnimation = animation;
  }

  private void initBounceInOutAnimationListener() {
    bounceInOutAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {

      }

      @Override public void onAnimationEnd(Animation animation) {
        ((ViewGroup)view.getParent()).removeView(view);
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
  }

  private void stopBounceInOutAnimation() {
    if (bounceInOutAnimation != null) {
      bounceInOutAnimation.setAnimationListener(null);
      bounceInOutAnimation.cancel();
      bounceInOutAnimation = null;
    }
  }

  // endregion

  // region Spinning

  public void startSpinningAnimation() {
    initSpinningAnimation();
    initSpinningAnimationListener();
    view.startAnimation(spinningAnimation);
  }

  private void initSpinningAnimation() {
    RotateAnimation animation =
      new RotateAnimation(0.f, 360.0f, view.getWidth()/2.0f, view.getHeight()/2.0f);
    animation.setRepeatCount(17);
    animation.setRepeatMode(Animation.RESTART);
    animation.setDuration(1700);
    animation.setInterpolator(new LinearInterpolator());
    spinningAnimation = animation;
  }

  private void initSpinningAnimationListener() {
    spinningAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationStart(Animation animation) {

      }

      @Override public void onAnimationEnd(Animation animation) {
        stopAnimations();
      }

      @Override public void onAnimationRepeat(Animation animation) {

      }
    });
  }

  private void stopSpinningAnimation() {
    if (spinningAnimation != null) {
      spinningAnimation.setAnimationListener(null);
      spinningAnimation.cancel();
      spinningAnimation = null;
    }
  }

  // endregion
}
