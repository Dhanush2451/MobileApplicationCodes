package com.example.lexifind;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvWord, tvIncorrectGuesses, tvAttemptsLeft, tvStatus;
    private String wordToGuess;
    private StringBuilder currentGuess;
    private int attemptsLeft = 7;
    private Random random = new Random();
    private String[] wordList = {
            "APPLE", "BANANA", "GRAPE", "ORANGE", "PINEAPPLE", "MANGO", "STRAWBERRY", "WATERMELON", "PEACH", "LEMON",
            "PUMPKIN", "CHERRY", "BLUEBERRY", "PLUM", "APRICOT", "MELON", "CANTALOUPE", "PEAR", "COCONUT", "FIG",
            "TOMATO", "KIWI", "CARAMEL", "COFFEE", "TEA", "SUGAR", "SPICE", "CINNAMON", "GINGER", "CLOVE",
            "HONEY", "ALMOND", "CASHEW", "WALNUT", "PISTACHIO", "PEANUT", "MACADAMIA", "CHOCOLATE", "VANILLA", "MAPLE",
            "LIME", "BERRY", "RASPBERRY", "BLACKBERRY", "STRAW", "CUPCAKE", "COOKIE", "CAKE", "BROWNIE", "MUFFIN",
            "DONUT", "CROISSANT", "PASTRY", "CARAMEL", "BISCUIT", "MINT", "MELON", "TANGERINE", "MANDARIN", "MELON",
            "GRAPEFRUIT", "RASPBERRY", "POMEGRANATE", "MANGO", "TOMATO", "LETTUCE", "SPINACH", "CARROT", "POTATO", "ONION",
            "GARLIC", "PARSLEY", "ROSEMARY", "BASIL", "THYME", "OREGANO", "CILANTRO", "DILL", "MINT", "SAGE",
            "PEPPERCORN", "CUMIN", "PAPRIKA", "CHILI", "TURMERIC", "CORIANDER", "FENNEL", "CARDAMOM", "SALT", "PEPPER",
            "SOYA", "WHEAT", "RICE", "CORN", "OATS", "BARLEY", "QUINOA", "FLOUR", "RAVIOLI", "LASAGNA",
            "SPAGHETTI", "PASTA", "PIZZA", "BURGER", "HOTDOG", "FRIES", "TACO", "SUSHI", "NIGIRI", "RAMEN",
            "PITA", "FALAFEL", "KABAB", "KURMA", "PAELLA", "TURKEY", "HAM", "STEAK", "CHICKEN", "FISH",
            "LOBSTER", "SHRIMP", "OYSTER", "CRAB", "SARDINE", "TUNA", "MACKEREL", "COD", "SALMON", "TRUFFER",
            "SEAFOOD", "VEGGIE", "VEGAN", "FARM", "BARN", "SILO", "GARDEN", "TERRACE", "BACKYARD", "GREENHOUSE",
            "GIRAFFE", "ELEPHANT", "LION", "TIGER", "LEOPARD", "CHEETAH", "ZEBRA", "KANGAROO", "KOALA", "PANDA",
            "RHINO", "HIPPO", "GIRAFFE", "CROCODILE", "ALLIGATOR", "SNAKE", "MONKEY", "GORILLA", "APE", "BISON",
            "BUFFALO", "COW", "SHEEP", "GOAT", "PIG", "HORSE", "DONKEY", "CAMEL", "LAMB", "RABBIT",
            "MOUSE", "SQUIRREL", "BEAR", "WOLF", "FOX", "RACCOON", "OPOSSUM", "SKUNK", "WEASEL", "FERRET",
            "BAT", "BIRD", "PARROT", "EAGLE", "OWL", "PEACOCK", "SWAN", "DUCK", "CHICKEN", "TURKEY",
            "ROOSTER", "FALCON", "VULTURE", "RAVEN", "CROW", "SPARROW", "HUMMINGBIRD", "WOODPECKER", "KINGFISHER", "SWALLOW",
            "PIGEON", "GOOSE", "LARK", "FINCH", "CANARY", "BLUEJAY", "SEAGULL", "CRANE", "STORK", "IBIS",
            "EGRET", "HERON", "SNIPE", "RUFF", "PLOVER", "HERRING", "CLOWNFISH", "TROUT", "SALMON", "TUNA",
            "COD", "HADDOCK", "MACKEREL", "SHAD", "PERCH", "STURGEON", "CATFISH", "EEL", "SEAFOOD", "FISHERY",
            "OCTOPUS", "SQUID", "CUTTLEFISH", "LOBSTER", "CRAB", "CLAM", "MUSSEL", "SCALLOP", "OYSTER", "SHRIMP",
            "WALRUS", "SEAL", "DOLPHIN", "WHAL", "ORCA", "MANATEE", "POLARBEAR", "KILLERWHALE", "MINK", "MARTEN",
            "BADGER", "OTTER", "HEDGEHOG", "SQUIRREL", "MOUSE", "BEE", "WASP", "HORNET", "BUTTERFLY", "MOTH",
            "ANT", "COCKROACH", "SPIDER", "LADYBUG", "TARANTULA", "CATERPILLAR", "CICADA", "BARNACLE", "MOLLUSK", "CRUSTACEAN",
            "PREDATOR", "PREY", "CARNIVORE", "HERBIVORE", "OMNIVORE", "MAMMAL", "REPTILE", "AMPHIBIAN", "FISH", "BIRD",
            "INSECT", "ARTHROPOD", "MOLLUSK", "FUNGUS", "BACTERIA", "VIRUS", "PROTIST", "EUKARYOTE", "BASIDIOMYCOTA", "CHITIN",
            "PROTOZOA", "EUGLENOPHYCYTA", "PLANT", "GENE", "DNA", "CELL", "MITOCHONDRIA", "NUCLEUS", "CHLOROPLAST", "RIBOSOME",
            "MITOSIS", "MEIOSIS", "CORTEX", "MELANIN", "PHYTOCHROME", "PHOTOSYNTHESIS", "MITOCHONDRION", "ENZYME", "METABOLISM", "ADENOSINE",
            "NUCLEOTIDE", "PROTEIN", "RIBOSOMAL", "PEPTIDE", "AMINOACID", "RNA", "TRANSLATION", "TRANSCRIPTION", "GENOME", "EXON",
            "INTRON", "GENETICS", "MUTATION", "ALLELE", "HOMOLOGOUS", "DNASE", "GEL", "BUFFER", "RECOMBINANT", "PLASMID",
            "VIRUS", "BACTERIA", "FUNGUS", "PROTOZOA", "ALGAE", "CYPHER", "GENOMIC", "SEQUENCE", "EXPRESSION", "REPAIR",
            "REPLICATION", "BIOTECHNOLOGY", "CLONING", "GENETICMODIFICATION", "TESTTUBE", "STEMCELL", "BIOREACTOR", "DNAEXTRACTION", "BLOOD",
            "HORMONE", "IMMUNE", "INFECTIOUS", "HEALTH", "ALCOHOL", "STIMULANT", "DEPRESSION", "SADNESS", "ANXIETY", "HAPPINESS",
            "FEAR", "WORRY", "STRESS", "RELAXATION", "EXERCISE", "YOGA", "MEDITATION", "MIND", "BRAIN", "MEMORY",
            "ATTENTION", "CONCENTRATION", "IMAGINATION", "CREATIVITY", "INSPIRATION", "INTUITION", "VISUALIZATION", "PERSUASION", "COGNITION", "EMOTION",
            "MOTIVATION", "LEADERSHIP", "COMMUNICATION", "LEARNING", "KNOWLEDGE", "EDUCATION", "STUDY", "TEACHING", "SCHOOL", "UNIVERSITY",
            "COLLEGE", "LECTURE", "EXAM", "TEXTBOOK", "LIBRARY", "STUDENT", "PROFESSOR", "SCHOOLWORK", "HOMEWORK", "PAPER",
            "ESSAY", "PROJECT", "ASSIGNMENT", "RESEARCH", "EXPERIMENT", "SCHOOLBUS", "TEACHER", "TUTOR", "COUNSELOR", "DEAN",
            "CAMPUS", "LECTURER", "STUDYROOM", "LABORATORY", "AUDITORIUM", "CLASSROOM", "ONLINE", "E-LEARNING", "DISTANCELEARNING", "MOBILELEARNING"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWord = findViewById(R.id.tvWord);
        tvIncorrectGuesses = findViewById(R.id.tvIncorrectGuesses);
        tvAttemptsLeft = findViewById(R.id.tvAttemptsLeft);
        tvStatus = findViewById(R.id.tvStatus);
        wordToGuess = wordList[random.nextInt(wordList.length)];
        currentGuess = new StringBuilder(createUnderscoreString(wordToGuess));
        tvWord.setText(currentGuess.toString());
        tvAttemptsLeft.setText("Attempts left: " + attemptsLeft);
        setUpLetterButtons();
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }

    private void setUpLetterButtons() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            String buttonId = "button" + letter;
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            Button letterButton = findViewById(resId);
            letterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleGuess(((Button) v).getText().toString());
                }
            });
        }
    }

    private String createUnderscoreString(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            builder.append("_ ");
        }
        return builder.toString();
    }

    private void handleGuess(String guess) {
        // Prevent further guesses after win or loss
        if (attemptsLeft == 0 || tvStatus.getText().toString().equals("You Win!") || tvStatus.getText().toString().equals("You Lose!")) {
            return;
        }
        Button button = findViewById(getButtonIdForLetter(guess));
        if (button != null) {
            button.setEnabled(false);
        }
        if (wordToGuess.contains(guess)) {
            updateWord(guess);
        } else {
            tvIncorrectGuesses.append(guess + " ");
            attemptsLeft--;
        }
        tvAttemptsLeft.setText("Attempts left: " + attemptsLeft);
        if (currentGuess.toString().replace(" ", "").equals(wordToGuess)) {
            tvStatus.setText("You Win!");
            disableAllButtons();
            onGameEnd();
        }
        else if (attemptsLeft == 0) {
            tvStatus.setText("You Lose! The word was: " + wordToGuess);
            disableAllButtons();
            onGameEnd();
        }
    }

    private int getButtonIdForLetter(String letter) {
        return getResources().getIdentifier("button" + letter, "id", getPackageName());
    }

    private void updateWord(String guess) {
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess.charAt(0)) {
                currentGuess.setCharAt(i * 2, guess.charAt(0));
            }
        }
        tvWord.setText(currentGuess.toString());
    }

    private void onGameEnd() {
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setVisibility(View.VISIBLE);
    }

    private void disableAllButtons() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            String buttonId = "button" + letter;
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            Button letterButton = findViewById(resId);
            letterButton.setEnabled(false);
        }
    }

    private void restartGame() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
