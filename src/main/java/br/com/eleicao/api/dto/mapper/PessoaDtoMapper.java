package br.com.eleicao.api.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.eleicao.api.domain.Pessoa;
import br.com.eleicao.api.dto.PessoaEdition;

@Mapper(componentModel = "spring", uses = {})
public interface PessoaDtoMapper {

    // Edition
    Pessoa editionToDomain(PessoaEdition dto);

    @InheritInverseConfiguration
    PessoaEdition domainToEdition(Pessoa domain);
}
