package net.ausiasmarch.persutil.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.ausiasmarch.persutil.entity.PavonEntity;
import net.ausiasmarch.persutil.repository.PavonRepository;

public class PavonService {

    @Autowired
    PavonRepository oPavonRepository;

     // ----------------------------CRUD---------------------------------
    public PavonEntity get(Long id) {
        return oPavonRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Long create(PavonEntity pavonEntity) {
        pavonEntity.setFechaCreacion(LocalDateTime.now());
        pavonEntity.setFechaModificacion(null);
        oPavonRepository.save(pavonEntity);
        return pavonEntity.getId();
    }

    public Long update(PavonEntity pavonEntity) {
        PavonEntity existingBlog = oPavonRepository.findById(pavonEntity.getId())
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        existingBlog.setTitulo(pavonEntity.getTitulo());
        existingBlog.setUrl(pavonEntity.getUrl());
        existingBlog.setEtiquetas(pavonEntity.getEtiquetas());
        existingBlog.setFechaModificacion(LocalDateTime.now());
        oPavonRepository.save(existingBlog);
        return existingBlog.getId();
    }

    public Long delete(Long id) {
        oPavonRepository.deleteById(id);
        return id;
    }

    public Page<PavonEntity> getPage(Pageable oPageable) {
        return oPavonRepository.findAll(oPageable);
    }

    public Long count() {
        return oPavonRepository.count();
    }

}
