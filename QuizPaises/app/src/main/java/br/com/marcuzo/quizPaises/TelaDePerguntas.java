package br.com.marcuzo.quizPaises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TelaDePerguntas extends AppCompatActivity {

    private AtomicInteger respostasCorretas;
    private AtomicInteger idPaisAtual;
    private Partidas partida;
    private ArrayList<Integer> idPaises ;
    private RadioGroup grp_alternativas;
    private RadioButton rb_alternativaA;
    private RadioButton rb_alternativaB;
    private RadioButton rb_alternativaC;
    private RadioButton rb_alternativaD;
    private ImageView iv_bandeira;
    private String alternativaCorreta;
    private Button btn_responder;
    private String respostaDoUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_perguntas);

        //Inicialização dos objetos
        this.btn_responder = findViewById(R.id.btn_responder);
        this.grp_alternativas = findViewById(R.id.rg_alternativas);
        this.rb_alternativaA = findViewById(R.id.rb_alternativaA);
        this.rb_alternativaB = findViewById(R.id.rb_alternativaB);
        this.rb_alternativaC = findViewById(R.id.rb_alternativaC);
        this.rb_alternativaD = findViewById(R.id.rb_alternativaD);
        this.iv_bandeira = findViewById(R.id.iv_bandeira);

        //Iniciar nova partida.
        this.zerarPartida();

        //Carregar primeira pergunta.
        btn_responder.post(() -> btn_responder.performClick());

        //Carregar respota selecionada.
        grp_alternativas.setOnCheckedChangeListener((radioGroup, i) -> {
            btn_responder.setEnabled(true);
            btn_responder.setBackgroundTintList(getColorStateList(R.color.teal_250));
            btn_responder.setText("Responder");
            switch (i){
                case R.id.rb_alternativaA:
                    respostaDoUsuario = "A";
                    break;
                case R.id.rb_alternativaB:
                    respostaDoUsuario = "B";
                    break;
                case R.id.rb_alternativaC:
                    respostaDoUsuario = "C";
                    break;
                case R.id.rb_alternativaD:
                    respostaDoUsuario = "D";
                    break;
            }
        });

        //Carregar próxima pergunta
        btn_responder.setOnClickListener(v -> {

            //Verificar se resposta enviada está correta
            if(respostaDoUsuario.equals(alternativaCorreta)){
                respostasCorretas.getAndIncrement();
            }
            else {
                if(respostasCorretas.get() <= 0){
                    respostasCorretas.set(0);
                }
            }

            //Limpar resposta selecionada e bloquear botão de resposta.
            grp_alternativas.clearCheck();
            btn_responder.setEnabled(false);
            btn_responder.setText("Escolha uma alternativa");
            btn_responder.setBackgroundTintList(getColorStateList(R.color.black));

            //Verificar se ainda há perguntas a serem respondidas.
            try {
                EnumPaises pais = partida.retornarPaises(idPaises.get(idPaisAtual.get()));
                Arrays.asList( pais ).forEach(p -> {

                    rb_alternativaA.setText(p.getAlternativas()[0]);
                    rb_alternativaB.setText(p.getAlternativas()[1]);
                    rb_alternativaC.setText(p.getAlternativas()[2]);
                    rb_alternativaD.setText(p.getAlternativas()[3]);
                    iv_bandeira.setImageResource(p.getCodImgPais());

                    this.alternativaCorreta = p.getRespostaCorreta();
                });
                idPaisAtual.getAndIncrement();
            }
            catch (Exception e){
                this.finish();
                Intent intent = new Intent(TelaDePerguntas.this, Ranking.class);
                intent.putExtra("pontuacao", String.valueOf(respostasCorretas.get()));
                intent.putExtra("nomeJogador", partida.getNomeJogador());
                startActivity(intent);
                System.out.println("Respostas corretas: " + respostasCorretas.get());
            }
        });

    }

    //Iniciar partida zerada.
    public void zerarPartida(){
        this.respostasCorretas = new AtomicInteger();
        this.idPaisAtual = new AtomicInteger();
        this.partida = (Partidas) getIntent().getSerializableExtra("partida");
        this.idPaises = getIntent().getIntegerArrayListExtra("idPaises");

    }

    //Voltar para a tela inicial.
    @Override
    public void onBackPressed() {
        this.finish();
        Intent it = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(it);
    }

}