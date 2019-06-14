package com.csti.cetx.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.csti.cetx.R;

public class SlideableCardView extends FrameLayout {
    private final static int DEFAULT_COLOR_ID=android.R.color.black;
    private final static int DEFAULT_CARD_RADIUS=0;
    private final static int DEFAULT_CARD_SHADOW_WIDTH=0;
    private final static int DEFAULT_CARD_SRC_ID=-1;
    private final static int DEFAULT_CARD_SRC_ALPHA=110;
    private final static int DEFAULT_CARD_TEXT_COLOR_ID=android.R.color.white;
    private final static float DEFAULT_CARD_TEXT_SIZE=10;
    private final static int DEFAULT_CARD_FINAL_TRANSLATION_X=0;
    private final static int DEFAULT_CARD_FINAL_TRANSLATION_Y=0;
    private final static int DEFAULT_CARD_FINAL_ROTATION=0;
    private final static int DEFAULT_CARD_TRIGGER_TRANSLATION_X=250;

    private final static int ANIMATION_DURATION=250;

    private int mCardColor;
    private int mCardRadius;
    private int mCardShadowWidth;
    private int mCardSrcAlpha;
    private Bitmap mCardSrc;
    private int mCardTextColor;
    private int mCardFinalTranslationX;
    private int mCardFinalTranslationY;
    private int mCardFinalRotation;
    private int mCardTriggerTranslationX;

    private ImageView mCardBackground;
    private TextView mText;
    private float mLastX;
    private float mTranslationX;
    private boolean mDraggable =true;
    private OnDragListener mOnDragListener;

    public enum Direction{
        LEFT,RIGHT
    }

    public SlideableCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_slideable_card_view,this);

        mCardBackground=findViewById(R.id.card_background);
        mText=findViewById(R.id.text);

        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.SlideableCardView);
        int colorId=ta.getResourceId(R.styleable.SlideableCardView_card_color,DEFAULT_COLOR_ID);
        String text=ta.getString(R.styleable.SlideableCardView_card_text);
        mCardRadius =ta.getDimensionPixelSize(R.styleable.SlideableCardView_card_corner_radius,DEFAULT_CARD_RADIUS);
        mCardShadowWidth =ta.getDimensionPixelSize(R.styleable.SlideableCardView_card_shadow_width,DEFAULT_CARD_SHADOW_WIDTH);
        int srcId=ta.getResourceId(R.styleable.SlideableCardView_card_src,DEFAULT_CARD_SRC_ID);
        mCardSrcAlpha =ta.getInt(R.styleable.SlideableCardView_card_src_alpha,DEFAULT_CARD_SRC_ALPHA);
        int textColorId=ta.getResourceId(R.styleable.SlideableCardView_card_text_color,DEFAULT_CARD_TEXT_COLOR_ID);
        float textSize=ta.getDimension(R.styleable.SlideableCardView_card_text_size,DEFAULT_CARD_TEXT_SIZE);
        mCardFinalTranslationX =ta.getInt(R.styleable.SlideableCardView_card_final_translation_x,DEFAULT_CARD_FINAL_TRANSLATION_X);
        mCardFinalTranslationY =ta.getInt(R.styleable.SlideableCardView_card_final_translation_y,DEFAULT_CARD_FINAL_TRANSLATION_Y);
        mCardFinalRotation =ta.getInt(R.styleable.SlideableCardView_card_final_rotation,DEFAULT_CARD_FINAL_ROTATION);
        mCardTriggerTranslationX=ta.getInt(R.styleable.SlideableCardView_card_trigger_translation_x,DEFAULT_CARD_TRIGGER_TRANSLATION_X);
        ta.recycle();

        mCardColor =getResources().getColor(colorId);
        mCardTextColor =getResources().getColor(textColorId);
        mText.setText(text);
        mText.setTextColor(mCardTextColor);
        mText.setTextSize(textSize);
        mCardSrc =srcId!=DEFAULT_CARD_SRC_ID?BitmapFactory.decodeResource(getResources(), srcId):null;

        Drawable mDrawable=new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
//                setLayerType(LAYER_TYPE_SOFTWARE, null);

                Paint p=new Paint();
                p.setColor(mCardColor);
//                p.setMaskFilter(new BlurMaskFilter(mCardShadowWidth, BlurMaskFilter.Blur.SOLID));
                canvas.drawRoundRect(mCardShadowWidth, mCardShadowWidth,canvas.getWidth()- mCardShadowWidth
                        ,canvas.getHeight()- mCardShadowWidth, mCardRadius, mCardRadius,p);

                if(mCardSrc !=null) {
                    Paint p3 = new Paint();
                    p3.setAlpha(mCardSrcAlpha);
                    Rect rect = new Rect(mCardShadowWidth, mCardShadowWidth, canvas.getWidth() - mCardShadowWidth,
                            canvas.getHeight() - mCardShadowWidth);
                    canvas.drawBitmap(mCardSrc, null, rect, p3);
                }
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
        mCardBackground.setImageDrawable(mDrawable);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX=event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mDraggable){
                    break;
                }

                float x=event.getRawX();
                mTranslationX+=x-mLastX;

                setTranslationX(mTranslationX);
                float ratioYX=(float) mCardFinalTranslationY / mCardFinalTranslationX;
                setTranslationY(Math.abs(mTranslationX)*ratioYX);
                float ratioRX=(float)mCardFinalRotation/mCardFinalTranslationX;
                setRotation(mTranslationX*ratioRX);

                mLastX=x;
                break;
            case MotionEvent.ACTION_UP:
                mDraggable =false;
                ObjectAnimator oa,oa2,oa3;
                AnimatorSet s=new AnimatorSet();
                s.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mDraggable =true;
                        mTranslationX=0;
                    }
                });
                if(mTranslationX<-mCardTriggerTranslationX){
                    if(mOnDragListener !=null){
                        mOnDragListener.onDragOut(Direction.LEFT);
                    }
                    oa=ObjectAnimator.ofFloat(this, TRANSLATION_X, -mCardFinalTranslationX);
                    oa2=ObjectAnimator.ofFloat(this, TRANSLATION_Y, mCardFinalTranslationY);
                    oa3=ObjectAnimator.ofFloat(this, ROTATION, -mCardFinalRotation);

                }else if(mTranslationX>mCardTriggerTranslationX){
                    if(mOnDragListener !=null){
                        mOnDragListener.onDragOut(Direction.RIGHT);
                    }
                    oa=ObjectAnimator.ofFloat(this, TRANSLATION_X, mCardFinalTranslationX);
                    oa2=ObjectAnimator.ofFloat(this, TRANSLATION_Y, mCardFinalTranslationY);
                    oa3=ObjectAnimator.ofFloat(this, ROTATION, mCardFinalRotation);
                }
                else{
                    oa=ObjectAnimator.ofFloat(this, TRANSLATION_X, 0);
                    oa2=ObjectAnimator.ofFloat(this, TRANSLATION_Y, 0);
                    oa3=ObjectAnimator.ofFloat(this, ROTATION, 0);
                    s.setInterpolator(new OvershootInterpolator());
                }
                s.setDuration(ANIMATION_DURATION);
                s.playTogether(oa,oa2,oa3);
                s.start();
                break;
        }
        return true;
    }

    public interface OnDragListener {
        void onDragOut(Direction dir);
    }

    public void setText(String text){
        mText.setText(text);
    }

    public int getCardColor() {
        return mCardColor;
    }

    public void setCardColor(int cardColor) {
        mCardColor = cardColor;
        mCardBackground.invalidate();
    }

    public int getCardRadius() {
        return mCardRadius;
    }

    public void setCardRadius(int cardRadius) {
        mCardRadius = cardRadius;
        mCardBackground.invalidate();
    }

    public int getCardShadowWidth() {
        return mCardShadowWidth;
    }

    public void setCardShadowWidth(int cardShadowWidth) {
        mCardShadowWidth = cardShadowWidth;
        mCardBackground.invalidate();
    }

    public int getCardSrcAlpha() {
        return mCardSrcAlpha;
    }

    public void setCardSrcAlpha(int cardSrcAlpha) {
        mCardSrcAlpha = cardSrcAlpha;
        mCardBackground.invalidate();
    }

    public String getCardText(){
        return mText.getText().toString();
    }

    public void setCardText(String text){
        mText.setText(text);
    }

    public void setCardSrc(Bitmap bitmap){
        mCardSrc =bitmap;
        mCardBackground.invalidate();
    }

    public int getCardTextColor() {
        return mCardTextColor;
    }

    public void setCardTextColor(int cardTextColor) {
        mCardTextColor=cardTextColor;
        mText.invalidate();
    }

    public void setOnDragListener(OnDragListener onDragListener) {
        mOnDragListener = onDragListener;
    }

    public float getCardTextSize(){
        return mText.getTextSize();
    }

    public void setCardTextSize(float size){
        mText.setTextSize(size);
    }

    public int getCardFinalTranslationX() {
        return mCardFinalTranslationX;
    }

    public void setCardFinalTranslationX(int cardFinalTranslationX) {
        mCardFinalTranslationX = cardFinalTranslationX;
    }

    public int getCardFinalTranslationY() {
        return mCardFinalTranslationY;
    }

    public void setCardFinalTranslationY(int cardFinalTranslationY) {
        mCardFinalTranslationY = cardFinalTranslationY;
    }

    public int getCardFinalRotation() {
        return mCardFinalRotation;
    }

    public void setCardFinalRotation(int cardFinalRotation) {
        mCardFinalRotation = cardFinalRotation;
    }

    public int getCardTriggerTranslationX() {
        return mCardTriggerTranslationX;
    }

    public void setCardTriggerTranslationX(int cardTriggerTranslationX) {
        mCardTriggerTranslationX = cardTriggerTranslationX;
    }
}
