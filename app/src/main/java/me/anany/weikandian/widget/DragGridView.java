package me.anany.weikandian.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import me.anany.weikandian.R;
import me.anany.weikandian.adapter.DragGridViewAdapter;
import me.anany.weikandian.utils.UiUtil;

/**
 * Created by anany on 16/1/22.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class DragGridView extends GridView {

    private static String tag = "MyDragGird";

    /** 手指相对于GridView的横坐标位置 */
    private int gridViewX;

    /** 手指相对于GridView的纵坐标位置 */
    private int gridViewY;

    /** 手指相对于被拖拽的item的横坐标*/
    private int itemViewX;

    /**手指相对于被拖拽的item的纵坐标*/
    private int itemViewY;

    /** from: 开始拖动的item的position*/
    private int startPosition;

    /**to:手指结束拖动时候的位置 */
    private int dropPosition;

    /** item的高 */
    private int itemHeight;

    /** item的宽 */
    private int itemWidth;

    /** 拖动时对应的item生成的ImageView */
    private ImageView dragImageView = null;

    /** windowManager管理器 */
    private WindowManager windowManager = null;

    private WindowManager.LayoutParams windowParams = null;

    /** 一行的item数量 */
    private int nColumns = 4;

    /** 其它是否在移动 */
    private boolean isMoving = false;

    /** 记录移动的item的索引位置 */
    private int holdPosition;

    /** 拖动时候放大的倍数 */
    private double dragScale = 1.0D;

    /** 振动器，长按时候触发 */
    private Vibrator mVibrator = null;

    /** 每个item之间的水平距离 */
    private int mHorizontalSpacing = 15;

    /** 每个item之间的竖直距离 */
    private int mVerticalSpacing = 15;

    /** 移动时候最后那个动画的ID */
    private String LastAnimationID;

    public DragGridView(Context context) {
        super(context);
        init(context);
    }

    public DragGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public DragGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {

        // 将布局文件中设置的间距dip转为px
        mHorizontalSpacing = UiUtil.dip2px(context, mHorizontalSpacing);
    }

    // down事件先执行这个方法
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        // 单点触摸屏幕按下事件，记录此时x和y的数据
        Log.e(tag, "--myDragGird onInterceptTouchEvent--");

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 注意此时接受该方法的是GirdView。所以getX()获取的是相对于GirdView左上角的坐标
            gridViewX = (int) ev.getX();
            gridViewY = (int) ev.getY();

            // 监听长按事件
            setOnItemClickListener(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    /*
     * 长按点击监听
     */
    private void setOnItemClickListener(final MotionEvent ev) {

        setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                // 如果位置有效
                if (position != AdapterView.INVALID_POSITION) {

                    // 记录第一次点击的位置
                    startPosition = position;// 开始拖动item的位置

                    // 获取当前位置的ViewGroup或者item
                    ViewGroup dragViewGroup = (ViewGroup) getChildAt(startPosition
                            - getFirstVisiblePosition());
                    TextView dragTextView = (TextView) dragViewGroup
                            .findViewById(R.id.tv_name);

                    //设置拖动item的样式
                    dragTextView.setSelected(true);
                    dragTextView.setEnabled(false);

                    dragViewGroup.destroyDrawingCache();
                    dragViewGroup.setDrawingCacheEnabled(true);

                    Bitmap dragBitmap = Bitmap.createBitmap(dragViewGroup
                            .getDrawingCache());
                    startDrag(dragBitmap, (int) ev.getRawX(),
                            (int) ev.getRawY());

                    // 获取当前item的宽和高
                    itemHeight = dragViewGroup.getHeight();
                    itemWidth = dragViewGroup.getWidth();

                    // 屏幕上的x和y dragViewGroup.getLeft()是当前item相对于父控件GirdView的间距
                    itemViewX = gridViewX - dragViewGroup.getLeft();
                    // item相对自己左上角的x值，以自己的view的左上角为(0,0)


                    // 隐藏当前的item
                    dragViewGroup.setVisibility(View.INVISIBLE);

                    requestDisallowInterceptTouchEvent(true);
                    return true;
                }

                return false;
            }
        });

    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (dragImageView != null
                && startPosition != AdapterView.INVALID_POSITION) {

            int x = (int) ev.getX();
            int y = (int) ev.getY();

            switch (ev.getAction()) {
                case MotionEvent.ACTION_MOVE:// 当手势移动的时候

                    Log.e(tag, "--on moving--");

                    onDrag((int) ev.getRawX(), (int) ev.getRawY());

                    //移动其他的item
                    if(!isMoving){
                        onMove(x, y);
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    // 手指抬起的时候让drawImageView从windowManage里删除
                    stopDrag();

                    onDrop(x,y);
                    requestDisallowInterceptTouchEvent(false);
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 手指抬起时,让你拖拽的那个item显示
     * @param x
     * @param y
     */
    private void onDrop(int x, int y) {

        dropPosition = pointToPosition(x, y);

        //剩下的交给adapter处理
        DragGridViewAdapter dragAdapter = (DragGridViewAdapter)getAdapter();

        dragAdapter.setShowDropItem(true);
        dragAdapter.notifyDataSetChanged();

    }

    /** 在ScrollView内，所以要进行计算高度 */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    /**
     * 移动的时候触发,移动处理其他的item
     *
     * @param x
     *            手指相对于当前view也就是GirdView 横坐标的位置
     * @param y
     *            手指相对于当前view也就是GirdView纵坐标的位置
     */
    public void onMove(int x, int y) {

        // 判断当前的item的位置，或者说判断当前位置是那一个item
        // 或者说是当前手指的位置
        int dPosition = pointToPosition(x, y);

        // 注意，本应用第一行的前两个item是不能拖动的
        Log.e(tag, "--dPosition--" + dPosition);
        if (dPosition > 1) {

            // 如果现在手势的位置==你拖拽开始的位置
            if (dPosition == startPosition) {
                return;
            }
            // 放下的位置或者手指移动后不动的位置
            dropPosition = dPosition;

            // 需要移动的item的数量
            int movecount;

            // 拖动的==开始拖的，并且拖动的不等于放下的
            if (startPosition != dropPosition) {

                // 当前手指的位置减去开始移动时候的那个位置
                movecount = dropPosition - startPosition;
                ViewGroup dragGroup = (ViewGroup) getChildAt(startPosition);
                dragGroup.setVisibility(View.INVISIBLE);

                int adsMovCount = Math.abs(movecount);
                float to_x;// 当前下方position
                float to_y;// 当前下方右边position

                // 移动距离的百分比（相对于自己宽度的百分比)
                float x_value = ((float) mHorizontalSpacing / (float) itemWidth) + 1.0f;

                // 移动距离的百分比(相对于自己长度度的百分比)
                float y_value = ((float) mVerticalSpacing / (float) itemHeight) + 1.0f;

                // 循环遍历每一个
                for (int i = 0; i < adsMovCount; i++) {
                    // 如果手指是像右或者向下一行移动，那么其余的item就像左移动
                    if (movecount > 0) {
                        // 挨个判断要移动item与被拖拽的item是否是同一行的
                        // //相对于被拖拽的item右边要移动的第i个item的position

                        holdPosition = startPosition + i + 1;
                        if (startPosition / nColumns == holdPosition / nColumns) {// 是dragImageView同一行
                            //与dragViewImageView 同行的向水平左移动
                            to_x = -x_value;
                            to_y = 0;
                        } else if (holdPosition % 4 == 0) {//dragImageView所在行的下一行的第一列
                            to_x = 3 * x_value;//x的位置移动到dragImageView行的最后一列，所以位置要*3
                            to_y = -y_value;//向上移动一行
                        } else {
                            //dragViewImageView行下一行的水平移动
                            to_x = -x_value;
                            to_y = 0;
                        }
                    } else {// 如果手指是向左或者上一行移动，那么其余的item就向右移动
                        // 相对于被拖拽的item左边要移动的第i个item的position
                        holdPosition = startPosition - i - 1;
                        if (startPosition / nColumns == holdPosition / nColumns) {// 是dragImageView同一行
                            // 同行item的向右移动
                            to_x = x_value;
                            to_y = 0;
                        } else if ((holdPosition + 1) % 4 == 0) {//dragImageView的上一行的最后一列
                            to_x = -3 * x_value;//x的位置移动到dragImageView所在行的第一列
                            to_y = y_value;//向下移动一行
                        } else {
                            //dragViewImageView所在行上一行的水平移动
                            to_x = x_value;
                            to_y = 0;
                        }
                    }// end if-else

                    // 获取第i个要移动的item
                    ViewGroup moveViewGroup = (ViewGroup) getChildAt(holdPosition);
                    Animation animation = getMoveAnimation(to_x, to_y);
                    moveViewGroup.startAnimation(animation);

                    // 如果是最后一个移动的，那么设置他的最后动画id为lastAdimationId
                    if (holdPosition == dropPosition) {
                        LastAnimationID = animation.toString();
                    }

                    animation.setAnimationListener(new Animation.AnimationListener() {

                        @Override
                        public void onAnimationStart(Animation animation) {
                            //标志着正在运动
                            isMoving = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            // TODO Auto-generated method stub
                            // 如果为最后个动画结束，那执行下面的方法
                            if (animation.toString().equalsIgnoreCase(
                                    LastAnimationID)) {
                                DragGridViewAdapter mDragAdapter = (DragGridViewAdapter)
                                        getAdapter();
                                mDragAdapter.exchange(startPosition,dropPosition);
                                startPosition = dropPosition;

                                isMoving = false;
                            }
                        }
                    });

                }

            }

        }
    }

    /** 获取移动动画 */
    public Animation getMoveAnimation(float toXValue, float toYValue) {

        TranslateAnimation mTranslateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0F, Animation.RELATIVE_TO_SELF,
                toXValue, Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, toYValue);// 当前位置移动到指定位置

        mTranslateAnimation.setFillAfter(true);// 设置一个动画效果执行完毕后，View对象保留在终止的位置。
        mTranslateAnimation.setDuration(300L);
        return mTranslateAnimation;
    }

    /**
     * 注意，拖动的时候手指是不动的，手指相对于Item左上角的位置是始终不变的
     * 手指位置相对于屏幕左上角是变化的，我们计算的就是变化过后手指相对于屏幕左上角的x,y值 在拖动的情况下
     * 重新绘制你手指选中的那个方法
     *
     * @param rawx
     *               手指的相对于屏幕左上角的横坐标的位置
     * @param rawy
     *               手指相对于屏幕左上角的纵坐标的位置
     */
    private void onDrag(int rawx, int rawy) {

        if (dragImageView != null) {

            // 设置窗口的透明度
            windowParams.alpha = 0.6f;

            // 重新计算此时item的x和y坐标的位置
            windowParams.x = rawx - itemViewX;
            windowParams.y = rawy - itemViewY;

            // 更新view的布局，也就是重新绘制它的位置
            windowManager.updateViewLayout(dragImageView, windowParams);
        }
    }

    /**
     * 本方法的就是在长按某一个item的时候，生成该item的一个ImageView对象，保存在dragImageView中
     * 然后用这个dragImageView在action_move中随着手势的运行不停的绘制该imageView的位置
     *
     * @param dragBitmap
     * @param rawX
     * @param rawY
     */
    protected void startDrag(Bitmap dragBitmap, int rawX, int rawY) {

        windowParams = new WindowManager.LayoutParams();
        windowParams.gravity = Gravity.TOP | Gravity.LEFT;

        // 计算item左上角的坐标值
        windowParams.x = rawX - itemViewX;
        windowParams.y = rawY - itemViewY;

        // 放大dragScale倍，可以设置拖动后的倍数
        windowParams.width = (int) (dragScale * dragBitmap.getWidth());
        windowParams.height = (int) (dragScale * dragBitmap.getHeight());

        windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        windowParams.format = PixelFormat.TRANSLUCENT;
        windowParams.windowAnimations = 0;

        //item生成
        ImageView iv = new ImageView(getContext());
        iv.setImageBitmap(dragBitmap);

        windowManager = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);// "window"
        windowManager.addView(iv, windowParams);

        //保存生成的imageView
        dragImageView = iv;
    }

    /** 停止之前的拖动，把之前拖动的那个item从windowManage里面remove掉 **/
    private void stopDrag() {
        if (dragImageView != null) {
            windowManager.removeView(dragImageView);
            dragImageView = null;
        }
    }

}