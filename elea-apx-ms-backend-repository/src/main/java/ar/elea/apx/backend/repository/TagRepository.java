package ar.elea.apx.backend.repository;

import ar.elea.apx.backend.entity.Tag;
import ar.elea.apx.backend.entity.TagType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Guillermo Nasi
 */
public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByNombreIgnoreCase(String nombre);

    Tag findByNombreIgnoreCaseAndTipo(String nombre, TagType tipo);


    List<Tag> findByNombreContainsAndTipo(@Param("nombre") String nombre, @Param("tipo") TagType tipo);

    List<Tag> findByTipo(TagType tagType);
}
