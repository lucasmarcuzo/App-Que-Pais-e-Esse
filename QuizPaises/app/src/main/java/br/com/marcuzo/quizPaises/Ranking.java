package br.com.marcuzo.quizPaises;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    TextView tv_nomeJogador;
    TextView tv_pontuacao;
    Button btn_jogarNovamente;
    Button btn_telaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        //Esconder a ActionBar
        getSupportActionBar().hide();

        tv_nomeJogador = findViewById(R.id.tv_nomeJogador);
        tv_pontuacao = findViewById(R.id.tv_pontuacao);
        btn_jogarNovamente = findViewById(R.id.btn_jogarNovamente);
        btn_telaPrincipal = findViewById(R.id.btn_telaPrincipal);

        tv_nomeJogador.setText(getIntent().getStringExtra("nomeJogador"));
        tv_pontuacao.setText(getIntent().getStringExtra("pontuacao"));

        btn_jogarNovamente.setOnClickListener(view -> {
            Partidas partida = new Partidas(tv_nomeJogador.getText().toString(), 0);
            ArrayList<Integer> idPaises = partida.gerarOrdemPerguntas();

            this.finish();
            Intent it = new Intent(getApplicationContext(), TelaDePerguntas.class);
            it.putExtra("partida", partida);
            it.putIntegerArrayListExtra("idPaises", idPaises);
            startActivity(it);
        });

        btn_telaPrincipal.setOnClickListener(view -> {
            this.finish();
            Intent it = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(it);
        });

       //Voltar para a tela inicial se for clicado no botão de voltar no celular.
       getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }
        });
    }
}