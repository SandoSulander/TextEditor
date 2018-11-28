package store.catsocket.texteditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    EditText fileName, editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fileName = (EditText) findViewById(R.id.fileName);
        editText = (EditText) findViewById(R.id.editText);

    }

    // Save Button
    public void saveButton(View v) {
        String filename = fileName.getText().toString();
        String edittext = editText.getText().toString();

        if (!filename.equals("") && !edittext.equals("")) {
            saveTextFile(filename, edittext);
        }else{
            Toast.makeText(this, "Empty fields!", Toast.LENGTH_SHORT).show();
        }
    }

    // Load Button
    public void loadButton(View v) {
        String filename = fileName.getText().toString();

        if (!filename.equals("")) {
            loadTextFile(filename);
        } else{
            Toast.makeText(this, "Input file name!", Toast.LENGTH_SHORT).show();
        }
    }

    // Saves the input text as a file
    public void saveTextFile(String filename, String edittext) {

        String fileNameTxt = filename + ".txt";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileNameTxt, MODE_PRIVATE);
            fos.write(edittext.getBytes());

            editText.getText().clear();
            fileName.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + fileNameTxt,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not found!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving!", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error saving!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    // Loads a text file into the text field
    public void loadTextFile(String filename){

        String fileNameTxt = filename + ".txt";

        FileInputStream fis = null;

        try {
            fis = openFileInput(fileNameTxt);
            fileName.getText().clear();
            Toast.makeText(this, filename + ".txt loaded!", Toast.LENGTH_SHORT).show();
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            editText.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not found!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading!", Toast.LENGTH_SHORT).show();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error loading!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

