package com.example.dynamicRadioButton;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;

public class RadioButtonDialog extends DialogFragment {

    public interface OnOptionSelectedListener {
        void onOptionSelected(String selectedOption);
    }

    private OnOptionSelectedListener listener;
    private ArrayList<String> optionsList;
    private String selectedValue;

    public RadioButtonDialog(ArrayList<String> optionsList, String selectedValue) {
        this.optionsList = optionsList;
        this.selectedValue = selectedValue;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OnOptionSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnOptionSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_radio_button, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnConfirm = view.findViewById(R.id.btnConfirm);

        // ✅ 동적으로 라디오 버튼 추가
        for (String option : optionsList) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(option);
            radioButton.setId(View.generateViewId());
            radioGroup.addView(radioButton);

            if (option.equals(selectedValue)) {
                radioButton.setChecked(true);
            }
        }

        // ❌ 취소 버튼 클릭 시 다이얼로그 닫기
        btnCancel.setOnClickListener(v -> dismiss());

        // ✅ 확인 버튼 클릭 시 선택한 값 전달 후 닫기
        btnConfirm.setOnClickListener(v -> {
            int checkedId = radioGroup.getCheckedRadioButtonId();
            if (checkedId != -1) {
                RadioButton selectedRadioButton = radioGroup.findViewById(checkedId);
                if (selectedRadioButton != null) {
                    listener.onOptionSelected(selectedRadioButton.getText().toString());
                }
            }
            dismiss();
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}

