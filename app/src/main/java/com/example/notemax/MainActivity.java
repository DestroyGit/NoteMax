package com.example.notemax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {
    int count = 0; // счетчик количества заходов в метод addFragment. Для добавления в бэкстек всего кроме первого MAIN

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(new ListFragment());
        initViews();
    }

    public void initViews(){
        initToolbar();
    }

    public void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_main:
                addFragment(new ListFragment());
                break;
            case R.id.action_favorite:
                addFragment(new FavoriteFragment());
                break;
            case R.id.action_help:
                addFragment(new HelpFragment());
                break;
            case R.id.action_settings:
                addFragment(new SettingsFragment());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
//        MenuItem search = menu.findItem(R.id.action_search); // поиск "поиска"
//        SearchView searchText = (SearchView) search.getActionView(); // строка поиска
//        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) { // реакция на полный ввод в поисковике
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) { // реакция на посимволный ввод
//                return true;
//            }
//        });
        return true;
    }

    public void addFragment(Fragment fragment){
        //Получить менеджер фрагментов
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Открыть транзакцию
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Заменить фрагмент
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (count != 0){
            // Добавить транзакцию в бэкстек
            fragmentTransaction.addToBackStack(null);
        }
        count++;
        // Закрыть транзакцию
        fragmentTransaction.commit();
    }
}
