package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeMBeanException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j

public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch(Exception e){
            log.error("Excepion ",e);
            throw new RuntimeException("An error occurred while savng the data");
        }

    }
    public void saveEntry(JournalEntry journalEntry){
        try{

            journalEntryRepository.save(journalEntry);
        }catch(Exception e){
            log.error("Excepion ",e);
        }

    }


    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);

    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed =false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed) {
                userService.saveUser(user);

                journalEntryRepository.deleteById(id);
            }

        }catch(Exception e){
            log.error("Error",e);
            throw new RuntimeException("Am error occurred while deleting the entry. ", e);
        }
        return removed;
    }

}

//controller->> services->>repository

