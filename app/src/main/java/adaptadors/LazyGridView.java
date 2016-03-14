package adaptadors;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.GridView;

/**
 * Created by christianalos on 14/3/16.
 */
public class LazyGridView extends GridView implements AbsListView.OnScrollListener {

    public LazyGridView(Context context) {
        super(context);
    }

    public LazyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LazyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /**
     * List view rolling
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                Log.v("onScrollStateChanged", "Has stopped: SCROLL_STATE_IDLE");
                // Scroll to the end of a judgment
                if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                    Log.v("onScrollStateChanged", "The end part. Can request to refresh.~~~~~~");
                    if (listener != null) {
                        listener.onScrollBottom();
                    }
                }
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                Log.v("onScrollStateChanged", "Start rolling: SCROLL_STATE_FLING");
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                Log.v("onScrollStateChanged", "Are rolling: SCROLL_STATE_TOUCH_SCROLL");
                break;
        }

    }

    private OnScrollBottomListener listener;

    public void setOnScrollBottomListener(OnScrollBottomListener listener) {
        this.setOnScrollListener(this);
        this.listener = listener;
    }

    public void removeOnScrollBottomListener() {
        listener = null;
        System.out.println("removeOnScrollBottomListener");
    }


    public interface OnScrollBottomListener {
        /**
         * List view bottom roll response
         */
        public void onScrollBottom();
    }
}
