package anim;

import java.util.ArrayList;

public class MQuestion {
    private ArrayList<Question> questions = new ArrayList<>();
        public MQuestion() {
                    // Matemáticas
            questions.add(new Question(
                "¿Cuánto es 3/4 + 1/2?",
                new String[]{"1/4", "5/4", "3/8"},
                1
            ));
            questions.add(new Question(
                "Si x + 5 = 12, ¿cuánto vale x?",
                new String[]{"7", "17", "6"},
                0
            ));
            questions.add(new Question(
                "¿Cuál es el área de un cuadrado de lado 5 cm?",
                new String[]{"10 cm²", "25 cm²", "20 cm²"},
                1
            ));
            questions.add(new Question(
                "¿Qué número es primo?",
                new String[]{"12", "7", "15"},
                1
            ));
            questions.add(new Question(
                "¿Cuánto es 15% de 200?",
                new String[]{"15", "30", "45"},
                1
            ));
            questions.add(new Question(
                "¿Cuál es el resultado de 2³ × 3²?",
                new String[]{"36", "72", "18"},
                1
            ));
            questions.add(new Question(
                "Si un triángulo tiene dos ángulos de 45°, ¿qué tipo de triángulo es?",
                new String[]{"Equilátero", "Isósceles", "Escaleno"},
                1
            ));
            questions.add(new Question(
                "¿Cuál es el perímetro de un rectángulo de lados 8 cm y 3 cm?",
                new String[]{"11 cm", "22 cm", "24 cm"},
                1
            ));
            questions.add(new Question(
                "¿Qué fracción es equivalente a 0.75?",
                new String[]{"1/4", "3/4", "2/3"},
                1
            ));
            questions.add(new Question(
                "¿Cuál es el MCD de 12 y 18?",
                new String[]{"3", "6", "9"},
                1
            ));

            // Inglés
            questions.add(new Question(
                "What is the past tense of \"go\"?",
                new String[]{"goed", "went", "gone"},
                1
            ));
            questions.add(new Question(
                "Choose the correct sentence:",
                new String[]{"She don't like pizza.", "She doesn't likes pizza.", "She doesn't like pizza."},
                2
            ));
            questions.add(new Question(
                "The cat is ___ the table. (Complete with the correct preposition).",
                new String[]{"on", "in", "at"},
                0
            ));
            questions.add(new Question(
                "What is the opposite of \"cheap\"?",
                new String[]{"expensive", "small", "ugly"},
                0
            ));
            questions.add(new Question(
                "Which word is a noun?",
                new String[]{"run", "happiness", "quickly"},
                1
            ));
            questions.add(new Question(
                "I ___ my homework yesterday. (Complete with the correct verb).",
                new String[]{"do", "did", "done"},
                1
            ));
            questions.add(new Question(
                "What is the plural of \"child\"?",
                new String[]{"childs", "children", "childes"},
                1
            ));
            questions.add(new Question(
                "She ___ to the park every Sunday. (Complete with the correct verb).",
                new String[]{"go", "goes", "going"},
                1
            ));
            questions.add(new Question(
                "Which is the correct question form?",
                new String[]{"Where you live?", "Where do you live?", "Where does you live?"},
                1
            ));
            questions.add(new Question(
                "This book is ___ than the other one. (Complete with the correct comparative).",
                new String[]{"interestinger", "more interesting", "most interesting"},
                1
            ));

            // Historia
            questions.add(new Question(
                "¿Quién descubrió América en 1492?",
                new String[]{"Hernán Cortés", "Cristóbal Colón", "Francisco Pizarro"},
                1
            ));
            questions.add(new Question(
                "La Revolución Francesa ocurrió en:",
                new String[]{"1776", "1789", "1810"},
                1
            ));
            questions.add(new Question(
                "¿Qué civilización construyó las pirámides de Egipto?",
                new String[]{"Los griegos", "Los egipcios", "Los romanos"},
                1
            ));
            questions.add(new Question(
                "La Independencia de México comenzó en:",
                new String[]{"1810", "1821", "1848"},
                0
            ));
            questions.add(new Question(
                "¿Quién fue el primer presidente de Estados Unidos?",
                new String[]{"Thomas Jefferson", "Abraham Lincoln", "George Washington"},
                2
            ));
            questions.add(new Question(
                "La Segunda Guerra Mundial terminó en:",
                new String[]{"1945", "1939", "1918"},
                0
            ));
            questions.add(new Question(
                "¿Qué imperio dominó México antes de la llegada de los españoles?",
                new String[]{"Inca", "Maya", "Azteca"},
                2
            ));
            questions.add(new Question(
                "La Revolución Industrial comenzó en:",
                new String[]{"Francia", "Inglaterra", "Alemania"},
                1
            ));
            questions.add(new Question(
                "¿Quién pintó \"La Mona Lisa\"?",
                new String[]{"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci"},
                2
            ));
            questions.add(new Question(
                "La caída del Muro de Berlín ocurrió en:",
                new String[]{"1989", "1991", "1975"},
                0
            ));

            // Geografía
            questions.add(new Question(
                "¿Cuál es el río más largo del mundo?",
                new String[]{"Amazonas", "Nilo", "Mississippi"},
                0
            ));
            questions.add(new Question(
                "¿Qué continente es el más grande?",
                new String[]{"África", "Asia", "América"},
                1
            ));
            questions.add(new Question(
                "La capital de Canadá es:",
                new String[]{"Toronto", "Ottawa", "Vancouver"},
                1
            ));
            questions.add(new Question(
                "¿Qué océano está entre América y Europa?",
                new String[]{"Pacífico", "Atlántico", "Índico"},
                1
            ));
            questions.add(new Question(
                "El Monte Everest está en:",
                new String[]{"Los Andes", "Los Alpes", "El Himalaya"},
                2
            ));
            questions.add(new Question(
                "¿Cuál es el país más grande del mundo?",
                new String[]{"China", "Rusia", "Estados Unidos"},
                1
            ));
            questions.add(new Question(
                "La capital de Australia es:",
                new String[]{"Sídney", "Canberra", "Melbourne"},
                1
            ));
            questions.add(new Question(
                "¿Qué desierto es el más grande del mundo?",
                new String[]{"Sahara", "Gobi", "Antártico"},
                2
            ));
            questions.add(new Question(
                "El lago más grande de América es:",
                new String[]{"Lago Titicaca", "Lago Superior", "Lago Michigan"},
                1
            ));
            questions.add(new Question(
                "¿Qué país tiene forma de bota?",
                new String[]{"Grecia", "Italia", "España"},
                1
            ));

            // Ciencias Naturales
            questions.add(new Question(
                "¿Qué gas necesitan las plantas para la fotosíntesis?",
                new String[]{"Oxígeno", "Dióxido de carbono", "Nitrógeno"},
                1
            ));
            questions.add(new Question(
                "El hueso más largo del cuerpo humano es:",
                new String[]{"Cráneo", "Fémur", "Columna vertebral"},
                1
            ));
            questions.add(new Question(
                "¿Qué planeta es conocido como \"el planeta rojo\"?",
                new String[]{"Venus", "Marte", "Júpiter"},
                1
            ));
            questions.add(new Question(
                "¿Cuál es la unidad básica de la vida?",
                new String[]{"Átomo", "Célula", "Molécula"},
                1
            ));
            questions.add(new Question(
                "La fuerza que atrae los objetos hacia la Tierra es:",
                new String[]{"Magnetismo", "Gravedad", "Fricción"},
                1
            ));
            questions.add(new Question(
                "¿Qué tipo de energía tiene una batería?",
                new String[]{"Cinética", "Potencial", "Térmica"},
                1
            ));
            questions.add(new Question(
                "¿Qué capa protege a la Tierra de los rayos UV?",
                new String[]{"Ionosfera", "Atmósfera", "Capa de ozono"},
                2
            ));
            questions.add(new Question(
                "¿Qué animal es un mamífero?",
                new String[]{"Tiburón", "Pingüino", "Delfín"},
                2
            ));
            questions.add(new Question(
                "El proceso por el cual las plantas pierden agua se llama:",
                new String[]{"Fotosíntesis", "Transpiración", "Respiración"},
                1
            ));
            questions.add(new Question(
                "¿Cuál es el metal líquido a temperatura ambiente?",
                new String[]{"Hierro", "Mercurio", "Plata"},
                1
            ));
    }

    public Question getRandomQuestion() {
        int index = (int) (Math.random() * questions.size());
        return questions.get(index);
    }
}
