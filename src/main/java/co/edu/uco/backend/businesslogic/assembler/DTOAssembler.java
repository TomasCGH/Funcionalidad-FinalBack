package co.edu.uco.backend.businesslogic.assembler;

import java.util.List;

public interface DTOAssembler <T, D> {
    D toDomain(T dto);

    T toDTO(D domain);

    List<T> toDTOs(List<D> domainList);

    List<D> toDomains(List<T> dtoList);
}
