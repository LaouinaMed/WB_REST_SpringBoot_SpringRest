package com.example.rest.dao;

import com.example.rest.domaine.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoImpl implements IDao{

    private static final List<Article> database = new ArrayList<>
            (
                    Arrays.asList(
                            new Article(1L, "PC PORTABLE HP I7", 15000d, 10d),
                            new Article(2L, "ECRAN", 1500d, 10d),
                            new Article(3L, "CAMERA LG", 3000d, 10d),
                            new Article(4L, "SOURIS", 200d, 10d)));

    @Override
    public Article findById(Long id) {
        return null;
    }

    @Override
    public List<Article> findAll() {
        return null;
    }

    @Override
    public void save(Article article) {

    }
//HELLP
    //HELLP
    @Override
    public void deleteById(Long id) {

    }
}
