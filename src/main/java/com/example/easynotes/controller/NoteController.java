package com.example.easynotes.controller;

import com.example.easynotes.dto.NoteDTO;
import com.example.easynotes.repository.NoteRepository;
import com.example.easynotes.service.NoteServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value="Easy Notes API", description = "List all operations of the easy notes API")
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteServiceImpl noteService;

    // Get All notes
    @GetMapping(value = "/notes",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllNotes(){
        return ResponseEntity.ok(noteService.findAll());
    }

    // Create a new Note
    @PostMapping(value = "/notes",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNote(@Valid @RequestBody NoteDTO noteDTO){
        return ResponseEntity.ok(noteService.create(noteDTO));
    }

    // Get a Single Note
    @GetMapping(value = "/notes/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getNoteById(@PathVariable(value = "id") String noteId){
        return ResponseEntity.ok(noteService.getById(noteId));
    }
    // Update a Note
    @PutMapping("/notes/{id}")
    public ResponseEntity<?> updateNoteById(@Valid @PathVariable(value = "id") String noteId, @RequestBody NoteDTO noteDetails){
        return ResponseEntity.ok(noteService.update(noteId,noteDetails));
    }

    // Delete a Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNoteById(@Valid @PathVariable(value= "id")String noteId){
        return ResponseEntity.ok(noteService.deleteById(noteId));
    }
}
