// From: https://github.com/plokhotnyuk/jsoniter-scala/commit/e089f06c2d8b4bdb87a6874e17bf716e8608b117#diff-3602e7991d01bab723a637f0f1b1d029
package org.sireum.$internal;

public class UnsafeUtils {
  static final sun.misc.Unsafe UNSAFE;
  static {
    try {
      java.lang.reflect.Field field = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      UNSAFE = (sun.misc.Unsafe) field.get(null);
      field.setAccessible(false);
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }
  public static void releaseFence() {
    UNSAFE.storeFence();
  }
}
