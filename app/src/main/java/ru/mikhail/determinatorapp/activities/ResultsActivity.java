package ru.mikhail.determinatorapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import ru.mikhail.determinatorapp.R;
import ru.mikhail.determinatorapp.common.LifeForm;

public class ResultsActivity extends AppCompatActivity {

    private TextView resClass;
    private TextView resPodClass;
    private TextView resOtryad;
    private TextView resSemeystvo;
    private TextView resRod;
    private TextView resVid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resClass = findViewById(R.id.results_class);
        resPodClass = findViewById(R.id.results_podclass);
        resOtryad = findViewById(R.id.results_otryad);
        resSemeystvo = findViewById(R.id.results_semeystvo);
        resRod = findViewById(R.id.results_rod);
        resVid = findViewById(R.id.results_vid);

        Map<LifeForm.Taxon, String> map = (HashMap<LifeForm.Taxon, String>) getIntent().getSerializableExtra("lifeform");
        LifeForm lifeForm = new LifeForm(map);
        resClass.setText(map.get(LifeForm.Taxon.CLASS));
        resPodClass.setText(map.get(LifeForm.Taxon.PODCLASS));
        resOtryad.setText(map.get(LifeForm.Taxon.OTRYAD));
        resSemeystvo.setText(map.get(LifeForm.Taxon.SEMEYSTVO));
        resRod.setText(map.get(LifeForm.Taxon.ROD));
        resVid.setText(map.get(LifeForm.Taxon.VID));
    }
}