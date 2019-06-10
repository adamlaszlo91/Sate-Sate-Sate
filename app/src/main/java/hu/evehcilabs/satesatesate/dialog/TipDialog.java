package hu.evehcilabs.satesatesate.dialog;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import hu.evehcilabs.androidbase.BaseDialog;

public class TipDialog extends BaseDialog {
  public TipDialog(@NonNull Context context) {
    super(context);
  }

  public TipDialog(@NonNull Context context, int themeResId) {
    super(context, themeResId);
  }

  protected TipDialog(
    @NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener)
  {
    super(context, cancelable, cancelListener);
  }
}
