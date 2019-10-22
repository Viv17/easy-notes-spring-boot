package com.example.easynotes.service;

import com.example.easynotes.dto.NoteDTO;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.mapper.NoteMapper;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteMapper noteMapper;

    @Override
    public List<NoteDTO> findAll() {
        List<Note> allNotes = noteRepository.findAll();
        return noteMapper.convertToNoteDTOS(allNotes);
    }

    @Override
    public NoteDTO create(NoteDTO noteDTO) {
        Note createdNote = noteRepository.save(noteMapper.convertToNote(noteDTO));
        return noteMapper.convertToNoteDTO(createdNote);
    }

    @Override
    public NoteDTO getById(String  noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        return noteMapper.convertToNoteDTO(note);
    }

    @Override
    public NoteDTO deleteById(String noteId) {
        Note note =  noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        noteRepository.delete(note);
        return noteMapper.convertToNoteDTO(note);
    }

    @Override
    public NoteDTO update(String noteId,NoteDTO noteDetails) {
        Note note =  noteRepository.findById(noteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setUpdatedAt(new Date());
        return noteMapper.convertToNoteDTO(noteRepository.save(note));
    }
}
