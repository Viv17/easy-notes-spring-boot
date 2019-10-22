package com.example.easynotes.service;

import com.example.easynotes.dto.NoteDTO;

import java.util.List;

public interface NoteService {

    List<NoteDTO> findAll();
    NoteDTO create(NoteDTO note);
    NoteDTO getById(String id);
    NoteDTO deleteById(String id);
    NoteDTO update(String id,NoteDTO note);
}
