# ShaderView 2017/3/1 17:30
Canvas's method [drawRoundRect()] can’t draw view with specfic corner,here is the way~

  /**
     * Draw the specified round-rect using the specified paint. The roundrect
     * will be filled or framed based on the Style in the paint.
     *
     * @param rect  The rectangular bounds of the roundRect to be drawn
     * @param rx    The x-radius of the oval used to round the corners
     * @param ry    The y-radius of the oval used to round the corners
     * @param paint The paint used to draw the roundRect
     */
    public void drawRoundRect(@NonNull RectF rect, float rx, float ry, @NonNull Paint paint) {
        drawRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, paint);
    }
    
    rx and ry  cant use as shape corner,so let's us ShaderView
    <shape xmlns:android="http://schemas.android.com/apk/res/android">
      <solid android:color="#FF00BCD4"/>
      <corners
        android:bottomLeftRadius="8dp"
        android:bottomRightRadius="8dp"
        android:topLeftRadius="8dp"
        android:topRightRadius="8dp"/>
     </shape>
     
