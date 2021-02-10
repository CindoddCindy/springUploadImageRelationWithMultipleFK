package com.uirmfk.uirmfk.relation.services;

import com.uirmfk.uirmfk.relation.model.ImageDescription;
import com.uirmfk.uirmfk.relation.model.Item;
import com.uirmfk.uirmfk.relation.repository.ImageDescriptionRepository;
import com.uirmfk.uirmfk.relation.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    private ImageDescriptionRepository imageDescriptionRepository;

    public ItemService(ItemRepository itemRepository,ImageDescriptionRepository imageDescriptionRepository ) {
        this.itemRepository = itemRepository;
        this.imageDescriptionRepository = imageDescriptionRepository;
    }

    /**
     * Create a new role along with users
     */

    @Transactional
    public ResponseEntity<Object> addItem(Item item) {

        Item newItem = new Item();
        newItem.setNameItem(item.getNameItem());
        newItem.setEmailItem(item.getEmailItem());

        newItem.setUsers(item.getUsers());
        newItem.setImageDescriptions(item.getImageDescriptions());
        Item savedItem = itemRepository.save(newItem);
        if (itemRepository.findById(savedItem.getId()).isPresent()) {
            return ResponseEntity.accepted().body("Successfully Created Role and Users");
        } else
            return ResponseEntity.unprocessableEntity().body("Failed to Create specified Role");
    }

    public ResponseEntity<Object> deleteItem(Long id) {
        if (itemRepository.findById(id).isPresent()) {
            itemRepository.deleteById(id);
            if (itemRepository.findById(id).isPresent()) {
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }

    public Item getItem(Long id) {
        if(itemRepository.findById(id).isPresent()) {
            Item item = itemRepository.findById(id).get();
            Item itemModel = new Item();
            itemModel.setNameItem(item.getNameItem());
            itemModel.setEmailItem(item.getEmailItem());
            itemModel.setUsers(item.getUsers());
            itemModel.setImageDescriptions(item.getImageDescriptions());
            return itemModel;
        } else return null;
    }

}
