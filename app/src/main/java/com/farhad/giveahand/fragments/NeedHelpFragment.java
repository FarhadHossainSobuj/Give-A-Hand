package com.farhad.giveahand.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.farhad.giveahand.MainActivity;
import com.farhad.giveahand.R;
import com.farhad.giveahand.models.Area;
import com.farhad.giveahand.models.Areas;
import com.farhad.giveahand.models.Post;
import com.farhad.giveahand.models.ResponseResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NeedHelpFragment extends Fragment {
    private EditText edt_address, edt_message, edt_mobile, edt_mobile_other;
    private Button btn_post;
    private Spinner sp_main_address;

    private List<String> listAreas;
    private List<Integer> mListAreaCode;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_need_help, container, false);

        edt_address = view.findViewById(R.id.edt_address);
        edt_message = view.findViewById(R.id.edt_message);
        edt_mobile = view.findViewById(R.id.edt_mobile);
        edt_mobile_other = view.findViewById(R.id.edt_mobile_other);
        sp_main_address = view.findViewById(R.id.sp_main_address);

        mListAreaCode = new ArrayList<>();

        listAreas = new ArrayList<>();
        getAreas();

        ArrayAdapter area = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listAreas);
        area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_main_address.setAdapter(area);

        btn_post = view.findViewById(R.id.btn_post);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()){
                    String address = edt_address.getText().toString();
                    String phone = edt_mobile.getText().toString();
                    String message = edt_message.getText().toString();
                    if (edt_mobile_other.getText().toString()==null){
                        Post post = new Post(mListAreaCode.get(sp_main_address.getSelectedItemPosition()),phone, message, address);
                        requestPost(post);
                    } else {
                        String otherPhone = edt_mobile_other.getText().toString();
                        Post post = new Post(mListAreaCode.get(sp_main_address.getSelectedItemPosition()),phone, otherPhone, message, address);
                        requestPost(post);
                    }
                }
            }
        });

        return view;
    }

    private void getAreas() {
        Call<Areas> call = MainActivity.apiServices.getAreas();
        call.enqueue(new Callback<Areas>() {
            @Override
            public void onResponse(Call<Areas> call, Response<Areas> response) {
                if(response.isSuccessful()){
                    Areas areas = response.body();
                    for(Area area: areas.getAreas()){
                        listAreas.add(area.getName());
                        mListAreaCode.add(area.getId());
                    }

                }
            }

            @Override
            public void onFailure(Call<Areas> call, Throwable t) {

            }
        });
    }

    private void requestPost(Post post) {
        Call<ResponseResult> call = MainActivity.apiServices.requestPost(post);
        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call<ResponseResult> call, Response<ResponseResult> response) {
                if (response.isSuccessful()){
                    Log.d("TAG", "onResponse: " + response.body().getStatus());
                    clearTextFields();
                    showDialog();
                }
            }

            @Override
            public void onFailure(Call<ResponseResult> call, Throwable t) {
                Log.d("TAG", "onResponse: " + t.getMessage());
                Toast.makeText(getContext(), "Your request is not accepted.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Your request accepted!!!");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                // Toast.makeText(AssignTrainingActivity.this, "" + selectedAthletes.get(0), Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();



    }

    private void clearTextFields() {
        edt_message.setText("");
        edt_address.setText("");
        edt_mobile.setText("");
        edt_mobile_other.setText("");
    }

    private boolean isValid() {
        boolean flag = true;
        if(TextUtils.isEmpty(edt_address.getText().toString())){
            edt_address.setError("আপনার ঠিকানা দিন");
            flag = false;
        }
        if(TextUtils.isEmpty(edt_message.getText().toString())){
            edt_message.setError("আপনার মেসেজ দিন");
            flag = false;
        }
        if(TextUtils.isEmpty(edt_mobile.getText().toString())){
            edt_mobile.setError("আপনার মোবাইল নম্বর দিন");
            flag = false;
        }
        return flag;
    }

}
