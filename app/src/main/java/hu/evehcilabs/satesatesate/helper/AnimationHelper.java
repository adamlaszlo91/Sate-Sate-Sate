package hu.evehcilabs.satesatesate.helper;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_0;
import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_180;
import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_90;

public class AnimationHelper {

  private View view;
  private Animation wiggleAnimationStart;
  private Animation wiggleAnimationMiddle;
  private Animation wiggleAnimationEnd;
  private Animation bounceInOutAnimation;

  public AnimationHelper(View view) {
    this.view = view;
  }

  public void destroy() {
    stopWiggleAnimation();
    stopBounceInOutAnimation();
    view = null;
  }

  // region Wiggle animation

  public void startWiggleAnimation() {
    initWiggleAnimations();
    initWiggleAnimationListeners();
    view.startAnimation(wiggleAnimationStart);
  }

  public void stopWiggleAnimation() {
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

  private void initWiggleAnimations() {
    wiggleAnimationStart =
      new RotateAnimation(0.f, -30.0f, view.getWidth()/2.0f, view.getHeight()/2.0f);
    wiggleAnimationStart.setDuration(175);
    wiggleAnimationStart.setRepeatCount(0);

    wiggleAnimationMiddle =
      new RotateAnimation(-30.f, 30.0f, view.getWidth()/2.0f, view.getHeight()/2.0f);
    wiggleAnimationMiddle.setDuration(350);
    wiggleAnimationMiddle.setRepeatMode(Animation.REVERSE);
    wiggleAnimationMiddle.setRepeatCount(2);

    wiggleAnimationEnd = new RotateAnimation(30.f, 0f, view.getWidth()/2.0f, view.getHeight()/2.0f);
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

  // endregion

  // region Bounce in animation

  public void startBounceInOutAnimation() {
    initBounceInOutAnimation();
    initBounceInOutAnimationListener();
    view.startAnimation(bounceInOutAnimation);
  }

  public void stopBounceInOutAnimation() {
    if (bounceInOutAnimation != null) {
      bounceInOutAnimation.setAnimationListener(null);
      bounceInOutAnimation.cancel();
      bounceInOutAnimation = null;
    }
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
        toYDelta = 0;
        fromYDelta = view.getHeight();
        break;
      case ROTATION_DEGREE_90:
        toYDelta = 0;
        fromYDelta = 0;
        toXDelta = 0;
        fromXDelta = -view.getHeight();
        break;
      case ROTATION_DEGREE_180:
        toXDelta = 0;
        fromXDelta = 0;
        toYDelta = 0;
        fromYDelta = -view.getHeight();
        break;
      default:
        toYDelta = 0;
        fromYDelta = 0;
        toXDelta = 0;
        fromXDelta = view.getHeight();
    }
    TranslateAnimation animation =
      new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
    animation.setRepeatCount(1);
    animation.setRepeatMode(Animation.REVERSE);
    animation.setDuration(400);
    animation.setInterpolator(new OvershootInterpolator());
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

  // endregion
}
