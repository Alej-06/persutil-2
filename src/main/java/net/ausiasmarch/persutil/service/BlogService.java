package net.ausiasmarch.persutil.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.persutil.entity.BlogEntity;
import net.ausiasmarch.persutil.repository.BlogRepository;

@Service
public class BlogService {

    @Autowired
    BlogRepository oBlogRepository;

    @Autowired
    AleatorioService oAleatorioService;

    ArrayList<String> alFrases = new ArrayList<>();

    public BlogService() {
        alFrases.add("No es profesor el que sabe, sino el que enseña.");
        alFrases.add("Quien da, recibirá.");
        alFrases.add("Aquel que presume sin cesar, solo refleja su inseguridad.");
        alFrases.add("El que madruga, Dios le ayuda.");
        alFrases.add("Solo aquel que ha sellado un pacto con su propia muerte tiene derecho a tomar la vida de otro.");
        alFrases.add("La esperanza es lo último que se pierde.");
        alFrases.add("La confianza es fácil de perder, pero difícil de ganar.");
        alFrases.add("Prefiero que me claven mil espadas antes que morder la mano que me da de comer.");
        alFrases.add("Hay que trabajar duro, estudiar bien, comer y descansar mucho. Ese es el lema de la Escuela Tortuga.");
        alFrases.add("Un Dios no es nada ante un no creyente.");
        alFrases.add("Cada derrota es una pequeña victoria.");
        alFrases.add("Hasta el guerrero de clase más baja se puede enfrentar a la élite si entrena lo suficiente.");
        alFrases.add("Dos en armonía superan a uno en perfección.");
        alFrases.add("Viaje antes que destino.");
        alFrases.add("Verdad antes que mentira.");
        alFrases.add("Vida antes que muerte.");
    }

    public Long rellenaBlog() {
        BlogEntity oBlogEntity = new BlogEntity();        
        oBlogEntity.setTitulo( alFrases.get(oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, alFrases.size() - 1)));
        String contenidoGenerado = "";
        int numFrases=oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(1, 30);
        for (int i=1; i<=numFrases; i++) {
            contenidoGenerado += alFrases.get(oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, alFrases.size() - 1)) + " ";
            if (oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, 10) == 1) {
                contenidoGenerado += "\n";
            }
        }
        oBlogEntity.setContenido(contenidoGenerado.trim());
                contenidoGenerado += "\n";
        // extraer 5 palabras aleatorias del contenido  para las etiquetas
        String[] palabras = contenidoGenerado.split(" ");
        // eliminar signos de puntuacion de las palabras
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].replace(".", "").replace(",", "").replace(";", "").replace(":", "").replace("!", "").replace("?", "");
        }   
        // convertir todas las palabras a minúsculas
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].toLowerCase();
        }
        // seleccionar palabras de más de 4 letras
        ArrayList<String> alPalabrasFiltradas = new ArrayList<>();
        for (String palabra : palabras) {
            if (palabra.length() > 4 && !alPalabrasFiltradas.contains(palabra)) {
                alPalabrasFiltradas.add(palabra);
            }
        }
        palabras = alPalabrasFiltradas.toArray(new String[0]);
        String etiquetas = "";
        for (int i = 0; i < 5; i++) {
            String palabra = palabras[oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, palabras.length - 1)];
            if (!etiquetas.contains(palabra)) {
                etiquetas += palabra + ", ";
            }
        }
        if (etiquetas.endsWith(", ")) {
            etiquetas = etiquetas.substring(0, etiquetas.length() - 2);
        }
        oBlogEntity.setEtiquetas(etiquetas);
        oBlogEntity.setFechaCreacion(LocalDateTime.now());
        oBlogEntity.setFechaModificacion(null);
        oBlogRepository.save(oBlogEntity);
        return oBlogRepository.count();
    }

    public Long rellenaBlog(Long num) {

        for (long j = 0;j<num;j++){
        //Esto crea la entidad blog y la rellena
        BlogEntity oBlogEntity = new BlogEntity();        
        oBlogEntity.setTitulo( alFrases.get(oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, alFrases.size() - 1)));
        //rellena contenido
        String contenidoGenerado = "";
        int numFrases=oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(1, 30);
        for (int i=1; i<=numFrases; i++) {
            contenidoGenerado += alFrases.get(oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, alFrases.size() - 1)) + " ";
            if (oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, 10) == 1) {
                contenidoGenerado += "\n";
            }
        }
        oBlogEntity.setContenido(contenidoGenerado.trim());
                contenidoGenerado += "\n";
        // extraer 5 palabras aleatorias del contenido  para las etiquetas
        String[] palabras = contenidoGenerado.split(" ");
        // eliminar signos de puntuacion de las palabras
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].replace(".", "").replace(",", "").replace(";", "").replace(":", "").replace("!", "").replace("?", "");
        }   
        // convertir todas las palabras a minúsculas
        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].toLowerCase();
        }
        // seleccionar palabras de más de 4 letras
        ArrayList<String> alPalabrasFiltradas = new ArrayList<>();
        for (String palabra : palabras) {
            if (palabra.length() > 4 && !alPalabrasFiltradas.contains(palabra)) {
                alPalabrasFiltradas.add(palabra);
            }
        }
        palabras = alPalabrasFiltradas.toArray(new String[0]);
        String etiquetas = "";
        for (int i = 0; i < 5; i++) {
            String palabra = palabras[oAleatorioService.GenerarNumeroAleatorioEnteroEnRango(0, palabras.length - 1)];
            if (!etiquetas.contains(palabra)) {
                etiquetas += palabra + ", ";
            }
        }
        //Eliminar unltima coma
        if (etiquetas.endsWith(", ")) {
            etiquetas = etiquetas.substring(0, etiquetas.length() - 2);
        }
        oBlogEntity.setEtiquetas(etiquetas);
        oBlogEntity.setFechaCreacion(LocalDateTime.now());
        oBlogEntity.setFechaModificacion(null);
        oBlogRepository.save(oBlogEntity);
    }
        return oBlogRepository.count();
    }

    // ----------------------------CRUD---------------------------------

    public BlogEntity get(Long id) {
        return oBlogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public Long create(BlogEntity blogEntity) {
        blogEntity.setFechaCreacion(LocalDateTime.now());
        blogEntity.setFechaModificacion(null);
        oBlogRepository.save(blogEntity);
        return blogEntity.getId();
    }

    public Long update(BlogEntity blogEntity) {
        BlogEntity existingBlog = oBlogRepository.findById(blogEntity.getId())
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        existingBlog.setTitulo(blogEntity.getTitulo());
        existingBlog.setContenido(blogEntity.getContenido());
        existingBlog.setEtiquetas(blogEntity.getEtiquetas());
        existingBlog.setFechaModificacion(LocalDateTime.now());
        oBlogRepository.save(existingBlog);
        return existingBlog.getId();
    }

    public Long delete(Long id) {
        oBlogRepository.deleteById(id);
        return id;
    }

    public Page<BlogEntity> getPage(Pageable oPageable) {
        return oBlogRepository.findAll(oPageable);
    }

    public Long count() {
        return oBlogRepository.count();
    }   

}