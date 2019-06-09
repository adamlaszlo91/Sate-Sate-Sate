package hu.evehcilabs.satesatesate.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import hu.evehcilabs.satesatesate.R;
import hu.evehcilabs.satesatesate.helper.AnimationHelper;
import java.util.Random;

import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_0;
import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_180;
import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_270;
import static hu.evehcilabs.satesatesate.Constant.ROTATION_DEGREE_90;

public class MeliodasImageView extends androidx.appcompat.widget.AppCompatImageView {

  enum PositionInParent {
    TOP, BOTTOM, LEFT, RIGHT, CENTER;
  }

  private AnimationHelper animationHelper;
  private PositionInParent positionInParent = PositionInParent.CENTER;

  public MeliodasImageView(Context context) {
    super(context);
    customInit();
  }

  public MeliodasImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    customInit();
  }

  public MeliodasImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    customInit();
  }

  private void customInit() {
    setImageResource(R.drawable.placeholder_common);
    setScaleType(ScaleType.CENTER_INSIDE);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    animationHelper = new AnimationHelper(this);
  }

  @Override protected void onDetachedFromWindow() {
    animationHelper.destroy();
    super.onDetachedFromWindow();
  }

  public void reStartWiggleAnimation() {
    animationHelper.stopWiggleAnimation();
    animationHelper.startWiggleAnimation();
  }

  public void reStartBounceInOutAnimation() {
    animationHelper.stopBounceInOutAnimation();
    animationHelper.startBounceInOutAnimation();
  }

  public void stopAnimations(){
    animationHelper.stopWiggleAnimation();
    animationHelper.stopBounceInOutAnimation();
  }

  // region Layout parameter generation

  public void setupSizeAndRandomPosition() {
    setupLayoutParams();
    setupConstraintsForSizeAndCenterPosition();
    setupConstraintsForRandomPositionAndPerpendicularBias();
    setRotationForPlacement();
  }

  public void setupSizeAndCenterPosition() {
    setupLayoutParams();
    setupConstraintsForSizeAndCenterPosition();
  }

  private void setupLayoutParams() {
    ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
    setLayoutParams(layoutParams);
  }

  private void setupConstraintsForSizeAndCenterPosition() {
    ConstraintLayout parent = (ConstraintLayout)getParent();
    ConstraintSet constraintSet = new ConstraintSet();
    constraintSet.clone(parent);
    constraintSet.constrainPercentWidth(getId(), 0.5f);
    constraintSet.setDimensionRatio(getId(), "1:1");
    constraintSet.connect(getId(), ConstraintSet.RIGHT, parent.getId(), ConstraintSet.RIGHT, 0);
    constraintSet.connect(getId(), ConstraintSet.LEFT, parent.getId(), ConstraintSet.LEFT, 0);
    constraintSet.connect(getId(), ConstraintSet.BOTTOM, parent.getId(), ConstraintSet.BOTTOM, 0);
    constraintSet.connect(getId(), ConstraintSet.TOP, parent.getId(), ConstraintSet.TOP, 0);
    constraintSet.applyTo(parent);
  }

  private void setupConstraintsForRandomPositionAndPerpendicularBias() {
    ConstraintLayout parent = (ConstraintLayout)getParent();
    ConstraintSet constraintSet = new ConstraintSet();
    constraintSet.clone(parent);
    PositionInParent randomPositionExcludingCenter =
      PositionInParent.values()[new Random().nextInt(PositionInParent.values().length-1)];
    switch (randomPositionExcludingCenter) {
      case TOP:
        constraintSet.setVerticalBias(getId(), 0.0f);
        constraintSet.setHorizontalBias(getId(), new Random().nextFloat());
        break;
      case LEFT:
        constraintSet.setHorizontalBias(getId(), 0.0f);
        constraintSet.setVerticalBias(getId(), new Random().nextFloat());
        break;
      case BOTTOM:
        constraintSet.setVerticalBias(getId(), 1.0f);
        constraintSet.setHorizontalBias(getId(), new Random().nextFloat());
        break;
      case RIGHT:
        constraintSet.setHorizontalBias(getId(), 1.0f);
        constraintSet.setVerticalBias(getId(), new Random().nextFloat());
      default:
    }
    positionInParent = randomPositionExcludingCenter;
    constraintSet.applyTo(parent);
  }

  private void setRotationForPlacement() {
    switch (positionInParent) {
      case TOP:
        setRotation(ROTATION_DEGREE_180);
        break;
      case LEFT:
        setRotation(ROTATION_DEGREE_90);
        break;
      case BOTTOM:
        setRotation(ROTATION_DEGREE_0);
        break;
      case RIGHT:
        setRotation(ROTATION_DEGREE_270);
      default:
    }
  }

  // endregion
}
