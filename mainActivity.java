package com.meuapp1986.oguru;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
 
 private TextView textFrases;
    private Button bt1;
    private String[] frases = {

            "Sim",
            "Não",
            "Talvez",
            "Já tentou responder essa pergunta para si mesmo?",
            "Provavelmente",
            "Definitivamente sim",
            "Definitivamente não",
            "Melhor você não contar com isso",
            "Concentre-se e pergunte novamente",
            "Não posso prever agora",
            "Pergunte novamente mais tarde",
           "Minha resposta é não",
            "Minhas fontes dizem não",
          "Perspectiva não tão boa",
            "Muito duvidoso",
           "Sem dúvida",
            "Sim definitivamente",
            "É decididamente assim",
            "Você pode confiar nisso",
            "Sinais apontam para sim",
    "Sem sombra de dúvida", 
    };
     
    private Handler handler = new Handler();
    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            animateNewQuestionText();
            isRunning = false;
        }
    };
     
    private int updateDelay = 2000; //Declaração de uma variável do tipo inteiro chamada
    // updateDelay que armazena o tempo de delay em milissegundos antes da atualização de
    // um novo texto na tela. O valor inicial é de 2000 milissegundos (2 segundos).
    private boolean isRunning = false; //Declaração de uma variável do tipo booleano chamada
    // isRunning que é utilizada para verificar se a animação do texto está em execução ou não.
    // O valor inicial é false.
 
    @Override // Indica que o método que segue sobrescreve um método existente na classe pai (superclasse).
    protected void onCreate(Bundle savedInstanceState) { // - Início do método onCreate(), que é chamado quando a atividade é criada.
        super.onCreate(savedInstanceState);  //Chamada do método onCreate() da superclasse.
        setContentView(R.layout.activity_main); // Define o layout XML que será exibido na tela para a atividade. Nesse caso, o layout é activity_main.xml.
        textFrases = findViewById(R.id.bt1); // Encontra o componente de texto textFrases no layout, que é representado pelo elemento com o ID bt1.
        bt1 = findViewById(R.id.bt1);  //Encontra o botão bt1 no layout, que é representado pelo elemento com o ID bt1.
        bt1.setOnClickListener(new View.OnClickListener() {  //Adiciona um ouvinte de clique ao botão bt1.
             
            @Override
            public void onClick(View v) {
                if (!isRunning) {       //Verifica se a animação do texto não está em execução.
                    Random random = new Random();      //Cria uma instância da classe Random, que é usada para gerar um número aleatório.
                    int indice = random.nextInt(frases.length); //Gera um número aleatório entre 0 e o comprimento do array frases.
                    String frase = frases[indice];          //Seleciona uma frase aleatória do array frases.
                    bt1.setBackgroundResource(R.color.transp);  //Define o fundo do botão bt1 como transparente.
                    animateText(frase);     //Inicia a animação do texto com a frase selecionada.
                    isRunning = true;       //Define a variável isRunning como true para indicar que a animação do texto está em execução.
                    handler.removeCallbacks(updateRunnable);   //Remove quaisquer callbacks pendentes associados ao objeto handler.
                    handler.postDelayed(updateRunnable, updateDelay);
                }
            }
        });
    }
     
    private void animateText(String text) {  //Início do método animateText(), que é usado para animar o texto exibido na tela.
        textFrases.setText(""); //Define o texto do componente textFrases como vazio.
        Animation animation = new AlphaAnimation(0.0f, 1.0f);//- Cria uma animação de transição de opacidade do texto,
        // que começa com opacidade zero (invisível) e termina com opacidade um (totalmente visível).
        animation.setDuration(800);     // Define a duração da animação em milissegundos. Nesse caso, a duração é de 800 milissegundos
        animation.setStartOffset(600);  // Define a duração da animação em milissegundos. Nesse caso, a duração é de 600 milissegundos
        textFrases.setText(text);  //Define o texto do componente textFrases com a string text recebida como parâmetro.
        textFrases.startAnimation(animation); // Inicia a animação definida na variável animation.
    }
     
    private void animateNewQuestionText() {
        String text = "Faça uma nova pergunta\ne clique aqui";
        Animation animation = new AlphaAnimation(1.0f, 0.0f); ////- Cria uma animação de transição de opacidade do texto,
        animation.setDuration(800);
        animation.setStartOffset(600);
        animation.setAnimationListener(new Animation.AnimationListener() {
             
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                textFrases.setText(text);
                Animation animation2 = new AlphaAnimation(0.0f, 1.0f);
                animation2.setDuration(800);
                animation2.setStartOffset(600);
                textFrases.startAnimation(animation2);
            }
             
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        textFrases.startAnimation(animation);
    }
 } 
