package com.mythoshibernate.mythoshibernate.dao;

import com.mythoshibernate.mythoshibernate.domain.Mytho;

import java.util.List;

public interface MythoDAO {
    void saveMytho(Mytho mytho);

    Mytho getMythoById(int id);

    List<Mytho> getAllMythos();

    void updateMytho(Mytho mytho);

    void deleteMythoById(int id);
}
