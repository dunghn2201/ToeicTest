package com.dunghn.toeictest.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dunghn.toeictest.GrammarDetailActivity;
import com.dunghn.toeictest.R;
import com.dunghn.toeictest.adapter.GrammarAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrammarFragment extends Fragment {

  private View rootView;
    int images[] = {R.mipmap.present_tenses, R.mipmap.past_tenses, R.mipmap.future_tenses,
            R.mipmap.v_ing, R.mipmap.to_infinitive, R.mipmap.bare_infinitive_gerund_infinitive,
            R.mipmap.modal_verbs, R.mipmap.setence_elements, R.mipmap.passive_voice, R.mipmap.passive_voice2,
            R.mipmap.comparisons, R.mipmap.conditionals, R.mipmap.wishes, R.mipmap.relative_clauses,
            R.mipmap.exclamatory_sentences, R.mipmap.reported_speech, R.mipmap.types_of_questions,
            R.mipmap.subjunctive_structures, R.mipmap.subject_verb_agreement, R.mipmap.inversion_of_verbs,
            R.mipmap.emphasis, R.mipmap.relationships_between_ideas, R.mipmap.relationships_between_ideas2,
            R.mipmap.relationships_between_ideas3};
    String title[] = {};
    String meaning[] = {};
    GrammarAdapter rvAdapter;
    RecyclerView rv;
    LinearLayoutManager llm;
    public GrammarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_grammar, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv1);
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.scrollToPosition(0);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        title = getActivity().getResources().getStringArray(R.array.grammar_lessons);
        meaning = getActivity().getResources().getStringArray(R.array.grammar_meaning);
        rvAdapter = new GrammarAdapter(images, title, meaning);
        rv.setAdapter(rvAdapter);
        rvAdapter.setOnItemClickListener(new GrammarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent i = new Intent(getActivity(), GrammarDetailActivity.class);
                i.putExtra("position", position);
                i.putExtra("title", title[position]);
                startActivity(i);
            }
        });
        return rootView;
    }

}
