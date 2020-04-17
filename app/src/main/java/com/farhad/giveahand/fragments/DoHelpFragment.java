package com.farhad.giveahand.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.farhad.giveahand.DoHelpActivity;
import com.farhad.giveahand.MainActivity;
import com.farhad.giveahand.R;
import com.farhad.giveahand.api.ApiClient;
import com.farhad.giveahand.api.ApiServices;
import com.farhad.giveahand.models.Area;
import com.farhad.giveahand.models.Areas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoHelpFragment extends Fragment {
    private AppCompatAutoCompleteTextView edtPrimaryTask;
    private List<String> mAreasList;
    private String date;
    private TextView tv_date, tv_area, tv_desc;
    private Button btn_details, btn_contact;
    private ProgressBar mProgressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_do_help, container, false);

        edtPrimaryTask = view.findViewById(R.id.search_location);
        btn_details = view.findViewById(R.id.btn_details);
        btn_contact = view.findViewById(R.id.btn_contact);
        tv_area = view.findViewById(R.id.tv_location);
        tv_date = view.findViewById(R.id.tv_date);
        tv_desc = view.findViewById(R.id.desc);
        mProgressBar = view.findViewById(R.id.progressBar2);

        mProgressBar.setVisibility(View.VISIBLE);
        getAreas();

        getDate();

        tv_date.setText(date);

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getContext(), DoHelpActivity.class);
                intent.putExtra("location", edtPrimaryTask.getText().toString());
                intent.putExtra("date", tv_date.getText().toString());
                startActivity(intent);*/
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_desc.getText().toString(), null));
                startActivity(intent);
            }
        });
        btn_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DoHelpActivity.class);
                startActivity(intent);
            }
        });

        edtPrimaryTask.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_area.setText("" + edtPrimaryTask.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_area.setText("" + edtPrimaryTask.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tv_area.setText("" + edtPrimaryTask.getText().toString());
            }
        });
        return view;
    }

    private void getDate() {
        LocalDate myObj = null; // Create a date object
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            myObj = LocalDate.now();
            // Date--------------------------------------------------------
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = myObj.format(myFormatObj);
        } else {
            date = new Date().toString();
        }
    }

    private void getAreas() {
        Call<Areas> call = MainActivity.apiServices.getAreas();
        call.enqueue(new Callback<Areas>() {
            @Override
            public void onResponse(Call<Areas> call, Response<Areas> response) {
                if(response.isSuccessful()){
                    mAreasList = new ArrayList<>();
                    Areas areas = response.body();
                    for(Area area: areas.getAreas()){
                        mAreasList.add(area.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (getContext(), android.R.layout.select_dialog_item, mAreasList);
                    edtPrimaryTask.setThreshold(1); //will start working from first character
                    edtPrimaryTask.setAdapter(adapter);
                    Log.d("TAG", "onResponse: " + mAreasList.size());
                    mProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Areas> call, Throwable t) {

            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("No data found to show...");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user clicked OK
                // Toast.makeText(AssignTrainingActivity.this, "" + selectedAthletes.get(0), Toast.LENGTH_SHORT).show();

            }
        });
//        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
