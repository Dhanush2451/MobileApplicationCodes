package com.example.textvault;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private EditText editTextFileName;
    private Button buttonSave;
    private Button buttonViewFiles;
    private Button buttonReadFile;
    private Button buttonVoiceInput;
    private TextView textViewFileList;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextInput = findViewById(R.id.editTextInput);
        editTextFileName = findViewById(R.id.editTextFileName);
        buttonSave = findViewById(R.id.buttonSave);
        buttonViewFiles = findViewById(R.id.buttonViewFiles);
        buttonReadFile = findViewById(R.id.buttonReadFile);
        buttonVoiceInput = findViewById(R.id.buttonVoiceInput);
        textViewFileList = findViewById(R.id.textViewFileList);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int langResult = textToSpeech.setLanguage(Locale.US);
                if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(this, "Language not supported or missing data", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Text-to-Speech initialization failed", Toast.LENGTH_SHORT).show();
            }
        });

        buttonSave.setOnClickListener(v -> saveTextToFile());

        buttonViewFiles.setOnClickListener(v -> viewSavedFiles());

        buttonReadFile.setOnClickListener(v -> readFileContent());
        buttonVoiceInput.setOnClickListener(v -> startVoiceRecognition());
    }

    private void saveTextToFile() {
        String text = editTextInput.getText().toString();
        String fileName = editTextFileName.getText().toString();

        if (fileName.isEmpty()) {
            Toast.makeText(this, "Please enter a file name", Toast.LENGTH_SHORT).show();
            return;
        }

        File directory = getExternalFilesDir("MyTextFiles");
        if (directory == null) {
            Toast.makeText(this, "Directory not accessible", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(directory, fileName + ".txt");

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(text);
            Toast.makeText(this, "File saved: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            textToSpeech.speak("File saved successfully", TextToSpeech.QUEUE_FLUSH, null, null);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewSavedFiles() {
        File directory = getExternalFilesDir("MyTextFiles");

        if (directory != null && directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                StringBuilder fileNames = new StringBuilder("Saved files:\n");
                for (File file : files) {
                    fileNames.append(file.getName()).append("\n");
                }
                textViewFileList.setText(fileNames.toString());
            } else {
                textViewFileList.setText("No files found.");
            }
        } else {
            textViewFileList.setText("Directory not found.");
        }
    }

    private void readFileContent() {
        String fileName = editTextFileName.getText().toString();

        if (fileName.isEmpty()) {
            Toast.makeText(this, "Please enter a file name", Toast.LENGTH_SHORT).show();
            return;
        }

        File directory = getExternalFilesDir("MyTextFiles");
        if (directory == null) {
            Toast.makeText(this, "Directory not accessible", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(directory, fileName + ".txt");

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                String fileContent = content.toString();
                textToSpeech.speak(fileContent, TextToSpeech.QUEUE_FLUSH, null, null);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.US);

        try {
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            Toast.makeText(this, "Speech recognition not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                editTextInput.setText(result.get(0));
                saveTextToFile();
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (textViewFileList.getVisibility() == View.VISIBLE) {

            resetToMainView();
        } else {
            super.onBackPressed();
        }
    }

    private void resetToMainView() {

        textViewFileList.setText("");
        buttonSave.setVisibility(View.VISIBLE);
        buttonViewFiles.setVisibility(View.VISIBLE);
        buttonReadFile.setVisibility(View.VISIBLE);
        buttonVoiceInput.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
