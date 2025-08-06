package com.janzdev.restfulapi.service;

import com.janzdev.restfulapi.entity.Local;
import com.janzdev.restfulapi.error.LocalNotFoundException;
import com.janzdev.restfulapi.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocalServiceImpl implements LocalService{

    @Autowired
    LocalRepository localRepository;

    @Override
    public List<Local> findAllLocals() {
        return localRepository.findAll();
    }

    @Override
    public Local saveLocal(Local local) {
        return localRepository.save(local);
    }

    @Override
    public Local updateLocal(Long id, Local local) {
        Local localDB = localRepository.findById(id).get();
        if (Objects.nonNull(local.getCode()) && !"".equalsIgnoreCase(local.getCode())) {
            localDB.setCode(local.getCode());
        }
        if (Objects.nonNull(local.getFloor()) && !"".equalsIgnoreCase(local.getFloor())) {
            localDB.setFloor(local.getFloor());
        }
        if (Objects.nonNull(local.getName()) && !"".equalsIgnoreCase(local.getName())) {
            localDB.setName(local.getName());
        }
        return localRepository.save(localDB);
    }

    @Override
    public void deleteLocal(Long id) {
        localRepository.deleteById(id);
    }

    @Override
    public Optional<Local> findLocalByNameWithJPQL(String name) {
        return localRepository.findLocalByNameWithJPQL(name);
    }

    @Override
    public Optional<Local> findByName(String name) {
        return localRepository.findByName(name);
    }

    @Override
    public Optional<Local> findByNameIgnoreCase(String name) {
        return localRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Local findLocalById(Long id) throws LocalNotFoundException {
        Optional<Local> local = localRepository.findById(id);
        if (!local.isPresent()) {
            throw new LocalNotFoundException("Local is not available");
        }
        return local.get();
    }
}
