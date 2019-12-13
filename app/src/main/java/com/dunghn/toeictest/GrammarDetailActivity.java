package com.dunghn.toeictest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

public class GrammarDetailActivity extends AppCompatActivity {
    PDFView pdf;
    private static final String FILE[] = {"unit1_present_tenses.pdf", "unit2_past_tenses.pdf", "unit3_future_tenses.pdf",
            "unit4_v_ing.pdf", "unit5_to_infinitive.pdf", "unit6_bare_infinitive_gerund_or_infinitive.pdf", "unit7_modal_verbs.pdf",
            "unit8_sentences_elements.pdf", "unit9_passive_voice1.pdf", "unit10_passive_voice2.pdf", "unit11_comparisons.pdf",
            "unit12_conditionals.pdf", "unit13_wishes.pdf", "unit14_relative_clauses.pdf", "unit15_exclamatory_sentences.pdf",
            "unit16_reported_speech.pdf", "unit17_types_of_questions.pdf", "unit18_subjunctive_structures.pdf", "unit19_subject_verb_agreement.pdf",
            "unit20_inversion_of_verbs.pdf", "unit21_emphasis.pdf", "unit22_relationships_between_ideas.pdf", "unit23_relationships_between_ideas2.pdf",
            "unit24_relationships_between_ideas3.pdf"
    };
    String pdfName;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_detail);
        pdf = (PDFView) findViewById(R.id.pdfview);
        int position = getIntent().getIntExtra("position", -1);
        String title = getIntent().getStringExtra("title");
        try {
            toolbar = (Toolbar) (findViewById(R.id.toolbarg));
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            getSupportActionBar().setTitle(title);
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>" + title + " </font>"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        pdfName = FILE[position];
        setTitle(pdfName);
        pdf.fromAsset(pdfName)
                .enableSwipe(true) // allows to block changing pages using swipe
                .defaultPage(0)
                .enableAntialiasing(true)
                .load();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
