package android.sumanth.googlesignin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView pN;
    TextView pGN;
    TextView pFN;
    TextView pE;
    TextView pI;
    @SuppressLint("StaticFieldLeak")
    static ImageView img;
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);


        btnSignOut = findViewById(R.id.btnSignOut);
        pN = findViewById(R.id.pN);
        pGN = findViewById(R.id.pGN);
        pFN = findViewById(R.id.pF);
        pE = findViewById(R.id.pE);
        pI = findViewById(R.id.pI);
        img = findViewById(R.id.img);

        pN.setText(MainActivity.personName);
        pGN.setText(MainActivity.personGivenName);
        pFN.setText(MainActivity.personFamilyName);
        pE.setText(MainActivity.personEmail);
        pI.setText(MainActivity.personId);


        try {
            new ImageLoadTask(MainActivity.personPhoto.toString(), img).execute();
        } catch (Exception e) {
            Toast.makeText(SecondActivity.this,"User image not available !",Toast.LENGTH_LONG).show();

        }
        btnSignOut.setOnClickListener(v -> signOut());


    }

    private void signOut() {
        MainActivity.mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    Toast.makeText(SecondActivity.this,"Success !",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SecondActivity.this, MainActivity.class));
                    revokeAccess();
                });
    }

    private void revokeAccess() {
        MainActivity.mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, task -> Toast.makeText(SecondActivity.this,"Access Revoked !",Toast.LENGTH_LONG).show());
    }




}

