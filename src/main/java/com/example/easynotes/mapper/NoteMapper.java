package com.example.easynotes.mapper;

import com.example.easynotes.dto.NoteDTO;
import com.example.easynotes.model.Note;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class NoteMapper {

    public NoteDTO convertToNoteDTO(Note note){
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.get_id());
        noteDTO.setContent(note.getContent());
        noteDTO.setTitle(note.getTitle());
        if(null != note.getUpdatedAt()){
            noteDTO.setModifiedDate(note.getUpdatedAt());
        }else {
            noteDTO.setModifiedDate(note.getCreatedAt());
        }
        return noteDTO;
    }

    public List<NoteDTO> convertToNoteDTOS(List<Note> notes){
        return notes.stream().map(note -> convertToNoteDTO(note)).collect(Collectors.toList());
    }

    public Note convertToNote(NoteDTO noteDTO){
        Note note = new Note();
        note.set_id(UUID.randomUUID().toString());
        note.setContent(noteDTO.getContent());
        note.setTitle(noteDTO.getTitle());
        note.setCreatedAt(new Date());
        return note;
    }

}
