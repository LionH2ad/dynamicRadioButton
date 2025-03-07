package com.example.dynamicRadioButton;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RadioButtonDialog.OnOptionSelectedListener {

    private TextView selectedValueTextView;
    private ArrayList<String> optionsList;
    private String selectedValue = "옵션 1"; // 기본 선택 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedValueTextView = findViewById(R.id.selectedValueTextView);
        Button openPopupButton = findViewById(R.id.openPopupButton);

        // ✅ 라디오 버튼 목록 (동적으로 변경 가능)
        optionsList = new ArrayList<>();
        optionsList.add("옵션 1");
        optionsList.add("옵션 2");
        optionsList.add("옵션 3");
        optionsList.add("옵션 4");
        optionsList.add("옵션 5");
        optionsList.add("옵션 6");
        optionsList.add("옵션 7");
        optionsList.add("옵션 8");
        optionsList.add("옵션 9");
        optionsList.add("옵션 10");

        // 초기 선택 값 표시
        selectedValueTextView.setText("선택된 옵션: " + selectedValue);

        // ✅ 팝업 버튼 클릭 시 다이얼로그 열기
        openPopupButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            String customTitle = "라디오 버튼 선택";
            RadioButtonDialog dialog = new RadioButtonDialog(customTitle, optionsList, selectedValue);
            dialog.show(fragmentManager, "RadioButtonDialog");
        });
    }

    // ✅ 팝업에서 선택한 값 적용
    @Override
    public void onOptionSelected(String selectedOption) {
        selectedValue = selectedOption;
        selectedValueTextView.setText("선택된 옵션: " + selectedValue);
    }
}
