package com.dunghn.toeictest.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dunghn.toeictest.ConversationActivity;
import com.dunghn.toeictest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConversationFragment extends Fragment {
private View rootView;
    Spinner optionsp;
    String options[] = {"Select Option", "Chat between two friends","Interview Conversation","Shopping in store","Discussion about a book","Asking for directions","Chat about weather","Booking a Taxi"};
    Button startchatbtn;

    public ConversationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_conversation, container, false);
        optionsp = rootView.findViewById(R.id.optionsp);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, options);
        optionsp.setAdapter(ad);
        startchatbtn = rootView.findViewById(R.id.startchatbtn);
        startchatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = optionsp.getSelectedItem().toString();
                if (value.equalsIgnoreCase("Select Option")) {

                    Toast.makeText(getContext(),"Please select a choice",Toast.LENGTH_SHORT).show();
                } else if (value.equalsIgnoreCase("Chat between two friends")) {
                    Intent in = new Intent(getActivity(), ConversationActivity.class);
                    in.putExtra("filename", "conversation1.txt");
                    startActivity(in);
                } else if (value.equalsIgnoreCase("Interview Conversation")) {
                    Intent in = new Intent(getActivity(), ConversationActivity.class);
                    in.putExtra("filename", "conversation2.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Shopping in store")) {
                    Intent in = new Intent(getActivity(), ConversationActivity.class);
                    in.putExtra("filename", "conversation3.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Discussion about a book")) {
                    Intent in = new Intent(getActivity(), ConversationActivity.class);
                    in.putExtra("filename", "conversation4.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Asking for directions")) {
                    Intent in = new Intent(getActivity(), ConversationActivity.class);
                    in.putExtra("filename", "conversation5.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Chat about weather")) {
                    Intent in = new Intent(getActivity(), ConversationActivity.class);
                    in.putExtra("filename", "conversation6.txt");
                    startActivity(in);
                }
                else if (value.equalsIgnoreCase("Booking a Taxi")) {
                    Intent in = new Intent(getActivity(), ConversationActivity.class);
                    in.putExtra("filename", "conversation7.txt");
                    startActivity(in);
                }
            }
        });

        return rootView;
    }

}
