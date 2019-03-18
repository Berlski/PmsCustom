package com.berlski.tool.custom.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.inter.SwitchButtonInter;
import com.berlski.tool.custom.util.ColorUtil;

import java.util.List;

public class SwitchViewAdapter extends RecyclerView.Adapter<SwitchViewAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    private SwitchButtonInter inter;

    //默认选中项
    private int selectedPosition = 0;
    private String maxText = "";

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public SwitchViewAdapter(Context context, List<String> data, SwitchButtonInter inter) {
        this.context = context;
        this.data = data;
        this.inter = inter;


        //获取所有条目最长文字长度
        int maxTextLength = 0;
        for (String s : data) {
            if (s.length() > maxTextLength) {
                maxTextLength = s.length();
                maxText = s;
            }
        }
    }

    @Override
    public SwitchViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_input_info_switch, parent, false);
        return new SwitchViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwitchViewAdapter.ViewHolder holder, final int position) {

        TextView textView = holder.textView;

        textView.setText(data.get(position).trim());

        if (selectedPosition == holder.getAdapterPosition()) {

            textView.setBackgroundResource(0);

            textView.setTextColor(Color.WHITE);
        } else {
            textView.setTextColor(ColorUtil.getColor(context, R.color.color_styles));

            if (holder.getAdapterPosition() == 0) {
                textView.setBackgroundResource(R.drawable.background_switch_button_left);

            } else if (holder.getAdapterPosition() == data.size() - 1) {
                textView.setBackgroundResource(R.drawable.background_switch_button_right);
            } else {
                textView.setBackgroundColor(Color.WHITE);
            }
        }

        textView.setWidth(getTextWidth(13) + getCount(R.dimen.dp20));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedPosition(position);
                notifyDataSetChanged();

                if (inter != null) {
                    inter.switchIndex(position);
                }

                //Log.e("这里是点击每一行item的响应事件", "" + position );
            }
        });
    }

    /**
     * 根据文本的
     *
     * @param textSize
     * @return
     */
    public int getTextWidth(float textSize) {

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(sp2px(textSize));
        return (int) textPaint.measureText(maxText);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public int sp2px(float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据dimen值计算返回对应屏幕的px值，
     *
     * @param id R.dimen.id
     * @return
     */
    private int getCount(int id) {
        return context.getResources().getDimensionPixelSize(id);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_iis_text);
        }
    }
}
