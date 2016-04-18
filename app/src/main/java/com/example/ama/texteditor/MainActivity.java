package com.example.ama.texteditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText fileName;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = (EditText) findViewById(R.id.edit_text1);
        text = (EditText) findViewById(R.id.editText2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return  true;
    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.open:
                openFile(fileName.getText().toString());
                break;
            case R.id.save:
                saveFile(fileName.getText().toString());
                break;
        }
       return true;
    }

    private void openFile(String fileName){
        if (fileName.isEmpty()){
            Toast.makeText(this,"Введите название файла!",Toast.LENGTH_SHORT).show();
        }else {
            try {
                InputStream inputStream = openFileInput(fileName);

                if (inputStream != null) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder builder = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    inputStream.close();
                    text.setText(builder.toString());
                }
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "Произошла ошибка файл не найден!: " + e.toString(), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveFile(String fileName){
        if (fileName.isEmpty()){
            Toast.makeText(this,"Введите название файла!",Toast.LENGTH_SHORT).show();
        }else{
            try {
                OutputStream outputStream = openFileOutput(fileName, 0);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                outputStreamWriter.write(text.getText().toString());
                outputStreamWriter.close();
                Toast.makeText(this,"Файл успешно сохранен!",Toast.LENGTH_SHORT).show();
            }catch (FileNotFoundException e) {
                Toast.makeText(this, "Произошла ошибка :"+e.toString(),Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                Toast.makeText(this,"Произошла ошибка при записи файла!:"+e.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
