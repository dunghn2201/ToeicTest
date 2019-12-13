package com.dunghn.toeictest.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.R;
import com.dunghn.toeictest.model.QuestionPart3_4;
import com.dunghn.toeictest.model.Status;

import java.util.List;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    Activity mactivity;
    private List<QuestionPart3_4> mquestionPs;
    public Status mstatus;
    public int groupNumber; //thứ tự của nhóm câu hỏi : câu hỏi đầu tiên trong nhóm câu hỏi


    public QuestionAdapter(Activity activity, List<QuestionPart3_4> questionPart3_4, int group, Status _status) {
        this.mactivity = activity;
        this.mquestionPs = questionPart3_4;
        this.mstatus = _status;
        this.groupNumber = group;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View questionView = inflater.inflate(R.layout.item_layout_part3_4_detail, parent, false);
        QuestionViewHolder questionViewHolder = new QuestionViewHolder(questionView);
        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionViewHolder holder, int position) {
        //xét từng câu hỏi
        QuestionPart3_4 questionP = mquestionPs.get(position);

        holder.tvIDquestion.setText("Question " + questionP.getId());
        holder.tvQuestion.setText(questionP.getContent());
        holder.rbtnAP.setText(questionP.getAnswerA());
        holder.rbtnCP.setText(questionP.getAnswerC());
        holder.rbtnBP.setText(questionP.getAnswerB());
        holder.rbtnDP.setText(questionP.getAnswerD());
        final int now = position + groupNumber;// toan bo la index
        switch (mstatus.getCustomerAnwser(now)) {
            case 1:
                holder.rbtnAP.setChecked(true);
                holder.rbtnBP.setChecked(false);
                holder.rbtnCP.setChecked(false);
                holder.rbtnDP.setChecked(false);
                break;
            case 2:
                holder.rbtnAP.setChecked(false);
                holder.rbtnBP.setChecked(true);
                holder.rbtnCP.setChecked(false);
                holder.rbtnDP.setChecked(false);
                break;
            case 3:
                holder.rbtnDP.setChecked(false);
                holder.rbtnAP.setChecked(false);
                holder.rbtnCP.setChecked(true);
                holder.rbtnBP.setChecked(false);
                break;
            case 4:
                holder.rbtnAP.setChecked(false);
                holder.rbtnBP.setChecked(false);
                holder.rbtnCP.setChecked(false);
                holder.rbtnDP.setChecked(true);
                break;
            default:
                holder.radioGroup.clearCheck();
        }
        holder.rbtnAP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    SharedPreferences sharedPref = mactivity.getSharedPreferences("myKey", mactivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value", "1|" + now);
                    if (holder.rbtnAP.isChecked()) {
                        editor.apply();
                        Log.d("XEMINDEX", "1|" + now);
                    }

                }
            }
        });

        holder.rbtnBP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    SharedPreferences sharedPref = mactivity.getSharedPreferences("myKey", mactivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value", "2|" + now);
                    if (holder.rbtnBP.isChecked()) {
                        editor.apply();
                        Log.d("XEMINDEX", "2|" + now);
                    }
                }
            }
        });


        holder.rbtnCP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    SharedPreferences sharedPref = mactivity.getSharedPreferences("myKey", mactivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value", "3|" + now);
                    if (holder.rbtnCP.isChecked()) {
                        editor.apply();
                        Log.d("XEMINDEX", "3|" + now);
                    }
                }
            }
        });


        holder.rbtnDP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    SharedPreferences sharedPref = mactivity.getSharedPreferences("myKey", mactivity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("value", "4|" + now);
                    if (holder.rbtnDP.isChecked()) {
                        editor.apply();
                        Log.d("XEMINDEX", "4|" + now);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mquestionPs.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView tvIDquestion, tvQuestion;
        RadioButton rbtnAP, rbtnBP, rbtnCP, rbtnDP;
        CardView cvquestion;
        RadioGroup radioGroup;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cvquestion = (CardView) itemView.findViewById(R.id.cardviewcauhoi);
            this.tvIDquestion = (TextView) itemView.findViewById(R.id.tvQuestionpart3_4);
            this.tvQuestion = (TextView) itemView.findViewById(R.id.tvQuesition);
            this.rbtnAP = (RadioButton) itemView.findViewById(R.id.rbtnAP);
            this.rbtnBP = (RadioButton) itemView.findViewById(R.id.rbtnBP);
            this.rbtnCP = (RadioButton) itemView.findViewById(R.id.rbtnCP);
            this.rbtnDP = (RadioButton) itemView.findViewById(R.id.rbtnDP);
            this.radioGroup = (RadioGroup) itemView.findViewById(R.id.radioGroup);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
