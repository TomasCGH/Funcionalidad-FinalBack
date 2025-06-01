package co.edu.uco.backend.businesslogic.assembler;

import co.edu.uco.backend.businesslogic.businesslogic.domain.CanchaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.domain.TipoCanchaDomain;
import co.edu.uco.backend.entity.CanchaEntity;
import co.edu.uco.backend.entity.TipoCanchaEntity;

import java.util.List;

public interface EntityAssembler<E, D> {

    E toEntity(D domain);

    D toDomain(E entity);


    List<D> toDomain(List<E> entityList);

    List<E> toEntity(List<D> domainList);

}
