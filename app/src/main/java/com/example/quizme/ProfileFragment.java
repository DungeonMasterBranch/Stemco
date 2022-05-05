package com.example.quizme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser vUser = mAuth.getCurrentUser();

        FirebaseFirestore fFireStroe = FirebaseFirestore.getInstance();


//        vUser.reload();
//        vUser = mAuth.getCurrentUser();



        EditText nameEdit, emailEdit;
        TextView editBtn;
        Button verifyBtn;
        nameEdit = getView().findViewById(R.id.nameEdit);
        //String passEdit = getView().findViewById(R.id.passwordEdit);
        emailEdit = getView().findViewById(R.id.emailEdit);
        editBtn = getView().findViewById(R.id.editBtn);
        verifyBtn = getView().findViewById(R.id.verifyBtn);
        //code for check and send verification;

        emailEdit.setText(vUser.getEmail());
            if (!vUser.isEmailVerified()){
                verifyBtn.setVisibility(View.VISIBLE);
            } else {
                verifyBtn.setVisibility(View.INVISIBLE);
            }

//        public void fetchData()
//    {
//            DocumentReference document = fFireStroe.collection("users").document("HrBLagFe70gq7ferQHNWlLqzFl72");
//            document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                @Override
//                public void onSuccess(DocumentSnapshot documentSnapshot) {
//                    if(documentSnapshot.exists()){
//
//
//                    }
//                }
//            })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getContext(), "check your email to verify your account!", Toast.LENGTH_LONG).show();
//                        }
//                    });
//
//    }
    }


}
