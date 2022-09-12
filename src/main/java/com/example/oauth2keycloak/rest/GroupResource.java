package com.example.oauth2keycloak.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/group")
public class GroupResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupResource.class);
    private final List<Group> groups = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Group>> getAll(){
        return ResponseEntity.ok(groups);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Group dto){

        Group group = new Group();
        group.setId(UUID.randomUUID().toString());
        group.setCode(dto.getCode());
        group.setName(dto.getName());

        groups.add(group);

        return ResponseEntity.ok("OK");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Group dto){
        if(ObjectUtils.isEmpty(dto.getId())){
            throw new RuntimeException("Group not found");
        }

        groups.stream()
                .filter(g -> g.getId().equals(dto.getId()))
                .findFirst()
                .ifPresent(g -> {
                    g.setCode(dto.getCode());
                    g.setName(dto.getName());
                });

        return ResponseEntity.ok("OK");
    }

    @DeleteMapping
    public ResponseEntity<String> delete(String id){
        while(groups.iterator().hasNext()){
            Group g = groups.iterator().next();
            if(g.getId().equals(id)){
                groups.iterator().remove();
                break;
            }
        }
        return ResponseEntity.ok("OK");
    }

}
