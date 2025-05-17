
package anim;

public class Question {
    private String pregunta;
    private String[] opciones;
    private int respuestaCorrecta;

    public Question(String pregunta, String[] opciones, int respuestaCorrecta) {
        this.pregunta = pregunta;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public boolean checkAnswer(int respuestaUsuario) {
        return respuestaUsuario == respuestaCorrecta;
    }

    public String getPregunta() { return pregunta; }
    public String[] getOpciones() { return opciones; }
}