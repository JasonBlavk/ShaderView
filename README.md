# ShaderView 2017/3/1 17:30
Canvas's method [drawRoundRect()] can’t draw view with specfic corner,here is the way~
* @param rx    The x-radius of the oval used to round the corners
* @param ry    The y-radius of the oval used to round the corners

public void drawRoundRect(@NonNull RectF rect, float rx, float ry, @NonNull Paint paint) {

    drawRoundRect(rect.left, rect.top, rect.right, rect.bottom, rx, ry, paint);
    
}
 
 rx and ry not so flexible to every corner like shape corner(like this: android:bottomLeftRadius="8dp" android:bottomRightRadius="8dp" android:topLeftRadius="8dp" android:topRightRadius="8dp"),so ShaderView come here!
     
