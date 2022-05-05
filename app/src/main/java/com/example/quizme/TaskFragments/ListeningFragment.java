package com.example.quizme.TaskFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.quizme.ListeningQuiz;
import com.example.quizme.R;

public class ListeningFragment extends Fragment  implements View.OnClickListener{
    private CardView cardView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_listening, container, false);

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardView = cardView.findViewById(R.id.card_view3);
        cardView.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_view3:
                Intent intent = new Intent();
                intent.setClass(getActivity(), ListeningQuiz.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
