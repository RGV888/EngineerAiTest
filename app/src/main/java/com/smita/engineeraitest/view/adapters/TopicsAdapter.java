package com.smita.engineeraitest.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smita.engineeraitest.Models.Hits;
import com.smita.engineeraitest.R;
import com.smita.engineeraitest.utils.AppUtils;

import java.util.List;


public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicViewHolder> {
    private Context context;
    private List<Hits> topicList;
    private int selectedCounter=0;
    private ItemClick itemClick;
    public TopicsAdapter(Context context, List<Hits> topicList,ItemClick itemClick) {
        this.context = context;
        this.topicList = topicList;
        this.itemClick=itemClick;
    }

    public void resetCounter(){
        selectedCounter=0;
    }


    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topic_item_card,parent,false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        try {
            Hits info=topicList.get(position);

            try {
                holder.title.setText(info.getTitle());
            }catch (NullPointerException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                String reqDate= AppUtils.convertDateToStr(info.getCreated_at(),"dd-mm-yyyy hh:mm a");
                holder.createdDate.setText(reqDate);
            }catch (NullPointerException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            holder.counterSwitch.setChecked(info.isSelected());


        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return topicList==null?0:topicList.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView createdDate;
        private Switch counterSwitch;
        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            createdDate=itemView.findViewById(R.id.createdDate);
            counterSwitch=itemView.findViewById(R.id.counterSwitch);
            counterSwitch.setOnCheckedChangeListener(null);
            counterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(counterSwitch.isPressed()){
                        if(topicList.get(getAdapterPosition()).isSelected()){
                            selectedCounter--;
                            counterSwitch.setChecked(false);
                            topicList.get(getAdapterPosition()).setSelected(false);
                        }else{
                            selectedCounter++;
                            counterSwitch.setChecked(true);
                            topicList.get(getAdapterPosition()).setSelected(true);
                        }

                        itemClick.ClickPost(selectedCounter,topicList.get(getAdapterPosition()));
                    }

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(topicList.get(getAdapterPosition()).isSelected()){
                        selectedCounter--;
                        counterSwitch.setChecked(false);
                        topicList.get(getAdapterPosition()).setSelected(false);
                    }else{
                        selectedCounter++;
                        counterSwitch.setChecked(true);
                        topicList.get(getAdapterPosition()).setSelected(true);
                    }

                    itemClick.ClickPost(selectedCounter,topicList.get(getAdapterPosition()));

                }
            });


        }
    }

    public interface ItemClick{

        void ClickPost(int selectedCounter,Hits selectedPost);

    }

}
